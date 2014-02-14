/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.TipoDAO;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CambiarTipoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      PrintWriter out = resp.getWriter();
      String idTipo = req.getParameter("idTipo");
      String nombre = req.getParameter("nombre");
      String descripcion = req.getParameter("descripcion");
      if (idTipo != null && idTipo.trim().isEmpty() && nombre != null && nombre.trim().isEmpty()){ 
          try {
                
              int idTipo1 = Integer.parseInt(idTipo);
              Session session = HibernateUtil.getSessionFactory().getCurrentSession();
              Transaction tx = session.beginTransaction();
              TipoDAO tDAO = new TipoDAO(session);
              tDAO.CambiarTipo(idTipo1, nombre, descripcion);
                
          } catch (NumberFormatException e) {
                
          }
      }
        
        
        
    }

}