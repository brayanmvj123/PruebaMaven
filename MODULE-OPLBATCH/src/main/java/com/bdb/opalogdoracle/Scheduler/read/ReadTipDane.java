package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterTipDane;
import com.bdb.opalogdoracle.controller.service.interfaces.TipDaneService;
import com.bdb.opaloshare.persistence.entity.TipDaneEntity;

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
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;

@Configuration
@EnableBatchProcessing
public class ReadTipDane {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    TipDaneService serviceDane;

    private static final Logger LOG = LoggerFactory.getLogger(ReadTipCiiu.class);

    @Bean
    @StepScope
    FlatFileItemReader<TipDaneEntity> csvFileItemReaderDane() {

        LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE DANE");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = serviceDane.cargarDane();

        FlatFileItemReader<TipDaneEntity> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        //FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPCIIU_PAR_TBL.csv"));
        csvFileReader.setLinesToSkip(1);

        LineMapper<TipDaneEntity> DaneLineMapper = createDaneLineMapper();
        csvFileReader.setLineMapper(DaneLineMapper);

        return csvFileReader;
    }

    private LineMapper<TipDaneEntity> createDaneLineMapper() {
        DefaultLineMapper<TipDaneEntity> DaneLineMapper = new DefaultLineMapper<>();

        LineTokenizer DaneLineTokenizer = null;
        DaneLineTokenizer = createDaneLineTokenizer();
        DaneLineMapper.setLineTokenizer(DaneLineTokenizer);

        FieldSetMapper<TipDaneEntity> DaneInformationMapper = createDaneInformationMapper();
        DaneLineMapper.setFieldSetMapper(DaneInformationMapper);

        return DaneLineMapper;
    }

    private LineTokenizer createDaneLineTokenizer() {
        DelimitedLineTokenizer DaneLineTokenizer = new DelimitedLineTokenizer();
        DaneLineTokenizer.setDelimiter(";");
        DaneLineTokenizer.setNames(new String[] { "codDane", "homoCrm", "desDane" });
        return DaneLineTokenizer;
    }

    private FieldSetMapper<TipDaneEntity> createDaneInformationMapper() {
        BeanWrapperFieldSetMapper<TipDaneEntity> DaneInformationMapper = new BeanWrapperFieldSetMapper<>();
        DaneInformationMapper.setTargetType(TipDaneEntity.class);
        return DaneInformationMapper;
    }

    @Bean
    @StepScope
    ItemWriter<TipDaneEntity> writerDane(){
        return new DBWriterTipDane();
    }

    @Bean
    @Qualifier("StepDane")
    Step StepDane(ItemReader<TipDaneEntity> csvFileItemReaderDane,
                  //ItemProcessor<CIIUEntity, CIIUEntity> csvFileItemProcessor,
                  ItemWriter<TipDaneEntity> writerDane,
                  StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepDane")
                .<TipDaneEntity, TipDaneEntity>chunk(2000)
                .reader(csvFileItemReaderDane)
                //.processor(csvFileItemProcessor)
                .writer(writerDane)
                .build();
    }

    @Bean(name = "JobDane")
    Job JobDane(JobBuilderFactory jobBuilderFactory,
                @Qualifier("StepDane") Step csvDaneStep) {
        return jobBuilderFactory.get("JobDane")
                .incrementer(new RunIdIncrementer())
                .flow(csvDaneStep)
                .end()
                .build();
    }
    
}
