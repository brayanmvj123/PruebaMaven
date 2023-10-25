package com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResultSimuladorCuota implements Serializable {

    private String interesBruto;
    private String retencionIntereses;
    private String interesNeto;
    private String interesBrutoPeriodo;
    private String retencionInteresesPeriodo;
    private String interesNetoPeriodo;
    private Double factor;
    private Double tasaNominal;
    private Double tasaEfectiva;

}
