/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.bean.GrupoBean;
import com.teide.dam.planfinder.bean.UsuarioBean;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author dam2
 */
public class ComprobarUsuario extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sim = req.getParameter("sim");
        PrintWriter out = resp.getWriter();
        if (sim != null && !sim.trim().isEmpty()) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            UsuarioDAO uDAO = new UsuarioDAO(session);
            Usuario u = uDAO.comprobarUsuario(sim);
            if (u != null) {
                UsuarioBean ub = new UsuarioBean();
                Set<Pertenece> perteneces = u.getPerteneces();
                ub.setClaveGCM(u.getClaveGcm());
                ub.setEstado(u.getEstado());
                ub.setRadioRecepcion(u.getRadioRecepcion());
                ArrayList<GrupoBean> grupos = new ArrayList<>();
              
                for (Pertenece pertenece : perteneces) {
                    GrupoBean gb = new GrupoBean();
                    gb.setDescripcion(pertenece.getGrupo().getDescripcion());
                    gb.setNombreGrupo(pertenece.getGrupo().getNombre());
                    gb.setIdGrupo(pertenece.getGrupo().getIdGrupo());
                    grupos.add(gb);
                                    
                }
                ub.setGrupos(grupos);
                
                Gson json = new Gson();
                String resultado = json.toJson(ub);
                out.println(resultado);
            } else {
                out.println("NOK");
            }
        }
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        GrupoDAO gDAO = new GrupoDAO(session);
        
    }
}