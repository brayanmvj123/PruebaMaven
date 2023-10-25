package com.bdb.opalo.cofnal.controller.service.interfaces;

import com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable.JSONResponseTasaVariableCofnal;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable.JSONResponseTasaVariableCofnalMensual;
import com.bdb.opaloshare.persistence.model.jsonschema.obtenerTasa.JSONRequestTasaVariableCofnal;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CofnalTasaVariableService {

    ResponseEntity<JSONResponseTasaVariableCofnal> consultaAllTasaVariable(HttpServletRequest urlRequest);

    ResponseEntity<JSONResponseTasaVariableCofnalMensual> consultaTasaVariableMes(HttpServletRequest urlRequest);

    JSONResponseTasaVariableCofnal consultaFechaTasaVariable(JSONRequestTasaVariableCofnal request, HttpServletRequest urlRequest);
}
