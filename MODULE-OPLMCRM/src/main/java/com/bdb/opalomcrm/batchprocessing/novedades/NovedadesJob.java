package com.bdb.opalomcrm.batchprocessing.novedades;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import com.bdb.opalomcrm.common.Constants;
import com.bdb.opaloshare.persistence.entity.SalNovCrmCliEntity;
import com.bdb.opaloshare.persistence.repository.OplNovcrmcliSalDownTblRepository;

@Component
public class NovedadesJob extends JobExecutionListenerSupport {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

    @Autowired
	WriterNovedades writerNovedadesSal;
	
	@Autowired
	ProcessorNovedades processorNovedades;

	@Autowired
	private OplNovcrmcliSalDownTblRepository repoNov;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		writerNovedadesSal.ip =jobExecution.getJobParameters().getString("ip");

	}

	@Bean(name = "novedadesBeanJob")
	public Job novedadJob() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		
		HashMap<String, Direction> sorts = new HashMap<>();
		sorts.put("item", Direction.ASC);
		
		// ******** Reader 'SALNOV' configuration ********
		RepositoryItemReader<SalNovCrmCliEntity> readerSalNovCrm = new RepositoryItemReader<>();
		readerSalNovCrm.setRepository(repoNov);
		readerSalNovCrm.setMethodName("findAll");
		readerSalNovCrm.setSort(sorts);
		// *****************************************
				
		Step stepSalNovCrm = stepBuilderFactory.get("step-salnovcrm").<SalNovCrmCliEntity, SalNovCrmCliEntity>chunk(1).
				reader(readerSalNovCrm).processor(processorNovedades)
				.writer(writerNovedadesSal).build();
		
		Job job = jobBuilderFactory.get("load-novedades-job").incrementer(new RunIdIncrementer()).listener(this)
				.start(stepSalNovCrm).build();

		return job;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info(Constants.JOBNOV_NAME + " " + Constants.JOBNOV_OK);
			logger.info("BATCH STATUS: " + jobExecution.getStatus());
			logger.info("BATCH START TIME: " + jobExecution.getStartTime());
			logger.info("BATCH END TIME: " + jobExecution.getEndTime());
		}else {
			logger.error(Constants.JOBNOV_NAME + " " +  Constants.JOBNOV_ERROR);
			logger.error("BATCH STATUS: " + jobExecution.getStatus());
			logger.error("BATCH START TIME: " + jobExecution.getStartTime());
			logger.error("BATCH END TIME: " + jobExecution.getEndTime());
        }
	}

}
