package com.bdb.opalogdoracle.Scheduler.transform;

import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import org.springframework.batch.item.ItemProcessor;

public class ProcessorOficina implements ItemProcessor<OficinaParDownEntity,OficinaParDownEntity> {

    @Override
    public OficinaParDownEntity process(OficinaParDownEntity item) throws Exception {

        return null;
    }

}
