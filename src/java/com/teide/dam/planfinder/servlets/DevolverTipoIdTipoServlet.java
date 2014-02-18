/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.dao.TipoDAO;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jose
 */
public class DevolverTipoIdTipoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      PrintWriter out = resp.getWriter();
      String nombre = req.getParameter("nombre");
        if (nombre != null && nombre.trim().isEmpty()){ 
            try {
                
                    
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                TipoDAO tDAO = new TipoDAO(session);
                tDAO.BuscarIdTipo(nombre);
                Tipo t = tDAO.BuscarIdTipo(nombre);
                
                if ( t!= null) {  
                Gson json = new Gson();
                String resultado = json.toJson(t);
                out.println(resultado);
                out.println("OK");
                
                }
                else{ out.println("NOK");}
                
                
                
                
            } catch (HibernateException e) {
                
            }
        }
            
            
            
        }

}
