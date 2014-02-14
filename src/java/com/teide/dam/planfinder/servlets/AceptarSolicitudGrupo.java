/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.PerteneceDAO;
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
 * @author Lexio
 */
public class AceptarSolicitudGrupo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //out.println("Entrooo en el servlet!!");
        String usuarioSim = req.getParameter("usuarioSim");
        String idGrupo = req.getParameter("idGrupo");
        
        if (usuarioSim != null || !usuarioSim.trim().isEmpty()||idGrupo != null || !idGrupo.trim().isEmpty()){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PerteneceDAO pDAO = new PerteneceDAO(session);
            pDAO.aceptarSolicitud(usuarioSim, idGrupo);
            tx.commit();
            out.println("OK");        
        }else out.println("NOK"); 
    
        
    }
}
