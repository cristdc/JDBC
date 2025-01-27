package org.example.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDDL {

    public static void crearTablaTarea() {
        String sql = """
            CREATE TABLE IF NOT EXISTS public."Tarea" (
                id SERIAL PRIMARY KEY,
                titulo TEXT NOT NULL,
                descripcion TEXT NOT NULL
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

        public static void guardarDatos(String titulo, String descripcion){
            String sql = """
                INSERT INTO "Tarea" (titulo, descripcion)
                VALUES (?, ?);
            """;

            try (PreparedStatement ps = ConexionSingleton.getConnection().prepareStatement(sql)) {
                ps.setString(1, titulo);
                ps.setString(2, descripcion);

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
        }

    }
}
