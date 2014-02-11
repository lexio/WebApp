/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.pojos.Ubicacion;
import com.teide.dam.planfinder.pojos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mario
 */
public class AltaGrupoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String ubicacion = req.getParameter("ubicacion");
        //Ubicacion ubicacion = new Ubicacion(req.getParameter("ubicacion"));
        //Ubicacion ubicaciones = u;
        String usuario = req.getParameter("usuario");
        //Usuario usuario = new Usuario(req.getParameter("usuario"));
        String tipo = req.getParameter("tipo");
        //Tipo tipo = new Tipo(req.getParameter("tipo"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String fechaCreacion = req.getParameter("fechaCreacion");
        String fechaFinalizacion = req.getParameter("fechaFinalizacion");
        String fechaInicioActividad = req.getParameter("fechaInicioActividad");
        String fechaFinActividad = req.getParameter("fechaFinActividad");
        String estado = req.getParameter("estado");
        String radioEmisio = req.getParameter("radioEmision");
        String perteneces = req.getParameter("perteneces");
        String mensajes = req.getParameter("mensajes");
        
        
        
        if(usuario == null || usuario.trim().isEmpty() || 
                tipo == null || tipo.trim().isEmpty() || 
                nombre == null || nombre.trim().isEmpty() || 
                fechaCreacion == null || fechaCreacion.trim().isEmpty() || 
                estado == null || estado.trim().isEmpty() ||
                radioEmisio == null || radioEmisio.trim().isEmpty() ){
            
        }
        else{
            Ubicacion u = new Ubicacion
            DateFormat fF = new SimpleDateFormat(fechaFinalizacion);
            DateFormat fIA = new SimpleDateFormat(fechaInicioActividad);
            DateFormat fFA = new SimpleDateFormat(fechaFinActividad);
            int radioEmision=Integer.parseInt(radioEmisio);
            Grupo g = new Grupo(ubicacion, usuario, tipo, nombre, descripcion, new GregorianCalendar().getTime(), 
                    fF, fIA, fFA, "Deshabilitado", radioEmision, perteneces, mensajes);
            GrupoDAO.altaGrupo(g);
            out.println("grupo añadido");
            
        }
    }

}
