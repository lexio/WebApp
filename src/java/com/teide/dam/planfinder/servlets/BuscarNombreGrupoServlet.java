/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
        if (nombre == null || nombre.trim().isEmpty()){
            out.println("NOK");
        }
        else{
            try{
                nombre= new String(nombre.getBytes("iso-8859-1"),"UTF-8");
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                GrupoDAO gDAO = new GrupoDAO(session);

                ArrayList<Grupo> g = gDAO.buscarGrupoNombre(nombre);

                for (Grupo grupo : g) {
                    session.evict(grupo);
                    grupo.setUbicacion(null);
                    grupo.setTipo(null);
                    grupo.setMensajes(null);
                    grupo.setUsuario(null);
                    grupo.setPerteneces(null);
                }
    //                System.out.println(g);

                Gson json = new Gson();
                String resultado = json.toJson(g);
                out.println(resultado);
                session.flush();
            }
            catch(Exception e){
                e.printStackTrace();
            }
                        
        }
    }

}

