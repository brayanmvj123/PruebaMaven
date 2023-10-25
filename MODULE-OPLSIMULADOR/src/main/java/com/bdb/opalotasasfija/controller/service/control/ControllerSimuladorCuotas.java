package com.bdb.opalotasasfija.controller.service.control;

import com.bdb.opalotasasfija.controller.service.interfaces.SimuladorCuotaService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseSimuladorCuota;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("CDTSDesmaterializado/v2")
@Api(tags = "Simulador Cuota Versión 2")
public class ControllerSimuladorCuotas {

    @Autowired
    private SimuladorCuotaService simuladorCuotaService;

    @ApiOperation(value = "Este servicio se encarga de Simular los datos enviados de acuerdo a la tasa solicitada.",
            produces = "application/json",
            response = JSONResponseSimuladorCuota.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Simulación exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al simular, por favor reportar")
    }
    )
    @ApiParam(name = "JSONRequestSimuladorCuota", value = "Parametros utilizados por el simulador", required = true)
    @PostMapping(value = "simulacionCuota", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {"application/json"})
    public ResponseEntity<JSONResponseSimuladorCuota> calculoTasas(@Valid @RequestBody JSONRequestSimuladorCuota request,
                                                                   HttpServletRequest urlRequest) throws ParseException {
        JSONResponseSimuladorCuota response = simuladorCuotaService.simulador(request, urlRequest);
        return ResponseEntity.status(response.getStatus().getCode()).body(response);
    }
}