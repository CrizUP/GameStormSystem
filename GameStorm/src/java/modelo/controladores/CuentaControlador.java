/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.controladores;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Cuenta;

/**
 * Descripcion
 * @author Cristhian Ubaldo Promotor
 * @version fecha 
 */
public class CuentaControlador {

    private static final String UNIDAD_PERSISTENCIA = "GameStormPU";
    private EntityManagerFactory emf;

    public CuentaControlador() {
        emf = Persistence.createEntityManagerFactory(UNIDAD_PERSISTENCIA);
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public List<Cuenta> buscarEmpleados(){
        EntityManager em = null;
        List<Cuenta> empleados = null;
        try{
            //@SuppressWarnings("null")
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Cuenta.findByRol");
            consulta.setParameter("rol", "Empleado");
            empleados = consulta.getResultList();
            
        }catch(Exception e){
            e.printStackTrace();            
        }finally{
            if (em != null){
                em.close();
            }
        }
        return empleados;        
    }
    
    
    
}
