package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.AcuDerpatriemiDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
public interface RepositoryAcuDerpatriemiDownEntity extends JpaRepository<AcuDerpatriemiDownEntity, Serializable> {

    @Query("select f from AcuDerpatriemiDownEntity f where f.isin = :isin and f.ctaInv = :ctaInv " +
            " and f.idTit = :idTit")
    AcuDerpatriemiDownEntity findAccumulatedDepositor(String isin, String ctaInv, String idTit);

    @Modifying
    @Transactional
    @Query("update AcuDerpatriemiDownEntity acuDerpatriEmi set acuDerpatriEmi.depositanteV = '1' " +
            " where acuDerpatriEmi.isin = :isin and acuDerpatriEmi.ctaInv = :ctaInv and acuDerpatriEmi.idTit = :idTit")
    void updateDepositorStatus(@Param("isin") String isin, @Param("ctaInv") String ctaInv, @Param("idTit") String idTit);

}
