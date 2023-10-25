package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opalotasasfija.persistence.JSONSchema.request.calculadoradias.JSONRequestCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResponseCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResultCalculoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;

import javax.servlet.http.HttpServletRequest;

public interface CalculadoraDiasService {

    JSONResponseCalculadoraDias<JSONResponseStatus,
            JSONRequestCalculadoraDias,
            JSONResultCalculoraDias> calculoDias(JSONRequestCalculadoraDias request, HttpServletRequest url);

}
