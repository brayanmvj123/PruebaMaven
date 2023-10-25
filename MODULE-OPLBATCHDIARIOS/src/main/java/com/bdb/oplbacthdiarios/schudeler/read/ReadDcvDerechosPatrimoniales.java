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
package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.persistence.entity.CarDerpatriemiDownEntity;
import com.bdb.oplbacthdiarios.schudeler.load.DBWriterDcvDerechoPatrimoniales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;

/**
 * Read encargado de preparar la informacion del archivo leido DCV dcob01s y guardarlo en la base de Datos
 *
 * @author: Esteban Talero
 * @version: 19/11/2020
 * @since: 19/11/2020
 */
@Configuration
@EnableBatchProcessing
public class ReadDcvDerechosPatrimoniales {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final MetodosGenericos metodosGenericos;

    public ReadDcvDerechosPatrimoniales(MetodosGenericos metodosGenericos) {
        this.metodosGenericos = metodosGenericos;
    }

    @Bean(name = "readerCargaToDcvTbl")
    @StepScope
    FlatFileItemReader<CarDerpatriemiDownEntity> fileItemReaderDcvPatri() {
        logger.info("start fileItemReaderDcvPatri...");
        ByteArrayOutputStream outputStream = metodosGenericos.cargarArchivo();
        FlatFileItemReader<CarDerpatriemiDownEntity> fileReader = new FlatFileItemReader<>();
        fileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        LineMapper<CarDerpatriemiDownEntity> lineMapper = createLineMapper();
        fileReader.setLineMapper(lineMapper);
        return fileReader;
    }

    private LineMapper<CarDerpatriemiDownEntity> createLineMapper() {
        logger.info("start createLineMapper...");
        DefaultLineMapper<CarDerpatriemiDownEntity> lineMapper = new DefaultLineMapper<>();

        LineTokenizer lineTokenizer = createLineTokenizer();
        lineMapper.setLineTokenizer(lineTokenizer);

        FieldSetMapper<CarDerpatriemiDownEntity> informationMapper = createInformationMapper();
        lineMapper.setFieldSetMapper(informationMapper);

        return lineMapper;
    }

    private LineTokenizer createLineTokenizer() {
        logger.info("start createLineTokenizer...");
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        String[] names = {
                "codEmisor",
                "isin",
                "codDep",
                "tipDer",
                "fechaVenc",
                "ctaInv",
                "tipId",
                "idTit",
                "nomTit",
                "salCont",
                "cobDivEfe",
                "cobDivAcc",
                "cobCap",
                "cobRend",
                "reinv",
                "recCap",
                "recDivAcc",
                "recRend",
                "rteFte",
                "enajenacion",
                "adminDcvl",
                "preimpreso",
                "fechaIni",
                "fechaFin",
                "tasEfe",
                "factor",
                "impIca",
                "impCre",
                "impAdi"
        };

        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 5), //codEmisor-5
                new Range(6, 17), //isin-12
                new Range(18, 27),//codDep-10
                new Range(28, 29),//tipDer-2
                new Range(30, 37),//fechaVenc-8
                new Range(38, 45),//ctaInv-8
                new Range(46, 48),//tipId-3
                new Range(49, 63),//idTit-15
                new Range(64, 113),//nomTit-50
                new Range(114, 133),//salCont-20
                new Range(134, 153),//cobDivEfe-20
                new Range(154, 173),//cobDivAcc-20
                new Range(174, 193),//cobCap-20
                new Range(194, 213),//cobRend-20
                new Range(214, 233),//reinv-20
                new Range(234, 253),//recCap-20
                new Range(254, 273),//recDivAcc-20
                new Range(274, 293),//recRend-20
                new Range(294, 313),//rteFte-20
                new Range(314, 333),//enajenacion-20
                new Range(334, 335),//adminDcvl-2
                new Range(336, 345),// preimpreso-10
                new Range(346, 353),// fechaIni-8
                new Range(354, 361),// fechaFin-8
                new Range(362, 367),// tasEfe-6
                new Range(368, 375),// factor-8
                new Range(376, 395),// impIca-20
                new Range(396, 415),// impCre-20
                new Range(416, 435),// impAdi-20
        };

        tokenizer.setColumns(ranges);

        return tokenizer;
    }

    private FieldSetMapper<CarDerpatriemiDownEntity> createInformationMapper() {
        logger.info("start createInformationMapper...");
        BeanWrapperFieldSetMapper<CarDerpatriemiDownEntity> informationMapper = new BeanWrapperFieldSetMapper<>();
        informationMapper.setTargetType(CarDerpatriemiDownEntity.class);
        return informationMapper;
    }

    @Bean
    @StepScope
    ItemWriter<CarDerpatriemiDownEntity> writerDcvPatri() {
        logger.info("Inicio writer CarDerpatriemiDownEntity");
        return new DBWriterDcvDerechoPatrimoniales();
    }

    @Bean
    Step stepDerechosDcvPatrimoniales(@Qualifier("readerCargaToDcvTbl") ItemReader<CarDerpatriemiDownEntity> readerCargaToDcvTbl,
                                      ItemWriter<CarDerpatriemiDownEntity> writer, StepBuilderFactory stepBuilderFactory) {
        logger.info("stepDerechosDcvPatrimoniales...");
        return stepBuilderFactory.get("stepDerechosDcvPatrimoniales")
                .<CarDerpatriemiDownEntity, CarDerpatriemiDownEntity>chunk(50000)
                .reader(readerCargaToDcvTbl)
                .writer(writer)
                .build();
    }

    @Bean(name = "JobDcvDerechosPatrimoniales")
    Job jobDcvDerechosPatrimoniales(JobBuilderFactory jobBuilderFactory,
                                    @Qualifier("stepDerechosDcvPatrimoniales") Step dcvDerPatriStep) {
        logger.info("carga jobDcvDerechosPatrimoniales...");
        return jobBuilderFactory.get("jobDcvDerechosPatrimoniales")
                .incrementer(new RunIdIncrementer())
                .flow(dcvDerPatriStep)
                .end()
                .build();
    }
}
