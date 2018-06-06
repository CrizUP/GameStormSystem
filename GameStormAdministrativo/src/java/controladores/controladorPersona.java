/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import modelo.Cuenta;
import modelo.Persona;
import servicios.ServicioCuenta;
import servicios.ServicioPersona;

/**
 *
 * @author Irdevelo
 */
@Named(value = "controladorPersona")
@SessionScoped
public class controladorPersona implements Serializable {

    private Integer idPersona;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String sexo;
    private Persona persona;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public controladorPersona() {

    }

    public void eliminar() {
        ServicioPersona servicio = new ServicioPersona();
        ServicioCuenta servicioCuenta = new ServicioCuenta();
        servicioCuenta.remove(nombre);
        servicio.remove(idPersona);
    }

    public List<Cuenta> getObtenerEmpleados() {

        ServicioPersona servicio = new ServicioPersona();
        ServicioCuenta servicioCuenta = new ServicioCuenta();
        List<Cuenta> empleados;

        empleados = servicio.obtenerEmpleados();

        return empleados;

    }

    public void registrarEmpleado() {

        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellidos(apellidos);
        persona.setCorreo(correo);
        persona.setTelefono(telefono);
        persona.setSexo(sexo);

        ServicioPersona servicio = new ServicioPersona();
        servicio.create(persona);

        Persona persona2;
        persona2 = servicio.obtenerID();

        ServicioCuenta servicioCuenta = new ServicioCuenta();
        Cuenta cuenta = new Cuenta();

        cuenta.setRol("Empleado");
        cuenta.setUsuario(persona.getNombre());
        try {
            cuenta.setContrasena(cifrarContrasena(persona.getNombre()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(controladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        cuenta.setIdPersona(persona2);
        servicioCuenta.create(cuenta);

        this.nombre = "";
        this.apellidos = "";
        this.correo = "";
        this.telefono = "";
        this.sexo = "";

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

}
