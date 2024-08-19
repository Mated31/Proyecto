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

/**
 * Clase para gestionar las operaciones relacionadas con las devoluciones de libros.
 */
public class ConsultarDevoluciones extends Conexion {

    /**
     * Registra una devolución en la base de datos.
     * @param devolucion Objeto Devoluciones que contiene los datos de la devolución.
     * @return true si la devolución se registró exitosamente, false en caso contrario.
     */
    public boolean registrarDevolucion(Devoluciones devolucion) {
        
        // Obtener la conexión a la base de datos
        Connection con = getConexion();
        
        // Inicializar PreparedStatement
        PreparedStatement ps = null;
        
        // Consulta SQL para verificar si el préstamo existe
        String sqlCheck = "SELECT COUNT(*) FROM prestamos WHERE id_libro = ? AND id_miembro = ?";
        
        // Consulta SQL para eliminar el préstamo correspondiente
        String sqlDelete = "DELETE FROM prestamos WHERE id_libro = ? AND id_miembro = ?";
        
        // Consulta SQL para actualizar la cantidad de libros disponibles
        String sqlUpdate = "UPDATE libros SET cantidad = cantidad + 1 WHERE id_libro = ?";
        
        // Consulta SQL para insertar una nueva devolución
        String sqlInsert = "INSERT INTO devoluciones (id_libro, id_miembro, fecha_devolucion) VALUES (?, ?, ?)";

        try {
            // Iniciar transacción
            con.setAutoCommit(false);

            // Preparar la consulta para verificar si el préstamo existe
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, devolucion.getIdLibro());
            ps.setInt(2, devolucion.getIdMiembro());
            
            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = ps.executeQuery();
            
            // Verificar si no se encontró un préstamo correspondiente
            if (rs.next() && rs.getInt(1) == 0) {
                // Mostrar mensaje de error
                System.err.println("No se encontró un préstamo correspondiente para la devolución.");
                
                // Revertir transacción
                con.rollback();
                return false;
            }

            // Preparar la consulta para registrar la devolución
            ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, devolucion.getIdLibro());
            ps.setInt(2, devolucion.getIdMiembro());
            ps.setDate(3, new java.sql.Date(devolucion.getFechaDevolucion().getTime()));
            
            // Ejecutar la consulta para registrar la devolución
            ps.executeUpdate();

            // Preparar la consulta para eliminar el préstamo correspondiente
            ps = con.prepareStatement(sqlDelete);
            ps.setInt(1, devolucion.getIdLibro());
            ps.setInt(2, devolucion.getIdMiembro());
            
            // Ejecutar la consulta para eliminar el préstamo
            ps.executeUpdate();

            // Preparar la consulta para actualizar la cantidad de libros disponibles
            ps = con.prepareStatement(sqlUpdate);
            ps.setInt(1, devolucion.getIdLibro());
            
            // Ejecutar la consulta para actualizar la cantidad de libros
            ps.executeUpdate();

            // Confirmar transacción
            con.commit();
            return true;
            
        // Manejar excepciones SQL
        } catch (SQLException e) {
            // Mostrar mensaje de error
            System.err.println("Error en la transacción: " + e.getMessage());
            try {
                // Revertir transacción en caso de error
                con.rollback();
            } catch (SQLException ex) {
                // Mostrar mensaje de error al revertir la transacción
                System.err.println("Error al revertir la transacción: " + ex.getMessage());
            }
            return false;
        } finally {
            try {
                // Restaurar el auto commit
                con.setAutoCommit(true);
                
                // Cerrar PreparedStatement si no es nulo
                if (ps != null) ps.close();
                
                // Cerrar la conexión
                con.close();
            } catch (SQLException e) {
                // Mostrar mensaje de error al cerrar la conexión
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Lista todas las devoluciones en la base de datos.
     * @return Un DefaultTableModel con los datos de las devoluciones.
     */
    public DefaultTableModel listarDevoluciones() {
        
        // Obtener la conexión a la base de datos
        Connection con = getConexion();
        
        // Crear el modelo de la tabla
        DefaultTableModel tableM = new DefaultTableModel();
        
        // Agregar columnas al modelo de la tabla
        tableM.addColumn("ID Devolución");
        tableM.addColumn("ID Libro");
        tableM.addColumn("ID Miembro");
        tableM.addColumn("Fecha de Devolución");

        // Consulta SQL para seleccionar las devoluciones
        String sql = "SELECT id_devolucion, id_libro, id_miembro, fecha_devolucion FROM devoluciones";

        try {
            // Preparar la consulta SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = ps.executeQuery();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                // Crear un array para almacenar la fila
                Object[] fila = new Object[4];
                
                // Obtener el ID de la devolución y almacenarlo en la fila
                fila[0] = rs.getInt("id_devolucion");
                
                // Obtener el ID del libro y almacenarlo en la fila
                fila[1] = rs.getInt("id_libro");
                
                // Obtener el ID del miembro y almacenarlo en la fila
                fila[2] = rs.getInt("id_miembro");
                
                // Obtener la fecha de devolución y almacenarla en la fila
                fila[3] = rs.getDate("fecha_devolucion");

                // Agregar la fila al modelo de la tabla
                tableM.addRow(fila);
            }

            // Cerrar ResultSet y PreparedStatement
            rs.close();
            ps.close();
            return tableM;

        // Manejar excepciones SQL
        } catch (SQLException e) {
            // Mostrar mensaje de error
            System.err.println(e);
            return null;
        } finally {
            try {
                // Cerrar la conexión
                con.close();
            } catch (SQLException e) {
                // Mostrar mensaje de error al cerrar la conexión
                System.err.println(e);
            }
        }
    }
}
