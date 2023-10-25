package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.controller.service.interfaces.CDTVencidoDTO;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.TitularCtaInvDTO;
import com.bdb.opaloshare.persistence.model.columnselected.ColumnsReportPgWeekly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RepositorySalPg extends JpaRepository<SalPgDownEntity, Long> {

    @Query(value = "SELECT  MAEDCV.OFICINA as nroOficina," +
            "        CASE WHEN esDepositante.IDTIT = CARDEPATRIEMI.ID_TIT " +
            "                   AND esDepositante.ISIN = CARDEPATRIEMI.ISIN " +
            "                   AND esDepositante.CTAINV = CARDEPATRIEMI.CTA_INV " +
            "        THEN '1' ELSE '0' END depositante, " +
            "        MAEDCV.NUM_CDT as numCdt," +
            "        MAEDCV.COD_ISIN as codIsin," +
            "        CARDEPATRIEMI.CTA_INV as ctaInv," +
            "        MAEDCV.OPL_TIPID_TBL_COD_ID as codId," +
            "        CARDEPATRIEMI.ID_TIT as numTit," +
            "        CARDEPATRIEMI.NOM_TIT as nomTit," +
            "        TO_CHAR(TO_DATE(MAEDCV.FECHA_EMI, 'DD-MM-YYYY'), 'YYYY-MM-DD') as fechaEmi," +
            "        TO_CHAR(TO_DATE(MAEDCV.FECHA_VEN, 'DD-MM-YYYY'), 'YYYY-MM-DD') as fechaVen," +
            "        TO_CHAR(TO_DATE(MAEDCV.FECHA_PROX_PG,'DD-MM-YYYY'), 'YYYY-MM-DD') as fechaProxPg," +
            "        MAEDCV.OPL_TIPPLAZO_TBL_TIP_PLAZO as tipPlazo," +
            "        MAEDCV.PLAZO as plazo," +
            "        MAEDCV.OPL_TIPBASE_TBL_TIP_BASE as tipBase," +
            "        MAEDCV.OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD as tipPeriodicidad," +
            "        MAEDCV.OPL_TIPTASA_TBL_TIP_TASA as tipTasa," +
            "        MAEDCV.TAS_EFE as tasaEfe," +
            "        MAEDCV.TAS_NOM as tasaNom," +
            "        MAEDCV.VAL_CDT as valorNominal," +
            "        MAEDCV.SPREAD as spread," +
            "        HISTCDT.COD_PROD as codProd," +
            "        round(((CARDEPATRIEMI.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)  AS intBruto," +
            "        round(((CARDEPATRIEMI.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)   AS rteFte," +
            "        ((round(((CARDEPATRIEMI.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) - " +
            "         round(((CARDEPATRIEMI.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) AS intNeto," +
            "        round(((CARDEPATRIEMI.rec_cap / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)   AS capPg," +
            "        ((round(((CARDEPATRIEMI.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) - " +
            "         round(((CARDEPATRIEMI.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) + " +
            "        (round(((CARDEPATRIEMI.rec_cap / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) AS totalPagar," +
            "        MAEDCV.OPL_TIPPOSICION_TBL_TIP_POSICION as tipPosicion," +
            "        CARDEPATRIEMI.FACTOR as factorDcvsa," +
            "        0 as factorOpl " +
            "FROM    OPL_TMP_MAEDCV_DOWN_TBL MAEDCV " +
            "        INNER JOIN OPL_CAR_DERPATRIEMI_DOWN_TBL CARDEPATRIEMI ON CARDEPATRIEMI.ISIN = MAEDCV.COD_ISIN " +
            "                                                            AND LTRIM(CARDEPATRIEMI.ID_TIT, '0') = LTRIM(MAEDCV.ID_TIT,'0') " +
            "                                                            AND LTRIM(CARDEPATRIEMI.CTA_INV,'0') = LTRIM(MAEDCV.CTA_INV,'0') " +
            "        INNER JOIN OPL_HIS_CDTS_LARGE_TBL HISTCDT ON MAEDCV.NUM_CDT = HISTCDT.NUM_CDT " +
            "        LEFT JOIN (" +
            "           SELECT  DERPATRIDEP.ID_TIT IDTIT, DERPATRIDEP.ISIN ISIN, DERPATRIDEP.CTA_INV CTAINV " +
            " /*, DERPATRIDEP.FACTOR*/ " +
            "           FROM    OPL_CAR_DERPATRIEMI_DOWN_TBL CARDEPATRIEMI " +
            "                   INNER JOIN OPL_CAR_DERPATRIDEP_DOWN_TBL DERPATRIDEP  ON CARDEPATRIEMI.ISIN = DERPATRIDEP.ISIN " +
            "                                                                        AND CARDEPATRIEMI.ID_TIT = LTRIM(DERPATRIDEP.ID_TIT,'0') " +
            "                                                                        AND LTRIM(CARDEPATRIEMI.CTA_INV,'0') = LTRIM(DERPATRIDEP.CTA_INV,'0')" +
            "       ) esDepositante ON   CARDEPATRIEMI.ISIN = esDepositante.ISIN " +
            "                           AND CARDEPATRIEMI.CTA_INV = esDepositante.CTAINV " +
            "                           AND CARDEPATRIEMI.ID_TIT = esDepositante.IDTIT," +
            "        ( " +
            "            SELECT  SUM(mae.val_cdt)       suma," +
            "                    ltrim(mae.id_tit, '0') id," +
            "                    mae.cod_isin," +
            "                    mae.cta_inv " +
            "            FROM   OPL_CAR_DERPATRIEMI_DOWN_TBL patri " +
            "                   INNER JOIN opl_tmp_maedcv_down_tbl mae  ON mae.cod_isin = patri.isin " +
            "                                                           AND ltrim(mae.id_tit, '0') = patri.id_tit " +
            "                                                           AND mae.cta_inv = patri.cta_inv " +
            "                   GROUP BY     mae.id_tit," +
            "                                mae.cod_isin," +
            "                                mae.cta_inv" +
            "        ) porcentaje " +
            "WHERE   CARDEPATRIEMI.id_tit = porcentaje.id " +
            "        AND CARDEPATRIEMI.isin = porcentaje.cod_isin " +
            "        AND CARDEPATRIEMI.cta_inv = porcentaje.cta_inv", nativeQuery = true)
    List<ColumnsReportPgWeekly> cruceReportFechaVenOfic();

    @Query(value = "SELECT  " +
            "        SALPG.ITEM as item," +
            "        SALPG.NRO_OFICINA as nroOficina," +
            "        SALPG.DEPOSITANTE as depositante," +
            "        SALPG.NUM_CDT as numCdt," +
            "        SALPG.COD_ISIN as codIsin," +
            "        SALPG.CTA_INV as ctaInv," +
            "        SALPG.COD_ID as codId," +
            "        SALPG.NUM_TIT as numTit," +
            "        SALPG.NOM_TIT as nomTit," +
            "        SALPG.FECHA_EMI as fechaEmi," +
            "        SALPG.FECHA_VEN as fechaVen," +
            "        SALPG.FECHA_PROX_PG as fechaProxPg," +
            "        SALPG.TIP_PLAZO as tipPlazo," +
            "        SALPG.PLAZO as plazo," +
            "        SALPG.TIP_BASE as tipBase," +
            "        SALPG.TIP_PERIODICIDAD as tipPeriodicidad," +
            "        SALPG.TIP_TASA as tipTasa," +
            "        SALPG.TASA_EFE as tasaEfe," +
            "        SALPG.TASA_NOM as tasaNom," +
            "        SALPG.SPREAD as spread," +
            "        SALPG.VALOR_NOMINAL as valorNominal," +
            "        SALPG.INT_BRUTO as intBruto," +
            "        SALPG.RTE_FTE as rteFte," +
            "        SALPG.INT_NETO as intNeto, " +
            "        SALPG.CAP_PG as capPg, " +
            "        SALPG.TOTAL_PAGAR as totalPagar," +
            "        SALPG.TIP_POSICION as tipPosicion," +
            "        SALPG.FACTOR_DCVSA as factorDcvsa," +
            "        SALPG.FACTOR_OPL as factorOpl," +
            "        SALPG.COD_PROD as codProd," +
            "        SALPG.ESTADO as estado," +
            "        SALPG.FECHA as fecha," +
            "        TIPPOSICION.DESC_POSICION as descPosicion," +
            "        CASE " +
            "            WHEN SALPG.FACTOR_OPL <> SALPG.FACTOR_DCVSA THEN 'NO CONCILIADO' " +
            "            WHEN AJUSLIQDIA.FACTOR_DCVSA = SALPG.FACTOR_DCVSA THEN 'DECEVAL' " +
            "            WHEN AJUSLIQDIA.FACTOR_OPL = SALPG.FACTOR_OPL THEN 'OPALO' " +
            "            ELSE 'CONCILIADO'  " +
            "        END as conciliacion " +
            "FROM    OPL_SAL_PG_DOWN_TBL SALPG " +
            "        INNER JOIN OPL_PAR_TIPPOSICION_DOWN_TBL TIPPOSICION ON SALPG.TIP_POSICION = TIPPOSICION.TIP_POSICION " +
            "        LEFT JOIN OPL_HIS_AJUSLIQDIA_LARGE_TBL AJUSLIQDIA ON SALPG.COD_ISIN = AJUSLIQDIA.OPL_PG_TBL_COD_ISIN " +
            "                                                            AND SALPG.CTA_INV = AJUSLIQDIA.OPL_PG_TBL_CTA_INV " +
            "                                                            AND SALPG.NUM_CDT = AJUSLIQDIA.OPL_PG_TBL_NUM_CDT " +
            "ORDER BY    NUM_CDT" +
            "", nativeQuery = true)
    List<ColumnsReportPgWeekly> cdtsConciliacion();


    @Query(value = "SELECT  " +
            "        SALPG.ITEM as item," +
            "        SALPG.NRO_OFICINA as nroOficina," +
            "        SALPG.DEPOSITANTE as depositante," +
            "        SALPG.NUM_CDT as numCdt," +
            "        SALPG.COD_ISIN as codIsin," +
            "        SALPG.CTA_INV as ctaInv," +
            "        SALPG.COD_ID as codId," +
            "        SALPG.NUM_TIT as numTit," +
            "        SALPG.NOM_TIT as nomTit," +
            "        SALPG.FECHA_EMI as fechaEmi," +
            "        SALPG.FECHA_VEN as fechaVen," +
            "        SALPG.FECHA_PROX_PG as fechaProxPg," +
            "        SALPG.TIP_PLAZO as tipPlazo," +
            "        SALPG.PLAZO as plazo," +
            "        SALPG.TIP_BASE as tipBase," +
            "        SALPG.TIP_PERIODICIDAD as tipPeriodicidad," +
            "        SALPG.TIP_TASA as tipTasa," +
            "        SALPG.TASA_EFE as tasaEfe," +
            "        SALPG.TASA_NOM as tasaNom," +
            "        SALPG.SPREAD as spread," +
            "        SALPG.VALOR_NOMINAL as valorNominal," +
            "        SALPG.INT_BRUTO as intBruto," +
            "        SALPG.RTE_FTE as rteFte," +
            "        SALPG.INT_NETO as intNeto, " +
            "        SALPG.CAP_PG as capPg, " +
            "        SALPG.TOTAL_PAGAR as totalPagar," +
            "        SALPG.TIP_POSICION as tipPosicion," +
            "        SALPG.FACTOR_DCVSA as factorDcvsa," +
            "        SALPG.FACTOR_OPL as factorOpl," +
            "        SALPG.COD_PROD as codProd," +
            "        SALPG.ESTADO as estado," +
            "        SALPG.FECHA as fecha," +
            "        TIPPOSICION.DESC_POSICION as descPosicion," +
            "        CASE " +
            "            WHEN SALPG.FACTOR_OPL <> SALPG.FACTOR_DCVSA THEN 'NO CONCILIADO' " +
            "            WHEN AJUSLIQDIA.FACTOR_DCVSA = SALPG.FACTOR_DCVSA THEN 'DECEVAL' " +
            "            WHEN AJUSLIQDIA.FACTOR_OPL = SALPG.FACTOR_OPL THEN 'OPALO' " +
            "            ELSE 'CONCILIADO'  " +
            "        END as conciliacion " +
            "FROM    OPL_SAL_PG_DOWN_TBL SALPG " +
            "        INNER JOIN OPL_PAR_TIPPOSICION_DOWN_TBL TIPPOSICION ON SALPG.TIP_POSICION = TIPPOSICION.TIP_POSICION" +
            "        LEFT JOIN OPL_HIS_AJUSLIQDIA_LARGE_TBL AJUSLIQDIA ON SALPG.COD_ISIN = AJUSLIQDIA.OPL_PG_TBL_COD_ISIN" +
            "                                                            AND SALPG.CTA_INV = AJUSLIQDIA.OPL_PG_TBL_CTA_INV" +
            "                                                            AND SALPG.NUM_CDT = AJUSLIQDIA.OPL_PG_TBL_NUM_CDT " +
            "ORDER BY NUM_CDT ",
            countQuery = "SELECT count(*) " +
                    "FROM    OPL_SAL_PG_DOWN_TBL SALPG" +
                    "        INNER JOIN OPL_PAR_TIPPOSICION_DOWN_TBL TIPPOSICION ON SALPG.TIP_POSICION = TIPPOSICION.TIP_POSICION" +
                    "        LEFT JOIN OPL_HIS_AJUSLIQDIA_LARGE_TBL AJUSLIQDIA ON SALPG.COD_ISIN = AJUSLIQDIA.OPL_PG_TBL_COD_ISIN" +
                    "                                                            AND SALPG.CTA_INV = AJUSLIQDIA.OPL_PG_TBL_CTA_INV" +
                    "                                                            AND SALPG.NUM_CDT = AJUSLIQDIA.OPL_PG_TBL_NUM_CDT " +
                    "ORDER BY NUM_CDT", nativeQuery = true)
    Page<ColumnsReportPgWeekly> cdtsConciliacionPag(Pageable pageable);


    @Query(value = "SELECT  " +
            "        MAEDCV.PLAZO as plazo," +
            "        SALPG.TIP_PLAZO as tipPlazo," +
            "        MAEDCV.NUM_CDT as numCdt," +
            "        MAEDCV.FECHA_VEN as fechaVen," +
            "        SALPG.COD_PROD as tipProd," +
            "        MAEDCV.OPL_TIPID_TBL_COD_ID as tipId," +
            "        MAEDCV.ID_TIT as numTit," +
            "        MAEDCV.NOM_TIT as nomTit," +
            "        MAEDCV.OFICINA as oficina," +
            "        OFICINA.DESC_OFICINA as nomOficina," +
            "        SALPG.CAP_PG as capPg," +
            "        SALPG.TOTAL_PAGAR as totalPagar," +
            "        SALPG.INT_BRUTO as intTotal," +
            "        SALPG.RTE_FTE as rteFte," +
            "        SALPG.INT_NETO as intNeto, " +
            "        SALPG.COD_ISIN as codIsin, " +
            "        SALPG.INT_BRUTO as intBruto, " +
            "        CTAINV.REL_CTA as relCta, " +
            "        MAEDCV.CTA_INV as ctaInv " +
            "FROM    OPL_TMP_MAEDCV_DOWN_TBL MAEDCV " +
            "INNER JOIN OPL_SAL_PG_DOWN_TBL SALPG ON MAEDCV.NUM_CDT = SALPG.NUM_CDT " +
            "INNER JOIN OPL_PAR_OFICINA_DOWN_TBL OFICINA ON MAEDCV.OFICINA = OFICINA.NRO_OFICINA " +
            "INNER JOIN OPL_PAR_TIPPLAZO_DOWN_TBL TIPPLAZO ON MAEDCV.OPL_TIPPLAZO_TBL_TIP_PLAZO = TIPPLAZO.TIP_PLAZO " +
            "INNER JOIN OPL_HIS_CTAINV_MEDIUM_TBL CTAINV on ltrim(MAEDCV.CTA_INV, 0) = ltrim(CTAINV.NUM_CTA,0) " +
            "WHERE  MAEDCV.NUM_CDT = :numCdt" +
            "        AND MAEDCV.ID_TIT = :numTit" +
            "        AND MAEDCV.NUM_CDT = :numCdt " +
            "        AND (:codIsin is null or MAEDCV.COD_ISIN = :codIsin) " +
            "        AND (:ctaInv is null or LTRIM(MAEDCV.CTA_INV, 0) = LTRIM(:ctaInv, 0))" +
            "", nativeQuery = true)
    CDTVencidoDTO cdtsInfo(@Param("numTit") Long numTit, @Param("numCdt") Long numCdt,
                           @Param("codIsin") String codIsin, @Param("ctaInv") String ctaInv);


    @Query(value = "SELECT  " +
            "        CTAXCLI.OPL_INFOCLIENTE_TBL_NUM_TIT as numTit " +
            "FROM    OPL_TMP_MAEDCV_DOWN_TBL MAEDCV " +
            "INNER JOIN OPL_HIS_CTAINV_MEDIUM_TBL CTAINV on ltrim(MAEDCV.CTA_INV, 0) = ltrim(CTAINV.NUM_CTA, 0) " +
            "INNER JOIN OPL_HIS_CTAINVXCLI_MEDIUM_TBL CTAXCLI on CTAINV.NUM_CTA = CTAXCLI.OPL_CTAINV_TBL_NUM_CTA " +
            "WHERE  MAEDCV.NUM_CDT = :numCdt AND CTAXCLI.TITULARIDAD = 1" +
            "", nativeQuery = true)
    Long titularPrincipalByNumcdt(@Param("numCdt") Long numCdt);


    @Query(value = "SELECT  " +
            "        INFOCLIENTE.OPL_TIPID_TBL_COD_ID as tipId, " +
            "        CTAINV.OPL_INFOCLIENTE_TBL_NUM_TIT  as numTit, " +
            "        INFOCLIENTE.NOM_TIT as nomTit, " +
            "        CTAINV.TITULARIDAD as titularidad, " +
            "        CTAINV.OPL_CTAINV_TBL_NUM_CTA  as ctaInv " +
            "FROM  OPL_HIS_CTAINVXCLI_MEDIUM_TBL CTAINV " +
            "INNER JOIN OPL_HIS_INFOCLIENTE_LARGE_TBL INFOCLIENTE ON CTAINV.OPL_INFOCLIENTE_TBL_NUM_TIT = INFOCLIENTE.NUM_TIT " +
            "WHERE  CTAINV.OPL_CTAINV_TBL_NUM_CTA = :ctaInv" +
            "", nativeQuery = true)
    List<TitularCtaInvDTO> titulares(@Param("ctaInv") Long ctaInv);

    @Query(value = "SELECT  * " +
            "FROM OPL_SAL_PG_DOWN_TBL SALPG " +
            "   WHERE SALPG.TIP_POSICION <> 2 " +
            "   AND SALPG.TIP_POSICION <> 4 " +
            "   AND SALPG.FACTOR_OPL <> SALPG.FACTOR_DCVSA" +
            "", nativeQuery = true)
    List<SalPgDownEntity> cdtsNotReconcilied();

    @Query(value = "SELECT   " +
            "        SALPG.ITEM as item," +
            "        SALPG.NRO_OFICINA as nroOficina," +
            "        SALPG.DEPOSITANTE as depositante," +
            "        SALPG.NUM_CDT as numCdt," +
            "        SALPG.COD_ISIN as codIsin," +
            "        SALPG.CTA_INV as ctaInv," +
            "        SALPG.COD_ID as codId," +
            "        SALPG.NUM_TIT as numTit," +
            "        SALPG.NOM_TIT as nomTit," +
            "        SALPG.FECHA_EMI as fechaEmi," +
            "        SALPG.FECHA_VEN as fechaVen," +
            "        SALPG.FECHA_PROX_PG as fechaProxPg," +
            "        SALPG.TIP_PLAZO as tipPlazo," +
            "        SALPG.PLAZO as plazo," +
            "        SALPG.TIP_BASE as tipBase," +
            "        SALPG.TIP_PERIODICIDAD as tipPeriodicidad," +
            "        SALPG.TIP_TASA as tipTasa," +
            "        SALPG.TASA_EFE as tasaEfe," +
            "        SALPG.TASA_NOM as tasaNom," +
            "        SALPG.SPREAD as spread," +
            "        SALPG.VALOR_NOMINAL as valorNominal," +
            "        SALPG.INT_BRUTO as intBruto," +
            "        SALPG.RTE_FTE as rteFte," +
            "        SALPG.INT_NETO as intNeto, " +
            "        SALPG.CAP_PG as capPg, " +
            "        SALPG.TOTAL_PAGAR as totalPagar," +
            "        SALPG.TIP_POSICION as tipPosicion," +
            "        SALPG.FACTOR_DCVSA as factorDcvsa," +
            "        SALPG.FACTOR_OPL as factorOpl," +
            "        SALPG.COD_PROD as codProd," +
            "        SALPG.ESTADO as estado," +
            "        SALPG.FECHA as fecha," +
            "        TIPPOSICION.DESC_POSICION as descPosicion," +
            "        CASE " +
            "            WHEN SALPG.FACTOR_OPL <> SALPG.FACTOR_DCVSA THEN 'NO CONCILIADO' " +
            "            WHEN AJUSLIQDIA.FACTOR_DCVSA = SALPG.FACTOR_DCVSA THEN 'DECEVAL' " +
            "            WHEN AJUSLIQDIA.FACTOR_OPL = SALPG.FACTOR_OPL THEN 'OPALO' " +
            "            ELSE 'CONCILIADO'  " +
            "        END as conciliacion " +
            "FROM    OPL_SAL_PG_DOWN_TBL SALPG " +
            "        INNER JOIN OPL_PAR_TIPPOSICION_DOWN_TBL TIPPOSICION ON SALPG.TIP_POSICION = TIPPOSICION.TIP_POSICION " +
            "        LEFT JOIN OPL_HIS_AJUSLIQDIA_LARGE_TBL AJUSLIQDIA ON SALPG.COD_ISIN = AJUSLIQDIA.OPL_PG_TBL_COD_ISIN " +
            "                                                            AND SALPG.CTA_INV = AJUSLIQDIA.OPL_PG_TBL_CTA_INV " +
            "                                                            AND SALPG.NUM_CDT = AJUSLIQDIA.OPL_PG_TBL_NUM_CDT " +
            "   WHERE SALPG.TIP_POSICION <> 2 " +
            "   AND SALPG.TIP_POSICION <> 4 " +
            "   AND SALPG.FACTOR_OPL = SALPG.FACTOR_DCVSA" +
            "", nativeQuery = true)
    List<ColumnsReportPgWeekly> cdtsReconcilied();

    @Query(value = "SELECT DISTINCT NRO_OFICINA " +
            "FROM USR_OPL.OPL_SAL_PG_DOWN_TBL SALPG " +
            "   WHERE SALPG.TIP_POSICION <> 2 " +
            "   AND SALPG.TIP_POSICION <> 4 " +
            "ORDER BY NRO_OFICINA" +
            "", nativeQuery = true)
    List<Long> nroOficinasSorted();

    @Transactional
    @Modifying
    @Query(value = "update SalPgDownEntity pg set pg.factorOpl = :factor where pg.item = :item")
    void updateFactorValue(@Param("factor") BigDecimal factor, @Param("item") Long item);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update SalPgDownEntity pg set pg.estado = :estado where pg.numCdt = :numCdt")
    void updateStateValue(@Param("numCdt") Long numCdt, @Param("estado") Integer estado);

    void deleteAllByFactorOplGreaterThan(BigDecimal value);

    Long countByFactorOplEquals(BigDecimal value);

    List<SalPgDownEntity> findAllByEstadoAndCodProdIsBetween(Integer estado, Integer codProd, Integer codProdTwo);

    List<SalPgDownEntity> findAllByNumCdtNotInAndNumCdtInAndCodProdIsBetween(List<Long> numCdt, List<Long> numCdtDig, Integer codProd, Integer codProdTwo);

    List<SalPgDownEntity> findAllByEstadoAndCodProdIsNot(Integer estado, Integer codProd);

    @Transactional
    @Modifying
    @Query(value = "update SalPgDownEntity salpg set salpg.estado = 5 where salpg.estado = :estado")
    void updateStatusReinvested(@Param("estado") Integer estado);

    @Transactional
    @Modifying
    void deleteAllByEstado(Integer valor);

    SalPgDownEntity findByNumCdtAndCodIsinAndCtaInvAndNumTit(Long numCdt, String codIsin, String ctaInv, String numTit);

    SalPgDownEntity findByNumCdtAndCodIsinAndNumTit(Long numCdt, String codIsin, String numTit);
}
