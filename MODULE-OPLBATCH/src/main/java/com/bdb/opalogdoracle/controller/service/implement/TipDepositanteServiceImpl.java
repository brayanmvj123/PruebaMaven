package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.TipDepositanteService;
import com.bdb.opaloshare.persistence.entity.TipDepositanteEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipDepositante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class TipDepositanteServiceImpl implements TipDepositanteService {

    @Autowired
    RepositoryTipDepositante repoDepositante;

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    @Override
    public void agregarDepositante(List<? extends TipDepositanteEntity> items) {
        repoDepositante.saveAll(items);
    }

    @Override
    public void guardarDepositante(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public ByteArrayOutputStream cargarDepositante() {
        return archivo;
    }
    
}
