/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.MensajeDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Mensaje;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
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
public class GuardarMensajesServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       //out.println("Entra en el servlet");
       //?mensaje=Hola&grupo=Fiesta&usuario=Pepe
       String mensaje = req.getParameter("mensaje");
       String grupo = req.getParameter("grupo") ;
       String sim = req.getParameter("sim");
       //Buscar grupo y usuario en base a los datos que le enviamos (id_grupo y sim) y a partir de ahí añadir
       //Por ahora supondré que nos dan el nombre del grupo y no el id

       try {
                if (sim != null || !sim.trim().isEmpty()|| grupo != null && !grupo.trim().isEmpty() || mensaje != null && !mensaje.trim().isEmpty()) {
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    Transaction tx = session.beginTransaction();
                    GrupoDAO gDAO=new GrupoDAO(session);
                    Grupo grupos= gDAO.comprobarGrupo(grupo);
                    UsuarioDAO uDAO=new UsuarioDAO(session);
                    Usuario usuario= uDAO.comprobarUsuario(sim);
                    PerteneceDAO pDAO=new PerteneceDAO(session);
                    Pertenece p = pDAO.comprobarEstadoUsuario(sim, grupo);
                    if(p.getEstado().equals("ACEPTADO")){
                        Mensaje m = new Mensaje(grupos, usuario, mensaje, new GregorianCalendar().getInstance().getTime(), "Enviado");
                        MensajeDAO.alta(m);
                        session.persist(m);
                        tx.commit();
                        out.println("OK");}
                    else out.println("NOK");   
               }
                
         else {out.println("NOK"); }
      
      }catch (Exception e){out.println("Error: "+e.getMessage()); {
        
    }}


  }  
}
