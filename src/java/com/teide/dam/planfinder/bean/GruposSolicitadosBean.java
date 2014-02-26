/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.bean;

/**
 *
 * @author dam2
 */
public class GruposSolicitadosBean {
    
    private String nombreUsuario;
    private String nombreGrupo;
    private String idUsuario;
    private int idGrupo;

    public GruposSolicitadosBean(String nombreUsuario, String nombreGrupo, String idUsuario, int idGrupo) {
        this.nombreUsuario = nombreUsuario;
        this.nombreGrupo = nombreGrupo;
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
    }

    public GruposSolicitadosBean() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    
    
    
}
