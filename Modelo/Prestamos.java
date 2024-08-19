/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Modelo;

import java.sql.Date;

public class Prestamos {
    // Atributos
    // Identificador único del préstamo
    private int idPrestamo;
    // Identificador del libro prestado
    private int idLibro;
    // Identificador del miembro que realiza el préstamo
    private int idMiembro;
    // Fecha en la que se realiza el préstamo
    private Date fechaPrestamo;

    // Getters y setters
    // Obtiene el identificador del préstamo
    public int getIdPrestamo() {
        return idPrestamo;
    }

    // Establece el identificador del préstamo
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    // Obtiene el identificador del libro prestado
    public int getIdLibro() {
        return idLibro;
    }

    // Establece el identificador del libro prestado
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    // Obtiene el identificador del miembro que realiza el préstamo
    public int getIdMiembro() {
        return idMiembro;
    }

    // Establece el identificador del miembro que realiza el préstamo
    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    // Obtiene la fecha en la que se realiza el préstamo
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    // Establece la fecha en la que se realiza el préstamo
    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
}
