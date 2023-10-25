/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.opalogdoracle.Scheduler.read.renovacion;

import com.bdb.opalogdoracle.Scheduler.load.renovacion.AperIntoRenautTasklet;
import com.bdb.opalogdoracle.Scheduler.load.renovacion.CrossSalDCvRenautDigTasklet;
import com.bdb.opalogdoracle.Scheduler.load.renovacion.CrossSalRenautDigTasklet;
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


/**
 * Read encargado de ejecutar los Jobs de clasificar y realizar la renovacion de CDT's digitales
 * y generar las tramas del traductor contable CADI
 *
 * @author: J Sebastian Rivera
 * @author: Esteban Talero
 * @version: 09/11/2020
 * @since: 09/11/2020
 */
@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadAperIntoRenaut {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    //Step para llenar tabla de salida renautDig
    @Bean
    Step crossSalRenautDigTaskletStep(StepBuilderFactory stepBuilders) {
        log.info("start crossSalRenautDigTaskletStep...");
        return stepBuilders.get("crossSalRenautDigTaskletStep")
                .tasklet(crossSalRenautDigTaskletTasklet()).build();
    }

    @Bean
    public CrossSalRenautDigTasklet crossSalRenautDigTaskletTasklet() {
        log.info("start crossSalRenautDigTaskletTasklet...");
        return new CrossSalRenautDigTasklet();
    }

    @Bean(name = "JobControlSalRenautDig")
    Job JobControlSalRenautDigital(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilders
    ) {
        return jobBuilderFactory.get("JobControlSalRenautDig")
                .incrementer(new RunIdIncrementer())
                .flow(crossSalRenautDigTaskletStep(stepBuilders))
                .end()
                .build();
    }

    //Step para llenar tabla de salida DCV renautDig
    @Bean
    Step crossSalDCvRenautDigTaskletStep(StepBuilderFactory stepBuilders) {
        log.info("start crossSalDCvRenautDigTaskletStep...");
        return stepBuilders.get("crossSalDCvRenautDigTaskletStep")
                .tasklet(crossSalDCvRenautDigTasklet()).build();
    }

    @Bean
    public CrossSalDCvRenautDigTasklet crossSalDCvRenautDigTasklet() {
        log.info("start crossSalDCvRenautDigTasklet...");
        return new CrossSalDCvRenautDigTasklet();
    }

    @Bean(name = "JobControlSalDcvRenautDig")
    Job JobControlSalDcvRenautDigital(JobBuilderFactory jobBuilderFactory,
                                      StepBuilderFactory stepBuilders
    ) {
        return jobBuilderFactory.get("JobControlSalDcvRenautDig")
                .incrementer(new RunIdIncrementer())
                .flow(crossSalDCvRenautDigTaskletStep(stepBuilders))
                .end()
                .build();
    }

    @Bean
    Step startAperIntoRenautStep(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PROCESO RENOVACIÓN DE CDTS DIGITALES ...");
        return stepBuilders
                .get("startAperIntoRenautStep")
                .tasklet(aperIntoRenautTasklet())
                .build();
    }

    //Step apertura Into Renaut
    @Bean
    public AperIntoRenautTasklet aperIntoRenautTasklet() {
        log.info("Se inicia obteniendo la información de los CDTs Digitales que se renovaran ...");
        return new AperIntoRenautTasklet();
    }

    @Bean(name = "JobAperIntoRenaut")
    Job jobAperIntoRenaut(JobBuilderFactory jobBuilderFactory,
                          StepBuilderFactory stepBuilders) {
        log.info("Job para la renovación de Cdts Digitales");
        return jobBuilderFactory
                .get("jobAperIntoRenaut")
                .incrementer(new RunIdIncrementer())
                .flow(startAperIntoRenautStep(stepBuilders))
                .end()
                .build();
    }
}
