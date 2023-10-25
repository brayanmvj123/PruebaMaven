package com.bdb.opalo.oficina.controller.service.implement.startjob;

import com.bdb.opalo.oficina.controller.service.interfaces.startjob.StartJobService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import java.util.Map;

@CommonsLog
@Service
public class StartJobServiceImpl implements StartJobService {

    /**
     * @param jobSend Job enviado a ejecutar.
     * @return JobExecution, contiene la información de la ejecución o resultado de la tarea o Job.
     * @throws JobParametersInvalidException       Exception for {@link Job} to signal that some {@link JobParameters} are
     *                                             invalid.
     * @throws JobExecutionAlreadyRunningException Excepciones al momento de estar la tarea ejecutandose.
     * @throws JobRestartException                 An exception indicating an illegal attempt to restart a job.
     * @throws JobInstanceAlreadyCompleteException An exception indicating an illegal attempt to restart a job that was already
     *                                             completed successfully
     */
    public JobExecution startJob(Job jobSend, Map<String, JobParameter> maps, JobLauncher jobLauncher) throws
            JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        log.info("INICIA EL SIGUIENTE PASO :)");

        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(jobSend, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        log.info(jobExecution.getStatus().getBatchStatus().toString());

        jobExecution.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));

        return jobExecution;
    }
}
