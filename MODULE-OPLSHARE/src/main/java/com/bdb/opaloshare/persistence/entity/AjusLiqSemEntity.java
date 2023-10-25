package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(AjusLiqSemEntityPk.class)
@Table(name="OPL_HIS_AJUSLIQSEM_LARGE_TBL")
public class AjusLiqSemEntity implements Serializable {

    @Id
    @Column(name = "OPL_PG_TBL_NUM_CDT")
    private Long oplPgTblNumCdt;

    @Id
    @Column(name = "OPL_PG_TBL_COD_ISIN")
    private String oplPgTblCodIsin;

    @Id
    @Column(name = "OPL_PG_TBL_CTA_INV")
    private String oplPgTblCtaInv;

    @Id
    @Column(name = "OPL_PG_TBL_NUM_TIT")
    private String oplPgTblNumTit;

    private LocalDateTime fecha;

    @Column(name = "FECHA_EMI")
    private LocalDate fechaEmi;

    @Column(name = "FECHA_VEN")
    private LocalDate fechaVen;

    @Column(name = "TIP_PLAZO")
    private String tipPlazo;

    @Column(name = "PLAZO")
    private Long plazo;

    @Column(name = "TIP_BASE")
    private String tipBase;

    @Column(name = "TIP_PERIODICIDAD")
    private String tipPeriodicidad;

    @Column(name = "TIP_TASA")
    private String tipTasa;

    @Column(name = "TASA_EFE")
    private BigDecimal tasaEfe;

    @Column(name = "TASA_NOM")
    private BigDecimal tasaNom;

    @Column(name = "VALOR_NOMINAL")
    private BigDecimal valorNominal;

    @Column(name = "TIP_POSICION")
    private Long tipPosicion;

    @Column(name = "FACTOR_DCVSA")
    private BigDecimal factorDcvsa;

    @Column(name = "FACTOR_OPL")
    private BigDecimal factorOpl;

    @Column(name = "SPREAD")
    private BigDecimal spread;

    private static final long serialVersionUID = 1L;
}
