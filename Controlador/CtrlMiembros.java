/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Controlador;

import Modelo.Miembros;
import Modelo.ConsultarMiembros;
import Vista.ventanaMiembros;
import Vista.ventanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlMiembros implements ActionListener {

    // Atributos privados para el modelo, consultas y vista
    private Miembros mi;
    private ConsultarMiembros consultas;
    private ventanaMiembros vista;
    private ventanaPrincipal vPrincipal;

    // Constructor para inicializar los atributos y agregar ActionListeners a los botones
    public CtrlMiembros(Miembros mi, ConsultarMiembros consultas, ventanaMiembros vista, ventanaPrincipal vPrincipal) {
        this.mi = mi;
        this.consultas = consultas;
        this.vista = vista;
        this.vPrincipal = vPrincipal;
        
        // Agregar ActionListeners solo si la vista no es null
        this.vista.btnGuardarM.addActionListener(this);
        this.vista.btnBuscarM.addActionListener(this);
        this.vista.btnEliminarM.addActionListener(this);
        this.vista.btnLimpiarM.addActionListener(this);
        this.vista.btnModificarM.addActionListener(this);
        this.vista.btnAtrasM.addActionListener(this);
        
        llenarTabla();  // Llenar la tabla al iniciar el controlador
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Si se presiona el botón de guardar
        if (e.getSource() == vista.btnGuardarM) {
            // Validar cédula
            String cedula = vista.txtCedulaMiembros.getText();
            if (!validarCedula(cedula)) {
                // Mostrar mensaje si la cédula no es válida
                JOptionPane.showMessageDialog(null, "La cédula no es válida. Debe tener exactamente 10 dígitos.");
                return;
            }
            
            // Configurar el modelo con los datos de la vista
            mi.setId_miembro(Integer.parseInt(vista.txtIdMiembros.getText()));
            mi.setNombre(vista.txtNombreMiembros.getText());
            mi.setApellido(vista.txtApellidoMienbros.getText());
            mi.setCedula(cedula);
            mi.setDireccion(vista.txtDireccionMiembros.getText());
            mi.setTelefono(vista.txtTelefonoMiembros.getText());
            mi.setEmail(vista.txtEmailMiembros.getText());
            mi.setFecha_nacimiento(vista.jDateFechaNacimiento.getDate());

            // Verificar si el ID de miembro ya existe
            if (consultas.existeId(mi.getId_miembro())) {
                // Mostrar mensaje si el ID ya existe
                JOptionPane.showMessageDialog(null, "El ID de miembro ya existe. Utilice un ID único.");
                return;
            }

            // Registrar el miembro y actualizar la tabla
            if (consultas.registrar(mi)) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                limpiar();
                llenarTabla();  // Actualiza la tabla después de guardar
            } else {
                // Mostrar mensaje si ocurre un error al guardar
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        }

        // Si se presiona el botón de buscar
        if (e.getSource() == vista.btnBuscarM) {
            // Configurar el ID del miembro en el modelo
            mi.setId_miembro(Integer.parseInt(vista.txtIdMiembros.getText()));

            // Buscar el miembro en la base de datos
            if (consultas.buscar(mi)) {
                // Mostrar los datos del miembro en la vista
                vista.txtNombreMiembros.setText(mi.getNombre());
                vista.txtApellidoMienbros.setText(mi.getApellido());
                vista.txtCedulaMiembros.setText(mi.getCedula());
                vista.txtDireccionMiembros.setText(mi.getDireccion());
                vista.txtTelefonoMiembros.setText(mi.getTelefono());
                vista.txtEmailMiembros.setText(mi.getEmail());
                vista.jDateFechaNacimiento.setDate(mi.getFecha_nacimiento()); // Establecer la fecha de nacimiento en el JDateChooser
            } else {
                // Mostrar mensaje si no se encuentra el registro
                JOptionPane.showMessageDialog(null, "No se encontró el registro");
                limpiar();
            }
        }

        // Si se presiona el botón de eliminar
        if (e.getSource() == vista.btnEliminarM) {
            // Configurar el ID del miembro en el modelo
            mi.setId_miembro(Integer.parseInt(vista.txtIdMiembros.getText()));

            // Verificar si el ID de miembro existe
            if (!consultas.existeId(mi.getId_miembro())) {
                // Mostrar mensaje si el ID no existe
                JOptionPane.showMessageDialog(null, "El ID de miembro no existe.");
                return;
            }

            // Eliminar el miembro y actualizar la tabla
            if (consultas.eliminar(mi)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
                llenarTabla();  // Actualiza la tabla después de eliminar
            } else {
                // Mostrar mensaje si ocurre un error al eliminar
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        }

        // Si se presiona el botón de modificar
        if (e.getSource() == vista.btnModificarM) {
            // Validar cédula
            String cedula = vista.txtCedulaMiembros.getText();
            if (!validarCedula(cedula)) {
                // Mostrar mensaje si la cédula no es válida
                JOptionPane.showMessageDialog(null, "La cédula no es válida. Debe tener exactamente 10 dígitos.");
                return;
            }

            // Configurar el modelo con los datos de la vista
            mi.setId_miembro(Integer.parseInt(vista.txtIdMiembros.getText()));
            mi.setNombre(vista.txtNombreMiembros.getText());
            mi.setApellido(vista.txtApellidoMienbros.getText());
            mi.setCedula(cedula);
            mi.setDireccion(vista.txtDireccionMiembros.getText());
            mi.setTelefono(vista.txtTelefonoMiembros.getText());
            mi.setEmail(vista.txtEmailMiembros.getText());
            mi.setFecha_nacimiento(vista.jDateFechaNacimiento.getDate());

            // Verificar si el ID de miembro existe
            if (!consultas.existeId(mi.getId_miembro())) {
                // Mostrar mensaje si el ID no existe
                JOptionPane.showMessageDialog(null, "El ID de miembro no existe. No se puede modificar.");
                return;
            }

            // Modificar el miembro y actualizar la tabla
            if (consultas.modificar(mi)) {
                JOptionPane.showMessageDialog(null, "Registro modificado");
                limpiar();
                llenarTabla();  // Actualiza la tabla después de modificar
            } else {
                // Mostrar mensaje si ocurre un error al modificar
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }

        // Si se presiona el botón de limpiar
        if (e.getSource() == vista.btnLimpiarM) {
            limpiar();
        }

        // Si se presiona el botón de atrás
        if (e.getSource() == vista.btnAtrasM) {
            // Ocultar la ventana actual y mostrar la ventana principal
            vista.setVisible(false);
            vPrincipal.setVisible(true);
        }
    }

    // Método para llenar la tabla con los datos de los miembros
    public void llenarTabla() {
        vista.jTableMiembros.setModel(consultas.listarMiembros());
    }

    // Método para limpiar los campos de texto de la vista
    private void limpiar() {
        vista.txtIdMiembros.setText(null);
        vista.txtNombreMiembros.setText(null);
        vista.txtApellidoMienbros.setText(null);
        vista.txtCedulaMiembros.setText(null);
        vista.txtDireccionMiembros.setText(null);
        vista.txtTelefonoMiembros.setText(null);
        vista.txtEmailMiembros.setText(null);
        vista.jDateFechaNacimiento.setDate(null); // Limpiar el JDateChooser
    }

    // Método para validar el formato de la cédula
    private boolean validarCedula(String cedula) {
        // Ejemplo: validar que la cédula tenga exactamente 10 dígitos
        return cedula != null && cedula.matches("\\d{10}");
    }
}
