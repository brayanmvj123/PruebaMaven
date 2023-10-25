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

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.model.email.ResponseEmail;
import com.bdb.opaloshare.persistence.model.jsonschema.semanal.JSONGetSalPgSemanal;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.platform.servfront.controller.service.interfaces.ConciliactionService;
import com.bdb.platform.servfront.controller.service.interfaces.InfoFechaVenOfiService;
import com.bdb.platform.servfront.model.JSONSchema.JSONGetSalPgDiaria;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPg;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPgSemanal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador del servicio encargado realizar la consulta de la informacion semanal de fecha de vencimiento de oficinas
 *
 * @author: Andres Marles
 * @version: 03/01/2022
 * @since: 03/01/2022
 */
@RestController
@RequestMapping("Information/Office")
@CrossOrigin(origins = "*", maxAge = 0, allowedHeaders = "*", methods = {RequestMethod.POST})
@Tag(name = "Liquidación de CDTs", description = "Servicio que permite realizar la consulta de la data con " +
        "la informacion semanal de fecha de vencimiento de oficinas")
public class InfoFechaVenOficinaController {

    @Autowired
    InfoFechaVenOfiService infoFechaVenOfiService;

    @Autowired
    @Qualifier("ConciliationServicempl")
    ConciliactionService conciliactionService;

