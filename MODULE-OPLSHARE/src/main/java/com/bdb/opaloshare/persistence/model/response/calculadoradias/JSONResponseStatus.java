package com.bdb.opaloshare.persistence.model.response.calculadoradias;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseStatus implements Serializable {

    private Integer code;
    private String message;

}
