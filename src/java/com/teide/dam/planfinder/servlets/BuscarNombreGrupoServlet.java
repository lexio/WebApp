
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.teide.dam.planfinder.bean.GrupoBean;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
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
                ArrayList<GrupoBean> g = gDAO.buscarGrupoNombre(nombre);
                PerteneceDAO pDAO = new PerteneceDAO(session);
                ArrayList<Pertenece> p = pDAO.buscarPertenece(sim);

                while (p.size() > 0) {
                    for (GrupoBean grupoBean : g) {
                        for (Pertenece pertenece : p) {
                            if (grupoBean.getIdGrupo() == pertenece.getGrupo().getIdGrupo()) {
                                g.remove(grupoBean);
                                p.remove(pertenece);
                                break;
                            }
                        }
                    }
                }
                
                ArrayList<GrupoBean> gfinal = new ArrayList<>();
                
                for (GrupoBean grupoBean : g) {
                    if (grupoBean.getEstado().equals(Estados.HABILITADO)){
                        gfinal.add(grupoBean);
                    }
                }

                //session.flush();
                //System.out.println("Este es el resultado:" + resultado);

                System.out.println(gfinal.size());

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
                String resultado = json.toJson(gfinal);
                //session.flush();
                //System.out.println("Este es el resultado:" + resultado);
                out.println(resultado);

            } else {
                out.println("NOK");
            }

        } catch (Exception e) {
            out.println("NOK");
        } finally {
            session.flush();
        }
    }
}
