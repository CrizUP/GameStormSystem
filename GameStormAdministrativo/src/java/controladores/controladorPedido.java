/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import modelo.Cuenta;
import modelo.Juego;
import modelo.Pedido;
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

    public controladorPedido() {
    }

    public List<Pedido> getObtenerPedidos() {
        List<Pedido> pedidos;
        ServicioPedido servicio = new ServicioPedido();
        pedidos = servicio.findAll();
        return pedidos;
    }

}
