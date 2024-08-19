/*     UNIVERSIDAD DE LAS FUERZAS ARMADAS "ESPE"
                    Carrera: Ingeniería en Telecomunicaciones
Nombres: Guerrero Mateo-Guachamín Marco-Jerez Wendy     Período:202450
Asignatura: Programación Orientada a Objetos            NRC:17507
Fecha: 2024/08/20
Tema: Programa para gestionar una biblioteca
*/
package Modelo;

public class Libros {
    // Identificador único del libro en la base de datos
    private int id; 
    // Identificador del libro (puede ser una referencia externa)
    private int id_libro; 
    // Autor del libro
    private String autor; 
    // Título del libro
    private String titulo; 
    // Género del libro
    private String genero; 
    // Cantidad disponible del libro
    private int cantidad; 

    // Obtiene el identificador único del libro.
    public int getId() {
        // Retorna el identificador único del libro
        return id;
    }

    // Establece el identificador único del libro.
    public void setId(int id) {
        // Establece el identificador único del libro
        this.id = id;
    }

    // Obtiene el identificador del libro.
    public int getId_libro() {
        // Retorna el identificador del libro
        return id_libro;
    }

    // Establece el identificador del libro.
    public void setId_libro(int id_libro) {
        // Establece el identificador del libro
        this.id_libro = id_libro;
    }

    // Obtiene el autor del libro.
    public String getAutor() {
        // Retorna el autor del libro
        return autor;
    }

    // Establece el autor del libro.
    public void setAutor(String autor) {
        // Establece el autor del libro
        this.autor = autor;
    }

    // Obtiene el título del libro.
    public String getTitulo() {
        // Retorna el título del libro
        return titulo;
    }

    // Establece el título del libro.
    public void setTitulo(String titulo) {
        // Establece el título del libro
        this.titulo = titulo;
    }

    // Obtiene el género del libro.
    public String getGenero() {
        // Retorna el género del libro
        return genero;
    }

    // Establece el género del libro.
    public void setGenero(String genero) {
        // Establece el género del libro
        this.genero = genero;
    }

    // Obtiene la cantidad disponible del libro.
    public int getCantidad() {
        // Retorna la cantidad disponible del libro
        return cantidad;
    }

    // Establece la cantidad disponible del libro.
    public void setCantidad(int cantidad) {
        // Establece la cantidad disponible del libro
        this.cantidad = cantidad;
    }
}
