package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HisPgEntityPk implements Serializable {

    private Long numCdt;

    private String codIsin;

    private String ctaInv;

    private String numTit;

    private static final long serialVersionUID = 1L;
}
