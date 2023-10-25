package com.bdb.opalogdoracle.controller.service.interfaces.renovaflex;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface RenovacionService {

    ResponseEntity<RequestResult<HashMap<String, Object>>> renovacion(HttpServletRequest http) throws Exception;
    
}
