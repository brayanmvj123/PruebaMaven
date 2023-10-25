package com.bdb.opalo.pagoaut.persistence.jsonschema.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePagCdt implements Serializable {

    private HttpStatus status;
    private LocalDateTime hourPetition;
    private String result;
    private List<ResponseTranpg> responseTranpgList;

}
