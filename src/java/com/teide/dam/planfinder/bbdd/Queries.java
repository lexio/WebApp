/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.bbdd;

/**
 *
 * @author dam2
 */
public class Queries {
    //Queries de grupo
    public static final String BUSCAR_GRUPO_NOMBRE = "from Grupo as g where g.nombre=:nombre";
    public static final String BUSCAR_GRUPO_TIPO = "from Grupo as g where g.tipo=:tipo";
    public static final String BUSCAR_GRUPOS_USUARIO = " from Pertenece p, grupo g where p.usuario_sim=:usuario_sim order by g.nombre";
    
    //Queries pertenece
    public static final String COMPROBAR_USUARIO_GRUPO = "from Pertenece p where p.usuario_sim=:usuario and p.grupo_id_grupo=: idgrupo";
    
    //Queries de tipo 
    public static final String BUSCAR_TIPO_NOMBRE = "from Tipo as t where t.IdTipo=:IdTipo";
    //Queries de Usuario
    public static final String BUSCAR_USUARIO_SIM = "from Usuario as u where u.Sim=:Sim";
    //Queries de ubicacion
    public static final String BUSCAR_UBICACION_ID = "from Ubicacion as ub where ub.idubicacion =:idubicacion";
}
