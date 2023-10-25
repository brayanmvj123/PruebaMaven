package com.bdb.oplbacthsemanal.schudeler.read.report.fechavenofic;

import com.bdb.oplbacthsemanal.schudeler.tasklet.reportpg.ReportPgWeeklyTasklet;
import com.bdb.oplbacthsemanal.schudeler.tasklet.simuladorquota.SimulatorQuotaTasklet;
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
public class ReadReportPgWeekly {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /**
     * Construye el metodo de acuerdo a la clase que se parametriza.
     * @param stepBuilders {@link StepBuilderFactory}
     * @return
     */
    @Bean
    Step stepReportPgWeekly(StepBuilderFactory stepBuilders) {
        log.info("INICIA LA CREACIÓN DEL REPORTE DE VENCIMIENTOS PARA OFICINA ...");
        return stepBuilders
                .get("stepReportPgWeekly")
                .tasklet(reportPgWeeklyTasklet())
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     * @return
     */
    @Bean
    public ReportPgWeeklyTasklet reportPgWeeklyTasklet() {
        log.info("Se inicia obteniendo la información de los CDTs Digitales que se renovaran ...");
        return new ReportPgWeeklyTasklet();
    }

    @Bean
    Step stepSimulatorQuota(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO PARA LA CREACIÓN DEL REPOTE DE VENCIMIENTOS PARA OFICINA ...");
        return stepBuilders
                .get("stepSimulatorQuota")
                .tasklet(simulatorQuotaTasklet())
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     * @return
     */
    @Bean
    public SimulatorQuotaTasklet simulatorQuotaTasklet() {
        log.info("SE INICIA LA SIMULACIÓN, PARA CALCULAR EL FACTOR DE LOS CDTS ...");
        return new SimulatorQuotaTasklet();
    }

    @Bean(name = "JobReportPgWeekly")
    Job jobReportPgWeekly(JobBuilderFactory jobBuilderFactory,
                          StepBuilderFactory stepBuilders) {
        log.info("Job para la reporte de Cdts Digitales");
        return jobBuilderFactory
                .get("jobReportPgWeekly")
                .incrementer(new RunIdIncrementer())
                .flow(stepReportPgWeekly(stepBuilders))
                .end()
                .build();
    }

    @Bean(name = "JobSimulatorPgWeekly")
    Job jobSimulatorPgWeekly(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilders) {
        log.info("Job para la simluacion de los reportes PG semanales");
        return jobBuilderFactory
                .get("jobSimulatorPgWeekly")
                .incrementer(new RunIdIncrementer())
                .flow(stepSimulatorQuota(stepBuilders))
                .end()
                .build();
    }
}
