package com.bdb.moduleoplcovtdsa.controller.service.control;

import com.bdb.moduleoplcovtdsa.controller.service.interfaces.InfoCdtRenoService;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.request.JSONRequestInfoCdtReno;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.response.JSONResponseInfoCdtReno;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(value = "Servicio para obtener la información de renovación de CDTs Digitales Desmaterializado.", tags = "Información " +
        "sobre la renovación CDTs Digitales.")
public class ControllerInfoCdtReno {

    final InfoCdtRenoService infoCdtRenoService;

    public ControllerInfoCdtReno(InfoCdtRenoService infoCdtRenoService){
        this.infoCdtRenoService = infoCdtRenoService;
    }

    @ApiOperation(value = "Consulta para obtener la información sobre la renovación de CDTS Digitales Desmaterializados " +
            "realizado desde Banca Movil/Virtual", produces = "application/json",
            response = JSONResponseInfoCdtReno.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    }
    )
    @PostMapping(value = "infoCdtReno", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<JSONResponseInfoCdtReno> infoCdtReno(@Valid @RequestBody JSONRequestInfoCdtReno request, HttpServletRequest urlRequest){
        log.info("SE CONSUME EL SERVICIO PARA OBTENER INFORMACIÓN DE LA RENOVACIÓN DE UN CDT DIGITAL DESMATERIALZIADO.");
        return infoCdtRenoService.consultarNumCdt(request, urlRequest);
    }
}
