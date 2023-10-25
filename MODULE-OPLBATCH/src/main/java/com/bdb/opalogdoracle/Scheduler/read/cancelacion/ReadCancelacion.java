package com.bdb.opalogdoracle.Scheduler.read.cancelacion;

import com.bdb.opalogdoracle.Scheduler.load.cancelacion.SendCdtDigCancelTasklet;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
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
public class ReadCancelacion {

    private final JobBuilderFactory builderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final SharedService serviceShared;

    public ReadCancelacion(JobBuilderFactory builderFactory, StepBuilderFactory stepBuilderFactory,
                           SharedService serviceShared){
        this.builderFactory = builderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.serviceShared = serviceShared;
    }

    @Bean
    @Qualifier("stepSendCdtDigCancel")
    Step stepSendCdtDigCancel(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PROCESO DE ENVIO DE CDTS DIGITALES CANCELADOS A LA TABLE DCV_SAL_PGDIGITAL ...");
        return stepBuilders
                .get("stepSendCdtDigCancel")
                .tasklet(sendCdtDigCancel())
                .build();
    }

    @Bean
    public SendCdtDigCancelTasklet sendCdtDigCancel() {
        log.info("Se consume el TASKLET de consumo de los CDTs Digitales que se enviaran a DECV BTA ...");
        return new SendCdtDigCancelTasklet(serviceShared);
    }

    @Bean(name = "JobSendCdtDigCancelacion")
    Job jobSendCdtDigCancelacion(JobBuilderFactory jobBuilderFactory,
                                 @Qualifier("stepSendCdtDigCancel") Step stepSendCdtDigCancel) {
        log.info("Job encargado de enviarlo los CDTs Digitales cancelados a la BD DECEVAL BTA.");
        return jobBuilderFactory
                .get("JobSendCdtDigCancelacion")
                .incrementer(new RunIdIncrementer())
                .flow(stepSendCdtDigCancel)
                .end()
                .build();
    }
}
