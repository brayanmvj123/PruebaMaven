package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_TMP_TASASCDTMDS_DOWN_TBL")
public class TmpTasasCdtMdsEntity implements Serializable {

    @Id
    @Column(name = "IDENTIFICADOR")
    private Long identificador;

    @Column(name = "NOM_PROD")
    private String nomProd;

    @Column(name = "COD_PROD")
    private String codProd;

    @Column(name = "TIP_PLAZO")
    private String tipPlazo;

    @Column(name = "PLAZO_MIN")
    private Integer plazoMin;

    @Column(name = "PLAZO_MAX")
    private Integer plazoMax;

    @Column(name = "MONTO_MIN")
    private Long montoMin;

    @Column(name = "MONTO_MAX")
    private Long montoMax;

    @Column(name = "SIGNO")
    private String signo;

    @Column(name = "TASA_SPREAD")
    private BigDecimal tasaSpread;

    @Column(name = "FECHA_CARGA")
    private LocalDate fechaCarga;

}
