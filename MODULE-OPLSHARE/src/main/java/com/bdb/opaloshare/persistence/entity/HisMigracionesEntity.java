package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_MIGRACIONES_DOWM_TBL")
public class HisMigracionesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item;

    private LocalDate fecha;

    @Column(name = "NUM_CDT")
    private Long numCdt;

    @Column(name = "NUEVA_OFICINA")
    private Integer nuevaOficina;

    private Integer oficina;
}
