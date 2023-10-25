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
package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opalogdoracle.controller.service.interfaces.ApersobreRenautService;
import com.bdb.opalogdoracle.controller.service.interfaces.EnvioCancelacionService;
import com.bdb.opalogdoracle.controller.service.interfaces.TramaTraductorCadiService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.RenovacionService;
import com.bdb.opalogdoracle.persistence.model.servicecancel.JSONCancelCdtDig;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.google.gson.Gson;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador del servicio encargado de realizar renovacion automatica de CDTs digitales y generacion de
 * tramas capital intereses y rteFte de traductor CADI
 *
 * @author: Esteban Talero
 * @version: 10/11/2020
 * @since: 09/11/2020
 */
@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerAperSobreRenovat {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobControlSalRenautDig")
    Job jobSalRenaut;

    @Autowired
    @Qualifier(value = "JobControlSalDcvRenautDig")
    Job jobDcv;

    @Autowired
    @Qualifier(value = "JobAperIntoRenaut")
    Job job;

    @Autowired
    @Qualifier(value = "JobSendCdtDigCancelacion")
    Job jobCancel;

    @Autowired
    TramaTraductorCadiService tramaTraductorCadiService;

    @Autowired
    SharedService serviceShared;

    @Autowired
    ApersobreRenautService apersobreRenautService;

    @Autowired
    EnvioCancelacionService envioCancelacionService;

    @Autowired
    RenovacionService renovacionService;

    @Autowired
    RepositoryTipVarentorno repositoryTipVarentorno;

    HttpStatus httpStatus;

    @GetMapping(value = "renovacion/cdtsdigitales")
    @ResponseBody
    public ResponseEntity<RequestResult<HashMap<String, Object>>> apersobreRenaut(HttpServletRequest http) throws Exception {
        log.info("SE INICIA EL PROCESO DE MANTENIMIENTO TABLA SAL RENAUT DIG");

        if(repositoryTipVarentorno.findByDescVariable("RENOVACION_VERSION").getValVariable().equals("v2")) {
            return renovacionService.renovacion(http);
        }

        RequestResult<HashMap<String, Object>> result;
        result = new RequestResult<>(http, HttpStatus.OK);
        String host = serviceShared.generarUrlBatch(http.getRequestURL().toString());
        final String url = host + "OPLBATCH/CDTSDesmaterializado/v1";
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(http.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(jobSalRenaut, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        log.info(jobExecution.getStatus().getBatchStatus().toString());

        //Limpia y carga la tabla de salida RenautDig y deja los CDT's en estado P
        if (jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")) {
            apersobreRenautService.almacenarEstadoRenautSQLServer(url, "RENAUT_FAIL");
            log.error("OCURRIO UN ERROR :(");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando carga en " +
                    "tabla de salida renautDig: " + jobExecution.getStatus());
        }

        if (jobExecution.getStatus().getBatchStatus().toString().equals("COMPLETED")) {
            //Limpia y carga la tabla DCV de salida RenautDig y deja los CDT's
            JobExecution jobExecutionDcv = jobLauncher.run(jobDcv, parameters);
            while (jobExecutionDcv.isRunning()) {
                log.info("...");
            }

            log.info(jobExecutionDcv.getStatus().getBatchStatus().toString());
            if (jobExecutionDcv.getStatus().isUnsuccessful()) {
                apersobreRenautService.almacenarEstadoRenautSQLServer(url, "RENAUT_FAIL");
                log.error("OCURRIO UN ERROR :(");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando carga en " +
                        "tabla de salida DCV renautDig: " + jobExecutionDcv.getStatus());
            } else {
                //Toma de la tabla de salida RenautDig los CDT's con estado en P y Realiza Apertura sobre Renaut
                JobExecution jobExecutionAperSobreRenaut = jobLauncher.run(job, parameters);
                while (jobExecutionAperSobreRenaut.isRunning()) {
                    log.info("...");
                }

                log.info(jobExecutionAperSobreRenaut.getStatus().getBatchStatus().toString());
                httpStatus = apersobreRenautService.getCdtsRenaut().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
                if (jobExecutionAperSobreRenaut.getStatus().isUnsuccessful()) {
                    apersobreRenautService.almacenarEstadoRenautSQLServer(url, "RENAUT_FAIL");
                    log.error("OCURRIO UN ERROR :(");
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando apertura " +
                            "sobre renovacion: " + jobExecutionAperSobreRenaut.getStatus());
                } else {
                    //Genera las tramas de Capital, Intereses y Rte Fte para Traductor CADI
                    String tramaTraductor = tramaTraductorCadiService.tramasTraductorCadi(url, http.getRequestURL().toString());

                    if (Optional.ofNullable(tramaTraductor).isPresent() && !tramaTraductor.isEmpty()) {
                        JSONObject body = new JSONObject(tramaTraductor);
                        HashMap<String, Object> resultTramasCadi = new Gson().fromJson(body.getJSONObject("result")
                                .toString(), HashMap.class);
                        result.setResult(resultTramasCadi);
                    }
                    apersobreRenautService.almacenarEstadoRenautSQLServer(url, "RENAUT_COMPLETED");
                }
            }
        }

        if (getSendCdtsDigToDcvBta(parameters, url).isUnsuccessful()){
            log.error("OCURRIO UN ERROR EN EL ENVIO DE CDTS DIGITALES CANCELADOS :(");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando el envio de CDTs " +
                    "Digitales a la DECEVAL BTA, el proceso de cancelación ha fallado. Inicio proceso de Cancelación: FALLIDO.");
        }

        return ResponseEntity.ok(result);
    }

    private BatchStatus getSendCdtsDigToDcvBta(JobParameters parameters, String url) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobExecution jobExecutionCancel = jobLauncher.run(jobCancel, parameters);
        if (jobExecutionCancel.getStatus().isUnsuccessful())
            apersobreRenautService.almacenarEstadoRenautSQLServer(url, "CANCELACION_FAIL");
        else
            apersobreRenautService.almacenarEstadoRenautSQLServer(url, "CANCELACION_COMPLETED");
        return jobExecutionCancel.getStatus();
    }

    @PostMapping(value = "envioCancelaciones")
    public List<JSONCancelCdtDig> envioCancelaciones(){
        log.info("Se inicia el envio de las cancelaciones almacenadas en la PGDIGITAL.");
        return envioCancelacionService.envioCancelaciones();
    }
}
