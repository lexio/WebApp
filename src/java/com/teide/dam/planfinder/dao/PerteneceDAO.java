/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;


import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Pertenece;
import java.util.Set;
import org.hibernate.Query;
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
 
     public Pertenece comprobarEstadoUsuario(String usuarioSim,String idGrupo){
    /*comprobar estado de usuario (baneado, solicitado, aceptado, noSolicitado)
     * si es baneado o solicitado, devuelve un false, si no es true
     * (pongo el return, para que no de error).
     */  
        Query q = getSession().createQuery(Queries.COMPROBAR_USUARIO_GRUPO);
        q.setParameter("usuario_sim", usuarioSim);
        q.setParameter("id_grupo", idGrupo);
        Pertenece p = (Pertenece) q.list();
        return p ;
    }
          
    public void enviarSolicitudAUsuario(String usuarioSim,String idGrupo,String estado){
       /* se cambia el estado de ese usuario en ese grupo, a "solicitado"  */
        Query q = getSession().createQuery(Queries.CAMBIAR_ESTADO_USUARIO_GRUPO);
        q.setParameter("usuario_sim", usuarioSim);
        q.setParameter("id_grupo", idGrupo);
        q.setParameter("estado",estado);
        //q.setParameter("nombre_creador",nombreCreador);
        //getSession().persist();         
    }
            
    public void aceptarSolicitud(String usuarioSim, String idGrupo,String estado){
        /* se cambia el estado de ese usuario en ese grupo, a "aceptado"  */
        Query q = getSession().createQuery(Queries.CAMBIAR_ESTADO_USUARIO_GRUPO);
        q.setParameter("usuario_sim", usuarioSim);
        q.setParameter("id_grupo", idGrupo);
        q.setParameter("estado",estado);
        //getSession().persist();
            }
    
//    public void rechazarSolicitudEstado(String usuarioSim, String idGrupo){
//         /* se cambia el estado de ese usuario en ese grupo, a "baneado"  */
//        Query q = getSession().createQuery(Queries.CAMBIAR_ESTADO_USUARIO_GRUPO);
//        q.setParameter("usuario_sim", usuarioSim);
//        q.setParameter("id_grupo", idGrupo);
//        //getSession().persist();
//    }
    
    public void rechazarSolicitud(String usuarioSim, String idGrupo,String estado){
        /* se cambia el estado de ese usuario en ese grupo, a "baneado"  */
        Query q = getSession().createQuery(Queries.CAMBIAR_ESTADO_USUARIO_GRUPO);
        q.setParameter("usuario_sim", usuarioSim);
        q.setParameter("id_grupo", idGrupo);
        q.setParameter("estado",estado);
        //getSession().persist();
    }
}
