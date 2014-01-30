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
public class GenericDAO {
    private Session session;
    
    public GenericDAO(Session session){
        this.session = session;
    }

    Session getSession() {
        return session;
    }
    
}
