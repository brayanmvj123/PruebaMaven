package com.bdb.opalogdoracle.mapper;

import com.bdb.opalogdoracle.persistence.model.NuevasCondicionesCdtModel;
import com.bdb.opalogdoracle.persistence.model.ReAperturaModel;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperReApertura {

    @Mapping(target = "nuevasCondicionesCdtModel", source = "nuevasCondicionesCdtModel")
    @Mapping(target = "numTit", source = "numTit")
    @Mapping(target = "tipId", source = "tipId")
    @Mapping(target = "cdt", source = "cdt")
    @Mapping(target = "tasaNominal", source = "tasaNominal")
    @Mapping(target = "tasaEfectiva", source = "tasaEfectiva")
    @Mapping(target = "capital", source = "capital")
    @Mapping(target = "codigoCut", source = "codigoCut")
    @Mapping(target = "host", source = "host")
    ReAperturaModel reAperturaModel(NuevasCondicionesCdtModel nuevasCondicionesCdtModel,
                                    Long numTit,
                                    Long tipId,
                                    HisCDTSLargeEntity cdt,
                                    String resultSimuFechVen,
                                    BigDecimal tasaNominal,
                                    BigDecimal tasaEfectiva,
                                    BigDecimal capital,
                                    String codigoCut,
                                    List<ParEndpointDownEntity> host);

}
