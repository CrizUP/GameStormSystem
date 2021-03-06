/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Juego;

/**
 *
 * @author Irdevelo
 */
@Stateless
@Path("modelo.juego")
public class JuegoFacadeREST extends AbstractFacade<Juego> {

    @PersistenceContext(unitName = "GameStormServidorPU")
    private EntityManager em;

    public JuegoFacadeREST() {
        super(Juego.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Juego entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Juego entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Juego find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Juego> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Juego> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @POST
    @Path("/fotos/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response subirImagen(@Context HttpServletRequest request, @PathParam("id") Integer id) {
        String salida = "";
        FileOutputStream archivo = null;
        try {
            if (request.getParts().size() > 0) {
                Part parte = (Part) request.getParts().toArray()[0];
                String ruta = new File(".").getCanonicalPath() + "/fotos/" + id + ".jpg";
                InputStream is = parte.getInputStream();
                int tamano = (int) parte.getSize();
                byte array[] = new byte[tamano];
                is.read(array, 0, tamano);
                archivo = new FileOutputStream(ruta);
                archivo.write(array, 0, tamano);

                salida = "{\"Respuesta\": \"OK\"}";
            }
        } catch (IOException | ServletException ex) {
            salida = "{\"Respuesta\": \"" + ex.toString() + "\"}";
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(JuegoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(200).entity(salida).build();
    }

    @POST
    @Path("/fotos/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void subirImagenJSF(File imagen, @PathParam("id") Integer id) {
       
        BufferedImage imagenCrear = null;
        try{
            String ruta = new File(".").getCanonicalPath() + "/fotos/" + id + ".jpg";
            imagenCrear = ImageIO.read(imagen);
            System.out.println("Imagen empieza a crear");
            ImageIO.write(imagenCrear, "jpg", new File(ruta));
            System.out.println("Se creó la imagen");
        } catch (IOException ex) {
            Logger.getLogger(JuegoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no se puede crear la imagen");
        }
        
    }

}
