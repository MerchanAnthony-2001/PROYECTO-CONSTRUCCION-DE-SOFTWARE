/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Timestamp;

public class Comentario {
    private String autor;
    private String texto;
    private Timestamp fecha;

    public Comentario(String autor, String texto, Timestamp fecha) {
        this.autor = autor;
        this.texto = texto;
        this.fecha = fecha;
    }

    public String getAutor() { return autor; }
    public String getTexto() { return texto; }
    public Timestamp getFecha() { return fecha; }
}