package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarDerpatridepDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.opaloshare.util.Constants;
import com.bdb.oplbacthdiarios.controller.service.interfaces.DerechosPatrimonialesService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

@Service("serviceDerechosPatrimoniales")
@CommonsLog
public class DerechosPatrimonialesServiceImpl implements DerechosPatrimonialesService {

    @Autowired
    SharedService sharedService;

    @Autowired
    ResponseService responseService;

    @Autowired
    private RepositoryCarDerpatridepDownEntity repoPatrimonialesCarga;

    @Autowired
    private RepositoryTipVarentorno repositoryTipVarentorno;

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    @Override
    public void eliminarPatrimonialesCarga() {
        repoPatrimonialesCarga.deleteAll();
    }

    @Override
    public boolean validarPeriodo() {
        VarentornoDownEntity varentorno = repositoryTipVarentorno.findByDescVariable("FILE_DATE_PATRIMONIALES");
        Predicate<String> validarFechaIni = x -> LocalDate.parse(varentorno.getValVariable())
                .isBefore(LocalDate.parse(x, DateTimeFormatter.ofPattern("yyyyMMdd")));
        Predicate<String> validarFechaFin = x -> LocalDate.parse(x, DateTimeFormatter.ofPattern("yyyyMMdd"))
                .isEqual(LocalDate.now());
        long item = repoPatrimonialesCarga.findAll()
                .stream()
                .limit(1)
                .filter(x -> validarFechaIni.test(x.getFechaIni()))
                .filter(x -> validarFechaFin.test(x.getFechaFin()))
                .count();
        if (item == 1) repositoryTipVarentorno.save(new VarentornoDownEntity(varentorno.getCodVariable(),
                varentorno.getDescVariable(), LocalDate.now().toString()));
        return item == 1;
    }

    /**
     * Ejecución de los <i>Job(s)</i> solicitados.
     *
     * @param urlRequest Información del host que ha enviado la petición.
     * @return ResponseEntity<ResponseBatch>, valida la ejecución del job a cargo del servicio.
     * @throws JobParametersInvalidException       Exception for {@link Job} to signal that some {@link JobParameters}
     *                                             are invalid.
     * @throws JobExecutionAlreadyRunningException Excepciones al momento de estar la tarea ejecutandose.
     * @throws JobRestartException                 An exception indicating an illegal attempt to restart a job.
     * @throws JobInstanceAlreadyCompleteException An exception indicating an illegal attempt to restart a job that was
     *                                             already completed successfully.
     * @throws IOException                         Signals that an I/O exception of some sort has occurred. This class
     *                                             is the general class of exceptions produced by failed or interrupted
     *                                             I/O operations.
     */
    @Override
    public ResponseEntity<ResponseBatch> cargaArchivoDepo(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException {

        /*log.info("Inicio el proceso de carga de emision derechos patrimoniales DEPOSITANTE...");

        log.info("eliminarDepartiDep ...");
        repoPatrimonialesCarga.deleteAllInBatch();

        log.info("leer Archivo DepatriDepositante del sitio Ftps ...");
        if (getFile()) {
            JobParameters parameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .addLong("time", System.currentTimeMillis()).toJobParameters();

            log.info("joblauncher Run...");
            JobExecution jobExecution = jobLauncher.run(job, parameters);

            log.info("JobExecution: " + jobExecution.getStatus());

            log.info("JobDcvPatrimoniales Job is Running...");
            while (jobExecution.isRunning()) {
                log.info("...");
            }

            return responseService.getResponseJob(jobExecution,
                    "EL ARCHIVO HA SIDO CARGADO CON EXITO.",
                    urlRequest);

        } else {
            log.info("NO SE ENCONTRO EL ARCHIVO :(");
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    urlRequest.getRequestURL().toString(), "No se alcanzo a ejecutar el Job", "N/A",
                    "El archivo no se encontro en la ruta indicada", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        return null;
    }

    @Override
    public void guardar(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public ByteArrayOutputStream cargar() {
        return archivo;
    }

    @Override
    public ByteArrayOutputStream eliminarRegistroControl(ByteArrayOutputStream resource) {

        log.info("entroooooo al metodo LIMPIAR ARCHIVO");

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
                        out.write(validarLinea(line, 50, 73, 121).getBytes());
                        out.write(nuevaLinea.getBytes());
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    } catch (Exception e) {
                        log.error("ERROR: Class=DerechosPatrimonialesServiceImpl, method=eliminarRegistroControl");
                    }
                }
            }

            log.info("CANTIDAD: " + byteArrayOutputStream.toString().length());

        } catch (Exception e) {
            log.error(e);
        } finally {
            reader.close();
        }

        return byteArrayOutputStream;
    }

    /**
     * Obtiene el <u>archivo plano</u> buscado en el sitio FTPS asignado a la aplicación.
     *
     * @return Un boolean, indicando:
     * <ul>
     *     <li>TRUE, si el archivo fue encontrado, bajado a memoria y esta listo para ser trabajado.</li>
     *     <li>FALSE, si el archivo NO fue encontrado o ocurrio algun error al momento de trabajar con el.</li>
     * </ul>
     */
    public boolean getFile() throws IOException {
        boolean saber = sharedService.obtenerArchivo(Constants.FILEINPATRI,
                "INPUT",
                "CONFIGURATION");
        if (saber) sharedService.limpiarArchivo(null, 70);
        return saber;
    }

    public String validarLinea(String linea, int longitudDeseada, int inicio, int fin){
        return linea.length() == 767 ? linea : linea.replace(linea.substring(inicio, fin),
                linea.substring(inicio, fin).concat(" "));
    }

}
