package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.load.ReporteCuentasContablesTasklet;
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
public class ReadReportePgCuentasContables {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public ReporteCuentasContablesTasklet reporteCuentasContablesTasklet() {
        log.info("start crossInfoPatrimonialesTasklet...");
        return new ReporteCuentasContablesTasklet();
    }


    @Bean
    Step stepReporteCuentasContables(StepBuilderFactory stepBuilders) {
        log.info("start stepReporteCuentasContables...");
        return stepBuilders.get("crossInfoPatrimonioStep").tasklet(reporteCuentasContablesTasklet()).build();
    }


    @Bean(name = "jobReportesPgCuentasContables")
    Job JobCrucePatrimoniales(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilders) {
        log.info("carga jobReportesPgCuentasContables...");
        return jobBuilderFactory.get("jobReportesPgCuentasContables")
                .incrementer(new RunIdIncrementer())
                .flow(stepReporteCuentasContables(stepBuilders))
                .end()
                .build();
    }
}
