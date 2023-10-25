package com.bdb.opalo.mds.mapper;

import com.bdb.opaloshare.persistence.entity.TmpTasaSegEntity;
import com.bdb.opaloshare.persistence.entity.TmpTasasCdtMdsEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    default List<TmpTasasCdtMdsEntity> liststringToTmpTasasCdtMdsEntity(List<String> cadena) {
        return cadena.stream().parallel().map(items -> {
            List<String> item = Arrays.asList(items.split(";"));
            TmpTasasCdtMdsEntity tmpTasasCdtMdsEntity = new TmpTasasCdtMdsEntity();
            Function<String,String> function = dato -> dato.replaceAll("[^0-9]","");
            tmpTasasCdtMdsEntity.setIdentificador(Long.valueOf(item.get(0)));
            tmpTasasCdtMdsEntity.setNomProd(item.get(1));
            tmpTasasCdtMdsEntity.setCodProd(function.apply(item.get(2)));
            tmpTasasCdtMdsEntity.setTipPlazo(function.apply(item.get(3)));
            tmpTasasCdtMdsEntity.setPlazoMin(Integer.valueOf(item.get(4)));
            tmpTasasCdtMdsEntity.setPlazoMax(Integer.valueOf(item.get(5)));
            tmpTasasCdtMdsEntity.setMontoMin(Long.valueOf(item.get(6)));
            tmpTasasCdtMdsEntity.setMontoMax(Long.valueOf(item.get(7)));
            tmpTasasCdtMdsEntity.setSigno(item.get(8));
            tmpTasasCdtMdsEntity.setTasaSpread(new BigDecimal(item.get(9)));
            tmpTasasCdtMdsEntity.setFechaCarga(LocalDate.now());
            return tmpTasasCdtMdsEntity;
        }).collect(Collectors.toList());
    }

    default List<TmpTasaSegEntity> liststringToTmpTasaSegEntity(List<String> cadena) {
        return cadena.stream().parallel().map(items -> {
            List<String> item = Arrays.asList(items.split(";"));
            TmpTasaSegEntity tmpTasaSegEntity = new TmpTasaSegEntity();
            tmpTasaSegEntity.setIdentificador(Long.valueOf(item.get(0)));
            tmpTasaSegEntity.setNomProd(item.get(1));
            tmpTasaSegEntity.setSegmento(Integer.parseInt(item.get(2).split(" ")[0].trim()));
            tmpTasaSegEntity.setDesSegmento(item.get(2).split(" ")[1]);
            tmpTasaSegEntity.setPuntos(new BigDecimal(item.get(3)));
            return tmpTasaSegEntity;
        }).collect(Collectors.toList());
    }
}
