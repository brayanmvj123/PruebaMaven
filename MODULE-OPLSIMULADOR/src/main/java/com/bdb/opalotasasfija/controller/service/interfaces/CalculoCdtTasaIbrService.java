package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;

import java.text.ParseException;

public interface CalculoCdtTasaIbrService {

    JSONResultSimuladorCuota calculoIbr(JSONRequestSimuladorCuota request) throws ParseException;

}
