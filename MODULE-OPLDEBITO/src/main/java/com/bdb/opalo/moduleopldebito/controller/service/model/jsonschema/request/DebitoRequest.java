package com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitoRequest implements Serializable {

    private String tipoId;
    private String numeroId;
    private Long numCdt;
    private Integer oficinaOrigen;
    private Long numCta;
    private String tipoCta;
    private BigDecimal valor;

}
