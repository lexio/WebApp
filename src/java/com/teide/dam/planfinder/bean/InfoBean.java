/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.bean;

import com.teide.dam.planfinder.pojos.Tipo;
import com.teide.dam.planfinder.pojos.Ubicacion;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dam2
 */
public class InfoBean {
   
    private String nombreGrupo, idGrupo, nombreTipo, descripcion, creador;
    private int radioEmision;
    private double lat, log;
    private Date fechaCreacion, fechaFinalizacion, fechaInicioActividad, fechaFinActivididad;

    public InfoBean() {
    }

    public InfoBean(String nombreGrupo, String idGrupo, String nombreTipo, String descripcion, String creador, int radioEmision, Date fechaCreacion, Date fechaFinalizacion, Date fechaInicioActividad, Date fechaFinActivididad) {
        this.nombreGrupo = nombreGrupo;
        this.idGrupo = idGrupo;
        this.nombreTipo = nombreTipo;
        this.descripcion = descripcion;
        this.creador = creador;
        this.radioEmision = radioEmision;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.fechaInicioActividad = fechaInicioActividad;
        this.fechaFinActivididad = fechaFinActivididad;
    }

      
   
    public InfoBean(String nombreGrupo, String idGrupo, String nombreTipo, String creador, String descripcion, int radioEmision, double lat, double log, Date fechaCreacion, Date fechaFinalizacion, Date fechaInicioActividad, Date fechaFinActivididad) {
        this.nombreGrupo = nombreGrupo;
        this.idGrupo = idGrupo;
        this.creador = creador;
        this.nombreTipo = nombreTipo;
        this.descripcion = descripcion;
        this.radioEmision = radioEmision;
        this.lat = lat;
        this.log = log;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.fechaInicioActividad = fechaInicioActividad;
        this.fechaFinActivididad = fechaFinActivididad;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRadioEmision() {
        return radioEmision;
    }

    public void setRadioEmision(int radioEmision) {
        this.radioEmision = radioEmision;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Date getFechaInicioActividad() {
        return fechaInicioActividad;
    }

    public void setFechaInicioActividad(Date fechaInicioActividad) {
        this.fechaInicioActividad = fechaInicioActividad;
    }

    public Date getFechaFinActivididad() {
        return fechaFinActivididad;
    }

    public void setFechaFinActivididad(Date fechaFinActivididad) {
        this.fechaFinActivididad = fechaFinActivididad;
    }

}
