package com.bdb.platform.servfront.controller.service.interfaces;

import com.bdb.platform.servfront.model.JSONSchema.ResultJSONPorcentaje;

import java.math.BigDecimal;


public interface GetPorcentajesService {

    ResultJSONPorcentaje getPorcentajes(BigDecimal valorTotal, BigDecimal valorGravado, BigDecimal valorNoGravado);

}
