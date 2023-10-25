package com.bdb.opalossqls.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.interfaces.DataCdtCanceladoDTO;
import com.bdb.opaloshare.controller.service.interfaces.DataCdtRenovadoDTO;

import java.util.List;

public interface CdtTesoreriaService {

    List<DataCdtRenovadoDTO> getDiarioCdtRenovado();
    List<DataCdtCanceladoDTO> getDiarioCdtCancelado();

}
