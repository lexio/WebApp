/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.MensajeDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Mensaje;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class DevolverMensajesServlet extends HttpServlet {

    //A MEDIO ACABAR
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String usuariosim = req.getParameter("sim");
        String idGrupo = req.getParameter("idgrupo");
        
        if (usuariosim == null && usuariosim.trim().isEmpty()|| idGrupo == null && idGrupo.trim().isEmpty()){
            
        }
        else{
            
            //Busco primero en pertenece si el usuario está en ese grupo. La query de comprobarEstadoUsuario vale para esto XD
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PerteneceDAO pDAO = new PerteneceDAO(session);
            pDAO.comprobarEstadoUsuario(usuariosim, idGrupo);
            
            if (pDAO== null){
                
            }
            else{
                MensajeDAO mDAO = new MensajeDAO(session);
                //return mDAO.devolverMensajes(idGrupo);
            }
              
        }
        
    }  
}