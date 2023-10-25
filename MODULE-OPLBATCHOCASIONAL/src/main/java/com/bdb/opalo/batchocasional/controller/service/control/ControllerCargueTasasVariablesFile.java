package com.bdb.opalo.batchocasional.controller.service.control;

import com.bdb.opalo.batchocasional.controller.service.interfaces.CargueTasasVariablesFileService;
import com.bdb.opalo.batchocasional.persistence.Response.ResponseBatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(value = "Modulo Batch Ocacional")
public class ControllerCargueTasasVariablesFile {

    @Autowired
    CargueTasasVariablesFileService cargueTasasVariablesFileService;

    @ApiOperation(value = "Este servicio carga las tasas variables desde un documento csv.",
            produces = "application/json",
            response = ResponseBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proceso exitoso."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al procesar la información.")
    }
    )
    @GetMapping("carga/tasasvariablesFile")
    public ResponseEntity<ResponseBatch> tasasVariablesCofnal(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE INICIA El CARGUE DE INFORMACIÓN PARA LLENAR LA TABLA OPL_PAR_TASAVARIABLE_DOWN_TBL");
        return cargueTasasVariablesFileService.consumeJobsTasaVariableFile(urlRequest);
    }

}
