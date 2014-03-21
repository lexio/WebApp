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
        if (idGrupo != null && !idGrupo.trim().isEmpty() && sim != null && !sim.trim().isEmpty()) {

            try {
                System.out.println("Acabo de iniciar el try");
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                GrupoDAO gDAO = new GrupoDAO(session);
                PerteneceDAO pDAO = new PerteneceDAO(session);

                Grupo g = gDAO.comprobarGrupoyEstado(idGrupo, Estados.HABILITADO);
                if (g != null) {
                    System.out.println("el grupo no es nul");
                    //Es propietario
                    if (sim.equals(g.getUsuario().getSim())) {
                        for (Pertenece p : g.getPerteneces()) {
                            p.setEstado(Estados.NOSOLICITADO);
                           // System.out.println("Entro para borrar el grupo entero");
                            //Enviar notificación GCM
                        }
                        g.setEstado(Estados.NOHABILITADO);
                        System.out.println("He hecho el no habilitado");
                        req.setAttribute("msgB", "Grupo eliminado");
                        req.setAttribute("idGrupoB", idGrupo);
                        req.setAttribute("simB", sim);
                        req.getServletContext().getRequestDispatcher("/sendallmessages").include(req, resp);                        
                    } else {
                        //Obtener el pertenece del usuario en ese grupo
                        //cambiar el estado a ESE pertenece
                        Pertenece p = pDAO.comprobarEstadoUsuario(sim, idGrupo);
                        p.setEstado(Estados.NOSOLICITADO);
                        //System.out.println("cambio estado usuario");
                    }
                    session.flush();
                    tx.commit();
                    out.println("OK");
                }else out.println("NOK");
            } catch (Exception e) {
                System.out.println("ERROR:"+e.getMessage());
                out.println("NOK");
            }
        }else out.println("NOK");

    }
}
