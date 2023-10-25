package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalCutEntity;
import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WriterFileCut implements ItemWriter<SalCutEntity> {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Override
    public void write(List<? extends SalCutEntity> items) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);
        String nombre = "CUT.OPL";
        System.out.println("CANTIDAD DE TRAMAS: "+items.size());
        try {
            int count=0;
            String nuevaLinea = "\n";
            String primeraLinea = "CUTORIGEN|CUTDESTINO";
            out.write(primeraLinea.getBytes());
            out.write(nuevaLinea.getBytes());
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            for (SalCutEntity item : items) {
                try {
                    StringBuffer cadena = new StringBuffer();
                    cadena.append(item.getCodOrigen()).append("|").append(item.getCodDestino());
                    //System.out.println(cadena);
                    out.write(cadena.toString().getBytes());
                    out.write(nuevaLinea.getBytes());
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
                count++;
            }
            String fechaArchivo = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String finalLinea = count+"|"+fechaArchivo+"|"+"CUT.OPL";
            out.write(finalLinea.getBytes());
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();

            //System.out.println("CANTIDAD: " + byteArrayOutputStream.toString().length());
        } catch (Exception e) {
            System.out.println(e);// e;
        }

        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        serviceFTP.makeDirectoryDay(parameters.getRuta());
        serviceFTP.makeSubDirectorys();
        String fechaActual = serviceFTP.obtenerFechaActual();
        serviceFTP.makeFile(byteArrayOutputStream, serviceFTP.rutaEspecifica("%OUTPUT%",fechaActual) , nombre);
    }
}
