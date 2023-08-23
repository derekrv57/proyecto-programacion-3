/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

public class usuario {
    private String cedula, nombre, contra, puesto;
    float salario;

    public usuario() {
    }
    
    public usuario(String cedula, String nombre, String contra, String puesto, float salario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contra = contra;
        this.puesto = puesto;
        this.salario = salario;
    }

    public usuario(String cedula, String contra) {
        this.cedula = cedula;
        this.contra = contra;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
       
}
