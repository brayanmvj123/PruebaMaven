package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_HIS_CANCELAUT_DOWN_TBL")
@IdClass(value = HisCDTSCancelationPk.class)
public class HisCDTSCancelationEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="COD_ISIN")
    private String codIsin;

    @Id
    @Column(name="NUM_CDT")
    private Long numCdt;

    @Id
    @Column(name="NUM_TIT")
    private String numTit;

    private String pago;

    @Column(name="FECHA")
    private LocalDateTime fecha;

}
