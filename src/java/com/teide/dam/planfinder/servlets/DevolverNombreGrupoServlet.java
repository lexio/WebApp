/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.dao.TipoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Tipo;
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


public class DevolverNombreGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      PrintWriter out = resp.getWriter();
      String nombre = req.getParameter("nombre");
        if (nombre != null && nombre.trim().isEmpty()){ 
            try {
                
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                TipoDAO tDAO = new TipoDAO(session);
                tDAO.BuscarGruposTipoPorNombre(nombre);
                
                
                ArrayList<Grupo> t = tDAO.BuscarGruposTipoPorNombre(nombre);
                Gson json = new Gson();
                String resultado = json.toJson(t);
                out.println(resultado);
                
            } catch (NumberFormatException e) {
                
            }
        }
            
            
            
        }
        
        
    }