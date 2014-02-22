/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dam2
 */
public class UsuarioDAO extends GenericDAO{

   /*
    
    
    */
    
    public UsuarioDAO(Session session) {
        super(session);
    }
    
    public String insertarUsuario(String sim, String nombre, int radioRecepcion, double latitud, double longitud,String gcm ){
   
        GregorianCalendar gc = new GregorianCalendar();
        Date ultimaConexion = gc.getTime();
        Usuario u = new Usuario(sim, nombre, "disponible", radioRecepcion, latitud, longitud, ultimaConexion,gcm);
        try {
            getSession().persist(u);
            return "OK";
        } catch (Exception e) {
            return "NOK";
        }        
    }
    
    public Usuario comprobarUsuario(String sim){
        Query q = getSession().createQuery(Queries.BUSCAR_USUARIO_SIM);
        q.setParameter("sim", sim);
        return (Usuario)q.uniqueResult();
    }
    
    public ArrayList<Usuario> allUser(){
        Query q = getSession().createQuery(Queries.BUSCAR_TODOS_LOS_USUARIOS);
        ArrayList<Usuario> usu = (ArrayList<Usuario>)q.list();
        return usu;
    }
    
    public ArrayList<Usuario> buscarUsuariosEstado (String estado){
        Query q = getSession().createQuery(Queries.BUSCAR_ESTADO_USUARIO);
        q.setParameter("estado",estado);
        ArrayList<Usuario> usu = (ArrayList<Usuario>)q.list();
        return usu;
    }
    
    public String editarUsuario(String sim, String nombre, String estado, int radioRecepcion){
        Usuario u = comprobarUsuario(sim);
        if (u!=null) {
            u.setEstado(estado);
            u.setNombre(nombre);
            u.setRadioRecepcion(radioRecepcion);
            return "OK";
        }
        else return "NOK";        
    }
    
    public String actualizarPosicionUsuario(String sim, double latitud, double longitud){
        Usuario u = comprobarUsuario(sim);
        if (u!=null){
            u.setLatitud(latitud);
            u.setLongitud(longitud);
            return "OK";
        }
        else return "NOK";
    }  
      
}