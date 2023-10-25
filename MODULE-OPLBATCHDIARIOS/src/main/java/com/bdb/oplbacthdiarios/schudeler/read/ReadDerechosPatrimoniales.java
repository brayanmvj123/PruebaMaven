package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.persistence.entity.CarDerpatridepDownEntity;
import com.bdb.oplbacthdiarios.controller.service.interfaces.DerechosPatrimonialesService;
import com.bdb.oplbacthdiarios.schudeler.load.CrossInfoPatrimonialesTasklet;
import com.bdb.oplbacthdiarios.schudeler.load.DBWriterDerPatri;
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

@Configuration
@EnableBatchProcessing
public class ReadDerechosPatrimoniales {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    DerechosPatrimonialesService derechosPatrimonialesService;

    @Bean(name = "archivoDepatriDep")
    @StepScope
    FlatFileItemReader<CarDerpatridepDownEntity> archivoDepatriDep(MetodosGenericos metodosGenericos) {
        logger.info("Inicio la lectura del archivo Derechos patrimoniales Depositante...");
        ByteArrayOutputStream outputStream = derechosPatrimonialesService.cargar();
        //metodosGenericos.cargarArchivo();
        FlatFileItemReader<CarDerpatridepDownEntity> fileReader = new FlatFileItemReader<>();
        fileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        LineMapper<CarDerpatridepDownEntity> lineMapper = createLineMapper();
        fileReader.setLineMapper(lineMapper);
        return fileReader;
    }

    private LineMapper<CarDerpatridepDownEntity> createLineMapper() {
        logger.info("start createLineMapper...");
        DefaultLineMapper<CarDerpatridepDownEntity> lineMapper = new DefaultLineMapper<>();

        LineTokenizer lineTokenizer = createLineTokenizer();
        lineMapper.setLineTokenizer(lineTokenizer);

        FieldSetMapper<CarDerpatridepDownEntity> informationMapper = createInformationMapper();
        lineMapper.setFieldSetMapper(informationMapper);

        return lineMapper;
    }

    private LineTokenizer createLineTokenizer() {
        logger.info("start createLineTokenizer...");
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        String[] names = {
                "codDepositante",
                "codAdmin",
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
                "pgCud",
                "pgPdi",
                "cobCheque",
                "cobCons",
                "grav",
                "banco",
                "nroCta",
                "estPgPdi",
                "impIca",
                "impCre",
                "impAdi",
                "fechaCons",
                "estProRein",
                "monProCAut",
                "monProCon",
                "rendAdi",
                "compRein",
                "totCobCop",
                "totPgCop"
        };

        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 4), //codDepositante-4
                new Range(5, 9),//codAdmin-5
                new Range(10, 14),//codEmisor-5
                new Range(15, 26),//isin-12
                new Range(27, 36),//codDep-10
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
                new Range(243, 262),//recap-20
                new Range(263, 282),//recDivAcc-20
                new Range(283, 302),//recrend-20
                new Range(303, 322),// rteFte-20
                new Range(323, 342),// enajenacion-20
                new Range(343, 344),// adminDcvl-2
                new Range(345, 354),// preimpreso-10
                new Range(355, 362),// fechaIni-8
                new Range(363, 370),// fechaFin-8
                new Range(371, 376),// tasEfe-6
                new Range(377, 384),// factor-8
                new Range(385, 404),// pgCud-20
                new Range(405, 424),// pgPdi-20
                new Range(425, 444),// cobCheque-20
                new Range(445, 464),// cobCons-20
                new Range(465, 484),// grav-20
                new Range(485, 499),// banco-15
                new Range(500, 510),// nroCta-20
                new Range(511, 549),// estPgPdi-30
                new Range(550, 569),// impIca-20
                new Range(570, 589),// impCre-20
                new Range(590, 609),// impAdi-20
                new Range(610, 617),// fechaCons-8
                new Range(618, 647),// estProRein-30
                new Range(648, 667),// monProCAut-20
                new Range(668, 687),// monProCon-20
                new Range(688, 707),// rendAdi-20
                new Range(708, 727),// compAdi-20
                new Range(728, 747),// totCobCop-20
                new Range(748, 767),// totPgCop-20
        };

        tokenizer.setColumns(ranges);

        return tokenizer;
    }

    private FieldSetMapper<CarDerpatridepDownEntity> createInformationMapper() {
        logger.info("start createInformationMapper...");
        BeanWrapperFieldSetMapper<CarDerpatridepDownEntity> informationMapper = new BeanWrapperFieldSetMapper<>();
        informationMapper.setTargetType(CarDerpatridepDownEntity.class);
        return informationMapper;
    }

    @Bean
    @StepScope
    ItemWriter<CarDerpatridepDownEntity> writer() {
        logger.info("Inicio writer CarDerpatridepDownEntity");
        return new DBWriterDerPatri();
    }

    @Bean
    public CrossInfoPatrimonialesTasklet crossInfoPatrimonialesTasklet() {
        logger.info("start crossInfoPatrimonialesTasklet...");
        return new CrossInfoPatrimonialesTasklet();
    }

    @Bean
    Step crossInfoPatrimonioStep(StepBuilderFactory stepBuilders) {
        logger.info("start crossInfoPatrimonioStep...");
        return stepBuilders.get("crossInfoPatrimonioStep")
                .tasklet(crossInfoPatrimonialesTasklet()).build();
    }

    @Bean
    Step stepDerechosPatrimoniales(@Qualifier("archivoDepatriDep") ItemReader<CarDerpatridepDownEntity> archivoDepatriDep,
                                   ItemWriter<CarDerpatridepDownEntity> writer,
                                   StepBuilderFactory stepBuilderFactory) {
        logger.info("stepDerechosPatrimoniales...");
        return stepBuilderFactory.get("stepDerechosPatrimoniales")
                .<CarDerpatridepDownEntity, CarDerpatridepDownEntity>chunk(50000)
                .reader(archivoDepatriDep)
                .writer(writer)
                .build();
    }

    @Bean(name = "JobDerechosPatrimoniales")
    Job JobDerechosPatrimoniales(JobBuilderFactory jobBuilderFactory,
                                 @Qualifier("stepDerechosPatrimoniales") Step derPatriStep,
                                 StepBuilderFactory stepBuilders
    ) {
        logger.info("carga JobDerechosPatrimoniales...");
        return jobBuilderFactory.get("JobDerechosPatrimoniales")
                .incrementer(new RunIdIncrementer())
                .flow(derPatriStep)
                .next(crossInfoPatrimonioStep(stepBuilders))
                .end()
                .build();
    }

    @Bean(name = "JobCrucePatrimoniales")
    Job JobCrucePatrimoniales(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilders
    ) {
        logger.info("carga JobDerechosPatrimoniales...");
        return jobBuilderFactory.get("JobDerechosPatrimoniales")
                .incrementer(new RunIdIncrementer())
                .flow(crossInfoPatrimonioStep(stepBuilders))
                .end()
                .build();
    }

}
