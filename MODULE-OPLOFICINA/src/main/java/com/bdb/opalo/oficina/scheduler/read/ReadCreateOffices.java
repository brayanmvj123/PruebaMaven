package com.bdb.opalo.oficina.scheduler.read;

import com.bdb.opalo.oficina.persistence.model.CreateOfficeModel;
import com.bdb.opalo.oficina.scheduler.load.WriterCreateOffices;
import com.bdb.opalo.oficina.scheduler.processor.ProcessorCreateOffice;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
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
public class ReadCreateOffices {

    final JobBuilderFactory builderFactory;

    final StepBuilderFactory stepBuilderFactory;

    public ReadCreateOffices(JobBuilderFactory builderFactory, StepBuilderFactory stepBuilderFactory){
        this.builderFactory = builderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    FlatFileItemReader<CreateOfficeModel> readFileCreateOffice(MetodosGenericos metodosGenericos) {
        log.info("ENTRO AL READ ... LEYENDO EL ARCHIVO DE CREACIÓN DE OFICINAS");

        ByteArrayOutputStream outputStream = metodosGenericos.cargarArchivo();

        FlatFileItemReader<CreateOfficeModel> fileReader = new FlatFileItemReader<>();
        fileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));

        LineMapper<CreateOfficeModel> lineMapper = createLineMapper();
        fileReader.setLineMapper(lineMapper);

        return fileReader;
    }

    private LineMapper<CreateOfficeModel> createLineMapper() {
        DefaultLineMapper<CreateOfficeModel> lineMapper = new DefaultLineMapper<>();

        LineTokenizer lineTokenizer = createLineTokenizer();
        lineMapper.setLineTokenizer(lineTokenizer);

        FieldSetMapper<CreateOfficeModel> informationMapper = createInformationMapper();
        lineMapper.setFieldSetMapper(informationMapper);

        return lineMapper;
    }

    private LineTokenizer createLineTokenizer() {
        log.info("start createLineTokenizer...");
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        String[] names = {
                "fillerOne",
                "office",
                "fillerTwo",
                "nameOffice",
                "fillerFour",
                "indCeo",
                "fillerFive",
                "codCsc",
                "fillerSix"
        };

        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 5), //fillerOne-4
                new Range(6, 9),//office-5
                new Range(10, 49),//fillerTwo-5
                new Range(50, 69),//nameOffice-5
                new Range(70, 78),//fillerFour-5
                new Range(79, 79), //indCeo
                new Range(80, 92),//fillerFive-5
                new Range(93, 96),//codCsc-5
                new Range(94, 160)//fillerSix-5
        };

        tokenizer.setColumns(ranges);

        return tokenizer;
    }

    private FieldSetMapper<CreateOfficeModel> createInformationMapper() {
        BeanWrapperFieldSetMapper<CreateOfficeModel> informationMapper = new BeanWrapperFieldSetMapper<>();
        informationMapper.setTargetType(CreateOfficeModel.class);
        return informationMapper;
    }

    @Bean
    @StepScope
    ItemProcessor<CreateOfficeModel, OficinaParDownEntity> processorOffice(){
        return new ProcessorCreateOffice();
    }

    @Bean
    @StepScope
    ItemWriter<OficinaParDownEntity> writerCreateOffices(){
        return new WriterCreateOffices();
    }

    @Bean
    Step stepCreateOffices(ItemReader<CreateOfficeModel> readFileCreateOffice,
                            ItemProcessor<CreateOfficeModel, OficinaParDownEntity> processorOffice,
                            ItemWriter<OficinaParDownEntity> writerCreateOffices,
                            StepBuilderFactory stepBuilderFactory) {
        log.info("INICIA EL PASO PARA CARGAR EL ARCHIVO PARA LA CREACIÓN DE NUEVAS OFICINAS.");
        return stepBuilderFactory.get("stepCreateOffices")
                .<CreateOfficeModel, OficinaParDownEntity>chunk(2000)
                .reader(readFileCreateOffice)
                .processor(processorOffice)
                .writer(writerCreateOffices)
                .build();
    }

    @Bean(name = "jobCreateOffices")
    Job jobCreateOffices(JobBuilderFactory jobBuilderFactory,
                          @Qualifier("stepCreateOffices") Step stepCreateOffices,
                          StepBuilderFactory stepBuilders) {
        return jobBuilderFactory.get("jobCreateOffices")
                .incrementer(new RunIdIncrementer())
                .flow(stepCreateOffices)
                .end()
                .build();
    }

}
