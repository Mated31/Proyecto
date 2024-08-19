/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos.
 */
public class Conexion {

    // Constante que define la URL de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3307/biblioteca3";
    
    // Constante que define el usuario de la base de datos
    private static final String USER = "root";
    
    // Constante que define la contraseña del usuario de la base de datos
    private static final String PASSWORD = "1234";

    /**
     * Método para establecer la conexión con la base de datos.
     * @return Una conexión a la base de datos o null si ocurre un error.
     */
    public Connection getConexion() {
        
        // Variable que almacenará la conexión
        Connection connection = null;
        
        // Intentar establecer la conexión
        try {
            // Crear una conexión con la base de datos usando la URL, usuario y contraseña
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // Imprimir un mensaje de éxito si la conexión es establecida
            System.out.println("Conexión exitosa a la base de datos.");
        
        // Capturar cualquier excepción de tipo SQLException
        } catch (SQLException e) {
            
            // Imprimir un mensaje de error si la conexión falla
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        // Retornar la conexión establecida o null si falló
        return connection;
    }
}
