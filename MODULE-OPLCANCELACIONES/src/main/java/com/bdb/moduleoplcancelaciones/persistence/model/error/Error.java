package com.bdb.moduleoplcancelaciones.persistence.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Error implements Serializable {

    private String app;
    private LocalDate date;
    private String message;
    private HttpStatus status;

}
