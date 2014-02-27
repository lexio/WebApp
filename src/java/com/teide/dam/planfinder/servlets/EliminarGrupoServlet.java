/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
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

/**
 *
 * @author Mario
 */
public class EliminarGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idGrupo = req.getParameter("idgrupo");
        String sim = req.getParameter("sim");
        if (idGrupo != null || !idGrupo.trim().isEmpty() || sim != null || !sim.trim().isEmpty()) {
            try {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                GrupoDAO gDAO = new GrupoDAO(session);
                PerteneceDAO pDAO = new PerteneceDAO(session);
                ArrayList<Pertenece> personas = pDAO.devolverPersonasGrupo(idGrupo);
                if (personas.size() == 0) {
                    
                } else {
                    for (Pertenece p : personas) {
                        p.setEstado(Estados.BANEADO);
                    }
                    out.println("OK");
                }
                String resultado = gDAO.eliminarGrupo(idGrupo);
                if (resultado == "OK") {
                    tx.commit();
                    out.println("OK");
                }

            } catch (Exception e) {
                out.println("Error "+ e.getMessage());
            }


        }

    }
}
