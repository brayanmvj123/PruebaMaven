package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AjusLiqSemEntityPk implements Serializable {

    private Long oplPgTblNumCdt;

    private String oplPgTblCodIsin;

    private String oplPgTblCtaInv;

    private String oplPgTblNumTit;

    private static final long serialVersionUID = 1L;
}
