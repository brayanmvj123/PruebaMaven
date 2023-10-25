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

public class CrossCreateLockedUserFileTasklet implements Tasklet {

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
        logger.info("Se ejecuta dado que se va a enviar correo cuando no existe usuarios a inactivar");
        try {
            List<HisLoginDownEntity> data = hisLoginService.generarData("1");
            boolean tieneArchivo = hisLoginService.generarExcelSegunEstadoUsuario(data, "1");
            logger.info("end generate Excel bloquearUsuario()...");
            logger.info("start send Email bloquearUsuario()...");
            ParEndpointDownEntity uri = endpointRepo.getParametro(Constants.FIND_URL_EMAIL);
            VarentornoDownEntity var = repoVarentorno.findByDescVariable("ID_PERFIL_EMAIL_BLOCKED_USER");
            Integer valVariable = Integer.valueOf(var.getValVariable());
            PerfilEmailDto perfilEmailDto = new PerfilEmailDto();
            perfilEmailDto.setIdPerfil(valVariable);
            if (tieneArchivo) {
                perfilEmailDto.setEmailSubjectType("1");
                perfilEmailDto.setEmailContentType("1");
                perfilEmailDto.setAttached(true);
            } else {
                perfilEmailDto.setEmailSubjectType("2");
                perfilEmailDto.setEmailContentType("2");
                perfilEmailDto.setAttached(false);
            }
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(uri.getRuta(), perfilEmailDto, String.class);
            logger.info("end send Email bloquearUsuario()...");

        } catch (Exception e) {
            logger.error("Error en CrossCreateLockedUserFileTasklet: " + e);
            throw new UnexpectedJobExecutionException("Error en CrossCreateLockedUserFileTasklet");
        }

        return RepeatStatus.FINISHED;

    }
}