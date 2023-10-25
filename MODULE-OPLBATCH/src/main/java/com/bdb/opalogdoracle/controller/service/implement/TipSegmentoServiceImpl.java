package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.TipSegmentoService;
import com.bdb.opaloshare.persistence.entity.TipSegmentoEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipDepositante;
import com.bdb.opaloshare.persistence.repository.RepositoryTipSegmento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class TipSegmentoServiceImpl implements TipSegmentoService {

    @Autowired
    RepositoryTipSegmento repositoryTipSegmento;

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    @Override
    public void agregarSegmento(List<? extends TipSegmentoEntity> items) {
        repositoryTipSegmento.saveAll(items);
    }

    @Override
    public void guardarSegmento(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public ByteArrayOutputStream cargarSegmento() {
        return archivo;
    }

}
