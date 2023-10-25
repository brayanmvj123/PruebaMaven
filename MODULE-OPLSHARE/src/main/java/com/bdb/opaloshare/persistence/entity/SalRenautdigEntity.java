package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_SAL_RENAUTDIG_DOWN_TBL")
public class SalRenautdigEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "COD_ISIN")
    private String codIsin;

    @Column(name = "NUM_CDT")
    private Long numCdt;

    @Column(name = "TIP_ID")
    private String tipId;

    @Column(name = "NUM_TIT")
    private String numTit;

    @Column(name = "NOM_TIT")
    private String nomTit;

    @Column(name = "CAP_PG")
    private BigDecimal capPg;

    @Column(name = "INT_BRUTO")
    private BigDecimal intBruto;

    @Column(name = "RTE_FTE")
    private BigDecimal rteFte;

    @Column(name = "INT_NETO")
    private BigDecimal intNeto;

    @Column(name = "FORMA_PAGO")
    private String formaPago;

    @Column(name = "ESTADO_V")
    private String estadoV;

}
