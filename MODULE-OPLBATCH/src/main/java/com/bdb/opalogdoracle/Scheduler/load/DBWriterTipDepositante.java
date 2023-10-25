package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.TipDepositanteService;
import com.bdb.opaloshare.persistence.entity.TipDepositanteEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTipDepositante implements ItemWriter<TipDepositanteEntity> {

    @Autowired
    TipDepositanteService serviceDepositante;

    @Override
    public void write(List<? extends TipDepositanteEntity> items) throws Exception {
        serviceDepositante.agregarDepositante(items);
    }
}
