/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/apprecetas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";
    private static final Logger logger = Logger.getLogger(ConexionBD.class.getName());

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "No se encontró el driver JDBC de MySQL", e);
            throw new SQLException("Driver JDBC no disponible", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al conectar a la base de datos", e);
            throw e;
        }
    }

    // Método para test local
    public static void main(String[] args) {
        try {
            Connection con = obtenerConexion();
            if (con != null) {
                System.out.println("✅ ¡Conexión a MySQL exitosa!");
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("❌ No se pudo conectar a la base de datos.");
        }
    }
}

