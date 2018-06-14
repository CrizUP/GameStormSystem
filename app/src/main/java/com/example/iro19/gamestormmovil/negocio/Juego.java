package com.example.iro19.gamestormmovil.negocio;

import android.graphics.Bitmap;
import android.os.Parcel;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class Juego implements Serializable {
    private String nombreJuego;
    private float precioJuego;
    private String nombreFoto;
    private int idJuego;
    private String descripcion;
    private float promedio;
    private int stock;
    private String consola;
    private String linkVideo;
    private String genero;
    private String desarrolladores;
    private ProxyBitmap proxyBitmap;

    public Juego(JSONObject jsonJuego) throws JSONException{
        this.nombreJuego = jsonJuego.getString("nombreJuego");
        this.precioJuego = jsonJuego.getInt("precio");
        this.consola = jsonJuego.getString("consola");
        this.desarrolladores = jsonJuego.getString("empresaDesarrolladora");
        this.descripcion = jsonJuego.getString("descripcion");
        this.genero = jsonJuego.getString("genero");
        this.idJuego = jsonJuego.getInt("idJuego");
        this.linkVideo = jsonJuego.getString("linkVideo");
        this.promedio = Float.parseFloat(jsonJuego.getString("promedio"));
        this.stock = jsonJuego.getInt("stock");
        this.nombreFoto = jsonJuego.getString("imagen");
    }

    public ProxyBitmap getProxyBitmap() {
        return proxyBitmap;
    }

    public void setProxyBitmap(ProxyBitmap proxyBitmap) {
        this.proxyBitmap = proxyBitmap;
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDesarrolladores() {
        return desarrolladores;
    }

    public void setDesarrolladores(String desarrolladores) {
        this.desarrolladores = desarrolladores;
    }

    public void setNombreFoto(String nombreFoto) {
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
