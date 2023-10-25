package com.bdb.opalo.cofnal.controller.service.implement;

import com.bdb.opalo.cofnal.controller.service.interfaces.CofnalCalendarioService;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.JSONResponseStatus;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.calendario.JSONResponseCalendarioCofnal;
import com.bdb.opalo.cofnal.persistance.model.entity.CalendarioEntity;
import com.bdb.opalo.cofnal.persistance.model.repository.RepositoryCalendarioCofnal;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class CofnalCalendarioImpl implements CofnalCalendarioService {

    private final RepositoryCalendarioCofnal repositoryCalendarioCofnal;

    public CofnalCalendarioImpl(RepositoryCalendarioCofnal repositoryCalendarioCofnal) {
        this.repositoryCalendarioCofnal = repositoryCalendarioCofnal;
    }

    @Override
    public ResponseEntity<JSONResponseCalendarioCofnal> consultaAllCalendario(HttpServletRequest urlRequest) {
        log.info("SE INICIA LA CONSULTA DE TODA LOS DATOS EXPUESTO DEL CALENDARIO DE COFNAL.");
        Optional<List<CalendarioEntity>> calendario = Optional.of(repositoryCalendarioCofnal.findAll());
        return calendario.map(calendarioEntities -> ResponseEntity
                .status(HttpStatus.OK)
                .body(new JSONResponseCalendarioCofnal(
                        new JSONResponseStatus(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()),
                        urlRequest.getRequestURL().toString(),
                        calendarioEntities
                ))).orElse(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new JSONResponseCalendarioCofnal(
                        new JSONResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                        urlRequest.getRequestURL().toString(),
                        null
                )));
    }
}
