package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.CtaInvSecCarEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvSecCar;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CommonsLog
public class DBWriterCtaInvT2 implements ItemWriter<CtaInvSecCarEntity> {

    @Autowired
    RepositoryCtaInvSecCar repositoryCtaInvSecCar;

    @Override
    public void write(List<? extends CtaInvSecCarEntity> items) throws Exception {
        repositoryCtaInvSecCar.saveAll(items);
    }
}
