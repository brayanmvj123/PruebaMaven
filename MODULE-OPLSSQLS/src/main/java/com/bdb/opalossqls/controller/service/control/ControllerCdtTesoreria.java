package com.bdb.opalossqls.controller.service.control;

import com.bdb.opaloshare.controller.service.interfaces.DataCdtCanceladoDTO;
import com.bdb.opaloshare.controller.service.interfaces.DataCdtRenovadoDTO;
import com.bdb.opalossqls.controller.service.implement.CdtTesoreriaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerCdtTesoreria {

    final CdtTesoreriaServiceImpl cdtTesor;

    public ControllerCdtTesoreria(CdtTesoreriaServiceImpl cdtTesor) {
        this.cdtTesor = cdtTesor;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("cdtRenovado")
    public List<DataCdtRenovadoDTO> retornoCdtReinvertidos() {

        logger.info("start controller retornoCdtReinvertidos()...");
        return cdtTesor.getDiarioCdtRenovado();

    }

    @GetMapping("cdtCancelado")
    public List<DataCdtCanceladoDTO> retornoCdtCancelados() {

        logger.info("start controller retornoCdtCancelados()...");
        return cdtTesor.getDiarioCdtCancelado();

    }


}
