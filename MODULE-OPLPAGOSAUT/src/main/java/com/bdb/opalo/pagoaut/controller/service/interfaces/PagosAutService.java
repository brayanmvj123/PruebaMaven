package com.bdb.opalo.pagoaut.controller.service.interfaces;

import com.bancodebogota.customers.product.service.DelCDAFault;
import com.bdb.opalo.pagoaut.persistence.jsonschema.request.JSONCancelacionAut;
import com.bdb.opalo.pagoaut.persistence.jsonschema.response.ResponsePagCdt;

public interface PagosAutService {

    ResponsePagCdt consumoPagosAut(JSONCancelacionAut jsonCancelacionAut) throws DelCDAFault;
}
