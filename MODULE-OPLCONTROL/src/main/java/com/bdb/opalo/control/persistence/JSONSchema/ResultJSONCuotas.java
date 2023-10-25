package com.bdb.opalo.control.persistence.JSONSchema;

import lombok.*;

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
