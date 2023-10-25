package com.bdb.opalotasasfija.persistence.JSONSchema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResultJSONCuotas {
    private String interesBruto;
    private String retencionIntereses;
    private String interesNeto;
    private String interesBrutoPeriodo;
    private String retencionInteresesPeriodo;
    private String interesNetoPeriodo;
    private Double tasaNominal;
}
