/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import javax.faces.context.FacesContext;
import modelo.Cuenta;
import modelo.Juego;
import modelo.Pedido;
import servicios.ServicioJuego;
import servicios.ServicioPedido;

/**
 *
 * @author Irdevelo
 */
@Named(value = "controladorPedido")
@SessionScoped
public class controladorPedido implements Serializable {

    private Integer idPedido;
    private int cantidadJuegos;
    private Date fechaApartado;
    private Date fechaLimite;
    private float precioTotal;
    private Date fechaPago;
    private String estatus;
    private Juego idJuego;
    private Cuenta usuario;
    private Pedido pedido;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public int getCantidadJuegos() {
        return cantidadJuegos;
    }

    public void setCantidadJuegos(int cantidadJuegos) {
        this.cantidadJuegos = cantidadJuegos;
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

    public Juego getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Juego idJuego) {
        this.idJuego = idJuego;
    }

    public Cuenta getUsuario() {
        return usuario;
    }

    public void setUsuario(Cuenta usuario) {
        this.usuario = usuario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public controladorPedido() {
    }

    public List<Pedido> getObtenerPedidos() {
        List<Pedido> pedidos;
        ServicioPedido servicio = new ServicioPedido();
        pedidos = servicio.findAll();
        return pedidos;
    }

    public void registrarCompra() {
        ServicioPedido servicio = new ServicioPedido();

        java.sql.Date fechaEntrega = java.sql.Date.valueOf(LocalDate.now());

        pedido.setEstatus("Entregado");
        pedido.setFechaPago(fechaEntrega);
        servicio.edit(pedido, pedido.getIdPedido());
        FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(SEVERITY_INFO, "Vendido", "El pedido ha sido entregado"));

    }

    public void cancelarCompra() {

        if (pedido.getEstatus().equals("Entregado")) {

            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(SEVERITY_INFO, "Error", "No se puede cancelar un pedido entregado"));

        } else {
            ServicioPedido servicio = new ServicioPedido();
            ServicioJuego servicioJuego = new ServicioJuego();

            int stock = pedido.getIdJuego().getStock() + 1;

            pedido.getIdJuego().setStock(stock);

            servicioJuego.edit(pedido.getIdJuego(), pedido.getIdJuego().getIdJuego());

            pedido.setEstatus("Cancelado");
            servicio.edit(pedido, pedido.getIdPedido());
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(SEVERITY_INFO, "Cancelado", "El pedido ha sido cancelado"));

        }

    }

}
