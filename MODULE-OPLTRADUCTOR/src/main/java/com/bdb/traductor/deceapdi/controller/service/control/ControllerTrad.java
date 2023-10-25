package com.bdb.traductor.deceapdi.controller.service.control;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.model.trad.JSONPlotFields;
import com.bdb.opaloshare.util.Plot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file war write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@RestController
@RequestMapping("TR/v1")
@Api(value = "Traductor APDI", description = "Servicio que permite generar la trama del traductor contable de Apertura de CDTs" +
        "enviando como parametro todos los datos para procesos y retornar la trama")
public class ControllerTrad {

    /**
     * Petición de traducción de información de transacción de CDT a Trama.
     *
     * @param request Http Servlet Request
     * @param data    JSON Data
     * @return response
     */
    @PostMapping(value = "dece/apdi", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "Traductor APDI", notes = "Retorna la trama del traductor de Apertura de CDTs enviando todos" +
            "los parametros para procesar y retornar la trama")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER")})
    public ResponseEntity<RequestResult<String>> doToPlot(HttpServletRequest request,
                                                          @Valid @RequestBody JSONPlotFields data,
                                                          @RequestParam("canal") String canal) {
        return ResponseEntity.ok(new RequestResult<>(request, HttpStatus.OK, Plot.deceApdi(data)));
    }
}
