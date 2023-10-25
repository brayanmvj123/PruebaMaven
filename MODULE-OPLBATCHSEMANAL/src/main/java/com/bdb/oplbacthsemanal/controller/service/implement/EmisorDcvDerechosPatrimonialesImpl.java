/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.oplbacthsemanal.controller.service.implement;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGeneralesService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.oplbacthsemanal.controller.service.interfaces.EmisorDcvDerechosPatrimonialesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * Service encargado de leer y realizar el cargue del archivo DCV con el resultado de
 * Derechos patrimoniales
 *
 * @author: Esteban Talero
 * @version: 26/11/2020
 * @since: 24/11/2020
 */
@Service("serviceDcvSemanalDerechosPatrimoniales")
public class EmisorDcvDerechosPatrimonialesImpl implements EmisorDcvDerechosPatrimonialesService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();


    //Service encargado de obtener el nombre del archivo a buscar en la base de Datos
    @Autowired
    MetodosGeneralesService metodoGeneralesService;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    /**
     * Metodo encargado de leer el archivo de Deceval guardado en el FTP
     *
     * @return resultado de la operacion
     */
    @Override
    public boolean leerArchivoDcvFtp(String claveArchivo) {
        logger.info("Inicio leerArchivoDcvFtp()...");
        String inicioArchivo = metodoGeneralesService.nombreArchivo(claveArchivo);
        boolean convertido = false;

        try {
            logger.info("Inicio leerArchivoDcvFtp() try catch...");
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();

            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
            String fechaActual = serviceFTP.obtenerFechaActual();
            String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%INPUT%", fechaActual);
            String rutaArchivoProcesado = serviceFTP.rutaEspecifica("%CONFIGURATION%", fechaActual);
            String leerArchivo = serviceFTP.listFile(inicioArchivo);

            if (!leerArchivo.isEmpty()) {
                logger.info("Inicio leerArchivoDcvFtp() try catch --> !leerArchivo.isEmpty() ...");
                System.out.println("EL ARCHIVO A LEER ES: " + leerArchivo);

                String nombreArchivoProcesado = "Procesado_" + leerArchivo;
                ByteArrayOutputStream resource = serviceFTP.archivoResource(rutaArchivoOriginal + leerArchivo);
                ByteArrayOutputStream nuevoResource = eliminarRegistroControl(resource);

                serviceFTP.makeFile(nuevoResource, rutaArchivoProcesado, nombreArchivoProcesado);

                guardar(nuevoResource);
                convertido = true;
            }
            serviceFTP.disconnectFTP();
        } catch (ErrorFtps ftpErrors) {

            logger.error(ftpErrors.getMessage());
            return convertido;
        }
        return convertido;
    }

    /**
     * Metodo donde se obtiene el archivo cargado del FTP
     *
     * @return data del archivo
     */
    @Override
    public ByteArrayOutputStream cargarDatosArchivoDcv() {
        return archivo;
    }

    /**
     * Metodo encargado de limpiar el archivo Leido
     *
     * @param resource archivo a limpiar
     * @return Archivo limpio
     */
    private ByteArrayOutputStream eliminarRegistroControl(ByteArrayOutputStream resource) {

        System.out.println("Ingreso al metodo LIMPIAR ARCHIVO dcv Derechos patrimoniales");

        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setLineMapper(new PassThroughLineMapper());
        reader.setResource(new ByteArrayResource(resource.toByteArray()));
        reader.open(new ExecutionContext());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

        try {
            String line;
            String nuevaLinea = "\n";
            while ((line = reader.read()) != null) {

                if (line.length() > 70) {
                    try {
                        out.write(line.getBytes());
                        out.write(nuevaLinea.getBytes());
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    } catch (Exception e) {
                        System.out.println("ERROR: Class=CargueDcvDerechosPatrimonialesImpl, method=eliminarRegistroControl");
                    }
                }
            }

            System.out.println("CANTIDAD: " + byteArrayOutputStream.toString().length());

        } catch (Exception e) {
            System.out.println("ERROR: En eliminarRegistroControl Class: EmisorDcvDerechosPatrimonialesImpl");
        } finally {
            reader.close();
        }

        return byteArrayOutputStream;
    }

    /**
     * Metodo encargado de guardar en la variable archivo la data del archivo leido del FTP
     *
     * @param archivo del FTP
     */
    private void guardar(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

}
