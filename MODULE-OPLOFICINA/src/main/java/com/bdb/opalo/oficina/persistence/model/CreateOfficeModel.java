package com.bdb.opalo.oficina.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfficeModel implements Serializable {

    private String fillerOne;

    /**
     * CODIGO DE LA OFICINA.
     */
    private Integer office;

    private String fillerTwo;

    /**
     * NOMBRE DE LA OFICINA.
     */
    private String nameOffice;

    private String fillerFour;

    /**
     * CODIGO CSC.
     */
    private Integer codCsc;

    private String fillerFive;

    /**
     * INDICADOR CEO (ARCHIVO SAVINGS).
     */
    private Integer indCeo;

    private String fillerSix;

}
