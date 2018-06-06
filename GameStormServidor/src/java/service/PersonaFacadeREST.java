/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
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
import modelo.Persona;

/**
 *
 * @author Irdevelo
 */
@Stateless
@Path("modelo.persona")
public class PersonaFacadeREST extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "GameStormServidorPU")
    private EntityManager em;

    public PersonaFacadeREST() {
        super(Persona.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Persona entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Persona entity) {
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
    public Persona find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Persona> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Persona> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("/ultimo")
    @Produces({MediaType.APPLICATION_JSON})
    public Persona obtenerID() {
        Persona persona;
        List<Persona> personas;
        personas = findAll();
        persona = personas.get(personas.size() - 1);
        return persona;
    }

    @GET
    @Path("/rol")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cuenta> obtenerEmpleados() {
        List<Cuenta> empleados;
        String empleado = "Empleado";
        String consulta = "SELECT c FROM  Cuenta c WHERE c.rol=:empleado";
        empleados = em.createQuery(consulta).setParameter("empleado", empleado).getResultList();
        
        return empleados;
    }

}
