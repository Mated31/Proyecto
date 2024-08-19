/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
 */
 
package Controlador;

import Modelo.Devoluciones;
import Modelo.ConsultarDevoluciones;
import Vista.ventanaDevoluciones;
import Vista.ventanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlDevoluciones implements ActionListener {

    // Atributos privados para el modelo, consultas, y vistas
    private Devoluciones modelo;
    private ConsultarDevoluciones consultas;
    private ventanaDevoluciones vista;
    private ventanaPrincipal vPrincipal;
    private CtrlLibros ctrlLibros;  // Instancia del controlador de libros

    // Constructor para inicializar los atributos y agregar ActionListeners
    public CtrlDevoluciones(Devoluciones modelo, ConsultarDevoluciones consultas, ventanaDevoluciones vista, ventanaPrincipal vPrincipal, CtrlLibros ctrlLibros) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vPrincipal = vPrincipal;
        this.ctrlLibros = ctrlLibros;

        // Agregar ActionListeners solo si la vista no es null
        if (this.vista != null) {
            this.vista.btnDevoluciones.addActionListener(this);
            this.vista.btnLimpiarD.addActionListener(this);
            this.vista.btnAtrasD.addActionListener(this);
            this.vista.btnActualizarTablaD.addActionListener(this);  // Agregar ActionListener para el nuevo botón
        } else {
            System.err.println("Error: La vista ventanaDevoluciones es null.");
        }
    }

    // Implementación del método actionPerformed para manejar los eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        // Si se presiona el botón de Devoluciones
        if (e.getSource() == vista.btnDevoluciones) {
            try {
                // Obtener y establecer el ID del libro y del miembro
                modelo.setIdLibro(Integer.parseInt(vista.txtid_libroD.getText()));
                modelo.setIdMiembro(Integer.parseInt(vista.txtIdMiembroD.getText()));
                
                // Establecer la fecha de devolución a la fecha actual
                modelo.setFechaDevolucion(new java.sql.Date(System.currentTimeMillis()));

                // Registrar la devolución y actualizar las tablas
                if (consultas.registrarDevolucion(modelo)) {
                    JOptionPane.showMessageDialog(null, "Devolución registrada con éxito");
                    limpiar();  // Limpiar los campos después de registrar
                    llenarTabla();  // Actualiza la tabla de devoluciones
                    ctrlLibros.llenarTabla();  // Actualiza la tabla de libros en el controlador de libros
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar la devolución");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos");
            }
        }

        // Si se presiona el botón de Limpiar
        if (e.getSource() == vista.btnLimpiarD) {
            limpiar();  // Limpiar los campos de la vista
        }

        // Si se presiona el botón de Atrás
        if (e.getSource() == vista.btnAtrasD) {
            vista.setVisible(false);  // Ocultar la ventana actual
            vPrincipal.setVisible(true);  // Mostrar la ventana principal
        }

        // Si se presiona el botón de Actualizar Tabla
        if (e.getSource() == vista.btnActualizarTablaD) {
            llenarTabla();  // Actualizar la tabla de devoluciones
        }
    }

    // Método para llenar la tabla con los datos de las devoluciones
    public void llenarTabla() {
        vista.jTableD.setModel(consultas.listarDevoluciones());
    }

    // Método para limpiar los campos de texto de la vista
    private void limpiar() {
        vista.txtid_libroD.setText(null);
        vista.txtIdMiembroD.setText(null);
    }
}
