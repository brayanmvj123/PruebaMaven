package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.controller.service.interfaces.CrucePatrimonioDTO;
import com.bdb.opaloshare.controller.service.interfaces.CruceSalpgDTO;
import com.bdb.opaloshare.persistence.entity.SalPgdigitalDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RepositorySalPgdigitalDown extends JpaRepository<SalPgdigitalDownEntity, Serializable>  {

	@Query(value = "SELECT\r\n" +
			"		maedcv.num_cdt                                                                AS numCdt, " +
			"       derpatri.isin                                                                 AS codIsin, " +
			"       derpatri.tip_id                                                               AS tipId, " +
			"       derpatri.id_tit                                                               AS numTit, " +
			"       derpatri.nom_tit                                                              AS nomTit, " +
			"       round(((derpatri.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)  AS intBruto, " +
			"       round(((derpatri.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)   AS rteFte, " +
			"       ((round(((derpatri.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) - " +
			"        round(((derpatri.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) AS intNeto, " +
			"       round(((derpatri.rec_cap / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)   AS capPg, " +
			"       ((round(((derpatri.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) - " +
			"        round(((derpatri.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) + " +
			"       (round(((derpatri.rec_cap / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) AS totalPagar, " +
			"       CASE WHEN tranpg.nro_pord_destino = cdtOrigen.CDT_ANT " +
			"           THEN cdtOrigen.TIP_TRANSACCION_ORIGEN " +
			"           ELSE tranpg.opl_tiptrans_tbl_tip_transaccion END                                       AS tipCta, " +
			"       CASE WHEN tranpg.nro_pord_destino = cdtOrigen.CDT_ANT " +
			"           THEN cdtOrigen.DES_TRANSACCION_ORIGEN " +
			"           ELSE tiptrans.desc_transaccion END                                                     AS descTipCta, " +
			"       CASE WHEN tranpg.nro_pord_destino = cdtOrigen.CDT_ANT " +
			"           THEN cdtOrigen.NRO_PORD_DESTINO " +
			"           ELSE tranpg.NRO_PORD_DESTINO END                                          AS nroCta, " +
			"       (maedcv.val_cdt / porcentaje.suma)                                            AS nroPor, " +
			"       HISCDTS.OPL_OFICINA_TBL_NRO_OFICINA                                           AS oficina " +
			"		FROM    opl_car_derpatridep_down_tbl derpatri " +
	"       	 	INNER JOIN opl_tmp_maedcv_down_tbl maedcv   ON maedcv.cod_isin = derpatri.isin " +
"                            	AND (maedcv.NUM_CDT NOT IN (SELECT 	OHCLT.NUM_CDT " +
			"												FROM 	OPL_HIS_CTRCDTS_LARGE_TBL OHCLT " +
			"												where 	OHCLT.OPL_CONTROLES_TBL_TIP_CONTROL = 1 AND OHCLT.NOVEDAD_V = 1) " +
			"												OR TO_NUMBER(derpatri.REC_CAP) = 0 ) " +
			"        INNER JOIN OPL_HIS_CDTS_LARGE_TBL HISCDTS on HISCDTS.NUM_CDT = maedcv.NUM_CDT " +
			"        INNER JOIN opl_his_tranpg_large_tbl tranpg  ON  tranpg.opl_cdts_tbl_num_cdt = maedcv.num_cdt " +
			"                                                    AND (tranpg.opl_tiptrans_tbl_tip_transaccion = 1 " +
			"                                                        OR  tranpg.opl_tiptrans_tbl_tip_transaccion = 2 " +
			"                                                        OR  tranpg.opl_tiptrans_tbl_tip_transaccion = 7) " +
			"                                                    AND (tranpg.proceso = '1' OR tranpg.proceso = '2') " +
			"        INNER JOIN opl_his_clixcdt_large_tbl clixcdt ON clixcdt.opl_cdts_tbl_num_cdt = tranpg.opl_cdts_tbl_num_cdt " +
			"                                        AND derpatri.id_tit = ltrim(maedcv.id_tit, '0') " +
			"                                        AND clixcdt.opl_clientes_tbl_num_tit = ltrim(maedcv.id_tit, '0') " +
			"                                        AND clixcdt.tip_titular = 1 " +
			"                                        AND maedcv.cta_inv = derpatri.cta_inv " +
			"        INNER JOIN opl_par_tiptrans_down_tbl tiptrans ON tiptrans.tip_transaccion = tranpg.opl_tiptrans_tbl_tip_transaccion " +
			"        LEFT JOIN ( " +
			"            SELECT  RENOVACDT.CDT_ANT, RENOVACDT.CDT_ACT, RENOVACDT.CDT_ORIGEN CUENTA_ORIGEN, TRANPGORIGEN.NRO_PORD_DESTINO, " +
			"                   TRANPGORIGEN.OPL_TIPTRANS_TBL_TIP_TRANSACCION TIP_TRANSACCION_ORIGEN, OPTDT.DESC_TRANSACCION DES_TRANSACCION_ORIGEN " +
			"            FROM    OPL_HIS_RENOVACDT_DOWN_TBL RENOVACDT " +
			"                    INNER JOIN OPL_HIS_TRANPG_LARGE_TBL TRANPGORIGEN ON TRANPGORIGEN.OPL_CDTS_TBL_NUM_CDT = RENOVACDT.CDT_ORIGEN " +
			"                    INNER JOIN OPL_PAR_TIPTRANS_DOWN_TBL OPTDT on TRANPGORIGEN.OPL_TIPTRANS_TBL_TIP_TRANSACCION = OPTDT.TIP_TRANSACCION " +
			"            WHERE   (TRANPGORIGEN.opl_tiptrans_tbl_tip_transaccion = 1 OR  TRANPGORIGEN.opl_tiptrans_tbl_tip_transaccion = 2) " +
			"        ) cdtOrigen ON cdtOrigen.CDT_ACT = tranpg.OPL_CDTS_TBL_NUM_CDT, " +
			"        ( " +
			"         SELECT SUM(mae.val_cdt)       suma, " +
			"                ltrim(mae.id_tit, '0') id, " +
			"                mae.cod_isin, " +
			"                mae.cta_inv " +
			"         FROM   opl_car_derpatridep_down_tbl patri " +
			"                INNER JOIN opl_tmp_maedcv_down_tbl mae  ON mae.cod_isin = patri.isin " +
			"                                                        AND ltrim(mae.id_tit, '0') = patri.id_tit " +
			"                                                        AND mae.cta_inv = patri.cta_inv " +
			"         GROUP BY mae.id_tit, " +
			"                  mae.cod_isin, " +
			"                  mae.cta_inv " +
			"        ) porcentaje " +
			"			WHERE   derpatri.id_tit = porcentaje.id " +
			"        		AND derpatri.isin = porcentaje.cod_isin " +
			"        		AND derpatri.cta_inv = porcentaje.cta_inv", nativeQuery = true)
	List<CrucePatrimonioDTO> loadInfoFromCrossData();


	@Query(value = "SELECT " +
			"        salPg.NUM_CDT as numCdt, " +
			"        salPg.COD_ISIN as codIsin, " +
			"        salPg.NUM_TIT as numTit, " +
			"        salPg.NOM_TIT as nomTit, " +
			"        salPg.INT_BRUTO as intBruto, " +
			"        salPg.RTE_FTE as rteFte, " +
			"        salPg.INT_NETO as intNeto, " +
			"        salPg.TOTAL_PAGAR as totalPagar, " +
			"        salPg.CAP_PG as capPg, " +
			"        salPg.COD_ID as tipId, " +
			"        hisCdts.OPL_OFICINA_TBL_NRO_OFICINA as nroOficina, " +
			"			 hisCdts.COD_PROD as codProd, " +
			"        CASE WHEN tranpg.nro_pord_destino = cdtOrigen.CDT_ANT " +
			"            THEN cdtOrigen.TIP_TRANSACCION_ORIGEN " +
			"            ELSE tranpg.opl_tiptrans_tbl_tip_transaccion END         AS tipCta, " +
			"        CASE WHEN tranpg.nro_pord_destino = cdtOrigen.CDT_ANT " +
			"            THEN cdtOrigen.NRO_PORD_DESTINO " +
			"            ELSE tranpg.NRO_PORD_DESTINO END                         AS nroCta, " +
			"        tranpg.OPL_TIPTRANS_TBL_TIP_TRANSACCION  as tipoTran " +
			"FROM  OPL_SAL_PG_DOWN_TBL salPg " +
			"Inner Join OPL_HIS_CDTS_LARGE_TBL hisCdts on salPg.NUM_CDT = hisCdts.NUM_CDT " +
			"Inner Join OPL_HIS_TRANPG_LARGE_TBL tranpg on salPg.NUM_CDT = tranpg.OPL_CDTS_TBL_NUM_CDT " +
			"LEFT JOIN ( " +
			"    SELECT  RENOVACDT.CDT_ANT, RENOVACDT.CDT_ACT, RENOVACDT.CDT_ORIGEN CUENTA_ORIGEN, TRANPGORIGEN.NRO_PORD_DESTINO, " +
			"    TRANPGORIGEN.OPL_TIPTRANS_TBL_TIP_TRANSACCION TIP_TRANSACCION_ORIGEN, OPTDT.DESC_TRANSACCION DES_TRANSACCION_ORIGEN " +
			"    FROM    OPL_HIS_RENOVACDT_DOWN_TBL RENOVACDT " +
			"    INNER JOIN OPL_HIS_TRANPG_LARGE_TBL TRANPGORIGEN ON TRANPGORIGEN.OPL_CDTS_TBL_NUM_CDT = RENOVACDT.CDT_ORIGEN " +
			"    INNER JOIN OPL_PAR_TIPTRANS_DOWN_TBL OPTDT on TRANPGORIGEN.OPL_TIPTRANS_TBL_TIP_TRANSACCION = OPTDT.TIP_TRANSACCION " +
			") cdtOrigen ON cdtOrigen.CDT_ACT = tranpg.OPL_CDTS_TBL_NUM_CDT", nativeQuery = true)
	List<CruceSalpgDTO> loadInfoFromCrossDataWithSalPg();


	@Query(value = "SELECT " +
			"        salPgDigital.NUM_CDT as numCdt, " +
			"        salPgDigital.COD_ISIN as codIsin, " +
			"        salPgDigital.NUM_TIT as numTit, " +
			"        salPgDigital.NOM_TIT as nomTit, " +
			"        salPgDigital.INT_BRUTO as intBruto, " +
			"        salPgDigital.RTE_FTE as rteFte, " +
			"        salPgDigital.INT_NETO as intNeto, " +
			"        salPgDigital.TOTAL_PAGAR as totalPagar, " +
			"        salPgDigital.CAP_PG as capPg, " +
			"        salPgDigital.TIP_ID  as tipId, " +
			"        oficina.DESC_OFICINA as nomOficina, " +
			"        salPgDigital.OFICINA as nroOficina, " +
			"        salPgDigital.TIP_CTA  as tipCta, " +
			"        salPgDigital.NRO_CTA  as nroCta, " +
			"        tranpg.OPL_TIPTRANS_TBL_TIP_TRANSACCION  as tipoTran " +
			"FROM  OPL_HIS_CANCELAUT_DOWN_TBL cancelAut " +
			"Inner Join OPL_HIS_TRANPG_LARGE_TBL tranPg on cancelAut.NUM_CDT = tranPg.OPL_CDTS_TBL_NUM_CDT " +
			"Inner join OPL_SAL_PGDIGITAL_DOWN_TBL salPgDigital on salPgDigital.NUM_CDT = cancelAut.NUM_CDT " +
			"Inner Join OPL_PAR_OFICINA_DOWN_TBL oficina on salPgDigital.OFICINA = oficina.NRO_OFICINA " +
			"where tranPg.OPL_TIPTRANS_TBL_TIP_TRANSACCION = 6  and tranPg.PROCESO = 3",
			nativeQuery = true)
	List<CruceSalpgDTO> loadInfoGFromSalPgwithTransPg();

	boolean existsByCodIsinAndNumCdtAndNumTit(String codIsin, BigDecimal numCdt, String numTit);
}
