package com.bdb.oplbacthdiarios.schudeler.read;

import com.bdb.opaloshare.persistence.entity.CtaInvCarEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvHisEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvSecCarEntity;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvWService;
import com.bdb.oplbacthdiarios.schudeler.load.CrossReadCuentasTasklet;
import com.bdb.oplbacthdiarios.schudeler.load.DBWriterCtaInvHis;
import com.bdb.oplbacthdiarios.schudeler.load.DBWriterCtaInvT;
import com.bdb.oplbacthdiarios.schudeler.load.DBWriterCtaInvT2;
import com.bdb.oplbacthdiarios.schudeler.transform.ProcessorAccSec;
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
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;

@Configuration
@EnableBatchProcessing
public class ReadCtaInv {

    @Autowired
    public JobBuilderFactory builderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    CtaInvWService serviceCuentas;

    private Logger logger = LoggerFactory.getLogger(ReadCtaInv.class);

    ByteArrayOutputStream[] leerArchivos = new ByteArrayOutputStream[2];

    /*
     * LO PRIMERO EN UN STEP ES LEER LA INFORMACION , PARA UN STEP SOLO PUEDE EXISIR UN PROCESO DE LECTURA , LA INTERFACE ITEMREADER
     * ES LA ENCARGADA DE REALIZAR ESTE PROCESO.
     * PARA LA EJECUCIÓN DE ESTE JOB EXISTEN VARIOS ITEMREADERS , ES DECIR QUE HAY VARIOS STEPS PARA COMPLETAR EL JOB. Y ESTO SE DEBE
     * PORQUE EL ARCHIVO DE CUENTAS CUENTA CON DIFERENTES LONGITUDES EN LAS ESTRUCTURAS DE LAS LINEAS , POR ESTA RAZON EL ARCHIVO SE
     * DIVIDIO EN DOS , OBTENIENDO LINEAS HOMOGENEAS EN CADA ARCHIVO.
     */

    @Bean
    @StepScope
    FlatFileItemReader<CtaInvCarEntity> FileItemReaderCuenta() {

        logger.info("ENTRO AL PASO 1 ... LEYENDO EL ARCHIVO DE CUENTAS TIPO 1");
        leerArchivos = serviceCuentas.cargarCuentas();

        //System.out.println("ENTRO A LEER el documento de CUENTAS - " + saber[1]);

        FlatFileItemReader<CtaInvCarEntity> FileReaderCuenta = new FlatFileItemReader<>();
        FileReaderCuenta.setResource(new ByteArrayResource(leerArchivos[1].toByteArray()));
        //csvFileReader.setLinesToSkip(1);

        LineMapper<CtaInvCarEntity> cuentaLineMapper = createCuentasLineMapper("archivo_TIPO1.txt");
        FileReaderCuenta.setLineMapper(cuentaLineMapper);

        return FileReaderCuenta;
    }

    @Bean
    @StepScope
    FlatFileItemReader<CtaInvSecCarEntity> FileItemReaderCuentaT2() {

        logger.info("ENTRO AL PASO 2 ... LEYENDO EL ARCHIVO DE CUENTAS TIPO 2");

        FlatFileItemReader<CtaInvSecCarEntity> FileReaderCuentaT2 = new FlatFileItemReader<>();
        FileReaderCuentaT2.setResource(new ByteArrayResource(leerArchivos[0].toByteArray()));
        //csvFileReader.setLinesToSkip(1);

        LineMapper<CtaInvSecCarEntity> cuentaLineMapperT2 = createCuentasTipoIILineMapper();
        FileReaderCuentaT2.setLineMapper(cuentaLineMapperT2);

        return FileReaderCuentaT2;
    }

    @Bean
    @StepScope
    FlatFileItemReader<CtaInvCarEntity> FileItemReaderCuentaTBL() {

        logger.info("ENTRO AL PASO 3 ... LEYENDO EL ARCHIVO DE CUENTASTBL");

        FlatFileItemReader<CtaInvCarEntity> FileReaderCuenta = new FlatFileItemReader<>();
        FileReaderCuenta.setResource(new ByteArrayResource(leerArchivos[1].toByteArray()));
        //csvFileReader.setLinesToSkip(1);

        LineMapper<CtaInvCarEntity> cuentaLineMapper = createCuentasLineMapper("archivo_TBL.txt");
        FileReaderCuenta.setLineMapper(cuentaLineMapper);

        return FileReaderCuenta;
    }

