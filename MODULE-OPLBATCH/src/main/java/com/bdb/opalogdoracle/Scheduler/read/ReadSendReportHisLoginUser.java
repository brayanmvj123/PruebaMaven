package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.CrossCreateSendReportHisLoginUserTasklet;
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
public class ReadSendReportHisLoginUser {
    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    Step crossCreateSendReportHisLoginUserStep(StepBuilderFactory stepBuilders) {
        log.info("start crossCreateSendReportHisLoginUserStep...");
        return stepBuilders.get("crossCreateSendReportHisLoginUserStep")
                .tasklet(crossCreateSendReportHisLoginUserTasklet()).build();
    }

    @Bean
    public CrossCreateSendReportHisLoginUserTasklet crossCreateSendReportHisLoginUserTasklet() {
        log.info("start crossInfoPatrimonialesTasklet...");
        return new CrossCreateSendReportHisLoginUserTasklet();
    }

    @Bean(name = "JobEnvioCorreoReporteHisLoginUser")
    Job JobEnvioCorreoReporteHisLogin(JobBuilderFactory jobBuilderFactory,
                                      StepBuilderFactory stepBuilders
    ) {
        return jobBuilderFactory.get("JobEnvioCorreoReporteHisLoginUser")
                .incrementer(new RunIdIncrementer())
                .flow(crossCreateSendReportHisLoginUserStep(stepBuilders))
                .end()
                .build();
    }
}
