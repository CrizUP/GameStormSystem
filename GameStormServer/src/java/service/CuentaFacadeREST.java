/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Cuenta;

/**
 * Descripcion
 * @author Cristhian Ubaldo Promotor
 * @version fecha 
 */
@javax.ejb.Stateless
@javax.ws.rs.Path("modelo.cuenta")
public class CuentaFacadeREST extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "GameStormServerPU")
    private EntityManager em;

    public CuentaFacadeREST() {
        super(Cuenta.class);
    }

    @javax.ws.rs.POST
    @Override
    @javax.ws.rs.Consumes({ javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public void create(Cuenta entity) {
        super.create(entity);
    }

    @javax.ws.rs.PUT
    @javax.ws.rs.Path("{id}")
    @javax.ws.rs.Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public void edit(@javax.ws.rs.PathParam("id") String id, Cuenta entity) {
        super.edit(entity);
    }

    @javax.ws.rs.DELETE
    @javax.ws.rs.Path("{id}")
    public void remove(@javax.ws.rs.PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("{id}")
    @javax.ws.rs.Produces({ javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public Cuenta find(@javax.ws.rs.PathParam("id") String id) {
        return super.find(id);
    }

    @javax.ws.rs.GET
    @Override
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Cuenta> findAll() {
        return super.findAll();
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("{from}/{to}")
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Cuenta> findRange(@javax.ws.rs.PathParam("from") Integer from, @javax.ws.rs.PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("count")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
