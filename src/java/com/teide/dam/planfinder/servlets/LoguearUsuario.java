/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author dam2
 */
public class LoguearUsuario extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String sim = req.getParameter("sim");
         String latS = req.getParameter("lat");
         String logS = req.getParameter("log");
         PrintWriter out = resp.getWriter();         
         if(sim !=null && !sim.trim().isEmpty() || latS !=null && !latS.trim().isEmpty() || logS !=null && !logS.trim().isEmpty()){ 
         }
         else{
             try{
             double lat = Double.parseDouble("latS");
             double log = Double.parseDouble("logS");
             Session session = HibernateUtil.getSessionFactory().getCurrentSession();
             session.beginTransaction();
             UsuarioDAO uDAO = new UsuarioDAO(session);
             uDAO.actualizarPosicionUsuario("sim", lat, log);
             out.println("OK");
             }
             catch(Exception e){
                 out.println("NOK");
             }
             
             
         }
    }

  
}
