package com.bdb.opalocdeceval.model.scheduler.read;

import java.io.ByteArrayOutputStream;

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

import com.bdb.opalocdeceval.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opalocdeceval.model.scheduler.load.DBWriterCarMaeCDTS;
import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;

@Configuration
@EnableBatchProcessing
public class ReadCarMaeCDTS {

	@Autowired
	public JobBuilderFactory builderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	CarMaeCDTSService serviceCarCDTS;
	
	private static final Logger LOG = LoggerFactory.getLogger(ReadCarMaeCDTS.class); 

	@Bean(name="readerDataDeceval")
	@StepScope
	FlatFileItemReader<MaeCDTSCarEntity> oplFileItemReaderCDT() {
		
		LOG.info("ENTRO AL PASO ... LEYENDO EL ARCHIVO DE PAIS");
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream = serviceCarCDTS.cargarCDT();
				
		FlatFileItemReader<MaeCDTSCarEntity> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ByteArrayResource(outputStream.toByteArray()));
				//new FileSystemResource("C:\\Users\\JRIVE16\\Documents\\Personal\\Proyecto_Acciones\\Archivos\\PARAMETROS\\ACC_TIPPAIS_PAR_TBL.csv"));
		//csvFileReader.setLinesToSkip(3);
		
		LineMapper<MaeCDTSCarEntity> paisLineMapper = createCDTLineMapper();
		csvFileReader.setLineMapper(paisLineMapper);
		
		return csvFileReader;
	}
	
	private LineMapper<MaeCDTSCarEntity> createCDTLineMapper() {
		DefaultLineMapper<MaeCDTSCarEntity> cdtLineMapper = new DefaultLineMapper<>();

		LineTokenizer cdtLineTokenizer = null;
		cdtLineTokenizer = createCDTLineTokenizer();
		cdtLineMapper.setLineTokenizer(cdtLineTokenizer);

		FieldSetMapper<MaeCDTSCarEntity> cdtInformationMapper = createCDTInformationMapper();
		cdtLineMapper.setFieldSetMapper(cdtInformationMapper);

		return cdtLineMapper;
	}

	private LineTokenizer createCDTLineTokenizer() {
		DelimitedLineTokenizer cdtLineTokenizer = new DelimitedLineTokenizer();
		cdtLineTokenizer.setDelimiter(";"); 
		cdtLineTokenizer.setNames(new String[] { "itemDcv","fechaReg","codIsin","numCDT","idTit","tipId","nomTit","ctaInv",
				"plazo","fechaEmi","fechaVen","vlrCDT","tipBase","tipPeriod","tipTasa","spread","tasNom","tasEfe",
				"tipPlazo","posicion","oficina","fechaProPago" });
		return cdtLineTokenizer;
	}
	
	private FieldSetMapper<MaeCDTSCarEntity> createCDTInformationMapper() {
		BeanWrapperFieldSetMapper<MaeCDTSCarEntity> cdtInformationMapper = new BeanWrapperFieldSetMapper<>();
		cdtInformationMapper.setTargetType(MaeCDTSCarEntity.class);
		return cdtInformationMapper;
	}
	
	@Bean
	@StepScope
	ItemWriter<MaeCDTSCarEntity> oplDBWriter(){
		return new DBWriterCarMaeCDTS();
	}
	
	@Bean
	Step StepCDTDeceval(@Qualifier("readerDataDeceval") ItemReader<MaeCDTSCarEntity> oplFileItemReaderCDT,
			ItemWriter<MaeCDTSCarEntity> oplDBWriter, 
			StepBuilderFactory stepBuilderFactory) {
		System.out.println("ENTRO A LEER EL PASO CDT DECEVAL ");
		return stepBuilderFactory.get("StepCDTDeceval")
				.<MaeCDTSCarEntity, MaeCDTSCarEntity>chunk(2000)
				.reader(oplFileItemReaderCDT)
				//.processor(csvFileItemProcessor)
				.writer(oplDBWriter)
				.build();
	}

	@Bean(name = "JobCDTDeceval")
	Job JobCDTDeceval(JobBuilderFactory jobBuilderFactory,
			@Qualifier("StepCDTDeceval") Step oplCDTStep) {
		System.out.println("ENTRO A LEER al job oplCDTS");
		return jobBuilderFactory.get("JobCDTDeceval")
				.incrementer(new RunIdIncrementer())
				.flow(oplCDTStep)
				.end()
				.build();
	}
}