    @GetMapping(value = "expirationDate/weekly")
    @ResponseBody
    @Operation(summary = "Retorna información de los CDTS cuya fecha de vencimiento es la semana actual", description = "Consulta todos los registros de la tabla OPL_SAL_PGSEMANAL_DOWN_TBL")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna la información semanal de fecha de vencimiento de oficinas."),
            @ApiResponse(responseCode = "204", description = "La tabla OPL_SAL_PGSEMANAL_DOWN_TBL no tiene registros",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<JSONGetSalPgSemanal>>> consultaSalPgSemanal(HttpServletRequest http) {
        List<JSONGetSalPgSemanal> result = infoFechaVenOfiService.consultaDataFechaVenOfiSemanal();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }

    @GetMapping(value = "expirationDate/daily")
    @Operation(summary = "Retorna información de los CDTS cuya fecha de vencimiento es el dia actual", description = "Consulta todos los registros de la tabla OPL_SAL_PG_DOWN_TBL")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna información con los CDTS cuya fecha de vencimiento es el dia actual."),
            @ApiResponse(responseCode = "204", description = "La tabla OPL_SAL_PG_DOWN_TBL no tiene registros",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<JSONGetSalPgDiaria>>> consultaSalPg(HttpServletRequest http) {
        List<JSONGetSalPgDiaria> result = infoFechaVenOfiService.consultaDataFechaVenOfi();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.size() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }


    @GetMapping(value = "expirationDate/daily/page/{page}/size/{size}")
    @Operation(summary = "Retorna información de los CDTS cuya fecha de vencimiento es el dia actual, devuelve el resultado paginado", description = "Consulta todos los registros de la tabla OPL_SAL_PG_DOWN_TBL, devuelve el resultado paginado")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna información con los CDTS cuya fecha de vencimiento es el dia actual."),
            @ApiResponse(responseCode = "404", description = "La tabla OPL_SAL_PG_DOWN_TBL no tiene registros",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Page<JSONGetSalPgDiaria>>> consultaSalPgWithPagination(@Parameter( description = "Número de pagina.",  required = true) @PathVariable Integer page,
                                                                                               @Parameter( description = "Tamaño de la página, número de registros por página.", required = true) @PathVariable Integer size,
                                                                                               HttpServletRequest http) {

        Page<JSONGetSalPgDiaria> result = infoFechaVenOfiService.consultaDataFechaVenOfiPagination(PageRequest.of(page, size));
        return ResponseEntity.status((result == null || result.getSize() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(
                new RequestResult<>(http,
                        (result == null || result.getSize() < 1) ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                        result));
    }


    @PutMapping(value = "expirationDate/daily/update")
    @ResponseBody
    @Operation(summary = "Actualiza algunos campos del CDT diario ingresado",
            description = "Actualiza los registros de las tablas OPL_SAL_PG_DOWN_TBL y OPL_HIS_PG_DOWN_TBL a partir de los datos enviada en el Request")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Datos actualizados exitosamente en la tabla OPL_SAL_PG_DOWN_TBL y OPL_HIS_PG_DOWN_TBL"),
            @ApiResponse(responseCode = "400", description = "1- El registro ingresado en el request no existe en la tabla OPL_SAL_PG_DOWN_TBL, " +
                                        "por lo tanto, NO se actualiza. 2-Los datos ingresados no son correctos, revisar respuesta.",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "404", description = "EL CDT ingresado no está registrado en la tabla OPL_SAL_PG_DOWN_TBL",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Map<String, Object>>> updateConciliacionDaily(@Valid @RequestBody RequestSalPg request, HttpServletRequest http) {
        return conciliactionService.updateDaily(request, http);
    }


    @PutMapping(value = "expirationDate/weekly/update")
    @ResponseBody
    @Operation(summary = "Actualiza algunos campos del CDT semanal ingresado",
            description = "Actualiza los registros de las tablas OPL_SAL_PGSEMANAL_DOWN_TBL a partir de los datos enviada en el Request")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Datos actualizados exitosamente en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL"),
            @ApiResponse(responseCode = "400", description = "1- El registro ingresado en el request no existe en la tabla OPL_SAL_PG_DOWN_TBL, " +
                    "por lo tanto, NO se actualiza. 2-Los datos ingresados no son correctos, revisar respuesta.",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "404", description = "EL CDT ingresado no está registrado en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Map<String, Object>>> updateConciliacionWeekly(@Valid @RequestBody RequestSalPgSemanal request, HttpServletRequest http) {
        return conciliactionService.updateWeekly(request, http);
    }


    @GetMapping(value = "expirationDate/daily/confirmation")
    @ResponseBody
    @Operation(summary = "Permite confirmar si todos los CDTs del dia fueron conciliados",
            description = "Revisa que los valores de los factores de Opalo y DCVSA sean iguales en cada CDT registrado en tabla " +
                    "OPL_SAL_PG_DOWN_TBL.\nSi todos los CDTs cumplen esta condición, se crea un archivo plano vacío con nombre " +
                    "'cdtsconcilia_YYYYMMdd.OPL' en el servicio FTPS para la liquidación diaria y se actualiza el campo " +
                    "estado a 4 en todos los CDTs, sino se cumple la condición se muestra una respuesta 404")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Todos los CDTs actualizan su estado a 4 y " +
                    "se crea un archivo plano vacío en el servicio FTPS para la liquidación diaria"),
            @ApiResponse(responseCode = "404", description = "No se actualiza el estado de ningún CDT " +
                                        "y no se crea el archivo plano en el sitio FTPS porque los factores de " +
                                        " Opalo y DCVSA en la tabla OPL_SAL_PG_DOWN_TBL son diferentes en uno o más registros",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Map<String, Object>>> confirmacionDaily(HttpServletRequest http) throws ErrorFtps, IOException {

        Map<String, Object> response = new  HashMap<>();
        Boolean validation = conciliactionService.confirmationDaily();
        String resp = validation ? "Archivo creado exitosamente":
                "No se actualizó el estado de ningún CDT ni se creó el archivo porque los factores de Opalo y DCVSA en la tabla OPL_SAL_PG_DOWN_TBL son diferentes en uno o más registros";
        response.put("response", resp);
        return ResponseEntity.status( validation ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(
                new RequestResult<>(http, validation ? HttpStatus.OK : HttpStatus.NOT_FOUND, response));
    }


    @GetMapping(value = "expirationDate/weekly/confirmation")
    @ResponseBody
    @Operation(summary = "Permite confirmar si todos los CDTs de la semana fueron conciliados",
            description = "Revisa que los valores de los factores de Opalo y DCVSA sean iguales en cada CDT registrado en tabla " +
                    "OPL_SAL_PGSEMANAL_DOWN_TBL.\nSi todos los CDTs cumplen esta condición, se actualiza el campo " +
                    "estado a 4 en todos los CDTs, sino se cumple la condición se muestra una respuesta 404")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Todos los CDTs actualizan su estado a 4"),
            @ApiResponse(responseCode = "404", description = "No se actualiza el estado de ningún CDT " +
                                        "porque los factores de Opalo y DCVSA en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL son " +
                                        "diferentes en uno o más registros",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Map<String, Object>>> confirmationWeekly(HttpServletRequest http) {

        Map<String, Object> response = new  HashMap<>();
        Boolean validation = conciliactionService.confirmationWeekly();
        String resp = validation ? "Proceso exitoso": "No se actualizó el estado de ningún CDT porque los factores de Opalo y" +
                " DCVSA en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL son diferentes en uno o más registros";
        response.put("response", resp);
        return ResponseEntity.status( validation ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(
                new RequestResult<>(http, validation ? HttpStatus.OK : HttpStatus.NOT_FOUND, response));
    }



    @GetMapping(value = "expirationDate/daily/sendFilesToEmail")
    @Operation(summary = "Envía un Excel a cada oficina con los CDTs liquidados en el día correspondiente",
            description = "Envía la liquidación diaria por correo a cada oficina por medio de un excel que contiene la información de CDTs " +
                    "Conciliados No digitales (tipPosicion !=4) y pertenecientes a oficinas distintas a tesorería (tipPosicion !=2).")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Indica que los archivos se crearon correctamente en el FTPS y " +
                    "todos los correos fueron enviados exitosamente"),
            @ApiResponse(responseCode = "206", description = "Indica que los archivos se crearon correctamente en el FTPS y " +
                    "muestra las oficinas a las cuales se le enviaron y NO los correos. Revisar respuesta",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Map<String, Object>>> sendFilesDaily(HttpServletRequest http) throws Exception {

        Map<String, Object> response = new  HashMap<>();
        Map<String, List<ResponseEmail>> validation = conciliactionService.sendFilesOfficeDaily(http);
        String resp = (validation.get("Correos NO enviados").isEmpty() || validation.get("Correos NO enviados") == null) ? "Todos los correos fueron enviados exitosamente":
                "Algunos correos no fueron enviados, revisar";
        response.put("response", resp);
        if(!validation.values().isEmpty())  response.put("process", validation);
        return ResponseEntity.status( (validation.get("Correos NO enviados").isEmpty() || validation.get("Correos NO enviados")== null)
                        ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT)
                .body(new RequestResult<>(http, (validation.get("Correos NO enviados").isEmpty() || validation.get("Correos NO enviados") == null)
                        ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT, response));
    }


    @GetMapping(value = "expirationDate/weekly/sendFilesToEmail")
    @Operation(summary = "Envía un Excel a cada oficina con los CDTs liquidados en la semana correspondiente",
            description = "Envía la liquidación semanal por correo a cada oficina por medio de un excel que contiene la " +
                    "información de CDTs Conciliados No digitales (tipPosicion !=4) y pertenecientes a oficinas distintas " +
                    "a tesorería (tipPosicion !=2).")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Indica que los archivos se crearon correctamente en el FTPS y " +
                    "todos los correos fueron enviados exitosamente"),
            @ApiResponse(responseCode = "206", description = "Indica que los archivos se crearon correctamente en el FTPS y " +
                    "muestra las oficinas a las cuales se le enviaron y NO los correos. Revisar respuesta",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse( responseCode = "404", description = "Indica que No hay correos nuevos o pendientes por enviar, " +
                    "todos los correos correspondientes al periodo actual fueron enviados anteriormente."),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Map<String, Object>>> sendFilesWeekly(HttpServletRequest http) throws Exception {

        Map<String, Object> response = new  HashMap<>();
        Map<String, Object> validation = conciliactionService.sendFilesOfficeWeekly(http);
        if (validation.containsKey("info")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new RequestResult<>(http, HttpStatus.NOT_FOUND, validation));
        if (validation.containsKey("validation")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new RequestResult<>(http, HttpStatus.BAD_REQUEST, validation));
        String resp = (((List<?>) validation.get("Correos NO enviados")).isEmpty() || validation.get("Correos NO enviados") == null )
                ? "Todos los correos fueron enviados exitosamente": "Algunos correos no fueron enviados, revisar";
        response.put("response", resp);
        if(!validation.values().isEmpty())  response.put("process", validation);
        return ResponseEntity.status( (((List<?>) validation.get("Correos NO enviados")).isEmpty() || validation.get("Correos NO enviados") == null ) ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT)
                .body(new RequestResult<>(http, (((List<?>) validation.get("Correos NO enviados")).isEmpty() || validation.get("Correos NO enviados") == null)
                        ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT, response));
    }
}
