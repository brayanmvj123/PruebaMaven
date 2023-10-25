package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.WriterFileCut;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.SalCutEntity;
import com.bdb.opaloshare.persistence.model.userdata.SQLArchivoCut;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class ReadFileCodCut {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public SharedService serviceShared;

    SQLArchivoCut sqlFileCUT = new SQLArchivoCut();

    public String sql = sqlFileCUT.sql;

    //private Resource outputResource = new FileSystemResource("./TC.OPL");

    @Bean
    public ItemReader<SalCutEntity> leerTablaCut(DataSource datasource){
        JdbcCursorItemReader<SalCutEntity> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(datasource);
        databaseReader.setSql(sql);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(SalCutEntity.class));
        return databaseReader;
    }

    @Bean
    @StepScope
    ItemWriter<SalCutEntity> writerFileCut(){
        return new WriterFileCut();
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
    Step StepArchivoCut(ItemReader<SalCutEntity> leerTablaCut,
                              ItemWriter<SalCutEntity> writerFileCut,
                              StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepArchivoCut")
                .<SalCutEntity, SalCutEntity>chunk(10000)
                .reader(leerTablaCut)
                .writer(writerFileCut)
                .build();
    }

    @Bean(name = "JobArchivoCut")
    Job JobArchivoCut(JobBuilderFactory jobBuilderFactory,
                  @Qualifier("StepArchivoCut") Step StepArchivoCut
    ) {
        return jobBuilderFactory.get("JobArchivoCut")
                .incrementer(new RunIdIncrementer())
                .flow(StepArchivoCut)
                .end()
                .build();
    }

}
