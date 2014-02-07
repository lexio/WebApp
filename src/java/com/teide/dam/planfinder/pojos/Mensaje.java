package com.teide.dam.planfinder.pojos;
// Generated Feb 7, 2014 12:10:03 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mensaje generated by hbm2java
 */
@Entity
@Table(name="mensaje"
    ,catalog="proyecto"
)
public class Mensaje  implements java.io.Serializable {


     private Integer idMensaje;
     private Grupo grupo;
     private Usuario usuario;
     private String mensaje;
     private Date fecha;
     private String estado;

    public Mensaje() {
    }

    public Mensaje(Grupo grupo, Usuario usuario, String mensaje, Date fecha, String estado) {
       this.grupo = grupo;
       this.usuario = usuario;
       this.mensaje = mensaje;
       this.fecha = fecha;
       this.estado = estado;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idMensaje", unique=true, nullable=false)
    public Integer getIdMensaje() {
        return this.idMensaje;
    }
    
    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="grupo_id_grupo", nullable=false)
    public Grupo getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_sim", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Column(name="mensaje", nullable=false, length=3000)
    public String getMensaje() {
        return this.mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha", nullable=false, length=19)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Column(name="estado", nullable=false, length=45)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


