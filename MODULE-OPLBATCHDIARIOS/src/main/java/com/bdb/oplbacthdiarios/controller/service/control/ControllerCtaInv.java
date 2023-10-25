/*
 * Copyright (c) 2021 Banco de Bogotá. All Rights Reserved.
 * <p>
 * ACCIONESBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResultJSON;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvJobExecutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Controlador del servicio encargado de cargar y realizar el cruce de cuentas inversionistas por accionistas principales
 * y secundarios
 *
 * @author: Andres Marles
 * @version: 25/10/2021
 * @since: 25/10/2021
 */
@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(value = "Modulo Batch", description = "Servicio Cuenta inversionista por accionista")
public class ControllerCtaInv {

    @Autowired
    CtaInvJobExecutorService ctaInvJobExecutorService;

    /**
     * @param request request de la petición
     * @return Json con status result del JOB
     * @throws Exception exception
     */
    @GetMapping("Cuenta/cargarCuentas")
    @ApiOperation(value = "Carga de cuenta inversionista", notes = "Realiza la carga del archivo de Cuenta inversionista y realiza el cruce por principales y secundarios")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")})
    public ResponseEntity<RequestResult<ResultJSON>> cargarCuentas(HttpServletRequest request) throws Exception {
        log.info("ENTRO AL CONTROLLER DE CUENTAS ... SE INICIA EL CARGUE DE ");

        RequestResult<ResultJSON> result;
        ResultJSON resultJSON = new ResultJSON();
        LocalDate date = LocalDate.now();

        try {
            JobParameters parameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .addString("ip", request.getRemoteAddr()).toJobParameters();

            log.info("joblauncher Run ctaInv...");
            CompletableFuture<String> resJob = ctaInvJobExecutorService.execJobCtaInv(parameters);

            log.info("JobExecution Async ctaInv: " + resJob.toString());

            resultJSON.setDate(date.toString());
            resultJSON.setMessage("Batch-Job-CtaInv");
            result = new RequestResult<>(request, HttpStatus.OK);
            result.setResult(resultJSON);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error general servicio Cuenta/cargarCuentas" +
                    e);
        }

        return ResponseEntity.ok(result);
    }

}
