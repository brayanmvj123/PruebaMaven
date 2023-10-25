package com.bdb.opaloshare.persistence.model.userdata;

public class SQLBloquearUsuario {


    public final String sqlBloqueo = "SELECT  LOGIN.ITEM, LOGIN.USUARIO,\n" +
            "LOGIN.NOMBRES, LOGIN.APELLIDOS, LOGIN.FECHA_CONEXION, LOGIN.ESTADO,\n" +
            "LOGIN.TOKEN, LOGIN.IDENTIFICACION  \n" +
            "FROM    OPL_HIS_LOGIN_DOWN_TBL LOGIN ,\n" +
            "        OPL_PAR_VARENTORNO_DOWN_TBL VARENTORNO \n" +
            "WHERE   VARENTORNO.DESC_VARIABLE = 'CANT_DIAS_BLOQUEO_USR' \n" +
            "        AND sysdate - LOGIN.FECHA_CONEXION > to_number(VARENTORNO.VAL_VARIABLE) \n" +
            "        AND estado = 1";
}
