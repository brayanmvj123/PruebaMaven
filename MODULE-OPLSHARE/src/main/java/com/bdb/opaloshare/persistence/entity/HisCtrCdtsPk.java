package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HisCtrCdtsPk implements Serializable {

    private Long numCdt;

    private String numTit;

    private Long parControlesEntity;

    private static final long serialVersionUID = 1L;
}
