package com.bdb.oplbacthdiarios.mapper;

import com.bdb.opaloshare.persistence.entity.HisMigracionesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MapperMigraciones {

    @Mapping(target = "numCdt", source = "numCdt")
    @Mapping(target = "fecha", expression = ("java(java.time.LocalDate.now())"))
    @Mapping(target = "nuevaOficina", source = "officeNew")
    @Mapping(target = "oficina", source = "office")
    HisMigracionesEntity changesOffice(Long numCdt, Integer officeNew, Integer office);
}
