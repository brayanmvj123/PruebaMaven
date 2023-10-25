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
package com.bdb.opalo.control.controller.service.control;

import com.bdb.opalo.control.controller.service.interfaces.MediadorService;
import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.dto.Response;
import com.bdb.opalo.control.persistence.dto.ResponseParametros;
import com.bdb.opalo.control.persistence.exception.ControlCdtsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * Controlador del servicio de Control renovacion CDT
 *
 * @author: Esteban Talero
 * @version: 01/10/2020
 * @since: 01/10/2020
 */
@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CrossOrigin(origins = "*", maxAge = 0, allowedHeaders = "*", methods = {RequestMethod.POST})
@Api(value = "Controles CDTs microservice", description = "Servicio que permite llevar el control de CDTs")
@CommonsLog
public class ControlCdtController {

    /**
     * Service encargado de guardar o Actualizar la marcacion de la renovacion CDT
     */
    private final MediadorService mediadorService;

    public ControlCdtController(MediadorService mediadorService) {
        this.mediadorService = mediadorService;
    }

    /**
     * Controlador expuesto para marcacion renovacion CDT
     *
     * @param controlCdtDto Objeto de marcacion renovacion de un cdt
     * @return ResponseEntity
     */
    @PostMapping(path = "/controlesCDTs", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Marcacion de CDT", notes = "Retorna si el CDT fue marcado o no")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Cdt no aplica para renovacion automatica"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Cdt no aplica para renovación automática, debido a que no está constituido o por fecha"),
            @ApiResponse(code = 404, message = "Cdt no encontrado"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN")})
    public ResponseEntity<Response> controlCdt(@RequestHeader("x-version") String version,
                                               @Valid @RequestBody ControlCdtDto controlCdtDto) throws ControlCdtsException {
        log.info("SE INICIA EL CONSUMO DEL SERVICIO CONTROL, " + LocalDateTime.now() + " EL NUMCDT A CONSULTAR: "
                + controlCdtDto.getParametros().getNumCdt());

        try {
            mediadorService.mediador(version, controlCdtDto);
            Response resultEntity = new Response("OPALO", LocalDateTime.now(), new ResponseParametros(controlCdtDto.getCanal(), controlCdtDto.getParametros()), "controlesCDTs", HttpStatus.OK, "Cdt se ha marcado para renovación");
            return new ResponseEntity<>(resultEntity, HttpStatus.OK);
        } catch (ControlCdtsException e) {
            // Captura la excepción personalizada y crea una respuesta personalizada
            Response response = new Response();

            response.setResponseCode(HttpStatus.valueOf(e.getHttpStatus()));
            response.setErrorMessage(e.getMessage());
            response.setApp("OPALO");
            response.setDate(LocalDateTime.now());
            response.setParametrosRequest(new ResponseParametros(controlCdtDto.getCanal(), controlCdtDto.getParametros()));
            response.setNameService("controlesCDTs");

            return new ResponseEntity<>(response, HttpStatus.valueOf(e.getHttpStatus()));
        }
    }

}
