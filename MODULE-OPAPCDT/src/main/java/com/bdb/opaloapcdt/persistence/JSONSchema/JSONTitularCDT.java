package com.bdb.opaloapcdt.persistence.JSONSchema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONTitularCDT {
    @NotNull
    private String id_cliente;
}
