package com.bdb.opalogdoracle.mapper;

import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MapperInfoCtaClient {

    @Mapping(target = "codIsin", source = "salRenautdigEntity.codIsin")
    @Mapping(target = "ctaInv", source = "hisTranpgEntity.nroPordDestino")
    @Mapping(target = "nombreBeneficiario", source = "salRenautdigEntity.nomTit")
    @Mapping(target = "numCdt", source = "salRenautdigEntity.numCdt")
    @Mapping(target = "numCta", source = "hisTranpgEntity.nroPordDestino")
    @Mapping(target = "numeroId", source = "salRenautdigEntity.numTit")
    @Mapping(target = "oficinaOrigen", source = "hisTranpgEntity.unidDestino")
    @Mapping(target = "oficinaRadic", source = "hisTranpgEntity.unidOrigen")
    @Mapping(target = "tipoCta", source = "hisTranpgEntity.oplTiptransTblTipTrasaccion")
    @Mapping(target = "tipoCtaInv", source = "hisTranpgEntity.oplTiptransTblTipTrasaccion")
    @Mapping(target = "tipoId", constant = "C")
    InfoCtaClienteModel infoCtaPago(HisTranpgEntity hisTranpgEntity, SalRenautdigEntity salRenautdigEntity);
}
