package com.bdb.opaloshare.persistence.model.userbyoffice;

import com.bdb.opaloshare.persistence.entity.OficinaParWithRelationsDownEntity;
import com.bdb.opaloshare.persistence.entity.TipEstadosEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserByOffice implements Serializable {

    @Schema(description = "Descripción: Llave primaria de la base de datos", name = "item", type = "Long", required = true,
            example = "2037")
    private Long item;

    @Schema(description = "Descripción: número de oficina.", name = "nroOficina", type = "Long", required = true,
            example = "9973")
    @NotNull
//    @JsonIgnore
    private Long nroOficina;

    @NotNull
    @JsonIgnore
    private OficinaParWithRelationsDownEntity oficina;

    @Schema(description = "Descripción: Subsegmento.", name = "subsegmento", type = "String", required = true,
            example = "Vinculación")
    private String subsegmento;

    @Schema(description = "Descripción: Zona de oficina.", name = "zona", type = "String", required = true,
            example = "OIS")
    private String zona;

    @Schema(description = "Descripción: Cargo del usuario.", name = "cargo", type = "String", required = true,
            example = "Director Segmento")
    @NotBlank
    private String cargo;

    @Schema(description = "Descripción: Nombre del usuario.", name = "nombre", type = "String", required = true,
            example = "Álvaro Fernando Gómez García")
    @NotBlank
    private String nombre;

    @Schema(description = "Descripción: Correo del usuario.", name = "correo", type = "String", required = true,
            example = "agomez3@bancodebogota.com.co")
    @NotBlank
    private String correo;

    @Schema(description = "Descripción: Número celular del usuario.", name = "celular", type = "Long", required = true,
            example = "3152122884")
    private Long celular;

    @Schema(description = "Descripción: Número de conmutador del usuario", name = "conmutador", type = "String", required = true,
            example = "(1) 3320032")
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
//    @JsonIgnore
    private Integer estadoUsuario;

    @ManyToOne(targetEntity = TipEstadosEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ESTADO_USUARIO", insertable = false, updatable = false)
    @JsonIgnore
    private TipEstadosEntity estado;

    private static final long serialVersionUID = 1L;
}
