/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/apprecetas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = ""; // O tu contraseña real

    public static Connection obtenerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        Connection con = obtenerConexion();
        if (con != null) {
            System.out.println("✅ ¡Conexión a MySQL exitosa!");
        } else {
            System.out.println("❌ No se pudo conectar a la base de datos.");
        }
    }
   

}
