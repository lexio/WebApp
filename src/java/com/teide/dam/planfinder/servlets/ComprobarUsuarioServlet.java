/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author dam2
 */
public class ComprobarUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sim = req.getParameter("sim");
        PrintWriter out = resp.getWriter();
        if (sim != null && !sim.trim().isEmpty()) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            UsuarioDAO uDAO = new UsuarioDAO(session);
            Usuario u = uDAO.comprobarUsuario(sim);
            if (u != null) {
                Set<Pertenece> grupos = u.getPerteneces();
                System.out.println("Num perteneces: "+grupos.size());
                session.evict(u);
                u.setGrupos(null);
                u.setMensajes(null);
               
                for (Pertenece pertenece : grupos) {
                    //session.evict(pertenece);
                    pertenece.setGrupo(null);
                    pertenece.setUsuario(null);
                    
                }
                
                Gson json = new Gson();
                String resultado = json.toJson(u);
                out.println(resultado);
            } else {
                out.println("NOK");
            }
        }
    }
}
