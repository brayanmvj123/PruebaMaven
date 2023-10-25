package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.InformacionClienteService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.RechazoRenovacionService;
import com.bdb.opalogdoracle.mapper.MapperRechazosRenovacion;
import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.entity.SalPgdigitalDownEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@CommonsLog
public class RechazoRenovacionImpl implements RechazoRenovacionService {

    private final RepositorySalPg repositorySalPg;
    private final RepositorySalRenautdig repositorySalRenautdig;
    private final MapperRechazosRenovacion mapperRechazosRenovacion;
    private final RepositorySalPgdigitalDown repositorySalPgdigitalDown;
    private final InformacionClienteService informacionClienteService;

    public RechazoRenovacionImpl(RepositorySalPg repositorySalPg,
                                 RepositorySalRenautdig repositorySalRenautdig,
                                 MapperRechazosRenovacion mapperRechazosRenovacion,
                                 RepositorySalPgdigitalDown repositorySalPgdigitalDown,
                                 InformacionClienteService informacionClienteService) {
        this.repositorySalPg = repositorySalPg;
        this.repositorySalRenautdig = repositorySalRenautdig;
        this.mapperRechazosRenovacion = mapperRechazosRenovacion;
        this.repositorySalPgdigitalDown = repositorySalPgdigitalDown;
        this.informacionClienteService = informacionClienteService;
    }

    @Override
    public boolean rechazoRenovacion(SalRenautdigEntity salRenautdigEntity) {
        log.info("SE REALIZA EL RECHAZO DE LA RENOVACIÓN DEL CDT DIGITAL: " + salRenautdigEntity.getNumCdt());
        SalPgDownEntity salPgDownEntity = repositorySalPg.findByNumCdtAndCodIsinAndNumTit(
                salRenautdigEntity.getNumCdt(), salRenautdigEntity.getCodIsin(), salRenautdigEntity.getNumTit());

        if (salPgDownEntity == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generado");

        log.info("SE REALIZA LA CONSULTA DEL TIPO Y CUENTA DEL CDT A RECHAZAR: " + salRenautdigEntity.getNumCdt());
        InfoCtaClienteModel infoCtaClienteModel = informacionClienteService.obtenerInformacion(salRenautdigEntity);

        transladarCdtListaCancelar(salPgDownEntity, infoCtaClienteModel);

        eliminacionCdtListaRenovacion(salRenautdigEntity);

        if (!validacionEliminacionSalRenaut(salPgDownEntity) && validacionAdicionSalPgdigital(
                salPgDownEntity.getNumCdt(), salPgDownEntity.getCodIsin(), salPgDownEntity.getNumTit())) {
            log.info("TRANSLADO EXITOSO.");
            return true;
        }

        return false;
    }

    private SalPgdigitalDownEntity transladarCdtListaCancelar(SalPgDownEntity salPgDownEntity, InfoCtaClienteModel infoCtaClienteModel) {
        log.info("SE REALIZA EL TRANSLADO DEL CDT DIGITAL DE LA LISTA DE RENOVACIONES (SAL_RENAUT) A LA TABLA SAL_PGDIGITAL.");
        return repositorySalPgdigitalDown.save(mapperRechazosRenovacion.toSalPgdigitalDownEntity(salPgDownEntity, infoCtaClienteModel));
    }

    public void eliminacionCdtListaRenovacion(SalRenautdigEntity salRenautdigEntity) {
        log.info("SE REALIZA LA ELIMINACIÓN DEL CDT DIGITAL DE LA LISTA DE RENOVACIONES (SAL_RENAUT).");
        repositorySalRenautdig.delete(salRenautdigEntity);
    }

    public boolean validacionEliminacionSalRenaut(SalPgDownEntity salPgDownEntity) {
        return repositorySalRenautdig.existsByCodIsinAndNumCdtAndNumTit(salPgDownEntity.getCodIsin(),
                salPgDownEntity.getNumCdt(), salPgDownEntity.getNumTit());
    }

    public boolean validacionAdicionSalPgdigital(Long numCdt, String codIsin, String numTit) {
        return repositorySalPgdigitalDown.existsByCodIsinAndNumCdtAndNumTit(codIsin, new BigDecimal(numCdt), numTit);
    }
}
