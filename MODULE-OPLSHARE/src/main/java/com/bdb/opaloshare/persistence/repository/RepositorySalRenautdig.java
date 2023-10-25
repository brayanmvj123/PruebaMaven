package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.controller.service.interfaces.CrucePatrimonioRenautDTO;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RepositorySalRenautdig extends JpaRepository<SalRenautdigEntity, Serializable> {

    @Query(value = "SELECT\r\n" +
            "       maedcv.num_cdt                                                                AS numCdt, " +
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
            "       (maedcv.val_cdt / porcentaje.suma)                                            AS nroPor, " +
            "       HISCDTS.OPL_OFICINA_TBL_NRO_OFICINA                                           AS oficina " +
            "       FROM    opl_car_derpatridep_down_tbl derpatri " +
            "           INNER JOIN opl_tmp_maedcv_down_tbl maedcv   ON maedcv.cod_isin = derpatri.isin " +
            "                                                    AND (maedcv.NUM_CDT IN (SELECT OHCLT.NUM_CDT FROM OPL_HIS_CTRCDTS_LARGE_TBL OHCLT) " +
            "                                                    AND maedcv.NUM_CDT IN (  SELECT  OHCLT.NUM_CDT " +
            "                                                                            FROM    OPL_HIS_CTRCDTS_LARGE_TBL OHCLT " +
            "                                                                            WHERE   ((   OHCLT.OPL_CONTROLES_TBL_TIP_CONTROL = 1 " +
            "                                                                                    AND OHCLT.NOVEDAD_V = 1 " +
            "                                                                                    AND TO_NUMBER(derpatri.REC_CAP) != 0)) )) " +
            "        INNER JOIN opl_his_clixcdt_large_tbl clixcdt ON clixcdt.opl_cdts_tbl_num_cdt = maedcv.num_cdt  " +
            "                                        AND derpatri.id_tit = ltrim(maedcv.id_tit, '0') " +
            "                                        AND clixcdt.opl_clientes_tbl_num_tit = ltrim(maedcv.id_tit, '0') " +
            "                                        AND clixcdt.tip_titular = 1 " +
            "                                        AND maedcv.cta_inv = derpatri.cta_inv " +
            "        INNER JOIN OPL_HIS_CDTS_LARGE_TBL HISCDTS on HISCDTS.NUM_CDT = maedcv.NUM_CDT , " +
            "        ( " +
            "         SELECT SUM(mae.val_cdt)       suma, " +
            "                ltrim(mae.id_tit, '0') id, " +
            "                mae.cod_isin, " +
            "                mae.cta_inv " +
            "         FROM   opl_car_derpatridep_down_tbl patri " +
            "                INNER JOIN opl_tmp_maedcv_down_tbl mae  ON mae.cod_isin = patri.isin " +
            "                                                        AND ltrim(mae.id_tit, '0') = patri.id_tit " +
            "                                                       AND mae.cta_inv = patri.cta_inv " +
            "        GROUP BY mae.id_tit, " +
            "                  mae.cod_isin, " +
            "                  mae.cta_inv " +
            "        ) porcentaje " +
            "        WHERE   derpatri.id_tit = porcentaje.id " +
            "           AND derpatri.isin = porcentaje.cod_isin " +
            "           AND derpatri.cta_inv = porcentaje.cta_inv", nativeQuery = true)
    List<CrucePatrimonioRenautDTO> loadInfoFromCrossRenautData();

    @Modifying
    @Transactional
    @Query(value = "update SalRenautdigEntity salRenaut set salRenaut.estadoV = 'C' where salRenaut.numCdt = :numCdt ")
    void actualizarEstadoRenovacion(@Param("numCdt") Long numCdt);

    @Query("select f from SalRenautdigEntity f WHERE f.numCdt = :numCdt")
    SalRenautdigEntity findByNumCdt(Long numCdt);

    boolean existsByCodIsinAndNumCdtAndNumTit(String codIsin, Long numCdt, String numTit);

}
