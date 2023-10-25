package com.bdb.oplbacthdiarios.persistence.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarInteresesModel {
    
    private String codTrans;
    private String fechaReal;
    private String fechaContable;
    private String numCta;
    private Integer oficinaRecep;
    private BigDecimal interesBruto;
    private BigDecimal interesNeto;
    private String numTit;
    private String formaPago;
    private String periodo;
    private String texto;
    private String seccOrigen;
    private String filler;

}
