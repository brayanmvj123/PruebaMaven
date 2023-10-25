package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.*;
import com.bdb.oplbacthdiarios.controller.service.interfaces.ReportPgService;
import com.bdb.oplbacthdiarios.mapper.MapperMigraciones;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class ReportPgServiceImpl implements ReportPgService {

    private final JobLauncher jobLauncher;

    private final Job jobReportPg;

    private final Job jobSimulatorPg;

    private final Job jobCreateFiles;

    private final RepositorySalPg repositorySalPg;

    private final ResponseService responseService;

    private final RepositoryCDTSLarge repositoryCDTSLarge;

    final RepositoryTransaccionesPago repositoryTransaccionesPago;

    final RepositoryHisRenovaCdt repositoryHisRenovaCdt;

    final RepositoryHisMigraciones repositoryHisMigraciones;

    final MapperMigraciones mapperMigraciones;

    final RepositoryOficina repositoryOficina;

    final RepositoryTipVarentorno repositoryTipVarentorno;

    public ReportPgServiceImpl(RepositoryCDTSLarge repositoryCDTSLarge,
                               JobLauncher jobLauncher,
                               @Qualifier(value = "JobReportPg") Job jobReportPg,
                               @Qualifier(value = "JobSimulatorPg") Job jobSimulatorPg,
                               @Qualifier(value = "JobCreateFiles") Job jobCreateFiles,
                               RepositorySalPg repositorySalPg,
                               ResponseService responseService,
                               RepositoryTransaccionesPago repositoryTransaccionesPago,
                               RepositoryHisRenovaCdt repositoryHisRenovaCdt,
                               RepositoryHisMigraciones repositoryHisMigraciones,
                               MapperMigraciones mapperMigraciones, RepositoryOficina repositoryOficina,
                               RepositoryTipVarentorno repositoryTipVarentorno) {
        this.repositoryCDTSLarge = repositoryCDTSLarge;
        this.jobLauncher = jobLauncher;
        this.jobReportPg = jobReportPg;
        this.jobSimulatorPg = jobSimulatorPg;
        this.jobCreateFiles = jobCreateFiles;
        this.repositorySalPg = repositorySalPg;
        this.responseService = responseService;
        this.repositoryTransaccionesPago = repositoryTransaccionesPago;
        this.repositoryHisRenovaCdt = repositoryHisRenovaCdt;
        this.repositoryHisMigraciones = repositoryHisMigraciones;
        this.mapperMigraciones = mapperMigraciones;
        this.repositoryOficina = repositoryOficina;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
    }

    @Override
    public void deleteData() {
        repositorySalPg.deleteAllByEstado(5);
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobReportPg(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
        deleteData();
        log.info("DATOS ELIMINADOS.");
        JobExecution reportPgWeekly = runJob(jobReportPg, urlRequest);
        log.info("SE TERMINO EL JOB DE CRUCE ... INICIA EL PROCESO DE VALIDACIÒN DE OFICINA DUEÑA ...");

        if (repositoryTipVarentorno.findByDescVariable("VALIDATE_MIGRATIONS").getValVariable().equals("1"))
            validationChangeOfficeMigration();

        return responseService.getResponseJob(reportPgWeekly, "EL CRUCE HA FALLADO", urlRequest);
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobSimuladorPg(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE PROCEDE A SIMULAR LOS DATOS");
        JobExecution simulatorPgWeekly = runJob(jobSimulatorPg, urlRequest);
        return responseService.getResponseJob(simulatorPgWeekly, "LA SIMULACIÓN HA FALLADO", urlRequest);
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobCreateFiles(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("COMIENZA LA CREACIONDE ARCHIVOS DE EXCEL PARA ENVIAR A OFICINAS");
        JobExecution simulatorPgWeekly = runJob(jobCreateFiles, urlRequest);
        return responseService.getResponseJob(simulatorPgWeekly, "LA SIMULACIÓN HA FALLADO", urlRequest);
    }

    @Override
    public JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(urlRequest.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        return jobExecution;
    }

    /**
     * Este metodo valida si el centro de costo dueño del CDT Digital no ha sido migrado. Si el centro de costo no ha
     * sido cambiado continua con otro titulo, si algún titulo ha sido ajustado su centro de costo se procede a modificar
     * las tablas HIS_CDTS (Campo <b>OPL_OFICINA_NRO_OFICINA</b>) y HIS_TRANPG (Campo <b>UNID_DESTINO</b>).
     */
    private void validationChangeOfficeMigration() {
        List<SalPgDownEntity> salPgAll = repositorySalPg.findAll();
        List<SalPgDownEntity> collectChangesOfficeCdt = salPgAll.stream()
                .filter(cdt ->
                        cdt.getNroOficina() != repositoryCDTSLarge.findByNumCdt(cdt.getNumCdt().toString())
                                .get(0)
                                .getOplOficinaTblNroOficina().longValue())
                .filter(office -> repositoryOficina.findById(office.getNroOficina().intValue())
                        .get()
                        .getOplEstadosTblTipEstado() == 4)
                .collect(Collectors.toList());
        collectChangesOfficeCdt.forEach(cdt -> {
            Integer officeOld = saveHisCdts(cdt);
            saveHisTranpg(cdt.getNumCdt(), cdt.getNroOficina().intValue());
            saveChangesMigrations(cdt.getNumCdt(), cdt.getNroOficina().intValue(), officeOld);
        });
    }

    private Integer saveHisCdts(SalPgDownEntity cdt) {
        log.info("SE INICIA LA ACTUALIZACIÓN DEL CENTRO DE COSTO EN LA HIS_CDTS PARA EL CDT DIGITAL: " + cdt.getNumCdt());
        HisCDTSLargeEntity hisCDTSLargeEntity = repositoryCDTSLarge.findByNumCdt(cdt.getNumCdt().toString()).get(0);
        Integer officeOld = hisCDTSLargeEntity.getOplOficinaTblNroOficina();
        hisCDTSLargeEntity.setUnidCeo(cdt.getNroOficina().toString());
        hisCDTSLargeEntity.setUnidNegocio(cdt.getNroOficina().toString());
        hisCDTSLargeEntity.setOplOficinaTblNroOficina(cdt.getNroOficina().intValue());
        repositoryCDTSLarge.save(hisCDTSLargeEntity);
        return officeOld;
    }

    public void saveHisTranpg(Long numCdt, Integer officeOwner) {
        log.info("SE INICIA LA ACTUALIZACIÓN DEL CENTRO DE COSTO EN LA HIS_TRANPG PARA EL CDT DIGITAL: " + numCdt);

        Optional<HisRenovaCdtEntity> byCdtAct = Optional.ofNullable(repositoryHisRenovaCdt.findByCdtAct(numCdt));
        if (byCdtAct.isPresent()) {
            log.info("SI ES UNA RENOVACION ..." + byCdtAct.get().getCdtOrigen());
            changesTranpg(officeOwner, byCdtAct.get().getCdtOrigen(), "1");
            changesTranpg(officeOwner, numCdt, "2");
        } else {
            log.info("NO ES RENOVACIÓN");
            changesTranpg(officeOwner, numCdt, "1");
        }
    }

    private void changesTranpg(Integer officeOwner, Long numCdt, String process) {
        HisTranpgEntity hisTranpgEntity = repositoryTransaccionesPago.buscarTransaccionCdtDig(numCdt.toString(), process);
        hisTranpgEntity.setUnidDestino(officeOwner);
        repositoryTransaccionesPago.save(hisTranpgEntity);
    }

    private void saveChangesMigrations(Long numCdt, Integer officeNew, Integer officeOld) {
        log.info("SE ALMACENA EL CAMBIO DE OFICINA.");
        repositoryHisMigraciones.save(mapperMigraciones.changesOffice(numCdt, officeNew, officeOld));
    }

}
