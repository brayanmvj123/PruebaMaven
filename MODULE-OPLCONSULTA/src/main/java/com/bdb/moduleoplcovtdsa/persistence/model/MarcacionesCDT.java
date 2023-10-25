package com.bdb.moduleoplcovtdsa.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionesCDT implements Serializable {

    private Long codMarca;
    private String descMarca;
    private Integer novedad;
    private Integer disponible;

}
