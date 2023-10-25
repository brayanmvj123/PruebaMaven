package com.bdb.opalotasasfija.controller.service.control;

import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraDiasService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.calculadoradias.JSONRequestCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResponseCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResultCalculoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v2")
@Api(tags = "Calculadora de Dias")
public class ControllerCalculadoraDias {

    @Autowired
    CalculadoraDiasService calculadoraDiasService;

    @ApiOperation(value = "Este servicio se encarga de calcular los dias entre la fecha de la apertura y la fecha " +
            "vencimiento de acuerdo a la base solicitada.",
            produces = "application/json",
            response = JSONResponseCalculadoraDias.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Calculo exitoso"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al calcular, por favor reportar")
    }
    )
    @ApiParam(name = "JSONRequestCalculadoraDias", value = "Parametros utilizados por la calculadora de dias.", required = true)
    @PostMapping(value = "simulador/calculoDiasCDT" , consumes = {"application/json"} , produces = {"application/json"})
    public ResponseEntity<JSONResponseCalculadoraDias<JSONResponseStatus,
            JSONRequestCalculadoraDias,
            JSONResultCalculoraDias>> calculoDiasCDT(@Valid @RequestBody JSONRequestCalculadoraDias request, HttpServletRequest url){
        JSONResponseCalculadoraDias<JSONResponseStatus,
                JSONRequestCalculadoraDias,
                JSONResultCalculoraDias> result = calculadoraDiasService.calculoDias(request, url);
        return ResponseEntity
                .status(result.getStatus().getCode()).body(result);
    }

}
