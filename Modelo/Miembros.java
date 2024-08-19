/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Modelo;

import java.util.Date;

public class Miembros {

    // Identificador único del miembro en la base de datos
    private int miembro_id; 
    // Identificador del miembro (puede ser una referencia externa)
    private int id_miembro; 
    // Nombre del miembro
    private String nombre; 
    // Apellido del miembro
    private String apellido; 
    // Cédula del miembro
    private String cedula; 
    // Dirección del miembro
    private String direccion; 
    // Teléfono del miembro
    private String telefono; 
    // Email del miembro
    private String email; 
    // Fecha de nacimiento del miembro
    private Date fecha_nacimiento; 

    // Obtiene el identificador único del miembro.
    public int getMiembro_id() {
        // Retorna el identificador único del miembro
        return miembro_id;
    }

    // Establece el identificador único del miembro.
    public void setMiembro_id(int miembro_id) {
        // Establece el identificador único del miembro
        this.miembro_id = miembro_id;
    }

    // Obtiene el identificador del miembro.
    public int getId_miembro() {
        // Retorna el identificador del miembro
        return id_miembro;
    }

    // Establece el identificador del miembro.
    public void setId_miembro(int id_miembro) {
        // Establece el identificador del miembro
        this.id_miembro = id_miembro;
    }

    // Obtiene el nombre del miembro.
    public String getNombre() {
        // Retorna el nombre del miembro
        return nombre;
    }

    // Establece el nombre del miembro.
    public void setNombre(String nombre) {
        // Establece el nombre del miembro
        this.nombre = nombre;
    }

    // Obtiene el apellido del miembro.
    public String getApellido() {
        // Retorna el apellido del miembro
        return apellido;
    }

    // Establece el apellido del miembro.
    public void setApellido(String apellido) {
        // Establece el apellido del miembro
        this.apellido = apellido;
    }

    // Obtiene la cédula del miembro.
    public String getCedula() {
        // Retorna la cédula del miembro
        return cedula;
    }

    // Establece la cédula del miembro.
    public void setCedula(String cedula) {
        // Establece la cédula del miembro
        this.cedula = cedula;
    }

    // Obtiene la dirección del miembro.
    public String getDireccion() {
        // Retorna la dirección del miembro
        return direccion;
    }

    // Establece la dirección del miembro.
    public void setDireccion(String direccion) {
        // Establece la dirección del miembro
        this.direccion = direccion;
    }

    // Obtiene el teléfono del miembro.
    public String getTelefono() {
        // Retorna el teléfono del miembro
        return telefono;
    }

    // Establece el teléfono del miembro.
    public void setTelefono(String telefono) {
        // Establece el teléfono del miembro
        this.telefono = telefono;
    }

    // Obtiene el email del miembro.
    public String getEmail() {
        // Retorna el email del miembro
        return email;
    }

    // Establece el email del miembro.
    public void setEmail(String email) {
        // Establece el email del miembro
        this.email = email;
    }

    // Obtiene la fecha de nacimiento del miembro.
    public Date getFecha_nacimiento() {
        // Retorna la fecha de nacimiento del miembro
        return fecha_nacimiento;
    }

    // Establece la fecha de nacimiento del miembro.
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        // Establece la fecha de nacimiento del miembro
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
