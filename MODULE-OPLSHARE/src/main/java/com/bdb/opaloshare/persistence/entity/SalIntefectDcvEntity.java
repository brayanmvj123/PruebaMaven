package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_SAL_INTEFECTDCV_DOWN_TBL")
public class SalIntefectDcvEntity {

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

    public String strucReportMonthly(){
        Function<String, DateTimeFormatter> funcion = DateTimeFormatter::ofPattern;
        return codTransaccion+";"+fechaReal.format(funcion.apply("uu/MM/dd"))+";"+fechaContable.format(funcion.apply("uu/MM/dd"))+";"+
                numeroCta+";"+oficina+";"+intBruto+";"+intNeto+";"+idCliente+";"+formaPg+";"+periodo+";"+descripcion+";"+
                aplicacion;
    }

}
