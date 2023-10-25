package com.bdb.opalo.cofnal.persistance.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vwtasasvarlimitdays")
@org.hibernate.annotations.Immutable
public class TasaVariableCofnalMensualEntity implements Serializable {

    @Id
    @Column(name="fectasvar")
    private String fecha;

    @Column(name="tasvardtf")
    private Double tasaVarDtf;

    @Column(name="tasvaripc")
    private Double tasaVarIpc;

    @Column(name="tasvaribr")
    private Double tasaVarIbr;

    @Column(name="tasvaribrdiaria")
    private Double tasaVarIbrDiaria;

    @Column(name="tasvaribrtrimest")
    private Double tasaVarIbrTrimestral;

    @Column(name="tasvaribrsemestr")
    private Double tasaVarIbrSemestral;

}
