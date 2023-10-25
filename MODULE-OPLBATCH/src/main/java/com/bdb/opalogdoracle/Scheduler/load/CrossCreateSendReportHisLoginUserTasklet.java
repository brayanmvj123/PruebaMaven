package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.HisLoginService;
import com.bdb.opalogdoracle.persistence.model.PerfilEmailDto;
import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.opaloshare.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CrossCreateSendReportHisLoginUserTasklet implements Tasklet {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    HisLoginService hisLoginService;

    @Autowired
    RepositoryParEndpointDown endpointRepo;

    @Autowired
    RepositoryTipVarentorno repoVarentorno;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        logger.info("start generate Excel bloquearUsuario()...");

        try {
            List<HisLoginDownEntity> data = hisLoginService.generarData("2");
            hisLoginService.generarExcelSegunEstadoUsuario(data, "2");
            logger.info("end generate Excel Report users his login()...");
            logger.info("start send Email Report users his login()...");
            ParEndpointDownEntity uri = endpointRepo.getParametro(Constants.FIND_URL_EMAIL);
            VarentornoDownEntity var = repoVarentorno.findByDescVariable("ID_PERFIL_EMAIL_REPORT_USER");
            Integer valVariable = Integer.valueOf(var.getValVariable());
            PerfilEmailDto perfilEmailDto = new PerfilEmailDto();
            perfilEmailDto.setIdPerfil(valVariable);
            perfilEmailDto.setEmailSubjectType("1");
            perfilEmailDto.setEmailContentType("1");
            perfilEmailDto.setAttached(true);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(uri.getRuta(), perfilEmailDto, String.class);
            logger.info("end send Email Report users his login()...");

        } catch (Exception e) {
            logger.error("Error en CrossCreateSendReportHisLoginUserTasklet: " + e);
            throw new UnexpectedJobExecutionException("Error en CrossCreateSendReportHisLoginUserTasklet");
        }

        return RepeatStatus.FINISHED;

    }
}
