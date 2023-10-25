package com.bdb.opalo.mds.controller.service.control;

import com.bancodebogota.rdm.classification.service.GetEntityMembersFault;
import com.bdb.opalo.mds.controller.service.interfaces.RatesService;
import com.bdb.opalo.mds.persistence.ResponseBatch;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("CDTSDesmaterializado/v1/")
@CommonsLog
@Api(value = "Este controller consulta las tasas expuestas por MDS y se cargan en una tabla temporal para " +
        "su respectivo uso", produces = "MediaType.APPLICATION_JSON_VALUE")
public class ControllerConsumeRates {

    private final RatesService ratesService;

    public ControllerConsumeRates(RatesService ratesService){
        this.ratesService = ratesService;
    }

    @GetMapping(value = "mds/consumoTasas", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Este servicio se encarga de consultar las tasas de los CDTS Digitales emitidas " +
            "por MDS, despues de obtener la informacion a traves de un servicio BATCH se procede almacenar " +
            "la data en la BD", produces = "application/json",
            response = ResponseBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al realizar la operación, por favor reportar")
    }
    )
    public ResponseEntity<ResponseBatch> consumeRates(HttpServletRequest httpServletRequest) throws GetEntityMembersFault,
            JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException {
        return ratesService.responseConsumeRates(httpServletRequest);
    }
}
