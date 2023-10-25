package com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseContingenciaCDT extends ResponseCdtProxVen {

    private BigDecimal valorRenovacion;
}
