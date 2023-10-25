package com.bdb.moduleoplcancelaciones.controller.service.implement;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.CutService;
import com.bdb.opaloshare.controller.service.interfaces.CDTVencidoDTO;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@CommonsLog
public class CutServiceImpl implements CutService {

    @Autowired
    private RepositoryCDTSCancelacion repositoryCDTSCancelacion;

    @Override
    public void crearCodCut(CDTVencidoDTO cdt, String pago) {

        HisCDTSCancelationEntity hisCDTSCancelationEntity = new HisCDTSCancelationEntity(cdt.getCodIsin(),
                cdt.getNumCdt(),
                cdt.getNumTit(), pago, LocalDateTime.now());
        repositoryCDTSCancelacion.save(hisCDTSCancelationEntity);
    }

}
