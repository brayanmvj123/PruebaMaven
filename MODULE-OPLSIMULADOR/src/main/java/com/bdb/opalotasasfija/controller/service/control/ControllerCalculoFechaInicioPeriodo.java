package com.bdb.opalotasasfija.controller.service.control;

import com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo.JSONRequestFechaInicioPeriodo;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoFechaInicioPeriodoService;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResponseCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculoFechaInicioPeriodo.JSONResultCalculoFechaInicioPeriodo;
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
@Api(tags = "Calculo Fecha Inicio de Periodo")
public class ControllerCalculoFechaInicioPeriodo {
    @Autowired
    CalculoFechaInicioPeriodoService calculadoraFechasTasasService;


    @ApiOperation(value = "Este servicio se encarga de calcular la fecha de inicio de periodo, basado en el plazo y la fecha" +
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
    @ApiParam(name = "JSONRequestCalculadoraDias", value = "Parametros utilizados por la calculadora de fecha.", required = true)
    @PostMapping(value = "calculoFechaInicioPeriodo" , consumes = {"application/json"} , produces = {"application/json"})
    public ResponseEntity<JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo>>
                calculoFechaInicioPeriodo(@Valid @RequestBody JSONRequestFechaInicioPeriodo request, HttpServletRequest url){

        JSONResponseCalculadoraDias<JSONResponseStatus,
                JSONRequestFechaInicioPeriodo,
                JSONResultCalculoFechaInicioPeriodo> result = calculadoraFechasTasasService.calculoFechaInicioPeriodo(request, url);
        return ResponseEntity
                .status(result.getStatus().getCode()).body(result);
    }
}
