package com.bdb.platform.servfront.model.JSONSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonRequestUpdateSalPg implements Serializable {

    @Valid
    private List<JSONGetSalPgDiaria>  cdts = null;

    private static final long serialVersionUID = 1L;
}
