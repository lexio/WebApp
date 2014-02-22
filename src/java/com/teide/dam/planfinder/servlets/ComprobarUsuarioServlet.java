/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
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
                Set<Pertenece> perteneces = u.getPerteneces();
                System.out.println("Num perteneces: " + perteneces.size());
                session.evict(u);
                u.setGrupos(null);
                u.setMensajes(null);

                for (Pertenece pertenece : perteneces) {
                    pertenece.setUsuario(null);
                    Grupo grupo = pertenece.getGrupo();
                    //session.evict(grupo);
                    grupo.setUbicacion(null);
                    System.out.println("Nombre: "+grupo.getNombre());
                    grupo.setTipo(null);
                    grupo.setMensajes(null);
                    grupo.setUsuario(null);
                    grupo.setPerteneces(null);
                }

                Gson json = new Gson();
                String resultado = json.toJson(u);
                out.println(resultado);
            } else {
                out.println("NOK");
            }
        }
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        GrupoDAO gDAO = new GrupoDAO(session);
        
    }
}