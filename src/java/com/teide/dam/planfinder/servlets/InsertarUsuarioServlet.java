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
public class InsertarUsuarioServlet extends HttpServlet {
    
    //QUEDA PENDIENTE EL VALOR QUE LE PASAMOS POR EL GCM
    //QUEDA PENDIENTE POR VER LO DE LA FECHA

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sim = req.getParameter("sim");
        String nombre = req.getParameter("nombre");
        String radioRecepcionString = req.getParameter("radioRecepcion");
        String latitudString = req.getParameter("latitud");
        String longitudString = req.getParameter("longitud");
        
        if(sim == null && sim.trim().isEmpty() || nombre == null && nombre.trim().isEmpty() || radioRecepcionString == null || radioRecepcionString.trim().isEmpty() 
                || latitudString == null && latitudString.trim().isEmpty() || longitudString == null && longitudString.trim().isEmpty()){
                                   
        }
        else{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            UsuarioDAO uDAO = new UsuarioDAO(session);
            int radioRecepcion = Integer.parseInt(radioRecepcionString);
            double latitud = Double.parseDouble(latitudString);
            double longitud = Double.parseDouble(longitudString);
            uDAO.insertarUsuario(sim, nombre, radioRecepcion, latitud, longitud, null, sim);
            tx.commit();
        }
    }
    
}