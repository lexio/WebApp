package com.teide.dam.planfinder.pojos;
// Generated Feb 4, 2014 10:15:51 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,catalog="proyecto"
)
public class Usuario  implements java.io.Serializable {


     private String sim;
     private String nombre;
     private String estado;
     private int radioRecepcion;
     private double latitud;
     private double longitud;
     private Date ultimaConexion;
     private String claveGcm;
     private Set<Grupo> grupos = new HashSet<Grupo>(0);
     private Set<Mensaje> mensajes = new HashSet<Mensaje>(0);
     private Set<Pertenece> perteneces = new HashSet<Pertenece>(0);

    public Usuario() {
    }

	
    public Usuario(String sim, String nombre, String estado, int radioRecepcion, double latitud, double longitud, Date ultimaConexion) {
        this.sim = sim;
        this.nombre = nombre;
        this.estado = estado;
        this.radioRecepcion = radioRecepcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ultimaConexion = ultimaConexion;
    }
    public Usuario(String sim, String nombre, String estado, int radioRecepcion, double latitud, double longitud, Date ultimaConexion, String claveGcm, Set<Grupo> grupos, Set<Mensaje> mensajes, Set<Pertenece> perteneces) {
       this.sim = sim;
       this.nombre = nombre;
       this.estado = estado;
       this.radioRecepcion = radioRecepcion;
       this.latitud = latitud;
       this.longitud = longitud;
       this.ultimaConexion = ultimaConexion;
       this.claveGcm = claveGcm;
       this.grupos = grupos;
       this.mensajes = mensajes;
       this.perteneces = perteneces;
    }
   
     @Id 
    
    @Column(name="sim", unique=true, nullable=false, length=20)
    public String getSim() {
        return this.sim;
    }
    
    public void setSim(String sim) {
        this.sim = sim;
    }
    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="estado", nullable=false, length=45)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name="radioRecepcion", nullable=false)
    public int getRadioRecepcion() {
        return this.radioRecepcion;
    }
    
    public void setRadioRecepcion(int radioRecepcion) {
        this.radioRecepcion = radioRecepcion;
    }
    
    @Column(name="latitud", nullable=false, precision=22, scale=0)
    public double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    @Column(name="longitud", nullable=false, precision=22, scale=0)
    public double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ultimaConexion", nullable=false, length=19)
    public Date getUltimaConexion() {
        return this.ultimaConexion;
    }
    
    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }
    
    @Column(name="claveGcm", length=200)
    public String getClaveGcm() {
        return this.claveGcm;
    }
    
    public void setClaveGcm(String claveGcm) {
        this.claveGcm = claveGcm;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usuario")
    public Set<Grupo> getGrupos() {
        return this.grupos;
    }
    
    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usuario")
    public Set<Mensaje> getMensajes() {
        return this.mensajes;
    }
    
    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usuario")
    public Set<Pertenece> getPerteneces() {
        return this.perteneces;
    }
    
    public void setPerteneces(Set<Pertenece> perteneces) {
        this.perteneces = perteneces;
    }




}


