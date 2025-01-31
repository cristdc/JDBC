package org.example;

import org.example.modelo.ConexionSingleton;
import org.example.modelo.DBDDL;
import org.example.modelo.Tarea;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        List<Tarea> tareas = new ArrayList<>();

        ConexionSingleton.getConnection();
        DBDDL.crearTablaTarea();
        boolean repetir = true;
        while(repetir){
            System.out.println("""
                    Elija una de las siguientes opciones:
                    1 -> Consultar tareas.
                    2 -> Añadir tarea.
                    3 -> Editar tarea.
                    4 -> Eliminar tarea.
                    5 -> Salir.
                    """);
            String eleccion = new Scanner(System.in).nextLine();
            int num = Integer.parseInt(eleccion);
            switch (num){
                case 1 -> {
                    List<Tarea> tareas1 = DBDDL.consultarDatos();
                    tareas=tareas1;
                }
                case 2 -> {
                    System.out.println("Introduce el título de la tarea");
                    String titulo = new Scanner(System.in).nextLine();
                    System.out.println("Introduce las descripción de la tarea");
                    String descripcion = new Scanner(System.in).nextLine();
                    Tarea tarea = new Tarea(titulo,descripcion,false);
                    DBDDL.guardarDatos(tarea.getTitulo(), tarea.getDescripcion(), false);
                }
                case 3 ->{
                    System.out.println("¿Cuál es la tarea que quieres editar?");
                    int id = new Scanner(System.in).nextInt();
                    System.out.println("""
                            ¿Que desea editar de la tarea?
                            1 -> Título y descripción.
                            2 -> Realizada.
                            """);
                    int opcionEditar = new Scanner(System.in).nextInt();
                    switch (opcionEditar){

                        case 1 -> {
                            System.out.println("Introduce el nuevo título");
                            String tituloNuevo = new Scanner(System.in).nextLine();
                            System.out.println("Introduce la nueva descripción");
                            String descripcionNueva = new Scanner(System.in).nextLine();
                            DBDDL.editarDatos(id, tituloNuevo, descripcionNueva,false,  opcionEditar);
                        }
                        case 2 -> {
                            System.out.println("Introduce 'true' o 'false' si está hecha o no");
                            boolean hecha = new Scanner(System.in).nextBoolean();
                            DBDDL.editarDatos(id, "", "",hecha,  opcionEditar);
                        }
                    }
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