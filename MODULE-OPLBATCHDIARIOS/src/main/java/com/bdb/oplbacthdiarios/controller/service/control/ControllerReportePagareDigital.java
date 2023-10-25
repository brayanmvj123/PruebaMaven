package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGeneralesService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import com.bdb.opaloshare.util.Constants;
import com.bdb.oplbacthdiarios.controller.service.implement.ReportePagareDigitalServiceImpl;
import com.bdb.oplbacthdiarios.controller.service.interfaces.DerechosPatrimonialesService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Date;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@Api(value = "Modulo Batch Diarios")
public class ControllerReportePagareDigital {

    private final Logger logger = LoggerFactory.getLogger(ControllerReportePagareDigital.class);

    @Autowired
    RepositorySalPgdigitalDown repoSalPgdigital;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobDerechosPatrimoniales")
    Job job;

    @Autowired
    @Qualifier(value = "JobCrucePatrimoniales")
    Job jobCruce;

    @Autowired
    DerechosPatrimonialesService derPatriService;

    @Autowired
    ReportePagareDigitalServiceImpl pgdigital;

    @Autowired
    private MetodosGeneralesService metodoGeneralesService;

    @GetMapping("carga/reportePagareDigital")
    @ApiOperation(value = "Reporte pagare digital", notes = "Toma la información de la tabla de salida y genera el " +
            "reporte Excel dejándolo en el FTP")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<RequestResult<String>> reportePagareDigital(HttpServletRequest request) throws Exception {

        RequestResult<String> result;

        try {
            pgdigital.loadDatatableToXlsxRequest();

            if (!pgdigital.excelGenerado) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hubo un error al generar excel");
            } else {
                result = new RequestResult<>(request, HttpStatus.OK);
                result.setResult("Excel generado en la ruta correspondiente");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseEntity.ok(result);

    }

    @GetMapping("carga/derechosPatrimoniales")
    @ApiOperation(value = "Derechos patrimoniales", notes = "Carga del archivo hasta generación del reporte")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<RequestResult<String>> derechosPatrimoniales(HttpServletRequest urlRequest) throws Exception {

        logger.info("inicio job carga derechos patrimoniales...");
        /*logger.info("Se inicia el consumo del archivo carga Derechos Patrimoniales Depositante...");
        return derPatriService.cargaArchivoDepo(urlRequest);*/
        JobExecution jobExecution = null;
        RequestResult<String> result;
        result = new RequestResult<>(urlRequest, HttpStatus.OK);

        try {

            logger.info("eliminarPatrimonialesCarga...");
            derPatriService.eliminarPatrimonialesCarga();
            logger.info("eliminarPatrimonialesCarga FIN...");
            boolean resultadoConversion = convertir();
            logger.info("resultadoConversion... {}", resultadoConversion);
            if (resultadoConversion) {
                JobParameters parameters = new JobParametersBuilder()
                        .addDate("date", new Date())
                        .addLong("time", System.currentTimeMillis()).toJobParameters();

                logger.info("joblauncher Run...");
                jobExecution = jobLauncher.run(job, parameters);

                logger.info("JobExecution: {}", jobExecution.getStatus());

                while (jobExecution.isRunning()) {
                    logger.info("...");
                }
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        if (jobExecution != null) {
            if (jobExecution.getStatus().isUnsuccessful()) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error batch: " + jobExecution.getStatus());
            }

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

                logger.info("pgdigital.loadDatatableToXlsxRequest()...");
                pgdigital.loadDatatableToXlsxRequest();

                if (!pgdigital.excelGenerado) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Archivo cargado e informacion " +
                            "de pagos cruzada, pero archivo excel no generado: " + jobExecution.getStatus());
                } else {
                    result.setResult("Archivo cargado e informacion de pagos cruzada.Excel generado en la ruta correspondiente");
                }
            }

            return ResponseEntity.ok(result);

        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error batch jobExecution null");

        }
    }

    @GetMapping("carga/crucePatrimoniales")
    @ApiOperation(value = "Cruce patrimoniales", notes = "Cruza información de la carga y la MAE, dejando el " +
            "resultado en la tabla de salida")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<RequestResult<String>> crucePatrimoniales(HttpServletRequest request) throws Exception {

        logger.info("inicio job cruce derechos patrimoniales...");
        JobExecution jobExecution = null;
        RequestResult<String> result;
        result = new RequestResult<>(request, HttpStatus.OK);

        try {

            JobParameters parameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .addLong("time", System.currentTimeMillis()).toJobParameters();

            logger.info("joblauncher Run...");
            jobExecution = jobLauncher.run(jobCruce, parameters);

            System.out.println("JobExecution: " + jobExecution.getStatus());
            logger.info("JobExecution: " + jobExecution.getStatus());

            System.out.println("JobPatrimoniales Job is Running...");
            while (jobExecution.isRunning()) {
                System.out.println("...");
            }


        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        if (jobExecution.getStatus().isUnsuccessful()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error batch: " + jobExecution.getStatus());
        }

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

            result.setResult("crce generado correctamente");
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("carga/depatridep")
    @ApiOperation(value = "Derechos patrimoniales", notes = "Carga del archivo Derechos patrimoniales Depositante.")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> cargaDepatriDep(HttpServletRequest urlRequest) throws Exception {
        logger.info("Se inicia el consumo del archivo carga Derechos Patrimoniales Depositante...");
        return derPatriService.cargaArchivoDepo(urlRequest);
    }


    public Boolean convertir() {
        logger.info("Inicio Convertir()...");
        String rutaArchivoProcesado;
        String inicioArchivo = metodoGeneralesService.nombreArchivo(Constants.FILEINPATRI);
        boolean convertido = false;

        try {
            logger.info("Inicio Convertir() try catch...");
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();

            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
            String fechaActual = serviceFTP.obtenerFechaActual();
            String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%INPUT%", fechaActual);
            rutaArchivoProcesado = serviceFTP.rutaEspecifica("%CONFIGURATION%", fechaActual);
            String leerArchivo = serviceFTP.listFile(inicioArchivo);

            if (!leerArchivo.isEmpty()) {
                logger.info("Inicio Convertir() try catch --> !leerArchivo.isEmpty() ...");
                logger.info("EL ARCHIVO A LEER ES: {}", leerArchivo);

                String nombreArchivoProcesado = "Procesado_" + leerArchivo;
                ByteArrayOutputStream resource = serviceFTP.archivoResource(rutaArchivoOriginal + leerArchivo);
                ByteArrayOutputStream nuevoResource = derPatriService.eliminarRegistroControl(resource);

                serviceFTP.makeFile(nuevoResource, rutaArchivoProcesado, nombreArchivoProcesado);

                derPatriService.guardar(nuevoResource);
                convertido = true;
            }
            serviceFTP.disconnectFTP();
        } catch (ErrorFtps ftpErrors) {
            logger.error(ftpErrors.getMessage());
            return convertido;
        }

        return convertido;
    }

}
