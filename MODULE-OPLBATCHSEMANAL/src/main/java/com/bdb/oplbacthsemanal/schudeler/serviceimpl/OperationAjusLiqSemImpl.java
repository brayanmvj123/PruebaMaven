package com.bdb.oplbacthsemanal.schudeler.serviceimpl;

import com.bdb.opaloshare.persistence.entity.AjusLiqSemEntity;
import com.bdb.opaloshare.persistence.entity.SalPgSemanalDownEntity;
import com.bdb.opaloshare.persistence.repository.*;
import com.bdb.oplbacthsemanal.mapper.MapperReportPg;
import com.bdb.oplbacthsemanal.schudeler.services.OperationAjusLiqSem;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class OperationAjusLiqSemImpl implements OperationAjusLiqSem {

    @Autowired
    RepositorySalPgSemanalDown repositorySalPgSemanal;

    @Autowired
    RepositoryAjusLiqSem repositoryAjusLiqSem;

    @Autowired
    MapperReportPg mapperReportPg;

    @Override
    public void createAjusLiqSem() {

        List<SalPgSemanalDownEntity> salPgSemanalList = repositorySalPgSemanal.findAll().stream()
                .filter(x -> !x.getFactorOpl().equals(x.getFactorDcvsa())).collect(Collectors.toList());
        List<AjusLiqSemEntity> ajusLiqSem = mapperReportPg.listToSalpgtoAjusLiqSem(salPgSemanalList);
        repositoryAjusLiqSem.saveAll(ajusLiqSem);
    }
}
