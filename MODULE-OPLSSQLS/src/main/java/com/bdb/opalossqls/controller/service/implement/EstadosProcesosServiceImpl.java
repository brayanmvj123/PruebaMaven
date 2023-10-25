package com.bdb.opalossqls.controller.service.implement;

import com.bdb.opalossqls.controller.service.interfaces.EstadosProcesosService;
import com.bdb.opalossqls.persistence.entity.StatusInsertOpl;
import com.bdb.opalossqls.persistence.repository.RepositoryStatusInsertOpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstadosProcesosServiceImpl implements EstadosProcesosService {

    @Autowired
    private RepositoryStatusInsertOpl repoStatus;

    @Override
    public void llenarEstado(String estado) {
        StatusInsertOpl status = new StatusInsertOpl();
        status.setEstado(estado);
        status.setFecha(LocalDate.now());
        repoStatus.save(status);
    }

}
