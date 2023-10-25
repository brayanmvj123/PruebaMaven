package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.EnvioCdtsDcvBtaService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ControlEstadoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.NotificacionDcvBtaService;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.model.component.ModelCrucePatrimonioRenaut;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@CommonsLog
public class EnvioCdtsDcvBtaImpl implements EnvioCdtsDcvBtaService {

    private final RepositorySalRenautdig repositorySalRenautdig;

    private final RepositoryParEndpointDown repositoryParEndpointDown;

    private final ControlEstadoService controlEstadoService;

    private final NotificacionDcvBtaService notificacionDcvBtaService;


    public EnvioCdtsDcvBtaImpl(RepositorySalRenautdig repositorySalRenautdig,
                               RepositoryParEndpointDown repositoryParEndpointDown,
                               ControlEstadoService controlEstadoService,
                               NotificacionDcvBtaService notificacionDcvBtaService) {
        this.repositorySalRenautdig = repositorySalRenautdig;
        this.repositoryParEndpointDown = repositoryParEndpointDown;
        this.controlEstadoService = controlEstadoService;
        this.notificacionDcvBtaService = notificacionDcvBtaService;
    }

    @Override
    public void envioCdtsDcvBta() {
        log.info("Se inicia la preparación de los CDTs Digitales en estado completado (C) que se enviarán a Deceval BTA.");
        List<SalRenautdigEntity> listResult = repositorySalRenautdig.findAll();
        List<ModelCrucePatrimonioRenaut> listResulCruce = new ArrayList<>();
        if (!listResult.isEmpty()) {
            listResult.stream()
                    .filter(cdt -> cdt.getEstadoV().equals("C"))
                    .forEach(dataCruce -> {
                        ModelCrucePatrimonioRenaut modelCrucePatrimonioRenaut = new ModelCrucePatrimonioRenaut();
                        modelCrucePatrimonioRenaut.setNumCdt(dataCruce.getNumCdt().toString());
                        modelCrucePatrimonioRenaut.setCodIsin(dataCruce.getCodIsin());
                        modelCrucePatrimonioRenaut.setTipId(dataCruce.getTipId());
                        modelCrucePatrimonioRenaut.setNumTit(dataCruce.getNumTit());
                        modelCrucePatrimonioRenaut.setNomTit(dataCruce.getNomTit());
                        modelCrucePatrimonioRenaut.setIntBruto(dataCruce.getIntBruto().toString());
                        modelCrucePatrimonioRenaut.setRteFte(dataCruce.getRteFte().toString());
                        modelCrucePatrimonioRenaut.setIntNeto(dataCruce.getIntNeto().toString());
                        modelCrucePatrimonioRenaut.setCapPg(dataCruce.getCapPg().toString());
                        listResulCruce.add(modelCrucePatrimonioRenaut);
                    });
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        log.info("Se valida el endpoint de consumo para el servicio envioCDTsDcvRenautDig.");
        String endpoint = repositoryParEndpointDown.findById(20L)
                .orElse(new ParEndpointDownEntity(20L, "http://localhost:8082/CDTSDesmaterializado/v1/envioCDTsDcvRenautDig"))
                .getRuta();

        HttpEntity<List<ModelCrucePatrimonioRenaut>> requestEntity = new HttpEntity<>(listResulCruce, httpHeaders);
        getResponseService(requestEntity, endpoint);
        log.info("De forma exitosa se han enviado los CDTs Digitales a Deceval BTA.");
        controlEstadoService.actualizarHistorialResultado("paso3");
        controlEstadoService.actualizarControlRenovacion("EJECUTANDOSE", 4);
    }

    public <T> ResponseEntity<String> getResponseService(HttpEntity<T> requestEntity, String url) {
        log.info("Se inicia el consumo envioCDTsDcvRenautDig");
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        } catch (HttpStatusCodeException e) {
            log.error("Lastimosamente el servicio consumido: " + url + " fallo: " + e.getMessage());
            controlEstadoService.actualizarControlRenovacion("FALLIDO", 3);
            notificacionDcvBtaService.enviarNotificacion("RENAUT_FAIL");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lastimosamente el servicio consumido: " +
                    url + " fallo: " + e.getMessage());
        }
    }
}
