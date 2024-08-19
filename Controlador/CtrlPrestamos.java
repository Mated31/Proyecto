/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Controlador;

import Modelo.Prestamos;
import Modelo.ConsultarPrestamos;
import Vista.ventanaPrestamos;
import Vista.ventanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlPrestamos implements ActionListener {

    // Atributos privados para el modelo, consultas, vistas y controlador de libros
    private Prestamos modelo;
    private ConsultarPrestamos consultas;
    private ventanaPrestamos vista;
    private ventanaPrincipal vPrincipal;
    private CtrlLibros ctrlLibros;  // Instancia del controlador de libros

    // Constructor para inicializar los atributos y agregar ActionListeners a los botones
    public CtrlPrestamos(Prestamos modelo, ConsultarPrestamos consultas, ventanaPrestamos vista, ventanaPrincipal vPrincipal, CtrlLibros ctrlLibros) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vPrincipal = vPrincipal;
        this.ctrlLibros = ctrlLibros;  // Inicializa la instancia del controlador de libros
        
        // Agregar ActionListeners a los botones
        this.vista.btnPrestamos.addActionListener(this);
        this.vista.btnLimpiarP.addActionListener(this);
        this.vista.btnAtrasP.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Si se presiona el botón de préstamos
        if (e.getSource() == vista.btnPrestamos) {
            try {
                // Configurar el modelo con los datos de la vista
                modelo.setIdLibro(Integer.parseInt(vista.txtIdLibroP.getText()));
                modelo.setIdMiembro(Integer.parseInt(vista.txtIdMiembroP.getText()));
                modelo.setFechaPrestamo(new java.sql.Date(System.currentTimeMillis()));

                // Registrar el préstamo y actualizar la tabla
                if (consultas.registrarPrestamo(modelo)) {
                    JOptionPane.showMessageDialog(null, "Préstamo registrado con éxito");
                    limpiar();
                    llenarTabla(); // Actualiza la tabla con los datos más recientes
                    ctrlLibros.llenarTabla();  // Actualiza la tabla de libros
                } else {
                    // Mostrar mensaje si ocurre un error al registrar el préstamo
                    JOptionPane.showMessageDialog(null, "Error al registrar el préstamo");
                }
            } catch (NumberFormatException ex) {
                // Mostrar mensaje si hay un error de formato en los datos ingresados
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos");
            }
        }

        // Si se presiona el botón de limpiar
        if (e.getSource() == vista.btnLimpiarP) {
            limpiar();
        }

        // Si se presiona el botón de atrás
        if (e.getSource() == vista.btnAtrasP) {
            // Ocultar la ventana actual y mostrar la ventana principal
            vista.setVisible(false);
            vPrincipal.setVisible(true);
        }
    }

    // Método para llenar la tabla con los datos de los préstamos
    public void llenarTabla() {
        vista.jTableP.setModel(consultas.listarPrestamos());
    }

    // Método para limpiar los campos de texto de la vista
    private void limpiar() {
        vista.txtIdLibroP.setText(null);
        vista.txtIdMiembroP.setText(null);
    }
}
