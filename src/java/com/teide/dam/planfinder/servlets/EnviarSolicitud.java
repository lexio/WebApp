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
import com.teide.dam.planfinder.pojos.PerteneceId;
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
import org.hibernate.Transaction;

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=  session.beginTransaction();
        if (usuarioSim != null && !usuarioSim.trim().isEmpty() && idGrupo != null && !idGrupo.trim().isEmpty()) {
            try {
                PerteneceDAO pDAO = new PerteneceDAO(session);
                UsuarioDAO uDAO = new UsuarioDAO(session);
                GrupoDAO gDAO = new GrupoDAO(session);
                Pertenece p = pDAO.comprobarEstadoUsuario(usuarioSim, idGrupo);
                Usuario u = uDAO.comprobarUsuario(usuarioSim);
                Grupo g = gDAO.comprobarGrupoyEstado(idGrupo,Estados.HABILITADO);
                if(p!=null){
                    String estado = p.getEstado();
                    if (!estado.equals(Estados.BANEADO)) {
                        Gson json = new Gson();
                        String nombreUsu = u.getNombre();
                        String nombreGru = g.getNombre();
                        String[] values = new String[4];
                        values[0] = usuarioSim;
                        values[1] = nombreUsu;
                        values[2] = idGrupo;
                        values[3] = nombreGru;
                        String valores = json.toJson(values);
                        out.println(valores);
                    }
                } else{
                    
                    if (g!=null && u!=null) {
                        int idgrupoInt = Integer.parseInt(idGrupo);
                        PerteneceId pId = new PerteneceId(usuarioSim, idgrupoInt);
                        p = new Pertenece(pId, g, u, Estados.SOLICITADO);
                        session.persist(p);
                        //session.flush();
                        //tx.commit();
                        out.println("OK");
                    }else  out.println("NOK");
                    
                }
            } catch (Exception e) {
                out.println("NOK");
            }
            finally {
                session.flush();
            }
            tx.commit();
        } else out.println("NOK");
    }
}
