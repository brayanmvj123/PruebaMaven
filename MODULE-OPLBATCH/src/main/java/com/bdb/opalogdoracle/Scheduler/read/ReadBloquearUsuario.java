package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.CrossCreateLockedUserFileTasklet;
import com.bdb.opalogdoracle.Scheduler.load.DBWriterBloquearUsuario;
import com.bdb.opalogdoracle.Scheduler.transform.ProcessorBloquearUsuario;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.model.userdata.SQLBloquearUsuario;
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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@CommonsLog
public class ReadBloquearUsuario {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public SharedService serviceShared;

    SQLBloquearUsuario sqlBloquearUsuario = new SQLBloquearUsuario();

    String sql = sqlBloquearUsuario.sqlBloqueo;

    @Bean
    public ItemReader<HisLoginDownEntity> leerTablaLogin(DataSource datasource) {
        log.info("ENTRO AL READ DE BLOQUEAR USUARIO, SE REALIZARA LA CONSULTA DE LOS USUARIOS EN ESTADO 1 CON ULTIMO DIA DE CONEXION MAYOR A LO" +
                "INDICADO POR LA VARIABLE 'CANT_DIAS_BLOQUEO_USR' QUE SE ENCUENTRA EN LA TABLA 'OPL_PAR_VARENTORNO_DOWN_TBL'");
        JdbcCursorItemReader<HisLoginDownEntity> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(datasource);
        databaseReader.setSql(sql);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(HisLoginDownEntity.class));
        return databaseReader;
    }

    @Bean
    @StepScope
    ItemProcessor<HisLoginDownEntity, HisLoginDownEntity> processorCambioEstado() {
        return new ProcessorBloquearUsuario();
    }

    @Bean
    @StepScope
    ItemWriter<HisLoginDownEntity> writerHisLogin() {
        return new DBWriterBloquearUsuario();
    }

    @Bean
    Step StepBloquearUsuario(ItemReader<HisLoginDownEntity> leerTablaLogin,
                             ItemProcessor<HisLoginDownEntity, HisLoginDownEntity> processorCambioEstado,
                             ItemWriter<HisLoginDownEntity> writerHisLogin,
                             StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepBloquearUsuario")
                .<HisLoginDownEntity, HisLoginDownEntity>chunk(10000)
                .reader(leerTablaLogin)
                .processor(processorCambioEstado)
                .writer(writerHisLogin)
                .build();
    }

    @Bean(name = "JobBloquearUser")
    Job JobBloquearUsuario(JobBuilderFactory jobBuilderFactory,
                           @Qualifier("StepBloquearUsuario") Step StepBloquearUsuario,
                           StepBuilderFactory stepBuilders
    ) {
        return jobBuilderFactory.get("JobBloquearUser")
                .incrementer(new RunIdIncrementer())
                .flow(StepBloquearUsuario)
                .end()
                .build();
    }

    @Bean(name = "JobSendEmail")
    Job JobSendEmailExcel(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilders
    ) {
        return jobBuilderFactory.get("JobSendEmail")
                .incrementer(new RunIdIncrementer())
                .flow(crossCreateUserFile(stepBuilders))
                .end()
                .build();
    }

    @Bean
    Step crossCreateUserFile(StepBuilderFactory stepBuilders) {
        log.info("start crossInfoPatrimonioStep...");
        return stepBuilders.get("crossInfoPatrimonioStep")
                .tasklet(crossCreateLockedUserFileTasklet()).build();
    }

    @Bean
    public CrossCreateLockedUserFileTasklet crossCreateLockedUserFileTasklet() {
        log.info("start crossInfoPatrimonialesTasklet...");
        return new CrossCreateLockedUserFileTasklet();
    }

}
