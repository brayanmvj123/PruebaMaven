package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AjusLiqDiaEntityPk implements Serializable {

    private Long oplPgTblNumCdt;

    private String oplPgTblCodIsin;

    private String oplPgTblCtaInv;

    private String oplPgTblNumTit;

    private static final long serialVersionUID = 1L;
}
