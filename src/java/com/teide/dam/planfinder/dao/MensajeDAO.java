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
public class MensajeDAO extends GenericDAO{

    /* enviar mensajes(gcm), devolver mensajes
          
     
     */
    
    
    public MensajeDAO(Session session) {
        super(session);
    }
    
    
}
