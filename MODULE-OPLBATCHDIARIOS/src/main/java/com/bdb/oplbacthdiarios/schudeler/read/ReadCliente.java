package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.load.cuentasinv.CrossInsertAccionistasTasklet;
import com.bdb.oplbacthdiarios.schudeler.load.cuentasinv.CrossInsertAcctsSecTasklet;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadCliente {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    Step crossInsertAccionistasStep(StepBuilderFactory stepBuilders) {
        log.info("start crossInsertInfoClientesStep...");
        return stepBuilders.get("crossInsertAccionistasStep")
                .tasklet(crossInsertAccionistasTasklet()).build();
    }

    @Bean
    public CrossInsertAccionistasTasklet crossInsertAccionistasTasklet() {
        log.info("start crossInsertInfoClientesTasklet...");
        return new CrossInsertAccionistasTasklet();
    }

    @Bean
    Step crossInsertAcctsSecStep(StepBuilderFactory stepBuilders) {
        log.info("start crossInsertAcctsSecStep...");
        return stepBuilders.get("crossInsertAcctsSecStep")
                .tasklet(crossInsertAcctsSecTasklet()).build();
    }

    @Bean
    public CrossInsertAcctsSecTasklet crossInsertAcctsSecTasklet() {
        log.info("start crossInsertAcctsSecTasklet...");
        return new CrossInsertAcctsSecTasklet();
    }


    @Bean(name = "JobCliente")
    Job JobAccionista(JobBuilderFactory jobBuilderFactory,
                      StepBuilderFactory stepBuilders) {
        log.info("ENTRO AL JOB - JobCliente");
        return jobBuilderFactory.get("JobCliente")
                .incrementer(new RunIdIncrementer())
                .flow(crossInsertAccionistasStep(stepBuilders)).next(crossInsertAcctsSecStep(stepBuilders))
                .end()
                .build();
    }

}
