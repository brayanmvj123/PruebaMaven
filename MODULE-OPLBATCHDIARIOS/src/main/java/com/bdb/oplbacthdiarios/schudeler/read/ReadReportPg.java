package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchReportPg;
import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchSimQuota;
import com.bdb.oplbacthdiarios.schudeler.tasklets.reportpg.ReportPgTasklet;
import com.bdb.oplbacthdiarios.schudeler.tasklets.simuladorquota.SimulatorQuotaTasklet;
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
public class ReadReportPg {

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
    @Qualifier(value = "reportFechaVenOfic")
    Step reportFechaVenOfic(StepBuilderFactory stepBuilders, OperationBatchReportPg operationBatchReportPg) {
        log.info("INICIA LA CREACIÓN DEL REPOTE DE VENCIMIENTOS PARA OFICINA ...");
        return stepBuilders
                .get("reportFechaVenOfic")
                .tasklet(reportFechaVenOficTasklet(operationBatchReportPg))
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     *
     * @return
     */
    @Bean
    public ReportPgTasklet reportFechaVenOficTasklet(OperationBatchReportPg operationBatchReportPg) {
        log.info("Se inicia obteniendo la información de los CDTs Digitales que se renovaran ...");
        return new ReportPgTasklet(operationBatchReportPg);
    }

    @Bean
    @Qualifier(value = "stepSimulatorQuota")
    Step stepSimulatorQuota(StepBuilderFactory stepBuilders, OperationBatchSimQuota operationBatchSimQuota) {
        log.info("INICIA EL PASO PARA LA CREACIÓN DEL REPOTE DE VENCIMIENTOS PARA OFICINA ...");
        return stepBuilders
                .get("stepSimulatorQuota")
                .tasklet(simulatorQuotaTasklet(operationBatchSimQuota))
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     *
     * @return
     */
    @Bean
    public SimulatorQuotaTasklet simulatorQuotaTasklet(OperationBatchSimQuota operationBatchSimQuota) {
        log.info("SE INICIA LA SIMULACIÓN, PARA CALCULAR EL FACTOR DE LOS CDTS ...");
        return new SimulatorQuotaTasklet();
    }

    @Bean(name = "JobReportPg")
    Job jobReportPg(JobBuilderFactory jobBuilderFactory,
                    @Qualifier(value = "reportFechaVenOfic") Step reportFechaVenOfic) {
        log.info("Job para la renovación de Cdts Digitales");
        return jobBuilderFactory
                .get("JobReportPg")
                .incrementer(new RunIdIncrementer())
                .flow(reportFechaVenOfic)
                .end()
                .build();
    }

    @Bean(name = "JobSimulatorPg")
    Job jobSimulatorPg(JobBuilderFactory jobBuilderFactory,
                       @Qualifier(value = "stepSimulatorQuota") Step stepSimulatorQuota) {
        log.info("Job para la simluacion de los reportes PG semanales");
        return jobBuilderFactory
                .get("jobSimulatorPg")
                .incrementer(new RunIdIncrementer())
                .flow(stepSimulatorQuota)
                .end()
                .build();
    }
}
