/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import java.util.Set;
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
public class ComprobarEstadoUsuario extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuarioSim = req.getParameter("usuarioSim");
        String idGrupo = req.getParameter("idGrupo");
               
        if (usuarioSim == null && usuarioSim.trim().isEmpty()||idGrupo == null && idGrupo.trim().isEmpty()){
            
        }
        else{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PerteneceDAO pDAO = new PerteneceDAO(session);
            Pertenece p = pDAO.comprobarEstadoUsuario(usuarioSim, idGrupo);
            if(p.getEstado().equals("Baneado")){
                //??¿¿
            }else if(p.getEstado().equals("Aceptado")){
                //??¿¿
            }else if(p.getEstado().equals("Solicitado")){ 
                //??¿¿ 
            }else{
                
            }
            //tx.commit();
        }
    }

    
}
