/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Mensaje;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dam2
 */
public class MensajeDAO extends GenericDAO {

    /* enviar mensajes(gcm), devolver mensajes
          
     
     */
    public MensajeDAO(Session session) {
        super(session);
    }

    public static void alta(Mensaje m) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();

    }

    public ArrayList<Mensaje> devolverMensajes(String idGrupo) {
        try {
            int idgrupo = Integer.parseInt(idGrupo);
            Query q = getSession().createQuery(Queries.BUSCAR_MENSAJES_GRUPO);
            q.setParameter("idGrupo", idgrupo);
            return (ArrayList<Mensaje>) q.list();
        } catch (NumberFormatException e) {
            return null;
        }


    }
}
