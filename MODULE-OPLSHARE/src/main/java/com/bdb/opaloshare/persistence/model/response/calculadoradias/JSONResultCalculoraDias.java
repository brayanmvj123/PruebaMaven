package com.bdb.opaloshare.persistence.model.response.calculadoradias;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONResultCalculoraDias implements Serializable {

    private List<TipPeriodParDownEntity> periodicidad;

    private Long plazoenDias;

    private static final long serialVersionUID = 1L;

}
