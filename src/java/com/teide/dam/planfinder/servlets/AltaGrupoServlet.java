/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.servlets;

import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Ubicacion;
import java.io.IOException;
import java.io.PrintWriter;
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
        Ubicacion ubicaciones = ubicacion;
        String usuario = req.getParameter("usuario");
        String tipo = req.getParameter("tipo");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String fechaCreacion = req.getParameter("fechaCreacion");
        String fechaFinalizacion = req.getParameter("fechaFinalizacion");
        String fechaInicioActividad = req.getParameter("fechaInicioActividad");
        String fechaFinActividad = req.getParameter("fechaFinActividad");
        String estado = req.getParameter("estado");
        String radioEmision = req.getParameter("radioEmision");
        String perteneces = req.getParameter("perteneces");
        String mensajes = req.getParameter("mensajes");
        if(usuario == null || usuario.trim().isEmpty() || 
                tipo == null || tipo.trim().isEmpty() || 
                nombre == null || nombre.trim().isEmpty() || 
                fechaCreacion == null || fechaCreacion.trim().isEmpty() || 
                estado == null || estado.trim().isEmpty() ||
                radioEmision == null || radioEmision.trim().isEmpty() ){
            
        }
        else{
            Grupo g = new Grupo(ubicaciones, usuario, tipo, nombre, descripcion, new GregorianCalendar().getTime(), 
                    fechaFinalizacion, fechaInicioActividad, fechaFinActividad, "Deshabilitado", radioEmision, perteneces, mensajes);
            GrupoDAO.altaGrupo(g);
            out.println("grupo añadido");
            
        }
    }

}
