package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.HisLoginService;
import com.bdb.opalogdoracle.persistence.model.PerfilEmailDto;
import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.opaloshare.util.Constants;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DBWriterBloquearUsuario implements ItemWriter<HisLoginDownEntity> {

    @Autowired
    HisLoginService hisLoginService;

    @Autowired
    RepositoryParEndpointDown endpointRepo;

    @Autowired
    RepositoryTipVarentorno repoVarentorno;

    @Override
    public void write(List<? extends HisLoginDownEntity> items) throws Exception {
        items.forEach(data -> System.out.println(data.getItem()));
        System.out.println("ACTUALIZANDO EL USUARIO");
        hisLoginService.bloquearUsuario(items);
    }

}
