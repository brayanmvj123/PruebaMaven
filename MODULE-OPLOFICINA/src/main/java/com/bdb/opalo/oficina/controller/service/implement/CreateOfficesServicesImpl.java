package com.bdb.opalo.oficina.controller.service.implement;

import com.bdb.opalo.oficina.controller.service.interfaces.CreateOfficesService;
import com.bdb.opalo.oficina.controller.service.interfaces.startjob.StartJobService;
import com.bdb.opalo.oficina.persistence.model.CreateOfficeModel;
import com.bdb.opalo.oficina.persistence.response.ResponseBatch;
import com.bdb.opalo.oficina.persistence.response.ResponseService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryOficina;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@CommonsLog
public class CreateOfficesServicesImpl implements CreateOfficesService {

    final JobLauncher jobLauncher;

    final Job job;

    final RepositoryOficina repositoryOficina;

    final SharedService sharedService;

    final ResponseService responseService;

    final StartJobService startJobService;

    JobParameters parameters;
    JobExecution jobExecution;

    public CreateOfficesServicesImpl(JobLauncher jobLauncher,
                                     @Qualifier(value = "jobCreateOffices") Job job,
                                     RepositoryOficina repositoryOficina,
                                     SharedService sharedService,
                                     ResponseService responseService,
                                     StartJobService startJobService) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.repositoryOficina = repositoryOficina;
        this.sharedService = sharedService;
        this.responseService = responseService;
        this.startJobService = startJobService;
    }

    /**
     * Se obtiene los datos del archivo en el sitio FTPs de la aplicación.
     *
     * @return Un valor de tipo <u>boolean</u> informando si se encontro el archivo.
     */
    @Override
    public boolean getFile() {
        return sharedService.obtenerArchivo("FILE_OCASIONAL_CREATEOFI", "OCASIONAL", "CONFIGURATION");
    }

    /**
     * Permite validar si la oficina ya existe. Si ya existe no se tiene en cuenta, por el contrario si no existe se crea.
     *
     * @param data Este parametro sera enviado por ItemReader, contiene los datos de cada linea del archivo.
     * @return OficinaParDownEntity, {@link OficinaParDownEntity}
     */
    @Override
    public OficinaParDownEntity validateOffice(CreateOfficeModel data) {
        Optional<OficinaParDownEntity> oficinaParDownEntity = repositoryOficina.findById(data.getOffice());

        if (!oficinaParDownEntity.isPresent()) {
            log.info("NO EXISTE LA OFICINA, SE PROCEDE A CREARLA.");
            return new OficinaParDownEntity(data.getOffice(), data.getNameOffice(),
                    homolagaCodCeo(data.getIndCeo()), data.getCodCsc(), 4);
        }

        if (!oficinaParDownEntity.get().getDescOficina().equals(data.getNameOffice())) {
            log.info("LA OFICINA EXISTE PERO AL VALIDAR EL NOMBRE ACTUAL: "+oficinaParDownEntity.get().getDescOficina()+
                    " ES DIFERENTE: " + data.getNameOffice() + ", SE PROCEDE ACTUALIZAR EL CAMPO.");
            return new OficinaParDownEntity(data.getOffice(),
                    data.getNameOffice(),
                    oficinaParDownEntity.get().getOplTipoficinaTblTipOficina(),
                    data.getCodCsc(),
                    oficinaParDownEntity.get().getOplEstadosTblTipEstado());
        }

        return null;
    }

    /**
     * @param urlRequest Información del host que ha enviado la petición
     * @return ResponseEntity<ResponseBatch>, valida la ejecución del job a cargo del servicio.
     * @throws JobParametersInvalidException       Exception for {@link Job} to signal that some {@link JobParameters} are
     *                                             invalid.
     * @throws JobExecutionAlreadyRunningException Excepciones al momento de estar la tarea ejecutandose.
     * @throws JobRestartException                 An exception indicating an illegal attempt to restart a job.
     * @throws JobInstanceAlreadyCompleteException An exception indicating an illegal attempt to restart a job that was already
     *                                             * completed successfully
     */
    @Override
    public ResponseEntity<ResponseBatch> createOffice(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE INICIA LA EJECUCIÓN DE LA CARGA, E INSERCIÓN DE LAS OFICINAS NO EXISTENTES EN OPALO");

        if (getFile()) {
            log.info("SE ENCONTRO EL ARCHIVO PARA SER PROCESADO :)");

            Map<String, JobParameter> maps = new HashMap<>();
            maps.put("time", new JobParameter(System.currentTimeMillis()));
            JobExecution resultJobLoad = startJobService.startJob(job, maps, this.jobLauncher);

            if (!resultJobLoad.getStatus().isUnsuccessful()) {
                log.info("JOB EXITOSO :)");
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.OK.toString(), urlRequest.getRequestURL().toString(),
                        resultJobLoad.getJobId().toString(), resultJobLoad.getStatus().toString(), null, resultJobLoad.getStepExecutions());
                return new ResponseEntity<>(responseEntity, HttpStatus.OK);
            } else {
                log.info("ERROR EN EL JOB :(");
                ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                        resultJobLoad.getJobId().toString(), resultJobLoad.getStatus().toString(), "La carga del archivo fue FALLIDO.", resultJobLoad.getStepExecutions());
                return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            log.info("NO SE ENCONTRO EL ARCHIVO :(");
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                    "No se alcanzo a ejecutar el Job", "N/A", "El archivo no se encontro en la ruta indicada", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Almacena los nuevos centros de costos.
     *
     * @param items Lista de tipo {@link OficinaParDownEntity}.
     */
    @Override
    public void saveOffice(List<? extends OficinaParDownEntity> items) {
        repositoryOficina.saveAll(items);
    }

    /**
     * Es necesario realizar la homologación de los codigos CEO, ya que el archivo contiene unos y la tabla de Opalo
     * contiene otros. {@link com.bdb.opaloshare.persistence.entity.TipOficinaParDownEntity}
     *
     * @param codCeo Se envia el indicador CEO expuesto en el archivo.
     * @return Un tipo {@link Integer}, el valor es homologado de acuerdo a los tipos de oficina existentes en Opalo.
     */
    public Integer homolagaCodCeo(Integer codCeo) {
        int value;
        switch (codCeo) {
            case 0:
                value = 1;
                break;
            case 1:
            case 2:
            case 3:
                value = 3;
                break;
            case 4:
            case 5:
            case 6:
                value = 2;
                break;
            default:
                value = 99;
                break;
        }
        return value;
    }

    /**
     * @param jobSend Job enviado a ejecutar.
     * @return JobExecution, contiene la información de la ejecución o resultado de la tarea o Job.
     * @throws JobParametersInvalidException       Exception for {@link Job} to signal that some {@link JobParameters} are
     *                                             invalid.
     * @throws JobExecutionAlreadyRunningException Excepciones al momento de estar la tarea ejecutandose.
     * @throws JobRestartException                 An exception indicating an illegal attempt to restart a job.
     * @throws JobInstanceAlreadyCompleteException An exception indicating an illegal attempt to restart a job that was already
     *                                             completed successfully
     */
    public JobExecution startJob(Job jobSend) throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        parameters = new JobParameters(maps);
        jobExecution = jobLauncher.run(jobSend, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        log.info(jobExecution.getStatus().getBatchStatus().toString());

        jobExecution.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));

        return jobExecution;
    }

}
