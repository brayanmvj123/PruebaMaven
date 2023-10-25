package com.bdb.opalo.cofnal.controller.service.implement;

import com.bdb.opalo.cofnal.controller.service.interfaces.CofnalTasaVariableService;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.JSONResponseStatus;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable.JSONResponseTasaVariableCofnal;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable.JSONResponseTasaVariableCofnalMensual;
import com.bdb.opalo.cofnal.persistance.model.entity.TasaVariableCofnalEntity;
import com.bdb.opalo.cofnal.persistance.model.entity.TasaVariableCofnalMensualEntity;
import com.bdb.opalo.cofnal.persistance.model.repository.RepositoryTasaVariableCofnal;
import com.bdb.opalo.cofnal.persistance.model.repository.RepositoryTasaVariableCofnalMensual;
import com.bdb.opaloshare.persistence.model.jsonschema.obtenerTasa.JSONRequestTasaVariableCofnal;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class CofnalTasaVariableServiceImpl implements CofnalTasaVariableService {

    private final RepositoryTasaVariableCofnal repositoryTasaVariableCofnal;

    private final RepositoryTasaVariableCofnalMensual repositoryTasaVariableCofnalMensual;

    public CofnalTasaVariableServiceImpl(RepositoryTasaVariableCofnal repositoryTasaVariableCofnal,
                                         RepositoryTasaVariableCofnalMensual repositoryTasaVariableCofnalMensual) {
        this.repositoryTasaVariableCofnal = repositoryTasaVariableCofnal;
        this.repositoryTasaVariableCofnalMensual = repositoryTasaVariableCofnalMensual;
    }

    @Override
    public ResponseEntity<JSONResponseTasaVariableCofnal> consultaAllTasaVariable(HttpServletRequest urlRequest) {
        log.info("SE INICIA LA CONSULTA consultaAllTasaVariable.");
        Optional<List<TasaVariableCofnalEntity>> tasas = Optional.of(repositoryTasaVariableCofnal.findAll());
        return tasas.filter(item -> !item.isEmpty())
                .map(tasaVariableCofnalEntities -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new JSONResponseTasaVariableCofnal(
                                new JSONResponseStatus(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()),
                                urlRequest.getRequestURL().toString(),
                                null,
                                tasaVariableCofnalEntities
                        ))).orElse(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new JSONResponseTasaVariableCofnal(
                                new JSONResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                                urlRequest.getRequestURL().toString(),
                                null,
                                null
                        )));
    }

    @Override
    public ResponseEntity<JSONResponseTasaVariableCofnalMensual> consultaTasaVariableMes(HttpServletRequest urlRequest) {
        log.info("SE INICIA LA CONSULTA consultaTasaVariableMes.");
        Optional<List<TasaVariableCofnalMensualEntity>> tasas = Optional.of(repositoryTasaVariableCofnalMensual.findAll());
        return tasas.filter(item -> !item.isEmpty())
                .map(tasaVariableCofnalMensualEntities -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new JSONResponseTasaVariableCofnalMensual(
                                new JSONResponseStatus(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()),
                                urlRequest.getRequestURL().toString(),
                                null,
                                tasaVariableCofnalMensualEntities
                        ))).orElse(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new JSONResponseTasaVariableCofnalMensual(
                                new JSONResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                                urlRequest.getRequestURL().toString(),
                                null,
                                null
                        )));
    }

    @Override
    public JSONResponseTasaVariableCofnal consultaFechaTasaVariable(JSONRequestTasaVariableCofnal request,
                                                                    HttpServletRequest urlRequest) {
        JSONResponseTasaVariableCofnal result = new JSONResponseTasaVariableCofnal();
        result.setRequestUrl(urlRequest.getRequestURL().toString());
        result.setParameters(result.getParameters());
        List<TasaVariableCofnalEntity> tasas = repositoryTasaVariableCofnal.findAllByFechaBetween(
                LocalDate.parse(request.getParametros().getFechaInicio(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
                , LocalDate.parse(request.getParametros().getFechaFin(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
        );
        result.setResult(tasas);
        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(HttpStatus.OK.value());
        jsonResponseStatus.setMessage(HttpStatus.OK.getReasonPhrase());
        result.setStatus(jsonResponseStatus);
        return result;
    }


}
