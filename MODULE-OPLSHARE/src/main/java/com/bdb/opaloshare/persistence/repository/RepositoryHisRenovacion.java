package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisRenovacion;
import com.bdb.opaloshare.persistence.entity.HisRenovacionPk;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RepositoryHisRenovacion extends JpaRepository<HisRenovacion, HisRenovacionPk> {


    @Transactional
    @Modifying
    @Query(value = "UPDATE HisRenovacion r set r.oplEstadosTblTipEstado = 3, r.nuevoNumCdt = :nuevoNumCdt WHERE r.oplCdtxctainvTblNumCdt = :anteriorNumCdt ")
    Integer updateStateCdt(@Param(value = "nuevoNumCdt") Long nuevoNumCdt, @Param(value = "anteriorNumCdt") Long anteriorNumCdt);


    @Query(value = "SELECT h FROM HisRenovacion h where h.oplCdtxctainvTblNumCdt = ?1 AND h.oplEstadosTblTipEstado = 1 ")
    HisRenovacion findBynumCdtAndREnovacion(Long numCdt);

    HisRenovacion findByOplCdtxctainvTblNumCdt(Long numCdt);

    @Query(value = "SELECT * " +
            "FROM OPL_HIS_RENOVACION_DOWN_TBL RENOVACION " +
            "INNER JOIN OPL_HIS_CDTXCTAINV_LARGE_TBL CDTXCTAINV ON RENOVACION.OPL_CDTXCTAINV_TBL_NUM_CDT = CDTXCTAINV.NUM_CDT " +
            "INNER JOIN OPL_HIS_CTAINVXCLI_MEDIUM_TBL CTAINVXCLI on CDTXCTAINV.OPL_CTAINV_TBL_NUM_CTA = CTAINVXCLI.OPL_CTAINV_TBL_NUM_CTA " +
            "INNER JOIN OPL_HIS_INFOCLIENTE_LARGE_TBL CLIENTE on CTAINVXCLI.OPL_INFOCLIENTE_TBL_NUM_TIT = CLIENTE.NUM_TIT " +
            "WHERE CLIENTE.NUM_TIT = :numTit and RENOVACION.OPL_ESTADOS_TBL_TIP_ESTADO=1" +
            "", nativeQuery = true)
    HisRenovacion findBynumTitular(@Param(value = "numTit" ) Long numTit);
}
