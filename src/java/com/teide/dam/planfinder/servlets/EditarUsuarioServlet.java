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
        if(sim == null && sim.trim().isEmpty() || estado == null && estado.trim().isEmpty() || radioRecepcionString == null && radioRecepcionString.trim().isEmpty()
                || nombre == null && nombre.trim().isEmpty()){
            
        }
        else{
            int radioRecepcion = Integer.parseInt(radioRecepcionString);
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            UsuarioDAO uDAO = new UsuarioDAO(session);
            uDAO.editarUsuario(sim, nombre, estado, radioRecepcion);
            tx.commit();
            
        }
    }
    
}
