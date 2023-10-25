package com.bdb.opalo.oficina.scheduler.processor;

import com.bdb.opalo.oficina.controller.service.interfaces.CreateOfficesService;
import com.bdb.opalo.oficina.persistence.model.CreateOfficeModel;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessorCreateOffice implements ItemProcessor<CreateOfficeModel, OficinaParDownEntity> {

    @Autowired
    CreateOfficesService createOfficesService;

    @Override
    public OficinaParDownEntity process(CreateOfficeModel item) throws Exception {
        return createOfficesService.validateOffice(item);
    }

}
