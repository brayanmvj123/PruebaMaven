package com.bdb.oplbacthsemanal.controller.service.control;

import com.bdb.oplbacthsemanal.controller.service.interfaces.CdtTesoreriaSemanalService;
import com.bdb.oplbacthsemanal.persistence.model.response.ResponseBatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@Api(value = "Modulo Batch Semanal")
public class ControllerCdtSemanalTesoreria {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final CdtTesoreriaSemanalService cdtTesoreriaSemanalService;

    public ControllerCdtSemanalTesoreria(CdtTesoreriaSemanalService cdtTesoreriaSemanalService) {
        this.cdtTesoreriaSemanalService = cdtTesoreriaSemanalService;
    }

    @GetMapping("CdtSemanalTesoreria")
    @ApiOperation(value = "CDT Semanal Tesoreria", notes = "Cdt Semanal Tesoreria")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> cdtSemanalTesoreria(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtSemanalTesoreria...");
        return cdtTesoreriaSemanalService.cdtSemanalTesoreria(request);

    }

    @GetMapping("CdtSemanalTesoreriaRenovado")
    @ApiOperation(value = "CDT Semanal Tesoreria Renovado", notes = "CDT Semanal Tesoreria Renovado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreriaRenovado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtSemanalTesoreriaRenovado...");
        return cdtTesoreriaSemanalService.cdtDiarioTesoreriaRenovado(request);

    }

    @GetMapping("CdtSemanalTesoreriaCancelado")
    @ApiOperation(value = "CDT Semanal Tesoreria Cancelado", notes = "CDT Semanal Tesoreria Cancelado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreriaCancelado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtSemanalTesoreriaCancelado...");
        return cdtTesoreriaSemanalService.cdtDiarioTesoreriaCancelado(request);

    }

}
