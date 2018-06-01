/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Cuenta;
import servicios.ServicioCuenta;

/**
 *
 * @author Irdevelo
 */
@Named(value = "controladorCuenta")
@SessionScoped
public class controladorCuenta implements Serializable{

    private String nombreUsuario;
    private String contrasena;
    private Cuenta cuenta;
    private boolean esValido;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public boolean isEsValido() {
        return esValido;
    }

    public void setEsValido(boolean esValido) {
        this.esValido = esValido;
    }

    public controladorCuenta() {
        esValido = false;
    }

    public void iniciarSesion(){
        try {
            ServicioCuenta servicio = new ServicioCuenta();            
            String respuesta = servicio.encontrarUsuario(nombreUsuario,cifrarContrasena(contrasena));
            
            if (respuesta.equals("true")) {
                esValido = true;
                 FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("/GameStormAdministrativo/faces/PlantillaGeneralEmpleado.xhtml");
                 //System.out.println(servicio.find(Cuenta.class, nombreUsuario).toString());
            } 
            
        }catch(Exception e){
        e.printStackTrace();
        }

    }

    public String cifrarContrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(contrasena.getBytes(Charset.forName("UTF-8")));
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
//    
//    public void validar() {
//        if (!esValido) {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext()
//                        .redirect("/GameStormeEmpleados/faces/index.xhtml");
//            } catch (IOException e) {
//
//            }
//            esValido = false;
//        } 
//    }

}
