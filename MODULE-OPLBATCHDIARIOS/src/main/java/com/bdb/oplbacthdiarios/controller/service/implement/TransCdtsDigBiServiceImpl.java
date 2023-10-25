package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.persistence.repository.RepositorySalMaeVentas;
import com.bdb.oplbacthdiarios.controller.service.interfaces.TransCdtsDigBiService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
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
import java.util.HashMap;
import java.util.Map;

@Service
@CommonsLog
public class TransCdtsDigBiServiceImpl implements TransCdtsDigBiService {

    final JobLauncher jobLauncher;

    final Job job;

    final ResponseService responseService;

    private final RepositorySalMaeVentas repositorySalMaeVentas;

    public TransCdtsDigBiServiceImpl(JobLauncher jobLauncher,
                                     @Qualifier(value = "JobReportTransCdtsDig") Job job,
                                     ResponseService responseService,
                                     RepositorySalMaeVentas repositorySalMaeVentas) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.responseService = responseService;
        this.repositorySalMaeVentas = repositorySalMaeVentas;
    }


    /**
     * Consume el Job para generar el cruce de información (ejecución del query) y generación del archivo.
     * @param urlRequest    Parametro enviado para conocer la Url del consumo.
     * @return  ResponseEntity<ResponseBatch>, contiene la información de la ejeución del Job y los pasos ejecutados.
     * @throws JobParametersInvalidException Validación de los parametros enviados a traves del Job.
     * @throws JobExecutionAlreadyRunningException  Validación de la ejecución del Job.
     * @throws JobRestartException  Validación del reinicio del job.
     * @throws JobInstanceAlreadyCompleteException Validación para conocer si el Job ha sido completado o se encuentra
     * incompleto.
     */
    @Override
    public ResponseEntity<ResponseBatch> consumeJobs(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
        deleteData();
        log.info("DATA ELIMINADA...");
        JobExecution transCdtsDigBi = runJob(job, urlRequest);
        if (transCdtsDigBi.getStatus().isUnsuccessful()) {
            log.error("OCURRIO UN ERROR :(");
            transCdtsDigBi.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                    transCdtsDigBi.getJobId().toString(), "N/A", "SE PRESENTO FALLO AL GENERAR EL REPORTE", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(responseBatch(transCdtsDigBi, urlRequest), validateStatus(transCdtsDigBi));
        }
    }

    /**
     * Se elimina los datos existentes de la tabla <u>SAL_MAEVENTAS</u> para no duplicar datos.
     */
    public void deleteData() {
        repositorySalMaeVentas.deleteAllInBatch();
    }

    /**
     * Es utilizado para generar la respuesta de la ejecución.
     * @param jobExecution
     * @param urlRequest
     * @return {@link ResponseBatch}
     */
    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest) {
        return responseService.resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
    }

    /**
     * Ejecución del Job(s).
     * @param job Se debe enviar el <u>Job</u> a ejecutar.
     * @param urlRequest Se envia la Url de la ejecución del servicio.
     * @return  JobExecution, revisar {@link JobExecution}.
     * @throws JobParametersInvalidException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     */
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

    /**
     * Se valida la respuesta del Job. Si:
     * <ul>
     *     <li>OK se envia status de Exito, <u><b>(Cod: 200)</b></u>.</li>
     *     <li>Falla se envia status de Error, <u><b>(Cod: 500)</b></u>.</li>
     * </ul>
     * @param jobExecution  Se toma el resultado del Job.
     * @return  HttpStatus, revisar {@link HttpStatus}.
     */
    public HttpStatus validateStatus(JobExecution jobExecution) {
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }


}
