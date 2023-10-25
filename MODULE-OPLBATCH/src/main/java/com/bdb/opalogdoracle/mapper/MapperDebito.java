package com.bdb.opalogdoracle.mapper;

import com.bdb.opalogdoracle.persistence.model.debito.request.DebitoRequest;
import com.bdb.opaloshare.persistence.entity.HisCtaCliEntity;
import com.bdb.opaloshare.persistence.entity.HisCtrCdtsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface MapperDebito {

    @Mapping(target = "tipoId", constant = "C")
    @Mapping(target = "numeroId", source = "hisCtrCdtsEntity.numTit")
    @Mapping(target = "numCdt", source = "hisCtrCdtsEntity.numCdt")
    @Mapping(target = "oficinaOrigen", source = "hisCtaCliEntity.ofiOrigen")
    @Mapping(target = "numCta", source = "hisCtaCliEntity.numCta")
    @Mapping(target = "tipoCta", source = "hisCtaCliEntity.tipCta")
    @Mapping(target = "valor", source = "capital")
    DebitoRequest debitoRequest(BigDecimal capital, HisCtrCdtsEntity hisCtrCdtsEntity, HisCtaCliEntity hisCtaCliEntity);

}
