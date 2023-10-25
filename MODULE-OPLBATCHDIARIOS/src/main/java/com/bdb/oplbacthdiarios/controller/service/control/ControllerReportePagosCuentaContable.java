package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.oplbacthdiarios.controller.service.implement.ReportePagareDigitalServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerReportePagosCuentaContable {

    private final Logger logger = LoggerFactory.getLogger(ControllerReportePagosCuentaContable.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "jobReportesPgCuentasContables")
    Job job;

    @Autowired
    ReportePagareDigitalServiceImpl pgdigital;

    @GetMapping(value = "cancelacion/reporteContables", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "Derechos patrimoniales", notes = "Carga del archivo hasta generación del reporte")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<RequestResult<String>> cancelacionCdtDigital(HttpServletRequest urlRequest) throws Exception {

        logger.info("inicio job reporte de cuentas contables...");
        JobExecution jobExecution = null;
        RequestResult<String> result;
        result = new RequestResult<>(urlRequest, HttpStatus.OK);

        try {
            JobParameters parameters = new JobParametersBuilder().addDate("date", new Date()).addLong("time", System.currentTimeMillis()).toJobParameters();
            logger.info("joblauncher Run...");
            jobExecution = jobLauncher.run(job, parameters);
            logger.info("JobExecution: {}", jobExecution.getStatus());
            while (jobExecution.isRunning()) {
                logger.info("...");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        if (jobExecution != null) {
            if (jobExecution.getStatus().isUnsuccessful()) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error batch: " + jobExecution.getStatus());

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                logger.info("Reporte de cuentas contables generado exitosamente...");
                result.setResult("Reporte de cuentas contables generado exitosamente...");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error batch jobExecution null");
        }

        return ResponseEntity.ok(result);
    }
}
