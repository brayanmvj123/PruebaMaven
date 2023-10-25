package com.bdb.opalo.control.controller.service.interfaces;

import com.bdb.opalo.control.persistence.JSONSchema.ResultJSONCuotas;
import com.bdb.opaloshare.persistence.model.response.RequestResult;

import java.math.BigDecimal;

public interface SimuladorRendimientoService {
    public RequestResult<ResultJSONCuotas> simulador(Integer base, BigDecimal capital, String periodicidad, int retencion,
                                                     BigDecimal tasaFija, String fechaVen);
}
