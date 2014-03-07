/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
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
     Grupo--> Añadir, Comprobar, modificar, borrar/salir grupo, buscarGruposTodos...
     El crear es altagrupo, las dos busquedas por nombre y tipo, en borrar hay que cambiar el estado=false
     Preguntar por el modificar
     */
    public GrupoDAO(Session session) {
        super(session);
    }
    
    /* comprobar que lo que devuelven los metodos es lo correcto, tendremos que devolver "Json" a los moviles
     * esta mario con ello.
    */
    public void altaGrupo (Grupo g){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.persist(g);
        
    }
    
    public ArrayList<Grupo> buscarGrupoNombre (String nombre){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPO_NOMBRE);
        q.setParameter("nombre", "%"+nombre+"%");
        ArrayList<Grupo> grupos = (ArrayList<Grupo>) q.list();
        return grupos;
    }
    
    public ArrayList<Grupo> buscarGrupoTipo (Tipo tipo){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPO_TIPO);
        q.setParameter("tipo", tipo);
        ArrayList<Grupo> grupos = (ArrayList<Grupo>) q.list();
        return grupos;
    }
    
    public String eliminarGrupo (String idGrupo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Grupo g = comprobarGrupo(idGrupo);
        if (g.getEstado().equals(Estados.HABILITADO)) {
            g.setEstado(Estados.NOHABILITADO);
            session.persist(g);
            return "OK";
        }
        else{
            return "NOK";
        }
    }
        /*buscar grupo de usuario. + sql.
         * 
         * 
         */
    public ArrayList<Grupo> buscarGruposUsuario (String usuario_sim){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPOS_USUARIO);
        q.setParameter("sim", usuario_sim);
        return (ArrayList<Grupo>) q.list();
    }
    
        
    public Grupo comprobarGrupo(String idGrupo){
        try {
            int idgrupo = Integer.parseInt(idGrupo);
            Query q = getSession().createQuery(Queries.BUSCAR_GRUPO_POR_ID);
            q.setParameter("idgrupo", idgrupo);
            return (Grupo)q.uniqueResult();
        } catch (NumberFormatException e) {
            return null;
        }
         
    }
    
    public Grupo comprobarGrupoyEstado(String idGrupo, String estado){
        try {
            int idgrupo = Integer.parseInt(idGrupo);
            Query q = getSession().createQuery(Queries.BUSCAR_GRUPOS);
            q.setParameter("idgrupo", idgrupo);
            q.setParameter("estado", estado);
            return (Grupo)q.uniqueResult();
        } catch (NumberFormatException e) {
            return null;
        }
        
    }
    public ArrayList<Grupo> devolverNombresGrupo (int tipo){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPO_POR_ID);
        q.setParameter("tipo", tipo);
        ArrayList<Grupo> gruposTipo = (ArrayList<Grupo>)q.list();
        return gruposTipo;
    }
    
    public Usuario comprobarCreador (String usuario){
        Query q = getSession().createQuery(Queries.COGER_CREADOR);
        q.setParameter("usuario", usuario);
        return (Usuario)q.uniqueResult();
    }
    
    public Grupo comprobarCreadorGrupo (String usuario, String idgrupo){
        try {
             int idGrupo = Integer.parseInt(idgrupo);
             Query q = getSession().createQuery(Queries.COMPROBAR_CREADOR_GRUPO);
             q.setParameter("usuario", usuario);
             q.setParameter("idgrupo", idGrupo);
             return (Grupo)q.uniqueResult();
        } catch (NumberFormatException e) {
            return null;
        }
        
    }
    
    
    public ArrayList<Grupo> devolverGruposCreador(String sim){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPOS_CREADOR);
        q.setParameter("usuariosim", sim);
        q.setParameter("estado", Estados.HABILITADO);
        ArrayList<Grupo> grupoCreador = (ArrayList<Grupo>) q.list();       
        return grupoCreador;
    }
    
    
    public String recogerSim (String usuario_Sim){
        Query q = getSession().createQuery(Queries.COGER_SIM);
        q.setParameter("sim", usuario_Sim);
        if(q.uniqueResult()!=null) return "OK";
        else return "NOK";
    }
    
    
    public ArrayList<Grupo> devolverGruposalfabetico(){
        Query q = getSession().createQuery(Queries.BUSCAR_GRUPOS_ALFABETICO);
        ArrayList<Grupo> gruposAlfab = (ArrayList<Grupo>)q.list();
        return gruposAlfab;
        
    }


}
