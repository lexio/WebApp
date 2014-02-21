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
public class EditarUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sim = req.getParameter("sim");
        String nombre = req.getParameter("nombre");
        String estado = req.getParameter("estado");
        String radioRecepcionString = req.getParameter("radioRecepcion");
        PrintWriter out = resp.getWriter();
        
        System.out.println("Entro en el Service");
        System.out.println(sim);
        System.out.println(nombre);
        System.out.println(radioRecepcionString);
        System.out.println(estado);
                        
        if(sim != null && !sim.trim().isEmpty() || estado != null && !estado.trim().isEmpty() || radioRecepcionString != null && !radioRecepcionString.trim().isEmpty()
                || nombre != null && !nombre.trim().isEmpty()){
            try {
                System.out.println("Entro en el if");
                int radioRecepcion = Integer.parseInt(radioRecepcionString);
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                UsuarioDAO uDAO = new UsuarioDAO(session);
                uDAO.editarUsuario(sim, nombre, estado, radioRecepcion);
            } catch (HibernateException e) {
                out.println("NOK");
            }
               
        }
    }
    
}
