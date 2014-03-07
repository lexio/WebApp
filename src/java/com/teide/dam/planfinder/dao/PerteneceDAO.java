/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;


import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
import java.util.ArrayList;
import java.util.List;
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
 
     public Pertenece comprobarEstadoUsuario(String sim,String idgrupo){
    /*comprobar estado de usuario (baneado, solicitado, aceptado, noSolicitado)
     * si es baneado o solicitado, devuelve un false, si no es true
     * (pongo el return, para que no de error).
     */  
         try {
            int idGrupo = Integer.parseInt(idgrupo);
            Query q = getSession().createQuery(Queries.COMPROBAR_USUARIO_GRUPO);
            q.setParameter("sim", sim);
            q.setParameter("idgrupo", idGrupo);
            Pertenece p = (Pertenece) q.uniqueResult();
            return p ;
         } catch (NumberFormatException e) {
             return null ;
         }
        
    }
          
     public Pertenece devolverUsuario(String sim,String idgrupo){
         try {
            int idGrupo = Integer.parseInt(idgrupo);
            Query q = getSession().createQuery(Queries.COMPROBAR_USUARIO_GRUPO);
            q.setParameter("sim", sim);
            q.setParameter("idgrupo", idGrupo);
            Pertenece p = (Pertenece) q.uniqueResult();
            return p;
         } catch (NumberFormatException e) {
             return null;
         }
       
     }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    public String enviarSolicitudAUsuario(String usuarioSim,String idGrupo){
       /* se cambia el estado de ese usuario en ese grupo, a "solicitado"  */
        Pertenece p = comprobarEstadoUsuario(usuarioSim, idGrupo);
        if (p!=null && !p.getEstado().equals(Estados.BANEADO)) {
            p.setEstado(Estados.SOLICITADO);
            return "OK";
        }else return "NOK";
           
    }
            
    public String aceptarSolicitud(String usuarioSim, String idGrupo){
        /* se cambia el estado de ese usuario en ese grupo, a "aceptado"  */
        Pertenece p = comprobarEstadoUsuario(usuarioSim, idGrupo);
        if (p!=null && p.getEstado().equals(Estados.SOLICITADO)){
            p.setEstado(Estados.ACEPTADO);
            return "OK";
        }else return "NOK";
     }
    

    
    public String rechazarSolicitud(String usuarioSim, String idGrupo){
        /* se cambia el estado de ese usuario en ese grupo, a "baneado"  */
        Pertenece p = comprobarEstadoUsuario(usuarioSim, idGrupo);
        if (p!=null && p.getEstado().equals(Estados.SOLICITADO)){
            p.setEstado(Estados.BANEADO);
            return "OK";
        }else return "NOK";
        //getSession().persist();
    }
    
    public ArrayList<Pertenece> devolverPersonasGrupo (String idGrupo){
        try {
            int idgrupo = Integer.parseInt(idGrupo);
            Query q = getSession().createQuery(Queries.DEVOLVER_PERSONAS_GRUPO);
            q.setParameter("idgrupo", idgrupo);
            return (ArrayList<Pertenece>) q.list();
        } catch (NumberFormatException e) {
            return null;
        }
        
    }
    
    public Pertenece comprobarSolicitud(String sim, String idgrupo){
        try {
            int idGrupo = Integer.parseInt(idgrupo);
            Query q = getSession().createQuery(Queries.COMPROBAR_USUARIO_GRUPO);
            q.setParameter("sim", sim);
            q.setParameter("idgrupo", idGrupo);
            return (Pertenece) q.uniqueResult();
        } catch (NumberFormatException e) {
            return null;
        }
        
      
     }
}
