package com.bdb.opaloapcdt.persistence.JSONSchema;

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
