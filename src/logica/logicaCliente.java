/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mysql.coneccion;

public class logicaCliente {
    public cliente[] seleccionar(String filtro) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "SELECT * FROM cliente";
            if (!filtro.equals("")) {
                query += " WHERE " + filtro;
            }
            Statement statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            cliente[] result = new cliente[rowCount];
            int index = 0;
            while (resultSet.next()) {
                cliente data = new cliente(); 
                data.setCedula(resultSet.getString("cedula"));
                data.setNombre(resultSet.getString("nombre"));
                data.setTelefono(resultSet.getString("telefono"));
                data.setDireccion(resultSet.getString("direccion"));
                result[index] = data; 
                index++;
            }
            conexion.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Error al obtener " + e.getMessage());
            return null;
        }
    }

    public boolean registrar(String cedula, String nombre, String telefono, String direccion) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "INSERT INTO cliente(cedula, nombre, telefono, direccion) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, telefono);
            preparedStatement.setString(4, direccion);
            int rowsAffected = preparedStatement.executeUpdate();
            conexion.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(String cedula, String nombre, String telefono, String direccion) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "UPDATE cliente SET cedula = ?, nombre = ?, telefono = ?, direccion = ?  WHERE cedula = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, telefono);
            preparedStatement.setString(4, direccion);
            preparedStatement.setString(5, cedula);
            int rowsAffected = preparedStatement.executeUpdate();
            conexion.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String cedula) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "DELETE FROM cliente WHERE cedula = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, cedula);
            int rowsAffected = preparedStatement.executeUpdate();
            conexion.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            return false;
        }
    }
}
