package com.bdb.opalo.batchocasional.Scheduler.read;

import com.bdb.opalo.batchocasional.Scheduler.load.DBWriterTasaVariable;
import com.bdb.opalo.batchocasional.Scheduler.transform.TransformTasaVariable;
import com.bdb.opalo.batchocasional.controller.service.interfaces.CargueTasasVariablesFileService;
import com.bdb.opalo.batchocasional.persistence.model.TasaVariableFileModel;
import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
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
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadTasaVariableFile {


    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    CargueTasasVariablesFileService cargueTasasVariablesFileService;

    @Bean
    @StepScope
    FlatFileItemReader<TasaVariableFileModel> csvFileItemReaderTasaVariableFile() {

        log.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE Tipo Tasa");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = cargueTasasVariablesFileService.cargarArchivo();

        FlatFileItemReader<TasaVariableFileModel> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
                //new FileSystemResource("C:\\Banco_de_bogota\\desarrollo\\documentos\\TASAS26.CSV"));
        csvFileReader.setLinesToSkip(2);

        LineMapper<TasaVariableFileModel> LineMapper = createLineMapper();
        csvFileReader.setLineMapper(LineMapper);

        return csvFileReader;
    }

    private LineMapper<TasaVariableFileModel> createLineMapper() {
        DefaultLineMapper<TasaVariableFileModel> DaneLineMapper = new DefaultLineMapper<>();

        LineTokenizer DaneLineTokenizer = null;
        DaneLineTokenizer = createDaneLineTokenizer();
        DaneLineMapper.setLineTokenizer(DaneLineTokenizer);

        FieldSetMapper<TasaVariableFileModel> DaneInformationMapper = createDaneInformationMapper();
        DaneLineMapper.setFieldSetMapper(DaneInformationMapper);

        return DaneLineMapper;
    }

    private LineTokenizer createDaneLineTokenizer() {
        DelimitedLineTokenizer LineTokenizer = new DelimitedLineTokenizer();
        LineTokenizer.setDelimiter(",");
        LineTokenizer.setStrict(false);
        LineTokenizer.setNames(new String[] {"id",
        "fecha",
        "dtf",
        "fbor",
        "usur",
        "uvr",
        "uvr_real",
        "uvr_proyectado",
        "ibrDiaria",
        "ibrMensual",
        "ibrTrimestral",
        "ibrSemestral","ipc"
            });
        return LineTokenizer;
    }

    private FieldSetMapper<TasaVariableFileModel> createDaneInformationMapper() {
        BeanWrapperFieldSetMapper<TasaVariableFileModel> DaneInformationMapper = new BeanWrapperFieldSetMapper<>();
        DaneInformationMapper.setTargetType(TasaVariableFileModel.class);
        return DaneInformationMapper;
    }

    @Bean
    @StepScope
    ItemProcessor<TasaVariableFileModel, List<OplHisTasaVariableEntity>> processorTasasVariablesFile(){
        return new TransformTasaVariable();
    }

    @Bean
    @StepScope
    ItemWriter<List<OplHisTasaVariableEntity>> writerTasasVariableFile(){
        return new DBWriterTasaVariable();
    }

    @Bean
    @Qualifier("StepTasasVariablesFile")
    Step StepTasasVariablesFile(ItemReader<TasaVariableFileModel> csvFileItemReaderTasaVariableFile,
                                ItemProcessor<TasaVariableFileModel,List<OplHisTasaVariableEntity>> processorTasasVariablesFile,
                                ItemWriter<List<OplHisTasaVariableEntity>> writerTasasVariableFile,
                                StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepTasasVariablesFile")
                .<TasaVariableFileModel, List<OplHisTasaVariableEntity>>chunk(2000)
                .reader(csvFileItemReaderTasaVariableFile)
                .processor(processorTasasVariablesFile)
                .writer(writerTasasVariableFile)
                .build();
    }

    @Bean(name = "JobTasasVariablesFile")
    Job JobTasasVariablesFile(JobBuilderFactory jobBuilderFactory,
                @Qualifier("StepTasasVariablesFile") Step csvDaneStep) {
        return jobBuilderFactory.get("JobTasasVariablesFile")
                .incrementer(new RunIdIncrementer())
                .flow(csvDaneStep)
                .end()
                .build();
    }

}
