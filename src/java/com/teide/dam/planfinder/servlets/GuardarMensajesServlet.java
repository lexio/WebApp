/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.pojos.Mensaje;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dam2
 */
public class GuardarMensajesServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String mensaje = req.getParameter("mensaje");
       String grupo = req.getParameter("grupo");
       String usuariosim = req.getParameter("usuariosim");
       
       if (usuariosim == null && usuariosim.trim().isEmpty()|| grupo == null && grupo.trim().isEmpty() || mensaje == null && mensaje.trim().isEmpty()) {
           
       }
       else {
            Mensaje m = new Mensaje(grupo, usuario, mensaje, equipo, tipo, comentarios, 
                    new GregorianCalendar().getTime(), true);
            IncidenciaDAO.alta(i);
            out.println("Incidencia dada de alta");
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            Transaction tx = session.beginTransaction();
//            PerteneceDAO pDAO = new PerteneceDAO(session);
//            pDAO.comprobarEstadoUsuario(usuariosim, grupo);
       }
       //estado,fecha,idmensaje
        //tx.commit();
        
    }

    
}
