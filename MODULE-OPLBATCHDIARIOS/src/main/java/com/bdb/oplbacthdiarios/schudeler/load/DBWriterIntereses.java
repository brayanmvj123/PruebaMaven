package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.CarIntefectDcvEntity;
import com.bdb.oplbacthdiarios.controller.service.interfaces.InteresesDiariosService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterIntereses implements ItemWriter<CarIntefectDcvEntity> {

    @Autowired
    private InteresesDiariosService interesesDiariosService;

    @Override
    public void write(List<? extends CarIntefectDcvEntity> items) throws Exception {
        interesesDiariosService.almacenar(items);
    }

}
