package com.bdb.opaloapcdt.persistence.JSONSchema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
