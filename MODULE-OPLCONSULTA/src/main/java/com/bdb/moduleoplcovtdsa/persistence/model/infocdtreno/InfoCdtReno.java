package com.bdb.moduleoplcovtdsa.persistence.model.infocdtreno;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoCdtReno implements Serializable {

    @ApiModelProperty(value = "80000778000088", name = "numCdtActual", dataType = "Long", required = true,
            example = "80000778000088", notes = "Número del CDT Digital actual (Nuevo número asignado al renovar.")
    private Long numCdtActual;
    @ApiModelProperty(value = "2021-07-02", name = "fechaReno", dataType = "LocalDate", required = true,
            example = "2021-07-02", notes = "Fecha en la que se realizo la renovación.")
    private LocalDate fechaReno;
    @ApiModelProperty(value = "2", name = "numReno", dataType = "Integer", required = true,
            example = "2", notes = "Cantidad de renovaciones.")
    private Integer numReno;
    @ApiModelProperty(value = "80000700000086", name = "cdtOrigen", dataType = "Long", required = true,
            example = "80000700000086", notes = "Número del CDT Digital aperturado por primera vez.")
    private Long cdtOrigen;
    @ApiModelProperty(value = "80000777000086", name = "cdtAnt", dataType = "Long", required = true,
            example = "80000777000086", notes = "Número del CDT Digital antes de la renovación.")
    private Long cdtAnt;

}
