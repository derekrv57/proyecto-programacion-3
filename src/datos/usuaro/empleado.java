/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.usuaro;

/**
 *
 * @author derek
 */
public class empleado extends usuario{
    double salaro;
    String puesto;

    public empleado(String usuario, String correo, String nombre, String cedula, double salaro, String puesto) {
        super(usuario, correo, nombre, cedula);
        this.salaro = salaro;
        this.puesto = puesto;
        super.setTipo("empleado");
    }
    
    public double getSalaro() {
        return salaro;
    }

    public String getPuesto() {
        return puesto;
    }
    
}
