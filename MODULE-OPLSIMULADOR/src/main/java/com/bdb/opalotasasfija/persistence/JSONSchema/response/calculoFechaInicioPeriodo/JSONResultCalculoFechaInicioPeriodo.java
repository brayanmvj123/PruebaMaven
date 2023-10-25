package com.bdb.opalotasasfija.persistence.JSONSchema.response.calculoFechaInicioPeriodo;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResultCalculoFechaInicioPeriodo implements Serializable {

    private LocalDate fechaInicio;

}
