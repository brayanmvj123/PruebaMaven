package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchTransCdtsDigBi;
import com.bdb.oplbacthdiarios.schudeler.tasklets.reporttranscdtsbi.GenerateDataFileCdtsDigBiTasklet;
import com.bdb.oplbacthdiarios.schudeler.tasklets.reporttranscdtsbi.ReportTransCdtsDigBiTasklet;
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
public class ReadTransCdtsDigBi {

    public final JobBuilderFactory builderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    final OperationBatchTransCdtsDigBi operationBatchTransCdtsDigBi;

    public ReadTransCdtsDigBi(JobBuilderFactory builderFactory,
                              StepBuilderFactory stepBuilderFactory,
                              OperationBatchTransCdtsDigBi operationBatchTransCdtsDigBi) {
        this.builderFactory = builderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.operationBatchTransCdtsDigBi = operationBatchTransCdtsDigBi;
    }

    /**
     * Construye el metodo de acuerdo a la clase que se parametriza.
     *
     * @param stepBuilders {@link StepBuilderFactory}
     * @return
     */
    @Bean
    @Qualifier(value = "stepGenerateDataFileCdtsDigBi")
    Step stepGenerateDataFileCdtsDigBi(StepBuilderFactory stepBuilders) {
        log.info("SE INICIA EL STEP REPORTE MAE_VENTAS...");
        return stepBuilders
                .get("stepGenerateDataFileCdtsDigBi")
                .tasklet(generateDataFileTasklet(operationBatchTransCdtsDigBi))
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     *
     * @return
     */
    @Bean
    public GenerateDataFileCdtsDigBiTasklet generateDataFileTasklet(OperationBatchTransCdtsDigBi operationBatchTransCdtsDigBi) {
        log.info("INICIA LA GENERACIÓN DE LOS DATOS PARA EL REPORTE MAE_VENTAS...");
        return new GenerateDataFileCdtsDigBiTasklet(operationBatchTransCdtsDigBi);
    }

    @Bean
    @Qualifier(value = "stepReportTransCdtsDig")
    Step stepReportTransCdtsDig(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO PARA GENERAR LA DATA PARA EL REPORTE TRANSMISIÓN DE ARCHIVOS CDTs DIGITALES...");
        return stepBuilders
                .get("stepReportTransCdtsDig")
                .tasklet(reportTransCdtsDigTasklet(operationBatchTransCdtsDigBi))
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     *
     * @return
     */
    @Bean
    public ReportTransCdtsDigBiTasklet reportTransCdtsDigTasklet(OperationBatchTransCdtsDigBi operationBatchTransCdtsDigBi) {
        log.info("SE INICIA LA GENERACIÓN DEL REPORTE TRANSCDTSDIG.");
        return new ReportTransCdtsDigBiTasklet(operationBatchTransCdtsDigBi);
    }

    @Bean(name = "JobReportTransCdtsDig")
    Job jobReportTransCdtsDig(JobBuilderFactory jobBuilderFactory,
                              @Qualifier(value = "stepGenerateDataFileCdtsDigBi") Step stepGenerateDataFileCdtsDigBi,
                              @Qualifier(value = "stepReportTransCdtsDig") Step stepReportTransCdtsDig) {
        log.info("Job para la generación del reporte Transmisión de archivos de CDTs Digitales.");
        return jobBuilderFactory
                .get("JobReportTransCdtsDig")
                .incrementer(new RunIdIncrementer())
                .flow(stepGenerateDataFileCdtsDigBi).on("COMPLETED").to(stepReportTransCdtsDig)
                .end()
                .build();
    }
}
