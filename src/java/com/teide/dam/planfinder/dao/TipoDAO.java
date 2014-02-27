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
    
    public ArrayList<Tipo> BuscarNombreTipo(int idtipo){
        Query q = getSession().createQuery(Queries.BUSCAR_TIPO_NOMBRE);
        q.setParameter("idtipo", idtipo);
        ArrayList<Tipo> tipos = (ArrayList<Tipo>)q.list();
        return tipos;
    }
    
    public Tipo ComprobarTipo(int idtipo, String nombre){
        Query q = getSession().createQuery(Queries.BUSCAR_TIPO_Y_GRUPO);
        q.setParameter("idtipo", idtipo);
        q.setParameter("nombre", nombre);
        return (Tipo)q.uniqueResult();
               
    }
      
    
    public Tipo buscaridtipo(String nombre){
       Query q = getSession().createQuery(Queries.BUSCAR_ID_TIPO_POR_NOMBRE);
       q.setParameter("nombre", nombre);
       return (Tipo)q.uniqueResult();
    }
    
    public ArrayList<Tipo> buscartodotipos (){
        Query q = getSession().createQuery(Queries.BUSCAR_TIPO_TODO);
        ArrayList<Tipo> tipos = (ArrayList<Tipo>)q.list();
        return tipos;
    }
    
   public Tipo buscarNombreTipo(int idtipo){
       Query q = getSession().createQuery(Queries.BUSCAR_NOMBRE_POR_ID);
       q.setParameter("idtipo", idtipo);
       return (Tipo)q.uniqueResult();
   }


}
