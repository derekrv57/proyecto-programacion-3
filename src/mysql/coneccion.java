/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql;
import java.sql.*;
/**
 *
 * @author derek
 */
public class coneccion {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/proyecto-programacion-3";
    public static final String user = "user", password = "access";

    public static Connection getConnection() {
        Connection cnx = null;
        try {
            cnx = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.err.println(e);
        }
        return cnx;
    }
    
    
}
