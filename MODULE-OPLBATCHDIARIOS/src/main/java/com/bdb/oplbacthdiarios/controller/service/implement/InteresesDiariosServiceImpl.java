package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import com.bdb.opaloshare.persistence.entity.CarIntefectDcvEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuIntefectDcv;
import com.bdb.opaloshare.persistence.repository.RepositoryCarIntereses;
import com.bdb.oplbacthdiarios.controller.service.interfaces.InteresesDiariosService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CommonsLog
public class InteresesDiariosServiceImpl implements InteresesDiariosService {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    private SharedService sharedService;

    @Autowired
    ResponseService responseService;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobCargaIntereses")
    Job job;

    @Autowired
    private RepositoryCarIntereses repositoryCarIntereses;

    @Autowired
    private RepositoryAcuIntefectDcv repositoryAcuIntefectDcv;

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    @Override
    public void almacenarData(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public ByteArrayOutputStream cargarData() {
        return archivo;
    }

    @Override
    public void almacenar(List<? extends CarIntefectDcvEntity> items) {
        repositoryCarIntereses.saveAll(items);
    }

    @Override
    public void eliminarInformacion() {
        repositoryCarIntereses.deleteAll();
    }

    @Override
    public void almacenarDataAcumulada(List<? extends AcuIntefectDcvEntity> items) {
        repositoryAcuIntefectDcv.saveAll(items);
    }

    @Override
    public ResponseEntity<ResponseBatch> interesesGenerados(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        log.info("ENTRO AL CONTROLLER DE INTERESES EFECTUADOS");

        eliminarInformacion();

        if (getFile()) {

            Map<String, JobParameter> maps = new HashMap<>();
            maps.put("time", new JobParameter(System.currentTimeMillis()));
            JobParameters parameters = new JobParameters(maps);
            JobExecution jobExecution = jobLauncher.run(job, parameters);

            log.info("JobExecution: " + jobExecution.getStatus());

            while (jobExecution.isRunning()) {
                log.info("...");
            }

            log.info(jobExecution.getStatus().getBatchStatus().toString());

            if (jobExecution.getStatus().isUnsuccessful()) {
                log.info("ERROR EN EL JOB :(");
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                        jobExecution.getStatus().toString(), "La carga del archivo fue FALLIDO.",
                        jobExecution.getStepExecutions());
                return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.OK.toString(),
                        urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                        jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
                return new ResponseEntity<>(responseEntity, HttpStatus.OK);
            }
        }else{
            log.info("NO SE ENCONTRO EL ARCHIVO :(");
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    urlRequest.getRequestURL().toString(), "No se alcanzo a ejecutar el Job", "N/A",
                    "El archivo no se encontro en la ruta indicada", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public boolean getFile() {
        String inicioArchivo = sharedService.nombreArchivo("FILE_INPUT_INTERESES_DCV");
        LocalDate fecha = LocalDate.now();
        String nombreArchivo = inicioArchivo.replace("DDMMAAAA",
                String.format("%02d", fecha.getDayOfMonth()) + String.format("%02d", fecha.getMonthValue()) + (fecha.getYear()));
        return sharedService.obtenerArchivo(nombreArchivo, "INPUT", "CONFIGURATION");
    }

}
