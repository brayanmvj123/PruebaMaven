package com.bdb.opaloshare.persistence.model.userdata;

public class SQLArchivoP {

    public final String sql = "SELECT cdts.cod_trn," +
            "cdts.num_Cdt," +
            "cdts.OPL_OFICINA_TBL_NRO_OFICINA," +
            "cliente.nom_tit," +
            "tipid.HOMO_ARCP as OPL_TIPID_TBL_COD_ID," +
            "cliente.NUM_TIT," +
            "cdts.plazo," +
            "cdts.valor," +
            "TO_CHAR(cdts.FECHA_VEN,'YYYYMMDD') AS fecha_ven," +
            "ltrim(depositante.homo_dcvsa,'0') AS OPL_DEPOSITANTE_TBL_TIP_DEPOSITANTE," +
            "TO_CHAR(cdts.fecha,'YYYYMMDD') AS fecha," +
            "cdts.COD_PROD," +
            "CASE WHEN cliente.CLASE_PER = '1' THEN 0 ELSE 1 END AS CLASE_PER," +
            "ciiu.HOMO_CRM OPL_TIPCIIU_TBL_COD_CIIU," +
            "CASE WHEN CDTS.TIP_TITULARIDAD = '3' THEN ' ' WHEN CDTS.TIP_TITULARIDAD = '2' THEN 'Y' ELSE 'O' END AS TIP_TITULARIDAD," +
            "cliente.DIR_TIT," +
            "cliente.OPL_PAR_TIPDANE_TBL_COD_DANE," +
            "cliente.TEL_TIT," +
            "CLIENTE.EXTENSION," +
            "CASE WHEN cliente.RETENCION = '1' THEN 'S' ELSE 'N' END AS RETENCION," +
            "cdts.TASA_NOM," +
            "cdts.TASA_EFE," +
            "tasa.HOMO_ARCP AS OPL_TIPTASA_TBL_TIP_TASA," +
            "CDTS.SPREAD," +
            "CDTS.OPL_TIPBASE_TBL_TIP_BASE," +
            "period.HOMO_DCVBTA as OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD," +
            "plazo.HOMO_DCVBTA as OPL_TIPPLAZO_TBL_TIP_PLAZO," +
            "dane.HOMO_CRM AS ciu_Dane," +
            "ciudad.HOMO_CRM ciudad " +
            "FROM    opl_his_cdts_large_tbl cdts" +
            " INNER JOIN opl_his_clixcdt_large_tbl clixcdt ON clixcdt.OPL_CDTS_TBL_NUM_CDT = cdts.num_cdt " +
            " INNER JOIN opl_his_clientes_large_tbl cliente ON cliente.NUM_TIT = clixcdt.opl_clientes_tbl_num_tit "
            + "INNER JOIN opl_par_tipid_down_tbl tipid ON tipid.cod_id = cliente.OPL_TIPID_TBL_COD_ID "
            + "INNER JOIN opl_par_tipperiod_down_tbl period ON period.TIP_PERIODICIDAD = cdts.OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD "
            + "INNER JOIN opl_par_tipplazo_down_tbl plazo ON plazo.TIP_PLAZO = cdts.OPL_TIPPLAZO_TBL_TIP_PLAZO " +
            "INNER JOIN OPL_par_TIPTASA_down_tbl tasa ON tasa.tip_tasa = cdts.OPL_TIPTASA_TBL_TIP_TASA " +
            "INNER JOIN OPL_PAR_TIPDANE_DOWN_TBL dane ON dane.cod_dane = cliente.OPL_PAR_TIPDANE_TBL_COD_DANE " +
			"INNER JOIN opl_par_depositante_down_tbl depositante ON depositante.tip_depositante = cdts.opl_depositante_tbl_tip_depositante " +
            "INNER JOIN OPL_PAR_TIPCIUD_DOWN_TBL ciudad ON cliente.OPL_TIPCIUD_TBL_COD_CIUD = ciudad.COD_CIUD " +
            "INNER JOIN OPL_PAR_TIPCIIU_DOWN_TBL ciiu ON cliente.OPL_TIPCIIU_TBL_COD_CIIU = ciiu.COD_CIIU " +
            "WHERE   cdts.OPL_ESTADOS_TBL_TIP_ESTADO = 1 AND clixcdt.tip_titular = 1 AND cdts.num_Cdt NOT IN (SELECT CTACDTBB FROM OPL_SAL_PDCVL_DOWN_TBL)" +
            "GROUP BY cdts.canal," +
            "cdts.cod_trn," +
            "cdts.num_Cdt," +
            "cdts.OPL_OFICINA_TBL_NRO_OFICINA," +
            "cliente.nom_tit," +
            "tipid.HOMO_ARCP," +
            "cliente.NUM_TIT," +
            "cdts.plazo," +
            "cdts.valor," +
            "cdts.FECHA_VEN," +
            "depositante.homo_dcvsa," +
            "cdts.fecha," +
            "cdts.COD_PROD," +
            "cliente.CLASE_PER," +
            "ciiu.HOMO_CRM," +
            "CDTS.TIP_TITULARIDAD," +
            "cliente.DIR_TIT," +
            "cliente.OPL_PAR_TIPDANE_TBL_COD_DANE," +
            "cliente.TEL_TIT," +
            "CLIENTE.EXTENSION," +
            "cliente.retencion," +
            "cdts.TASA_NOM," +
            "cdts.TASA_EFE," +
            "tasa.HOMO_ARCP," +
            "CDTS.SPREAD," +
            "CDTS.OPL_TIPBASE_TBL_TIP_BASE," +
            "period.HOMO_DCVBTA," +
            "plazo.HOMO_DCVBTA," +
            "dane.HOMO_CRM," +
            "ciudad.HOMO_CRM " +
            "ORDER BY cdts.num_cdt ASC";

    public final String sqlSecundario = "SELECT  DISTINCT(cdts.num_cdt) numCdt," +
            "LISTAGG(cliente.nom_tit||'!'||cliente.num_tit||'!'||tipid.HOMO_ARCP,'!') WITHIN GROUP (order by cliente.nom_tit) OVER (PARTITION BY cdts.num_cdt) listaClientesSecundario " +
            "FROM    opl_his_cdts_large_tbl cdts " +
            "        INNER JOIN opl_his_clixcdt_large_tbl clixcdt ON clixcdt.OPL_CDTS_TBL_NUM_CDT = cdts.num_cdt " +
            "        INNER JOIN opl_his_clientes_large_tbl cliente ON cliente.NUM_TIT = clixcdt.opl_clientes_tbl_num_tit " +
            "		 INNER JOIN opl_par_tipid_down_tbl tipid ON tipid.cod_id = cliente.OPL_TIPID_TBL_COD_ID " +
            " WHERE   cdts.OPL_ESTADOS_TBL_TIP_ESTADO = 1 AND clixcdt.tip_titular = 2 " +
            "GROUP by cdts.num_cdt,cliente.nom_tit," +
            "        tipid.HOMO_ARCP," +
            "        cliente.num_tit " +
            "ORDER BY cdts.num_cdt ASC ";
}
