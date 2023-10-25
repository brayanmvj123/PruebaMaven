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
@Table(name = "OPL_PAR_VARENTORNO_DOWN_TBL")
public class VarentornoDownEntity implements Serializable {

    @Id
    @Column(name = "COD_VARIABLE")
    private Integer codVariable;

    @Column(name = "DESC_VARIABLE")
    private String descVariable;

    @Column(name = "VAL_VARIABLE")
    private String valVariable;

}
