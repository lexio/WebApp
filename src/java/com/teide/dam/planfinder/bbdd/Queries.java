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
    public static final String BUSCAR_GRUPO_POR_ID = "from Grupo as g where g.idGrupo=:idGrupo";
    public static final String BUSCAR_GRUPOS_USUARIO = " from Pertenece p, grupo g where p.usuario=:usuario_sim order by g.nombre";
    public static final String COGER_CREADOR = "from Grupo as g where g.usuario=:usuario";
    public static final String COGER_SIM = "from Usuario as usu where usu.sim=:sim";
    public static final String BUSCAR_GRUPOS_ALFABETICO = "from Grupo order by nombre";
    
    //Queries pertenece
    public static final String COMPROBAR_USUARIO_GRUPO = "from Pertenece p where p.usuario=:usuario_sim and p.grupo=:id_grupo";
    //public static final String CAMBIAR_ESTADO_USUARIO_GRUPO = "from Pertenece p where p.usuario_sim=:usuario_sim and p.id_grupo=:id_grupo";
            
    //Queries de tipo
    public static final String ACTUALIZAR_POS = "UPDATE Usuario as u SET u.latitud=:lat, u.longitud:=log  WHERE u.sim=:sim";
    public static final String BUSCAR_TIPO_NOMBRE = "from Tipo as t where t.IdTipo=:idtipo";
    public static final String BUSCAR_TIPO_TODO = "from Tipo";
    public static final String BUSCAR_TIPO_Y_GRUPO = "from Tipo as t where t.IdTipo=:idtipo and t.nombre=:nombre";
    public static final String BUSCAR_ID_TIPO_POR_NOMBRE = "from Tipo as t where t.nombre=:nombre";
    //Queries de Usuario
    public static final String BUSCAR_USUARIO_SIM = "from Usuario as u where u.sim=:sim";
    public static final String BUSCAR_ESTADO_USUARIO = "from Usuario as u where u.estado=:estado";
    //Queries de ubicacion
    public static final String BUSCAR_UBICACION_ID = "from Ubicacion as ub where ub.idubicacion =:idubicacion";
    
    //Queries de mensaje 
    public static final String BUSCAR_MENSAJES_GRUPO = "from Mensaje as m where m.grupo=:idGrupo";
}
