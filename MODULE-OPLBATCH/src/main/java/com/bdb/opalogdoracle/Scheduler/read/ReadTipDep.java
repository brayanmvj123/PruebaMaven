package com.bdb.opalogdoracle.Scheduler.read;

import com.bdb.opalogdoracle.Scheduler.load.DBWriterTipDep;
import com.bdb.opalogdoracle.Scheduler.transform.TransformTipDep;
import com.bdb.opalogdoracle.controller.service.interfaces.TipDepWService;
import com.bdb.opaloshare.persistence.entity.TipDeparEntity;
import com.bdb.opaloshare.persistence.model.component.ModelTipDepPar;
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
public class ReadTipDep {

	@Autowired
	public JobBuilderFactory builderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	TipDepWService serviceDepartamento;
	
	private static final Logger LOG = LoggerFactory.getLogger(ReadTipDep.class);
	
	@Bean
	@StepScope
	FlatFileItemReader<ModelTipDepPar> csvFileItemReaderDepartamento() {
		
		LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE DEPARTAMENTO");
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = serviceDepartamento.cargarDepartamento();
				
		FlatFileItemReader<ModelTipDepPar> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray())); 
				//FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPDEP_PAR_TBL.csv"));
		csvFileReader.setLinesToSkip(1);
		
		LineMapper<ModelTipDepPar> DepartamentoLineMapper = createDepartamentoLineMapper();
		csvFileReader.setLineMapper(DepartamentoLineMapper);
		
		return csvFileReader;
	}
	
	private LineMapper<ModelTipDepPar> createDepartamentoLineMapper() {
		DefaultLineMapper<ModelTipDepPar> DepartamentoLineMapper = new DefaultLineMapper<>();

		LineTokenizer DepartamentoLineTokenizer = null;
		DepartamentoLineTokenizer = createDepartamentoLineTokenizer();
		DepartamentoLineMapper.setLineTokenizer(DepartamentoLineTokenizer);

		FieldSetMapper<ModelTipDepPar> DepartamentoInformationMapper = createDepartamentoInformationMapper();
		DepartamentoLineMapper.setFieldSetMapper(DepartamentoInformationMapper);

		return DepartamentoLineMapper;
	}

	private LineTokenizer createDepartamentoLineTokenizer() {
		DelimitedLineTokenizer DepartamentoLineTokenizer = new DelimitedLineTokenizer();
		DepartamentoLineTokenizer.setDelimiter(";"); 
		DepartamentoLineTokenizer.setNames(new String[] { "llave", "codPais" , "codDep" , "desDep"});
		return DepartamentoLineTokenizer;
	}
	
	private FieldSetMapper<ModelTipDepPar> createDepartamentoInformationMapper() {
		BeanWrapperFieldSetMapper<ModelTipDepPar> DepartamentoInformationMapper = new BeanWrapperFieldSetMapper<>();
		DepartamentoInformationMapper.setTargetType(ModelTipDepPar.class);
		return DepartamentoInformationMapper;
	}
	
	@Bean
	@StepScope
	ItemProcessor<ModelTipDepPar, TipDeparEntity> processorDepartamento(){
		return new TransformTipDep();
	}
	
	@Bean
	@StepScope
	ItemWriter<TipDeparEntity> writerDepartamento(){
		return new DBWriterTipDep();
	}
	
	@Bean
	Step StepDepartamento(ItemReader<ModelTipDepPar> csvFileItemReaderDepartamento,
						  ItemProcessor<ModelTipDepPar, TipDeparEntity> processorDepartamento,
						  ItemWriter<TipDeparEntity> writerDepartamento,
						  StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("StepDepartamento")
				.<ModelTipDepPar, TipDeparEntity>chunk(2000)
				.reader(csvFileItemReaderDepartamento())
				.processor(processorDepartamento)
				.writer(writerDepartamento)
				.build();
	}

	@Bean(name = "JobDepartamento")
	Job JobDepartamento(JobBuilderFactory jobBuilderFactory,
			@Qualifier("StepDepartamento") Step csvDepartamentoStep) {
		return jobBuilderFactory.get("JobDepartamento")
				.incrementer(new RunIdIncrementer())
				.flow(csvDepartamentoStep)
				.end()
				.build();
	}
}
