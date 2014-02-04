package com.teide.dam.planfinder.pojos;
// Generated Feb 4, 2014 10:15:51 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ubicacion generated by hbm2java
 */
@Entity
@Table(name="ubicacion"
    ,catalog="proyecto"
)
public class Ubicacion  implements java.io.Serializable {


     private Integer idubicacion;
     private String descripcion;
     private Double latitud;
     private Double longitud;
     private Set<Grupo> grupos = new HashSet<Grupo>(0);

    public Ubicacion() {
    }

	
    public Ubicacion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Ubicacion(String descripcion, Double latitud, Double longitud, Set<Grupo> grupos) {
       this.descripcion = descripcion;
       this.latitud = latitud;
       this.longitud = longitud;
       this.grupos = grupos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idubicacion", unique=true, nullable=false)
    public Integer getIdubicacion() {
        return this.idubicacion;
    }
    
    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }
    
    @Column(name="descripcion", nullable=false, length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Column(name="latitud", precision=22, scale=0)
    public Double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    
    @Column(name="longitud", precision=22, scale=0)
    public Double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ubicacion")
    public Set<Grupo> getGrupos() {
        return this.grupos;
    }
    
    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }




}


