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
package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGeneralesService;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvJobExecutorService;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvWService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CompletableFuture;

/**
 * Service encargado de cargar y realizar el cruce de cuentas inversionistas por accionistas principales
 * y secundarios
 *
 * @author: Andres Marles
 * @version: 25/10/2021
 * @since: 25/10/2021
 */
@Service
public class CtaInvJobExecutorImpl implements CtaInvJobExecutorService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobCuentas")
    Job jobCuentas;

    @Autowired
    @Qualifier(value = "JobCliente")
    Job jobAccionista;

    @Autowired
    @Qualifier(value = "JobCtaInvxCli")
    Job jobCtaInvxAcc;

    @Autowired
    @Qualifier("serviceCuentas")
    CtaInvWService cuentaService;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;


    @Autowired
    private MetodosGeneralesService metodoGeneralesService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Async("taskExecCtaInv")
    public CompletableFuture<String> execJobCtaInv(JobParameters jobParameters) throws InterruptedException {
        logger.info("Comienza ejecucion hilo Job...");
//        Thread.sleep(20000);
        String results = "";

        try {
            JobExecution jobExecution = jobLauncher.run(jobCuentas, jobParameters);

            logger.info("JobExecution: " + jobExecution.getStatus());

            while (jobExecution.isRunning()) {
                System.out.println("...");
            }

            logger.info("Status jobCuentas " + jobExecution.getStatus().getBatchStatus().toString());

            if (jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")) {
                logger.error("OCURRIO UN ERROR :(");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando carga en jobCuentas: " + jobExecution.getStatus());
            }

            if (jobExecution.getStatus().getBatchStatus().toString().equals("COMPLETED")) {
                logger.info("Inicio Homologacion ");
                boolean resultHom = homologaciones();
                if (!resultHom) {
                    logger.error("Fallo homologaciones.. ");
                } else {
                    logger.info("Inicia ejecucion del jobAccionista.... ");
                    //Limpia y carga la tabla DCV de salida RenautDig y deja los CDT's
                    JobExecution jobExecutionDcv = jobLauncher.run(jobAccionista, jobParameters);
                    while (jobExecutionDcv.isRunning()) {
                        System.out.println("...");
                    }

                    System.out.println(jobExecutionDcv.getStatus().getBatchStatus().toString());

                    if (jobExecutionDcv.getStatus().isUnsuccessful()) {
                        logger.error("OCURRIO UN ERROR :(.");
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando carga en jobAccionista: " + jobExecutionDcv.getStatus());
                    } else {
                        logger.info("Inicia ejecucion del jobCtaInvxAcc... ");
                        JobExecution jobExecutionAperSobreRenaut = jobLauncher.run(jobCtaInvxAcc, jobParameters);
                        while (jobExecutionAperSobreRenaut.isRunning()) {
                            System.out.println("...");
                        }

                        System.out.println(jobExecutionAperSobreRenaut.getStatus().getBatchStatus().toString());

                        if (jobExecutionAperSobreRenaut.getStatus().isUnsuccessful()) {
                            logger.error("OCURRIO UN ERROR :(..");
                            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando cruce CtaInvxAccionista " + jobExecutionAperSobreRenaut.getStatus());
                        }
                    }
                }
            }
            results = "Job execution with Parametes: " + jobParameters.toString();
        } catch (JobExecutionAlreadyRunningException e) {
            logger.error("Error Exception: JobExecutionAlreadyRunningException ");
            e.printStackTrace();
        } catch (JobRestartException e) {
            logger.error("Error Exception: JobRestartException ");
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            logger.error("Error Exception: JobInstanceAlreadyCompleteException ");
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            logger.error("Error Exception: JobParametersInvalidException ");
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(results);
    }


    public boolean homologaciones() {
        try {
            //ESTE METODO PERMITE ACTUALIZAR EL ID DE LOS TIPOS DE DOCUMENTOS (CC,TI,NIP,CE,PAP) EN EL CAMPO TIPO_DOCUMENTO (STRING)
            //DE LA TABLA DE CARGA DE LAS CUENTAS
            cuentaService.actualizarTipoDocumento();

            //ESTE METODO PERMITE ACTUALIZAR EL ID DE LOS TIPOS DE DOCUMENTOS (NIT) EN EL CAMPO TIPO_DOCUMENTO (STRING)
            //DE LA TABLA DE CARGA DE LAS CUENTAS
            cuentaService.actualizarTipoDocumentoNIT();
            //VERIFICAR CODIGOS NUMERICOS DE DEPARTAMENTOS
            cuentaService.verificarCodigosDep();
            //HOMOLOGACIONES PAIS , DEPARTAMENTOS Y CIUDADES
            cuentaService.homologarNulos();
            cuentaService.homologarPaises();
            cuentaService.homologarDepartamento();
            cuentaService.homologarCiudades();

            cuentaService.homologarCREENulos();
            cuentaService.corregirCodCree();
            cuentaService.homologarCREE();

            cuentaService.corregirCodSectorNulos();
            cuentaService.corregirCodSector();
            cuentaService.homologarSector();
            cuentaService.corregirCodSectorSec();
            cuentaService.homologarSectorSec();

            cuentaService.homologarIndExt();
            cuentaService.guardarResultadoHomologacion(true);
            return true;
        } catch (Exception e) {
            cuentaService.guardarResultadoHomologacion(false);
            return false;
        }
    }
}