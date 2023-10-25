package com.bdb.oplbatchmensual.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.OplCarCalendarconDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarCalendarconDownEntity;
import com.bdb.oplbatchmensual.controller.service.interfaces.CalendarioCofnalService;
import com.bdb.oplbatchmensual.persistence.jsonschema.CalendarioCofnal.JSONResponseCalendarioCofnal;
import com.bdb.oplbatchmensual.persistence.response.batch.ResponseBatch;
import com.bdb.oplbatchmensual.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@CommonsLog
public class CalendarioCofnalServiceImpl implements CalendarioCofnalService {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobCalendarioCofnal")
    Job jobCalendarioCofnal;

    @Autowired
    private RepositoryCarCalendarconDownEntity repositoryCarCalendarconDownEntity;

    @Autowired
    ResponseService responseService;

    @Autowired
    SharedService sharedService;

    @Override
    public void deleteData() {
        repositoryCarCalendarconDownEntity.deleteAll();
    }

    @Override
    public void migrarCalendarioCofnal(String host) {
        List<OplCarCalendarconDownEntity> calendario = consumeServiceCalendarioCofnal(sharedService.generarUrl(host,
                "OPLBATCHMENSUAL"));
        log.info("SE PROCEDE A ARMAR EL CALENDARIO.");
        almacenar(calendario);
    }

    @Override
    public void almacenar(List<? extends OplCarCalendarconDownEntity> items) {
        repositoryCarCalendarconDownEntity.saveAll(items);
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobsCalendario(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
        deleteData();
        log.info("----------SE PROCEDE JOB--------------");
        JobExecution actualizacionCalendarioCofnal = runJob(jobCalendarioCofnal, urlRequest);
        if (actualizacionCalendarioCofnal.getStatus().isUnsuccessful()) {
            log.error("OCURRIO UN ERROR");
            actualizacionCalendarioCofnal.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                    actualizacionCalendarioCofnal.getJobId().toString(), "N/A", "EL CRUCE HA FALLADO", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(responseBatch(actualizacionCalendarioCofnal, urlRequest), validateStatus(actualizacionCalendarioCofnal));
        }
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

    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest) {
        return responseService.resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
    }

    public HttpStatus validateStatus(JobExecution jobExecution) {
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }


    /**
     * Este metodo consume el servicio expuesto en el modulo OPLCOFNAL, el cual se encarga de consultar el calendario,
     *
     * @param host <b>Host</b>, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return lista valores de <b><i>CALENDARIO COFNAL</i></b>.
     */
    public List<OplCarCalendarconDownEntity> consumeServiceCalendarioCofnal(String host) {
        log.info("ENTRA AL CONSUMO DE CALENDARIO COFNAL.");

        log.info("NATES DE LA DATA");
        final String url = host + "OPLCOFNAL/OPALOcofnal/v1/calendario";
        //"http://localhost:8080/OPALOcofnal/v1/calendario";
        log.info("LA URL ES: " + url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONResponseCalendarioCofnal> response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<JSONResponseCalendarioCofnal>() {
                });
        log.info("SE OBTUVO LA DATA");
        if (response.getStatusCode().is2xxSuccessful() && Objects.requireNonNull(response.getBody()).getResult() != null) {
            JSONResponseCalendarioCofnal body = response.getBody();
            return body.getResult();
        } else {
            log.error("ERROR AL CONSUMIR SERVICIO CALENDARIO COFNAL ...");
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "ERROR AL CONSUMIR SERVICIO CALENDARIO COFNAL ...");
        }
    }
}
