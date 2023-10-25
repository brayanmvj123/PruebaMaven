package com.bdb.moduleoplcancelaciones.persistence.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionBVBM {

    @ExceptionHandler(value = {ServiceBVBMException.class})
    @ResponseBody
    public ResponseEntity<ErrorMessage> handlerException(String app, LocalDateTime fecha, String nombreService,
                                                         ServiceBVBMException ex){
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorMessage(app, fecha, nombreService, ex.getMessage()));
    }

}
