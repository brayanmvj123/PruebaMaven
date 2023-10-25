package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.EnvioCancelacionService;
import com.bdb.opalogdoracle.persistence.model.servicecancel.JSONCancelCdtDig;
import com.bdb.opaloshare.persistence.entity.SalPgdigitalDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class EnvioCancelacionesServiceImpl implements EnvioCancelacionService {

    private final RepositorySalPg repositorySalPg;

    private final RepositorySalRenautdig repositorySalRenautdig;

    private final RepositorySalPgdigitalDown repositorySalPgdigitalDown;

    private final RepositoryTipVarentorno repositoryTipVarentorno;

    public EnvioCancelacionesServiceImpl(RepositorySalPg repositorySalPg,
                                         RepositorySalRenautdig repositorySalRenautdig,
                                         RepositorySalPgdigitalDown repositorySalPgdigitalDown,
                                         RepositoryTipVarentorno repositoryTipVarentorno) {
        this.repositorySalPg = repositorySalPg;
        this.repositorySalRenautdig = repositorySalRenautdig;
        this.repositorySalPgdigitalDown = repositorySalPgdigitalDown;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
    }

    @Override
    public List<JSONCancelCdtDig> envioCancelaciones() {
            log.info("Se inicia la consulta a la tabla SAL_PGDIGITAL.");
            List<SalPgdigitalDownEntity> cdtsDig = repositorySalPgdigitalDown.findAll();

            log.info("Se verifica la cantidad limite de CDTs Digitales que se van a cancelar.");
            long limite = Long.parseLong(repositoryTipVarentorno.findByDescVariable("LIMITE_PILOTO_CANCAUT")
                    .getValVariable());

            log.info("Se inicia el envio a Deceval BTA.");
            return cdtsDig
                    .stream()
                    .sorted(Comparator.comparing(SalPgdigitalDownEntity::getCapPg))
                    .limit(limite)
                    .map(item -> new JSONCancelCdtDig(item.getCodIsin(),
                            Long.parseLong(item.getNumCdt().toString().substring(5,14)),
                            "CC",
                            item.getNumTit(),
                            item.getNomTit(),
                            item.getCapPg(),
                            item.getIntBruto(),
                            item.getRteFte(),
                            item.getIntNeto(),
                            "CANCELACION_OPL"))
                    .collect(Collectors.toList());

    }
}
