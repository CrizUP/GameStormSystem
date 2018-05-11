/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoresVistaWEB;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import modelo.Cuenta;
import modelo.controladores.CuentaControlador;

/**
 *
 * @author Cristhian Ubaldo Promotor
 */
@Named(value = "cuentaBean")
@SessionScoped 
public class CuentaBean implements Serializable{

    private String usuario;
    private String contrasena;
    private String rol;
    private CuentaControlador cuentas;
    private Cuenta cuenta;
    
    public CuentaBean() {
        cuentas = new CuentaControlador();
    }
    
    public List<Cuenta> getListaEmpleados(){
        return cuentas.buscarEmpleados();
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public CuentaControlador getCuentas() {
        return cuentas;
    }

    public void setCuentas(CuentaControlador cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
