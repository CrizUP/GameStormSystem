/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Juego;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;
import servicios.ServicioJuego;

/**
 *
 * @author Cristhian Ubaldo Promotor
 */
@Named(value = "controladorJuego")
@SessionScoped
public class controladorJuego implements Serializable {

    private Integer idJuego;
    private String nombreJuego;
    private String descripcion;
    private float promedio;
    private int stock;
    private float precio;
    private String consola;
    private UploadedFile imagen;
    private String linkVideo;
    private String genero;
    private String empresaDesarrolladora;
    private Juego juego;

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
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

    public String getEmpresaDesarrolladora() {
        return empresaDesarrolladora;
    }

    public void setEmpresaDesarrolladora(String empresaDesarrolladora) {
        this.empresaDesarrolladora = empresaDesarrolladora;
    }

    public controladorJuego() {

    }

    public void registrarJuego() {

        Juego juegoNuevo = new Juego();

        juegoNuevo.setNombreJuego(nombreJuego);
        juegoNuevo.setConsola(consola);
        juegoNuevo.setDescripcion(descripcion);
        juegoNuevo.setEmpresaDesarrolladora(empresaDesarrolladora);
        juegoNuevo.setGenero(genero);
        juegoNuevo.setLinkVideo(linkVideo);
        juegoNuevo.setImagen("sin imagen");
        juegoNuevo.setPrecio(precio);
        juegoNuevo.setPromedio(0);
        juegoNuevo.setStock(stock);

        //System.out.println("Archivo seleccionado " + imagen.getFileName() + " tamaño " + imagen.getSize());
        ServicioJuego servicio = new ServicioJuego();
        servicio.create(juegoNuevo);

        this.nombreJuego = "";
        this.descripcion = "";
        this.promedio = 0;
        this.stock = 0;
        this.precio = 0;
        this.consola = "";
        this.imagen = null;
        this.linkVideo = "";
        this.genero = "";
        this.empresaDesarrolladora = "";
    }

    public List<Juego> getObtenerJuegos() {
        List<Juego> juegos;
        ServicioJuego servicios = new ServicioJuego();
        juegos = servicios.findAll();
        return juegos;
    }

    public void subirFoto(FileUploadEvent event) {

        UploadedFile imagenSeleccionada = event.getFile();

        List<Juego> juegoConUltimo = getObtenerJuegos();
        Juego ultimoJuego = juegoConUltimo.get(juegoConUltimo.size() - 1);

        String rutaNueva = "C:\\Users\\Cris_\\Documents\\GitHub\\GameStormSystem\\GameStormServidor\\web\\imagenesJuegos\\";

        try {
            FileOutputStream salida;
            try (FileInputStream entrada = (FileInputStream) imagenSeleccionada.getInputstream()) {
                salida = new FileOutputStream(rutaNueva + (ultimoJuego.getIdJuego()+1) + ".jpg");
                byte[] buffer = new byte[(int) imagenSeleccionada.getSize()];
                int contador = 0;
                while ((contador = entrada.read(buffer)) != -1) {
                    salida.write(buffer, 0, contador);
                }
            }
            salida.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void editarJuego() {

        nombreJuego = juego.getNombreJuego();
        descripcion = juego.getDescripcion();
        promedio = juego.getPromedio();
        stock = juego.getStock();
        precio = juego.getPrecio();
        consola = juego.getConsola();
        linkVideo = juego.getLinkVideo();
        genero = juego.getGenero();
        empresaDesarrolladora = juego.getEmpresaDesarrolladora();

    }

    public void guardarCambiosJuego() {
        ServicioJuego servicio = new ServicioJuego();

        juego.setNombreJuego(nombreJuego);
        juego.setDescripcion(descripcion);
        juego.setConsola(consola);
        juego.setEmpresaDesarrolladora(empresaDesarrolladora);
        juego.setPromedio(promedio);
        juego.setPrecio(precio);
        juego.setStock(stock);
        juego.setLinkVideo(linkVideo);
        juego.setGenero(genero);

        servicio.edit(juego, juego.getIdJuego());

    }

}
