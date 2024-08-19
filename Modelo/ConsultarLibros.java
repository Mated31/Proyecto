/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase para gestionar operaciones relacionadas con los libros en la base de datos.
 */
public class ConsultarLibros extends Conexion {

    /**
     * Registra un nuevo libro en la base de datos.
     * @param lib Objeto Libros que contiene los datos del libro.
     * @return true si el libro se registró exitosamente, false en caso contrario.
     */
    public boolean registrar(Libros lib) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO libros (id_libro, autor, titulo, genero, cantidad) VALUES (?, ?, ?, ?, ?)";

        try {
            // Verificar si el ID del libro ya existe
            if (existeId(lib.getId_libro())) {
                System.err.println("El ID de libro ya existe.");
                return false;
            }
            
            // Preparar y ejecutar la consulta SQL para insertar el nuevo libro
            ps = con.prepareStatement(sql);
            ps.setInt(1, lib.getId_libro());
            ps.setString(2, lib.getAutor());
            ps.setString(3, lib.getTitulo());
            ps.setString(4, lib.getGenero());
            ps.setInt(5, lib.getCantidad());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión y el PreparedStatement
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Busca un libro en la base de datos por su ID.
     * @param lib Objeto Libros en el que se almacenarán los datos del libro encontrado.
     * @return true si el libro fue encontrado, false en caso contrario.
     */
    public boolean buscar(Libros lib) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM libros WHERE id_libro=?";

        try {
            // Preparar y ejecutar la consulta SQL para buscar el libro
            ps = con.prepareStatement(sql);
            ps.setInt(1, lib.getId_libro());
            rs = ps.executeQuery();

            // Si se encuentra el libro, llenar los datos en el objeto Libros
            if (rs.next()) {
                lib.setAutor(rs.getString("autor"));
                lib.setTitulo(rs.getString("titulo"));
                lib.setGenero(rs.getString("genero"));
                lib.setCantidad(rs.getInt("cantidad"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión, el ResultSet y el PreparedStatement
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Modifica los datos de un libro existente en la base de datos.
     * @param lib Objeto Libros con los nuevos datos del libro.
     * @return true si la modificación fue exitosa, false en caso contrario.
     */
    public boolean modificar(Libros lib) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE libros SET autor=?, titulo=?, genero=?, cantidad=? WHERE id_libro=?";

        try {
            // Verificar si el ID del libro existe antes de intentar modificarlo
            if (!existeId(lib.getId_libro())) {
                System.err.println("El ID de libro no existe.");
                return false;
            }
            
            // Preparar y ejecutar la consulta SQL para modificar el libro
            ps = con.prepareStatement(sql);
            ps.setString(1, lib.getAutor());
            ps.setString(2, lib.getTitulo());
            ps.setString(3, lib.getGenero());
            ps.setInt(4, lib.getCantidad());
            ps.setInt(5, lib.getId_libro());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión y el PreparedStatement
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Elimina un libro de la base de datos por su ID.
     * @param lib Objeto Libros que contiene el ID del libro a eliminar.
     * @return true si el libro fue eliminado exitosamente, false en caso contrario.
     */
    public boolean eliminar(Libros lib) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM libros WHERE id_libro=?";

        try {
            // Verificar si el ID del libro existe antes de intentar eliminarlo
            if (!existeId(lib.getId_libro())) {
                System.err.println("El ID de libro no existe.");
                return false;
            }
            
            // Preparar y ejecutar la consulta SQL para eliminar el libro
            ps = con.prepareStatement(sql);
            ps.setInt(1, lib.getId_libro());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión y el PreparedStatement
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Lista todos los libros en un DefaultTableModel para su uso en una JTable.
     * @return Un DefaultTableModel con los datos de todos los libros.
     */
    public DefaultTableModel listarLibros() {
        Connection con = getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Autor");
        modelo.addColumn("Título");
        modelo.addColumn("Género");
        modelo.addColumn("Cantidad");

        String sql = "SELECT id_libro, autor, titulo, genero, cantidad FROM libros";

        try {
            // Preparar y ejecutar la consulta SQL para listar los libros
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Agregar cada fila de resultados al modelo de la tabla
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_libro");
                fila[1] = rs.getString("autor");
                fila[2] = rs.getString("titulo");
                fila[3] = rs.getString("genero");
                fila[4] = rs.getInt("cantidad");

                modelo.addRow(fila);
            }

            rs.close();
            ps.close();
            return modelo;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            // Cerrar la conexión y el PreparedStatement
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Verifica si un ID de libro ya existe en la base de datos.
     * @param id_libro El ID del libro a verificar.
     * @return true si el ID existe, false si no.
     */
    public boolean existeId(int id_libro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT COUNT(*) FROM libros WHERE id_libro=?";

        try {
            // Preparar y ejecutar la consulta SQL para verificar la existencia del ID
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_libro);
            rs = ps.executeQuery();

            // Retorna true si el ID existe, false si no
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión, el ResultSet y el PreparedStatement
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
