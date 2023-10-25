package com.bdb.oplbatchmensual.Schudeler.load;

import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;
import com.bdb.oplbatchmensual.controller.service.interfaces.InteresesMensualesService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileReportInterestMonth implements ItemWriter<SalIntefectDcvEntity> {

    @Autowired
    private InteresesMensualesService interesesMensualesService;

    @Override
    public void write(List<? extends SalIntefectDcvEntity> items) throws Exception {
        interesesMensualesService.generarReporteMensual(items);
    }
}
