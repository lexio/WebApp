
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.teide.dam.planfinder.bean.GrupoBean;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
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
 * @author Mario
 */
public class BuscarNombreGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String nombre = req.getParameter("nombre");
        String sim = req.getParameter("sim");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (nombre != null && !nombre.trim().isEmpty() && sim != null && !sim.trim().isEmpty()) {

                nombre = new String(nombre.getBytes("iso-8859-1"), "UTF-8");
                GrupoDAO gDAO = new GrupoDAO(session);
                ArrayList<GrupoBean > g = gDAO.buscarGrupoNombre(nombre, sim);
               

                //session.flush();
                //System.out.println("Este es el resultado:" + resultado);
              
                System.out.println(g.size());

//                for (Grupo grupo : g) {
//                    session.evict(grupo);
//                    grupo.setUbicacion(null);
//                    grupo.setTipo(null);
//                    grupo.setMensajes(null);
//                    grupo.setUsuario(null);
//                    grupo.setPerteneces(null);
//                    if (grupo.getDescripcion() == "null") {
//                        grupo.setDescripcion("");
//                    }
//                }
                //System.out.println(g);

                Gson json = new Gson();
                String resultado = json.toJson(g);
                //session.flush();
                //System.out.println("Este es el resultado:" + resultado);
                out.println(resultado);

            } else {
                out.println("NOK1");
            }

        } catch (Exception e) {
            out.println("NOK2"+e.getMessage());
        } finally {
           session.flush();
        }
    }
}
