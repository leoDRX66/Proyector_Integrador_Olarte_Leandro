package com.mycompany.restaurante.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante"; 
    private static final String USER = "root"; 
    private static final String PASS = "littlefenix4862"; 

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error al conectando a la BD: " + e.getMessage());
            return null;
        }
    }
}