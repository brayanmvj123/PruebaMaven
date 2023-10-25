package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.oplbacthdiarios.schudeler.tasklets.creationfile.CreationExcelByOfficeTasklet;
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
public class ReadCreationFiles {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    @Qualifier(value = "stepCreationFiles")
    Step stepCreationExcelByOffice(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO PARA LA CREACIÓN DEL REPOTE DE VENCIMIENTOS PARA OFICINA ...");
        return stepBuilders
                .get("stepCreationFiles")
                .tasklet(creationExcelByOfficeTasklet())
                .build();
    }

    /**
     * Realiza la logica para la creacion del archivo.
     *
     * @return
     */
    @Bean
    public CreationExcelByOfficeTasklet creationExcelByOfficeTasklet() {
        log.info("SE INICIA LA CREACIÓN DE LOS ARCHIVOS DE EXCEL POR CADA OFICINA PARA LA LIQUIDACIÓN SEMANAL");
        return new CreationExcelByOfficeTasklet();
    }


    @Bean(name = "JobCreateFiles")
    Job jobSimulatorPg(JobBuilderFactory jobBuilderFactory,
                       @Qualifier(value = "stepCreationFiles") Step stepCreationExcelByOffice) {
        log.info("Job para la creación de archivos de excel por oficina para enviar a oficinas ");
        return jobBuilderFactory
                .get("JobCreateFiles")
                .incrementer(new RunIdIncrementer())
                .flow(stepCreationExcelByOffice)
                .end()
                .build();
    }
}
