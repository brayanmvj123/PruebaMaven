package com.bdb.moduleoplcovtdsa.config.exceptions;

import com.bdb.moduleoplcovtdsa.persistence.model.error.Error;
import com.bdb.moduleoplcovtdsa.persistence.model.error.ErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {ErrorException.class})
    @ResponseBody
    public ResponseEntity<Error> handlerException(ErrorException errorException) {
        return ResponseEntity
                .status(errorException.getHttpStatus())
                .body(new Error("OPL766",
                        LocalDate.now(),
                        errorException.getMessage(),
                        errorException.getHttpStatus()));
    }
}
