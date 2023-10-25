package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisTransaccionesEntity;
import com.bdb.opaloshare.persistence.entity.HisTransaccionesPk;
import com.bdb.opaloshare.persistence.model.trad.CruceHisCdtRenautDigDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositoryTransacciones extends JpaRepository<HisTransaccionesEntity, Long> {

    @Query(value = "SELECT        " +
            "               TRANSACCIONES.ID_CLIENTE as cliente," +
            "               TRANSACCIONES.OPL_CDTXCTAINV_TBL_NUM_CDT as numCdt," +
            "               TRANSACCIONES.OPL_TIPTRANS_TBL_TIP_TRANSACCION as tipTransaccion," +
            "               TRANSACCIONES.OPL_TIPPROCESO_TBL_TIP_PROCESO as proceso," +
            "               TRANSACCIONES.OPL_TIPTRANS_TBL_TIP_TRANSACCION as transaccion," +
            "               TRANSACCIONES.UNID_DESTINO as unidadDestino," +
            "               TRANSACCIONES.UNID_ORIGEN as unidadOrigen," +
            "               TRANSACCIONES.VALOR as valor," +
            "               TRANSACCIONES.CAPITAL_GMF as gmfCapital," +
            "               Round((TRANSACCIONES.VALOR / 1000) * (4),0) as gmf," +
            "               INFOCDT.COD_TRN as codTrn," +
            "               INFOCDT.OPL_TIPPLAZO_TBL_TIP_PLAZO as oplTipPlazo," +
            "               INFOCDT.PLAZO as plazo," +
            "               SALPG.COD_PROD as codProd, " +
            "               INFOCDT.OPL_OFICINA_TBL_NRO_OFICINA as oficina " +
            "FROM OPL_HIS_TRANSACCIONES_LARGE_TBL TRANSACCIONES " +
            "            INNER JOIN OPL_HIS_CDTXCTAINV_LARGE_TBL CDTXCTAINV ON TRANSACCIONES.OPL_CDTXCTAINV_TBL_NUM_CDT = CDTXCTAINV.NUM_CDT" +
            "            INNER JOIN OPL_HIS_INFOCDT_LARGE_TBL INFOCDT ON CDTXCTAINV.NUM_CDT = INFOCDT.OPL_CDTXCTAINV_TBL_NUM_CDT" +
            "            INNER JOIN OPL_SAL_PG_DOWN_TBL SALPG ON TRANSACCIONES.OPL_CDTXCTAINV_TBL_NUM_CDT = SALPG.NUM_CDT" +
            "                  AND SALPG.ESTADO != 5 " +
            "            WHERE TRANSACCIONES.OPL_CDTXCTAINV_TBL_NUM_CDT = :numCdt " +
            "                   AND TRANSACCIONES.OPL_CDTXCTAINV_TBL_COD_ISIN = :codIsin" +
            "                   AND TRANSACCIONES.OPL_CDTXCTAINV_TBL_OPL_CTAINV_TBL_NUM_CTA = :ctaInv" +
            "", nativeQuery = true)
    List<CruceHisCdtRenautDigDto> findAllByCdtsCaOficina(@Param("numCdt") Long numCdt, @Param("codIsin") String codIsin,
                                                         @Param("ctaInv") Long ctaInv);


    @Query(value = "SELECT        " +
            "               TRANSACCIONES.CAPITAL_GMF " +
            "FROM OPL_HIS_TRANSACCIONES_LARGE_TBL TRANSACCIONES " +
            "            WHERE TRANSACCIONES.OPL_CDTXCTAINV_TBL_NUM_CDT = :numCdt " +
            "                   AND TRANSACCIONES.OPL_CDTXCTAINV_TBL_COD_ISIN = :codIsin" +
            "                   AND TRANSACCIONES.OPL_CDTXCTAINV_TBL_OPL_CTAINV_TBL_NUM_CTA = :ctaInv" +
            "", nativeQuery = true)
    List<Integer> findGmfCapital(@Param("numCdt") Long numCdt, @Param("codIsin") String codIsin, @Param("ctaInv") Long ctaInv);

}
