package com.bdb.oplbatchmensual.Schudeler.read;

import com.bdb.oplbatchmensual.Schudeler.load.CalendarioCofnalTasklet;
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
public class ReadCalendarioCofnal {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Realiza la logica para el cargue del calendario.
     * @return CalendarioCofnalTasklet
     */
    @Bean
    public CalendarioCofnalTasklet calendarioCofnalTasklet() {
        log.info("SE INICIA EL PROCESO 'TRAER EL CALENDARIO DE COFNAL' ...");
        return new CalendarioCofnalTasklet();
    }

    @Bean
    Step stepCalendarioCofnal(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO PARA TRAER EL CALENDARIO DE COFNAL ...");
        return stepBuilders
                .get("jobCalendarioCofnal")
                .tasklet(calendarioCofnalTasklet())
                .build();
    }

    @Bean(name = "JobCalendarioCofnal")
    Job jobCalendarioCofnal(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilders) {
        log.info("Job para traer el calendario de cofnal.");
        return jobBuilderFactory
                .get("jobCalendarioCofnal")
                .incrementer(new RunIdIncrementer())
                .flow(stepCalendarioCofnal(stepBuilders))
                .end()
                .build();
    }

}
