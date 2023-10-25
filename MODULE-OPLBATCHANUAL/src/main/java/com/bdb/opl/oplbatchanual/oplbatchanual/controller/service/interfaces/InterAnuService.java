package com.bdb.opl.oplbatchanual.oplbatchanual.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;

import java.time.LocalDate;
import java.util.List;

public interface InterAnuService {

    void almacenar(List<? extends SalIntefectDcvEntity> items);

    LocalDate[] fechasConsulta();

    void generarReporteAnual(List<? extends SalIntefectDcvEntity> items) throws ErrorFtps;

    void eliminarRegistrosSalida();

}
