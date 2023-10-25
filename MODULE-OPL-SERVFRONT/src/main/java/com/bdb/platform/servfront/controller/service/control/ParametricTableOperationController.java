/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.platform.servfront.controller.service.control;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.platform.servfront.controller.service.interfaces.ParametricTableOperationService;
import com.bdb.platform.servfront.model.JSONSchema.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controlador del servicio encargado realizar operaciones a las tablas parametricas de la base de Datos
 *
 * @author: Esteban Talero
 * @version: 17/12/2020
 * @since: 02/12/2020
 */
@RestController
@RequestMapping("ParametricTable/Operation")
@CrossOrigin(origins = "*", maxAge = 0, allowedHeaders = "*", methods = {RequestMethod.POST})
@Tag(name = "Operaciones a Tablas Parametricas", description = "Servicio que permite realizar alguna operacion con las" +
        "tablas parametricas")
public class ParametricTableOperationController {

    @Autowired
    ParametricTableOperationService parametricTableOperationService;

    @GetMapping(value = "read/baseType")
    @ResponseBody
    @Operation(summary = "Consulta tabla tipo de Base", description = "Retorna informacion de tipos de Base")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "\"Retorna los tipo de Base"),
            @ApiResponse(responseCode = "204", description = "La tabla tipo Base esta vacia\"",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR SERVER",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<JSONGetTipoBase>>> consultaTipoBase(HttpServletRequest http) {
        List<JSONGetTipoBase> result = parametricTableOperationService.consultaTipoBase();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }

    @GetMapping(value = "read/periodType")
    @ResponseBody
    @Operation(summary = "Consulta tabla tipo de Periodo", description = "Retorna informacion de tipos de Periodo")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna los tipo de Periodo"),
            @ApiResponse(responseCode = "204", description = "La tabla tipo Periodo esta vacia",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR SERVER",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<JSONGetTipoPeriodo>>> consultaTipoPeriodo(HttpServletRequest http) {
        List<JSONGetTipoPeriodo> result = parametricTableOperationService.consultaTipoPeriodo();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }

    @GetMapping(value = "read/rateType")
    @ResponseBody
    @Operation(summary = "Consulta tabla tipo de Tasa", description = "Retorna informacion de tipos de Tasa")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna los tipo de Tasa"),
            @ApiResponse(responseCode = "204", description = "La tabla tipo Tasa esta vacia",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR SERVER",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<JSONGetTipoTasa>>> consultaTipoTasa(HttpServletRequest http) {
        List<JSONGetTipoTasa> result = parametricTableOperationService.consultaTipoTasa();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }

    @GetMapping(value = "read/termType")
    @ResponseBody
    @Operation(summary = "Consulta tabla tipo de Plazo", description = "Retorna informacion de tipos de Plazo")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna los tipo de Plazo"),
            @ApiResponse(responseCode = "204", description = "La tabla tipo Plazo esta vacia",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR SERVER",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<JSONGetTipoPlazo>>> consultaTipoPlazo(HttpServletRequest http) {
        List<JSONGetTipoPlazo> result = parametricTableOperationService.consultaTipoPlazo();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }

    @GetMapping(value = "read/yearCalendar/noHabil/{anno}")
    @ResponseBody
    @Operation(summary = "Consulta tabla calendario historico opalo", description = "Retorna información de calendario")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna el calendario, los días no hábiles"),
            @ApiResponse(responseCode = "204", description = "La tabla calendario esta vacia",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR SERVER",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<JSONGetCalendario>>> consultaCalendario(@PathVariable(value="anno") int anno,HttpServletRequest http) {
        List<JSONGetCalendario> result = parametricTableOperationService.consultaCalendarioAnnioNoHabil(anno);
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }
}
