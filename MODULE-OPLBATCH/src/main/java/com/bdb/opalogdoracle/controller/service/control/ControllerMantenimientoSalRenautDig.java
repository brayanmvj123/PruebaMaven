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

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.model.component.ModelCrucePatrimonioRenaut;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Controlador del servicio encargado del Control de marcacion renovacion CDT DIG
 *
 * @author: Esteban Talero
 * @version: 11/11/2020
 * @since: 09/10/2020
 */
@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@Api(value = "Modulo Batch", description = "Servicio Encargado de limpiar y posteriormente guardar en la tabla de " +
        "Salida de RenautDig")
public class ControllerMantenimientoSalRenautDig {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    SharedService serviceShared;

    @Autowired
    RepositorySalRenautdig repositorySalRenautdig;

    @Autowired
    @Qualifier(value = "JobControlSalRenautDig")
    Job job;

    @Autowired
    @Qualifier(value = "JobControlSalDcvRenautDig")
    Job jobDcv;

    private final Logger logger = LoggerFactory.getLogger(ControllerMantenimientoSalRenautDig.class);

    @GetMapping("controlRenautDig")
    @ApiOperation(value = "Control RenautDig", notes = "Limpia los registros de dias anteriores y guarda el de la fecha " +
            "actual los datos de los nuevos CDTs digitales a realizar marcacion Automatica")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")})
    public BatchStatus loadenvioReporteHisLogin(HttpServletRequest http) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
        logger.info("SE INICIA EL PROCESO DE MANTENIMIENTO TABLA SAL RENAUT DIG");

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(http.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        logger.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        System.out.println(jobExecution.getStatus().getBatchStatus().toString());

        if (jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando carga en " +
                    "tabla de salida renautDig: " + jobExecution.getStatus());
        }

        if (jobExecution.getStatus().getBatchStatus().toString().equals("COMPLETED")) {
            JobExecution jobExecutionDcv = jobLauncher.run(jobDcv, parameters);
            while (jobExecutionDcv.isRunning()) {
                System.out.println("...");
            }

            System.out.println(jobExecutionDcv.getStatus().getBatchStatus().toString());
            if (jobExecutionDcv.getStatus().getBatchStatus().toString().equals("FAILED")) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando carga en " +
                        "tabla de DCV de salida renautDig: " + jobExecution.getStatus());
            }
            return jobExecutionDcv.getStatus();
        }

        return jobExecution.getStatus();
    }

    @PostMapping(value = "controlDcvRenautDig")
    @ApiOperation(value = "Control DcvRenautDig", notes = "Limpia los registros de dias anteriores y guarda el de la fecha " +
            "actual los datos de los nuevos CDTs digitales a realizar marcacion Automatica en Dcv")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")})
    public String mantenimientoDcvRenautDig(HttpServletRequest http) {
        String host = serviceShared.generarUrlBatch(http.getRequestURL().toString());
        final String url = host + "OPLSSQLS/CDTSDesmaterializado/v1/controlDcvRenautDig";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
        });
        return response.getBody();

    }

    /**
     * Metodo encargado de consultar los CDTs del cruce de las tablas de CDTs y Mae que tengan la marca
     * de renovacion en la tabla de control CDT HISCTRCDT
     *
     * @return Listado del cruce de CDTs patrimonio que tengan la marca de renovacion
     */
    @PostMapping(value = "cruceInformacionRenautDig", produces = {"application/json"})
    public List<ModelCrucePatrimonioRenaut> consultaCrucePatrimonio() {
        List<SalRenautdigEntity> listResult = repositorySalRenautdig.findAll();
        List<ModelCrucePatrimonioRenaut> listResulCruce = new ArrayList<>();
        if (listResult.size() > 0) {
            listResult.forEach(dataCruce -> {
                ModelCrucePatrimonioRenaut modelCrucePatrimonioRenaut = new ModelCrucePatrimonioRenaut();
                modelCrucePatrimonioRenaut.setNumCdt(dataCruce.getNumCdt().toString());
                modelCrucePatrimonioRenaut.setCodIsin(dataCruce.getCodIsin());
                modelCrucePatrimonioRenaut.setTipId(dataCruce.getTipId());
                modelCrucePatrimonioRenaut.setNumTit(dataCruce.getNumTit());
                modelCrucePatrimonioRenaut.setNomTit(dataCruce.getNomTit());
                modelCrucePatrimonioRenaut.setIntBruto(dataCruce.getIntBruto().toString());
                modelCrucePatrimonioRenaut.setRteFte(dataCruce.getRteFte().toString());
                modelCrucePatrimonioRenaut.setIntNeto(dataCruce.getIntNeto().toString());
                modelCrucePatrimonioRenaut.setCapPg(dataCruce.getCapPg().toString());
                listResulCruce.add(modelCrucePatrimonioRenaut);
            });
        }
        return listResulCruce;
    }
}
