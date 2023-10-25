package com.bdb.moduleoplcovtdsa.mapper.infocdtreno;

import com.bdb.moduleoplcovtdsa.persistence.model.infocdtreno.InfoCdtReno;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisRenovaCdtEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InfoCdtRenoMapper {

    @Mapping(source = "cdtAct", target = "numCdtActual")
    @Mapping(source = "cdtAnt", target = "cdtAnt")
    @Mapping(source = "cdtOrigen", target = "cdtOrigen")
    @Mapping(source = "numRenova", target = "numReno")
    @Mapping(target = "fechaReno", ignore = true)
    InfoCdtReno toInfoCdtReno(HisRenovaCdtEntity hisRenovaCdtEntity, @Context RepositoryCDTSLarge repositoryCDTSLarge);

    @AfterMapping
    default void toInfoCdtReno(@MappingTarget InfoCdtReno target, HisRenovaCdtEntity hisRenovaCdtEntity,
                               @Context RepositoryCDTSLarge repositoryCDTSLarge) {
        target.setFechaReno(repositoryCDTSLarge.findByNumCdt(String.valueOf(hisRenovaCdtEntity.getCdtAct()))
                .stream()
                .map(HisCDTSLargeEntity::getFecha)
                .collect(Collectors.toList())
                .get(0)
                .toLocalDate());
    }
}
