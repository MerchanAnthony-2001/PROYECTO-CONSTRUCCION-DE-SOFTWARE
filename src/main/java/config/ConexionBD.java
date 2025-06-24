/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Configura tus valores según tu entorno
    private static final String URL = "jdbc:mysql://localhost:3306/apprecetas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = ""; // cambia si tu MySQL tiene clave

    public static Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
