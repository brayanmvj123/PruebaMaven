package com.bdb.opaloshare.persistence.model.CDTCancelaciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicAccountInformationModel {

    private Long numCta;

    private String nomCta;

    private Integer relCta;

    private Integer estCta;

}
