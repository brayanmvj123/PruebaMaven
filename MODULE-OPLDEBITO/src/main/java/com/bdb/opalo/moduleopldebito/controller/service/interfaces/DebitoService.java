package com.bdb.opalo.moduleopldebito.controller.service.interfaces;

import com.bancodebogota.customers.product.service.SetCreditDepositAcctPaymentFault;
import com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.request.DebitoRequest;
import com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.response.ResponseDebito;


public interface DebitoService {

    ResponseDebito debito(DebitoRequest jsonCancelacionAut) throws SetCreditDepositAcctPaymentFault;
}
