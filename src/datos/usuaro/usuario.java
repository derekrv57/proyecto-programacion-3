/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.usuaro;

import gui.fRegistro;
import javax.swing.JOptionPane;


/**
 *
 * @author derek
 */
public class usuario {

    private String usuario, correo, nombre, cedula, tipo;

    public usuario() {
    }

    public usuario(String usuario, String correo, String nombre, String cedula) {
        this.usuario = usuario;
        this.correo = correo;
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    
    public usuario login(javax.swing.JFrame aThis, String usuario, String contra) {
        if (usuario.equalsIgnoreCase("admin") && contra.equals("admin")) { //Momentaneo hasta implementar db
            return new empleado(usuario, correo, nombre, cedula, 50000.0, "Jefe");
        }
        else{
            if (usuario.equalsIgnoreCase("user") && contra.equals("user")) { //Momentaneo hasta implementar db
                return new cliente(usuario, correo, nombre, cedula, "casa", "800jorge");
            }
            else{
                JOptionPane.showMessageDialog(aThis, "Usuario y/o contraseña invalidos");
                return null;
            }
        }
    }

    public boolean registrar(fRegistro aThis, String usuario, String contra, String nombre, String cedula, String tipo) {
        return true;
    }
  
}
