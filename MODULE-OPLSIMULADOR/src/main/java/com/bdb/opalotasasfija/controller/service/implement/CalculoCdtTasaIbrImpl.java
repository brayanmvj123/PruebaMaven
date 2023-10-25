package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoCdtTasaIbrService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;

@Service
@CommonsLog
public class CalculoCdtTasaIbrImpl implements CalculoCdtTasaIbrService {

    @Autowired
    private CalculadoraService calculadoraService;

    @Override
    public JSONResultSimuladorCuota calculoIbr(JSONRequestSimuladorCuota request) throws ParseException {

        double capital = calculadoraService.convertDouble(request.getParametros().getCapital());
        double baseDias = calculadoraService.baseDias(request.getParametros().getBase());

        double diasPlazo = calculadoraService.convertDouble(request.getParametros().getDiasPlazo());
        double periodicidadDias = calculadoraService.periodicidadDias(request.getParametros().getPeriodicidad(),
                calculadoraService.convertDouble(request.getParametros().getDiasPlazo()));
        double retencion = calculadoraService.convertDouble(request.getParametros().getRetencion());

        double rate = calculadoraService.convertDouble(request.getParametros().getTasa());

        double numPeriodos = calculadoraService.numerosPeriodos(diasPlazo, periodicidadDias);

        // Important Data
        double months;
        double n = 0;
        double b;
        int periodicity = Integer.parseInt(request.getParametros().getPeriodicidad());
        int base = Integer.parseInt(request.getParametros().getBase());
        double tasaNominal = rate + (Double.parseDouble(request.getParametros().getSpread()));

        // Base
        switch (base) {
            case 1:
                b = 365;
                break;
            case 2:
                b = 360;
                break;
            case 3:
                b = 366;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La base ingresada no es válido para los " +
                        "cálculos requeridos de IBR.");
        }

        // Months
        switch (request.getParametros().getTipoTasa()) {
            case 6:
                months = 1;
                break;
            case 7:
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "El tipo de tasa overnight aún no está " +
                        "implementado en el cálculo de IBR.");
            case 8:
                months = 3;
                break;
            case 9:
                months = 6;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de tasa ingresado no es válido para " +
                        "el cálculo de IBR.");
        }

        // Periodicity
        switch (periodicity) {
            case 0:
                n = 1;
                break;
            case 1:
                n = 30;
                break;
            case 3:
                n = 90;
                break;
            case 6:
                n = 180;
                break;
        }

        // Equation
        double tasaEfectiva = ((Math.pow(1 + ((tasaNominal / 100) * (n / b)), b / n)) - 1) * 100;

        return calculadoraService.intereses(
                BigDecimal.valueOf(tasaNominal).setScale(2, RoundingMode.HALF_UP).doubleValue(), // Nominal Rate
                diasPlazo, // Not Required
                baseDias, // Not Required
                capital, // Not Required
                retencion, // Not Required
                numPeriodos, // Not Required
                BigDecimal.valueOf(tasaEfectiva).setScale(4, RoundingMode.HALF_UP).doubleValue(), // Effective Rate
                request.getParametros().getTipoTasa() // Rate Type
        );
    }
}
