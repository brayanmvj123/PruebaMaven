package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.load.cuentasinv.CrossInsertCtaInvxAccSecTasklet;
import com.bdb.oplbacthdiarios.schudeler.load.cuentasinv.CrossInsertCtaInvxAccTasklet;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ReadCtaInvxCli {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private static final Logger LOG = LoggerFactory.getLogger(ReadCtaInvxCli.class);

    @Bean
    Step crossInsertCtaInvxAccStep(StepBuilderFactory stepBuilders) {
        log.info("start crossInsertCtaInvxCliStep...");
        return stepBuilders.get("crossInsertCtaInvxCliStep")
                .tasklet(crossInsertCtaInvxAccTasklet()).build();
    }

    @Bean
    public CrossInsertCtaInvxAccTasklet crossInsertCtaInvxAccTasklet() {
        log.info("start crossInsertCtaInvxCliTasklet...");
        return new CrossInsertCtaInvxAccTasklet();
    }


    @Bean
    Step crossInsertCtaInvxAccSecStep(StepBuilderFactory stepBuilders) {
        log.info("start crossInsertCtaInvxCliSecStep...");
        return stepBuilders.get("crossInsertCtaInvxCliSecStep")
                .tasklet(crossInsertCtaInvxAccSecTasklet()).build();
    }

    @Bean
    public CrossInsertCtaInvxAccSecTasklet crossInsertCtaInvxAccSecTasklet() {
        log.info("start crossInsertCtaInvxCliSecTasklet...");
        return new CrossInsertCtaInvxAccSecTasklet();
    }


    @Bean(name = "JobCtaInvxCli")
    Job JobAccionista(JobBuilderFactory jobBuilderFactory,
                      StepBuilderFactory stepBuilders) {
        LOG.info("ENTRO AL JOB  - JobCtaInvxCli");
        return jobBuilderFactory.get("JobCtaInvxCli")
                .incrementer(new RunIdIncrementer())
                .flow(crossInsertCtaInvxAccStep(stepBuilders)).next(crossInsertCtaInvxAccSecStep(stepBuilders))
                .end()
                .build();
    }

}
