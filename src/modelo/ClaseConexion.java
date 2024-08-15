package modelo;

import java.sql.*;

public class ClaseConexion {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = "system";
    private static final String CONTRASENA = "Oracle2024";

    public static Connection getConexion() {
        try {
            // Cargar el driver JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");
 
            // Obtener la conexión en una variable
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            // Retornamos la variable que tiene la conexión
            return conexion;
        } catch (SQLException e) {
            System.out.println("Este es el error" + e);
              return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("este es el error de la clase" + ex);
              return null;
        }
    }
}
