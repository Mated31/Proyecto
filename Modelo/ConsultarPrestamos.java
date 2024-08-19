/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ConsultarPrestamos extends Conexion {

    public boolean registrarPrestamo(Prestamos prestamo) {
        Connection con = getConexion();
        PreparedStatement ps = null;
        String sqlInsert = "INSERT INTO prestamos (id_libro, id_miembro, fecha_prestamo) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE libros SET cantidad = cantidad - 1 WHERE id_libro = ?";
        String sqlCheck = "SELECT cantidad FROM libros WHERE id_libro = ?";
        
        try {
            // Iniciar transacción
            con.setAutoCommit(false);

            // Verificar cantidad disponible
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, prestamo.getIdLibro());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                if (cantidad <= 0) {
                    // No hay libros disponibles
                    con.rollback();
                    return false;
                }
            } else {
                // Libro no encontrado
                con.rollback();
                return false;
            }

            // Registrar el préstamo
            ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, prestamo.getIdLibro());
            ps.setInt(2, prestamo.getIdMiembro());
            ps.setDate(3, prestamo.getFechaPrestamo());
            ps.executeUpdate();

            // Restar cantidad de libros disponibles
            ps = con.prepareStatement(sqlUpdate);
            ps.setInt(1, prestamo.getIdLibro());
            ps.executeUpdate();

            // Confirmar transacción
            con.commit();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            try {
                // Revertir transacción en caso de error
                con.rollback();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
                if (ps != null) ps.close();
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para listar los préstamos
    public DefaultTableModel listarPrestamos() {
        Connection con = getConexion();
        DefaultTableModel tableM = new DefaultTableModel();
        tableM.addColumn("ID Préstamo");
        tableM.addColumn("ID Libro");
        tableM.addColumn("ID Miembro");
        tableM.addColumn("Fecha de Préstamo");

        String sql = "SELECT id_prestamo, id_libro, id_miembro, fecha_prestamo FROM prestamos";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("id_prestamo");
                fila[1] = rs.getInt("id_libro");
                fila[2] = rs.getInt("id_miembro");
                fila[3] = rs.getDate("fecha_prestamo");

                tableM.addRow(fila);
            }

            rs.close();
            ps.close();
            return tableM;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
