/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.dao.TipoDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Mensaje;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.pojos.Ubicacion;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class AltaGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String ubicacion = req.getParameter("ubicacion");
        String usuario = req.getParameter("usuario");
        String tipoo = req.getParameter("tipo");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String fechaCreacion = req.getParameter("fechaCreacion");
        String fechaFinalizacion = req.getParameter("fechaFinalizacion");
        String fechaInicioActividad = req.getParameter("fechaInicioActividad");
        String fechaFinActividad = req.getParameter("fechaFinActividad");
        String radioEmisio = req.getParameter("radioEmision");
        
        if(usuario == null || usuario.trim().isEmpty() || 
                tipoo == null || tipoo.trim().isEmpty() || 
                nombre == null || nombre.trim().isEmpty() || 
                fechaCreacion == null || fechaCreacion.trim().isEmpty()||
                radioEmisio == null || radioEmisio.trim().isEmpty() ){
            
        }
        else{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            
            Ubicacion u = new Ubicacion(ubicacion);
            
            UsuarioDAO uDAO = new UsuarioDAO(session);
            Usuario usu = uDAO.comprobarUsuario(usuario);
            
            Set<Pertenece> p = usu.getPerteneces();
            //PerteneceDAO pDAO = new PerteneceDAO(session);
            Set<Mensaje> m = usu.getMensajes();
            
            TipoDAO tDAO = new TipoDAO(session);
            Tipo tipo=tDAO.BuscarNombreTipo();
            
            
            GregorianCalendar fecha =new GregorianCalendar();
            String fecha1 []=fechaFinalizacion.split("/");
            fecha.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(fecha1[0]));
            fecha.set(GregorianCalendar.MONTH, Integer.parseInt(fecha1[1]));
            fecha.set(GregorianCalendar.YEAR, Integer.parseInt(fecha1[2])); 
             
            DateFormat fIA = new SimpleDateFormat(fechaInicioActividad);
            DateFormat fFA = new SimpleDateFormat(fechaFinActividad);
            int radioEmision=Integer.parseInt(radioEmisio);
            Grupo g = new Grupo(ubicacion, usu, tipo, nombre, descripcion, new GregorianCalendar().getTime(), fecha1, fIA, fFA, "Deshabilitado", radioEmision, p, m);
            GrupoDAO.altaGrupo(g);
            out.println("grupo añadido");
            
        }
    }

}