    private LineMapper<CtaInvCarEntity> createCuentasLineMapper(String nombre) {
        DefaultLineMapper<CtaInvCarEntity> cuentaLineMapper = new DefaultLineMapper<>();

        LineTokenizer cuentaLineTokenizer = null;
        if (nombre.indexOf("TIPO1") > 0) {
            cuentaLineTokenizer = createCuentasT1LineTokenizer();
        }/*else if (nombre.indexOf("TIPO2") > 0) {
			cuentaLineTokenizer = createCuentasT2LineTokenizer();
		}*/ else if (nombre.indexOf("TBL") > 0) {
            cuentaLineTokenizer = createCuentasTBLLineTokenizer();
        }

        cuentaLineMapper.setLineTokenizer(cuentaLineTokenizer);

        FieldSetMapper<CtaInvCarEntity> cuentaInformationMapper = createCuentaInformationMapper();
        cuentaLineMapper.setFieldSetMapper(cuentaInformationMapper);

        return cuentaLineMapper;
    }

    private LineMapper<CtaInvSecCarEntity> createCuentasTipoIILineMapper() {
        DefaultLineMapper<CtaInvSecCarEntity> cuentaLineMapper = new DefaultLineMapper<>();

        LineTokenizer cuentaLineTokenizer = createCuentasT2LineTokenizer();

        cuentaLineMapper.setLineTokenizer(cuentaLineTokenizer);

        FieldSetMapper<CtaInvSecCarEntity> cuentaInformationMapper = createCuentaTipoIIInformationMapper();
        cuentaLineMapper.setFieldSetMapper(cuentaInformationMapper);

        return cuentaLineMapper;
    }

    /*
     * EL METODO LineTokenizer() ES EL ENCARGADO DE DIVIDIR EL ARCHIVO ENTRE LOS DIFERENTES ATRIBUTOS DE LA CLASE , PARA ESTE CASO
     * LAS LINEAS SE PARTEN POR RANGOS DE LONGITUDES.
     * LA LONGITUD DEBE SER EXACTA CON LA CANTIDAD DE CARACTERES QUE CONTIENE LAS LINEAS , ES DECIR NO SE PUEDEN UTILIZAR DIVISIONES
     * DE DIFERENTE LONGITUD , DEBEN SER FIJAS.
     */

