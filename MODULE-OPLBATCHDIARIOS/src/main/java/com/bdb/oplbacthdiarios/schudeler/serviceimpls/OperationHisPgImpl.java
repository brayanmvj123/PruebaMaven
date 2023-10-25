package com.bdb.oplbacthdiarios.schudeler.serviceimpls;

import com.bdb.opaloshare.persistence.entity.HisPgEntity;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisPg;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.oplbacthdiarios.mapper.MapperReportPg;
import com.bdb.oplbacthdiarios.schudeler.services.OperationHisPg;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CommonsLog
public class OperationHisPgImpl implements OperationHisPg {

    @Autowired
    RepositoryHisPg repositoryHisPg;

    @Autowired
    RepositorySalPg repositorySalPg;

    @Autowired
    MapperReportPg mapperReportPg;

    @Override
    public void backupSalpg() {
        List<SalPgDownEntity> salPgEntities = repositorySalPg.findAll();
        List<HisPgEntity> hisPgEntities = mapperReportPg.listToSalpgtoHisPg(salPgEntities);
        repositoryHisPg.saveAll(hisPgEntities);
    }
}
