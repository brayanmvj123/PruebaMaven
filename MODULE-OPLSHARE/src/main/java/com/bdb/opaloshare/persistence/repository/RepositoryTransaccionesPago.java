package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.controller.service.interfaces.CDTVencidoDTO;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.cancelacionaut.InfoCancelAutDig;
import com.bdb.opaloshare.persistence.model.trad.CruceHisCdtRenautDigDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositoryTransaccionesPago extends JpaRepository<HisTranpgEntity, Serializable> {

    @Query(value = "select histranpg.id_cliente as cliente, " +
            "   histranpg.opl_cdts_tbl_num_cdt as numCdt, " +
            "   histranpg.opl_tiptrans_tbl_tip_transaccion as tipTransaccion, " +
            "   histranpg.proceso as proceso, " +
            "   histranpg.tip_tran as transaccion, " +
            "   histranpg.unid_destino as unidadDestino, " +
            "   histranpg.unid_origen as unidadOrigen, " +
            "   histranpg.valor as valor, " +
            "   SALPG.COD_PROD as codProd, " +
            "   Round((histranpg.valor / 1000) * (4),0) as gmf, " +
            "   cdt.cod_trn as codTrn, " +
            "   cdt.opl_tipplazo_tbl_tip_plazo as oplTipPlazo, " +
            "   cdt.plazo as plazo, " +
            "   cdt.opl_oficina_tbl_nro_oficina as oficina " +
            "from OPL_HIS_TRANPG_LARGE_TBL HISTRANPG " +
            "   inner join OPL_HIS_CDTS_LARGE_TBL cdt on histranpg.opl_cdts_tbl_num_cdt = cdt.num_cdt " +
            "   inner join OPL_SAL_PG_DOWN_TBL SALPG on histranpg.opl_cdts_tbl_num_cdt = SALPG.num_cdt " +
            "               and SALPG.ESTADO < 4 " +
            "where (histranpg.proceso = '3' and (histranpg.opl_tiptrans_tbl_tip_transaccion = '1'" +
            "   or histranpg.opl_tiptrans_tbl_tip_transaccion = '2' " +
            "   or histranpg.opl_tiptrans_tbl_tip_transaccion = '6' " +
            "   or histranpg.opl_tiptrans_tbl_tip_transaccion = '7')" +
            "   or histranpg.proceso = '4' and (histranpg.opl_tiptrans_tbl_tip_transaccion = '1'" +
            "   or histranpg.opl_tiptrans_tbl_tip_transaccion = '2' " +
            "   or histranpg.opl_tiptrans_tbl_tip_transaccion = '6' " +
            "   or histranpg.opl_tiptrans_tbl_tip_transaccion = '7') " +
            "   or histranpg.proceso = '5' and histranpg.opl_tiptrans_tbl_tip_transaccion = '8'" +
            "   or HISTRANPG.proceso = '7' and (HISTRANPG.opl_tiptrans_tbl_tip_transaccion = '1'" +
            "   or HISTRANPG.opl_tiptrans_tbl_tip_transaccion = '2'" +
            "   or HISTRANPG.opl_tiptrans_tbl_tip_transaccion = '6'))", nativeQuery = true)
    List<CruceHisCdtRenautDigDto> findAllByCdtsCadi();


    /*@Query(value = "select histranpg.id_cliente as cliente, " +
            "   histranpg.opl_cdts_tbl_num_cdt as numCdt, " +
            "   histranpg.opl_tiptrans_tbl_tip_transaccion as tipTransaccion, " +
            "   histranpg.proceso as proceso, " +
            "   histranpg.tip_tran as transaccion, " +
            "   histranpg.unid_destino as unidadDestino, " +
            "   histranpg.unid_origen as unidadOrigen, " +
            "   histranpg.valor as valor, " +
            "   Round((histranpg.valor / 1000) * (4),0) as gmf, " +
            "   cdt.cod_trn as codTrn, " +
            "   cdt.opl_tipplazo_tbl_tip_plazo as oplTipPlazo, " +
            "   cdt.plazo as plazo, " +
            "   cdt.opl_oficina_tbl_nro_oficina as oficina " +
            "from OPL_HIS_TRANPG_LARGE_TBL HISTRANPG " +
            "   inner join OPL_HIS_CDTS_LARGE_TBL cdt on histranpg.opl_cdts_tbl_num_cdt = cdt.num_cdt " +
            "where (histranpg.proceso = '3' and histranpg.opl_tiptrans_tbl_tip_transaccion = '7'" +
            "   or histranpg.proceso = '3' and histranpg.opl_tiptrans_tbl_tip_transaccion = '1'" +
            "   or histranpg.proceso = '3' and histranpg.opl_tiptrans_tbl_tip_transaccion = '2' " +
            "   or histranpg.proceso = '4' and histranpg.opl_tiptrans_tbl_tip_transaccion = '1'" +
            "   or histranpg.proceso = '4' and histranpg.opl_tiptrans_tbl_tip_transaccion = '2' " +
            "   or  histranpg.proceso = '4' and histranpg.opl_tiptrans_tbl_tip_transaccion = '7' " +
            "   or histranpg.proceso = '5' and histranpg.opl_tiptrans_tbl_tip_transaccion = '8')", nativeQuery = true)
    List<CruceHisCdtRenautDigDto> findAllByCdts();*/


//    @Query(value = "select histranpg.id_cliente as cliente, histranpg.opl_cdts_tbl_num_cdt as numCdt, histranpg.opl_tiptrans_tbl_tip_transaccion as tipTransaccion, " +
//            "   histranpg.proceso as proceso, histranpg.tip_tran as transaccion, histranpg.unid_destino as unidadDestino, histranpg.unid_origen as unidadOrigen, " +
//            "   histranpg.valor as valor, Round((histranpg.valor / 1000) * (4),0) as gmf, cdt.cod_trn as codTrn, cdt.opl_tipplazo_tbl_tip_plazo as oplTipPlazo, " +
//            "   cdt.plazo as plazo, cdt.opl_oficina_tbl_nro_oficina as oficina from OPL_HIS_TRANPG_LARGE_TBL HISTRANPG " +
//            "   inner join OPL_HIS_CDTS_LARGE_TBL cdt on histranpg.opl_cdts_tbl_num_cdt = cdt.num_cdt " +
//            "   inner join OPL_SAL_RENAUTDIG_DOWN_TBL salRenaut on histranpg.opl_cdts_tbl_num_cdt = salRenaut.num_cdt and salrenaut.estado_v = 'C' " +
//            "   where histranpg.proceso = '3' and histranpg.opl_tiptrans_tbl_tip_transaccion = '7' " +
//            "   or  histranpg.proceso = '4' and histranpg.opl_tiptrans_tbl_tip_transaccion = '7' " +
//            "   or histranpg.proceso = '5' and histranpg.opl_tiptrans_tbl_tip_transaccion = '8'", nativeQuery = true)
//    List<CruceHisCdtRenautDigDto> findAllByCdtsCadi();

    @Transactional
    @Modifying
    @Query("update HisTranpgEntity tranpg set tranpg.unidOrigen = function('to_char', :newOfficeId) , tranpg.unidDestino = :newOfficeId " +
            "where function('ltrim', tranpg.unidOrigen, '0') = function('to_char', :oldOfficeId) " +
            "and function('ltrim', tranpg.unidDestino, '0') = :oldOfficeId")
    void updateOffices(@Param("newOfficeId") Integer newOfficeId, @Param("oldOfficeId") Integer oldOfficeId);

    @Query(value = "select  tranpg.valor," +
            "               tranpg.proceso " +
            "from HisTranpgEntity tranpg " +
            "     inner join HisClixCDTLarge clixcdt on tranpg.hisCDTSLargeEntity.numCdt = clixcdt.oplCdtsTblNumCdt " +
            "where tranpg.hisCDTSLargeEntity = :numCdt" +
            "       and (tranpg.proceso = '3' " +
            "       and (tranpg.oplTiptransTblTipTrasaccion = 1 or tranpg.oplTiptransTblTipTrasaccion = 2)" +
            "       or  (tranpg.proceso = '4' " +
            "       and (tranpg.oplTiptransTblTipTrasaccion = 1 or tranpg.oplTiptransTblTipTrasaccion = 2)))")
    List<InfoCancelAutDig> infoCancelAutDig(@Param("numCdt") Long numCdt);

    @Query(value = "from HisTranpgEntity tranpg where tranpg.hisCDTSLargeEntity.numCdt = :numCdt and tranpg.proceso = :proceso")
    HisTranpgEntity buscarTransaccionCdtDig(@Param("numCdt") String numCdt, @Param("proceso") String proceso);

    /*@Query(value = "select histranpg.id_cliente as cliente, " +
            "   histranpg.opl_cdts_tbl_num_cdt as numCdt, " +
            "   histranpg.opl_tiptrans_tbl_tip_transaccion as tipTransaccion, " +
            "   histranpg.proceso as proceso, " +
            "   histranpg.tip_tran as transaccion, " +
            "   histranpg.unid_destino as unidadDestino, " +
            "   histranpg.unid_origen as unidadOrigen, " +
            "   histranpg.valor as valor, " +
            "   Round((histranpg.valor / 1000) * (4),0) as gmf, " +
            "   cdt.cod_trn as codTrn, " +
            "   cdt.opl_tipplazo_tbl_tip_plazo as oplTipPlazo, " +
            "   cdt.plazo as plazo, " +
            "   cdt.opl_oficina_tbl_nro_oficina as oficina " +
            "from OPL_HIS_TRANPG_LARGE_TBL HISTRANPG " +
            "   inner join OPL_HIS_CDTS_LARGE_TBL cdt on histranpg.opl_cdts_tbl_num_cdt = cdt.num_cdt " +
            "where (histranpg.proceso = '3' and histranpg.opl_tiptrans_tbl_tip_transaccion = '7'" +
            "   or histranpg.proceso = '3' and histranpg.opl_tiptrans_tbl_tip_transaccion = '1'" +
            "   or histranpg.proceso = '3' and histranpg.opl_tiptrans_tbl_tip_transaccion = '2' " +
            "   or histranpg.proceso = '4' and histranpg.opl_tiptrans_tbl_tip_transaccion = '1'" +
            "   or histranpg.proceso = '4' and histranpg.opl_tiptrans_tbl_tip_transaccion = '2' " +
            "   or  histranpg.proceso = '4' and histranpg.opl_tiptrans_tbl_tip_transaccion = '7' " +
            "   or histranpg.proceso = '5' and histranpg.opl_tiptrans_tbl_tip_transaccion = '8')", nativeQuery = true)
    List<CruceHisCdtRenautDigDto> findAllByCdtsVen();*/

    @Query(value = "SELECT  " +
            "        MAEDCV. NUM_CDT as numCdt," +
            "        MAEDCV.NUM_FOL as tipProd," +
            "        MAEDCV.OPL_TIPID_TBL_COD_ID as tipId," +
            "        MAEDCV.ID_TIT as cliente," +
            "        MAEDCV.NOM_TIT as nomTit," +
            "        MAEDCV.OFICINA as nroOficina," +
            "        OFICINA.DESC_OFICINA as nomOficina," +
            "        SALPG.CAP_PG as capPg," +
            "        SALPG.INT_BRUTO as intTotal," +
            "        SALPG.RTE_FTE as rteFte," +
            "        SALPG.INT_NETO as intNeto " +
            "FROM    OPL_TMP_MAEDCV_DOWN_TBL MAEDCV " +
            "INNER JOIN OPL_SAL_PG_DOWN_TBL SALPG ON MAEDCV.NUM_CDT = SALPG.NUM_CDT " +
            "INNER JOIN OPL_PAR_OFICINA_DOWN_TBL OFICINA ON MAEDCV.OFICINA = OFICINA.NRO_OFICINA " +
            "WHERE  MAEDCV.NUM_CDT = :numCdt" +
            "        AND MAEDCV.OPL_TIPID_TBL_COD_ID = :tipId AND MAEDCV.ID_TIT = :numTit" +
            "        AND MAEDCV.NUM_CDT = :numCdt " +
            "        AND (:codIsin is null or MAEDCV.COD_ISIN = :codIsin) " +
            "        AND (:ctaInv is null or MAEDCV.CTA_INV = :ctaInv)" +
            "", nativeQuery = true)
    List<CDTVencidoDTO> cdtsVen(@Param("tipId") String tipId, @Param("numTit") String numTit, @Param("numCdt") Long numCdt,
                                @Param("codIsin") String codIsin, @Param("ctaInv") String ctaInv);
}
