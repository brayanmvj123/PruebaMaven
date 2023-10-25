package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public interface RepositoryTipVarentorno extends JpaRepository<VarentornoDownEntity, Serializable> {

    VarentornoDownEntity findByDescVariable(String valor);

    @Transactional
    @Modifying
    @Query("update VarentornoDownEntity set valVariable = valVariable+1 where descVariable = :descVar ")
    void actualizarNumCdtDesma(@Param("descVar") String descVar);
}
