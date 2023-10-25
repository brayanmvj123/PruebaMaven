package com.bdb.opalo.control.model.mapper;

import com.bdb.opalo.control.persistence.dto.CondicionesDto;
import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.dto.CuentaClienteDto;
import com.bdb.opalo.control.persistence.dto.ParametrosControlCdtDto;
import com.bdb.opaloshare.persistence.entity.HisCondicionCdtsEntity;
import com.bdb.opaloshare.persistence.entity.HisCtaCliEntity;
import com.bdb.opaloshare.persistence.entity.HisCtrCdtsEntity;
import com.bdb.opaloshare.persistence.entity.ParControlesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ControlNovedadMapper {

    @Mapping(target = "numCdt", source = "controlCdtDto.parametros.numCdt")
    @Mapping(target = "numTit", source = "controlCdtDto.parametros.numTit")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion")
    @Mapping(target = "codCut", source = "controlCdtDto.parametros.codCut")
    @Mapping(target = "novedadV", source = "controlCdtDto.parametros.novedadV")
    @Mapping(target = "fechaModificacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "descripcion", source = "controlCdtDto.parametros.descripcion")
    @Mapping(target = "parControlesEntity", expression = "java(new com.bdb.opaloshare.persistence.entity.ParControlesEntity(controlCdtDto.getParametros().getCodMarcacion()))")
    @Mapping(target = "hisCondicionCdtsEntity", expression = "java(lista(controlCdtDto.getCondiciones(), controlCdtDto.getParametros()))")
    @Mapping(target = "hisCtaCliEntities", expression = "java(setHisCtaCli(controlCdtDto.getCuentaCliente(), controlCdtDto.getParametros()))")
    HisCtrCdtsEntity toHisCtrCdtsEntity(ControlCdtDto controlCdtDto, LocalDateTime fechaCreacion);

    default Set<HisCondicionCdtsEntity> lista(CondicionesDto condicionesDto, ParametrosControlCdtDto parametrosControlCdtDto) {
        Set<HisCondicionCdtsEntity> lista = new HashSet<>();
        lista.add(new HisCondicionCdtsEntity(new BigDecimal(condicionesDto.getCapital()), condicionesDto.getRendimientos(),
                condicionesDto.getPlazo(), condicionesDto.getTipPlazo(), condicionesDto.getBase(),
                new HisCtrCdtsEntity(parametrosControlCdtDto.getNumCdt(), parametrosControlCdtDto.getNumTit(),
                        new ParControlesEntity(parametrosControlCdtDto.getCodMarcacion()))));
        return lista;
    }

    default Set<HisCtaCliEntity> setHisCtaCli(CuentaClienteDto cuentaClienteDto, ParametrosControlCdtDto parametrosControlCdtDto) {
        Set<HisCtaCliEntity> lista = new HashSet<>();
        lista.add(new HisCtaCliEntity(cuentaClienteDto.getNumCta(), cuentaClienteDto.getTipCta(), cuentaClienteDto.getOfiOrigen(),
                new HisCtrCdtsEntity(parametrosControlCdtDto.getNumCdt(), parametrosControlCdtDto.getNumTit(),
                        new ParControlesEntity(parametrosControlCdtDto.getCodMarcacion()))));
        return lista;
    }

}
