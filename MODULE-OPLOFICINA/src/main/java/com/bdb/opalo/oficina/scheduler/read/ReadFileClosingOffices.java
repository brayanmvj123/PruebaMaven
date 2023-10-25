package com.bdb.opalo.oficina.scheduler.read;

import com.bdb.opalo.oficina.scheduler.load.WriterClosingOffices;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.persistence.entity.CarCierreOfiEntity;
import lombok.extern.apachecommons.CommonsLog;
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

@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadFileClosingOffices {

    public final JobBuilderFactory builderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    public ReadFileClosingOffices(JobBuilderFactory builderFactory, StepBuilderFactory stepBuilderFactory) {
        this.builderFactory = builderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    FlatFileItemReader<CarCierreOfiEntity> readFileCloseOffice(MetodosGenericos metodosGenericos) {
        log.info("ENTRO AL READ ... LEYENDO EL ARCHIVO DE CIERRE DE OFICINAS");

        ByteArrayOutputStream outputStream = metodosGenericos.cargarArchivo();

        FlatFileItemReader<CarCierreOfiEntity> fileReader = new FlatFileItemReader<>();
        fileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        //fileReader.setLinesToSkip(1);

        LineMapper<CarCierreOfiEntity> lineMapper = createLineMapper();
        fileReader.setLineMapper(lineMapper);

        return fileReader;
    }

    private LineMapper<CarCierreOfiEntity> createLineMapper() {
        DefaultLineMapper<CarCierreOfiEntity> lineMapper = new DefaultLineMapper<>();

        LineTokenizer lineTokenizer = createLineTokenizer();
        lineMapper.setLineTokenizer(lineTokenizer);

        FieldSetMapper<CarCierreOfiEntity> informationMapper = createInformationMapper();
        lineMapper.setFieldSetMapper(informationMapper);

        return lineMapper;
    }

    private LineTokenizer createLineTokenizer() {
        log.info("start createLineTokenizer...");
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        String[] names = {
                "tipCta",
                "numCta",
                "tipId",
                "numId",
                "ceo",
                "ofiOri",
                "ofiDes",
                "segmento",
                "fechaCie",
                "filler"
        };

        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 2), //tipCta-4
                new Range(3, 20),//numCta-5
                new Range(21, 21),//tipId-5
                new Range(22, 32),//numId-5
                new Range(33, 36),//ceo-5
                new Range(37, 40),//ofiOri-5
                new Range(41, 44),//ofiDes-5
                new Range(45, 46),//segmento-5
                new Range(47, 54),//fechaCie-5
                new Range(55, 80) //filler
        };

        tokenizer.setColumns(ranges);

        return tokenizer;
    }

    private FieldSetMapper<CarCierreOfiEntity> createInformationMapper() {
        BeanWrapperFieldSetMapper<CarCierreOfiEntity> informationMapper = new BeanWrapperFieldSetMapper<>();
        informationMapper.setTargetType(CarCierreOfiEntity.class);
        return informationMapper;
    }

    @Bean
    @StepScope
    ItemWriter<CarCierreOfiEntity> writerClosingOffices(){
        return new WriterClosingOffices();
    }

    @Bean
    Step stepClosingOffices(ItemReader<CarCierreOfiEntity> readFileCloseOffice,
                        ItemWriter<CarCierreOfiEntity> writerClosingOffices,
                        StepBuilderFactory stepBuilderFactory) {
        log.info("INICIA EL PASO PARA CARGAR EL ARCHIVO DE CIERRE DE OFICINAS.");
        return stepBuilderFactory.get("stepClosingOffices")
                .<CarCierreOfiEntity, CarCierreOfiEntity>chunk(2000)
                .reader(readFileCloseOffice)
                .writer(writerClosingOffices)
                .build();
    }

    @Bean
    @Qualifier(value = "stepUpdateCdtOfficeId")
    Step stepUpdateCdtOfficeId(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO DE ACTUALIZACIÓN DEL ID DE LAS OFICINAS QUE CIERRAN DE LOS CDTS DIGITALES ...");
        return stepBuilders
                .get("stepUpdateCdtOfficeId")
                .tasklet(updateCdtOfficeIdTasklet())
                .build();
    }

    @Bean
    public UpdateCdtOfficeIdTasklet updateCdtOfficeIdTasklet() {
        log.info("Se inicia el cierre de oficinas en la tabla HIS_CDTS ...");
        return new UpdateCdtOfficeIdTasklet();
    }

    @Bean
    @Qualifier(value = "stepUpdateTranPgOfficeId")
    Step stepUpdateTranPgOfficeId(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO DE ACTUALIZACIÓN DEL ID DE LAS OFICINAS QUE CIERRAN DE LOS TRANPG DIGITALES ...");
        return stepBuilders
                .get("stepUpdateTranPgOfficeId")
                .tasklet(updateTranPgOfficeIdTasklet())
                .build();
    }

    @Bean
    public UpdateTranPgOfficeIdTasklet updateTranPgOfficeIdTasklet() {
        log.info("Se inicia el cierre de oficinas en la tabla HIS_TRANPG ...");
        return new UpdateTranPgOfficeIdTasklet();
    }

    @Bean
    @Qualifier(value = "stepUpdateOfficeStatus")
    Step stepUpdateOfficeStatus(StepBuilderFactory stepBuilders) {
        log.info("INICIA EL PASO DE ACTUALIZACIÓN DEL ESTADO DE LAS OFICINAS QUE CIERRAN ...");
        return stepBuilders
                .get("stepUpdateOfficeStatus")
                .tasklet(updateOfficeStatusTasklet())
                .build();
    }

    @Bean
    public UpdateOfficesStatusTasklet updateOfficeStatusTasklet() {
        log.info("Se inicia el cambio de ESTADO en la tabla de OFICINAS ...");
        return new UpdateOfficesStatusTasklet();
    }

    @Bean(name = "jobClosingOffices")
    Job jobClosingOffices(JobBuilderFactory jobBuilderFactory,
                      @Qualifier("stepClosingOffices") Step stepClosingOffices,
                          StepBuilderFactory stepBuilders) {
        return jobBuilderFactory.get("jobClosingOffices")
                .incrementer(new RunIdIncrementer())
                .flow(stepClosingOffices)
                .end()
                .build();
    }

    @Bean(name = "jobUpdateOffices")
    Job jobUpdateOffices(JobBuilderFactory jobBuilderFactory,
                         @Qualifier(value = "stepUpdateCdtOfficeId") Step stepUpdateCdtOfficeId,
                         @Qualifier(value = "stepUpdateTranPgOfficeId") Step stepUpdateTranPgOfficeId,
                         @Qualifier(value = "stepUpdateOfficeStatus") Step stepUpdateOfficeStatus) {
        return jobBuilderFactory.get("jobUpdateOffices")
                .incrementer(new RunIdIncrementer())
                .flow(stepUpdateCdtOfficeId)
                .next(stepUpdateTranPgOfficeId)
                .next(stepUpdateOfficeStatus)
                .end()
                .build();
    }
}
