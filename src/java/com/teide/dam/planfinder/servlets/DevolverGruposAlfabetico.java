/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teide.dam.planfinder.servlets;
import com.google.gson.Gson;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class DevolverGruposAlfabetico extends HttpServlet {

  @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      PrintWriter out = resp.getWriter();
            try {
                
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                GrupoDAO gDAO = new GrupoDAO(session);
                gDAO.devolverGruposalfabetico();
                
                 ArrayList<Grupo> g = gDAO.devolverGruposalfabetico();
                 if ( g!= null) {
                Gson json = new Gson();
                String resultado = json.toJson(g);
                out.println(resultado);
                out.println("OK");
                 }
                 else{out.println("NOK");}
            } catch (HibernateException e) {
                
            }
        }
    
    
    

}
