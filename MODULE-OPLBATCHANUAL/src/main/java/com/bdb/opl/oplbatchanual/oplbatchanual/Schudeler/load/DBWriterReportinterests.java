package com.bdb.opl.oplbatchanual.oplbatchanual.Schudeler.load;

import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;
import com.bdb.opl.oplbatchanual.oplbatchanual.controller.service.interfaces.InterAnuService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterReportinterests implements ItemWriter<SalIntefectDcvEntity> {

    @Autowired
    private InterAnuService interAnuService;

    @Override
    public void write(List<? extends SalIntefectDcvEntity> items) throws Exception {
        System.out.println("ENTROOOOO AL ESCRIBIR: "+items.toString());
        interAnuService.almacenar(items);
    }
}
