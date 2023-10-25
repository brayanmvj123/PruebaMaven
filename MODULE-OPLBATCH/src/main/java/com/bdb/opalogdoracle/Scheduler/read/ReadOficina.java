package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterOficina;
import com.bdb.opalogdoracle.controller.service.interfaces.OficinaService;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
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
public class ReadOficina {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    OficinaService serviceOficina;

    private static final Logger LOG = LoggerFactory.getLogger(ReadTipCiiu.class);

    @Bean
    @StepScope
    FlatFileItemReader<OficinaParDownEntity> csvFileItemReaderOficina() {

        LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE OFICINA");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = serviceOficina.cargarOficina();

        FlatFileItemReader<OficinaParDownEntity> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
        //FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPCIIU_PAR_TBL.csv"));
        csvFileReader.setLinesToSkip(1);

        LineMapper<OficinaParDownEntity> OficinaLineMapper = createOficinaLineMapper();
        csvFileReader.setLineMapper(OficinaLineMapper);

        return csvFileReader;
    }

    private LineMapper<OficinaParDownEntity> createOficinaLineMapper() {
        DefaultLineMapper<OficinaParDownEntity> OficinaLineMapper = new DefaultLineMapper<>();

        LineTokenizer OficinaLineTokenizer = createOficinaLineTokenizer();
        OficinaLineMapper.setLineTokenizer(OficinaLineTokenizer);

        FieldSetMapper<OficinaParDownEntity> OficinaInformationMapper = createOficinaInformationMapper();
        OficinaLineMapper.setFieldSetMapper(OficinaInformationMapper);

        return OficinaLineMapper;
    }

    private LineTokenizer createOficinaLineTokenizer() {
        DelimitedLineTokenizer OficinaLineTokenizer = new DelimitedLineTokenizer();
        OficinaLineTokenizer.setDelimiter(";");
        OficinaLineTokenizer.setNames(new String[] { "nroOficina", "descOficina", "oplTipoficinaTblTipOficina" , "oplOficinaTblnroOficina" });
        return OficinaLineTokenizer;
    }

    private FieldSetMapper<OficinaParDownEntity> createOficinaInformationMapper() {
        BeanWrapperFieldSetMapper<OficinaParDownEntity> OficinaInformationMapper = new BeanWrapperFieldSetMapper<>();
        OficinaInformationMapper.setTargetType(OficinaParDownEntity.class);
        return OficinaInformationMapper;
    }


    @Bean
    @StepScope
    ItemWriter<OficinaParDownEntity> writerOficina(){
        return new DBWriterOficina();
    }

    /*
    @Bean
    @StepScope
    ItemWriter<OficinaParDownEntity> ItemWriterOficina(DataSource dataSource,
                                                                   NamedParameterJdbcTemplate jdbcTemplate) {
        JdbcBatchItemWriter<OficinaParDownEntity> databaseItemWriter = new JdbcBatchItemWriter<>();
        databaseItemWriter.setDataSource(dataSource);
        databaseItemWriter.setJdbcTemplate(jdbcTemplate);

        databaseItemWriter.setSql(
                "INSERT INTO OPL_PAR_OFICINA_DOWN_TBL(NRO_OFICINA,DESC_OFICINA,OPL_TIPOFICINA_TBL_TIP_OFICINA,OPL_OFICINA_TBL_NRO_OFICINA)"
                        + " VALUES (:nroOficina,:descOficina,:oplTipoficinaTblTipOficina,:oplOficinaTblnroOficina)");
        //System.out.println("sql" + databaseItemWriter.toString());
        ItemSqlParameterSourceProvider<OficinaParDownEntity> sqlParameterSourceProvider = oficinaSqlParameterSourceProvider();
        databaseItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);

        return databaseItemWriter;
    }

    private ItemSqlParameterSourceProvider<OficinaParDownEntity> oficinaSqlParameterSourceProvider() {
        return new BeanPropertyItemSqlParameterSourceProvider<>();
    }*/

    @Bean
    @Qualifier("StepOficina")
    Step StepOficina(ItemReader<OficinaParDownEntity> csvFileItemReaderOficina,
                  //ItemProcessor<CIIUEntity, CIIUEntity> csvFileItemProcessor,
                  ItemWriter<OficinaParDownEntity> writerOficina,
                  StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepOficina")
                .<OficinaParDownEntity, OficinaParDownEntity>chunk(2000)
                .reader(csvFileItemReaderOficina)
                //.processor(csvFileItemProcessor)
                .writer(writerOficina)
                .build();
    }

    @Bean(name = "JobOficina")
    Job JobOficina(JobBuilderFactory jobBuilderFactory,
                @Qualifier("StepOficina") Step csvOficinaStep) {
        return jobBuilderFactory.get("JobOficina")
                .incrementer(new RunIdIncrementer())
                .flow(csvOficinaStep)
                .end()
                .build();
    }

}
