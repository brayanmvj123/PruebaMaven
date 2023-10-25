package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;

import java.text.ParseException;

public interface CalculoCdtTasaDtfService {

    JSONResultSimuladorCuota calculoCdtDtf(JSONRequestSimuladorCuota request) throws ParseException;

    double calcularTasaEfectivaDtf(double tasaTA, double spread, double periodo, double base) throws ParseException;

}
