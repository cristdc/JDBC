package org.example.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DBDDL {

    public static void crearTablaTarea() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS public."Tarea" (
                        id SERIAL PRIMARY KEY,
                        titulo TEXT NOT NULL,
                        descripcion TEXT NOT NULL,
                        hecho BOOLEAN NOT NULL
                    );
                """;

        try (Connection conexion = ConexionSingleton.getConnection();
             Statement stmt = conexion.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabla creada");

        } catch (SQLException sqlEx) {
            System.out.println("Error SQL al ejecutar la sentencia:");
            System.out.println(sql);
            System.out.println("Detalles del error: " + sqlEx.getMessage());
            sqlEx.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error general al intentar crear la tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void guardarDatos(String titulo, String descripcion, boolean hecho) {
        String sql = """
                    INSERT INTO "Tarea" (titulo, descripcion, hecho)
                    VALUES (?, ?, ?);
                """;
        try (PreparedStatement ps = ConexionSingleton.getConnection().prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setBoolean(3, hecho);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarDatos(int id, String titulo, String descripcion, boolean hecho, int opcion) {
        String sql;
        switch (opcion) {
            case 1 -> {
                sql = """
                            UPDATE "Tarea"
                            SET titulo = ?, descripcion = ?
                            WHERE id = ?;
                        """;
                try (PreparedStatement ps = ConexionSingleton.getConnection().prepareStatement(sql)) {
                    ps.setString(1, titulo);
                    ps.setString(2, descripcion);
                    ps.setInt(3, id);

                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                sql = """
                            UPDATE "Tarea"
                            SET hecho = ?
                            WHERE id = ?;
                        """;
                try (PreparedStatement ps = ConexionSingleton.getConnection().prepareStatement(sql)) {
                    ps.setBoolean(1, hecho);
                    ps.setInt(2, opcion);


                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static List<Tarea> consultarDatos() {
        List<Tarea> tarea = new ArrayList<>();
        String query = """ 
                SELECT * FROM "Tarea";
                """;
        try (PreparedStatement ps = ConexionSingleton.getConnection().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tarea tarea1 = new Tarea(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("descripcion"),
                            rs.getBoolean("hecho")
                    );
                    tarea.add(tarea1);

                    //System.out.println(tarea1.toString());
                }
                Collections.sort(tarea, Comparator.comparingInt(Tarea::getId));

                for (Tarea t : tarea) {
                    System.out.println(t.toString());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarea;

    }

    public static void mostrarTareas(List<Tarea> tareas) {
        Collections.sort(tareas, Comparator.comparingInt(Tarea::getId));

        for (Tarea tarea : tareas) {
            System.out.println(tarea.toString());
        }
    }

    public static void eliminarTarea(int id){

    }
}
