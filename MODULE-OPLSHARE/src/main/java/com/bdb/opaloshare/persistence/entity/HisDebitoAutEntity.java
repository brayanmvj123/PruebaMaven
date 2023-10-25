package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_DEBITOAUT_DOWN_TBL")
@IdClass(value = HisDebitoAutPk.class)
public class HisDebitoAutEntity implements Serializable {

    @Id
    @Column(name="COD_ISIN")
    private String codIsin;

    @Id
    @Column(name="NUM_CDT")
    private Long numCdt;

    @Id
    @Column(name="NUM_TIT")
    private String numTit;

    private String debito;

    @Column(name="FECHA")
    private LocalDateTime fecha;
}
