package com.bdb.opaloshare.persistence.model.office;

import com.bdb.opaloshare.persistence.entity.HisUsuarioxOficinaEntity;
import com.bdb.opaloshare.persistence.entity.TipEstadosEntity;
import com.bdb.opaloshare.persistence.entity.TipOficinaParDownEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOfficeDetail implements Serializable {

    private Integer nroOficina;

    private String descOficina;

    @JsonIgnore
    private Integer oplTipoficinaTblTipOficina;

//    @JsonIgnore
    private TipOficinaParDownEntity tipoficina;

//    @JsonIgnore
    private Integer oplOficinaTblnroOficina;

    @JsonIgnore
    private Integer oplEstadosTblTipEstado;

//    @JsonIgnore
    private TipEstadosEntity tipoEstado;

//    @JsonIgnore
    private List<HisUsuarioxOficinaEntity> usuarios;

    private static final long serialVersionUID = 1L;
}
