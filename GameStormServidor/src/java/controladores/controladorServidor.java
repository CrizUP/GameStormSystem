/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Irdevelo
 */
@Named(value = "controladorServidor")
@Dependent
public class controladorServidor {

    /**
     * Creates a new instance of controladorServidor
     */
    public controladorServidor() {
        
    }
    
    
    public void encontrarRuta(){
        try {
            String ruta = new File(".").getCanonicalPath();
            System.out.println("La ruta del servidor es :"+ ruta);
        } catch (IOException ex) {
           // Logger.getLogger(controladorServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
