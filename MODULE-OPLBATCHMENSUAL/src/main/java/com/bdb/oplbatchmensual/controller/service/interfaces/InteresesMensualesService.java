package com.bdb.oplbatchmensual.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface InteresesMensualesService {

    void almacenar(List<? extends SalIntefectDcvEntity> items);

    LocalDate[] fechasConsulta();

    void generarReporteMensual(List<? extends SalIntefectDcvEntity> items) throws ErrorFtps;

    void eliminarRegistrosSalida();

    void verificarGeneracionArchivo() throws IOException, ErrorFtps;

}
