package com.bdb.opalo.batchocasional.persistence.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TasaVariableFileModel implements Serializable {
    private String id;
    private String fecha;
    private Double dtf;
    private Double fbor;
    private Double usur;
    private Double uvr;
    private Double uvr_real;
    private Double uvr_proyectado;
    private Double ibrDiaria;
    private Double ibrMensual;
    private Double ibrTrimestral;
    private Double ibrSemestral;
    private Double ipc;

}