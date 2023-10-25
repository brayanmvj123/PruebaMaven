package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.OficinaService;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryOficina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class OficinaServiceImpl implements OficinaService {

    @Autowired
    RepositoryOficina repoOficina;

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    @Override
    public void agregarOficina(List<? extends OficinaParDownEntity> items) {
        repoOficina.saveAll(items);
    }

    @Override
    public void guardarOficina(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public ByteArrayOutputStream cargarOficina() {
        return archivo;
    }

}
