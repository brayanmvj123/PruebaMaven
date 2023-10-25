package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.EnvioCdtsDcvBtaService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.*;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
@CommonsLog
public class RenovacionImpl implements RenovacionService {

    private final ObtenerRenovacionService obtenerRenovacionService;
    private final RenovacionCondFlexService renovacionCondFlexService;
    private final ControlEstadoService controlEstadoService;
    private final EnvioCdtsDcvBtaService envioCdtsDcvBtaService;
    private final TraductorRenovacionService traductorRenovacionService;
    private final NotificacionDcvBtaService notificacionDcvBtaService;

    public RenovacionImpl(ObtenerRenovacionService obtenerRenovacionService,
                          RenovacionCondFlexService renovacionCondFlexService,
                          ControlEstadoService controlEstadoService,
                          EnvioCdtsDcvBtaService envioCdtsDcvBtaService,
                          TraductorRenovacionService traductorRenovacionService,
                          NotificacionDcvBtaService notificacionDcvBtaService) {
        this.obtenerRenovacionService = obtenerRenovacionService;
        this.renovacionCondFlexService = renovacionCondFlexService;
        this.controlEstadoService = controlEstadoService;
        this.envioCdtsDcvBtaService = envioCdtsDcvBtaService;
        this.traductorRenovacionService = traductorRenovacionService;
        this.notificacionDcvBtaService = notificacionDcvBtaService;
    }

    @Override
    public ResponseEntity<RequestResult<HashMap<String, Object>>> renovacion(HttpServletRequest http) throws Exception {
        log.info("SE INICIA EL CONSUMO DE LA VERSIÓN 2 DEL SERVICIO DE RENOVACIÓN");

        HashMap<String, Object> resultado = new HashMap<>();

        if (controlEstadoService.validacionEstado()) {
            controlEstadoService.actualizarControlRenovacion("EJECUTANDOSE", 0);
            if (controlEstadoService.obtenerPaso() == 1) {
                obtenerRenovacionService.obtenerRenovaciones();
            }

            if (controlEstadoService.obtenerPaso() == 2) {
                renovacionCondFlexService.procesoRenovacion(http);
            }

            if (controlEstadoService.obtenerPaso() == 3) {
                envioCdtsDcvBtaService.envioCdtsDcvBta();
            }

            if (controlEstadoService.obtenerPaso() == 4) {
                traductorRenovacionService.generarTraductor();
            }

            if (controlEstadoService.obtenerPaso() == 5) {
                notificacionDcvBtaService.enviarNotificacion("RENAUT_COMPLETED");
            }

            //cancelacion

            controlEstadoService.actualizarControlRenovacion("COMPLETADO", 0);

            resultado.put("message", "Resultado exitoso, ejecución completa");
            return ResponseEntity.ok(new RequestResult<>(http, HttpStatus.OK, resultado));
        }

        resultado.put("message", "Conflicto, el servicio ya se ejecuto.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestResult<>(http, HttpStatus.OK, resultado));
    }

}
