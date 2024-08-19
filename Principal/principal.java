/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Principal;

import Controlador.CtrlDevoluciones;
import Controlador.CtrlLibros;
import Controlador.CtrlLogin;
import Controlador.CtrlMiembros;
import Controlador.CtrlPrestamos;
import Controlador.CtrlPrincipal;
import Controlador.CtrlUsuarios;
import Modelo.ConsultarDevoluciones;
import Modelo.ConsultarLibros;
import Modelo.ConsultarMiembros;
import Modelo.ConsultarPrestamos;
import Modelo.ConsultarLogin;
import Modelo.ConsultarUsuarios;
import Modelo.Devoluciones;
import Modelo.Libros;
import Modelo.Miembros;
import Modelo.Prestamos;
import Modelo.Usuarios;
import Vista.ventanaDevoluciones;
import Vista.ventanaLibros;
import Vista.ventanaMiembros;
import Vista.ventanaPrestamos;
import Vista.ventanaPrincipal;
import Vista.ventanaLogin;
import Vista.ventanaUsuarios;

public class principal {

    public static void main(String[] args) {
        Miembros mi = new Miembros();
        Usuarios user = new Usuarios();
        Libros lib = new Libros();
        Prestamos pres = new Prestamos();
        Devoluciones dev = new Devoluciones();

        ConsultarLibros clib = new ConsultarLibros();
        ConsultarLogin cUser = new ConsultarLogin();
        ConsultarMiembros cmi = new ConsultarMiembros();
        ConsultarPrestamos cpres = new ConsultarPrestamos();
        ConsultarDevoluciones cdev = new ConsultarDevoluciones();
        ConsultarUsuarios cUsu = new ConsultarUsuarios();

        ventanaLibros vLib = new ventanaLibros();
        ventanaPrincipal vPrin = new ventanaPrincipal();
        ventanaLogin vUser = new ventanaLogin();
        ventanaMiembros vMiembros = new ventanaMiembros();
        ventanaPrestamos vPrestamos = new ventanaPrestamos();
        ventanaDevoluciones vDevoluciones = new ventanaDevoluciones();
        ventanaUsuarios vUsuarios = new ventanaUsuarios();

        CtrlLibros ctrlib = new CtrlLibros(lib, clib, vLib, vPrin);
        ctrlib.llenarTabla();

        CtrlDevoluciones ctrlDev = new CtrlDevoluciones(dev, cdev, vDevoluciones, vPrin, ctrlib);
        ctrlDev.llenarTabla();

        CtrlPrestamos ctrlPres = new CtrlPrestamos(pres, cpres, vPrestamos, vPrin, ctrlib);
        ctrlPres.llenarTabla();
         
        CtrlMiembros ctrlM = new CtrlMiembros(mi, cmi, vMiembros, vPrin);
        ctrlM.llenarTabla();

        CtrlUsuarios ctrlU = new CtrlUsuarios(user, cUsu, vUsuarios, vPrin);
        ctrlU.llenarTabla();

        CtrlPrincipal ctrlP = new CtrlPrincipal(vPrin, vLib, vMiembros, vPrestamos, vDevoluciones, vUser, vUsuarios);

        CtrlLogin ctrlLog = new CtrlLogin(user, cUser, vUser, vPrin, vLib, vMiembros, vUsuarios);
        ctrlLog.Iniciar();

        vUser.setVisible(true);
    }
}
