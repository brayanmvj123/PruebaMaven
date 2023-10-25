package com.bdb.oplbacthdiarios.schudeler.transform;

import com.bdb.opaloshare.persistence.entity.CarIntefectDcvEntity;
import com.bdb.oplbacthdiarios.persistence.model.CarInteresesModel;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class ProcessorCarIntereses implements ItemProcessor<CarInteresesModel, CarIntefectDcvEntity> {

    @Override
    public CarIntefectDcvEntity process(CarInteresesModel item) throws Exception {
        CarIntefectDcvEntity carInteresesEntity = new CarIntefectDcvEntity();
        carInteresesEntity.setCodTransaccion(item.getCodTrans());
        Function<String,DateTimeFormatter> funcion = DateTimeFormatter::ofPattern;
        carInteresesEntity.setFechaContable(LocalDate.parse(item.getFechaContable(),funcion.apply("uu/MM/dd")));
        carInteresesEntity.setFechaReal(LocalDate.parse(item.getFechaReal(),funcion.apply("uu/MM/dd")));
        carInteresesEntity.setNumeroCta(item.getNumCta());
        carInteresesEntity.setOficina(item.getOficinaRecep());
        carInteresesEntity.setIntBruto(item.getInteresBruto());
        carInteresesEntity.setIntNeto(item.getInteresNeto());
        carInteresesEntity.setIdCliente(item.getNumTit());
        carInteresesEntity.setFormaPg(item.getFormaPago());
        carInteresesEntity.setPeriodo(item.getPeriodo());
        carInteresesEntity.setDescripcion(item.getTexto());
        carInteresesEntity.setAplicacion(item.getSeccOrigen());
        return carInteresesEntity;
    }
}
