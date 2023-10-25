package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ESTA INTERFACE PERMITE CONSTRUIR LOS METODOS QUE SERAN PROPIOS DEL SERVICEIMPLEMENTS (SALDOSERVICEIMPL) , ESTA CLASE
 * SIEMPRE DEBERA SER LLAMADA PARA TRABAJAR CON LOS SERVICESIMPLEMENTS , ESTO SE REALIZA PARA MANTENER UNA ORGANIZACION ,
 * PODER REUTILIZAR CODIGO Y MANTENER INTEGRIDAD ENTRE LAS DIFERENTES CAPAS.
 */
public interface DerechosPatrimonialesService {

    ResponseEntity<ResponseBatch> cargaArchivoDepo(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException;

    void guardar(ByteArrayOutputStream archivo);

    ByteArrayOutputStream cargar();

    ByteArrayOutputStream eliminarRegistroControl(ByteArrayOutputStream resource);

    void eliminarPatrimonialesCarga();

    boolean validarPeriodo();

}
