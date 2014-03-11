/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.bean.InfoBean;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
            Gson json = new Gson();
            GrupoDAO gDAO = new GrupoDAO(session);
            Grupo g = gDAO.comprobarGrupo(idGrupo);
            if (g != null) {
                String nombreTipo = g.getTipo().getNombre();
                String creador = g.getUsuario().getNombre();
                String nombreGrupo = g.getNombre();
                String descripcion = g.getDescripcion();
                int radioEmision = g.getRadioEmision();
               
                    Date fechaCreacion = null;
                try {
                    fechaCreacion = g.getFechaCreacion();
                } catch (Exception e) {
                }
               
                    Date fechaFinalizacion = null;
                try {
                    fechaFinalizacion = g.getFechaFinalizacion();
                } catch (Exception e) {
                   
                }
                    Date fechaInicioActividad = null;
                try {
                    fechaInicioActividad = g.getFechaInicioActividad();
                } catch (Exception e) {
                   
                }
                    Date fechaFinActividad = null;
                try {
                    fechaFinActividad = g.getFechaFinActividad();
                } catch (Exception e) {
                   
                }
                    double lat = 0;
                try {
                     lat = g.getUbicacion().getLatitud();
                } catch (Exception e) {
                    
                }
                    double lon = 0;
                try {
                    lon = g.getUbicacion().getLongitud();
                } catch (Exception e) {
                   
                }             
               
                //System.out.println("FECHA INICIO ACTIVIDAD " + fechaInicioActividad);
                //System.out.println("FECHA FIN ACTIVIDAD " + fechaFinActividad);

               
                try {
                    InfoBean ib = new InfoBean(nombreGrupo, idGrupo, nombreTipo, descripcion, creador, radioEmision, lat, lon, fechaCreacion, fechaFinalizacion, fechaInicioActividad, fechaFinActividad);
                    String resultado = json.toJson(ib);
                    session.flush();
                    out.println(resultado);
                } catch (Exception e) {
                    out.println("NOK");
                };
            }
            else out.println("NOK");
        }
        else out.println("NOK");
    }
}
