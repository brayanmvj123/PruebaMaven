package com.bdb.opalo.oficina.scheduler.load;

import com.bdb.opalo.oficina.controller.service.interfaces.CreateOfficesService;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CommonsLog
public class WriterCreateOffices implements ItemWriter<OficinaParDownEntity> {

    @Autowired
    CreateOfficesService createOfficesService;

    @Override
    public void write(List<? extends OficinaParDownEntity> items) throws Exception {
        log.info("SE INICIA EL PROCESO DE ESCRITURA A LA BD, CANTIDAD DE OFICINAS ALMACENAR: "+items.size());
        createOfficesService.saveOffice(items);
    }

}
