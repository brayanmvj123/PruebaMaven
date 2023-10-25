package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriter;
import com.bdb.opalogdoracle.Scheduler.load.DBWriterUpdateCliente;
import com.bdb.opalogdoracle.Scheduler.load.ProccesorArchivoP;
import com.bdb.opalogdoracle.Scheduler.load.ProcessorSQL;
import com.bdb.opalogdoracle.persistence.SalPdcvlSecundarioModel;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
import com.bdb.opaloshare.persistence.entity.SalPdcvlModel;
import com.bdb.opaloshare.persistence.model.userdata.SQLArchivoP;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class ReadPrueba {

	@Autowired
	public JobBuilderFactory builderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	SQLArchivoP sqlFileP = new SQLArchivoP();
	
	String sql = sqlFileP.sql;
	
	String sqlSecundario = sqlFileP.sqlSecundario;
	
	@Bean
	public ItemReader<SalPdcvlModel> leerTanque(DataSource datasource){
		JdbcCursorItemReader<SalPdcvlModel> databaseReader = new JdbcCursorItemReader<>();
		databaseReader.setDataSource(datasource);
		databaseReader.setSql(sql);
		databaseReader.setRowMapper(new BeanPropertyRowMapper<>(SalPdcvlModel.class));
		return databaseReader;
	}
	
	@Bean
	public ItemReader<SalPdcvlSecundarioModel> leerTanqueSecundarios(DataSource datasource){
		JdbcCursorItemReader<SalPdcvlSecundarioModel> databaseReader = new JdbcCursorItemReader<>();
		databaseReader.setDataSource(datasource);
		databaseReader.setSql(sqlSecundario);
		databaseReader.setRowMapper(new BeanPropertyRowMapper<>(SalPdcvlSecundarioModel.class));
		return databaseReader;
	}
	
	@Bean
	ItemProcessor<SalPdcvlSecundarioModel, SalPdcvlEntity> processorPropietarios(){
		return new ProcessorSQL();
	}
	
	@Bean
	ItemProcessor<SalPdcvlModel, SalPdcvlEntity> processorArchivoP(){
		return new ProccesorArchivoP();
	}
	
	@Bean
	ItemWriter<SalPdcvlEntity> escribir(){
		return new DBWriter();
	}
	
	@Bean
	ItemWriter<SalPdcvlEntity> actualizarClientes(){
		return new DBWriterUpdateCliente();
	}

	/*
	@Bean
	public ItemWriter<SalPdcvlEntity> writeArchivoP(DataSource dataSource,
			NamedParameterJdbcTemplate jdbcTemplate) {
		JdbcBatchItemWriter<SalPdcvlEntity> databaseItemWriter = new JdbcBatchItemWriter<>();
		databaseItemWriter.setDataSource(dataSource);
		databaseItemWriter.setJdbcTemplate(jdbcTemplate);

		databaseItemWriter.setSql(
				"INSERT INTO OPL_SAL_PDCVL_DOWN_TBL(ofioritx,codinttx,ctacdtbb,tipocdt,ofduena,nmbtit1,tipdoc1,"
				+ "nrodoc1,plazoti,kapital,fchvenc,CTAAABO,deposit,RESPCDTS,fechape,TIPPROD,clasper,acteco,TIPRELA,"
				+ "dirresi,teleres,ext_ofi,tiprete,tasnomi,tasefec,tiptasa,spread,fchaper,baseliq,periodi,tipplaz)"
				+ " VALUES (:OPL_OFICINA_TBL_NRO_OFICINA,001,:num_Cdt,2,:OPL_OFICINA_TBL_NRO_OFICINA,:nom_tit,:OPL_TIPID_TBL_COD_ID,"
				+ ":NUM_TIT,:plazo,:valor,:FECHA_VEN,00000000000000,:OPL_DEPOSITANTE_TBL_TIP_DEPOSITANTE,000,:fecha,:COD_PROD,:CLASE_PER,:OPL_TIPCIIU_TBL_COD_CIIU,:TIP_TITULARIDAD,"
				+ ":DIR_TIT,:TEL_TIT,:EXTENSION,:RETENCION,:TASA_NOM,:TASA_EFE,:OPL_TIPTASA_TBL_TIP_TASA,:SPREAD,:fecha,:OPL_TIPBASE_TBL_TIP_BASE,"
				+ ":OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD,:OPL_TIPPLAZO_TBL_TIP_PLAZO)");
		//System.out.println("sql" + databaseItemWriter.toString());
		ItemSqlParameterSourceProvider<SalPdcvlEntity> sqlParameterSourceProvider = archivoPParameterSourceProvider();
		databaseItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);

		return databaseItemWriter;
	}
	
	@Bean
	public ItemWriter<SalPdcvlEntity> writeArchivoPSecundario(DataSource dataSource,
			NamedParameterJdbcTemplate jdbcTemplate) {
		JdbcBatchItemWriter<SalPdcvlEntity> databaseItemWriter = new JdbcBatchItemWriter<>();
		databaseItemWriter.setDataSource(dataSource);
		databaseItemWriter.setJdbcTemplate(jdbcTemplate);

		databaseItemWriter.setSql("UPDATE OPL_SAL_PDCVL_DOWN_TBL SET "
				+ "nmbtit2 = :nmbtit2 , nmbtit3 = :nmbtit3 , nmbtit4 = :nmbtit4, "
				+ "tipdoc2 = :tipdoc2 , tipdoc3 = :tipdoc3 , tipdoc4 = :tipdoc4, "
				+ "nrodoc2 = :nrodoc2 , nrodoc3 = :nrodoc3 , nrodoc4 = :nrodoc4 "
				+ "WHERE CTACDTBB = :ctacdtbb");
		//System.out.println("sql" + databaseItemWriter.toString());
		ItemSqlParameterSourceProvider<SalPdcvlEntity> sqlParameterSourceProvider = archivoPParameterSourceProvider();
		databaseItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);

		return databaseItemWriter;
	}*/
	
	private ItemSqlParameterSourceProvider<SalPdcvlEntity> archivoPParameterSourceProvider() {
		return new BeanPropertyItemSqlParameterSourceProvider<>();
	}
	
	@Bean
	Step StepSendCDTS(ItemReader<SalPdcvlModel> leerTanque,
			ItemProcessor<SalPdcvlModel, SalPdcvlEntity> processorArchivoP,
			ItemWriter<SalPdcvlEntity> escribir,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("StepSendCDTS")
				.<SalPdcvlModel, SalPdcvlEntity>chunk(1000)
				.reader(leerTanque)
				.processor(processorArchivoP)
				.writer(escribir)
				.build();
	}
		
	@Bean
	Step StepUpdateCDTS(ItemReader<SalPdcvlSecundarioModel> leerTanqueSecundarios,
			ItemProcessor<SalPdcvlSecundarioModel, SalPdcvlEntity> processorUno,
			ItemWriter<SalPdcvlEntity> actualizarClientes,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("StepUpdateCDTS")
				.<SalPdcvlSecundarioModel, SalPdcvlEntity>chunk(1000)
				.reader(leerTanqueSecundarios)
				.processor(processorUno)
				.writer(actualizarClientes)
				.build();
	}

	@Bean(name = "JobSalPdcvlCDT")
	Job JobSendSQLServer(JobBuilderFactory jobBuilderFactory,
						 @Qualifier("StepSendCDTS") Step StepSendCDTS,
						 @Qualifier("StepUpdateCDTS") Step StepUpdateCDTS
			) {
		return jobBuilderFactory.get("JobSendSQLServer")
				.incrementer(new RunIdIncrementer())
				.flow(StepSendCDTS).next(StepUpdateCDTS)
				.end()
				.build();
	}
}
