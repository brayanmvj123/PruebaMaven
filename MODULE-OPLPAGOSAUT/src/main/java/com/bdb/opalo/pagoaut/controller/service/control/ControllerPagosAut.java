package com.bdb.opalo.pagoaut.controller.service.control;

import com.bancodebogota.customers.product.service.DelCDAFault;
import com.bdb.opalo.pagoaut.controller.service.interfaces.PagosAutService;
import com.bdb.opalo.pagoaut.persistence.jsonschema.request.JSONCancelacionAut;
import com.bdb.opalo.pagoaut.persistence.jsonschema.response.ResponsePagCdt;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerPagosAut {

    final PagosAutService pagosAutService;

    public ControllerPagosAut(PagosAutService pagosAutService) {
        this.pagosAutService = pagosAutService;
    }

    @PostMapping(value = "cancelacion/pagosaut", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ResponsePagCdt> pagosaut(@Valid @RequestBody JSONCancelacionAut jsonCancelacionAut) throws DelCDAFault {
        log.info("Se inicia el pago del CDT Digital " + jsonCancelacionAut.getNumCdt());
        ResponsePagCdt responsePagCdt = pagosAutService.consumoPagosAut(jsonCancelacionAut);
        return ResponseEntity.status(responsePagCdt.getStatus())
                .body(responsePagCdt);
    }
}
