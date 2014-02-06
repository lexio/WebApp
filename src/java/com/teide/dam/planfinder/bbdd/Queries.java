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
    //Queries de tipo 
    public static final String BUSCAR_TIPO_NOMBRE = "from Tipo as t where t.IdTipo=:IdTipo";
    //Queries de Usuario
    public static final String BUSCAR_USUARIO_SIM = "from Usuario as u where u.Sim=:Sim";
    //Queries de ubicacion
    public static final String BUSCAR_UBICACION_ID = "from Ubicacion as ub where ub.idubicacion =:idubicacion";
}
