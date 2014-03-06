/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.bean.GruposSolicitadosBean;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Pertenece;
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
 

public class DevolverSolicitudesPendientes extends HttpServlet {
     @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String usuarioSim = req.getParameter("sim");
            PrintWriter out = resp.getWriter();
            Gson json = new Gson();
            if (usuarioSim != null || !usuarioSim.trim().isEmpty()){
               
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                GrupoDAO gDAO = new GrupoDAO(session);
                ArrayList<GruposSolicitadosBean> listado = new ArrayList<>();
                ArrayList<Grupo> grupos = gDAO.devolverGruposCreador(usuarioSim);
                for (Grupo grupo : grupos) {
                    for (Pertenece pertenece : grupo.getPerteneces()) {
                        if (pertenece.getEstado().equals(Estados.SOLICITADO)) {
                            GruposSolicitadosBean gsb = new GruposSolicitadosBean();
                            gsb.setIdGrupo(pertenece.getId().getGrupoIdGrupo());
                            gsb.setIdUsuario(pertenece.getId().getUsuarioSim());
                            gsb.setNombreGrupo(grupo.getNombre());
                            gsb.setNombreUsuario(pertenece.getUsuario().getNombre());
                            listado.add(gsb);
                        }
                    }
                }
                
                out.println(json.toJson(listado));   
            }else out.println("NOK");
    }
    
    
    
}
