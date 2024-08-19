/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Controlador;

import Modelo.ConsultarLogin;
import Modelo.Usuarios;
import Vista.ventanaLibros;
import Vista.ventanaMiembros;
import Vista.ventanaPrincipal;
import Vista.ventanaLogin;
import Vista.ventanaUsuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlLogin implements ActionListener {

    // Atributos privados para el modelo, consultas y vistas
    private Usuarios us;
    private ConsultarLogin consultas;
    private ventanaLogin login;
    private ventanaPrincipal vPrincipal;
    private ventanaLibros vLibros;
    private ventanaMiembros vMiembros;
    private ventanaUsuarios vUsuarios;

    // Constructor para inicializar los atributos y agregar ActionListener al botón de ingresar
    public CtrlLogin(Usuarios us, ConsultarLogin consultas, ventanaLogin login, 
            ventanaPrincipal vPrincipal, ventanaLibros vLibros, ventanaMiembros vMiembros, ventanaUsuarios vUsuarios) {
        this.us = us;
        this.consultas = consultas;
        this.login = login;
        this.vPrincipal = vPrincipal;
        this.vLibros = vLibros;
        this.vMiembros = vMiembros;
        this.vUsuarios = vUsuarios;
        
        // Agregar ActionListener al botón de ingresar
        this.login.tbnIngresar.addActionListener(this);
    }

    // Método para inicializar las vistas
    public void Iniciar() {
        // Establecer los títulos de las ventanas
        vLibros.setTitle("Libros");
        vPrincipal.setTitle("Principal");
        login.setTitle("Login");
        vMiembros.setTitle("Gestionar Miembros");
        
        // Centrar las ventanas en la pantalla
        login.setLocationRelativeTo(null);
        vPrincipal.setLocationRelativeTo(null);
        vMiembros.setLocationRelativeTo(null);
        vLibros.setLocationRelativeTo(null);
        
        // Opcional: Hacer que el campo txtId en vLibros no sea visible
        // vLibros.txtId.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Si se presiona el botón de ingresar
        if (e.getSource() == login.tbnIngresar) {
            // Establecer usuario y contraseña desde los campos de texto
            us.setUsuario(login.txtUsuarioL.getText());
            us.setPassword(new String(login.txtPasswordL.getPassword()));

            // Autenticar al usuario
            if (consultas.autenticar(us)) {
                // Mostrar la ventana principal y cerrar la ventana de login
                vPrincipal.setVisible(true);
                login.dispose();
            } else {
                // Mostrar mensaje de error si la autenticación falla
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        }
    }
}
