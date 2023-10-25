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
@Table(name="OPL_PAR_TIPDANE_DOWN_TBL")
public class TipDaneEntity implements Serializable {

    @Id
    @Column(name="COD_DANE")
    private Integer codDane;

    @Column(name="DES_DANE")
    private String desDane;

    @Column(name="HOMO_CRM")
    private String homoCrm;

}
