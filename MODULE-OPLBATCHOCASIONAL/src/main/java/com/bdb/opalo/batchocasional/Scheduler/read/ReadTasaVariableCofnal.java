package com.bdb.opalo.batchocasional.Scheduler.read;

import com.bdb.opalo.batchocasional.Scheduler.load.TasaVariableTasklet;
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
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadTasaVariableCofnal {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Realiza la logica para el cargue de tasas variables.
     * @return TasaVariableTasklet
     */
    @Bean
    public TasaVariableTasklet tasaVariableTasklet() {
        log.info("SE INICIA EL PROCESO DE TRAER TASAS VARIABLES DE COFNAL ...");
        return new TasaVariableTasklet();
    }

    @Bean
    Step stepTasaVariableCofnal(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO PARA LA TRAER TASAS VARIABLES DE COFNAL ...");
        return stepBuilders
                .get("jobTasaVariableCofnal")
                .tasklet(tasaVariableTasklet())
                .build();
    }


    @Bean(name = "JobTasaVariableCofnal")
    Job jobTasaVariableCofnal(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilders) {
        log.info("Job para la traer las tasas variables de cofnal.");
        return jobBuilderFactory
                .get("jobTasaVariableCofnal")
                .incrementer(new RunIdIncrementer())
                .flow(stepTasaVariableCofnal(stepBuilders))
                .end()
                .build();
    }

}
