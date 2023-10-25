package com.bdb.opalo.mds.controller.service.implement;

import com.bancodebogota.ifx.base.v1.NetworkTrnInfoType;
import com.bancodebogota.ifx.base.v1.RecCtrlInType;
import com.bancodebogota.rdm.classification.event.EntityMembersInqRqType;
import com.bancodebogota.rdm.classification.service.EntityMembersManagement;
import com.bancodebogota.rdm.classification.service.GetEntityMembersFault;
import com.bancodebogota.rdm.classification.service.GetEntityMembersRequest;
import com.bancodebogota.rdm.classification.service.GetEntityMembersResponse;
import com.bancodebogota.rdm.classification.v1.EntityInfoType;
import com.bancodebogota.rdm.classification.v1.EntityMemberInfoType;
import com.bancodebogota.rdm.classification.v1.EntityMembersRecType;
import com.bdb.opalo.mds.controller.service.interfaces.RatesService;
import com.bdb.opalo.mds.persistence.Constants;
import com.bdb.opalo.mds.persistence.ResponseBatch;
import com.bdb.opalo.mds.persistence.ResponseService;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.opaloshare.persistence.repository.RepositoryTmpTasasCdtMds;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@Service("RatesServiceImpl")
@CommonsLog
public class RatesServiceImpl implements RatesService {

    final JobLauncher jobLauncher;

    final Job job;

    private final EntityMembersManagement entityMembersManagement;

    private final RepositoryTipVarentorno repositoryTipVarentorno;

    private final RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds;

    private final ResponseService responseService;

    private final MetodosGenericos metodosGenericos;

    JobParameters parameters;

    HttpStatus httpStatus;

    Constants constants = new Constants();

