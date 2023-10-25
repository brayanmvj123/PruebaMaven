package com.bdb.opl.oplbatchanual.oplbatchanual.Schudeler.transform;

import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;
import org.springframework.batch.item.ItemProcessor;

public class processorDataInt implements ItemProcessor<AcuIntefectDcvEntity, SalIntefectDcvEntity> {

    @Override
    public SalIntefectDcvEntity process(AcuIntefectDcvEntity item) throws Exception {
        SalIntefectDcvEntity salIntefectDcvEntity = new SalIntefectDcvEntity();
        salIntefectDcvEntity.setCodTransaccion(item.getCodTransaccion());
        salIntefectDcvEntity.setFechaReal(item.getFechaReal());
        salIntefectDcvEntity.setFechaContable(item.getFechaContable());
        salIntefectDcvEntity.setOficina(item.getOficina());
        salIntefectDcvEntity.setIntBruto(item.getIntBruto());
        salIntefectDcvEntity.setIntNeto(item.getIntNeto());
        salIntefectDcvEntity.setIdCliente(item.getIdCliente());
        salIntefectDcvEntity.setFormaPg("EFECTIVO");
        salIntefectDcvEntity.setPeriodo(item.getPeriodo());
        salIntefectDcvEntity.setDescripcion(item.getDescripcion());
        salIntefectDcvEntity.setAplicacion(item.getAplicacion());
        return salIntefectDcvEntity;
    }

}
