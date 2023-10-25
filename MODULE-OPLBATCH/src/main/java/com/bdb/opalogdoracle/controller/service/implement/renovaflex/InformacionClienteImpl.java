package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.InformacionClienteService;
import com.bdb.opalogdoracle.mapper.MapperInfoCtaClient;
import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opaloshare.persistence.entity.HisRenovaCdtEntity;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryDatosCliente;
import com.bdb.opaloshare.persistence.repository.RepositoryHisRenovaCdt;
import com.bdb.opaloshare.persistence.repository.RepositoryTransacciones;
import com.bdb.opaloshare.persistence.repository.RepositoryTransaccionesPago;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CommonsLog
public class InformacionClienteImpl implements InformacionClienteService {

    private final RepositoryTransaccionesPago repositoryTransaccionesPago;
    private final RepositoryHisRenovaCdt repositoryHisRenovaCdt;
    private final MapperInfoCtaClient mapperInfoCtaClient;


    public InformacionClienteImpl(RepositoryTransaccionesPago repositoryTransaccionesPago,
                                  MapperInfoCtaClient mapperInfoCtaClient,
                                  RepositoryHisRenovaCdt repositoryHisRenovaCdt) {
        this.repositoryTransaccionesPago = repositoryTransaccionesPago;
        this.mapperInfoCtaClient = mapperInfoCtaClient;
        this.repositoryHisRenovaCdt = repositoryHisRenovaCdt;
    }

    @Override
    public InfoCtaClienteModel obtenerInformacion(SalRenautdigEntity salRenautdigEntity) {
        return mapperInfoCtaClient.infoCtaPago(hisTranpgInfoCta(salRenautdigEntity.getNumCdt()), salRenautdigEntity);
    }

    public HisTranpgEntity hisTranpgInfoCta(Long numCdt) {
        log.info("Se inicia el proceso hisTranpgInfoCta...");
        Optional<HisRenovaCdtEntity> byCdtAct = Optional.ofNullable(repositoryHisRenovaCdt.findByCdtAct(numCdt));
        if (byCdtAct.isPresent()) {
            log.info("SI ES UNA RENOVACION ..." + byCdtAct.get().getCdtOrigen());
            return repositoryTransaccionesPago.buscarTransaccionCdtDig(byCdtAct.get().getCdtOrigen().toString(), "1");
        } else {
            log.info("NO ES RENOVACIÓN");
            return repositoryTransaccionesPago.buscarTransaccionCdtDig(numCdt.toString(), "1");
        }
    }
}
