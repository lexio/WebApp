/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.dao;

import com.teide.dam.planfinder.bbdd.Queries;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.PerteneceId;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
      


  public int insertarUsuarioGrupo(int idGrupo, String sim){
        Usuario u = comprobarUsuario(sim);
        Double latC = u.getLatitud();
        Double longC = u.getLongitud();
        int radioRecepcionC = u.getRadioRecepcion();
        int usuarios = 0;
        
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        
        ArrayList<Usuario> usu = buscarUsuariosEstado(Estados.VISIBLE);
        
        GrupoDAO gDAO = new GrupoDAO(sesion);
        String idGrupoS = Integer.toString(idGrupo);
        Grupo g = gDAO.comprobarGrupo(idGrupoS);
        
             for (Usuario usuario : usu) {
                sesion.evict(usuario);
                double latU = usuario.getLatitud();
                double longU = usuario.getLongitud();
                int radioRecepcionU = usuario.getRadioRecepcion();
                String simU = usuario.getSim();
                
                System.out.println("Usuario: "+simU);
                
                double dlong=(longC-longU);
                
                double degtorad=0.01745329;
                double radtodeg = 57.29577951;
                
                double dvalue = (Math.sin(latC * degtorad) * Math.sin(latU * degtorad))
                        + (Math.cos(latC * degtorad) * Math.cos(latU * degtorad)
                        * Math.cos(dlong * degtorad));
                double dd = Math.acos(dvalue)*radtodeg;
                double km = (dd*111.302);
                km = km*1000;
                if (km <= radioRecepcionC){
                    System.out.println("El usuario está en el radio del creador");
                    dlong=(longU-longC);
                    System.out.println("DLONG: "+dlong);
                    dvalue = (Math.sin(latU * degtorad) * Math.sin(latC * degtorad))
                            + (Math.cos(latU * degtorad) * Math.cos(latC * degtorad)
                            * Math.cos(dlong * degtorad));
                    dd = Math.acos(dvalue)*radtodeg;
                    km = (dd*111.302);
                    System.out.println("KM: "+km);
                    km = km*1000;
                    System.out.println("M: "+km);
                    if (km <= radioRecepcionU){
                        try {
                        System.out.println("El creador esta en el radio del usuario");
                            PerteneceId pId = new PerteneceId(simU, g.getIdGrupo());
                            Pertenece p = new Pertenece(pId, g, usuario, Estados.ACEPTADO);
                        sesion.persist(p);
                        usuarios++;
                            System.out.println("***************************Añadido :"+usuario.getNombre());
                        } catch (Exception e) {
                            System.out.println("NOK");
                        }
                    }
                    else {
                        System.out.println("Eliminando usuario: "+usuario.getNombre());
                    }
                }
                else {
                    System.out.println("Eliminado usuario abajo: "+usuario.getNombre());
                }
            }
            tx.commit();
            return usuarios;
        }
    }