package org.example;

import org.example.modelo.ConexionSingleton;
import org.example.modelo.DBDDL;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        ConexionSingleton.getConnection();
        DBDDL.crearTablaTarea();
    }
}