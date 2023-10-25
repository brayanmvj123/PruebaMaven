package com.bdb.opalotasasfija.persistence.JSONSchema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidacionFechaInicial implements Serializable {

    private LocalDate fecha;

    private Integer diasDiferencia;
}
