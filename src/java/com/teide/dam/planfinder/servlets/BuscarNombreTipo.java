/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.dao.TipoDAO;
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

/**
 *
 * @author dam2
 */
public class BuscarNombreTipo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        String idtipoString = req.getParameter("idtipo");
        if (idtipoString == null || idtipoString.trim().isEmpty()) {
        
            out.println("NOK");
        
        }
        else{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            try {
                
                TipoDAO tdao = new TipoDAO(session);
                int idtipo = Integer.parseInt(idtipoString);
                ArrayList<Tipo> t = tdao.BuscarNombreTipo(idtipo);
                session.evict(t);
                for (Tipo tipo : t) {
                    tipo.setGrupos(null);
                    
                }
                    Gson json = new Gson();
                    String resultado = json.toJson(t);
                    out.println(resultado);
                
            } catch (Exception e) {
                out.println("NOK");
            }
            finally{
                 session.flush();
            }

        } 

    }
}
