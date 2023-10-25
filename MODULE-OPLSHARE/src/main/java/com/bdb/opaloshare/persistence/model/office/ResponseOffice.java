package com.bdb.opaloshare.persistence.model.office;

import com.bdb.opaloshare.persistence.entity.HisUsuarioxOficinaEntity;
import com.bdb.opaloshare.persistence.entity.TipEstadosEntity;
import com.bdb.opaloshare.persistence.entity.TipOficinaParDownEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOffice implements Serializable {

    @Schema(description = "Descripción: Número de oficina.", name = "nroOficina", type = "Integer", required = true,
            example = "4")
    private Integer nroOficina;

    @Schema(description = "Descripción: Descripción de oficina.", name = "descOficina", type = "String", required = true,
            example = "CALLE 53 EXITO")
    private String descOficina;

    @Schema(description = "Descripción: Tipo de oficina: 1=OFICINA, 2=PYME, 3=CEO, 4=PREMIUM, 5=CENTRO DE SERVICIOS, " +
            "6=CENTRO DE COSTOS", name = "oplTipoficinaTblTipOficina", type = "Integer", required = true,
            example = "1")
    @JsonIgnore
    private Integer oplTipoficinaTblTipOficina;

//    @JsonIgnore
    private TipOficinaParDownEntity tipoficina;

    @Schema(description = "Descripción: Número de oficina.", name = "oplOficinaTblnroOficina", type = "Integer", required = true,
            example = "0")
    @JsonIgnore
    private Integer oplOficinaTblnroOficina;

    @Schema(description = "Descripción: Tipo de estado: Estados 1= procesado, 2=enviado, 3=finalizado, 4=oficina activa," +
            " 5= oficina cerrada", name = "oplEstadosTblTipEstado", type = "Integer", required = true,
            example = "4")
    @JsonIgnore
    private Integer oplEstadosTblTipEstado;

    @JsonIgnore
    private TipEstadosEntity tipoEstado;

    @JsonIgnore
    private List<HisUsuarioxOficinaEntity> usuarios;

    private static final long serialVersionUID = 1L;
}
