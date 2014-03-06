/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
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
        String radioRecepcionString = req.getParameter("radiorecepcion");
        PrintWriter out = resp.getWriter();
        if(sim != null && !sim.trim().isEmpty() && estado != null && !estado.trim().isEmpty() && radioRecepcionString != null && !radioRecepcionString.trim().isEmpty()
                && nombre != null && !nombre.trim().isEmpty()){
            try {
                nombre= new String(nombre.getBytes("iso-8859-1"),"UTF-8");
                int radioRecepcion = Integer.parseInt(radioRecepcionString);
                if(radioRecepcion<0){
                    out.println("NOK");
                }else{
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    session.beginTransaction();
                    Transaction tx = session.beginTransaction();
                    UsuarioDAO uDAO = new UsuarioDAO(session);
                    Usuario u = uDAO.comprobarUsuario(sim);
                    if(estado.equals(Estados.VISIBLE)||estado.equals(Estados.INVISIBLE)){
                        if (u!=null) {
                        uDAO.editarUsuario(sim, nombre, estado, radioRecepcion);
                        session.flush();
                        tx.commit();
                        out.println("OK");
                        }else out.println("NOK");
                    }else out.println("NOK");
                    
                }
            } catch (NumberFormatException | HibernateException e) {
                out.println("NOK");
            }
               
        }else out.println("NOK");
    }
    
}
