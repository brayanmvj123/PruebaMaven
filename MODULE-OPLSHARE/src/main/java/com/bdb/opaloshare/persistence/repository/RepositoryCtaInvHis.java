package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.CtaInvHisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RepositoryCtaInvHis extends JpaRepository<CtaInvHisEntity, CtaInvHisEntity> {

    /*
     * ESTE REPOSITORIO PERTENECE A LA TABLA ACC_CTAINV_HIS_TBL
     */

    boolean existsByNumCta(String numCta);

    @Query(value = "SELECT  * " +
            "FROM OPL_HIS_CTAINV_MEDIUM_TBL CTAINV " +
            "WHERE LTRIM(CTAINV.NUM_CTA, 0) = LTRIM(:numCta, 0)" +
            "", nativeQuery = true)
    CtaInvHisEntity findByNumCta(String numCta);

//    @Transactional
//    @Modifying
//    @Query(value = "update OPL_HIS_CTAINV_MEDIUM_TBL set RESIDENTE_V = :residenteV , IND_EXT_V = :indExtV , "
//            + "DECLA_V = :declaV where NUM_CTA = :numCta", nativeQuery = true)
//    void updateDemographics(@Param("residenteV") String residenteV, @Param("indExtV") Integer indExtV, @Param("declaV") String declaV,
//                            @Param("numCta") String numCta);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update OPL_HIS_CTAINV_MEDIUM_TBL set CORREO = :correo where NUM_CTA = :numCta", nativeQuery = true)
//    void updateCorreo(@Param("correo") String correo, @Param("numCta") String numCta);

}
