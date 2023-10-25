package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_MAEDCV_TMP_DOWN_TBL")
public class OplMaeDCVTmpEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "FECHA_REG")
    private Date fechaReg;

    @Column(name = "COD_ISIN")
    private String codIsin;

    @Column(name = "NUM_CDT")
    private String numCdt;

    @Column(name = "NUM_FOL")
    private String numFol;

    @Column(name = "CTA_INV")
    private String ctaInversionista;

    @Column(name = "ID_TIT")
    private String idTitulo;

    @Column(name = "NOM_TIT")
    private String nombreTitulo;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "FECHA_EMI")
    private Date fechaEmision;

    @Column(name = "FECHA_VEN")
    private Date fechaVencimiento;

    @Column(name = "FECHA_PROX_PG")
    private Date fechaProximoPago;

    @Column(name = "SPREAD")
    private BigDecimal spread;

    @Column(name = "TAS_EFE")
    private BigDecimal tasaEfectiva;

    @Column(name = "TAS_NOM")
    private BigDecimal tasaNominal;

    @Column(name = "OFICINA")
    private String oficina;

    @Column(name = "VAL_CDT")
    private BigDecimal valorCdt;
}
