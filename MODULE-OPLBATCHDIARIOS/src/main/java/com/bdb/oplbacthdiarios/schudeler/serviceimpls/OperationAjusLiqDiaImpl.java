package com.bdb.oplbacthdiarios.schudeler.serviceimpls;

import com.bdb.opaloshare.persistence.entity.AjusLiqDiaEntity;
import com.bdb.opaloshare.persistence.entity.HisPgEntity;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryAjusLiqDia;
import com.bdb.opaloshare.persistence.repository.RepositoryHisPg;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.oplbacthdiarios.mapper.MapperReportPg;
import com.bdb.oplbacthdiarios.schudeler.services.OperationAjusLiqDia;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class OperationAjusLiqDiaImpl implements OperationAjusLiqDia {

    @Autowired
    RepositorySalPg repositorySalPg;

    @Autowired
    RepositoryAjusLiqDia repositoryAjusLiqDia;

    @Autowired
    MapperReportPg mapperReportPg;

    @Override
    public void createAjusLiqDia() {

        List<SalPgDownEntity> salPgList = repositorySalPg.findAll().stream()
                .filter(x -> !x.getFactorOpl().equals(x.getFactorDcvsa())).collect(Collectors.toList());
        List<AjusLiqDiaEntity> ajusLiqDia = mapperReportPg.listToSalpgtoAjusLiqDia(salPgList);
        repositoryAjusLiqDia.saveAll(ajusLiqDia);
    }
}
