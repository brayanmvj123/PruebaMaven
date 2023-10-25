package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterTipDepositante;
import com.bdb.opalogdoracle.controller.service.interfaces.TipDepositanteService;
import com.bdb.opaloshare.persistence.entity.TipDepositanteEntity;
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
public class ReadDepositante {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    TipDepositanteService serviceDepositante;

    private static final Logger LOG = LoggerFactory.getLogger(ReadTipCiiu.class);

    @Bean
    @StepScope
    FlatFileItemReader<TipDepositanteEntity> csvFileItemReaderDepositante() {
        
        LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE DEPOSITANTE");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = serviceDepositante.cargarDepositante();

        FlatFileItemReader<TipDepositanteEntity> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        //FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPCIIU_PAR_TBL.csv"));
        csvFileReader.setLinesToSkip(1);

        LineMapper<TipDepositanteEntity> DepositanteLineMapper = createDepositanteLineMapper();
        csvFileReader.setLineMapper(DepositanteLineMapper);

        return csvFileReader;
    }

    private LineMapper<TipDepositanteEntity> createDepositanteLineMapper() {
        DefaultLineMapper<TipDepositanteEntity> DepositanteLineMapper = new DefaultLineMapper<>();

        LineTokenizer DepositanteLineTokenizer = null;
        DepositanteLineTokenizer = createDepositanteLineTokenizer();
        DepositanteLineMapper.setLineTokenizer(DepositanteLineTokenizer);

        FieldSetMapper<TipDepositanteEntity> DepositanteInformationMapper = createDepositanteInformationMapper();
        DepositanteLineMapper.setFieldSetMapper(DepositanteInformationMapper);

        return DepositanteLineMapper;
    }

    private LineTokenizer createDepositanteLineTokenizer() {
        DelimitedLineTokenizer DepositanteLineTokenizer = new DelimitedLineTokenizer();
        DepositanteLineTokenizer.setDelimiter(";");
        DepositanteLineTokenizer.setNames(new String[] { "tipDepositante", "homoDcvsa", "descDepositante" });
        return DepositanteLineTokenizer;
    }

    private FieldSetMapper<TipDepositanteEntity> createDepositanteInformationMapper() {
        BeanWrapperFieldSetMapper<TipDepositanteEntity> DepositanteInformationMapper = new BeanWrapperFieldSetMapper<>();
        DepositanteInformationMapper.setTargetType(TipDepositanteEntity.class);
        return DepositanteInformationMapper;
    }

    @Bean
    @StepScope
    ItemWriter<TipDepositanteEntity> writerDepositante(){
        return new DBWriterTipDepositante();
    }

    @Bean
    @Qualifier("StepDepositante")
    Step StepDepositante(ItemReader<TipDepositanteEntity> csvFileItemReaderDepositante,
                  //ItemProcessor<CIIUEntity, CIIUEntity> csvFileItemProcessor,
                  ItemWriter<TipDepositanteEntity> writerDepositante,
                  StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepDepositante")
                .<TipDepositanteEntity, TipDepositanteEntity>chunk(2000)
                .reader(csvFileItemReaderDepositante)
                //.processor(csvFileItemProcessor)
                .writer(writerDepositante)
                .build();
    }

    @Bean(name = "JobDepositante")
    Job JobDepositante(JobBuilderFactory jobBuilderFactory,
                @Qualifier("StepDepositante") Step csvDepositanteStep) {
        return jobBuilderFactory.get("JobDepositante")
                .incrementer(new RunIdIncrementer())
                .flow(csvDepositanteStep)
                .end()
                .build();
    }
}