    private LineTokenizer createCuentasT1LineTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        String[] names = {
                "tipoReg",
                "numCta",
                "nomCta",
                "tipId",
                "idTit",
                "nomTit",
                "indRet",
                "autRet",
                "dir",
                "tel",
                "claTit",
                "codSect",
                "carTit",
                "relCta",
                "clasCta",
                "ctaEmb",
                "estCta",
                "codPais",
                "codDep",
                "codCiud",
                "fechaIni",
                "fechaFin",
                "indExtr",
                "codCree"
        };
        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 2),
                new Range(3, 10),
                new Range(11, 60),
                new Range(61, 63),
                new Range(64, 78),
                new Range(79, 128),
                new Range(129, 129),
                new Range(130, 130),
                new Range(131, 180),
                new Range(181, 200),
                new Range(201, 201),
                new Range(202, 205),
                new Range(206, 206),
                new Range(207, 207),
                new Range(208, 208),
                new Range(209, 209),
                new Range(210, 210),
                new Range(211, 212),
                new Range(213, 214),
                new Range(215, 218),
                new Range(219, 226),
                new Range(227, 234),
                new Range(235, 235),
                new Range(236, 241)
        };
        tokenizer.setColumns(ranges);

        return tokenizer;
    }

    private LineTokenizer createCuentasT2LineTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        String[] names = {
                "tipReg",
                "tipRel",
                "tipId",
                "idTit",
                "nomTit",
                "dir",
                "tel",
                "claTit",
                "codSect",
                "carTit",
                "numCta"
        };
        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 2),
                new Range(3, 3),
                new Range(4, 6),
                new Range(7, 21),
                new Range(22, 71),
                new Range(72, 121),
                new Range(122, 141),
                new Range(142, 142),
                new Range(143, 146),
                new Range(147, 147),
                new Range(148, 155)
        };
        tokenizer.setColumns(ranges);

        return tokenizer;
    }

    private LineTokenizer createCuentasTBLLineTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        String[] names = {
                "tipoReg",
                "numCta",
                "nomCta",
                "tipId",
                "idTit",
                "nomTit",
                "indRet",
                "autRet",
                "dir",
                "tel",
                "claTit",
                "codSect",
                "carTit",
                "relCta",
                "clasCta",
                "ctaEmb",
                "estCta",
                "codPais",
                "codDep",
                "codCiud",
                "fechaIni",
                "fechaFin",
                "indExtr",
                "codCree"
        };
        tokenizer.setNames(names);
        Range[] ranges = {
                new Range(1, 2),
                new Range(3, 10),
                new Range(11, 60),
                new Range(61, 63),
                new Range(64, 78),
                new Range(79, 128),
                new Range(129, 129),
                new Range(130, 130),
                new Range(131, 180),
                new Range(181, 200),
                new Range(201, 201),
                new Range(202, 205),
                new Range(206, 206),
                new Range(207, 207),
                new Range(208, 208),
                new Range(209, 209),
                new Range(210, 210),
                new Range(211, 212),
                new Range(213, 214),
                new Range(215, 218),
                new Range(219, 226),
                new Range(227, 234),
                new Range(235, 235),
                new Range(236, 241)
        };
        tokenizer.setColumns(ranges);

        return tokenizer;
    }

    private FieldSetMapper<CtaInvCarEntity> createCuentaInformationMapper() {
        BeanWrapperFieldSetMapper<CtaInvCarEntity> cuentaInformationMapper = new BeanWrapperFieldSetMapper<>();
        cuentaInformationMapper.setTargetType(CtaInvCarEntity.class);
        return cuentaInformationMapper;
    }

    private FieldSetMapper<CtaInvSecCarEntity> createCuentaTipoIIInformationMapper() {
        BeanWrapperFieldSetMapper<CtaInvSecCarEntity> cuentaInformationMapper = new BeanWrapperFieldSetMapper<>();
        cuentaInformationMapper.setTargetType(CtaInvSecCarEntity.class);
        return cuentaInformationMapper;
    }

    @Bean
    @StepScope
    ItemProcessor<CtaInvSecCarEntity, CtaInvSecCarEntity> processorAccSec(){
        return new ProcessorAccSec();
    }

    @Bean
    @StepScope
    ItemWriter<CtaInvCarEntity> FileDatabaseItemWriterCuentasT1() {
        return new DBWriterCtaInvT();
    }

    @Bean
    @StepScope
    ItemWriter<CtaInvSecCarEntity> FileDatabaseItemWriterCuentasT2() {
        return new DBWriterCtaInvT2();
    }

    @Bean
    @StepScope
    ItemWriter<CtaInvCarEntity> FileDatabaseItemWriterCuentasTBL() {
        return new DBWriterCtaInvHis();
    }

    private ItemSqlParameterSourceProvider<CtaInvHisEntity> cuentaTBLSqlParameterSourceProvider() {
        return new BeanPropertyItemSqlParameterSourceProvider<>();
    }

    @Bean
    Step StepCuentasTipoI(ItemReader<CtaInvCarEntity> FileItemReaderCuenta,
                          ItemWriter<CtaInvCarEntity> FileDatabaseItemWriterCuentasT1, StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepCuentasTipoI")
                .<CtaInvCarEntity, CtaInvCarEntity>chunk(50000)
                .reader(FileItemReaderCuenta)
                .writer(FileDatabaseItemWriterCuentasT1)//.allowStartIfComplete(true)
                .build();
    }

    @Bean
    Step StepCuentasTipoII(ItemReader<CtaInvSecCarEntity> FileItemReaderCuentaT2,
                           ItemProcessor<CtaInvSecCarEntity,CtaInvSecCarEntity> processorAccSec,
                           ItemWriter<CtaInvSecCarEntity> FileDatabaseItemWriterCuentasT2,
                           StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepCuentasTipoII")
                .<CtaInvSecCarEntity, CtaInvSecCarEntity>chunk(50000)
                .reader(FileItemReaderCuentaT2)
                .processor(processorAccSec)
                .writer(FileDatabaseItemWriterCuentasT2)
                .build();
    }

    @Bean
    Step StepCuentasTipoIII(ItemReader<CtaInvCarEntity> FileItemReaderCuentaTBL,
                            ItemWriter<CtaInvCarEntity> FileDatabaseItemWriterCuentasTBL,
                            StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("StepCuentasTipoIII")
                .<CtaInvCarEntity, CtaInvCarEntity>chunk(50000)
                .reader(FileItemReaderCuentaTBL)
                .writer(FileDatabaseItemWriterCuentasTBL)
                .build();
    }

    @Bean
    Step crossReadCuentasStep(StepBuilderFactory stepBuilders) {
        logger.info("start crossReadCuentasStep...");
        return stepBuilders.get("crossReadCuentasStep").tasklet(crossReadCuentasTasklet()).build();
    }

    @Bean
    public CrossReadCuentasTasklet crossReadCuentasTasklet() {
        logger.info("start crossReadCuentasTasklet...");
        return new CrossReadCuentasTasklet();
    }

    @Bean(name = "JobCuentas")
    Job JobCuentas(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilders,
                   @Qualifier("StepCuentasTipoI") Step cuentaStepT1,
                   @Qualifier("StepCuentasTipoII") Step cuentaStepT2,
                   @Qualifier("StepCuentasTipoIII") Step cuentaStepT3) {
        return jobBuilderFactory.get("JobCuentas")
                .incrementer(new RunIdIncrementer()).flow(crossReadCuentasStep(stepBuilders))
                .next(cuentaStepT1).next(cuentaStepT2).next(cuentaStepT3)
                .end()
                .build();
    }
}
