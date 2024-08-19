
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
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class ConsultarMiembros extends Conexion {

    // Método para validar el formato de la cédula
    private boolean validarCedula(String cedula) {
        // Ejemplo: validar que la cédula tenga exactamente 10 dígitos
        return cedula != null && cedula.matches("\\d{10}");
    }

    // Método para registrar un nuevo miembro en la base de datos
    public boolean registrar(Miembros miembro) {
        // Verificar si la cédula es válida antes de proceder
        if (!validarCedula(miembro.getCedula())) {
            System.err.println("La cédula no es válida. Debe tener exactamente 10 dígitos.");
            return false;
        }

        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO miembros (id_miembro, nombre, apellido, cedula, direccion, telefono, email, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            ps.setInt(1, miembro.getId_miembro());
            ps.setString(2, miembro.getNombre());
            ps.setString(3, miembro.getApellido());
            ps.setString(4, miembro.getCedula());
            ps.setString(5, miembro.getDireccion());
            ps.setString(6, miembro.getTelefono());
            ps.setString(7, miembro.getEmail());
            if (miembro.getFecha_nacimiento() != null) {
                ps.setDate(8, new java.sql.Date(miembro.getFecha_nacimiento().getTime()));
            } else {
                ps.setNull(8, java.sql.Types.DATE);  
            }

            // Ejecutar la consulta de inserción
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Imprimir el error en caso de excepción
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para buscar un miembro en la base de datos por su ID
    public boolean buscar(Miembros miembro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM miembros WHERE id_miembro = ?";

        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            ps.setInt(1, miembro.getId_miembro());
            rs = ps.executeQuery();

            // Si se encuentra el miembro, actualizar los datos en el objeto
            if (rs.next()) {
                miembro.setNombre(rs.getString("nombre"));
                miembro.setApellido(rs.getString("apellido"));
                miembro.setCedula(rs.getString("cedula"));
                miembro.setDireccion(rs.getString("direccion"));
                miembro.setTelefono(rs.getString("telefono"));
                miembro.setEmail(rs.getString("email"));
                // Obtener la fecha de nacimiento
                miembro.setFecha_nacimiento(rs.getDate("fecha_nacimiento")); 
                return true;
            }
            return false;
        } catch (SQLException e) {
            // Imprimir el error en caso de excepción
            System.err.println(e);
            return false;
        } finally {
            // Cerrar los recursos en el bloque finally para asegurar el cierre en caso de error
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para modificar los datos de un miembro existente en la base de datos
    public boolean modificar(Miembros miembro) {
        // Verificar si la cédula es válida antes de proceder
        if (!validarCedula(miembro.getCedula())) {
            System.err.println("La cédula no es válida. Debe tener exactamente 10 dígitos.");
            return false;
        }

        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE miembros SET nombre = ?, apellido = ?, cedula = ?, direccion = ?, telefono = ?, email = ?, fecha_nacimiento = ? WHERE id_miembro = ?";

        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            ps.setString(1, miembro.getNombre());
            ps.setString(2, miembro.getApellido());
            ps.setString(3, miembro.getCedula());
            ps.setString(4, miembro.getDireccion());
            ps.setString(5, miembro.getTelefono());
            ps.setString(6, miembro.getEmail());
            ps.setDate(7, new java.sql.Date(miembro.getFecha_nacimiento().getTime()));
            ps.setInt(8, miembro.getId_miembro());
            // Ejecutar la consulta de actualización
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Imprimir el error en caso de excepción
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para eliminar un miembro de la base de datos por su ID
    public boolean eliminar(Miembros miembro) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM miembros WHERE id_miembro = ?";

        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            ps.setInt(1, miembro.getId_miembro());
            // Ejecutar la consulta de eliminación
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Imprimir el error en caso de excepción
            System.err.println(e);
            return false;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para listar todos los miembros en un DefaultTableModel para su uso en una JTable
    public DefaultTableModel listarMiembros() {
        Connection con = getConexion();
        DefaultTableModel tableM = new DefaultTableModel();
        tableM.addColumn("ID");
        tableM.addColumn("Nombre");
        tableM.addColumn("Apellido");
        tableM.addColumn("Cedula");
        tableM.addColumn("Dirección");
        tableM.addColumn("Teléfono");
        tableM.addColumn("Email");
        tableM.addColumn("Fecha de Nacimiento"); // Agregar columna de fecha de nacimiento

        String sql = "SELECT id_miembro, nombre, apellido, cedula, direccion, telefono, email, fecha_nacimiento FROM miembros";

        try {
            // Preparar la consulta SQL
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Procesar el resultado de la consulta y agregarlo al DefaultTableModel
            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("id_miembro");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("cedula");
                fila[4] = rs.getString("direccion");
                fila[5] = rs.getString("telefono");
                fila[6] = rs.getString("email");
                fila[7] = rs.getDate("fecha_nacimiento"); // Obtener la fecha de nacimiento

                tableM.addRow(fila);
            }

            rs.close();
            ps.close();
            return tableM;

        } catch (SQLException e) {
            // Imprimir el error en caso de excepción
            System.err.println(e);
            return null;
        } finally {
            // Cerrar la conexión en el bloque finally para asegurar el cierre en caso de error
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // Método para verificar si un ID de miembro existe en la base de datos
    public boolean existeId(int id_miembro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT COUNT(*) FROM miembros WHERE id_miembro = ?";

        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_miembro);
            rs = ps.executeQuery();

            // Retornar true si el conteo es mayor que 0
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            // Imprimir el error en caso de excepción
            System.err.println(e);
            return false;
        } finally {
            // Cerrar los recursos en el bloque finally para asegurar el cierre en caso de error
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

