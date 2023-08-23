/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.proveedor;
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
public class logicaProveedor {
    public proveedor[] seleccionar(String filtro) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "SELECT * FROM proveedor";
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
            proveedor[] result = new proveedor[rowCount];
            int index = 0;
            while (resultSet.next()) {
                proveedor data = new proveedor(); 
                data.setId(resultSet.getInt("id"));
                data.setEmpresa(resultSet.getString("empresa"));
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

    public boolean registrar(String proveedor) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "INSERT INTO proveedor(empresa) VALUES (?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, proveedor);
            int rowsAffected = preparedStatement.executeUpdate();
            conexion.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(int id, String proveedor) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "UPDATE proveedor SET empresa = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, proveedor);
            preparedStatement.setInt(2, id);
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
            String query = "DELETE FROM proveedor WHERE id = ?";
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
