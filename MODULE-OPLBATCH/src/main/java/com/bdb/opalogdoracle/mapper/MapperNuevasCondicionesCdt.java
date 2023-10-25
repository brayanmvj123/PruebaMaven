package com.bdb.opalogdoracle.mapper;

import com.bdb.opalogdoracle.persistence.model.NuevasCondicionesCdtModel;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisCondicionCdtsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface MapperNuevasCondicionesCdt {

    @Mapping(target = "capital", source = "capital")
    @Mapping(target = "rendimientos", source = "rendimientos")
    @Mapping(target = "tipPlazo", source = "condicionCdts.tipPlazo")
    @Mapping(target = "plazo", source = "condicionCdts.plazo")
    @Mapping(target = "base", expression = "java(homologacionBaseNuevasCondiciones(condicionCdts.getBase()))")
    @Mapping(target = "codProd", expression = "java(validarCodigoProducto(condicionCdts.getTipPlazo()))")
    NuevasCondicionesCdtModel nuevasCondiciones(BigDecimal capital, BigDecimal rendimientos,
                                                HisCondicionCdtsEntity condicionCdts);
    @Mapping(target = "capital", source = "capital")
    @Mapping(target = "rendimientos", source = "rendimientos")
    @Mapping(target = "tipPlazo", source = "hisCDTSLargeEntity.oplTipplazoTblTipPlazo")
    @Mapping(target = "plazo", source = "hisCDTSLargeEntity.plazo")
    @Mapping(target = "base", expression = "java(homologacionBaseCondicionesActuales(hisCDTSLargeEntity.getOplTipbaseTblTipBase()))")
    @Mapping(target = "codProd", source = "hisCDTSLargeEntity.codProd")
    NuevasCondicionesCdtModel condicionesActuales(BigDecimal capital, BigDecimal rendimientos,
                                                HisCDTSLargeEntity hisCDTSLargeEntity);

    default int homologacionBaseNuevasCondiciones(int valor){
        return valor == 360 ? 1 : 2;
    }
    default int homologacionBaseCondicionesActuales(int valor){
        return valor == 1 ? 2 : 1;
    }
    default String validarCodigoProducto(Integer tipPlazo){
        return tipPlazo == 1 ? "3002" : "3001";
    }

}
