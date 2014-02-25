/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Usuario;
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
 

public class DevolverSolicitudesPendientes extends HttpServlet {
     @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String usuarioSim = req.getParameter("sim");
            PrintWriter out = resp.getWriter();
            if (usuarioSim != null || !usuarioSim.trim().isEmpty()){
                try {
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    session.beginTransaction();
                    //PerteneceDAO pDAO = new PerteneceDAO(session);
                    UsuarioDAO uDAO = new UsuarioDAO(session);
                    GrupoDAO gDAO = new GrupoDAO(session);
                    Usuario u = uDAO.comprobarUsuario(usuarioSim);
                    Grupo g = gDAO.comprobarCreador(u);
                    String creador = g.getCreador();
                    if(!usuarioSim.equals(creador)){
                        Gson json = new Gson();
                        String nombreUsu = u.getNombre();
                        String nombreGru = g.getNombre();
                        String[] values =new String[2];
                        values[0]= nombreUsu;
                        values[1]= nombreGru;
                        String valores = json.toJson(values);
                        System.out.println(valores);
                    }
                } catch (HibernateException e) {
                    out.println("NOK");
                }
            }
    }
    
    
    
}