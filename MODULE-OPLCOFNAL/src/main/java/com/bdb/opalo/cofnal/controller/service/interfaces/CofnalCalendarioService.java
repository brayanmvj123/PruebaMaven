package com.bdb.opalo.cofnal.controller.service.interfaces;

import com.bdb.opalo.cofnal.persistance.JSONSchema.response.calendario.JSONResponseCalendarioCofnal;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CofnalCalendarioService {

    ResponseEntity<JSONResponseCalendarioCofnal> consultaAllCalendario(HttpServletRequest urlRequest);
}
