/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;


import org.hibernate.Session;

/**
 *
 * @author dam2
 */
public class PerteneceDAO extends GenericDAO{

    /*
     * enviar solicitud, aceptarla, o rechazarla.
     
     
     */
    
    public PerteneceDAO(Session session) {
        super(session);
    }
 
    
    
    public void aceptarSolicitud(String nombreG , String sim){
        
    }
    
    public void rechazarSolicitud(String nombreG, String sim){
        
    }
    
    
    
}
