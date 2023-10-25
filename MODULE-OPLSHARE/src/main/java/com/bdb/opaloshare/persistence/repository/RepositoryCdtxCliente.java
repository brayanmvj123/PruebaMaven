package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdb.opaloshare.persistence.entity.HisClixCDTLarge;

public interface RepositoryCdtxCliente extends JpaRepository<HisClixCDTLarge, Serializable> {

    boolean existsByOplCdtsTblNumCdtAndOplClientesTblNumTit(String numCdt, String numTit);

}
