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
package com.bdb.oplbacthsemanal.schudeler.read;

import com.bdb.opaloshare.persistence.entity.AcuDerpatriemiDownEntity;
import com.bdb.oplbacthsemanal.controller.service.interfaces.EmisorDcvDerechosPatrimonialesService;
import com.bdb.oplbacthsemanal.schudeler.load.DBWriterDcvDerechoPatrimonial;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;

/**
 * Read encargado de preparar la informacion del archivo leido DCV con el
 * resultado de derechos Patrimoniales y guardarlo en la base de Datos
 *
 * @author: Esteban Talero
 * @version: 26/11/2020
 * @since: 24/11/2020
 */
@Configuration
@EnableBatchProcessing
public class ReadDcvDerechoPatrimonial {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    EmisorDcvDerechosPatrimonialesService derechoSemanalDcvPatrimonialService;


    @Bean
    @StepScope
    ItemWriter<AcuDerpatriemiDownEntity> writerSemanalDcvPatri() {
        logger.info("Inicio writer AcuDerpatriemiDownEntity");
        return new DBWriterDcvDerechoPatrimonial();
    }

    private FieldSetMapper<AcuDerpatriemiDownEntity> createInformationMapper() {
        logger.info("start createInformationMapper...");
        BeanWrapperFieldSetMapper<AcuDerpatriemiDownEntity> InformationMapper = new BeanWrapperFieldSetMapper<>();
        InformationMapper.setTargetType(AcuDerpatriemiDownEntity.class);
        return InformationMapper;
    }

    private LineTokenizer createLineTokenizer(DefaultLineMapper<AcuDerpatriemiDownEntity> line) {
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


    private LineMapper<AcuDerpatriemiDownEntity> createLineMapper() {
        logger.info("start createLineMapper...");
        DefaultLineMapper<AcuDerpatriemiDownEntity> LineMapper = new DefaultLineMapper<>();

        LineTokenizer LineTokenizer = createLineTokenizer(LineMapper);
        LineMapper.setLineTokenizer(LineTokenizer);

        FieldSetMapper<AcuDerpatriemiDownEntity> InformationMapper = createInformationMapper();
        LineMapper.setFieldSetMapper(InformationMapper);

        return LineMapper;
    }

    @Bean(name = "readerSemanalCargaToDcvTbl")
    @StepScope
    FlatFileItemReader<AcuDerpatriemiDownEntity> FileItemReaderSemanalDcvPatri() {
        logger.info("FileItemReaderSemanalDcvPatri...");
        ByteArrayOutputStream outputStream = derechoSemanalDcvPatrimonialService.cargarDatosArchivoDcv();
        logger.info("ByteArrayOutputStream... " + outputStream);
        FlatFileItemReader<AcuDerpatriemiDownEntity> FileReader = new FlatFileItemReader<>();
        FileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        LineMapper<AcuDerpatriemiDownEntity> LineMapper = createLineMapper();
        FileReader.setLineMapper(LineMapper);
        return FileReader;
    }

    @Bean
    Step StepSemanalDerechosDcvPatrimoniales(@Qualifier("readerSemanalCargaToDcvTbl") ItemReader<AcuDerpatriemiDownEntity> readerSemanalCargaToDcvTbl,
                                             ItemWriter<AcuDerpatriemiDownEntity> writer, StepBuilderFactory stepBuilderFactory) {
        logger.info("StepSemanalDerechosDcvPatrimoniales...");
        return stepBuilderFactory.get("StepSemanalDerechosDcvPatrimoniales")
                .<AcuDerpatriemiDownEntity, AcuDerpatriemiDownEntity>chunk(50000)
                .reader(readerSemanalCargaToDcvTbl)
                .writer(writer)
                .build();
    }

    @Bean(name = "JobSemanalDcvDerechosPatrimoniales")
    Job JobSemanalDcvDerechosPatrimoniales(JobBuilderFactory jobBuilderFactory,
                                           @Qualifier("StepSemanalDerechosDcvPatrimoniales") Step dcvSemanalDerPatriStep,
                                           StepBuilderFactory stepBuilders
    ) {
        logger.info("carga JobSemanalDcvDerechosPatrimoniales...");
        return jobBuilderFactory.get("JobSemanalDcvDerechosPatrimoniales")
                .incrementer(new RunIdIncrementer())
                .flow(dcvSemanalDerPatriStep)
                .end()
                .build();
    }

}
