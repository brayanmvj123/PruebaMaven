package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;

import java.text.ParseException;

public interface CalculadoraService {

    double calcularTasaNominal(double tasaFija, double periodo) throws ParseException;

    double entreCien(double valor);

    JSONResultSimuladorCuota intereses(double tasaNominal, double diasPlazo, double baseDias, double capital,
                                               double retencion, double periodo, double tasaEfectivaAnual, Integer tiptasa) throws ParseException;

    double periodo(double base, double periodicidad);

    double numerosPeriodos(double plazo, double periodicidad);

    Double convertDouble(String value);

    double baseDias(String base);

    double periodicidadDias(String periodicidad, double diasPlazo);
}
