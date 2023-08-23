/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import gui.fVenta;

public class threadVenta extends Thread{
    fVenta v;

    public threadVenta(fVenta v) {
        this.v = v;
    }
    
    @Override
    public void run(){
        v.productos = new logicaProducto().seleccionar("");
        v.actualizarProductos();
    }
}
