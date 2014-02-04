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
public class GrupoDAO extends GenericDAO{
    
    /*
     Grupo--> Añadir/Crear, modificar, borrar/salir,buscar...
     El crear es altagrupo, las dos busquedas por nombre y tipo, en borrar hay que cambiar el estado=false
     Preguntar por el modificar
     */
    public GrupoDAO(Session session) {
        super(session);
    }
    
    public static void altaGrupo (Grupo g){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.persist(g);
        tx.commit();
    }
    
    public ArrayList<Grupo> buscarGrupoNombre (String nombre){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPO_NOMBRE);
        q.setParameter("nombre", nombre);
        ArrayList<Grupo> grupos = (ArrayList<Grupo>) q.list();
        return grupos;
    }
    
    public ArrayList<Grupo> buscarGrupoTipo (Tipo tipo){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPO_TIPO);
        q.setParameter("tipo", tipo);
        ArrayList<Grupo> grupos = (ArrayList<Grupo>) q.list();
        return grupos;
    }
    
    public Grupo eliminarGrupo (String nombre){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPO_NOMBRE);
        q.setParameter("nombre", nombre);
        return (Grupo) q.uniqueResult();
    }
    
    public void modificarGrupo(){
    
        
    }
    
}
