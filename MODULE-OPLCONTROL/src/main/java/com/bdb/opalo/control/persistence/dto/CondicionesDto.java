package com.bdb.opalo.control.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondicionesDto implements Serializable {

    @NotNull(message = "Debe especificar un valor para el capital")
    private Long capital;

    @NotNull(message = "Debe especificar un valor para el rendimientos")
    @Min(1)
    @Max(2)
    private Integer rendimientos;

    @NotNull(message = "Debe especificar un valor para el plazo")
    @Min(3)
    private Integer plazo;

    @NotNull(message = "Debe especificar un valor para el tipPlazo")
    @Min(1)
    @Max(2)
    private Integer tipPlazo;

    @NotNull(message = "Debe especificar un valor para el base")
    private Integer base;



}
