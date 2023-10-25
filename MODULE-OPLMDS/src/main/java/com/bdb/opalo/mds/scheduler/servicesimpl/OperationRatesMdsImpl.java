package com.bdb.opalo.mds.scheduler.servicesimpl;

import com.bdb.opalo.mds.mapper.Mapper;
import com.bdb.opalo.mds.scheduler.services.OperationRatesMds;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.persistence.repository.RepositoryTmpTasaSeg;
import com.bdb.opaloshare.persistence.repository.RepositoryTmpTasasCdtMds;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OperationRatesMdsImpl implements OperationRatesMds {

    private final MetodosGenericos metodosGenericos;

    private final RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds;

    private final RepositoryTmpTasaSeg repositoryTmpTasaSeg;

    private final Mapper mapper;

    public OperationRatesMdsImpl(MetodosGenericos metodosGenericos, RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds, RepositoryTmpTasaSeg repositoryTmpTasaSeg, Mapper mapper) {
        this.metodosGenericos = metodosGenericos;
        this.repositoryTmpTasasCdtMds = repositoryTmpTasasCdtMds;
        this.repositoryTmpTasaSeg = repositoryTmpTasaSeg;
        this.mapper = mapper;
    }

    @Override
    public void creationRates() {
        List<String> result = metodosGenericos.cargarArchivoTypeListString();
        List<String> tasasCDTS = Arrays.asList(result.get(0).split("\n"));
        List<String> tasasCDTSSegmento = Arrays.asList(result.get(1).split("\n"));
        repositoryTmpTasasCdtMds.saveAll(mapper.liststringToTmpTasasCdtMdsEntity(tasasCDTS));
        repositoryTmpTasaSeg.saveAll(mapper.liststringToTmpTasaSegEntity(tasasCDTSSegmento));
    }
}
