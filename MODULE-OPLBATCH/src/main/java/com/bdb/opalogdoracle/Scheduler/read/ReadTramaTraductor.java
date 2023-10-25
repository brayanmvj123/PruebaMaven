package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.WriterFileTraductor;
import com.bdb.opalogdoracle.Scheduler.load.WriterFileTraductorContigencia;
import com.bdb.opalogdoracle.Scheduler.load.WriterFileTraductorDifDeceapdi;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import com.bdb.opaloshare.persistence.model.userdata.SQLArchivoTraductor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class ReadTramaTraductor {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public SharedService serviceShared;

    SQLArchivoTraductor sqlFileTraductor = new SQLArchivoTraductor();

    public String sql = sqlFileTraductor.sql;
    public String sqlContigencia = sqlFileTraductor.sqlContigencia;
    public String sqlContiTramasDiferDeceapdi = sqlFileTraductor.sqlContiTramasDiferDeceapdi;
    //serviceShared.infoCarpeta("%OUTPUT%").getRuta()+
    private Resource outputResource = new FileSystemResource("./TC.OPL");

    @Bean
    public ItemReader<SalTramastcDownEntity> leerTabla(DataSource datasource){
        JdbcCursorItemReader<SalTramastcDownEntity> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(datasource);
        databaseReader.setSql(sql);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(SalTramastcDownEntity.class));
        return databaseReader;
    }

    @Bean
    public ItemReader<SalTramastcDownEntity> leerTablaContigencia(DataSource datasource){
        JdbcCursorItemReader<SalTramastcDownEntity> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(datasource);
        databaseReader.setSql(sqlContigencia);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(SalTramastcDownEntity.class));
        return databaseReader;
    }

    @Bean
    public ItemReader<SalTramastcDownEntity> leerTablaTramasDiferDeceapdi(DataSource datasource){
        JdbcCursorItemReader<SalTramastcDownEntity> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(datasource);
        databaseReader.setSql(sqlContiTramasDiferDeceapdi);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(SalTramastcDownEntity.class));
        return databaseReader;
    }

    @Bean
    @StepScope
    ItemWriter<SalTramastcDownEntity> writerFile(){
        return new WriterFileTraductor();
    }

    @Bean
    @StepScope
    ItemWriter<SalTramastcDownEntity> writerFileContigencia(){
        return new WriterFileTraductorContigencia();
    }

    @Bean
    @StepScope
    ItemWriter<SalTramastcDownEntity> writerFileTramasDiferDeceapdi(){
        return new WriterFileTraductorDifDeceapdi();
    }
    /*@Bean
    @StepScope
    public FlatFileItemWriter<SalTramastcDownEntity> writerFile() throws ErrorFtps {
        //Create writer instance
        FlatFileItemWriter<SalTramastcDownEntity> writer = new FlatFileItemWriter<>();

        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());

        //Set output file location
        writer.setResource(outputResource);

        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);

        //Name field values sequence based on object properties
        writer.setLineAggregator(new DelimitedLineAggregator<SalTramastcDownEntity>() {
            {
                setDelimiter(";");
                setFieldExtractor(new BeanWrapperFieldExtractor<SalTramastcDownEntity>() {
                    {
                        setNames(new String[] { "item", "fecha", "trama" });
                    }
                });
            }
        });

       // InputStream prueba = new FileInputStream();

        System.out.println(writer.getExecutionContextKey("item"));
        return writer;
    }*/

    @Bean
    Step StepArchivoTraductor(ItemReader<SalTramastcDownEntity> leerTabla,
                        ItemWriter<SalTramastcDownEntity> writerFile,
                        StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepArchivoTraductor")
                .<SalTramastcDownEntity, SalTramastcDownEntity>chunk(10000)
                .reader(leerTabla)
                .writer(writerFile)
                .build();
    }

    @Bean
    Step StepArchivoTraductorContigencia(ItemReader<SalTramastcDownEntity> leerTablaContigencia,
                              ItemWriter<SalTramastcDownEntity> writerFileContigencia,
                              StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepArchivoTraductorContigencia")
                .<SalTramastcDownEntity, SalTramastcDownEntity>chunk(10000)
                .reader(leerTablaContigencia)
                .writer(writerFileContigencia)
                .build();
    }

    @Bean
    Step StepArchivoTramasDiferDeceapdi(ItemReader<SalTramastcDownEntity> leerTablaTramasDiferDeceapdi,
                                         ItemWriter<SalTramastcDownEntity> writerFileTramasDiferDeceapdi,
                                         StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepArchivoTramasDiferDeceapdi")
                .<SalTramastcDownEntity, SalTramastcDownEntity>chunk(10000)
                .reader(leerTablaTramasDiferDeceapdi)
                .writer(writerFileTramasDiferDeceapdi)
                .build();
    }

    @Bean(name = "JobArchivoTraductor")
    Job JobPrueba(JobBuilderFactory jobBuilderFactory,
                  @Qualifier("StepArchivoTraductor") Step StepArchivoTraductor
    ) {
        return jobBuilderFactory.get("JobArchivoTraductor")
                .incrementer(new RunIdIncrementer())
                .flow(StepArchivoTraductor)
                .end()
                .build();
    }

    @Bean(name = "JobArchivoTraductorContigencia")
    Job JobTraductorContigencia(JobBuilderFactory jobBuilderFactory,
                  @Qualifier("StepArchivoTraductorContigencia") Step StepArchivoTraductorContigencia
    ) {
        return jobBuilderFactory.get("JobArchivoTraductorContigencia")
                .incrementer(new RunIdIncrementer())
                .flow(StepArchivoTraductorContigencia)
                .end()
                .build();
    }

    @Bean(name = "JobArchivoTramasDiferDeceapdi")
    Job JobTraductorDiferDeceapdi(JobBuilderFactory jobBuilderFactory,
                                @Qualifier("StepArchivoTramasDiferDeceapdi") Step StepArchivoTramasDiferDeceapdi
    ) {
        return jobBuilderFactory.get("JobArchivoTramasDiferDeceapdi")
                .incrementer(new RunIdIncrementer())
                .flow(StepArchivoTramasDiferDeceapdi)
                .end()
                .build();
    }

}
