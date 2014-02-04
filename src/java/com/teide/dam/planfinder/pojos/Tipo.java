package com.teide.dam.planfinder.pojos;
// Generated Feb 4, 2014 9:20:18 AM by Hibernate Tools 3.2.1.GA


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
 * Tipo generated by hbm2java
 */
@Entity
@Table(name="tipo"
    ,catalog="proyecto"
)
public class Tipo  implements java.io.Serializable {


     private Integer idTipo;
     private String nombre;
     private String descripcion;
     private Set<Grupo> grupos = new HashSet<Grupo>(0);

    public Tipo() {
    }

	
    public Tipo(String nombre) {
        this.nombre = nombre;
    }
    public Tipo(String nombre, String descripcion, Set<Grupo> grupos) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.grupos = grupos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idTipo", unique=true, nullable=false)
    public Integer getIdTipo() {
        return this.idTipo;
    }
    
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="descripcion", length=100)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tipo")
    public Set<Grupo> getGrupos() {
        return this.grupos;
    }
    
    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }




}


