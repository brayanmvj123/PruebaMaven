package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterTipSegmento;
import com.bdb.opalogdoracle.controller.service.interfaces.TipSegmentoService;
import com.bdb.opaloshare.persistence.entity.TipSegmentoEntity;
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
public class ReadSegmento {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    TipSegmentoService serviceTipSegmento;

    private static final Logger LOG = LoggerFactory.getLogger(ReadSegmento.class);

    @Bean
    @StepScope
    FlatFileItemReader<TipSegmentoEntity> csvFileItemReaderSegmento() {

        LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE Segmento");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = serviceTipSegmento.cargarSegmento();

        FlatFileItemReader<TipSegmentoEntity> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        //FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPCIIU_PAR_TBL.csv"));
        csvFileReader.setLinesToSkip(1);

        LineMapper<TipSegmentoEntity> SegmentoLineMapper = createSegmentoLineMapper();
        csvFileReader.setLineMapper(SegmentoLineMapper);

        return csvFileReader;
    }

    private LineMapper<TipSegmentoEntity> createSegmentoLineMapper() {
        DefaultLineMapper<TipSegmentoEntity> SegmentoLineMapper = new DefaultLineMapper<>();

        LineTokenizer SegmentoLineTokenizer = createSegmentoLineTokenizer();
        SegmentoLineMapper.setLineTokenizer(SegmentoLineTokenizer);

        FieldSetMapper<TipSegmentoEntity> SegmentoInformationMapper = createSegmentoInformationMapper();
        SegmentoLineMapper.setFieldSetMapper(SegmentoInformationMapper);

        return SegmentoLineMapper;
    }

    private LineTokenizer createSegmentoLineTokenizer() {
        DelimitedLineTokenizer SegmentoLineTokenizer = new DelimitedLineTokenizer();
        SegmentoLineTokenizer.setDelimiter(";");
        SegmentoLineTokenizer.setNames(new String[] { "codSegmento", "homoCrm", "desSegmento" });
        return SegmentoLineTokenizer;
    }

    private FieldSetMapper<TipSegmentoEntity> createSegmentoInformationMapper() {
        BeanWrapperFieldSetMapper<TipSegmentoEntity> SegmentoInformationMapper = new BeanWrapperFieldSetMapper<>();
        SegmentoInformationMapper.setTargetType(TipSegmentoEntity.class);
        return SegmentoInformationMapper;
    }

    @Bean
    @StepScope
    ItemWriter<TipSegmentoEntity> writerSegmento(){
        return new DBWriterTipSegmento();
    }

    @Bean
    @Qualifier("StepSegmento")
    Step StepSegmento(ItemReader<TipSegmentoEntity> csvFileItemReaderSegmento,
                         //ItemProcessor<CIIUEntity, CIIUEntity> csvFileItemProcessor,
                         ItemWriter<TipSegmentoEntity> writerSegmento,
                         StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepSegmento")
                .<TipSegmentoEntity, TipSegmentoEntity>chunk(2000)
                .reader(csvFileItemReaderSegmento)
                //.processor(csvFileItemProcessor)
                .writer(writerSegmento)
                .build();
    }

    @Bean(name = "JobSegmento")
    Job JobSegmento(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("StepSegmento") Step csvSegmentoStep) {
        return jobBuilderFactory.get("JobSegmento")
                .incrementer(new RunIdIncrementer())
                .flow(csvSegmentoStep)
                .end()
                .build();
    }
    
}
