package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(AcuRenovatesorDownEntityId.class)
@Table(name = "OPL_ACU_RENOVATESOR_DOWN_TBL")
public class AcuRenovatesorDownEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SYS_CARGUE")
    private int sysCargue;

    @Id
    @Column(name = "CDT_CANCELADO")
    private Long cdtCancelado;

    @Id
    @Column(name = "CDT_REINVERTIDO")
    private Long cdtReinvertido;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FECHA_CANCELACION")
    private LocalDate fechaCancelacion;

    @Column(name = "FECHA_REINVERSION")
    private LocalDate fechaReinversion;

    @Column(name = "NRO_OFICINA")
    private Long nroOficina;

    @Column(name = "RETENCION_FUENTE")
    private BigDecimal retencionFuente;

    @Column(name = "INTERES_CANCELADO")
    private BigDecimal interesCancelado;

    @Column(name = "CAPITAL_CANCELADO")
    private BigDecimal capitalCancelado;

    @Column(name = "VALOR_REINVERTIDO")
    private BigDecimal valorReinvertido;

    public List<String> toArrayValues() {

        List<String> values = new ArrayList<>();

        //values.add(String.valueOf(sysCargue));
        values.add(String.valueOf(cdtCancelado));
        values.add(String.valueOf(cdtReinvertido));
        values.add(String.valueOf(idCliente));
        values.add(nombre);
        values.add(fechaCancelacion.toString());
        values.add(fechaReinversion.toString());
        values.add(String.valueOf(nroOficina));
        values.add(String.valueOf(retencionFuente));
        values.add(String.valueOf(capitalCancelado));
        values.add(String.valueOf(interesCancelado));
        values.add(String.valueOf(valorReinvertido));

        return values;
    }


}
