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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para gestionar la autenticación de usuarios en la base de datos.
 */
public class ConsultarLogin extends Conexion {

    /**
     * Autentica un usuario en la base de datos verificando sus credenciales.
     * @param usuario Objeto Usuarios que contiene el nombre de usuario y la contraseña.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    public boolean autenticar(Usuarios usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        // Consulta SQL para verificar las credenciales del usuario
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=?";
        
        try {
            // Preparar la consulta SQL con los parámetros proporcionados
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario()); // Establecer el nombre de usuario
            ps.setString(2, usuario.getPassword()); // Establecer la contraseña
            
            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();
            
            // Retorna true si se encontró un registro que coincida con las credenciales
            return rs.next();
        } catch (SQLException e) {
            // Manejo de excepciones SQL, imprime el error en la consola
            System.err.println(e);
            return false;
        } finally {
            // Cerrar el ResultSet, PreparedStatement y la conexión
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
