/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Ubicacion;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dam2
 */
public class UbicacionDAO extends GenericDAO {

    /*
     //Marcamos ubicación al crear el grupo
     * //
     //modificamos ubicación cuando el creador quiera.
     
     */
    
    public UbicacionDAO(Session session) {
        super(session);
    }
    
    public boolean AltaUbicacion(int idubicacion, String descripcion, String latitud, String longitud){
    
        Ubicacion ub = new Ubicacion(descripcion, latitud, longitud, null);
        try {
            getSession().persist(ub);
            return true;
        } catch (Exception e) {
            return false;
        }  
        
        
}
    //Comprobamos que la idubicacion existe.
    public Ubicacion comprobarUbicacion(int idubicacion){
        Query q = getSession().createQuery(Queries.BUSCAR_UBICACION_ID);
        q.setParameter("idubicacion", q);
        return (Ubicacion)q.uniqueResult();
    }
    //Cogemos la idubicación y, si existe (es decir, está vinculada a un grupo), reescribimos. 
    public boolean EditarUbicacion(int idubicacion, String latitud, String longitud){
        Ubicacion ub = comprobarUbicacion(idubicacion);
        if (ub!=null){
            ub.setLatitud(latitud);
            ub.setLongitud(longitud);
            return true;
        }
        else return false;
    }
    
}
