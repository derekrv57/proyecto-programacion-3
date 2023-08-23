/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


import gui.fRegistro;
import datos.usuario;
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
public class logicaUsuario {
    public usuario[] seleccionar(String filtro) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "SELECT * FROM empleado";
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
            usuario[] result = new usuario[rowCount];
            int index = 0;
            while (resultSet.next()) {
                usuario data = new usuario();
                data.setCedula(resultSet.getString("cedula"));
                data.setNombre(resultSet.getString("nombre"));
                data.setSalario(resultSet.getFloat("salario"));
                data.setPuesto(resultSet.getString("puesto"));
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
    
    public usuario login(javax.swing.JFrame aThis, usuario u) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "SELECT cedula, nombre, salario, puesto FROM empleado WHERE cedula = ? AND contra = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, u.getCedula());
            preparedStatement.setString(2, u.getContra());
            ResultSet resultSet = preparedStatement.executeQuery();
            int index = 0;
            while (resultSet.next()) {
                u.setCedula(resultSet.getString("cedula"));
                u.setNombre(resultSet.getString("nombre"));
                u.setSalario(resultSet.getFloat("salario"));
                u.setPuesto(resultSet.getString("puesto"));
                u.setContra(null);
                index++;
            }
            conexion.close();
            if (index > 0) {
                return u;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
            return null;
        }
    }

    public usuario registrar(fRegistro aThis, String cedula, String nombre, String contra, float salario , String puesto) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "INSERT INTO empleado(cedula, nombre, contra, salario, puesto) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, contra);
            preparedStatement.setFloat(4, salario);
            preparedStatement.setString(5, puesto);
            int rowsAffected = preparedStatement.executeUpdate();
            conexion.close();
            if (rowsAffected > 0) {
                return new usuario(cedula, nombre, contra, puesto, salario);
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            return null;
        }
    }
    
    public boolean editar(String cedula, String nombre, float salario, String puesto) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "UPDATE empleado SET cedula = ?, nombre = ?, salario = ?, puesto = ?  WHERE cedula = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setFloat(3, salario);
            preparedStatement.setString(4, puesto);
            preparedStatement.setString(5, cedula);
            int rowsAffected = preparedStatement.executeUpdate();
            conexion.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            return false;
        }
    }
    
    public boolean editarContra(String cedula, String anterior, String nueva) {
        try {
            Connection conexion = coneccion.getConnection();
            String query = "UPDATE empleado SET contra = ? WHERE contra = ? AND cedula = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, nueva);
            preparedStatement.setString(2, anterior);
            preparedStatement.setString(3, cedula);
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
            String query = "DELETE FROM empleado WHERE cedula = ?";
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
