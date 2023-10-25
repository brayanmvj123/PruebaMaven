package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "OPL_HIS_RENOVACION_DOWN_TBL")
@IdClass(HisRenovacionPk.class)
public class HisRenovacion implements Serializable {

    @Id
    @Column(name = "OPL_CDTXCTAINV_TBL_NUM_CDT")
    private Long oplCdtxctainvTblNumCdt;

    @Id
    @Column(name = "OPL_CDTXCTAINV_TBL_OPL_CTAINV_TBL_NUM_CTA")
    private Long oplCdtxctainvTblOplCtainvTblNumCta;

    @Id
    @Column(name = "OPL_CDTXCTAINV_TBL_COD_ISIN")
    private String oplCdtxctainvTblOplCodIsin;

    @Column(name = "NUEVO_NUM_CDT")
    private Long nuevoNumCdt;

    @Column(name = "OPL_ESTADOS_TBL_TIP_ESTADO")
    private Integer oplEstadosTblTipEstado;

    @Column(name = "VALOR")
    private BigDecimal valor;
}
