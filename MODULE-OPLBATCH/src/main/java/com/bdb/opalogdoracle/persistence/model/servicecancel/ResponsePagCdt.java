package com.bdb.opalogdoracle.persistence.model.servicecancel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePagCdt implements Serializable {

    private HttpStatus status;
    private LocalDateTime horaPeticion;
    private String resultado;

}
