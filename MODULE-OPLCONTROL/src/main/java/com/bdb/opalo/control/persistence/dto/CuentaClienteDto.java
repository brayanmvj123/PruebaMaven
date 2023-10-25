package com.bdb.opalo.control.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaClienteDto implements Serializable {

    @NotNull(message = "Debe especificar un valor para numCta")
    private String numCta;

    @NotNull(message = "Debe especificar un valor para tipCta")
    private String tipCta;

    @NotNull(message = "Debe especificar un valor para ofiOrigen")
    private Integer ofiOrigen;

}
