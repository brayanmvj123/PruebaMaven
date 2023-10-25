package com.bdb.platform.servfront.controller.service.implement;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.bdb.platform.servfront.controller.service.interfaces.GetPorcentajesService;
import com.bdb.platform.servfront.model.JSONSchema.ResultJSONPorcentaje;
import com.bdb.platform.servfront.model.commons.Constants;
import org.springframework.stereotype.Service;


@Service
public class GetPorcentajesServiceImpl implements GetPorcentajesService {

    @Override
    public ResultJSONPorcentaje getPorcentajes(BigDecimal valorTotal, BigDecimal valorGravado,
                                               BigDecimal valorNoGravado) {

        ResultJSONPorcentaje JSONPorcentajes = new ResultJSONPorcentaje();

        JSONPorcentajes.setPorcentajeValorTotal(
                valorTotal.divide(valorTotal, 4, RoundingMode.HALF_UP).multiply(Constants.CIEN));
        JSONPorcentajes.setPorcentajeValorGravado(
                valorGravado.divide(valorTotal, 4, RoundingMode.HALF_UP).multiply(Constants.CIEN));
        JSONPorcentajes.setPorcentajeValorNoGravado(
                valorNoGravado.divide(valorTotal, 4, RoundingMode.HALF_UP).multiply(Constants.CIEN));

        return JSONPorcentajes;
    }

}
