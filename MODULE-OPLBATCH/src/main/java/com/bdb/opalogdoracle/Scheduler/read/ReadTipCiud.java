package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterTipCiud;
import com.bdb.opalogdoracle.Scheduler.transform.TransformTipCiud;
import com.bdb.opalogdoracle.controller.service.interfaces.TipCiudWService;
import com.bdb.opaloshare.persistence.entity.TipCiudEntity;
import com.bdb.opaloshare.persistence.model.component.ModelTipCiudPar;
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
public class ReadTipCiud {

	@Autowired
	public JobBuilderFactory builderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	TipCiudWService serviceCiudad;
	
	private static final Logger LOG = LoggerFactory.getLogger(ReadTipCiud.class);
	
	@Bean
	@StepScope
	FlatFileItemReader<ModelTipCiudPar> csvFileItemReaderCiudad() {
		
		LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE CIUDAD");
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = serviceCiudad.cargarCiudad();
				
		FlatFileItemReader<ModelTipCiudPar> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray())); 
				//FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPCIUD_PAR_TBL.csv"));
		csvFileReader.setLinesToSkip(1);
		
		LineMapper<ModelTipCiudPar> CiudadLineMapper = createCiudadLineMapper();
		csvFileReader.setLineMapper(CiudadLineMapper);
		
		return csvFileReader;
	}
	
	private LineMapper<ModelTipCiudPar> createCiudadLineMapper() {
		DefaultLineMapper<ModelTipCiudPar> CiudadLineMapper = new DefaultLineMapper<>();

		LineTokenizer CiudadLineTokenizer;
		CiudadLineTokenizer = createCiudadLineTokenizer();
		CiudadLineMapper.setLineTokenizer(CiudadLineTokenizer);

		FieldSetMapper<ModelTipCiudPar> CiudadInformationMapper = createCiudadInformationMapper();
		CiudadLineMapper.setFieldSetMapper(CiudadInformationMapper);

		return CiudadLineMapper;
	}

	private LineTokenizer createCiudadLineTokenizer() {
		DelimitedLineTokenizer CiudadLineTokenizer = new DelimitedLineTokenizer();
		CiudadLineTokenizer.setDelimiter(";"); 
		CiudadLineTokenizer.setNames(new String[] { "llave", "codDep" , "desDep" , "codCiud" , "desCiud" });
		return CiudadLineTokenizer;
	}
	
	private FieldSetMapper<ModelTipCiudPar> createCiudadInformationMapper() {
		BeanWrapperFieldSetMapper<ModelTipCiudPar> CiudadInformationMapper = new BeanWrapperFieldSetMapper<>();
		CiudadInformationMapper.setTargetType(ModelTipCiudPar.class);
		return CiudadInformationMapper;
	}
	
	@Bean
	@StepScope
	ItemProcessor<ModelTipCiudPar, TipCiudEntity> processorCiudad(){
		return new TransformTipCiud();
	}
	
	@Bean
	@StepScope
	ItemWriter<TipCiudEntity> writerCiudad(){
		return new DBWriterTipCiud();
	}
	
	@Bean
	Step StepCiudad(ItemReader<ModelTipCiudPar> csvFileItemReaderCiudad,
					ItemProcessor<ModelTipCiudPar, TipCiudEntity> processorCiudad,
					ItemWriter<TipCiudEntity> writerCiudad,
					StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("StepCiudad")
				.<ModelTipCiudPar, TipCiudEntity>chunk(2000)
				.reader(csvFileItemReaderCiudad)
				.processor(processorCiudad)
				.writer(writerCiudad)
				.build();
	}

	@Bean(name = "JobCiudad")
	Job JobCiudad(JobBuilderFactory jobBuilderFactory,
			@Qualifier("StepCiudad") Step csvCiudadStep) {
		return jobBuilderFactory.get("JobCiudad")
				.incrementer(new RunIdIncrementer())
				.flow(csvCiudadStep)
				.end()
				.build();
	}
}
