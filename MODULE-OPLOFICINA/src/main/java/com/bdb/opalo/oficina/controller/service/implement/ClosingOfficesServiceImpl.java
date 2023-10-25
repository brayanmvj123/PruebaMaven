package com.bdb.opalo.oficina.controller.service.implement;

import com.bdb.opalo.oficina.controller.service.interfaces.ClosingOfficesService;
import com.bdb.opalo.oficina.controller.service.interfaces.startjob.StartJobService;
import com.bdb.opalo.oficina.persistence.response.ResponseBatch;
import com.bdb.opalo.oficina.persistence.response.ResponseService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.CarCierreOfiEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarCierreOfi;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@CommonsLog
public class ClosingOfficesServiceImpl implements ClosingOfficesService {

    final JobLauncher jobLauncher;

    final Job jobClosingOffices;

    final Job jobUpdateOffices;

    final ResponseService responseService;

    final SharedService sharedService;

    final RepositoryCarCierreOfi repositoryCarCierreOfi;

    final StartJobService startJobService;

    HttpStatus httpStatus;

    public ClosingOfficesServiceImpl(JobLauncher jobLauncher,
                                     @Qualifier(value = "jobClosingOffices") Job jobClosingOffices,
                                     @Qualifier(value = "jobUpdateOffices") Job jobUpdateOffices,
                                     ResponseService responseService,
                                     SharedService sharedService,
                                     RepositoryCarCierreOfi repositoryCarCierreOfi,
                                     StartJobService startJobService) {
        this.jobLauncher = jobLauncher;
        this.jobClosingOffices = jobClosingOffices;
        this.jobUpdateOffices = jobUpdateOffices;
        this.responseService = responseService;
        this.sharedService = sharedService;
        this.repositoryCarCierreOfi = repositoryCarCierreOfi;
        this.startJobService = startJobService;
    }


    /**
     * Obtiene el <u>archivo plano</u> buscado en el sitio FTPS asignado a la aplicación.
     * @return Un boolean, indicando:
     * <ul>
     *     <li><b>TRUE</b>, si el archivo fue encontrado, bajado a memoria y esta listo para ser trabajado.</li>
     *     <li><b>FALSE</b>, si el archivo NO fue encontrado o ocurrio algun error al momento de trabajar con el.</li>
     * </ul>
     */
    @Override
    public boolean getFile() {
        return sharedService.obtenerArchivo("FILE_OCASIONAL_CIERREOFI", "OCASIONAL","CONFIGURATION");
    }

    /**
     * Elimina la data existente en la tabla {@link CarCierreOfiEntity}.
     * <p>La eliminación <u><i><b>solo</b></i></u> se debe realizar al inicio del cargue del archivo.</p>
     * @see RepositoryCarCierreOfi
     */
    @Override
    public void deleteData() {
        log.info("INICIA LA ELIMINACIÓN DE LA DATA EXISTENTE EN LA TABLA DE CARGA PARA EL CIERRE DE OFICINAS.");
        repositoryCarCierreOfi.deleteAllInBatch();
    }

    @Override
    public ResponseEntity<ResponseBatch> officesClosed(HttpServletRequest urlRequest) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
            JobParametersInvalidException, ErrorFtps, JobRestartException, IOException {
        deleteData();

        if (getFile()) {
            log.info("SE ENCONTRO EL ARCHIVO PARA SER PROCESADO :)");

            Map<String, JobParameter> maps = new HashMap<>();
            maps.put("time", new JobParameter(System.currentTimeMillis()));
            JobExecution resultJobLoad = startJobService.startJob(jobClosingOffices, maps, this.jobLauncher);

            if (!resultJobLoad.getStatus().isUnsuccessful()){
                JobExecution resultUpdateOfficesId = startJobService.startJob(jobUpdateOffices, maps, this.jobLauncher);
                httpStatus = resultUpdateOfficesId.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
                ResponseBatch responseEntity = responseService.resultJob(httpStatus.toString(), urlRequest.getRequestURL().toString(),
                        resultUpdateOfficesId.getJobId().toString() , resultUpdateOfficesId.getStatus().toString() , null , resultUpdateOfficesId.getStepExecutions());
                return new ResponseEntity<>(responseEntity,httpStatus);
            }else{
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                        resultJobLoad.getJobId().toString() , resultJobLoad.getStatus().toString() , "La carga del archivo fue FALLIDO." , resultJobLoad.getStepExecutions());
                return new ResponseEntity<>(responseEntity,httpStatus);
            }
        }else{
            log.info("NO SE ENCONTRO EL ARCHIVO :(");
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                    "No se alcanzo a ejecutar el Job" , "N/A" , "El archivo no se encontro en la ruta indicada" , null);
            return new ResponseEntity<>(responseEntity,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
