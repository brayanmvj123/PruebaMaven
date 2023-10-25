package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.List;

public class WriterFileTraductor implements ItemWriter<SalTramastcDownEntity> {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Override
    public void write(List<? extends SalTramastcDownEntity> items) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);
        String nombre = "TC.OPL";
        System.out.println("CANTIDAD DE TRAMAS: "+items.size());
        try {
            long count=0;
            String nuevaLinea = "\r\n";
            for (SalTramastcDownEntity item : items) {
                try {
                    //System.out.println(item.getTrama().getBytes());
                    out.write(item.getTrama().getBytes());
                    out.write(nuevaLinea.getBytes());
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
                count++;
            }
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
