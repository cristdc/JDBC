package org.example.modelo;

public class Tarea {
    private static int contadorId = 0;
    private int id;
    private String titulo;
    private String descripcion;
    private boolean hecho;

    public Tarea(String titulo, String descripcion) {
        this.id = ++contadorId; // AUTOINCREMENT
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hecho = false;
    }

    public boolean estaHecho() {
        return this.hecho;
    }

    public void completarTarea() {
        this.hecho = true;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String texto) {
        this.titulo = texto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String texto) {
        this.descripcion = texto;
    }

    public int getId() {
        return this.id;
    }

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
