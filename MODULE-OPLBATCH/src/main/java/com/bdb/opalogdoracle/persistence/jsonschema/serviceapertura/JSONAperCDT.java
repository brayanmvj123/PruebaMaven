package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONAperCDT {

    // Condiciones del DT
    @NotNull
    @Valid
    private JSONCondCDT cdt;

    // Datos del cliente
    @NotNull
    @Valid
    private List<JSONClientDatos> cliente;

    // Transacciones de pago de Cdts Desmaterializados
    @NotNull
    @Valid
    private List<JSONPagCDTDes> transaccionesPagoCdt;
}
