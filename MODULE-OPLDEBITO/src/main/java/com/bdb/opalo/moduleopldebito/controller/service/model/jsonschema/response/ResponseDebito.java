package com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDebito implements Serializable {

    private HttpStatus status;
    private LocalDateTime hourPetition;
    private String result;

}
