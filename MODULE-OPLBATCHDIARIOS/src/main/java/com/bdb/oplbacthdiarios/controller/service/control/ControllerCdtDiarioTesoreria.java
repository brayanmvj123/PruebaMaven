package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.oplbacthdiarios.controller.service.interfaces.CdtDiarioTesoreriaService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
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
@Api(value = "Servicio CDT Tesoreria Diario.")
public class ControllerCdtDiarioTesoreria {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final CdtDiarioTesoreriaService cdtDiarioTesoreriaService;

    public ControllerCdtDiarioTesoreria(CdtDiarioTesoreriaService cdtDiarioTesoreriaService) {
        this.cdtDiarioTesoreriaService = cdtDiarioTesoreriaService;
    }

    @GetMapping("CdtDiarioTesoreria")
    @ApiOperation(value = "CDT Diario Tesoreria", notes = "CDT Diario Tesoreria")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreria(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        logger.info("INICIA EL CONSUMO DE CDT Diario Tesoreria (RENOVACION / CANCELADOS).");
        return cdtDiarioTesoreriaService.cdtDiarioTesoreria(request);
    }

    @GetMapping("CdtDiarioTesoreriaRenovado")
    @ApiOperation(value = "CDT Diario Tesoreria Renovado", notes = "CDT Diario Tesoreria Renovado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreriaRenovado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        logger.info("INICIA EL CONSUMO DE CDT Diario Tesoreria Renovado");
        return cdtDiarioTesoreriaService.cdtDiarioTesoreriaRenovado(request);
    }

    @GetMapping("CdtDiarioTesoreriaCancelado")
    @ApiOperation(value = "CDT Diario Tesoreria Cancelado", notes = "CDT Diario Tesoreria Cancelado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreriaCancelado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        logger.info("INICIA EL CONSUMO DE CDT Diario Tesoreria Cancelado");
        return cdtDiarioTesoreriaService.cdtDiarioTesoreriaCancelado(request);
    }

}
