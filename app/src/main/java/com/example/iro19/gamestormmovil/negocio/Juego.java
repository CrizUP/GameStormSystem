package com.example.iro19.gamestormmovil.negocio;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class Juego {
    private String nombreJuego;
    private float precioJuego;
    private Bitmap nombreFoto;

    public Juego(JSONObject jsonJuego) throws JSONException{
        this.nombreJuego = jsonJuego.getString("nombreJuego");
        this.precioJuego = jsonJuego.getInt("precio");
    }

    public Bitmap getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(Bitmap nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public float getPrecioJuego() {
        return precioJuego;
    }

    public void setPrecioJuego(float precioJuego) {
        this.precioJuego = precioJuego;
    }
}
