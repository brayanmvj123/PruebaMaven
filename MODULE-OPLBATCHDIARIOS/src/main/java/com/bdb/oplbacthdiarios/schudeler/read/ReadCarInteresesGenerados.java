package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import com.bdb.opaloshare.persistence.entity.CarIntefectDcvEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarIntereses;
import com.bdb.oplbacthdiarios.persistence.model.CarInteresesModel;
import com.bdb.oplbacthdiarios.schudeler.load.DBWriterCartoAcuIntereses;
import com.bdb.oplbacthdiarios.schudeler.load.DBWriterIntereses;
import com.bdb.oplbacthdiarios.schudeler.transform.ProcessorCarIntereses;
import com.bdb.oplbacthdiarios.schudeler.transform.ProcessorCartoAcuIntereses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.batch.item.data.RepositoryItemReader;
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
import org.springframework.data.domain.Sort;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

@Configuration
@EnableBatchProcessing
public class ReadCarInteresesGenerados {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private static final Logger LOG = LoggerFactory.getLogger(ReadCarInteresesGenerados.class);

    @Bean(name = "readerDataInteresesDCV")
    @StepScope
    FlatFileItemReader<CarInteresesModel> fileItemReaderInteresesGen(MetodosGenericos metodosGenericos) {

        LOG.info("Leyendo el archivo enviado por DCVBTA... INTERESES EFECTUADOS");

        ByteArrayOutputStream outputStream = metodosGenericos.cargarArchivo();

        FlatFileItemReader<CarInteresesModel> fileReader = new FlatFileItemReader<>();
        fileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        //fileReader.setLinesToSkip(3);

        LineMapper<CarInteresesModel> interesesLineMapper = createCDTLineMapper();
        fileReader.setLineMapper(interesesLineMapper);

        return fileReader;
    }

    @Bean(name = "readerAcuDataInterests")
    @StepScope
    RepositoryItemReader<CarIntefectDcvEntity> acumularDataInteReader(RepositoryCarIntereses repositoryCarIntereses) {
        LOG.info("Acumulando la DATA para el reporte mensual de intereses efectuados");
        RepositoryItemReader<CarIntefectDcvEntity> data = new RepositoryItemReader<>();
        data.setRepository(repositoryCarIntereses);
        data.setMethodName("findAll");
        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("item", Sort.Direction.ASC);
        data.setSort(sorts);
        return data;
    }

    private LineMapper<CarInteresesModel> createCDTLineMapper() {
        DefaultLineMapper<CarInteresesModel> cdtLineMapper = new DefaultLineMapper<>();

        LineTokenizer interesesLineTokenizer = null;
        interesesLineTokenizer = createInteresesLineTokenizer();
        cdtLineMapper.setLineTokenizer(interesesLineTokenizer);

        FieldSetMapper<CarInteresesModel> interesesInformationMapper = createInteresesInformationMapper();
        cdtLineMapper.setFieldSetMapper(interesesInformationMapper);

        return cdtLineMapper;
    }

    private LineTokenizer createInteresesLineTokenizer() {
        DelimitedLineTokenizer cdtLineTokenizer = new DelimitedLineTokenizer();
        cdtLineTokenizer.setDelimiter(";");
        cdtLineTokenizer.setNames("codTrans", "fechaReal", "fechaContable", "numCta", "oficinaRecep",
                "interesBruto", "interesNeto", "numTit", "formaPago", "periodo", "texto", "seccOrigen", "filler");
        return cdtLineTokenizer;
    }

    private FieldSetMapper<CarInteresesModel> createInteresesInformationMapper() {
        BeanWrapperFieldSetMapper<CarInteresesModel> interesesInformationMapper = new BeanWrapperFieldSetMapper<>();
        interesesInformationMapper.setTargetType(CarInteresesModel.class);
        return interesesInformationMapper;
    }

    @Bean
    @StepScope
    ItemProcessor<CarInteresesModel, CarIntefectDcvEntity> processorCarIntereses() {
        return new ProcessorCarIntereses();
    }

    @Bean
    @StepScope
    ItemWriter<CarIntefectDcvEntity> interesesDBWriter() {
        return new DBWriterIntereses();
    }

    @Bean
    @StepScope
    ItemProcessor<CarIntefectDcvEntity, AcuIntefectDcvEntity> processorCartoAcuIntereses() {
        return new ProcessorCartoAcuIntereses();
    }

    @Bean
    @StepScope
    ItemWriter<AcuIntefectDcvEntity> almacenarDBWriter() {
        return new DBWriterCartoAcuIntereses();
    }

    @Bean
    Step StepCargaIntereses(@Qualifier("readerDataInteresesDCV") ItemReader<CarInteresesModel> fileItemReaderInteresesGen,
                            ItemProcessor<CarInteresesModel, CarIntefectDcvEntity> processorCarIntereses,
                            ItemWriter<CarIntefectDcvEntity> interesesDBWriter,
                            StepBuilderFactory stepBuilderFactory) {
        LOG.info("Entro al STEP StepCargaIntereses");
        return stepBuilderFactory.get("StepCargaIntereses")
                .<CarInteresesModel, CarIntefectDcvEntity>chunk(2000)
                .reader(fileItemReaderInteresesGen)
                .processor(processorCarIntereses)
                .writer(interesesDBWriter)
                .build();
    }

    @Bean
    Step StepAcuDataIntereses(@Qualifier("readerAcuDataInterests") ItemReader<CarIntefectDcvEntity> acumularDataInteReader,
                              ItemProcessor<CarIntefectDcvEntity, AcuIntefectDcvEntity> processorCartoAcuIntereses,
                              ItemWriter<AcuIntefectDcvEntity> almacenarDBWriter,
                              StepBuilderFactory stepBuilderFactory) {
        LOG.info("Entro al STEP StepAcuDataIntereses");
        return stepBuilderFactory.get("StepAcuDataIntereses")
                .<CarIntefectDcvEntity, AcuIntefectDcvEntity>chunk(2000)
                .reader(acumularDataInteReader)
                .processor(processorCartoAcuIntereses)
                .writer(almacenarDBWriter)
                .build();
    }

    @Bean(name = "JobCargaIntereses")
    Job JobCargaIntereses(JobBuilderFactory jobBuilderFactory,
                          @Qualifier("StepCargaIntereses") Step StepCargaIntereses,
                          @Qualifier("StepAcuDataIntereses") Step StepAcuDataIntereses) {
        LOG.info("Entro al job JobCargaIntereses");
        return jobBuilderFactory.get("JobCargaIntereses")
                .incrementer(new RunIdIncrementer())
                .flow(StepCargaIntereses).next(StepAcuDataIntereses)
                .end()
                .build();
    }
}
