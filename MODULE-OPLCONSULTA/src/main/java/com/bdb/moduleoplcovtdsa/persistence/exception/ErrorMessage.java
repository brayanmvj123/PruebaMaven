package com.bdb.moduleoplcovtdsa.persistence.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String app;
    private LocalDateTime date;
    private String nameServiceResponse;
    private String message;

}
