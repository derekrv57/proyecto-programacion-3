/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.producto;
import datos.venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import mysql.coneccion;

public class logicaVenta {
    public venta[] seleccionar(String filtro) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "SELECT * FROM venta";
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
            venta[] result = new venta[rowCount];
            int index = 0;
            while (resultSet.next()) {
                venta data = new venta(); 
                data.setId(resultSet.getInt("id"));
                data.setMonto(resultSet.getFloat("monto"));
                data.setProductos(resultSet.getString("productos"));
                data.setMetodoPago(resultSet.getString("metodo_pago"));
                data.setComprador(resultSet.getString("comprador"));
                data.setVendedor(resultSet.getString("vendedor"));
                data.setFecha(resultSet.getString("fecha"));
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

    public boolean registrar(float monto, String productos, String metodoPago, String comprador, String vendedor, List<producto> lista) {
    try {
        Connection conexion = coneccion.getConnection();
        String insertQuery = "INSERT INTO venta(monto, productos, metodo_pago, comprador, vendedor, fecha) VALUES (?, ?, ?, ?, ?, NOW())";
        PreparedStatement insertStatement = conexion.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setFloat(1, monto);
        insertStatement.setString(2, productos);
        insertStatement.setString(3, metodoPago);
        insertStatement.setString(4, comprador);
        insertStatement.setString(5, vendedor);
        int rowsAffected = insertStatement.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            int ventaId = -1;
            if (generatedKeys.next()) {
                ventaId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            if (ventaId != -1) {
                for (producto p : lista) {
                    String updateQuery = "UPDATE producto SET cantidad = cantidad - ? WHERE id = ?";
                    PreparedStatement updateStatement = conexion.prepareStatement(updateQuery);
                    updateStatement.setInt(1, p.getCantidad());
                    updateStatement.setInt(2, p.getId());
                    updateStatement.executeUpdate();
                    updateStatement.close();
                }
            }
            conexion.close();
            return true;
        } else {
            conexion.close();
            return false;
        }
    } catch (SQLException e) {
        System.err.println("Error al insertar datos: " + e.getMessage());
        return false;
    }
}

    public boolean editar(int id, float monto, String productos, String metodoPago) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "UPDATE venta SET monto = ?, productos = ?, metodo_pago = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setFloat(1, monto);
            preparedStatement.setString(2, productos);
            preparedStatement.setString(3, metodoPago);
            preparedStatement.setInt(4, id);
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
            String query = "DELETE FROM venta WHERE id = ?";
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
