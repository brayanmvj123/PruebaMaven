package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_CAR_INTEFECTDCV_DOWN_TBL")
public class CarIntefectDcvEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "COD_TRANSACCION")
    private String codTransaccion;

    @Column(name = "FECHA_REAL")
    private LocalDate fechaReal;

    @Column(name = "FECHA_CONTABLE")
    private LocalDate fechaContable;

    @Column(name = "NUMERO_CTA")
    private String numeroCta;

    @Column(name = "OFICINA")
    private Integer oficina;

    @Column(name = "INT_BRUTO")
    private BigDecimal intBruto;

    @Column(name = "INT_NETO")
    private BigDecimal intNeto;

    @Column(name = "ID_CLIENTE")
    private String idCliente;

    @Column(name = "FORMA_PG")
    private String formaPg;

    @Column(name = "PERIODO")
    private String periodo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "APLICACION")
    private String aplicacion;

}
