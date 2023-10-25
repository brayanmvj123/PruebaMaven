package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONTitularCDT {
    @NotNull
    private String id_cliente;
}
