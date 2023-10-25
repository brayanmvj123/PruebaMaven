package com.bdb.opalo.moduleopldebito.controller.service.control;

import com.bancodebogota.customers.product.service.SetCreditDepositAcctPaymentFault;
import com.bdb.opalo.moduleopldebito.controller.service.interfaces.DebitoService;
import com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.request.DebitoRequest;
import com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.response.ResponseDebito;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(value = "Debito", description = "Servicio que permite debitar de la cuenta de un cliente")
public class ControllerDebitos {

    final DebitoService debitoService;

    public ControllerDebitos(DebitoService debitoService) {
        this.debitoService = debitoService;
    }

    @PostMapping(value = "debito", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "Debito", notes = "Debito de la cuenta del cliente.")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN")})
    public ResponseEntity<ResponseDebito> debito(@Valid @RequestBody DebitoRequest request) throws SetCreditDepositAcctPaymentFault {
        log.info("Se inicia el debito para aumento de capital deL CDT Digital " + request.getNumCdt());
        ResponseDebito responseDebito = debitoService.debito(request);
        return ResponseEntity.status(responseDebito.getStatus())
                .body(responseDebito);
    }
}
