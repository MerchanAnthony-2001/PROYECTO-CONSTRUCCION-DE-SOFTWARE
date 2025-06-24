/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CodeAngel369
 */
package modelo;

public class Receta {
    private int id;
    private String titulo;
    private String ingredientes;
    private String preparacion;
    private String autor; // Puede ser el nombre del usuario o su correo

    // Constructor vacío (necesario para JSP y frameworks)
    public Receta() {
    }

    // Constructor con todos los campos
    public Receta(int id, String titulo, String ingredientes, String preparacion, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.autor = autor;
    }

    // Constructor sin ID (para insertar)
    public Receta(String titulo, String ingredientes, String preparacion, String autor) {
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.autor = autor;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
