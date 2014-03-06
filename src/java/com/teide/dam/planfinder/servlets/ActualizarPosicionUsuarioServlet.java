/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dam2
 */
public class ActualizarPosicionUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sim = req.getParameter("sim");
        String latitudString = req.getParameter("latitud");
        String longitudString = req.getParameter("longitud");
        PrintWriter out = resp.getWriter();
        if(sim != null && !sim.trim().isEmpty() || latitudString != null && !latitudString.trim().isEmpty() || longitudString != null && !longitudString.trim().isEmpty()){
            try {
                double latitud = Double.parseDouble(latitudString);
                double longitud = Double.parseDouble(longitudString);
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                UsuarioDAO uDAO = new UsuarioDAO(session);
                String respuesta = uDAO.actualizarPosicionUsuario(sim, latitud, longitud);
                tx.commit();
                out.println(respuesta);
            } catch (NumberFormatException | HibernateException e) {
                out.println("NOK");
            }
        } else out.println("NOK");
    }
    
}
