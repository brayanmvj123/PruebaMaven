package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TmpTasasCdtMdsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryTmpTasasCdtMds extends JpaRepository<TmpTasasCdtMdsEntity,Long> {

    TmpTasasCdtMdsEntity findByCodProdAndTipPlazoAndPlazoMinLessThanEqualAndPlazoMaxGreaterThanEqualAndMontoMinLessThanEqualAndMontoMaxGreaterThanEqualOrderByIdentificadorAsc(String codProd,
                                                                                                                                                              String tipPlazo,
                                                                                                                                                              Integer plazoMin,
                                                                                                                                                              Integer plazoMax,
                                                                                                                                                              Long montoMin,
                                                                                                                                                              Long montoMax);
}
