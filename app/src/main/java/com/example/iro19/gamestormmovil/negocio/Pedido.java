package com.example.iro19.gamestormmovil.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

public class Pedido implements Serializable {
    private Cuenta cuenta;
    private Juego juego;
    private int cantidadJuegos;
    private Date fechaApartado;
    private Date fechaLimite;
    private float precioTotal;
    private Date fechaPago;
    private String estatus;
    private int idPedido;
    private int idJuego;
    private String usuario;

    public Pedido() {
    }

    public Date calcularFechaLimite(Date fechaLimite){
        this.fechaLimite = fechaLimite;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaLimite);
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        fechaLimite = calendar.getTime();
        return fechaLimite;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getCantidadJuegos() {
        return cantidadJuegos;
    }

    public void setCantidadJuegos(int cantidadJuegos) {
        this.cantidadJuegos = cantidadJuegos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Date getFechaApartado() {
        return fechaApartado;
    }

    public void setFechaApartado(Date fechaApartado) {
        this.fechaApartado = fechaApartado;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
