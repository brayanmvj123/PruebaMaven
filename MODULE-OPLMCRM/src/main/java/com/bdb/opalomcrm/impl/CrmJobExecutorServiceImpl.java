package com.bdb.opalomcrm.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bdb.opalomcrm.interfaces.CrmJobExecutorService;

@Service
public class CrmJobExecutorServiceImpl implements CrmJobExecutorService {
	
	@Autowired
    JobLauncher jobLauncher;
	
	@Autowired
    @Qualifier("novedadesBeanJob")
    Job novedadesBeanJob; 
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Async
	public CompletableFuture<String> execJob(JobParameters jobParameters) throws InterruptedException {
		logger.info("Comienza ejecucion hilo Job...");
		Thread.sleep(10000);
		String results = "";
		
		try {
			jobLauncher.run(novedadesBeanJob, jobParameters);
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


}
