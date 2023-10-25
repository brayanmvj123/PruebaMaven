package com.bdb.moduleoplcancelaciones.controller.service.control;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.CDTService;
import com.bdb.moduleoplcancelaciones.persistence.model.error.ErrorException;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCDTContingencia;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCancelCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestConfirmacionCancelacion;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestQueryCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT.ResponseCdtProxVen;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v1/")
@Api(value = "Servicio para obtener la información de renovación de CDTs Digitales Desmaterializado.",
        tags = "Información sobre CDTs desmaterializados de oficina")
@CommonsLog
public class ControllerCDTsCancel {

    @Autowired
    CDTService cdtVenService;

    @Autowired
    RepositoryTipVarentorno repositoryTipVarentorno;



    @ApiOperation(value = "Consulta para obtener la información sobre de los CDTS proximos a vencer",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo, revisar los errores mencionado en la respuesta", response = ErrorException.class),
            @ApiResponse(code = 404, message = "CDT no encontrado o no se pude cancelar por oficina, revisar la respuesta", response = RequestResult.class),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    })
    @PostMapping(value = "/cdtsvencido/consultar", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<RequestResult<Object>> consulta(@Valid @RequestBody RequestQueryCDT requestQueryCDT, HttpServletRequest http ) throws Exception {

        log.info("Inicio de consulta de cdts proximos a vencer en el canal " + requestQueryCDT.getCanal());
        ResponseCdtProxVen cdt = cdtVenService.cdtProximoAVencer(requestQueryCDT);
        if (cdt == null) {
            String error = "No existe registro del CDT con número: '"+ requestQueryCDT.getNumCdt().toString() + "', " +
                    "codIsin '" + requestQueryCDT.getCodIsin() + "' y cuenta inversionista '" + requestQueryCDT.getCtaInv() +
                    "' para el usuario con identificación: '"  + requestQueryCDT.getNumTit()+"', revisar datos ingresados.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND, error));
        }

        boolean cancelacionByOficina = Boolean.parseBoolean(repositoryTipVarentorno.findByDescVariable("CANCELACION_CDTS_POR_OFICINA").getValVariable());

        if ((!cdt.getOficina().equals(requestQueryCDT.getOficina())) && !cancelacionByOficina ){
            String error = "El cdt '"+requestQueryCDT.getNumCdt()+"' solo puede ser cancelado en la oficina con id: '"
                    +cdt.getOficina().toString()+"', sucursal: '" + cdt.getNomOficina() + "' en la cual se radicó inicialmente";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND, error));
        }

        log.info("Consulta de cdts proximos a vencer en el canal " + requestQueryCDT.getCanal()+" realizada exitosamente");
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK, cdt));
    }



    @ApiOperation(value = "Servicio de cancelación de los CDTs Desmaterializados Oficina", produces = "application/json",
            response = RequestResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cancelacion exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo, revisar los errores mencionado en la respuesta", response = ErrorException.class),
            @ApiResponse(code = 404, message = "CDT no encontrado o no se pude cancelar por oficina, revisar la respuesta", response = RequestResult.class),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    })
    @PostMapping(value = "/cdtsvencido/cancelar", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<RequestResult<Object>> cancelacion(@Valid @RequestBody RequestCancelCDT requestCancelCDT, HttpServletRequest http) throws Exception {
        return (ResponseEntity<RequestResult<Object>>) cdtVenService.cancelarCdt(requestCancelCDT, http);
    }



    @ApiOperation(value = "Servicio encargado de confirmar la renovación del CDT Desmaterializado Oficina", produces = "application/json",
            response = RequestResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proceso exitoso"),
            @ApiResponse(code = 400, message = "Request erroneo, revisar los errores mencionado en la respuesta", response = ErrorException.class),
            @ApiResponse(code = 404, message = "CDT no encontrado o no cancelado, revisar la respuesta", response = RequestResult.class),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    })
    @PostMapping(value = "/cdtsvencido/renovacion/confirmar", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<RequestResult<Object>> confirmacion(@Valid @RequestBody RequestConfirmacionCancelacion requestConfirmacionCancelacion,
                                          HttpServletRequest httpServletRequest) throws Exception {

        return (ResponseEntity<RequestResult<Object>>) cdtVenService.renovarCDT(requestConfirmacionCancelacion, httpServletRequest);
    }



    @ApiOperation(value = "Servicio de contingencia que permite recordar a canales la información del CDT Desmaterializado " +
            "que se dió la orden de renovar pero ese servicio quedo incompleto", produces = "application/json",
            response = RequestResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo, revisar los errores mencionado en la respuesta", response = ErrorException.class),
            @ApiResponse(code = 404, message = "CDT no encontrado o no se pude cancelar por oficina, revisar la respuesta", response = RequestResult.class),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    })
    @PostMapping(value = "/cdtsvencido/contingencia/renovacion", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<RequestResult<Object>> contingencia(@Valid @RequestBody RequestCDTContingencia requestCDTContingencia, HttpServletRequest http ) throws Exception {
        return (ResponseEntity<RequestResult<Object>>) cdtVenService.contingencia(requestCDTContingencia, http);
    }
}
