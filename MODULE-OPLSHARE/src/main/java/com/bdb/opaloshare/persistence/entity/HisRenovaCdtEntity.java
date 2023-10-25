package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_RENOVACDT_DOWN_TBL")
@IdClass(value = HisRenovaCdtPK.class )
public class HisRenovaCdtEntity implements Serializable {

    @Id
    @Column(name = "cdtAct")
    private Long cdtAct;

    @Id
    @Column(name = "cdtAnt")
    private Long cdtAnt;

    @Column(name = "cdtOrigen")
    private Long cdtOrigen;

    @Column(name = "numRenova")
    private Integer numRenova;

}
