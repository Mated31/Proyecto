/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Modelo;

import java.util.Date;

public class Devoluciones {

    // Identificador único para la devolución
    private int idDevolucion;
    // Identificador del miembro que realiza la devolución
    private int idMiembro;
    // Identificador del libro que se devuelve
    private int idLibro;
    // Fecha en la que se realiza la devolución
    private Date fechaDevolucion;

    // Constructor vacío
    public Devoluciones() {
    }

    // Obtener el identificador de la devolución
    public int getIdDevolucion() {
        return idDevolucion;
    }

    // Establecer el identificador de la devolución
    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    // Obtener el identificador del miembro
    public int getIdMiembro() {
        return idMiembro;
    }

    // Establecer el identificador del miembro
    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    // Obtener el identificador del libro
    public int getIdLibro() {
        return idLibro;
    }

    // Establecer el identificador del libro
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    // Obtener la fecha de la devolución
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    // Establecer la fecha de la devolución
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
