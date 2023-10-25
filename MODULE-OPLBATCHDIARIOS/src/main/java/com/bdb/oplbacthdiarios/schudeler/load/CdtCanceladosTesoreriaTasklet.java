package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.AcuCancelatesorDownEntity;
import com.bdb.opaloshare.persistence.entity.OplParEndpoint;
import com.bdb.opaloshare.persistence.repository.OplParEndpointRepository;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuCancelatesorDownEntity;
import com.bdb.opaloshare.util.Constants;
import com.bdb.oplbacthdiarios.persistence.model.AcuCancelatesorDown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CdtCanceladosTesoreriaTasklet implements Tasklet {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OplParEndpointRepository endpointRepo;

    @Autowired
    private RepositoryAcuCancelatesorDownEntity acuCancelRepo;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        logger.info("start CdtCanceladosTesoreriaTasklet execute()...");
        RestTemplate restTemplate = new RestTemplate();

        try {

            OplParEndpoint uri = endpointRepo.getParametro(Constants.FIND_URL_TESOCDTCANCEL);
            logger.info("uri: {}", uri.toString());

            AcuCancelatesorDown[] response = restTemplate.getForObject(uri.getDescRuta(), AcuCancelatesorDown[].class);
            logger.info("response:  {}", response.length);

            List<AcuCancelatesorDownEntity> salItems = new ArrayList<>();

            SimpleDateFormat bdbFormat = new SimpleDateFormat(Constants.BDB_DATE_FORMAT);
            Date fechaActual = new Date();
            int obtenerFecha = Integer.parseInt(bdbFormat.format(fechaActual));

            for (AcuCancelatesorDown acu : response) {

                logger.info("start interate respose[]");
                AcuCancelatesorDownEntity acuCancel = new AcuCancelatesorDownEntity();

                acuCancel.setSysCargue(obtenerFecha);
                acuCancel.setIdTransaccion(acu.getIdTransaccion());
                acuCancel.setCdtCancelado(acu.getCdtCancelado());
                acuCancel.setIdCliente(acu.getIdCliente());
                acuCancel.setNombre(acu.getNombre());
                acuCancel.setFechaCancelacion(LocalDate.parse(acu.getFechaCancelacion(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                acuCancel.setFechaAbono(LocalDate.parse(acu.getFechaAbono(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                acuCancel.setNroOficina(acu.getNroOficina());
                acuCancel.setTipoCuenta(acu.getTipoCuenta());
                acuCancel.setNumeroCuenta(acu.getNumeroCuenta());
                acuCancel.setIdBeneficiario(acu.getIdBeneficiario());
                acuCancel.setNombreBeneficiario(acu.getNombreBeneficiario());
                acuCancel.setRetencionFuente(acu.getRetencionFuente());
                acuCancel.setInteresCancelado(acu.getInteresCancelado());
                acuCancel.setCapitalCancelado(acu.getCapitalCancelado());
                acuCancel.setValorAbonado(acu.getValorAbonado());

                logger.info("acuCancel: " + acuCancel.toString());
                salItems.add(acuCancel);

            }

            logger.info("acuCancelRepo.saveAll...");
            acuCancelRepo.saveAll(salItems);

        } catch (Exception e) {
            logger.error("Error en CdtCanceladosTesoreriaTasklet: " + e);
            throw new Exception("Error en CdtCanceladosTesoreriaTasklet");
        }

        return RepeatStatus.FINISHED;

    }

}
