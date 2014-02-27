/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.teide.dam.planfinder.bean.GruposSolicitadosBean;
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
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author dam2
 */
public class EnviarSolicitud extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuarioSim = req.getParameter("sim");
        String idGrupo = req.getParameter("idgrupo");
        PrintWriter out = resp.getWriter();
        if (usuarioSim != null || !usuarioSim.trim().isEmpty() || idGrupo != null || idGrupo.trim().isEmpty()) {
            try {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                PerteneceDAO pDAO = new PerteneceDAO(session);
                UsuarioDAO uDAO = new UsuarioDAO(session);
                GrupoDAO gDAO = new GrupoDAO(session);
                Pertenece p = pDAO.comprobarEstadoUsuario(usuarioSim, idGrupo);
                String estado = p.getEstado();
                if (!estado.equals(Estados.BANEADO)) {
                    Gson json = new Gson();
                    Usuario u = uDAO.comprobarUsuario(usuarioSim);
                    String nombreUsu = u.getNombre();
                    Grupo g = gDAO.comprobarGrupo(idGrupo);
                    String nombreGru = g.getNombre();
                    String[] values = new String[4];
                    values[0] = usuarioSim;
                    values[1] = nombreUsu;
                    values[2] = idGrupo;
                    values[3] = nombreGru;
                    String valores = json.toJson(values);
                    out.println(valores);
                }
            } catch (HibernateException e) {
                out.println("NOK");
            }
        }
    }
}
