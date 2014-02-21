/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import com.teide.dam.planfinder.pojos.Pertenece;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;


/**
 *
 * @author Lexio
 */
public class ComprobarEstadoUsuario extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuarioSim = req.getParameter("sim");
        String idGrupo = req.getParameter("idgrupo");
        PrintWriter out = resp.getWriter();
               
        if (usuarioSim != null || !usuarioSim.trim().isEmpty()||idGrupo != null || idGrupo.trim().isEmpty()){
            try {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                PerteneceDAO pDAO = new PerteneceDAO(session);
                Pertenece p = pDAO.comprobarEstadoUsuario(usuarioSim, idGrupo);
                String estado = p.getEstado();
                out.println(estado);
            } catch (HibernateException e) {
                out.println("NOK");
            }
        }
        else out.println("NOK");
        
    }

    
}
