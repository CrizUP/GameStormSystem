package com.example.iro19.gamestormmovil.negocio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Cuenta implements Serializable{
    private Persona persona;
    private String usuario;
    private String contrasena;
    private int idPersona;
    private String rol;

    public Cuenta(JSONObject jsonCuenta) throws JSONException {
        usuario = jsonCuenta.getString("usuario");
        persona = new Persona (jsonCuenta.getJSONObject("idPersona"));
        contrasena = jsonCuenta.getString("contrasena");
    }

    public Cuenta(){

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
