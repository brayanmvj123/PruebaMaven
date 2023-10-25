package com.bdb.oplbacthsemanal.schudeler.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bdb.oplbacthsemanal.schudeler.load.ReporteCdtCanceladosTasklet;
import com.bdb.oplbacthsemanal.schudeler.load.ReporteCdtReinvertidosTasklet;

@Configuration
@EnableBatchProcessing
public class ReadCdtSemanalTesoreria {

private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public ReporteCdtReinvertidosTasklet reporteCdtReinvertidosTasklet() {
    	logger.info("start ReporteCdtReinvertidosTasklet...");
    	return new ReporteCdtReinvertidosTasklet();
    }
    
    @Bean
    public ReporteCdtCanceladosTasklet reporteCdtCanceladosTasklet() {
    	logger.info("start ReporteCdtCanceladosTasklet...");
    	return new ReporteCdtCanceladosTasklet();
    }
    
    @Bean Step reporteCdtReinvertidosStep(StepBuilderFactory stepBuilders) {
    	logger.info("start reporteCdtReinvertidosStep...");
    	return stepBuilders.get("reporteCdtReinvertidosStep")
				.tasklet(reporteCdtReinvertidosTasklet()).build();
    }
    
    @Bean Step reporteCdtCanceladosStep(StepBuilderFactory stepBuilders) {
    	logger.info("start reporteCdtCanceladosStep...");
    	return stepBuilders.get("reporteCdtCanceladosStep")
				.tasklet(reporteCdtCanceladosTasklet()).build();
    }
    
    @Bean(name = "JobCdtSemanalTesoreriaRenovados")
    Job JobCdtSemanalTesoreriaRenovados(JobBuilderFactory jobBuilderFactory,
                 StepBuilderFactory stepBuilders
    		) 
    {
    	logger.info("JobCdtSemanalTesoreriaRenovados...");
        return jobBuilderFactory.get("JobCdtSemanalTesoreriaRenovados")
                .incrementer(new RunIdIncrementer())
                .flow(reporteCdtReinvertidosStep(stepBuilders))
                .end()
                .build();
    }
    
    
    @Bean(name = "JobCdtSemanalTesoreriaCancelados")
    Job JobCdtSemanalTesoreriaCancelados(JobBuilderFactory jobBuilderFactory,
                 StepBuilderFactory stepBuilders
    		) 
    {
    	logger.info("JobCdtSemanalTesoreriaCancelados...");
        return jobBuilderFactory.get("JobCdtSemanalTesoreriaCancelados")
                .incrementer(new RunIdIncrementer())
                .flow(reporteCdtCanceladosStep(stepBuilders))
                .end()
                .build();
    }
    
    @Bean(name = "JobCdtSemanalTesoreria")
    Job JobCdtSemanalTesoreria(JobBuilderFactory jobBuilderFactory,
                 StepBuilderFactory stepBuilders
    		) 
    {
    	logger.info("JobCdtSemanalTesoreria...");
        return jobBuilderFactory.get("JobCdtSemanalTesoreria")
                .incrementer(new RunIdIncrementer())
                .flow(reporteCdtReinvertidosStep(stepBuilders))
                .next(reporteCdtCanceladosStep(stepBuilders))
                .end()
                .build();
    }
    
}
