package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.ArchivoTraductorService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryConexiones;
import com.bdb.opaloshare.persistence.repository.RepositorySalTramastcDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ArchivoTraductorServiceImpl implements ArchivoTraductorService {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    RepositoryConexiones repoConexiones;

    @Autowired
    RepositorySalTramastcDown repoTramas;

    @Override
    public void generarArchivo(List<? extends SalTramastcDownEntity> items) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

        String nombre = "TC.OPL";
        try {

            long count=0;
            String nuevaLinea = System.lineSeparator();//"\r\n";
            for (SalTramastcDownEntity item : items) {
                String fila = "null";
                try {
                    System.out.println(item.getTrama().getBytes());
                    fila = item.getItem().toString()+item.getFecha()+item.getTrama();
                    out.write(fila.getBytes());
                    out.write(nuevaLinea.getBytes());
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                    System.out.println("ERROR");
                }

                count++;
            }

            System.out.println("CANTIDAD: " + byteArrayOutputStream.toString().length());

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

    @Override
    public void verificaGeneracionrArchivo() throws ErrorFtps, IOException {
        Integer cantidad = serviceFTP.getNameFile("%OUTPUT%","TC.OPL");
        if (cantidad == 0){
            System.out.println("LA CANTIDAD ES: "+cantidad);
            File file = new File("TC.OPL");
            ByteArrayOutputStream input = new ByteArrayOutputStream();
            serviceFTP.updateFile("%OUTPUT%", input , "TC.OPL");
        }
    }

    @Override
    public Boolean verificarTablaArchivoP(Long resultadoRegistros) {
        System.out.println("ENTRO A verificarTablaArchivoP");
        return resultadoRegistros == 0;
    }

    @Override
    public void eliminarTramasDeceapdi() {
        String fechaActual = serviceFTP.obtenerFechaActual();
        System.out.println("LA FECHA ACTUAL ES: "+fechaActual);
        repoTramas.deleteByTramaContainingAndFecha("DECEAPDI" , fechaActual);
    }


}
