package com.bdb.opalo.control.persistence.JSONSchema;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONSimulador {
    @NotNull
    @Valid
    private String canal;

    @NotNull
    @Valid
    private ParametrosJSONSimulador parametros;
}
