package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.TipDaneService;
import com.bdb.opaloshare.persistence.entity.TipDaneEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTipDane implements ItemWriter<TipDaneEntity> {

    @Autowired
    TipDaneService serviceDane;

    @Override
    public void write(List<? extends TipDaneEntity> items) throws Exception {
        serviceDane.agregarDane(items);
    }
}
