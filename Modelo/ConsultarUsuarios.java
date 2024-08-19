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

public class ConsultarUsuarios extends Conexion {

    // Método para registrar un nuevo usuario
    public boolean registrar(Usuarios user) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // Consulta SQL para insertar un nuevo usuario
        String sql = "INSERT INTO usuarios (nombre, apellido, usuario, password, telefono) VALUES (?, ?, ?, ?, ?)";

        try {
            // Preparar la consulta SQL con los parámetros proporcionados
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre()); // Establecer el nombre
            ps.setString(2, user.getApellido()); // Establecer el apellido
            ps.setString(3, user.getUsuario()); // Establecer el nombre de usuario
            ps.setString(4, user.getPassword()); // Establecer la contraseña
            ps.setString(5, user.getTelefono()); // Establecer el teléfono
            // Ejecutar la consulta para insertar el nuevo usuario
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Manejo de excepciones SQL, imprime el error en la consola
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para buscar un usuario por ID
    public boolean buscar(Usuarios user) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        // Consulta SQL para seleccionar un usuario por su ID
        String sql = "SELECT * FROM usuarios WHERE idusuarios=?";

        try {
            // Preparar la consulta SQL con el ID del usuario
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getIdusuarios()); // Establecer el ID del usuario
            // Ejecutar la consulta para buscar el usuario
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si se encuentra el usuario, establecer los atributos del objeto Usuario
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setUsuario(rs.getString("usuario"));
                user.setPassword(rs.getString("password"));
                user.setTelefono(rs.getString("telefono"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            // Manejo de excepciones SQL, imprime el error en la consola
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión y ResultSet en el bloque finally para asegurar el cierre en caso de error
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para modificar un usuario existente
    public boolean modificar(Usuarios user) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // Consulta SQL para actualizar un usuario existente
        String sql = "UPDATE usuarios SET nombre=?, apellido=?, usuario=?, password=?, telefono=? WHERE idusuarios=?";

        try {
            // Preparar la consulta SQL con los parámetros proporcionados
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre()); // Establecer el nombre
            ps.setString(2, user.getApellido()); // Establecer el apellido
            ps.setString(3, user.getUsuario()); // Establecer el nombre de usuario
            ps.setString(4, user.getPassword()); // Establecer la contraseña
            ps.setString(5, user.getTelefono()); // Establecer el teléfono
            ps.setInt(6, user.getIdusuarios()); // Establecer el ID del usuario
            // Ejecutar la consulta para actualizar el usuario
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Manejo de excepciones SQL, imprime el error en la consola
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para eliminar un usuario por ID
    public boolean eliminar(Usuarios user) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // Consulta SQL para eliminar un usuario por su ID
        String sql = "DELETE FROM usuarios WHERE idusuarios=?";

        try {
            // Preparar la consulta SQL con el ID del usuario
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getIdusuarios()); // Establecer el ID del usuario
            // Ejecutar la consulta para eliminar el usuario
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Manejo de excepciones SQL, imprime el error en la consola
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para listar todos los usuarios
    public DefaultTableModel listarUsuarios() {
        Connection con = getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        // Definir las columnas de la tabla
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Usuario");
        modelo.addColumn("Password");
        modelo.addColumn("Telefono");

        // Consulta SQL para seleccionar todos los usuarios
        String sql = "SELECT idusuarios, nombre, apellido, usuario, password, telefono FROM usuarios";

        try {
            // Preparar la consulta SQL
            PreparedStatement ps = con.prepareStatement(sql);
            // Ejecutar la consulta para obtener todos los usuarios
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Crear una fila con los datos del usuario
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("idusuarios");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("usuario");
                fila[4] = rs.getString("password");
                fila[5] = rs.getString("telefono");

                // Añadir la fila a la tabla
                modelo.addRow(fila);
            }

            rs.close();
            ps.close();
            return modelo;

        } catch (SQLException e) {
            // Manejo de excepciones SQL, imprime el error en la consola
            System.err.println(e);
            return null;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
