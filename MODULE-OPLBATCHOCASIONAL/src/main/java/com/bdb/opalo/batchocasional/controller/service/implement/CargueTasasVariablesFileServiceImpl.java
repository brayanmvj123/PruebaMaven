package com.bdb.opalo.batchocasional.controller.service.implement;

import com.bdb.opalo.batchocasional.controller.service.interfaces.CargueTasasVariablesFileService;
import com.bdb.opalo.batchocasional.persistence.Response.ResponseBatch;
import com.bdb.opalo.batchocasional.persistence.Response.ResponseService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.repository.RepositoryOplTasaVariable;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@CommonsLog
public class CargueTasasVariablesFileServiceImpl implements CargueTasasVariablesFileService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private RepositoryOplTasaVariable repositoryOplTasaVariable;

    @Autowired
    @Qualifier(value="JobTasasVariablesFile")
    Job jobTasaVariableFile;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    ResponseService responseService;

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    @Override
    public ResponseEntity<ResponseBatch> consumeJobsTasaVariableFile(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        BatchStatus resultado = null ;
        try {
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
            //serviceFTP.connectToFTP(host, port, user, pass);
            String fechaActual = serviceFTP.obtenerFechaActual();
            //"/PRUEBA_ACCIONES/"+fechaActual+"/INPUT/";
            String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%OCASIONAL%",fechaActual);
            //la.verificarDirectorio();
            List<String> nombres = serviceFTP.getNameParameters();
            //System.out.println("NOMBRE: " + nombres.get(0));
            String nombreArchivo="OPL_TASAS_VAR";
            ByteArrayOutputStream resource = null;
            resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
            this.archivo = resource;
            log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
            deleteData();
            log.info("----------SE PROCEDE JOB--------------");
            JobExecution reportCargueTasaVariable = runJob(jobTasaVariableFile, urlRequest);
            if (reportCargueTasaVariable.getStatus().isUnsuccessful()){
                log.error("OCURRIO UN ERROR");
                reportCargueTasaVariable.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                        reportCargueTasaVariable.getJobId().toString(), "N/A", "EL CRUCE HA FALLADO", null);
                serviceFTP.disconnectFTP();
                return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                serviceFTP.disconnectFTP();
                return new ResponseEntity<>(responseBatch(reportCargueTasaVariable, urlRequest), validateStatus(reportCargueTasaVariable));
            }
        } catch (ErrorFtps ftpErrors) {
            log.error(ftpErrors.getMessage());
        }
        return null;
    }

    @Override
    public JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(urlRequest.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        return jobExecution;
    }

    @Override
    public void deleteData() {
        repositoryOplTasaVariable.deleteAll();
    }


    @Override
    public ResponseEntity<ResponseBatch> almacenar(List<? extends OplHisTasaVariableEntity> items) {
        repositoryOplTasaVariable.saveAll(items);
        return null;
    }

    @Override
    public ByteArrayOutputStream cargarArchivo() {
        return archivo;
    }

    public HttpStatus validateStatus(JobExecution jobExecution){
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }

    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest){
        return responseService.resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
    }
}
