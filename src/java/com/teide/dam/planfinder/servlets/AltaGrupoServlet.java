/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.dao.TipoDAO;
import com.teide.dam.planfinder.dao.UbicacionDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Mensaje;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.pojos.Ubicacion;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class AltaGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        //String ubicacion = req.getParameter("ubicacion");
        String latitud = req.getParameter("latitud");
        String longitud = req.getParameter("longitud");
        String descripcionUbicacion = req.getParameter("descripcionubicacion");
        
        String usuario = req.getParameter("usuario");
        String tipoString = req.getParameter("tipo");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String fechaCreacion = req.getParameter("fechacreacion"); //no es necesario recibirlo, se coge del servidor
        String fechaFinalizacion = req.getParameter("fechafinalizacion");//lo mismo que arriba
        String fechaInicioActividad = req.getParameter("fechainicioactividad");
        String fechaFinActividad = req.getParameter("fechafinactividad");
        String radioEmisionString = req.getParameter("radioemision");
        //System.out.println("Nombre1: "+nombre);
        if(usuario == null || usuario.trim().isEmpty() ||
                tipoString == null || tipoString.trim().isEmpty() ||
                nombre == null || nombre.trim().isEmpty() ||
                radioEmisionString == null || radioEmisionString.trim().isEmpty() ){
            
            out.println("NOK");
            

        }
        else{
            try{
                nombre= new String(nombre.getBytes("iso-8859-1"),"UTF-8");
                //System.out.println("Nombre2: "+nombre);           
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                
                //RECIBIMOS LA SESION Y ALMACENAMOS UN USUARIO QUE SERÁ EL CREADOR

                UsuarioDAO uDAO = new UsuarioDAO(session);
                Usuario usu = uDAO.comprobarUsuario(usuario);

                //RECIBIMOS LOS DATOS DEL TIPO

                TipoDAO tDAO = new TipoDAO(session);
                System.out.println(tipoString);
                int idTipo= tDAO.buscaridtipo(tipoString).getIdTipo();
                System.out.println(idTipo);
                Tipo tipo = tDAO.buscarNombreTipo(idTipo);

                int radioEmision = Integer.parseInt(radioEmisionString);

                Grupo grupo = new Grupo(usu, tipo, nombre, new GregorianCalendar().getTime(), Estados.NOHABILITADO, radioEmision);

                if (latitud==null || latitud.trim().isEmpty() || latitud==""){}
                else {
                    UbicacionDAO ubiDAO = new UbicacionDAO(session);
                    double lat = Double.parseDouble(latitud);
                    double lon = Double.parseDouble(longitud);

                    Ubicacion ubicacion = ubiDAO.altaUbicacion(idTipo, descripcionUbicacion, lat, lon);
                    grupo.setUbicacion(ubicacion);
                }

                if (descripcion==null || descripcion.trim().isEmpty()){}
                else {
                    descripcion= new String(descripcion.getBytes("iso-8859-1"),"UTF-8");
                    grupo.setDescripcion(descripcion);
                }

                if (fechaFinalizacion==null || fechaFinalizacion.trim().isEmpty()){}
                else {
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                    Date fFinalizacion=null;
                    try {
                        fFinalizacion = formatoDelTexto.parse(fechaFinalizacion);
                    }  catch (Exception e) {
                        e.getMessage();
                    }
                    grupo.setFechaFinalizacion(fFinalizacion);
                }

                if (fechaInicioActividad==null || fechaInicioActividad.trim().isEmpty()){}
                else {
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                    Date fInicioActividad=null;
                    try {
                        fInicioActividad = formatoDelTexto.parse(fechaInicioActividad);
                    }  catch (Exception e) {
                        e.getMessage();
                    }
                    grupo.setFechaInicioActividad(fInicioActividad);
                }

                if (fechaFinActividad==null || fechaFinActividad.trim().isEmpty()){}
                else {
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                    Date fFinActividad=null;
                    try {
                        fFinActividad = formatoDelTexto.parse(fechaFinActividad);
                    }  catch (Exception e) {
                        e.getMessage();
                    }
                    grupo.setFechaFinActividad(fFinActividad);
                }
                GrupoDAO gdao= new GrupoDAO(session);
                gdao.altaGrupo(grupo);
                //tx.commit();
                int componentesGrupo = uDAO.insertarUsuarioGrupo(grupo.getIdGrupo(), usuario, radioEmision);
                session.flush();
                out.println(componentesGrupo+"/"+grupo.getIdGrupo());
            }
            
            catch(Exception e){
                out.println("NOK "+e.getMessage());
                e.printStackTrace();
            }
            
        }
    }

}
