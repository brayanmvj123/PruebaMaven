package com.bdb.opaloshare.persistence.model.userdata;

public class SQLArchivoTraductor {

    public final String sql="SELECT TRAMA FROM OPL_SAL_TRAMASTC_DOWN_TBL " +
            "WHERE fecha = to_number(to_char(sysdate,'YYYYMMDD'))";

    public final String sqlContigencia="SELECT TRAMA FROM OPL_SAL_TRAMASTC_DOWN_TBL " +
            "WHERE fecha = to_number(to_char(sysdate,'YYYYMMDD')) AND trama LIKE('%DECEAPDI%')";

    public final String sqlContiTramasDiferDeceapdi="SELECT TRAMA FROM OPL_SAL_TRAMASTC_DOWN_TBL " +
            "WHERE fecha = to_number(to_char(sysdate,'YYYYMMDD')) AND trama NOT LIKE('%DECEAPDI%')";

}
