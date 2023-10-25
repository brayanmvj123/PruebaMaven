package com.bdb.moduleoplcovtdsa.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoCDT implements Serializable {

    private String codigo;
    private String descripcion;
    private String observaciones;
    private List<MarcacionesCDT> marcacionesCDT;

}
