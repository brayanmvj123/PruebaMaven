package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONAsignarNumCDT {

    @NotNull
    private String canal;

    @NotNull
    private Parametros parametros;

}
