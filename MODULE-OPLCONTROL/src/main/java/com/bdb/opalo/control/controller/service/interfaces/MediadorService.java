package com.bdb.opalo.control.controller.service.interfaces;

import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.exception.ControlCdtsException;

public interface MediadorService {

    boolean mediador(String version, ControlCdtDto controlCdtDto) throws ControlCdtsException;
}
