package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ConsumoGenericoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ControlEstadoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.NotificacionDcvBtaService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.TraductorRenovacionService;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@CommonsLog
public class TraductorRenovacionImpl implements TraductorRenovacionService {

    private final RepositoryParEndpointDown repositoryParEndpointDown;
    private final NotificacionDcvBtaService notificacionDcvBtaService;
    private final ConsumoGenericoService consumoGenericoService;
    private final ControlEstadoService controlEstadoService;

    public TraductorRenovacionImpl(RepositoryParEndpointDown repositoryParEndpointDown,
                                   NotificacionDcvBtaService notificacionDcvBtaService,
                                   ConsumoGenericoService consumoGenericoService,
                                   ControlEstadoService controlEstadoService) {
        this.repositoryParEndpointDown = repositoryParEndpointDown;
        this.notificacionDcvBtaService = notificacionDcvBtaService;
        this.consumoGenericoService = consumoGenericoService;
        this.controlEstadoService = controlEstadoService;
    }

    @Override
    public int generarTraductor() {
        try {
            log.info("start TRADUCTOR CADI...");

            String url = repositoryParEndpointDown.findById(21L)
                    .orElse(new ParEndpointDownEntity(21L, "http://localhost:8082/DO/v1/process/cancelacion"))
                    .getRuta();

            ResponseEntity<String> response = consumoGenericoService.getResponseService(null, url);

            if (response.getStatusCodeValue() == 200) {
                log.info("FINALIZA LA GENERACIÓN DE LAS TRAMAS CONTABLES PARA EL PROCESO DE RENOVACIÓN.");
                return response.getStatusCodeValue();
            } else {
                log.error("Se genero error al generar el traductor contable del proceso de renovación.");
                controlEstadoService.actualizarControlRenovacion("FALLIDO", 4);
                notificacionDcvBtaService.enviarNotificacion("RENAUT_FAIL");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se genero error al momento de consumir " +
                        "el servicio Traductor.");
            }

        } catch (Exception e) {
            controlEstadoService.actualizarControlRenovacion("FALLIDO", 4);
            notificacionDcvBtaService.enviarNotificacion("RENAUT_FAIL");
            log.error("Error en CrossTraductorCadiTasklet: {0}", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se genero error al momento de consumir " +
                    "el servicio Traductor.");
        }
    }
}
