package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OplHisCalendarioDownIdEntity implements Serializable {

    @Column(name="ANNO")
    private Integer anno;

    @Column(name="MES")
    private Integer mes;

    @Column(name="DIA")
    private Integer dia;

}
