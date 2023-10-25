package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchCancelacion;
import com.bdb.oplbacthdiarios.schudeler.tasklets.pgaut.PgsCdtCancelTasklet;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadCdtPagAut {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /**
     * Estructura del paso para realizo los pagos de los CDTs Digitales.
     *
     * @param stepBuilders {@link StepBuilderFactory}
     * @return Step
     */
    @Bean
    @Qualifier(value = "pgsCdtCancel")
    Step pgsCdtCancel(StepBuilderFactory stepBuilders, OperationBatchCancelacion operationBatchCancelacion) {
        log.info("INICIA EL CONSUMO DEL SERVICIO DE PAGOS ...");
        return stepBuilders
                .get("pgsCdtCancel")
                .tasklet(pgsCdtCancelTasklet(operationBatchCancelacion))
                .build();
    }

    /**
     * Se realiza la logica de los pagos.
     *
     * @return PgsCdtCancelTasklet
     */
    @Bean
    public PgsCdtCancelTasklet pgsCdtCancelTasklet(OperationBatchCancelacion operationBatchCancelacion) {
        log.info("Se inicia obteniendo la información de los CDTs Digitales que se renovaran ...");
        return new PgsCdtCancelTasklet(operationBatchCancelacion);
    }

    @Bean(name = "JobPgsCdtCancel")
    Job jobPgsCdtCancel(JobBuilderFactory jobBuilderFactory,
                    @Qualifier(value = "pgsCdtCancel") Step pgsCdtCancel) {
        log.info("Job para el pago de Cdts Digitales");
        return jobBuilderFactory
                .get("JobPgsCdtCancel")
                .incrementer(new RunIdIncrementer())
                .flow(pgsCdtCancel)
                .end()
                .build();
    }
}
