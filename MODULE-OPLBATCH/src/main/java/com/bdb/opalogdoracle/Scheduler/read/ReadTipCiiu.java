package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterTipCiiu;
import com.bdb.opalogdoracle.controller.service.interfaces.TipCiiuService;
import com.bdb.opaloshare.persistence.entity.TipCIIUEntity;
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
public class ReadTipCiiu {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    TipCiiuService serviceCIIU;

    private static final Logger LOG = LoggerFactory.getLogger(ReadTipCiiu.class);

    @Bean
    @StepScope
    FlatFileItemReader<TipCIIUEntity> csvFileItemReaderCIIU() {

        LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE CIUU");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = serviceCIIU.cargarCIIU();

        FlatFileItemReader<TipCIIUEntity> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        //FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPCIIU_PAR_TBL.csv"));
        csvFileReader.setLinesToSkip(1);

        LineMapper<TipCIIUEntity> CIIULineMapper = createCIIULineMapper();
        csvFileReader.setLineMapper(CIIULineMapper);

        return csvFileReader;
    }

    private LineMapper<TipCIIUEntity> createCIIULineMapper() {
        DefaultLineMapper<TipCIIUEntity> CIIULineMapper = new DefaultLineMapper<>();

        LineTokenizer CIIULineTokenizer = null;
        CIIULineTokenizer = createCIIULineTokenizer();
        CIIULineMapper.setLineTokenizer(CIIULineTokenizer);

        FieldSetMapper<TipCIIUEntity> CIIUInformationMapper = createCIIUInformationMapper();
        CIIULineMapper.setFieldSetMapper(CIIUInformationMapper);

        return CIIULineMapper;
    }

    private LineTokenizer createCIIULineTokenizer() {
        DelimitedLineTokenizer CIIULineTokenizer = new DelimitedLineTokenizer();
        CIIULineTokenizer.setDelimiter(";");
        CIIULineTokenizer.setNames(new String[] { "codCiiu", "homoCrm", "desCiiu" });
        return CIIULineTokenizer;
    }

    private FieldSetMapper<TipCIIUEntity> createCIIUInformationMapper() {
        BeanWrapperFieldSetMapper<TipCIIUEntity> CIIUInformationMapper = new BeanWrapperFieldSetMapper<>();
        CIIUInformationMapper.setTargetType(TipCIIUEntity.class);
        return CIIUInformationMapper;
    }

    @Bean
    @StepScope
    ItemWriter<TipCIIUEntity> writerCIIU(){
        return new DBWriterTipCiiu();
    }

    @Bean
    Step StepCIIU(ItemReader<TipCIIUEntity> csvFileItemReaderCIIU,
                  //ItemProcessor<CIIUEntity, CIIUEntity> csvFileItemProcessor,
                  ItemWriter<TipCIIUEntity> writerCIIU,
                  StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepCIIU")
                .<TipCIIUEntity, TipCIIUEntity>chunk(2000)
                .reader(csvFileItemReaderCIIU)
                //.processor(csvFileItemProcessor)
                .writer(writerCIIU)
                .build();
    }

    @Bean(name = "JobCIIU")
    Job JobCIIU(JobBuilderFactory jobBuilderFactory,
                @Qualifier("StepCIIU") Step csvCIIUStep) {
        return jobBuilderFactory.get("JobCIIU")
                .incrementer(new RunIdIncrementer())
                .flow(csvCIIUStep)
                .end()
                .build();
    }
}
