/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Cuenta;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 * Descripcion
 *
 * @author Cristhian Ubaldo Promotor
 * @version fecha
 */
@Stateless
@Path("modelo.cuenta")
public class CuentaFacadeREST extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "GameStormPU")
    private EntityManager em;

    public CuentaFacadeREST() {
        super(Cuenta.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cuenta entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Cuenta entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cuenta find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cuenta> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cuenta> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("/login/{nombreUsuario}/{contraseña}")
    @Produces({MediaType.APPLICATION_JSON})
    public String encontrarUsuario(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("contraseña") String contraseña) throws JSONException {
        Cuenta UsuarioEsperado = find(nombreUsuario);
        JSONObject jsonObjeto = new JSONObject();

        if (UsuarioEsperado != null) {
            if (UsuarioEsperado.getContrasena().equals(contraseña)) {
                jsonObjeto.put("valor", true);
            }
        } else {
            jsonObjeto.put("valor", false);
        }
        return jsonObjeto.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
