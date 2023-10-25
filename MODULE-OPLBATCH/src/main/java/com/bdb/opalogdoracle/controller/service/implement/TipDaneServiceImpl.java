package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.TipDaneService;
import com.bdb.opaloshare.persistence.entity.TipDaneEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipDane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class TipDaneServiceImpl implements TipDaneService {

    @Autowired
    RepositoryTipDane repoDane;

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    @Override
    public void agregarDane(List<? extends TipDaneEntity> items) {
        repoDane.saveAll(items);
    }

    @Override
    public void guardarDane(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public ByteArrayOutputStream cargarDane() {
        return archivo;
    }
}
