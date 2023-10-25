package com.bdb.opalo.mds.scheduler.read;

import com.bdb.opalo.mds.scheduler.services.OperationRatesMds;
import com.bdb.opalo.mds.scheduler.tasklet.CreationRatesMdsTasklet;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadRatesMDS {

    final OperationRatesMds operationRatesMds;

    public ReadRatesMDS(OperationRatesMds operationRatesMds) {
        this.operationRatesMds = operationRatesMds;
    }

    @Bean
    public CreationRatesMdsTasklet creationRatesMdsTasklet(OperationRatesMds operationRatesMds) {
        log.info("SE INICIA LA CREACIÓN DE LOS ARCHIVOS DE EXCEL POR CADA OFICINA PARA LA LIQUIDACIÓN SEMANAL");
        return new CreationRatesMdsTasklet(operationRatesMds);
    }

    @Bean
    @Qualifier(value = "stepCreationRates")
    Step stepCreationRatesMds(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO PARA LA CREACIÓN DEL REPORTE DE VENCIMIENTOS PARA OFICINA ...");
        return stepBuilders
                .get("stepCreationRates")
                .tasklet(creationRatesMdsTasklet(operationRatesMds))
                .build();
    }

    @Bean(name = "JobRates")
    Job jobRates(JobBuilderFactory jobBuilderFactory, @Qualifier("stepCreationRates") Step stepCreationRatesMds) {
        log.info("INICIA EL JOB JobRatesMds");
        return jobBuilderFactory
                .get("JobRates")
                .incrementer(new RunIdIncrementer())
                .flow(stepCreationRatesMds)
                .end()
                .build();
    }
}
