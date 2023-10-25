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
package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarDerpatriemiDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.opaloshare.util.Constants;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CargueDcvDerechosPatrimonialesService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Predicate;

/**
 * Service encargado de leer y realizar el cargue del archivo DCV dcob01s
 *
 * @author: Esteban Talero
 * @version: 26/11/2020
 * @since: 19/11/2020
 */
@Service("serviceDcvDerechosPatrimoniales")
public class CargueDcvDerechosPatrimonialesImpl implements CargueDcvDerechosPatrimonialesService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final Job job;

    final JobLauncher jobLauncher;

    final ResponseService responseService;

    final RepositoryCarDerpatriemiDownEntity repoDcvPatrimonialesCarga;

    final RepositoryTipVarentorno repositoryTipVarentorno;

    final SharedService sharedService;

    public CargueDcvDerechosPatrimonialesImpl(RepositoryCarDerpatriemiDownEntity repoDcvPatrimonialesCarga,
                                              @Qualifier(value = "JobDcvDerechosPatrimoniales") Job job,
                                              JobLauncher jobLauncher,
                                              SharedService sharedService,
                                              ResponseService responseService,
                                              RepositoryTipVarentorno repositoryTipVarentorno) {
        this.sharedService = sharedService;
        this.job = job;
        this.jobLauncher = jobLauncher;
        this.repoDcvPatrimonialesCarga = repoDcvPatrimonialesCarga;
        this.responseService = responseService;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
    }

    /**
     * Ejecución de los <i>Job(s)</i> solicitados.
     *
     * @param urlRequest Información del host que ha enviado la petición.
     * @return ResponseEntity<ResponseBatch>, valida la ejecución del job a cargo del servicio.
     * @throws JobParametersInvalidException       Exception for {@link Job} to signal that some {@link JobParameters}
     *                                             are invalid.
     * @throws JobExecutionAlreadyRunningException Excepciones al momento de estar la tarea ejecutandose.
     * @throws JobRestartException                 An exception indicating an illegal attempt to restart a job.
     * @throws JobInstanceAlreadyCompleteException An exception indicating an illegal attempt to restart a job that was
     *                                             already completed successfully.
     * @throws IOException                         Signals that an I/O exception of some sort has occurred. This class
     *                                             is the general class of exceptions produced by failed or interrupted
     *                                             I/O operations.
     */
    @Override
    public ResponseEntity<ResponseBatch> loadDerechosPatrimoniales(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException {

        logger.info("Inicio el proceso de carga de emision derechos patrimoniales EMISOR...");

        logger.info("eliminarDepartiEmi ...");
        repoDcvPatrimonialesCarga.deleteAllInBatch();
        logger.info("leerArchivoDcvFtp ...");
        if (getFile()) {
            JobParameters parameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .addLong("time", System.currentTimeMillis()).toJobParameters();

            logger.info("joblauncher Run...");
            JobExecution jobExecution = jobLauncher.run(job, parameters);

            logger.info("JobExecution: {}", jobExecution.getStatus());

            logger.info("JobDcvPatrimoniales Job is Running...");
            while (jobExecution.isRunning()) {
                logger.info("...");
            }

            if (validarPeriodo())
                return responseService.getResponseJob(jobExecution,
                        "EL ARCHIVO HA SIDO CARGADO CON EXITO.",
                        urlRequest);
            else {
                logger.info("LA VALIDACION DEL PERIODO NO FUE EXITOSA :(");
                repoDcvPatrimonialesCarga.deleteAllInBatch();
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(), "N/A",
                        "LA VALIDACION DEL PERIODO NO FUE EXITOSA :(", null);
                return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            logger.info("NO SE ENCONTRO EL ARCHIVO :(");
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    urlRequest.getRequestURL().toString(), "No se alcanzo a ejecutar el Job", "N/A",
                    "El archivo no se encontro en la ruta indicada", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene el <u>archivo plano</u> buscado en el sitio FTPS asignado a la aplicación.
     *
     * @return Un boolean, indicando:
     * <ul>
     *     <li>TRUE, si el archivo fue encontrado, bajado a memoria y esta listo para ser trabajado.</li>
     *     <li>FALSE, si el archivo NO fue encontrado o ocurrio algun error al momento de trabajar con el.</li>
     * </ul>
     */
    public boolean getFile() throws IOException {
        boolean saber = sharedService.obtenerArchivo(Constants.FILE_DCV_DERPATRI,
                "INPUT",
                "CONFIGURATION");
        if (saber) sharedService.limpiarArchivo(null, 70);
        return saber;
    }

    public boolean validarPeriodo() {
        VarentornoDownEntity varentorno = repositoryTipVarentorno.findByDescVariable("FILE_DATE_PATRIMONIALES_EMI");
        Predicate<String> validarFechaIni = x -> LocalDate.parse(varentorno.getValVariable())
                .isBefore(LocalDate.parse(x, DateTimeFormatter.ofPattern("yyyyMMdd")));
        Predicate<String> validarFechaFin = x -> LocalDate.parse(x, DateTimeFormatter.ofPattern("yyyyMMdd"))
                .isEqual(LocalDate.now());
        long item = repoDcvPatrimonialesCarga.findAll()
                .stream()
                .limit(1)
                .filter(x -> validarFechaIni.test(x.getFechaIni()))
                .filter(x -> validarFechaFin.test(x.getFechaFin()))
                .count();
        if (item == 1) repositoryTipVarentorno.save(new VarentornoDownEntity(varentorno.getCodVariable(),
                varentorno.getDescVariable(), LocalDate.now().toString()));
        return item == 1;
    }


}
