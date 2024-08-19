/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Controlador;

import Modelo.Libros;
import Modelo.ConsultarLibros;
import Vista.ventanaLibros;
import Vista.ventanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlLibros implements ActionListener {
    
    // Atributos privados para el modelo, consultas, y vistas
    private Libros modelo;
    private ConsultarLibros consultas;
    private ventanaLibros vista;
    private ventanaPrincipal vPrincipal;

    // Constructor para inicializar los atributos y agregar ActionListeners
    public CtrlLibros(Libros modelo, ConsultarLibros consultas, ventanaLibros vista, ventanaPrincipal vPrincipal) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vPrincipal = vPrincipal;
        
        // Agregar ActionListeners a los botones de la vista
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.tbnAtrasLibros.addActionListener(this);
        
        // Llenar la tabla con los datos al iniciar el controlador
        llenarTabla();
    }

    // Implementación del método actionPerformed para manejar los eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        // Si se presiona el botón de Guardar
        if (e.getSource() == vista.btnGuardar) {
            try {
                // Obtener y establecer los valores del libro en el modelo
                modelo.setId_libro(Integer.parseInt(vista.txtId_libro.getText()));
                modelo.setAutor(vista.txtAutor.getText());
                modelo.setTitulo(vista.txtTitulo.getText());
                modelo.setGenero(vista.txtGenero.getText());
                modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));

                // Verificar si el ID del libro ya existe
                if (consultas.existeId(modelo.getId_libro())) {
                    JOptionPane.showMessageDialog(null, "El ID de libro ya existe.");
                    return;
                }

                // Registrar el libro en la base de datos
                if (consultas.registrar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    limpiar();  // Limpiar los campos después de guardar
                    llenarTabla();  // Actualiza la tabla después de guardar
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
            }
        }

        // Si se presiona el botón de Buscar
        if (e.getSource() == vista.btnBuscar) {
            try {
                // Obtener y establecer el ID del libro en el modelo
                modelo.setId_libro(Integer.parseInt(vista.txtId_libro.getText()));

                // Buscar el libro en la base de datos y llenar los campos de la vista
                if (consultas.buscar(modelo)) {
                    vista.txtAutor.setText(modelo.getAutor());
                    vista.txtTitulo.setText(modelo.getTitulo());
                    vista.txtGenero.setText(modelo.getGenero());
                    vista.txtCantidad.setText(String.valueOf(modelo.getCantidad()));
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el registro");
                    limpiar();  // Limpiar los campos si no se encuentra el libro
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
            }
        }

        // Si se presiona el botón de Eliminar
        if (e.getSource() == vista.btnEliminar) {
            try {
                // Obtener y establecer el ID del libro en el modelo
                modelo.setId_libro(Integer.parseInt(vista.txtId_libro.getText()));

                // Verificar si el ID del libro existe
                if (!consultas.existeId(modelo.getId_libro())) {
                    JOptionPane.showMessageDialog(null, "El ID de libro no existe.");
                    return;
                }

                // Eliminar el libro de la base de datos
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado");
                    limpiar();  // Limpiar los campos después de eliminar
                    llenarTabla();  // Actualiza la tabla después de eliminar
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
            }
        }

        // Si se presiona el botón de Modificar
        if (e.getSource() == vista.btnModificar) {
            try {
                // Obtener y establecer los valores del libro en el modelo
                modelo.setId_libro(Integer.parseInt(vista.txtId_libro.getText()));
                modelo.setAutor(vista.txtAutor.getText());
                modelo.setTitulo(vista.txtTitulo.getText());
                modelo.setGenero(vista.txtGenero.getText());
                modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));

                // Verificar si el ID del libro existe
                if (!consultas.existeId(modelo.getId_libro())) {
                    JOptionPane.showMessageDialog(null, "El ID de libro no existe.");
                    return;
                }

                // Modificar el libro en la base de datos
                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado");
                    limpiar();  // Limpiar los campos después de modificar
                    llenarTabla();  // Actualiza la tabla después de modificar
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
            }
        }

        // Si se presiona el botón de Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            limpiar();  // Limpiar los campos de la vista
        }

        // Si se presiona el botón de Atrás
        if (e.getSource() == vista.tbnAtrasLibros) {
            vista.setVisible(false);  // Oculta la ventana actual
            vPrincipal.setVisible(true);  // Muestra la ventana principal
        }
    }

    // Método para llenar la tabla con los datos de los libros
    public void llenarTabla() {
        vista.tablaLibros.setModel(consultas.listarLibros());
    }

    // Método para limpiar los campos de texto de la vista
    private void limpiar() {
        vista.txtId_libro.setText(null);
        vista.txtAutor.setText(null);
        vista.txtTitulo.setText(null);
        vista.txtGenero.setText(null);
        vista.txtCantidad.setText(null);
    }
}
