package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.tasklets.CdtCanceladosOficinasTasklet;
import com.bdb.oplbacthdiarios.schudeler.tasklets.CdtRenovadosOficinaTasklet;
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

import com.bdb.oplbacthdiarios.schudeler.load.CdtCanceladosTesoreriaTasklet;
import com.bdb.oplbacthdiarios.schudeler.load.CdtRenovadosTesoreriaTasklet;

@Configuration
@EnableBatchProcessing
public class ReadCdtDiarioTesoreria {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public CdtRenovadosTesoreriaTasklet cdtRenovadosTesoreriaTasklet() {
    	logger.info("start CdtRenovadosTesoreriaTasklet...");
    	return new CdtRenovadosTesoreriaTasklet();
    }

    @Bean
    public CdtRenovadosOficinaTasklet cdtRenovadosOficinaTasklet() {
        logger.info("start CdtRenovadosTesoreriaTasklet...");
        return new CdtRenovadosOficinaTasklet();
    }

    @Bean
    public CdtCanceladosTesoreriaTasklet cdtCanceladosTesoreriaTasklet() {
    	logger.info("start CdtCanceladosTesoreriaTasklet...");
    	return new CdtCanceladosTesoreriaTasklet();
    }

    @Bean
    public CdtCanceladosOficinasTasklet cdtCanceladosOficinasTasklet() {
    	logger.info("start CdtCanceladosOficinasTasklet...");
    	return new CdtCanceladosOficinasTasklet();
    }

    @Bean Step cdtRenovadosOficinaStep(StepBuilderFactory stepBuilders) {
    	logger.info("start cdtRenovadosOficinaStep...");
    	return stepBuilders.get("cdtRenovadosOficinaStep")
				.tasklet(cdtRenovadosOficinaTasklet()).build();
    }

    @Bean Step cdtRenovadosTesoreriaStep(StepBuilderFactory stepBuilders) {
        logger.info("start cdtRenovadosTesoreriaStep...");
        return stepBuilders.get("cdtRenovadosTesoreriaStep")
                .tasklet(cdtRenovadosTesoreriaTasklet()).build();
    }
    
    @Bean Step cdtCanceladosTesoreriaStep(StepBuilderFactory stepBuilders) {
    	logger.info("start cdtCanceladosTesoreriaStep...");
    	return stepBuilders.get("cdtCanceladosTesoreriaStep")
				.tasklet(cdtCanceladosTesoreriaTasklet()).build();
    }

    @Bean Step cdtCanceladosOficinasStep(StepBuilderFactory stepBuilders) {
    	logger.info("start cdtCanceladosOficinasStep...");
    	return stepBuilders.get("cdtCanceladosOficinasStep")
				.tasklet(cdtCanceladosTesoreriaTasklet()).build();
    }
    
    @Bean(name = "JobCdtDiarioTesoreriaRenovado")
    Job JobCdtDiarioTesoreriaRenovado(JobBuilderFactory jobBuilderFactory,
                 StepBuilderFactory stepBuilders
    		) 
    {
    	logger.info("JobCdtDiarioTesoreriaRenovado...");
        return jobBuilderFactory.get("JobCdtDiarioTesoreriaRenovado")
                .incrementer(new RunIdIncrementer())
                .flow(cdtRenovadosTesoreriaStep(stepBuilders))
                .next(cdtRenovadosOficinaStep(stepBuilders))
                .end()
                .build();
    }
    
    @Bean(name = "JobCdtDiarioTesoreriaCancelado")
    Job JobCdtDiarioTesoreriaCancelado(JobBuilderFactory jobBuilderFactory,
                 StepBuilderFactory stepBuilders
    		) 
    {
    	logger.info("JobCdtDiarioTesoreriaCancelado...");
        return jobBuilderFactory.get("JobCdtDiarioTesoreriaCancelado")
                .incrementer(new RunIdIncrementer())
                .flow(cdtCanceladosTesoreriaStep(stepBuilders))
                .end()
                .build();
    }
    
    @Bean(name = "JobCdtDiarioTesoreria")
    Job JobCdtDiarioTesoreria(JobBuilderFactory jobBuilderFactory,
                 StepBuilderFactory stepBuilders
    		) 
    {
    	logger.info("JobCdtDiarioTesoreria...");
        return jobBuilderFactory.get("JobCdtDiarioTesoreria")
                .incrementer(new RunIdIncrementer())
                .flow(cdtRenovadosTesoreriaStep(stepBuilders))
                .next(cdtCanceladosTesoreriaStep(stepBuilders))
                .end()
                .build();
    }
    
}
