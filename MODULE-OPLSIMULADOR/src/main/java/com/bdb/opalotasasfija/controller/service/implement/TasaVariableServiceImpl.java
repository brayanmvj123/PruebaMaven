package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.JSONRequestTasaVariable;
import com.bdb.opaloshare.persistence.repository.RepositoryOplTasaVariable;
import com.bdb.opalotasasfija.controller.service.interfaces.TasaVariableService;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.JSONResponse;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TasaVariableServiceImpl implements TasaVariableService {

    @Autowired
    RepositoryOplTasaVariable repositoryOplTasaVariable;

    @Override
    public JSONResponse<JSONResponseStatus,
            JSONRequestTasaVariable,
            List<OplHisTasaVariableEntity>> consultaTasaVariable(HttpServletRequest url) {
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
                List<OplHisTasaVariableEntity>> resultado = new JSONResponse<>();
        List<OplHisTasaVariableEntity> tasaVariable = tasaVariable();
        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(HttpStatus.OK.value());
        jsonResponseStatus.setMessage(HttpStatus.OK.getReasonPhrase());
        resultado.setStatus(jsonResponseStatus);
        resultado.setRequestUrl(url.getRequestURL().toString());
        resultado.setResult(tasaVariable);
        return resultado;
    }

    @Override
    public JSONResponse<JSONResponseStatus, JSONRequestTasaVariable, OplHisTasaVariableEntity>
    consultaTasaVariableTipoFecha(JSONRequestTasaVariable request, HttpServletRequest url) {
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable, OplHisTasaVariableEntity> resultado = new JSONResponse<>();
        OplHisTasaVariableEntity tasaVariable = tasaVariableTipoFecha(request.getParametros().getTipotasa(),
                LocalDate.parse(request.getParametros().getFecha(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(HttpStatus.OK.value());
        jsonResponseStatus.setMessage(HttpStatus.OK.getReasonPhrase());
        resultado.setStatus(jsonResponseStatus);
        resultado.setRequestUrl(url.getRequestURL().toString());
        resultado.setParameters(request);
        resultado.setResult(tasaVariable);
        return resultado;
    }

    @Override
    public JSONResponse<JSONResponseStatus, JSONRequestTasaVariable, List<OplHisTasaVariableEntity>>
    consultaTasaVariableFecha(JSONRequestTasaVariable request, HttpServletRequest url) {
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
                List<OplHisTasaVariableEntity>> resultado = new JSONResponse<>();
        List<OplHisTasaVariableEntity> tasaVariable = tasaVariableFecha(
                LocalDate.parse(request.getParametros().getFecha(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(HttpStatus.OK.value());
        jsonResponseStatus.setMessage(HttpStatus.OK.getReasonPhrase());
        resultado.setStatus(jsonResponseStatus);
        resultado.setRequestUrl(url.getRequestURL().toString());
        resultado.setParameters(request);
        resultado.setResult(tasaVariable);
        return resultado;
    }

    @Override
    public JSONResponse<JSONResponseStatus,
            JSONRequestTasaVariable,
            List<OplHisTasaVariableEntity>> consultaTasaVariableTipo(JSONRequestTasaVariable request, HttpServletRequest url) {
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
                List<OplHisTasaVariableEntity>> resultado = new JSONResponse<>();
        List<OplHisTasaVariableEntity> tasaVariable = tasaVariableTipo(request.getParametros().getTipotasa());
        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(HttpStatus.OK.value());
        jsonResponseStatus.setMessage(HttpStatus.OK.getReasonPhrase());
        resultado.setStatus(jsonResponseStatus);
        resultado.setRequestUrl(url.getRequestURL().toString());
        resultado.setParameters(request);
        resultado.setResult(tasaVariable);
        return resultado;
    }


    /**
     * query tasa variable en una fecha especifica
     *
     * @param tipotasa   tipo de tasa (Integer).
     * @param localfecha fecha a consultar.
     * @return valor de la tasa.
     */

    public OplHisTasaVariableEntity tasaVariableTipoFecha(Integer tipotasa, LocalDate localfecha) {
        int fecha = Integer.parseInt(localfecha.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        Optional<OplHisTasaVariableEntity> oplHisTasaVariableEntity = repositoryOplTasaVariable.findByIdTipotasaTipTasaAndIdFecha(tipotasa,
                fecha);
        return oplHisTasaVariableEntity
                .map(tasaDownEntity -> tasaDownEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                        "El tipo de tasa en la fecha indicada no se ha encontrado."));
    }

    /**
     * @param tipotasa tipo de tasa (Integer).
     * @return valor de la tasa.
     */
    public List<OplHisTasaVariableEntity> tasaVariableTipo(Integer tipotasa) {
        return repositoryOplTasaVariable.findAllByIdTipotasaTipTasa(tipotasa);
    }

    /**
     * registros de Tasas variables en una fecha especifica
     *
     * @param localfecha fecha a consultar.
     * @return valor de la tasa.
     */
    public List<OplHisTasaVariableEntity> tasaVariableFecha(LocalDate localfecha) {
        int fecha = Integer.parseInt(localfecha.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        return repositoryOplTasaVariable.findAllByIdFecha(fecha);
    }

    /**
     * todos los registros
     *
     * @return List<OplHisTasaVariableEntity>
     */
    public List<OplHisTasaVariableEntity> tasaVariable() {
        return repositoryOplTasaVariable.findAll();
    }
}
