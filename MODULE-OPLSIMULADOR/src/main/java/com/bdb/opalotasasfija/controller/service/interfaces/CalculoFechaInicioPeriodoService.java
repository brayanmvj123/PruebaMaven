package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo.JSONRequestFechaInicioPeriodo;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResponseCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculoFechaInicioPeriodo.JSONResultCalculoFechaInicioPeriodo;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;

import javax.servlet.http.HttpServletRequest;

public interface CalculoFechaInicioPeriodoService {

        JSONResponseCalculadoraDias<JSONResponseStatus,
                JSONRequestFechaInicioPeriodo,
                JSONResultCalculoFechaInicioPeriodo> calculoFechaInicioPeriodo(JSONRequestFechaInicioPeriodo request, HttpServletRequest url);


}
