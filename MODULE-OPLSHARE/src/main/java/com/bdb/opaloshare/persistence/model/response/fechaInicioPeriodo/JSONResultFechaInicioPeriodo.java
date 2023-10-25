package com.bdb.opaloshare.persistence.model.response.fechaInicioPeriodo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResultFechaInicioPeriodo implements Serializable {
    private String fechaInicio;
}
