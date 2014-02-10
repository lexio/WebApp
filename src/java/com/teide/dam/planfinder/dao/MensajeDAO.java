/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Grupo;
import java.util.ArrayList;
import org.hibernate.Query;
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
    
      public ArrayList<Grupo> devolverMensajes (String idgrupo) {
        
        Query q = getSession().createQuery(Queries.BUSCAR_MENSAJES_GRUPO);
        q.setParameter("idgrupo", idgrupo);
        ArrayList<Grupo> listado = (ArrayList<Grupo>) q.list();
        return listado;
    }
    
}
