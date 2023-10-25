package com.bdb.opalogdoracle.mapper;

import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.entity.SalPgdigitalDownEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperRechazosRenovacion {

    @Mapping(target = "codIsin", source = "salPgDownEntity.codIsin")
    @Mapping(target = "tipId", source = "salPgDownEntity.codId")
    @Mapping(target = "numCdt", source = "salPgDownEntity.numCdt")
    @Mapping(target = "numTit", source = "salPgDownEntity.numTit")
    @Mapping(target = "nomTit", source = "salPgDownEntity.nomTit")
    @Mapping(target = "capPg", source = "salPgDownEntity.capPg")
    @Mapping(target = "intBruto", source = "salPgDownEntity.intBruto")
    @Mapping(target = "rteFte", source = "salPgDownEntity.rteFte")
    @Mapping(target = "intNeto", source = "salPgDownEntity.intNeto")
    @Mapping(target = "totalPagar", source = "salPgDownEntity.totalPagar")
    @Mapping(target = "tipCta", expression = "java(descripcionTipoCuenta(infoCtaClienteModel.getTipoCta()))")
    @Mapping(target = "nroCta", source = "infoCtaClienteModel.numCta")
    @Mapping(target = "oficina", source = "salPgDownEntity.nroOficina")
    @Mapping(target = "tipoTran", constant = "2")
    SalPgdigitalDownEntity toSalPgdigitalDownEntity(SalPgDownEntity salPgDownEntity, InfoCtaClienteModel infoCtaClienteModel);

    default String descripcionTipoCuenta(Integer valor){
        return valor == 1 ? "CTA CC" : "CTA AH";
    }

}
