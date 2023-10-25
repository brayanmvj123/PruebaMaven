package com.bdb.opalo.control.controller.service.interfaces;

import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.exception.ControlCdtsException;

public interface ControlCondicionesService {

    boolean controlCondicinoesCdt(ControlCdtDto controlCdtDto) throws ControlCdtsException;

}
