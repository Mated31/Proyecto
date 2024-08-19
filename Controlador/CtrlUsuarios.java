/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Controlador;

import Modelo.Usuarios;
import Modelo.ConsultarUsuarios;
import Vista.ventanaUsuarios;
import Vista.ventanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Controlador para gestionar las operaciones relacionadas con los usuarios
 */
public class CtrlUsuarios implements ActionListener {

    // Atributos privados para el modelo, consultas, vistas y ventana principal
    private Usuarios modelo;
    private ConsultarUsuarios consultas;
    private ventanaUsuarios vista;
    private ventanaPrincipal vPrincipal;

    /**
     * Constructor para inicializar el controlador
     * @param modelo Objeto de modelo de Usuario
     * @param consultas Objeto para consultar usuarios
     * @param vista Vista de la interfaz de usuario para usuarios
     * @param vPrincipal Vista principal
     */
    public CtrlUsuarios(Usuarios modelo, ConsultarUsuarios consultas, ventanaUsuarios vista, ventanaPrincipal vPrincipal) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vPrincipal = vPrincipal;

        // Agregar ActionListeners a los botones de la vista
        this.vista.btnGuardarU.addActionListener(this);
        this.vista.btnBuscarU.addActionListener(this);
        this.vista.btnEliminarU.addActionListener(this);
        this.vista.btnLimpiarU.addActionListener(this);
        this.vista.btnModificarU.addActionListener(this);
        this.vista.btnAtrasU.addActionListener(this);
        // Llenar la tabla al iniciar el controlador
        llenarTabla();  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Manejar eventos para el botón de guardar
        if (e.getSource() == vista.btnGuardarU) {
            modelo.setNombre(vista.txtNombreU.getText());
            modelo.setApellido(vista.txtApellidosU.getText());
            modelo.setUsuario(vista.txtUsuarioU.getText());
            modelo.setPassword(vista.txtPassworsU.getText());
            modelo.setTelefono(vista.txtTelefonoU.getText());

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                limpiar();
                // Actualiza la tabla después de guardar
                llenarTabla();  
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        }

        // Manejar eventos para el botón de buscar
        if (e.getSource() == vista.btnBuscarU) {
            modelo.setIdusuarios(Integer.parseInt(vista.txtId2.getText()));

            if (consultas.buscar(modelo)) {
                vista.txtNombreU.setText(modelo.getNombre());
                vista.txtApellidosU.setText(modelo.getApellido());
                vista.txtUsuarioU.setText(modelo.getUsuario());
                vista.txtPassworsU.setText(modelo.getPassword());
                vista.txtTelefonoU.setText(modelo.getTelefono());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro");
                limpiar();
            }
        }

        // Manejar eventos para el botón de eliminar
        if (e.getSource() == vista.btnEliminarU) {
            modelo.setIdusuarios(Integer.parseInt(vista.txtId2.getText()));

            if (consultas.eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
                llenarTabla();  // Actualiza la tabla después de eliminar
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        }

        // Manejar eventos para el botón de modificar
        if (e.getSource() == vista.btnModificarU) {
            modelo.setIdusuarios(Integer.parseInt(vista.txtId2.getText()));
            modelo.setNombre(vista.txtNombreU.getText());
            modelo.setApellido(vista.txtApellidosU.getText());
            modelo.setUsuario(vista.txtUsuarioU.getText());
            modelo.setPassword(vista.txtPassworsU.getText());
            modelo.setTelefono(vista.txtTelefonoU.getText());

            if (consultas.modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro modificado");
                limpiar();
                // Actualiza la tabla después de modificar
                llenarTabla();  
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }

        // Manejar eventos para el botón de limpiar
        if (e.getSource() == vista.btnLimpiarU) {
            limpiar();
        }

        // Manejar eventos para el botón de atrás
        if (e.getSource() == vista.btnAtrasU) {
            vista.setVisible(false);  
            vPrincipal.setVisible(true); 
        }
    }

    /**
     * Método para llenar la tabla con los datos de los usuarios
     */
    public void llenarTabla() {
        vista.jTableUsuarios.setModel(consultas.listarUsuarios());
    }

    /**
     * Método para limpiar los campos de texto de la vista
     */
    private void limpiar() {
        vista.txtId2.setText(null);
        vista.txtNombreU.setText(null);
        vista.txtApellidosU.setText(null);
        vista.txtUsuarioU.setText(null);
        vista.txtPassworsU.setText(null);
        vista.txtTelefonoU.setText(null);
    }
}
