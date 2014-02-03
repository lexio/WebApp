/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dam2
 */
public class TipoDAO extends GenericDAO{

    public TipoDAO(Session session) {
        super(session);
    }
    public static void altaTipo (Tipo g){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.persist(g);
        tx.commit();
    }
    
    public Tipo BuscarNombreTipo(String nombre){
        Query q = getSession().createQuery(Queries.BUSCAR_TIPO_NOMBRE);
        q.setParameter("nombre", nombre);
        return (Tipo)q.uniqueResult();       
    }
    
}
