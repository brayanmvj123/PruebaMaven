package com.bdb.opalo.control.controller.service.implement;

import com.bdb.opalo.control.controller.service.interfaces.ControlCdtService;
import com.bdb.opalo.control.controller.service.interfaces.ControlCondicionesService;
import com.bdb.opalo.control.controller.service.interfaces.MediadorService;
import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.exception.ControlCdtsException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class MediadorImpl implements MediadorService {

    private final ControlCdtService controlCdtService;
    private final ControlCondicionesService controlCondicionesService;

    public MediadorImpl(ControlCdtService controlCdtService, ControlCondicionesService controlCondicionesService) {
        this.controlCdtService = controlCdtService;
        this.controlCondicionesService = controlCondicionesService;
    }

    @Override
    public boolean mediador(String version, ControlCdtDto controlCdtDto) throws ControlCdtsException {
        return version.equals("v1") ? controlCdtService.controlCdtMarcacion(controlCdtDto) :
                controlCondicionesService.controlCondicinoesCdt(controlCdtDto);
    }

}
