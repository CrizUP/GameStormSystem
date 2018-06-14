package com.example.iro19.gamestormmovil.negocio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Persona implements Serializable{
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String sexo;
    private int idPersona;

    public Persona(JSONObject jsonCuenta) throws JSONException {
        this.nombre = jsonCuenta.getString("nombre");
        this.apellidos = jsonCuenta.getString("apellidos");
        this.correo = jsonCuenta.getString("correo");
        this.telefono = jsonCuenta.getString("telefono");
        this.sexo = jsonCuenta.getString("sexo");
        this.idPersona = jsonCuenta.getInt("idPersona");
    }

    public Persona(){

    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
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
}
