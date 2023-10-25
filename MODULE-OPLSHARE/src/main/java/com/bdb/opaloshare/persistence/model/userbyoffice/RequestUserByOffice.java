package com.bdb.opaloshare.persistence.model.userbyoffice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserByOffice implements Serializable {

    @Schema(description = "Descripción: Número de oficina.", name = "nroOficina", type = "Long", required = true,
            example = "4")
    @NotNull
    private Long nroOficina;

    @Schema(description = "Descripción: Subsegmento.", name = "subsegmento", type = "String", required = true,
            example = "Empresarial")
    private String subsegmento;

    @Schema(description = "Descripción: Zona de oficina.", name = "zona", type = "String", required = true,
            example = "Nororiente Empresarial")
    private String zona;

    @Schema(description = "Descripción: Cargo del usuario.", name = "cargo", type = "String", required = true,
            example = "Desarrollador de software")
    @NotBlank
    private String cargo;

    @Schema(description = "Descripción: Nombre del usuario.", name = "nombre", type = "String", required = true,
            example = "Juan Diaz")
    @NotBlank
    @NotBlank
    private String nombre;

    @Schema(description = "Descripción: Correo del usuario.", name = "correo", type = "String", required = true,
            example = "juand@bancodebogota.com.co")
    @NotBlank
    private String correo;

    @Schema(description = "Descripción: Número celular del usuario.", name = "celular", type = "Long", required = true,
            example = "3152122888")
    private Long celular;

    @Schema(description = "Descripción: Número de conmutador del usuario", name = "conmutador", type = "String", required = true,
            example = "(1) 3320033")
    private String conmutador;

    @Schema(description = "Descripción: Número de extensión del usuario.", name = "ext", type = "Integer", required = true,
            example = "52300")
    private Integer ext;

    @Schema(description = "Descripción: Número de oficina.", name = "ciudad", type = "String", required = true,
            example = "Bogotá")
    private String ciudad;

    @Schema(description = "Descripción: Número de oficina.", name = "csc", type = "Integer", required = true,
            example = "571")
    private Integer csc;

    @Schema(description = "Descripción: Número de oficina.", name = "estadoUsuario", type = "Integer", required = true,
            example = "5")
    private Integer estadoUsuario;

    private static final long serialVersionUID = 1L;
}
