package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterTipPais;
import com.bdb.opalogdoracle.controller.service.interfaces.TipPaisWService;
import com.bdb.opaloshare.persistence.entity.TipPaisEntity;
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
public class ReadTipPais {
	
	@Autowired
	public JobBuilderFactory builderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	TipPaisWService serviceTipPais;
	
	private static final Logger LOG = LoggerFactory.getLogger(ReadTipPais.class); 

	@Bean(name="reader_pais")
	@StepScope
	FlatFileItemReader<TipPaisEntity> csvFileItemReaderPais() {
		
		LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE PAIS");
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream = serviceTipPais.cargarPais();
				
		FlatFileItemReader<TipPaisEntity> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
				//new FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPPAIS_PAR_TBL.csv"));
		csvFileReader.setLinesToSkip(1);
		
		LineMapper<TipPaisEntity> paisLineMapper = createPaisLineMapper();
		csvFileReader.setLineMapper(paisLineMapper);
		
		return csvFileReader;
	}
	
	private LineMapper<TipPaisEntity> createPaisLineMapper() {
		DefaultLineMapper<TipPaisEntity> paisLineMapper = new DefaultLineMapper<>();

		LineTokenizer paisLineTokenizer = null;
		paisLineTokenizer = createPaisLineTokenizer();
		paisLineMapper.setLineTokenizer(paisLineTokenizer);

		FieldSetMapper<TipPaisEntity> paisInformationMapper = createPaisInformationMapper();
		paisLineMapper.setFieldSetMapper(paisInformationMapper);

		return paisLineMapper;
	}

	private LineTokenizer createPaisLineTokenizer() {
		DelimitedLineTokenizer paisLineTokenizer = new DelimitedLineTokenizer();
		paisLineTokenizer.setDelimiter(";"); 
		paisLineTokenizer.setNames(new String[] { "codPais" , "homoCrm" , "desPais" });
		return paisLineTokenizer;
	}
	
	private FieldSetMapper<TipPaisEntity> createPaisInformationMapper() {
		BeanWrapperFieldSetMapper<TipPaisEntity> paisInformationMapper = new BeanWrapperFieldSetMapper<>();
		paisInformationMapper.setTargetType(TipPaisEntity.class);
		return paisInformationMapper;
	}
	
	@Bean
	@StepScope
	ItemWriter<TipPaisEntity> excelDBWriter(){
		return new DBWriterTipPais();
	}
	
	@Bean
	Step StepPais(@Qualifier("reader_pais") ItemReader<TipPaisEntity> excelReaderPais,
			ItemWriter<TipPaisEntity> excelDBWriter, 
			StepBuilderFactory stepBuilderFactory) {
		System.out.println("ENTRO A LEER EL PASO PAIS ");
		return stepBuilderFactory.get("StepPais")
				.<TipPaisEntity, TipPaisEntity>chunk(2000)
				.reader(excelReaderPais)
				//.processor(csvFileItemProcessor)
				.writer(excelDBWriter)
				.build();
	}

	@Bean(name = "JobPais")
	//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	Job JobPais(JobBuilderFactory jobBuilderFactory,
			@Qualifier("StepPais") Step excelPaisStep) {
		System.out.println("ENTRO A LEER al job PAIS");
		return jobBuilderFactory.get("JobPais")
				.incrementer(new RunIdIncrementer())
				.flow(excelPaisStep)
				.end()
				.build();
	}

}
