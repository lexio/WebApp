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
 
    public void enviarSolicitudAUsuario(String nombreCreador){
        /*
         
         */
        
         
    }
    
    
    public boolean comprobarEstado(String usuarioSim, Integer idGrupo){
    /*comprobar estado de usuario (baneado, solicitado, aceptado, noSolicitado)
     * si es baneado o solicitado, devuelve un false, si no es true
     * (pongo el return, para que no de error).
     */  
        return false;
    }
    
    public void aceptarSolicitud(String usuarioSim, Integer idGrupo){
        /* se cambia el estado de ese usuario en ese grupo, a "solicitado"
         
         */
    }
    
    public void rechazarSolicitud(String nombreG, String sim){
        
    }
    
    //
    
}
