package com.bdb.opalossqls.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DCV_SAL_PGOPALO_DOWN_TBL")
public class DCVSalPgOpl {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM")
    private Long item;

    @Column(name="COD_ISIN")
    private String codIsin;

    @Column(name="NUM_CDT")
    private Long numCdt;

    @Column(name="TIP_ID")
    private String tipId;

    @Column(name="NUM_TIT")
    private String numTit;

    @Column(name="NOM_TIT")
    private String nomTit;

    @Column(name="CAP_PG")
    private BigDecimal capPg;

    @Column(name="INT_BRUTO")
    private BigDecimal intBruto;

    @Column(name="RTE_FTE")
    private BigDecimal rteFte;

    @Column(name="INT_NETO")
    private BigDecimal intNeto;

    @Column(name="FORMA_PAGO")
    private String formaPago;

    public DCVSalPgOpl(String codIsin, Long numCdt, String tipId, String numTit, String nomTit, BigDecimal capPg,
                       BigDecimal intBruto, BigDecimal rteFte, BigDecimal intNeto, String formaPago) {
        this.codIsin = codIsin;
        this.numCdt = numCdt;
        this.tipId = tipId;
        this.numTit = numTit;
        this.nomTit = nomTit;
        this.capPg = capPg;
        this.intBruto = intBruto;
        this.rteFte = rteFte;
        this.intNeto = intNeto;
        this.formaPago = formaPago;
    }
}
