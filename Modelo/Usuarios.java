/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/

package Modelo;

public class Usuarios {

    // Atributos
    // Identificador único del usuario
    private int idusuarios;
    // Nombre del usuario
    private String nombre;
    // Apellido del usuario
    private String apellido;
    // Nombre de usuario
    private String usuario;
    // Contraseña del usuario
    private String password;
    // Teléfono del usuario
    private String telefono;

    // Constructor
    // (No se ha definido un constructor en el código proporcionado)

    // Getters y setters
    // Obtiene el identificador del usuario
    public int getIdusuarios() {
        return idusuarios;
    }

    // Establece el identificador del usuario
    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    // Obtiene el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre del usuario
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Obtiene el apellido del usuario
    public String getApellido() {
        return apellido;
    }

    // Establece el apellido del usuario
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Obtiene el nombre de usuario
    public String getUsuario() {
        return usuario;
    }

    // Establece el nombre de usuario
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Obtiene la contraseña del usuario
    public String getPassword() {
        return password;
    }

    // Establece la contraseña del usuario
    public void setPassword(String password) {
        this.password = password;
    }

    // Obtiene el teléfono del usuario
    public String getTelefono() {
        return telefono;
    }

    // Establece el teléfono del usuario
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
