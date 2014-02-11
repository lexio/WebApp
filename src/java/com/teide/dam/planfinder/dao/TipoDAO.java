/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.util.ArrayList;
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
    
    public Tipo BuscarNombreTipo(int idTipo){
        Query q = getSession().createQuery(Queries.BUSCAR_TIPO_NOMBRE);
        q.setParameter("idTipo", idTipo);
        return (Tipo)q.uniqueResult();       
    }
    
    public ArrayList<Grupo> BuscarGruposTipo(int idTipo){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPOS_USUARIO);
        q.setParameter("idTipo", idTipo);
        return (ArrayList<Grupo>) q.list();
        
        
        
        
    }
    
}
