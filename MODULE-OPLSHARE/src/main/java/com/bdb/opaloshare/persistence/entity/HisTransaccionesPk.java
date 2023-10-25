package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HisTransaccionesPk implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item;

//    private Long oplCdtxctainvTblNumCdt;
//
//    private Long oplCdtxctainvTblOplCtainvTblNumCta;
//
//    private String oplCdtxctainvTblCodIsin;

    private static final long serialVersionUID = 1L;
}
