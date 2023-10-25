package com.bdb.opalo.control.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseParametros {
    private String canal;
    private ParametrosControlCdtDto parametros;
}
