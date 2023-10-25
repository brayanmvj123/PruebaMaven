package com.bdb.opaloshare.persistence.model.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelCrucePatrimonioRenaut {

    private String numCdt;

    private String codIsin;

    private String tipId;

    private String numTit;

    private String nomTit;

    private String intBruto;

    private String rteFte;

    private String intNeto;

    private String capPg;

}
