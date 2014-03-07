/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.bean.InfoBean;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author dam2
 */
public class BuscarInformacionGrupo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idGrupo = req.getParameter("idgrupo");
        PrintWriter out = resp.getWriter();
        if (idGrupo != null && !idGrupo.trim().isEmpty()) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            GrupoDAO gDAO = new GrupoDAO(session);
            Grupo g = gDAO.comprobarGrupo(idGrupo);
            if (g != null) {
                String nombreTipo = g.getTipo().getNombre();
                String creador = g.getUsuario().getNombre();               
                Gson json = new Gson();
                try {
                    Double lat = g.getUbicacion().getLatitud();
                    Double lon = g.getUbicacion().getLongitud();
                    InfoBean ib = new InfoBean(g.getNombre(), idGrupo, nombreTipo, g.getDescripcion(),creador, g.getRadioEmision(), lat, lon, g.getFechaCreacion(), g.getFechaFinalizacion(), g.getFechaInicioActividad(), g.getFechaFinActividad());
                    String resultado = json.toJson(ib);
                    session.flush();
                    out.println(resultado);
                } catch (Exception e) {
                    InfoBean ib = new InfoBean(g.getNombre(), idGrupo, nombreTipo, g.getDescripcion(),creador, g.getRadioEmision(),g.getFechaCreacion(), g.getFechaFinalizacion(), g.getFechaInicioActividad(), g.getFechaFinActividad());
                    String resultado = json.toJson(ib);
                    session.flush();
                    out.println(resultado);
                }
            }
            else{
                out.println("NOK");
            }
        }
        else{
            out.println("NOK");
        }
    }
}

