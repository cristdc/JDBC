package org.example.modelo;

public class Tarea {
    private static int contadorId = 0;
    private int id;
    private String titulo;
    private String descripcion;
    private boolean hecho;

    public Tarea(String titulo, String descripcion, boolean hecho) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hecho = hecho;
    }

    public Tarea(int id, String titulo, String descripcion, boolean hecho) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hecho = hecho;
    }


    public void estaHecho(boolean realizar){
        this.hecho = realizar;
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
        StringBuilder sb = new StringBuilder();
        sb.append("+-----------------------------+\n");
        sb.append("| Tarea                       \n");
        sb.append("+-----------------------------+\n");
        sb.append("| ID: " + id + "\n");
        sb.append("| Título: " + titulo + "\n");
        sb.append("| Descripción: " + descripcion + "\n");
        sb.append("| Hecho: " + hecho + "\n");
        sb.append("+-----------------------------+\n");
        return sb.toString();
    }

}
