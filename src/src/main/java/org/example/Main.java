package org.example;

import org.example.modelo.ConexionSingleton;
import org.example.modelo.DBDDL;
import org.example.modelo.Tarea;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        List<Tarea> tareas;

        ConexionSingleton.getConnection();
        DBDDL.crearTablaTarea();
        tareas = DBDDL.consultarDatos();

        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                    Elija una de las siguientes opciones:
                    1 -> Mostrar tareas.
                    2 -> Añadir tarea.
                    3 -> Editar tarea.
                    4 -> Eliminar tarea.
                    5 -> Salir.
                    """);
            String eleccion = new Scanner(System.in).nextLine();
            int num = Integer.parseInt(eleccion);

            switch (num) {
                case 1 -> {
                    DBDDL.mostrarTareas(tareas);
                }
                case 2 -> {
                    System.out.println("Introduce el título de la tarea");
                    String titulo = new Scanner(System.in).nextLine();
                    System.out.println("Introduce las descripción de la tarea");
                    String descripcion = new Scanner(System.in).nextLine();

                    Tarea tarea = new Tarea(tareas.size()+1,titulo, descripcion, false);
                    DBDDL.guardarDatos(tarea.getTitulo(), tarea.getDescripcion(), false);
                    tareas.add(tarea);
                }
                case 3 -> {
                    System.out.println("¿Cuál es el ID de la tarea que quieres editar?");
                    int id = new Scanner(System.in).nextInt();
                    System.out.println("""
                            ¿Qué desea editar de la tarea?
                            1 -> Título y descripción.
                            2 -> Realizada.
                            """);
                    int opcionEditar = new Scanner(System.in).nextInt();
                    switch (opcionEditar) {
                        case 1 -> {
                            System.out.println("Introduce el nuevo título");
                            String tituloNuevo = new Scanner(System.in).nextLine();
                            System.out.println("Introduce la nueva descripción");
                            String descripcionNueva = new Scanner(System.in).nextLine();

                            tareas.get(id-1).setTitulo(tituloNuevo);
                            tareas.get(id-1).setDescripcion(descripcionNueva);
                            DBDDL.editarDatos(id, tituloNuevo, descripcionNueva, false, opcionEditar);
                        }
                        case 2 -> {
                            System.out.println("Introduce 'true' o 'false' si está hecha o no");
                            boolean hecha = new Scanner(System.in).nextBoolean();
                            DBDDL.editarDatos(id, "", "", hecha, opcionEditar);
                        }
                    }
                }
                case 4 -> {
                    System.out.println();
                }

            }

        }
        System.out.println("Introduce el título de la tarea: ");
        String titulo = new Scanner(System.in).nextLine();
        System.out.println("Introduce la descripcion de la tarea");
        String descripcion = new Scanner(System.in).nextLine();
        DBDDL.guardarDatos(titulo, descripcion, false);

    }
}