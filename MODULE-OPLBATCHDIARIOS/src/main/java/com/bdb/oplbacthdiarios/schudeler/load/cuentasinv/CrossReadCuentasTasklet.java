package com.bdb.oplbacthdiarios.schudeler.load.cuentasinv;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGeneralesService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvCar;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvSecCar;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvWService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.ByteArrayOutputStream;

public class CrossReadCuentasTasklet implements Tasklet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RepositoryCtaInvCar repoCuentas;

    @Autowired
    RepositoryCtaInvSecCar repoCtaInvSec;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    @Qualifier("serviceCuentas")
    CtaInvWService cuentaService;

    @Autowired
    MetodosGeneralesService metodoGeneralesService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

       repoCuentas.deleteAllInBatch();
       repoCtaInvSec.deleteAllInBatch();

        boolean valorConv = convertir();

        if (!valorConv) {
            logger.info("El archivo de cuentas no esta cargado en el FTP");
            chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);
        }
        logger.info("Termino CrossReadCuentasTasklet");

        return RepeatStatus.FINISHED;
    }

    public boolean convertir() {
        String inicioArchivo = metodoGeneralesService.nombreArchivo("FILE_CUENTAS");
        boolean saber = false;
        try {
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
            String fechaActual = serviceFTP.obtenerFechaActual();
            String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%INPUT%", fechaActual);
            String rutaArchivoProcesado = serviceFTP.rutaEspecifica("%CONFIGURATION%", fechaActual);
            String reporteLongErrada = serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual);

            String leerArchivo = serviceFTP.listFile(inicioArchivo);
            if (!leerArchivo.isEmpty()) {
                logger.info("EL ARCHIVO A LEER ES: " + leerArchivo);
                String[] nombreArchivoProcesado = new String[3];
                nombreArchivoProcesado[0] = "ProcesadoTIPO1_IPE155R_" + leerArchivo;
                nombreArchivoProcesado[1] = "ProcesadoTIPO2_IPE155R_" + leerArchivo;
                nombreArchivoProcesado[2] = metodoGeneralesService.nombreArchivo("OUT_FILE_CTAINV_RECH");
                ByteArrayOutputStream resource = serviceFTP.archivoResource(rutaArchivoOriginal + leerArchivo);
                ByteArrayOutputStream[] nuevoResource = cuentaService.ExaminarArchivo(resource);
                cuentaService.guardarCuentas(nuevoResource);

                if (nuevoResource[0].size() != 0) {
                    serviceFTP.makeFile(nuevoResource[0], rutaArchivoProcesado, nombreArchivoProcesado[1]);
                }

                if (nuevoResource[1].size() != 0) {
                    serviceFTP.makeFile(nuevoResource[1], rutaArchivoProcesado, nombreArchivoProcesado[0]);
                }

                if (nuevoResource[2].size() != 0) {
                    serviceFTP.makeFile(nuevoResource[2], reporteLongErrada, nombreArchivoProcesado[2]);
                }
                saber = true;
            }
            serviceFTP.disconnectFTP();
        } catch (ErrorFtps ftpErrors) {
            logger.error(ftpErrors.getMessage());
        }
        return saber;
    }
}
