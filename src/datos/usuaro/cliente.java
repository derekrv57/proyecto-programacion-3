/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.usuaro;

/**
 *
 * @author derek
 */
public class cliente extends usuario{
    String direccion, tel;

    public cliente(String usuario, String correo, String nombre, String cedula, String direccion, String tel) {
        super(usuario, correo, nombre, cedula);
        this.direccion = direccion;
        this.tel = tel;
        super.setTipo("cliente");
    }
    
    public String getDireccion() {
        return direccion;
    }

    public String getTel() {
        return tel;
    }
    
}
