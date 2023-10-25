package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.oplbacthdiarios.controller.service.interfaces.TransCdtsDigBiService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(tags = "Reporte maestro de ventas de CDTs Desmaterializados Digitales.")
public class ControllerTransCdtsDigBI {

    private final TransCdtsDigBiService transCdtsDigBiService;

    public ControllerTransCdtsDigBI(TransCdtsDigBiService transCdtsDigBiService) {
        this.transCdtsDigBiService = transCdtsDigBiService;
    }

    @ApiOperation(value = "Este servicio se encarga de generar el reporte de maestros de ventas de CDTs Desmaterializados Digitales..",
            produces = "application/json",
            response = ResponseBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reporte generado con exitoso."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al generar la data para el reporte.")
    }
    )
    @PostMapping(value = "bi/transCdtsDig", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseBatch> transCdtsDig(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE INICIA LA GENERACION DE DATA Y ELABORACIÓN DEL ARCHIVO DE CDTS DIGITALES PARA SER ENVIADO A BI, " +
                "INICIO DEL PROCESO: "+ LocalDateTime.now());
        return transCdtsDigBiService.consumeJobs(request);
    }
}
