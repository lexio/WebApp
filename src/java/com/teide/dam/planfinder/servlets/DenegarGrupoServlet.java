/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Usuario;
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
public class DenegarGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idGrupo = req.getParameter("idGrupo");
        int idGrup = Integer.parseInt(idGrupo);
        Usuario usuario = new Usuario();
        if(idGrupo == null || idGrupo.trim().isEmpty() || 
                usuario == null || usuario.trim().isEmpty()){
            
        }
        else{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            GrupoDAO gDAO = new GrupoDAO(session);
            gDAO.denegarGrupo(idGrup, usuario);
        }
    }

}
