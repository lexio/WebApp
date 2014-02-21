/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.MensajeDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Mensaje;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class DevolverMensajesServlet extends HttpServlet {

    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
        String usuarioSim = req.getParameter("usuariosim");
        String idGrupo = req.getParameter("idgrupo");
        try {
            if (usuarioSim == null && usuarioSim.trim().isEmpty()|| idGrupo == null && idGrupo.trim().isEmpty()){
            
            }
            else{
            
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                PerteneceDAO pDAO = new PerteneceDAO(session);
                pDAO.comprobarEstadoUsuario(usuarioSim, idGrupo);
                if (pDAO== null){
                
                }
                else{
                    MensajeDAO mDAO = new MensajeDAO(session);
                
                    //UsuarioDAO uDAO = new UsuarioDAO(session);
                    //uDAO.comprobarUsuario(sim);
                    ArrayList<Mensaje> lista=mDAO.devolverMensajes(idGrupo);
                    for (int i = 0; i<lista.size();i++){
                        Mensaje mensajes = lista.get(i);
                        
                        out.println(mensajes.getUsuario().getNombre()+": "+mensajes.getMensaje()+"  --------->   "+mensajes.getFecha());
                        //mensajes.getUsuario()+": "+
                    }
                }
            }
        }
         catch (Exception e) {out.println(e.getMessage()); };
    }
}
