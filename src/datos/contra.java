/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author derek
 */
public class contra {

    public String encriptar(String contra) {
        for (int i = 0; i < 100; i++) {
            contra = new md5().getMD5(contra);
        }
        return contra;
    }
    
}
