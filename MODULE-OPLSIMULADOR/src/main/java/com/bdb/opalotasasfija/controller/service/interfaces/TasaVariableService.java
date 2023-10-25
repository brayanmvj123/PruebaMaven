package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.JSONRequestTasaVariable;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.JSONResponse;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TasaVariableService {
    JSONResponse<JSONResponseStatus, JSONRequestTasaVariable, List<OplHisTasaVariableEntity>>
    consultaTasaVariable(HttpServletRequest url);

    JSONResponse<JSONResponseStatus, JSONRequestTasaVariable, OplHisTasaVariableEntity>
    consultaTasaVariableTipoFecha(JSONRequestTasaVariable request, HttpServletRequest url);

    JSONResponse<JSONResponseStatus, JSONRequestTasaVariable, List<OplHisTasaVariableEntity>>
    consultaTasaVariableFecha(JSONRequestTasaVariable request, HttpServletRequest url);

    JSONResponse<JSONResponseStatus, JSONRequestTasaVariable, List<OplHisTasaVariableEntity>>
    consultaTasaVariableTipo(JSONRequestTasaVariable request, HttpServletRequest url);

}
