package com.bdb.opalossqls.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DCV_SAL_RENAUTDIG_DOWN_TBL")
public class DCVSalRenautdigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

}
