/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.bean;

import java.util.Date;

/**
 *
 * @author dam2
 */
public class MensajeBean {
    private String idGrupo;
    private String nombreGrupo;
    private String descripcion;
    private String mensaje;
    private String usuario;
    private String fecha;

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }
    private String eliminado;

    public MensajeBean() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public MensajeBean(String idGrupo, String nombreGrupo, String descripcion, String mensaje, String usuario) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
        this.descripcion = descripcion;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
