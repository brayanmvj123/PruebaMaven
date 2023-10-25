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
@Table(name = "OPL_SAL_PGSEMANAL_DOWN_TBL")
public class SalPgSemanalDownEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "NRO_OFICINA")
    private Long nroOficina;

    @Column(name = "DEPOSITANTE")
    private String depositante;

    @Column(name = "NUM_CDT")
    private Long numCdt;

    @Column(name = "COD_ISIN")
    private String codIsin;

    @Column(name = "CTA_INV")
    private String ctaInv;

    @Column(name = "COD_ID")
    private Integer codId;

    @Column(name = "NUM_TIT")
    private String numTit;

    @Column(name = "NOM_TIT")
    private String nomTit;

    @Column(name = "FECHA_EMI")
    private LocalDate fechaEmi;

    @Column(name = "FECHA_VEN")
    private LocalDate fechaVen;

    @Column(name = "FECHA_PROX_PG")
    private String fechaProxPg;

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

    @Column(name = "SPREAD")
    private BigDecimal spread;

    @Column(name = "VALOR_NOMINAL")
    private BigDecimal valorNominal;

    @Column(name = "INT_BRUTO")
    private BigDecimal intBruto;

    @Column(name = "RTE_FTE")
    private BigDecimal rteFte;

    @Column(name = "INT_NETO")
    private BigDecimal intNeto;

    @Column(name = "CAP_PG")
    private BigDecimal capPg;

    @Column(name = "TOTAL_PAGAR")
    private BigDecimal totalPagar;

    @Column(name = "TIP_POSICION")
    private Long tipPosicion;

    @Column(name = "FACTOR_DCVSA")
    private BigDecimal factorDcvsa;

    @Column(name = "FACTOR_OPL")
    private BigDecimal factorOpl;

    @Column(name = "COD_PROD")
    private Integer codProd;

    @Column(name = "ESTADO")
    private Integer estado;

    @Column(name = "FECHA")
    private LocalDateTime fecha;

    @Column(name = "ENVIADO")
    private Integer enviado;

    private static final long serialVersionUID = 1L;
}
