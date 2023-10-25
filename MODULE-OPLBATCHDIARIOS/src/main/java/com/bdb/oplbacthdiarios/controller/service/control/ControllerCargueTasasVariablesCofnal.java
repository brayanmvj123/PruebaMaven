package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.oplbacthdiarios.controller.service.interfaces.CargueTasasVariablesCofnalService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(value = "Modulo Batch Diarios", description = "Servicio Modulo Batch Diarios")
public class ControllerCargueTasasVariablesCofnal {
    @Autowired
    CargueTasasVariablesCofnalService cargueTasasVariablesCofnalService;

    @ApiOperation(value = "Este servicio se encarga de traer las tasas variables consultando el servicio de OPLCofnal del último mes.",
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
    @GetMapping("carga/tasasvariables/ultimomes")
    public ResponseEntity<ResponseBatch> tasasVariablesCofnalUltimoMes(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE INICIA LA CONSULTA Y CARGUE DE INFORMACIÓN PARA LLENAR LA TABLA OPL_PAR_TASAVARIABLE_DOWN_TBL");
        //return cargueTasasVariablesCofnalService.consumeJobsTasaVariable(urlRequest);
        return null;
    }
}
