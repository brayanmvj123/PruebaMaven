package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ConsumoGenericoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.NotificacionDcvBtaService;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class NotificacionDcvBtaImpl implements NotificacionDcvBtaService {

    private final RepositoryParEndpointDown repositoryParEndpointDown;
    private final ConsumoGenericoService consumoGenericoService;

    public NotificacionDcvBtaImpl(RepositoryParEndpointDown repositoryParEndpointDown,
                                  ConsumoGenericoService consumoGenericoService) {
        this.repositoryParEndpointDown = repositoryParEndpointDown;
        this.consumoGenericoService = consumoGenericoService;
    }

    /**
     * Este metodo realiza el consumo del servicio <i><u>estadoRenaut</u></i> expuesto por <i><u>MODULE_OPLSSQLS</u></i>,
     * el cual almacena el estado final del proceso de renovación.
     *
     * @param estado Los estados (AJUSTAR LA DOCUMENTACION)
     */
    @Override
    public int enviarNotificacion(String estado) {
        log.info("Se inicia la notificación del resultado del proceso de renovación a Deceval BTA, con el siguiente " +
                "estado: " + estado);

        String url = repositoryParEndpointDown.findById(22L)
                .orElse(new ParEndpointDownEntity(22L, "http://localhost:8082/CDTSDesmaterializado/v1/renovacion/estadoRenaut?status=")).getRuta();

        url = url + estado;

        ResponseEntity<String> response = consumoGenericoService.getResponseService(null, url);

        return response.getStatusCodeValue();
    }

}
