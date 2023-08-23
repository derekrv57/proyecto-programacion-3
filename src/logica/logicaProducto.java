/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mysql.coneccion;

/**
 *
 * @author derek
 */
public class logicaProducto {
    public producto[] seleccionar(String filtro) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "SELECT * FROM producto";
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
            producto[] result = new producto[rowCount];
            int index = 0;
            while (resultSet.next()) {
                producto data = new producto(); 
                data.setId(resultSet.getInt("id"));
                data.setProducto(resultSet.getString("producto"));
                data.setCantidad(resultSet.getInt("cantidad"));
                data.setDescripcion(resultSet.getString("descripcion"));
                data.setPrecio(resultSet.getFloat("precio"));
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

    public boolean registrar(String producto, float precio, int cantidad, String descripcion) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "INSERT INTO producto(producto, precio, cantidad, descripcion) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, producto);
            preparedStatement.setFloat(2, precio);
            preparedStatement.setInt(3, cantidad);
            preparedStatement.setString(4, descripcion);
            int rowsAffected = preparedStatement.executeUpdate();
            conexion.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(int id, String producto, float precio, int cantidad, String descripcion) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "UPDATE producto SET producto = ?, precio = ?, cantidad = ?, descripcion = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, producto);
            preparedStatement.setFloat(2, precio);
            preparedStatement.setInt(3, cantidad);
            preparedStatement.setString(4, descripcion);
            preparedStatement.setInt(5, id);
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
            String query = "DELETE FROM producto WHERE id = ?";
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
