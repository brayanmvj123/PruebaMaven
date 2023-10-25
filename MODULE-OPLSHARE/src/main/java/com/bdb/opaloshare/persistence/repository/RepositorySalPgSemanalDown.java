package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.entity.SalPgSemanalDownEntity;
import com.bdb.opaloshare.persistence.model.columnselected.ColumnsReportPgWeekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RepositorySalPgSemanalDown extends JpaRepository<SalPgSemanalDownEntity, Long> {

    @Query(value = "SELECT  MAEDCV.OFICINA as nroOficina," +
            "        DEPATRIEMI.DEPOSITANTE_V as depositante," +
            "        MAEDCV.NUM_CDT as numCdt," +
            "        MAEDCV.COD_ISIN as codIsin," +
            "        DEPATRIEMI.CTA_INV as ctaInv," +
            "        MAEDCV.OPL_TIPID_TBL_COD_ID as codId," +
            "        DEPATRIEMI.ID_TIT as numTit," +
            "        DEPATRIEMI.NOM_TIT as nomTit," +
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
            "        MAEDCV.COD_PROD as codProd," +
            "        round(((DEPATRIEMI.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)  AS intBruto," +
            "        round(((DEPATRIEMI.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)   AS rteFte," +
            "        ((round(((DEPATRIEMI.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) - " +
            "         round(((DEPATRIEMI.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) AS intNeto," +
            "        round(((DEPATRIEMI.rec_cap / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)   AS capPg," +
            "        ((round(((DEPATRIEMI.rec_rend / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) - " +
            "         round(((DEPATRIEMI.rte_fte / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) + " +
            "        (round(((DEPATRIEMI.rec_cap / 10000) * (maedcv.val_cdt / porcentaje.suma)), 0)) AS totalPagar," +
            "        MAEDCV.OPL_TIPPOSICION_TBL_TIP_POSICION as tipPosicion," +
            "        DEPATRIEMI.FACTOR as factorDcvsa," +
            "        0 as enviado," +
            "        0 as factorOpl " +
            "FROM    OPL_TMP_MAEDCV_DOWN_TBL MAEDCV " +
            "        INNER JOIN OPL_ACU_DERPATRIEMI_DOWN_TBL DEPATRIEMI ON DEPATRIEMI.ISIN = MAEDCV.COD_ISIN " +
            "                                                            AND DEPATRIEMI.ID_TIT = LTRIM(MAEDCV.ID_TIT,'0') " +
            "                                                            AND LTRIM(DEPATRIEMI.CTA_INV,'0') = LTRIM(MAEDCV.CTA_INV,'0')," +
            "        ( " +
            "            SELECT  SUM(mae.val_cdt)       suma," +
            "                    ltrim(mae.id_tit, '0') id," +
            "                    mae.cod_isin," +
            "                    mae.cta_inv " +
            "            FROM   OPL_ACU_DERPATRIEMI_DOWN_TBL patri " +
            "                   INNER JOIN opl_tmp_maedcv_down_tbl mae  ON mae.cod_isin = patri.isin " +
            "                                                           AND ltrim(mae.id_tit, '0') = patri.id_tit " +
            "                                                           AND mae.cta_inv = patri.cta_inv " +
            "                   GROUP BY     mae.id_tit," +
            "                                mae.cod_isin," +
            "                                mae.cta_inv" +
            "        ) porcentaje " +
            "WHERE   DEPATRIEMI.id_tit = porcentaje.id " +
            "        AND DEPATRIEMI.isin = porcentaje.cod_isin " +
            "        AND DEPATRIEMI.cta_inv = porcentaje.cta_inv", nativeQuery = true)
    List<ColumnsReportPgWeekly> cruceReportFechaVenOfic();


    @Query(value = "SELECT  " +
            "        SALPGSEMANAL.ITEM as item," +
            "        SALPGSEMANAL.NRO_OFICINA as nroOficina," +
            "        SALPGSEMANAL.DEPOSITANTE as depositante," +
            "        SALPGSEMANAL.NUM_CDT as numCdt," +
            "        SALPGSEMANAL.COD_ISIN as codIsin," +
            "        SALPGSEMANAL.CTA_INV as ctaInv," +
            "        SALPGSEMANAL.COD_ID as codId," +
            "        SALPGSEMANAL.NUM_TIT as numTit," +
            "        SALPGSEMANAL.NOM_TIT as nomTit," +
            "        SALPGSEMANAL.FECHA_EMI as fechaEmi," +
            "        SALPGSEMANAL.FECHA_VEN as fechaVen," +
            "        SALPGSEMANAL.FECHA_PROX_PG as fechaProxPg," +
            "        SALPGSEMANAL.TIP_PLAZO as tipPlazo," +
            "        SALPGSEMANAL.PLAZO as plazo," +
            "        SALPGSEMANAL.TIP_BASE as tipBase," +
            "        SALPGSEMANAL.TIP_PERIODICIDAD as tipPeriodicidad," +
            "        SALPGSEMANAL.TIP_TASA as tipTasa," +
            "        SALPGSEMANAL.TASA_EFE as tasaEfe," +
            "        SALPGSEMANAL.TASA_NOM as tasaNom," +
            "        SALPGSEMANAL.SPREAD as spread," +
            "        SALPGSEMANAL.VALOR_NOMINAL as valorNominal," +
            "        SALPGSEMANAL.INT_BRUTO as intBruto," +
            "        SALPGSEMANAL.RTE_FTE as rteFte," +
            "        SALPGSEMANAL.INT_NETO as intNeto, " +
            "        SALPGSEMANAL.CAP_PG as capPg, " +
            "        SALPGSEMANAL.TOTAL_PAGAR as totalPagar," +
            "        SALPGSEMANAL.TIP_POSICION as tipPosicion," +
            "        SALPGSEMANAL.FACTOR_DCVSA as factorDcvsa," +
            "        SALPGSEMANAL.FACTOR_OPL as factorOpl," +
            "        SALPGSEMANAL.COD_PROD as codProd," +
            "        SALPGSEMANAL.ESTADO as estado," +
            "        SALPGSEMANAL.FECHA as fecha," +
            "        SALPGSEMANAL.ENVIADO as enviado," +
            "        TIPPOSICION.DESC_POSICION as descPosicion," +
            "        CASE " +
            "            WHEN SALPGSEMANAL.FACTOR_OPL <> SALPGSEMANAL.FACTOR_DCVSA THEN 'NO CONCILIADO'\n" +
            "            WHEN AJUSLIQSEM.FACTOR_DCVSA = SALPGSEMANAL.FACTOR_DCVSA THEN 'DECEVAL'\n" +
            "            WHEN AJUSLIQSEM.FACTOR_OPL = SALPGSEMANAL.FACTOR_OPL THEN 'OPALO'\n" +
            "            ELSE 'CONCILIADO'  " +
            "        END as conciliacion " +
            "FROM    OPL_SAL_PGSEMANAL_DOWN_TBL SALPGSEMANAL " +
            "        INNER JOIN OPL_PAR_TIPPOSICION_DOWN_TBL TIPPOSICION ON SALPGSEMANAL.TIP_POSICION = TIPPOSICION.TIP_POSICION" +
            "        LEFT JOIN OPL_HIS_AJUSLIQSEM_LARGE_TBL AJUSLIQSEM ON SALPGSEMANAL.COD_ISIN = AJUSLIQSEM.OPL_PG_TBL_COD_ISIN" +
            "                                                            AND SALPGSEMANAL.CTA_INV = AJUSLIQSEM.OPL_PG_TBL_CTA_INV" +
            "                                                            AND SALPGSEMANAL.NUM_CDT = AJUSLIQSEM.OPL_PG_TBL_NUM_CDT " +
            "ORDER BY    NUM_CDT" +
            "", nativeQuery = true)
    List<ColumnsReportPgWeekly> cdtsConciliacion();


    @Query(value = "SELECT  * " +
            "FROM    OPL_SAL_PGSEMANAL_DOWN_TBL SALPGSEMANAL " +
            "   WHERE SALPGSEMANAL.TIP_POSICION <> 2 " +
            "   AND SALPGSEMANAL.TIP_POSICION <> 4 " +
            "   AND SALPGSEMANAL.FACTOR_OPL <> SALPGSEMANAL.FACTOR_DCVSA" +
            "", nativeQuery = true)
    List<SalPgSemanalDownEntity> cdtsNotConcilied();

    @Query(value = "SELECT   " +
            "        SALPGSEMANAL.ITEM as item," +
            "        SALPGSEMANAL.NRO_OFICINA as nroOficina," +
            "        SALPGSEMANAL.DEPOSITANTE as depositante," +
            "        SALPGSEMANAL.NUM_CDT as numCdt," +
            "        SALPGSEMANAL.COD_ISIN as codIsin," +
            "        SALPGSEMANAL.CTA_INV as ctaInv," +
            "        SALPGSEMANAL.COD_ID as codId," +
            "        SALPGSEMANAL.NUM_TIT as numTit," +
            "        SALPGSEMANAL.NOM_TIT as nomTit," +
            "        SALPGSEMANAL.FECHA_EMI as fechaEmi," +
            "        SALPGSEMANAL.FECHA_VEN as fechaVen," +
            "        SALPGSEMANAL.FECHA_PROX_PG as fechaProxPg," +
            "        SALPGSEMANAL.TIP_PLAZO as tipPlazo," +
            "        SALPGSEMANAL.PLAZO as plazo," +
            "        SALPGSEMANAL.TIP_BASE as tipBase," +
            "        SALPGSEMANAL.TIP_PERIODICIDAD as tipPeriodicidad," +
            "        SALPGSEMANAL.TIP_TASA as tipTasa," +
            "        SALPGSEMANAL.TASA_EFE as tasaEfe," +
            "        SALPGSEMANAL.TASA_NOM as tasaNom," +
            "        SALPGSEMANAL.SPREAD as spread," +
            "        SALPGSEMANAL.VALOR_NOMINAL as valorNominal," +
            "        SALPGSEMANAL.INT_BRUTO as intBruto," +
            "        SALPGSEMANAL.RTE_FTE as rteFte," +
            "        SALPGSEMANAL.INT_NETO as intNeto, " +
            "        SALPGSEMANAL.CAP_PG as capPg, " +
            "        SALPGSEMANAL.TOTAL_PAGAR as totalPagar," +
            "        SALPGSEMANAL.TIP_POSICION as tipPosicion," +
            "        SALPGSEMANAL.FACTOR_DCVSA as factorDcvsa," +
            "        SALPGSEMANAL.FACTOR_OPL as factorOpl," +
            "        SALPGSEMANAL.COD_PROD as codProd," +
            "        SALPGSEMANAL.ESTADO as estado," +
            "        SALPGSEMANAL.FECHA as fecha," +
            "        SALPGSEMANAL.ENVIADO as enviado," +
            "        TIPPOSICION.DESC_POSICION as descPosicion," +
            "        CASE " +
            "            WHEN SALPGSEMANAL.FACTOR_OPL <> SALPGSEMANAL.FACTOR_DCVSA THEN 'NO CONCILIADO'\n" +
            "            WHEN AJUSLIQSEM.FACTOR_DCVSA = SALPGSEMANAL.FACTOR_DCVSA THEN 'DECEVAL'\n" +
            "            WHEN AJUSLIQSEM.FACTOR_OPL = SALPGSEMANAL.FACTOR_OPL THEN 'OPALO'\n" +
            "            ELSE 'CONCILIADO'  " +
            "        END as conciliacion " +
            "FROM    OPL_SAL_PGSEMANAL_DOWN_TBL SALPGSEMANAL " +
            "        INNER JOIN OPL_PAR_TIPPOSICION_DOWN_TBL TIPPOSICION ON SALPGSEMANAL.TIP_POSICION = TIPPOSICION.TIP_POSICION" +
            "        LEFT JOIN OPL_HIS_AJUSLIQSEM_LARGE_TBL AJUSLIQSEM ON SALPGSEMANAL.COD_ISIN = AJUSLIQSEM.OPL_PG_TBL_COD_ISIN" +
            "                                                            AND SALPGSEMANAL.CTA_INV = AJUSLIQSEM.OPL_PG_TBL_CTA_INV" +
            "                                                            AND SALPGSEMANAL.NUM_CDT = AJUSLIQSEM.OPL_PG_TBL_NUM_CDT " +
            "   WHERE SALPGSEMANAL.TIP_POSICION <> 2 " +
            "   AND SALPGSEMANAL.TIP_POSICION <> 4 " +
            "   AND SALPGSEMANAL.FACTOR_OPL = SALPGSEMANAL.FACTOR_DCVSA" +
            "", nativeQuery = true)
    List<ColumnsReportPgWeekly> cdtsReconcilied();

    @Query(value = "SELECT DISTINCT NRO_OFICINA " +
            "FROM OPL_SAL_PGSEMANAL_DOWN_TBL SALPGSEMANAL " +
            "   WHERE SALPGSEMANAL.TIP_POSICION <> 2 " +
            "   AND SALPGSEMANAL.TIP_POSICION <> 4 " +
            "   AND SALPGSEMANAL.ENVIADO = 0 " +
            "ORDER BY NRO_OFICINA" +
            "", nativeQuery = true)
    List<Long> nroOficinasSorted();

    @Transactional
    @Modifying
    @Query(value = "update SalPgSemanalDownEntity pgSemanal set pgSemanal.factorOpl = :factor where pgSemanal.item = :item")
    void updateFactorValue(@Param("factor") BigDecimal factor, @Param("item") Long item);

    @Transactional
    @Modifying
    @Query(value = "update SalPgSemanalDownEntity pgSemanal set pgSemanal.estado = :estado where pgSemanal.numCdt = :numCdt")
    void updateStateValue(@Param("numCdt") Long numCdt, @Param("estado") Integer estado);

    @Transactional
    @Modifying
    @Query(value = "update SalPgSemanalDownEntity pgSemanal set pgSemanal.enviado = 1 where pgSemanal.nroOficina = :nroOficina")
    void updateSent(@Param("nroOficina") Long nroOficina);

    void deleteAllByFactorOplGreaterThan(BigDecimal value);

    Long countByFactorOplEquals(BigDecimal value);


    SalPgSemanalDownEntity findByNumCdtAndCodIsinAndCtaInvAndNumTit(Long numCdt, String codIsin, String ctaInv, String numTit);
}
