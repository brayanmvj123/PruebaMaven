package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_SAL_PGDIGITAL_DOWN_TBL")
public class SalPgdigitalDownEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "NUM_CDT")
    private BigDecimal numCdt;

    @Column(name = "COD_ISIN")
    private String codIsin;

    @Column(name = "TIP_ID")
    private String tipId;

    @Column(name = "NUM_TIT")
    private String numTit;

    @Column(name = "NOM_TIT")
    private String nomTit;

    @Column(name = "INT_BRUTO")
    private BigDecimal intBruto;

    @Column(name = "RTE_FTE")
    private BigDecimal rteFte;

    @Column(name = "INT_NETO")
    private BigDecimal intNeto;

    @Column(name = "TOTAL_PAGAR")
    private BigDecimal totalPagar;

    @Column(name = "CAP_PG")
    private BigDecimal capPg;

    @Column(name = "TIP_CTA")
    private String tipCta;

    @Column(name = "NRO_CTA")
    private String nroCta;

    @Column(name = "OFICINA")
    private String oficina;

    @Column(name = "TIPO_TRAN")
    private Integer tipoTran;

    public List<String> toArrayValues() {

        List<String> values = new ArrayList<>();

        values.add(oficina);
        values.add(String.valueOf(numCdt));
        values.add(codIsin);
        values.add(String.valueOf(tipId));
        values.add(numTit);
        values.add(nomTit);
        values.add(String.valueOf(intBruto));
        values.add(String.valueOf(rteFte));
        values.add(String.valueOf(intNeto));
        values.add(String.valueOf(capPg));
        values.add(tipCta);
        values.add(nroCta);
        values.add(totalPagar.toString());

        return values;
    }

}
