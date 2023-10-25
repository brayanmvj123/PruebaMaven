package com.bdb.opalossqls.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.DataCdtCanceladoDTO;
import com.bdb.opaloshare.controller.service.interfaces.DataCdtRenovadoDTO;
import com.bdb.opalossqls.controller.service.interfaces.CdtTesoreriaService;
import com.bdb.opalossqls.persistence.repository.RepositoryCdtTesoreria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CdtTesoreriaServiceImpl implements CdtTesoreriaService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final RepositoryCdtTesoreria cdtTesoRepo;

    public CdtTesoreriaServiceImpl(RepositoryCdtTesoreria cdtTesoRepo) {
        this.cdtTesoRepo = cdtTesoRepo;
    }

    @Override
    public List<DataCdtRenovadoDTO> getDiarioCdtRenovado() {

        logger.info("start getDiarioCdtRenovado()...");
        List<DataCdtRenovadoDTO> cdtRenovados = null;

        try {

            cdtRenovados = cdtTesoRepo.loadInfoCdtRenovados();

        } catch (Exception e) {
            logger.error("Error en getDiarioCdtRenovado: []", e);
        }

        return cdtRenovados;

    }

    @Override
    public List<DataCdtCanceladoDTO> getDiarioCdtCancelado() {

        logger.info("start getDiarioCdtCancelado()...");
        List<DataCdtCanceladoDTO> cdtCancelados = null;

        try {

            cdtCancelados = cdtTesoRepo.loadInfoCdtCancelados();
            logger.info("CANTIDAD DE REGISTROS: {}", cdtCancelados.size());

        } catch (Exception e) {
            logger.error("Error en getDiarioCdtCancelado: []", e);
        }

        return cdtCancelados;

    }


}
