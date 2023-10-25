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

import com.bdb.opaloshare.persistence.model.component.ModelDepositanteDerechoPatrimonial;
import com.bdb.oplbacthsemanal.controller.service.interfaces.EmisorDcvDerechosPatrimonialesService;
import com.bdb.oplbacthsemanal.schudeler.load.DBWriterDepoDerechoPatrimonial;
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
 * resultado de derechos Patrimoniales Depositante y guardarlo en la base de Datos
 *
 * @author: Esteban Talero
 * @version: 26/11/2020
 * @since: 25/11/2020
 */
@Configuration
@EnableBatchProcessing
public class ReadDepoDerechoPatrimonial {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EmisorDcvDerechosPatrimonialesService derechoSemanalDcvPatrimonialService;


    @Bean
    @StepScope
    ItemWriter<ModelDepositanteDerechoPatrimonial> writerSemanalDepoDerPatri() {
        logger.info("Inicio writer ModelDepositanteDerechoPatrimonial");
        return new DBWriterDepoDerechoPatrimonial();
    }

    private FieldSetMapper<ModelDepositanteDerechoPatrimonial> createInformationMapper() {
        logger.info("start createInformationMapper...");
        BeanWrapperFieldSetMapper<ModelDepositanteDerechoPatrimonial> InformationMapper = new BeanWrapperFieldSetMapper<>();
        InformationMapper.setTargetType(ModelDepositanteDerechoPatrimonial.class);
        return InformationMapper;
    }

    private LineTokenizer createLineTokenizer(DefaultLineMapper<ModelDepositanteDerechoPatrimonial> line) {
        logger.info("start createLineTokenizer...");
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        String[] names = {
                "codDep",
                "codAdmin",
                "codEmisor",
                "isin",
                "codDeposito",
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
                "pagoCud",
                "pagoPdi",
                "cobroCheque",
                "cobroConsig",
                "gravamen",
                "banco",
                "numCta",
                "estadoPdi",
                "impIca",
                "impCre",
                "impAdi",
                "fechaConsig",
                "estadoProRe",
                "montoProAuto",
                "montoProCon",
                "RendAdic",
                "complRein",
                "totalCoPesos",
                "totalPaPesos"
        };

        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 4), //codDep-4
                new Range(5, 9), //codAdmin-5
                new Range(10, 14),//codEmisor-5
                new Range(15, 26),//isin-12
                new Range(27, 36),//codDeposito-10
                new Range(37, 38),//tipDer-2
                new Range(39, 46),//fechaVenc-8
                new Range(47, 54),//ctaInv-8
                new Range(55, 57),//tipId-3
                new Range(58, 72),//idTit-15
                new Range(73, 122),//nomTit-50
                new Range(123, 142),//salCont-20
                new Range(143, 162),//cobDivEfe-20
                new Range(163, 182),//cobDivAcc-20
                new Range(183, 202),//cobCap-20
                new Range(203, 222),//cobRend-20
                new Range(223, 242),//reinv-20
                new Range(243, 262),//recCap-20
                new Range(263, 282),//recDivAcc-20
                new Range(283, 302),//recRend-20
                new Range(303, 322),//rteFte-20
                new Range(323, 342),// enajenacion-20
                new Range(343, 344),// adminDcvl-2
                new Range(345, 354),// preimpreso-10
                new Range(355, 362),// fechaIni-8
                new Range(363, 370),// fechaFin-8
                new Range(371, 376),// tasEfe-6
                new Range(377, 384),// factor-8
                new Range(385, 404),// pagoCud-20
                new Range(405, 424),// pagoPdi-20
                new Range(425, 444),// cobroCheque-20
                new Range(445, 464),// cobroConsig-20
                new Range(465, 484),// gravamen-20
                new Range(485, 499),// banco-15
                new Range(500, 519),// numCta-20
                new Range(520, 549),// estadoPdi-30
                new Range(550, 569),// impIca-20
                new Range(570, 589),// impCre-20
                new Range(590, 609),// impAdi-20
                new Range(610, 617),// fechaConsig-8
                new Range(618, 639),// estadoProRe-30
                new Range(648, 667),// montoProAuto-20
                new Range(668, 687),// montoProCon-20
                new Range(688, 707),// RendAdic-20
                new Range(708, 727),// complRein-20
                new Range(728, 747),// totalCoPesos-20
                new Range(748, 767),// totalPaPesos-20
        };

        tokenizer.setColumns(ranges);

        return tokenizer;
    }


    private LineMapper<ModelDepositanteDerechoPatrimonial> createLineMapper() {
        logger.info("start createLineMapper...");
        DefaultLineMapper<ModelDepositanteDerechoPatrimonial> LineMapper = new DefaultLineMapper<>();

        LineTokenizer LineTokenizer = createLineTokenizer(LineMapper);
        LineMapper.setLineTokenizer(LineTokenizer);

        FieldSetMapper<ModelDepositanteDerechoPatrimonial> InformationMapper = createInformationMapper();
        LineMapper.setFieldSetMapper(InformationMapper);

        return LineMapper;
    }

    @Bean(name = "readerDeposCruceToDcvTbl")
    @StepScope
    FlatFileItemReader<ModelDepositanteDerechoPatrimonial> FileItemReaderDeposiDcvDerePatri() {
        logger.info("FileItemReaderSemanalDcvPatri...");
        ByteArrayOutputStream outputStream = derechoSemanalDcvPatrimonialService.cargarDatosArchivoDcv();
        logger.info("ByteArrayOutputStream... " + outputStream);
        FlatFileItemReader<ModelDepositanteDerechoPatrimonial> FileReader = new FlatFileItemReader<>();
        FileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        LineMapper<ModelDepositanteDerechoPatrimonial> LineMapper = createLineMapper();
        FileReader.setLineMapper(LineMapper);
        return FileReader;
    }

    @Bean
    Step StepDepoDerechosDcvPatrimoniales(@Qualifier("readerDeposCruceToDcvTbl") ItemReader<ModelDepositanteDerechoPatrimonial> readerDeposCargaToDcvTbl,
                                          ItemWriter<ModelDepositanteDerechoPatrimonial> writer, StepBuilderFactory stepBuilderFactory) {
        logger.info("StepSemanalDerechosDcvPatrimoniales...");
        return stepBuilderFactory.get("StepSemanalDerechosDcvPatrimoniales")
                .<ModelDepositanteDerechoPatrimonial, ModelDepositanteDerechoPatrimonial>chunk(50000)
                .reader(readerDeposCargaToDcvTbl)
                .writer(writer)
                .build();
    }

    @Bean(name = "JobDepositanteDerechoPatrimonial")
    Job JobDepositanteDerechoPatrimonial(JobBuilderFactory jobBuilderFactory,
                                         @Qualifier("StepDepoDerechosDcvPatrimoniales") Step dcvDeposDerPatriStep,
                                         StepBuilderFactory stepBuilders
    ) {
        logger.info("carga JobDepositanteDerechoPatrimonial...");
        return jobBuilderFactory.get("JobDepositanteDerechoPatrimonial")
                .incrementer(new RunIdIncrementer())
                .flow(dcvDeposDerPatriStep)
                .end()
                .build();
    }

}
