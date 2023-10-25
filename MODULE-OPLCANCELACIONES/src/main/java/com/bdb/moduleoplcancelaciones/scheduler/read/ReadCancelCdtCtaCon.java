package com.bdb.moduleoplcancelaciones.scheduler.read;

import com.bdb.moduleoplcancelaciones.scheduler.service.OperationCancelCdtaCtaCon;
import com.bdb.moduleoplcancelaciones.scheduler.tasklet.CancelCdtCtaConTasklet;
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
public class ReadCancelCdtCtaCon {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /**
     * Construye el metodo de acuerdo a la clase que se parametriza.
     *
     * @param stepBuilders {@link StepBuilderFactory}
     * @return
     */
    @Bean
    @Qualifier(value = "stepCancelCdtCtaCon")
    Step stepCancelCdtCtaCon(StepBuilderFactory stepBuilders, OperationCancelCdtaCtaCon operationCancelCdtaCtaCon) {
        log.info("INICIA EL CONSUMO DEL SERVICIO DE PAGOS ...");
        return stepBuilders
                .get("stepCancelCdtCtaCon")
                .tasklet(cancelCdtCtaConTasklet(operationCancelCdtaCtaCon))
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     *
     * @return
     * @param operationCancelCdtaCtaCon
     */
    @Bean
    public CancelCdtCtaConTasklet cancelCdtCtaConTasklet(OperationCancelCdtaCtaCon operationCancelCdtaCtaCon) {
        log.info("Se inicia obteniendo la información de los CDTs Digitales que se renovaran ...");
        return new CancelCdtCtaConTasklet(operationCancelCdtaCtaCon);
    }

    @Bean(name = "JobCancelCdtCtaCon")
    Job jobCancelCdtCtaCon(JobBuilderFactory jobBuilderFactory,
                    @Qualifier(value = "stepCancelCdtCtaCon") Step stepCancelCdtCtaCon) {
        log.info("Job para el pago de Cdts Digitales");
        return jobBuilderFactory
                .get("jobCancelCdtCtaCon")
                .incrementer(new RunIdIncrementer())
                .flow(stepCancelCdtCtaCon)
                .end()
                .build();
    }
}
