/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.util.Estados;
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
 * @author dam2
 */
public class ComprobarSolicitud extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String estado = req.getParameter("estado");
        String idgrupo = req.getParameter("idgrupo");
        //String creador = req.getParameter("creador");
        String sim = req.getParameter("sim");


        if (sim != null && !sim.trim().isEmpty() || idgrupo != null && !idgrupo.trim().isEmpty() || estado != null && !estado.trim().isEmpty()) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();

            if ("true".equals(estado)) {
                PerteneceDAO pDAO = new PerteneceDAO(session);
                Pertenece respuesta = pDAO.comprobarSolicitud(sim, idgrupo);
                if (respuesta != null && respuesta.getEstado().equals(Estados.SOLICITADO)) {
                    respuesta.setEstado(Estados.ACEPTADO);
                    session.persist(respuesta);
                    tx.commit();
                    session.flush();
                    out.println("OK");
                } else {
                    out.println("NOK");
                }

            } else {
                PerteneceDAO pDAO = new PerteneceDAO(session);
                Pertenece respuesta = pDAO.comprobarSolicitud(sim, idgrupo);
                if (respuesta != null && respuesta.getEstado().equals(Estados.SOLICITADO)) {
                    respuesta.setEstado(Estados.BANEADO);
                    session.persist(respuesta);
                    tx.commit();
                    session.flush();
                    out.println("OK");
                } else {
                    out.println("NOK");
                }
            }

        }




    }
}
