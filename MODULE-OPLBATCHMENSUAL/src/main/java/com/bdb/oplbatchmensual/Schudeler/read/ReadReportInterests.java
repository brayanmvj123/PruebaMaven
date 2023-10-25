package com.bdb.oplbatchmensual.Schudeler.read;

import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuIntefectDcv;
import com.bdb.opaloshare.persistence.repository.RepositorySalIntefectDcv;
import com.bdb.oplbatchmensual.Schudeler.load.DBWriterReportinterests;
import com.bdb.oplbatchmensual.Schudeler.load.FileReportInterestMonth;
import com.bdb.oplbatchmensual.Schudeler.transform.processorDataInt;
import com.bdb.oplbatchmensual.controller.service.interfaces.InteresesMensualesService;
import com.bdb.oplbatchmensual.persistence.model.Parametros;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ReadReportInterests {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    InteresesMensualesService interesesMensualesService;

    @Autowired
    private RepositoryAcuIntefectDcv repositoryAcuIntefectDcv;

    @Autowired
    private RepositorySalIntefectDcv repositorySalIntefectDcv;

    Parametros parametros = new Parametros();

    private static final Logger LOG = LoggerFactory.getLogger(ReadReportInterests.class);

    @Bean(name = "readerDataReportInterests")
    @StepScope
    RepositoryItemReader<AcuIntefectDcvEntity> generarDataInteReader() {
        LOG.info("Obteniendo la DATA para el reporte mensual de intereses efectuados");
        RepositoryItemReader<AcuIntefectDcvEntity> data = new RepositoryItemReader<>();
        data.setRepository(repositoryAcuIntefectDcv);
        data.setMethodName("findBySysCargueBetween");
        List<LocalDate> argumentos = new ArrayList<>();
        LocalDate[] fecha = interesesMensualesService.fechasConsulta();
        argumentos.add(fecha[0]);
        argumentos.add(fecha[1]);
        data.setArguments(argumentos);
        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("item", Sort.Direction.ASC);
        data.setSort(sorts);
        System.out.println("CUANTO TIEMPO");
        return data;
    }

    @Bean(name = "readerDataOut")
    @StepScope
    RepositoryItemReader<SalIntefectDcvEntity> generarDataOutReader() {
        LOG.info("Obteniendo la DATA para generar el reporte mensual de intereses efectuados");
        RepositoryItemReader<SalIntefectDcvEntity> data = new RepositoryItemReader<>();
        data.setRepository(repositorySalIntefectDcv);
        data.setMethodName("findAll");
        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("item", Sort.Direction.ASC);
        data.setSort(sorts);
        System.out.println("CUANTO TIEMPO");
        return data;
    }

    @Bean
    @StepScope
    ItemProcessor<AcuIntefectDcvEntity, SalIntefectDcvEntity> proccesorDataInt() {
        return new processorDataInt();
    }

    @Bean
    @StepScope
    ItemWriter<SalIntefectDcvEntity> writerData() {
        return new DBWriterReportinterests();
    }

    @Bean
    @StepScope
    ItemWriter<SalIntefectDcvEntity> writerReport() {
        return new FileReportInterestMonth();
    }

    @Bean
    Step StepReportMenInt(@Qualifier("readerDataReportInterests") ItemReader<AcuIntefectDcvEntity> generarDataInteReader,
                          ItemProcessor<AcuIntefectDcvEntity, SalIntefectDcvEntity> proccesorDataInt,
                          ItemWriter<SalIntefectDcvEntity> writerData,
                          StepBuilderFactory stepBuilderFactory) {
        LOG.info("Entro al STEP StepReportMenInt (GENERANDO LA DATA PARA EL ARCHIVO)");
        return stepBuilderFactory.get("StepReportMenInt")
                .<AcuIntefectDcvEntity, SalIntefectDcvEntity>chunk(2000)
                .reader(generarDataInteReader)
                .processor(proccesorDataInt)
                .writer(writerData)
                .build();
    }

    @Bean
    Step StepReportOutMenInt(@Qualifier("readerDataOut") ItemReader<SalIntefectDcvEntity> generarDataOutReader,
                             ItemWriter<SalIntefectDcvEntity> writerReport,
                             StepBuilderFactory stepBuilderFactory) {
        LOG.info("Entro al STEP StepReportOutMenInt (GENERANDO EL ARCHIVO EN LA RUTA INDICADA)");
        return stepBuilderFactory.get("StepReportOutMenInt")
                .<SalIntefectDcvEntity, SalIntefectDcvEntity>chunk(2000)
                .reader(generarDataOutReader)
                .writer(writerReport)
                .build();
    }

    @Bean(name = "JobReportInterests")
    Job JobReportInterests(JobBuilderFactory jobBuilderFactory,
                           @Qualifier("StepReportMenInt") Step StepReportMenInt,
                           @Qualifier("StepReportOutMenInt") Step StepReportOutMenInt) {
        LOG.info("Entro al Job JobReportInterests");
        return jobBuilderFactory.get("JobReportInterests")
                .incrementer(new RunIdIncrementer())
                .flow(StepReportMenInt).next(StepReportOutMenInt)
                .end()
                .build();
    }

}
