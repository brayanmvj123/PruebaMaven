package com.bdb.opalomcrm.interfaces;

import java.util.concurrent.CompletableFuture;

import org.springframework.batch.core.JobParameters;

public interface CrmJobExecutorService {
	
	public CompletableFuture<String> execJob(JobParameters jobParameters) throws InterruptedException;

}
