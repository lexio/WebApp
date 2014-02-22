/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author mike
 */
public class insertarUsuarioGrupo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idGrupo = req.getParameter("idGrupo");
        double latC = Double.parseDouble(req.getParameter("latitud"));
        double longC = Double.parseDouble(req.getParameter("longitud"));
        int radioRecepcionC = Integer.parseInt(req.getParameter("radioRecepcion"));
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        UsuarioDAO uDAO = new UsuarioDAO(session);
        ArrayList<Usuario> usu = uDAO.allUser();
        GrupoDAO gDAO = new GrupoDAO(session);
        Grupo g = gDAO.comprobarGrupo(idGrupo);
        
        
        
        for (Usuario usuario : usu) {
            double latU = usuario.getLatitud();
            double longU = usuario.getLongitud();
            int radioRecepcionU = usuario.getRadioRecepcion();
            String simU = usuario.getSim();
            
            double dlong=(longC-longU);

            double degtorad=0.01745329;
            double radtodeg = 57.29577951;
            
            double dvalue = (Math.sin(latC * degtorad) * Math.sin(latU * degtorad))
            + (Math.cos(latC * degtorad) * Math.cos(latU * degtorad)
            * Math.cos(dlong * degtorad));
            double dd = Math.acos(dvalue)*radtodeg;
            double km = (dd*111.302);
            if (km <= radioRecepcionC){
                dlong=(longU-longC);
                dvalue = (Math.sin(latU * degtorad) * Math.sin(latC * degtorad))
            + (Math.cos(latU * degtorad) * Math.cos(latC * degtorad)
            * Math.cos(dlong * degtorad));
            dd = Math.acos(dvalue)*radtodeg;
            km = (dd*111.302);
            if (km <= radioRecepcionU){
                Pertenece p = new Pertenece(null, g, usuario, simU);
            }
            else usu.remove(usuario);
            }
            else usu.remove(usuario);
        }
        out.println(usu.size());
        
    }
    
}