/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lexio
 */
public class RechazarSolicitudGrupo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuarioSim = req.getParameter("usuarioSim");
        String idGrupo = req.getParameter("idGrupo");
        String estado = req.getParameter("estado");
        
        if (usuarioSim == null && usuarioSim.trim().isEmpty()||idGrupo == null && idGrupo.trim().isEmpty()||
                estado == null && estado.trim().isEmpty()){
            
        }else{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PerteneceDAO pDAO = new PerteneceDAO(session);
            pDAO.rechazarSolicitud(usuarioSim, idGrupo, estado);
            tx.commit();
        }
    }
}