/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import modelo.Cuenta;
import servicios.ServicioCliente;

/**
 *
 * @author Irdevelo
 */
@Named(value = "controladorCuenta")
@Dependent
public class controladorCuenta {

    private String nombreUsuario;
    private String contraseña;
    private Cuenta cuenta;
    private boolean esValido;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
    }

    public void iniciarSesion()throws NoSuchAlgorithmException {
        try {
            ServicioCliente servicio = new ServicioCliente();
            String respuesta = "";
            System.out.println("123");
            respuesta = servicio.encontrarUsuario(nombreUsuario,cifrarContrasena(contraseña));
            System.out.println("1234");
            if (respuesta.equals("true")) {
                System.out.println("true");
            } else {
                System.out.println("Usuario incorrecto");
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
    
    public void validar() {
        if (!esValido) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("/GameStormeEmpleados/faces/index.xhtml");
            } catch (IOException e) {

            }
            esValido = false;
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("/GameStormeEmpleados/faces/ventanaEmpleadosxhtml");
            } catch (IOException e) {
            }
            esValido = true;
        }
    }

}
