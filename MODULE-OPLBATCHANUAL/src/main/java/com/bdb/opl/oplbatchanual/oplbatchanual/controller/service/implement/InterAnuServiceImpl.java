package com.bdb.opl.oplbatchanual.oplbatchanual.controller.service.implement;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalIntefectDcv;
import com.bdb.opl.oplbatchanual.oplbatchanual.controller.service.interfaces.InterAnuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class InterAnuServiceImpl implements InterAnuService {

    @Autowired
    private RepositorySalIntefectDcv repositorySalIntefectDcv;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Override
    public void almacenar(List<? extends SalIntefectDcvEntity> items) {
        repositorySalIntefectDcv.saveAll(items);
    }

    @Override
    public LocalDate[] fechasConsulta() {
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = LocalDate.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear());
        fechas[1] = LocalDate.now().minusYears(1).with(TemporalAdjusters.lastDayOfYear());
        return fechas;
    }

    @Override
    public void generarReporteAnual(List<? extends SalIntefectDcvEntity> items) throws ErrorFtps {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

        LocalDate fecha = LocalDate.now();
        String nombre = "INT_EFECT_CDTSDESM_AAAA.OPL".replace("AAAA",
                String.valueOf(fecha.getYear()-1));

        items.forEach(item -> {
            try {
                out.write(item.strucReportMonthly().concat("\n").getBytes());
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("CANTIDAD: " + byteArrayOutputStream.toString().length());

        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        String fechaActual = serviceFTP.obtenerFechaActual();
        serviceFTP.makeFile(byteArrayOutputStream, serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual), nombre);
    }

    @Override
    public void eliminarRegistrosSalida() {
        repositorySalIntefectDcv.deleteAll();
    }

}
