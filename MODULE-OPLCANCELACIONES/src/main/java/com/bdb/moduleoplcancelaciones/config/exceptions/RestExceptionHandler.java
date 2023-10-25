package com.bdb.moduleoplcancelaciones.config.exceptions;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@CommonsLog
public class RestExceptionHandler {


    @ExceptionHandler(value = {RestClientException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(RestClientException e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMessage());
        log.info("Exception: " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new RequestResult<>(http, HttpStatus.BAD_GATEWAY, response));
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(Exception e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        e.printStackTrace();
        response.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResult<>(http, HttpStatus.INTERNAL_SERVER_ERROR, response));
    }
}
