package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HisCDTSCancelationPk implements Serializable {

    private String codIsin;
    private Long numCdt;
    private String numTit;

}
