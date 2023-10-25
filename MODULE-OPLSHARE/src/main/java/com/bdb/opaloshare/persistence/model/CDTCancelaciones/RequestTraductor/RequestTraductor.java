package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestTraductor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestTraductor implements Serializable {

    private Long numCdt;
    private String codIsin;
    private Long ctaInv;
}
