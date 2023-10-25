package com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResultCalculoraDias implements Serializable {

    private List<TipPeriodParDownEntity> periodicidad;
    private Long plazoenDias;

}
