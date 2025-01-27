package org.example;

import org.example.modelo.ConexionSingleton;
import org.example.modelo.DBDDL;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        ConexionSingleton.getConnection();
        DBDDL.crearTablaTarea();
        System.out.println("Introduce el título de la tarea: ");
        String titulo = new Scanner(System.in).nextLine();
        System.out.println("Introduce la descripcion de la tarea");
        String descripcion = new Scanner(System.in).nextLine();
        DBDDL.guardarDatos(titulo, descripcion);

    }
}