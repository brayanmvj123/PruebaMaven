package com.bdb.opaloshare.persistence.model.email;

import com.bdb.opaloshare.persistence.entity.HisUsuarioxOficinaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmail implements Serializable {

    private Integer idOficina;

    private String nombre;

    private String motivo;

    private List<HisUsuarioxOficinaEntity> usuarios;

    private static final long serialVersionUID = 1L;
}
