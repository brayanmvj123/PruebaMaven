package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HisRenovacionPk implements Serializable {

//    private Long oplCdtxctainvTblNumCdt;

    private Long oplCdtxctainvTblOplCtainvTblNumCta;

    private String oplCdtxctainvTblOplCodIsin;

}
