package org.example.modelo;

public class Tarea {
    private static int contadorId = 0;
    private int id;
    private String titulo;
    private String descripcion;
    private boolean hecho;

    // Constructor
    public Tarea(String titulo, String descripcion) {
        this.id = ++contadorId; // AUTOINCREMENT
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hecho = false;
    }

    // Método para verificar si la tarea está hecha
    public boolean estaHecho() {
        return this.hecho;
    }

    // Método para completar la tarea
    public void completarTarea() {
        this.hecho = true;
    }

    // Getter para el título
    public String getTitulo() {
        return this.titulo;
    }

    // Setter para el título
    public void setTitulo(String texto) {
        this.titulo = texto;
    }

    // Getter para la descripción
    public String getDescripcion() {
        return this.descripcion;
    }

    // Setter para la descripción
    public void setDescripcion(String texto) {
        this.descripcion = texto;
    }

    // Getter para el ID
    public int getId() {
        return this.id;
    }

    // Método toString
    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", hecho=" + hecho +
                '}';
    }
}
