package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import com.bdb.oplbacthdiarios.controller.service.interfaces.InteresesDiariosService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterCartoAcuIntereses implements ItemWriter<AcuIntefectDcvEntity> {

    @Autowired
    private InteresesDiariosService interesesDiariosService;

    @Override
    public void write(List<? extends AcuIntefectDcvEntity> items) throws Exception {
        interesesDiariosService.almacenarDataAcumulada(items);
    }
}
