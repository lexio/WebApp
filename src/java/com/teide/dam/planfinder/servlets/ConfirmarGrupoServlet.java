/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Usuario;
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
public class ConfirmarGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String idgrupo = req.getParameter("idgrupo");
       String sim=req.getParameter("sim"); //sim del creador
       try {
       if (idgrupo != null || !idgrupo.trim().isEmpty() || sim != null || !sim.trim().isEmpty()){
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                GrupoDAO gDAO=new GrupoDAO(session);
                Grupo g= gDAO.comprobarGrupo(idgrupo);
                Grupo gr=gDAO.comprobarCreadorGrupo(sim,idgrupo);
                
                if (gr ==null) {
                    out.println("NOK");
                }
                else {
                if (g!=null){
                    Grupo grupos= gDAO.comprobarGrupo(idgrupo);
                    grupos.setEstado(Estados.HABILITADO);
                    session.persist(grupos);
                    session.flush();
                    tx.commit();
                    req.setAttribute("msg", "Has sido dado de alta en un nuevo grupo");
                    System.out.println("Antes de salir");
                    req.getServletContext().getRequestDispatcher("sendallmessagesservlet").include(req, resp);
                    System.out.println("Después de salir");
                    out.println("OK");
                }else {out.println("NOK");}
                }   
       }
       else{ out.println("NOK");}
       
       }catch (Exception e){out.println("Error: "+e.getMessage());}
    }
}