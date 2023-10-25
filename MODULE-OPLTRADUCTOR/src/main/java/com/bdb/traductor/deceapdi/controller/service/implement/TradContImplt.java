package com.bdb.traductor.deceapdi.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.model.trad.CruceHisCdtRenautDigDto;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import com.bdb.opaloshare.persistence.repository.RepositoryTransacciones;
import com.bdb.opaloshare.persistence.repository.RepositoryTransaccionesPago;
import com.bdb.traductor.deceapdi.controller.service.interfaces.TradContService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Service
@CommonsLog
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class TradContImplt implements TradContService {

    @Autowired
    RepositoryCDTSLarge repositoryCDTSLarge;

    @Autowired
    RepositoryTransaccionesPago repositoryTransaccionesPago;

    @Autowired
    RepositoryTransacciones repositoryTransacciones;

    @Override
    public List<HisCDTSLargeEntity> consultaAllCDTSLarge() {
        return repositoryCDTSLarge.findAll();
    }

    @Override
    public List<HisCDTSLargeEntity> consultaCDTSLargePorNumero(String numCdts) {
        return repositoryCDTSLarge.findByNumCdt(numCdts);
    }

    @Override
    public List<HisCDTSLargeEntity> consultaCDTSLargeDia() {
        return repositoryCDTSLarge.findAllByPdcvl();
    }

    @Override
    public List<CruceHisCdtRenautDigDto> consultaCDTSCadi() {
        return repositoryTransaccionesPago.findAllByCdtsCadi();
    }

    @Override
    public List<CruceHisCdtRenautDigDto> consultaCDTSCadiCancel() {
        return repositoryTransaccionesPago.findAllByCdtsCadi();
    }

    @Override
    public List<CruceHisCdtRenautDigDto> consultaCDTSCaOficina(Long numCdt, String codIsin, Long ctaInv) {
        return repositoryTransacciones.findAllByCdtsCaOficina(numCdt, codIsin, ctaInv);
    }

    @Override
    public BigDecimal validarRenovacion(HisTranpgEntity transac) {
        log.info("Campo 1");
        if (transac.getProceso().equals("2")) {
            log.info("Renovacion");
            List<HisCDTSLargeEntity> byNumCdt = repositoryCDTSLarge.findByNumCdt(transac.getHisCDTSLargeEntity().getNumCdt());
            if (byNumCdt.stream()
                    .filter(cdt -> cdt.getTransacciones().size() > 1)
                    .count() == 1L) {
                log.info("Se registra un aumento de capital por renovación.");
                Optional<Optional<HisTranpgEntity>> optionalHisTranpg = byNumCdt
                        .stream()
                        .map(tranpg -> tranpg.getTransacciones()
                                .stream()
                                .filter(hisTranpgEntity -> hisTranpgEntity.getProceso().equals("6")).findFirst())
                        .findFirst();
                if (optionalHisTranpg.isPresent()){
                    Optional<HisTranpgEntity> hisTranpgEntity = optionalHisTranpg.get();
                    if (hisTranpgEntity.isPresent())
                        return transac.getValor().subtract(hisTranpgEntity.get().getValor());
                }
            }
            return transac.getValor();
        }
        return transac.getValor();
    }

}
