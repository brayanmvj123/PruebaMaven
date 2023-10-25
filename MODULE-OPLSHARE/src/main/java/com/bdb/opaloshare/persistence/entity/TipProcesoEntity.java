package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_PAR_TIPPROCESO_DOWN_TBL")
public class TipProcesoEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TIP_PROCESO")
    private Integer tipProceso;

    @Column(name = "DESCRIPCION")
    private String descripcion;
}
