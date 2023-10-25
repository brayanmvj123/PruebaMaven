package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OplHisTasaVariableIdEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "OPL_TIPTASA_TBL_TIP_TASA",insertable = false,updatable = false)
    private TiptasaParDownEntity tipotasa;

    @Column(name="FECHA")
    private Integer fecha;
}
