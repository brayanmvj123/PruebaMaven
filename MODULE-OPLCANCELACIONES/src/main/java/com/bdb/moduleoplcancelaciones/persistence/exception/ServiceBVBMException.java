package com.bdb.moduleoplcancelaciones.persistence.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ServiceBVBMException extends RuntimeException{

    private final HttpStatus httpStatus;

    public ServiceBVBMException(String app, LocalDateTime date, String nameServiceResponse, String message,
                                HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

}
