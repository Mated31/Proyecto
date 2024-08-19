/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Controlador;

import Vista.ventanaDevoluciones;
import Vista.ventanaLibros;
import Vista.ventanaMiembros;
import Vista.ventanaPrestamos;
import Vista.ventanaPrincipal;
import Vista.ventanaLogin;
import Vista.ventanaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador principal para manejar la navegación entre diferentes vistas
 */
public class CtrlPrincipal implements ActionListener {

    // Atributos privados para las vistas
    private ventanaPrincipal vPrinc;
    private ventanaLibros vLibros;
    private ventanaMiembros vMiembros;
    private ventanaPrestamos vPrestamos;
    private ventanaDevoluciones vDevoluciones;
    private ventanaLogin vLogin;
    private ventanaUsuarios vUsuarios;

    // Constructor para inicializar las vistas y agregar ActionListeners a los botones
    public CtrlPrincipal(ventanaPrincipal vPrinc, ventanaLibros vLibros, ventanaMiembros vMiembros,
                         ventanaPrestamos vPrestamos, ventanaDevoluciones vDevoluciones, ventanaLogin vLogin, ventanaUsuarios vUsuarios) {
        this.vPrinc = vPrinc;
        this.vLibros = vLibros;
        this.vMiembros = vMiembros;
        this.vPrestamos = vPrestamos;
        this.vDevoluciones = vDevoluciones;
        this.vLogin = vLogin;
        this.vUsuarios = vUsuarios;
        
        // Agregar ActionListeners a los botones de la ventana principal
        this.vPrinc.btnLibros.addActionListener(this);
        this.vPrinc.btnMiembrosP.addActionListener(this);
        this.vPrinc.btnPrestamosP.addActionListener(this);
        this.vPrinc.btnDevolucionesP.addActionListener(this);
        this.vPrinc.btnNuevoUsuarioP.addActionListener(this);
        this.vPrinc.btnSalirUsuario.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Manejar eventos de los botones de la ventana principal
        if (e.getSource() == vPrinc.btnLibros) {
            vLibros.setVisible(true);
            vPrinc.dispose();  // Cerrar la ventana principal
        }
        if (e.getSource() == vPrinc.btnMiembrosP) {
            vMiembros.setVisible(true);
            vPrinc.dispose();  // Cerrar la ventana principal
        }
        if (e.getSource() == vPrinc.btnPrestamosP) {
            vPrestamos.setVisible(true);
            vPrinc.dispose();  // Cerrar la ventana principal
        }
        if (e.getSource() == vPrinc.btnDevolucionesP) {
            vDevoluciones.setVisible(true);
            vPrinc.dispose();  // Cerrar la ventana principal
        }
        if (e.getSource() == vPrinc.btnNuevoUsuarioP) {
            vUsuarios.setVisible(true);
            vPrinc.dispose();  // Cerrar la ventana principal
        }
        if (e.getSource() == vPrinc.btnSalirUsuario) {
            vLogin.setVisible(true);  // Mostrar la ventana de login
            vPrinc.dispose();  // Cerrar la ventana principal
        }
    }
}
