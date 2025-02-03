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
                    int autoincrementar = 0;
                    for (int i = 0; i < tareas.size(); i++) {
                        if (tareas.get(i).getId() > autoincrementar) {
                            autoincrementar = tareas.get(i).getId();
                        }
                    }

                    System.out.println("Introduce el título de la tarea");
                    String titulo = new Scanner(System.in).nextLine();
                    System.out.println("Introduce las descripción de la tarea");
                    String descripcion = new Scanner(System.in).nextLine();

                    Tarea tarea = new Tarea(autoincrementar + 1, titulo, descripcion, false);
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

                            Tarea tareaAEditar = null;
                            for (Tarea tarea : tareas) {
                                if (tarea.getId() == id) {
                                    tareaAEditar = tarea;
                                    break;
                                }
                            }

                            if (tareaAEditar != null) {
                                tareaAEditar.setTitulo(tituloNuevo);
                                tareaAEditar.setDescripcion(descripcionNueva);
                                DBDDL.editarDatos(id, tituloNuevo, descripcionNueva, false, opcionEditar);
                            } else {
                                System.out.println("No existe una tarea con ese ID.");
                            }
                        }
                        case 2 -> {
                            System.out.println("Introduce 'true' o 'false' si está hecha o no");
                            Scanner scanner = new Scanner(System.in);
                            String input = scanner.nextLine().trim();

                            boolean hecha;
                            if (input.equalsIgnoreCase("true")) {
                                hecha = true;
                            } else if (input.equalsIgnoreCase("false")) {
                                hecha = false;
                            } else {
                                System.out.println("Entrada no válida. Debes escribir 'true' o 'false'.");
                                break;
                            }
                            int posicionTarea = 0;
                            for (int i = 0; i < tareas.size(); i++) {
                                if (tareas.get(i).getId() == id) {
                                    posicionTarea = i;
                                }
                            }

                            tareas.get(posicionTarea).setHecho(hecha);
                            DBDDL.editarDatos(id, "", "", hecha, opcionEditar);
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Introduce el ID de la tarea que desea eliminar");
                    int idIntroducida = new Scanner(System.in).nextInt();

                    Tarea tareaAEliminar = null;
                    for (Tarea tarea : tareas) {
                        if (tarea.getId() == idIntroducida) {
                            tareaAEliminar = tarea;
                            break;
                        }
                    }

                    if (tareaAEliminar != null) {
                        tareas.remove(tareaAEliminar);
                        DBDDL.eliminarTarea(idIntroducida);
                    } else {
                        System.out.println("No existe una tarea con ese ID.");
                    }
                }

                case 5 -> repetir = false;

            }
        }
    }
}

