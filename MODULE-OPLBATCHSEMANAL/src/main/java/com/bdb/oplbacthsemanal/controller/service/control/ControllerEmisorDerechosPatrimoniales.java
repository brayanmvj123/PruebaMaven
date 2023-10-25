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
package com.bdb.oplbacthsemanal.controller.service.control;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuDerpatriemiDownEntity;
import com.bdb.opaloshare.util.Constants;
import com.bdb.oplbacthsemanal.controller.service.interfaces.EmisorDcvDerechosPatrimonialesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Controlador del servicio encargado de realizar el cargue del archivo semanal de DCV derechos Patrimoniales
 *
 * @author: Esteban Talero
 * @version: 25/11/2020
 * @since: 24/11/2020
 */
@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@Api(value = "Modulo Batch Semanal", description = "Servicio Modulo Batch Semanal")
public class ControllerEmisorDerechosPatrimoniales {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    JobLauncher jobLauncher;


    @Autowired
    @Qualifier(value = "JobSemanalDcvDerechosPatrimoniales")
    Job job;

    @Autowired
    @Qualifier(value = "JobDepositanteDerechoPatrimonial")
    Job jobDepostitante;

    @Autowired
    EmisorDcvDerechosPatrimonialesService emisorDcvDerechosPatrimonialesService;

    @Autowired
    RepositoryAcuDerpatriemiDownEntity repoDcvSemanalPatrimonialesCarga;

    @GetMapping("carga/semanal/emisor/dcvDerechosPatrimoniales")
    @ApiOperation(value = "Derechos patrimoniales", notes = "Carga del archivo semanal emisor de Deceval con el resultado" +
            "de derechos Patrimoniales hasta guardar la data del mismo en la base de Datos")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")})
    public ResponseEntity<RequestResult<String>> dcvDerechosPatrimoniales(HttpServletRequest request) throws Exception {

        logger.info("inicio job Semanal carga emision derechos patrimoniales...");
        RequestResult<String> result;
        result = new RequestResult<>(request, HttpStatus.OK);

        try {

            logger.info("eliminarAcuDepartiEmi ...");
            repoDcvSemanalPatrimonialesCarga.deleteAll();
            logger.info("leerArchivoSemanalDcvFtp ...");
            boolean resultadoConversion = emisorDcvDerechosPatrimonialesService.leerArchivoDcvFtp(
                    Constants.FILE_WEEK_DCV_DERPATRI);
            logger.info("resultadoConversionDcvSemanal... " + resultadoConversion);
            if (resultadoConversion) {
                JobParameters parameters = new JobParametersBuilder()
                        .addDate("date", new Date())
                        .addLong("time", System.currentTimeMillis()).toJobParameters();

                logger.info("joblauncher Run...");
                JobExecution jobExecution = jobLauncher.run(job, parameters);

                System.out.println("JobExecution: " + jobExecution.getStatus());
                logger.info("JobExecution: " + jobExecution.getStatus());

                System.out.println("JobSemanalDcvPatrimoniales Job is Running...");
                while (jobExecution.isRunning()) {
                    System.out.println("...");
                }
                if (jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")) {
                    logger.error("OCURRIO UN ERROR dcv Cargue derechos Patrimoniales... ");
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando carga en " +
                            "tabla OPLACUPATRIEMI: " + jobExecution.getStatus());
                }
                if (jobExecution.getStatus().getBatchStatus().toString().equals("COMPLETED")) {
                    logger.info("leerArchivoAcumuladoDepositanteSemanalDcvFtp ...");
                    boolean resultadoConversionDepo = emisorDcvDerechosPatrimonialesService.leerArchivoDcvFtp(
                            Constants.FILE_DEPOSITOR_DCV_DERPATRI);
                    if (resultadoConversionDepo) {
                        JobExecution jobExecutionDcv = jobLauncher.run(jobDepostitante, parameters);
                        while (jobExecutionDcv.isRunning()) {
                            System.out.println("...");
                        }
                        System.out.println(jobExecutionDcv.getStatus().getBatchStatus().toString());
                        if (jobExecutionDcv.getStatus().getBatchStatus().toString().equals("FAILED")) {
                            logger.error("OCURRIO UN ERROR en Cruce depositante dcv Cargue derechos Patrimoniales... ");
                            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando cruce de " +
                                    "depositante en la tabla OPLACUPATRIEMI: " + jobExecutionDcv.getStatus());
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}
