package com.bdb.oplbacthsemanal.controller.service.implement;

import com.bdb.opaloshare.persistence.repository.RepositorySalPgSemanalDown;
import com.bdb.oplbacthsemanal.controller.service.interfaces.ReportPgWeeklyService;
import com.bdb.oplbacthsemanal.persistence.model.response.ResponseBatch;
import com.bdb.oplbacthsemanal.persistence.model.response.ResponseService;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@CommonsLog
public class ReportPgWeeklyServiceImpl implements ReportPgWeeklyService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobReportPgWeekly")
    private Job jobReportPgWeekly;

    @Autowired
    @Qualifier(value = "JobSimulatorPgWeekly")
    private Job jobSimulatorPgWeekly;

    @Autowired
    @Qualifier(value = "JobCreateFiles")
    private Job jobCreateFiles;

    @Autowired
    private RepositorySalPgSemanalDown repositorySalPgSemanalDown;

    @Autowired
    private ResponseService responseService;

    @Override
    public void deleteData() {
        repositorySalPgSemanalDown.deleteAll();
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobsPgWeekly(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        log.info("SE PROCEDE A VERIFICAR LA DATA Y VALIDAR LA ELIMINACIÓN DE LOS DATOS");

        if (repositorySalPgSemanalDown.countByFactorOplEquals(new BigDecimal(0)) == 0 || repositorySalPgSemanalDown.findAll().isEmpty()){
            log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
            deleteData();
            JobExecution reportPgWeekly = runJob(jobReportPgWeekly, urlRequest);
            if (reportPgWeekly.getStatus().isUnsuccessful()){
                log.error("OCURRIO UN ERROR :(");
                reportPgWeekly.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                        reportPgWeekly.getJobId().toString(), "N/A", "EL CRUCE HA FALLADO", null);
                return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                JobExecution simulatorPgWeekly = runJob(jobSimulatorPgWeekly, urlRequest);
                return new ResponseEntity<>(responseBatch(simulatorPgWeekly, urlRequest), validateStatus(simulatorPgWeekly));
            }
        }else{
            log.warn("HAY AUN CDTS SIN CALCULAR EL FACTOR_OPALO, SE PROCEDE A CALCULAR LOS REGISTROS FALTANTES.");
            JobExecution simulatorPgWeekly = runJob(jobSimulatorPgWeekly, urlRequest);
            return new ResponseEntity<>(responseBatch(simulatorPgWeekly, urlRequest), validateStatus(simulatorPgWeekly));
        }
    }

    public HttpStatus validateStatus(JobExecution jobExecution){
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }

    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest){
        return responseService.resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobCreateFiles(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("COMIENZA LA CREACIONDE ARCHIVOS DE EXCEL PARA ENVIAR A OFICINAS");
        JobExecution simulatorPgWeekly = runJob(jobCreateFiles, urlRequest);
        return responseService.getResponseJob(simulatorPgWeekly, "LA SIMULACIÓN HA FALLADO", urlRequest);
    }


    @Override
    public JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(urlRequest.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());
        while (jobExecution.isRunning()) log.info("...");
        return jobExecution;
    }

}
