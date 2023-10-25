package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.TipSegmentoService;
import com.bdb.opaloshare.persistence.entity.TipSegmentoEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTipSegmento implements ItemWriter<TipSegmentoEntity> {

    @Autowired
    TipSegmentoService serviceTipSegmento;

    @Override
    public void write(List<? extends TipSegmentoEntity> items) throws Exception {
        serviceTipSegmento.agregarSegmento(items);
    }
}
