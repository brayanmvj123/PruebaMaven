package com.bdb.opalogdoracle.controller.service.interfaces.renovaflex;

import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opalogdoracle.persistence.model.servicecancel.ResponsePagCdt;
import org.springframework.http.ResponseEntity;

public interface AbonoService {

    ResponseEntity<ResponsePagCdt> abono(String endpoint, InfoCtaClienteModel ctaClient);

}
