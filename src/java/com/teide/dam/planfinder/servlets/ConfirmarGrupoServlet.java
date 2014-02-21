/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
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
 * @author Mario
 */
public class ConfirmarGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idGrupo = req.getParameter("idgrupo");
        String personas = req.getParameter("personas");
        if(idGrupo == null || idGrupo.trim().isEmpty() || 
                personas == null || personas.trim().isEmpty()){
            out.println("NOK");
        }
        else{
            int idGrup = Integer.parseInt(idGrupo);
            int numPersonas =Integer.parseInt(personas);
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            GrupoDAO gDAO = new GrupoDAO(session);
            gDAO.confirmarGrupo(idGrup, numPersonas);
            out.println("OK");
        }
    }

}