    public RatesServiceImpl(JobLauncher jobLauncher,
                            @Qualifier(value = "JobRates") Job job,
                            EntityMembersManagement entityMembersManagement,
                            RepositoryTipVarentorno repositoryTipVarentorno,
                            RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds,
                            ResponseService responseService,
                            MetodosGenericos metodosGenericos) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.entityMembersManagement = entityMembersManagement;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
        this.repositoryTmpTasasCdtMds = repositoryTmpTasasCdtMds;
        this.responseService = responseService;
        this.metodosGenericos = metodosGenericos;
    }


    @Override
    public ResponseEntity<ResponseBatch> responseConsumeRates(HttpServletRequest httpServletRequest) throws GetEntityMembersFault, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE INICIA EL CONSUMO DE LAS TASAS EMITIDAS PARA LOS CDTS DIGITALES POR MDS");

        eliminarInformacion();
        log.info("SE ELIMINÓ LA DATA DE LA TABLA OPL_TMP_TASASCDTMDS_DOWN_TBL");

        JSONArray getParams = new JSONArray(repositoryTipVarentorno.findByDescVariable(constants.VALUES_RATES_MDS).getValVariable());

        JobExecution jobExecutionResult = getJobExecution(Integer.parseInt(((JSONObject) getParams.get(0)).get(constants.getMAX_PAGES()).toString()));
        if (jobExecutionResult != null) {

            httpStatus = HttpStatus.OK;
            if (jobExecutionResult.getStatus().getBatchStatus().toString().equals("FAILED")) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            jobExecutionResult.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));

            ResponseBatch responseEntity = responseService.resultJob(httpStatus.toString(), httpServletRequest.getRequestURL().toString(),
                    jobExecutionResult.getJobId().toString(), jobExecutionResult.getStatus().toString(), null, jobExecutionResult.getStepExecutions());
            return new ResponseEntity<>(responseEntity, httpStatus);

        } else {
            log.error("NO SE REALIZO LA CARGA DE LOS TASAS");
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), httpServletRequest.getRequestURL().toString(),
                    "No se alcanzo a ejecutar el Job", "N/A", "El servicio expuesto por MDS puede estar inestable o algun error de data", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private JobExecution getJobExecution(int pages) throws JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, GetEntityMembersFault {

        JobExecution jobExecution = null;

        if (pages > 0) {
            List<String> result = consumeRates(pages);
            if (!result.isEmpty()) {
                enviarInfoJob(result);
                log.info("LA CADENA NO VIENE ... SE INICIA EL CARGUE EN LA TABLA");

                Map<String, JobParameter> maps = new HashMap<>();
                maps.put("time", new JobParameter(System.currentTimeMillis()));
                parameters = new JobParameters(maps);
                jobExecution = jobLauncher.run(job, parameters);

                log.info("JobExecution: " + jobExecution.getStatus());
                while (jobExecution.isRunning()) log.info("...");

                log.info(jobExecution.getStatus().getBatchStatus().toString());
                if (pages - 1 == 0) return jobExecution;
                getJobExecution(pages - 1);

            } else
                return null;
        }

        return jobExecution;
    }


    public List<String> consumeRates(int page) throws GetEntityMembersFault {

        JSONArray getListParams = new JSONArray(repositoryTipVarentorno
                .findByDescVariable(constants.VALUES_RATES_MDS)
                .getValVariable());

        List<String> results  = new ArrayList<>();

        for (Object params : getListParams) {
            JSONObject getParams =  new JSONObject(params.toString());

            EntityMembersInqRqType entityMembersInqRqType = new EntityMembersInqRqType();
            entityMembersInqRqType.setRqUID(generarUUID());
            entityMembersInqRqType.setNetworkTrnInfo(networkTrnInfoType(getParams));
            entityMembersInqRqType.setClientDt(generarFecha());
            entityMembersInqRqType.getEntityInfo().add(entityInfoType(getParams));
            entityMembersInqRqType.setRecCtrlIn(recCtrlInType(getParams, page));

            GetEntityMembersRequest getEntityMembersRequest = new GetEntityMembersRequest();
            getEntityMembersRequest.setEntityMembersInqRq(entityMembersInqRqType);

            GetEntityMembersResponse getEntityMembersResponse = entityMembersManagement.getEntityMembers(getEntityMembersRequest);
            log.info("CODIGO DE RESPUESTA: " + getEntityMembersResponse.getEntityMembersInqRs().getStatus().getStatusCode() + "\n" +
                    getEntityMembersResponse.getEntityMembersInqRs().getStatus().getStatusDesc());

            StringBuilder cadena = new StringBuilder();

            Stream<List<EntityMemberInfoType>> listInfoType = getEntityMembersResponse.getEntityMembersInqRs()
                    .getEntityMembersRec()
                    .stream().map(EntityMembersRecType::getEntityMemberInfo);

            listInfoType.forEach(item -> item.forEach(x -> {
                cadena.append(x.getIdentifier()).append(";").append(x.getMemo()).append(";");
                x.getAttributeInfo().forEach(j ->
                        cadena.append(j.getStatusDesc()).append(";")
                );
                cadena.replace(cadena.length() - 1, cadena.length(), "");
                cadena.append("\n");
            }));

            log.info("CADENA:" + cadena);
            results.add(cadena.toString());
        }

        return results;
    }

    public void eliminarInformacion() {
        repositoryTmpTasasCdtMds.deleteAllInBatch();
    }

    @Override
    public void enviarInfoJob(List<String> infoMds) {
        metodosGenericos.almacenarArchivo(infoMds);
    }

    public String generarUUID() {
        return UUID.randomUUID().toString();
    }

    public NetworkTrnInfoType networkTrnInfoType(JSONObject getParams) {
        NetworkTrnInfoType networkTrnInfoType = new NetworkTrnInfoType();
        networkTrnInfoType.setNetworkOwner(repositoryTipVarentorno.findByDescVariable(constants.CODIGO_APP)
                .getValVariable());
        networkTrnInfoType.setBankId(getParams.get(constants.BANKIN_ID).toString());
        return networkTrnInfoType;
    }

    public String generarFecha() {
        String formatoFecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern(constants.FORMATTER_TIMESTAMP));
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(LocalDateTime.parse(formatoFecha).atZone(ZoneId.of(constants.ZONE)));
    }

    public EntityInfoType entityInfoType(JSONObject params) {
        EntityInfoType entityInfoType = new EntityInfoType();
        entityInfoType.setName(params.get(constants.getNAME_RATES()).toString());
        entityInfoType.setDomainName(params.get(constants.getDOMAIN_NAME_RATES()).toString());
        entityInfoType.setVersion(params.get(constants.getVERSION_RATES()).toString());
        return entityInfoType;
    }

    public RecCtrlInType recCtrlInType(JSONObject params, int page) {
        RecCtrlInType recCtrlInType = new RecCtrlInType();
        recCtrlInType.setSentRec(Long.parseLong(String.valueOf(page)));
        recCtrlInType.setMaxRec(Long.parseLong(params.get(constants.getMAX_REC_RATES()).toString()));
        return recCtrlInType;
    }

}
