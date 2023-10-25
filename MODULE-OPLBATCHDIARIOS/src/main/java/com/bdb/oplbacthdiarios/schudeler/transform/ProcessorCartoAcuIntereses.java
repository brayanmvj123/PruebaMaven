package com.bdb.oplbacthdiarios.schudeler.transform;

import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import com.bdb.opaloshare.persistence.entity.CarIntefectDcvEntity;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class ProcessorCartoAcuIntereses implements ItemProcessor<CarIntefectDcvEntity, AcuIntefectDcvEntity> {

    @Override
    public AcuIntefectDcvEntity process(CarIntefectDcvEntity item) throws Exception {
        AcuIntefectDcvEntity acuIntefectDcvEntity = new AcuIntefectDcvEntity();
        acuIntefectDcvEntity.setSysCargue(LocalDate.now());
        acuIntefectDcvEntity.setCodTransaccion(item.getCodTransaccion());
        acuIntefectDcvEntity.setFechaReal(item.getFechaReal());
        acuIntefectDcvEntity.setFechaContable(item.getFechaContable());
        acuIntefectDcvEntity.setOficina(item.getOficina());
        acuIntefectDcvEntity.setIntBruto(item.getIntBruto());
        acuIntefectDcvEntity.setIntNeto(item.getIntNeto());
        acuIntefectDcvEntity.setIdCliente(item.getIdCliente());
        acuIntefectDcvEntity.setPeriodo(item.getPeriodo());
        acuIntefectDcvEntity.setDescripcion(item.getDescripcion());
        acuIntefectDcvEntity.setAplicacion(item.getAplicacion());
        acuIntefectDcvEntity.setNumeroCta(item.getNumeroCta());
        return acuIntefectDcvEntity;
    }

}
