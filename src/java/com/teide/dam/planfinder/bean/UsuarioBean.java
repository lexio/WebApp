/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.bean;

import java.util.ArrayList;

/**
 *
 * @author dam2
 */
public class UsuarioBean {
    
    private String nombre;
    private int radioRecepcion;
    private String estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private ArrayList<GrupoBean> grupos;
    private String claveGCM;

    public UsuarioBean() {
    }

    public UsuarioBean(int radioRecepcion, String estado, ArrayList<GrupoBean> grupos, String claveGCM) {
        this.radioRecepcion = radioRecepcion;
        this.estado = estado;
        this.grupos = grupos;
        this.claveGCM = claveGCM;
        this.nombre = nombre;
    }

    public int getRadioRecepcion() {
        return radioRecepcion;
    }

    public void setRadioRecepcion(int radioRecepcion) {
        this.radioRecepcion = radioRecepcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<GrupoBean> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<GrupoBean> grupos) {
        this.grupos = grupos;
    }

    public String getClaveGCM() {
        return claveGCM;
    }

    public void setClaveGCM(String claveGCM) {
        this.claveGCM = claveGCM;
    }
    
    
    
}
