package com.bdb.opalogdoracle.persistence.model;

import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReAperturaModel {

    private NuevasCondicionesCdtModel nuevasCondicionesCdtModel;
    private Long numTit;
    private Long tipId;
    private HisCDTSLargeEntity cdt;
    private String resultSimuFechVen;
    private BigDecimal tasaNominal;
    private BigDecimal tasaEfectiva;
    private BigDecimal capital;
    private String codigoCut;
    private List<ParEndpointDownEntity> host;

}
