package com.bdb.oplbatchmensual.Schudeler.read;

import com.bdb.oplbatchmensual.Schudeler.load.CalendarioCofnalTasklet;
import com.bdb.oplbatchmensual.Schudeler.load.CalendarioOpaloTasklet;
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
public class ReadCalendarioOpalo {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /**
     * Realiza la logica para el cargue del calendario de opalo.
     * @return TasaVariableTasklet
     */
    @Bean
    public CalendarioOpaloTasklet calendarioOpaloTasklet() {
        log.info("SE INICIA EL PROCESO 'ACTUALIZAR CALENDARIO OPALO' ...");
        return new CalendarioOpaloTasklet();
    }

    @Bean
    Step stepCalendarioOpalo(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO PARA ACTUALIZAR CALENDARIO OPALO ...");
        return stepBuilders
                .get("jobCalendarioOpalo")
                .tasklet(calendarioOpaloTasklet())
                .build();
    }

    @Bean(name = "JobCalendarioOpalo")
    Job jobCalendarioOpalo(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilders) {
        log.info("Job para actualizar calendario de opalo.");
        return jobBuilderFactory
                .get("jobCalendarioOpalo")
                .incrementer(new RunIdIncrementer())
                .flow(stepCalendarioOpalo(stepBuilders))
                .end()
                .build();
    }
}
