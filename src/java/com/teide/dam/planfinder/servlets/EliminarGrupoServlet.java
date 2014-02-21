/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
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
public class EliminarGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String sim = req.getParameter("sim");
        if (sim ==null || sim.trim().isEmpty()){
            
        }
        else{
            UsuarioDAO uDAO = new UsuarioDAO(session);
            GrupoDAO gDAO = new GrupoDAO(session);
            Grupo g = new Grupo();
            if (uDAO.comprobarUsuario(sim) == gDAO.comprobarCreador(sim)){
                gDAO.eliminarGrupo(sim);
            }
        }
        
    }

}