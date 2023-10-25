package com.bdb.opalo.oficina.scheduler.load;

import com.bdb.opaloshare.persistence.entity.CarCierreOfiEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarCierreOfi;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CommonsLog
public class WriterClosingOffices implements ItemWriter<CarCierreOfiEntity> {

    @Autowired
    RepositoryCarCierreOfi repositoryCarCierreOfi;

    @Override
    public void write(List<? extends CarCierreOfiEntity> items) throws Exception {
        log.info("CANTIDAD LISTA: "+items.size());
        repositoryCarCierreOfi.saveAll(items);
    }
}
