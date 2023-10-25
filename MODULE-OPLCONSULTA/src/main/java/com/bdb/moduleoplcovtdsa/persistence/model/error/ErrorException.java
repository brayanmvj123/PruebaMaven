package com.bdb.moduleoplcovtdsa.persistence.model.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ErrorException(String message, HttpStatus status){
        super(message);
        this.httpStatus = status;
    }

}
