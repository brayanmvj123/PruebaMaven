package com.bdb.oplbatchmensual.controller.service.implement;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalIntefectDcvEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalIntefectDcv;
import com.bdb.oplbatchmensual.controller.service.interfaces.InteresesMensualesService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@CommonsLog
public class InteresesMensualesServiceImpl implements InteresesMensualesService {

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
        fechas[0] = LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        fechas[1] = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        return fechas;
    }

    @Override
    public void generarReporteMensual(List<? extends SalIntefectDcvEntity> items) throws ErrorFtps {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

        LocalDate fecha = LocalDate.now();
        String nombre = "INT_EFECT_CDTSDESM_MMAAAA.OPL".replace("MMAAAA",
                String.format("%02d", fecha.getMonthValue() - 1) + (fecha.getYear()));

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

    @Override
    public void verificarGeneracionArchivo() throws IOException, ErrorFtps {
        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        serviceFTP.makeDirectoryDay(parameters.getRuta());
        serviceFTP.makeSubDirectorys();
        verificaGeneracionrArchivo();
        serviceFTP.disconnectFTP();

    }

    public void verificaGeneracionrArchivo() throws ErrorFtps, IOException {
        String nombre = "INT_EFECT_CDTSDESM_MMAAAA.OPL".replace("MMAAAA",
                String.format("%02d", LocalDate.now().getMonthValue() - 1) + (LocalDate.now().getYear()));
        Integer cantidad = serviceFTP.getNameFile("%OUTPUT%", nombre);
        if (cantidad == 0) {
            log.info("LA CANTIDAD DE ARCHIVOS ENCONTRADOS ES: " + cantidad);
            File file = new File("TC.OPL");
            ByteArrayOutputStream input = new ByteArrayOutputStream();
            serviceFTP.updateFile("%OUTPUT%", input, nombre);
        }
    }
}
