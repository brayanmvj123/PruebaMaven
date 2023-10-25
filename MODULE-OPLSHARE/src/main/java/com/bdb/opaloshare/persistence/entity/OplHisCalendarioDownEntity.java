package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_HIS_CALENDARIO_DOWN_TBL")
public class OplHisCalendarioDownEntity  implements Serializable {

    @EmbeddedId
    private OplHisCalendarioDownIdEntity fecha;

    @Column(name="FECHA_V")
    private Integer valor;
}
