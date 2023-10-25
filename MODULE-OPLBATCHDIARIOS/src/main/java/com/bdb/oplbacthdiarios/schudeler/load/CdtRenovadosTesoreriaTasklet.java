package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.AcuRenovatesorDownEntity;
import com.bdb.opaloshare.persistence.entity.OplParEndpoint;
import com.bdb.opaloshare.persistence.repository.OplParEndpointRepository;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuRenovatesorDownEntity;
import com.bdb.opaloshare.util.Constants;
import com.bdb.oplbacthdiarios.persistence.model.AcuRenovatesorDown;
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

public class CdtRenovadosTesoreriaTasklet implements Tasklet {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OplParEndpointRepository endpointRepo;
	
	@Autowired
	private RepositoryAcuRenovatesorDownEntity acuRenovaRepo;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		logger.info("start CdtRenovadosTesoreriaTasklet execute()...");
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			OplParEndpoint uri = endpointRepo.getParametro(Constants.FIND_URL_TESOCDTRENOVA);
			logger.info("uri:  " + uri.toString());
			
			AcuRenovatesorDown[] response = restTemplate.getForObject(uri.getDescRuta(), AcuRenovatesorDown[].class);
			logger.info("response:  " + response.toString());

			List<AcuRenovatesorDownEntity> salItems = new ArrayList<>();
			
			SimpleDateFormat bdbFormat = new SimpleDateFormat(Constants.BDB_DATE_FORMAT);
			Date fechaActual = new Date();
			int obtenerFecha = Integer.parseInt(bdbFormat.format(fechaActual));
			
			for(AcuRenovatesorDown acu: response) {
				
				logger.info("start interate respose[]");
				AcuRenovatesorDownEntity acuReno = new AcuRenovatesorDownEntity();
				
				acuReno.setSysCargue(obtenerFecha);
				acuReno.setCdtCancelado(acu.getCdtCancelado());
				acuReno.setCdtReinvertido(acu.getCdtReinvertido());
				acuReno.setIdCliente(acu.getIdCliente());
				acuReno.setNombre(acu.getNombre());
				acuReno.setFechaCancelacion(LocalDate.parse(acu.getFechaCancelacion(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				acuReno.setFechaReinversion(LocalDate.parse(acu.getFechaReinversion(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				acuReno.setNroOficina(acu.getNroOficina());
				acuReno.setRetencionFuente(acu.getRetencionFuente());
				acuReno.setInteresCancelado(acu.getInteresCancelado());
				acuReno.setCapitalCancelado(acu.getCapitalCancelado());
				acuReno.setValorReinvertido(acu.getValorReinvertido());
				
				
				logger.info("acuReno: " + acuReno.toString());
				salItems.add(acuReno);	

			}
			
			logger.info("acuRenovaRepo.saveAll...");
			acuRenovaRepo.saveAll(salItems);

			
		/*	restData.forEach(acu -> {
				
				logger.info("start restData.forEach(cru ->)...");
				
				AcuRenovatesorDownEntity acuReno = new AcuRenovatesorDownEntity();
				
				acuReno.setSysCargue(obtenerFecha);
				acuReno.setCdtCancelado(acu.getCdtCancelado() != null ? Long.parseLong(acu.getCdtCancelado()) : 0L);
				acuReno.setCdtReinvertido(acu.getCdtReinvertido() != null ? Long.parseLong(acu.getCdtReinvertido()) : 0L);
				acuReno.setIdCliente(acu.getIdCliente() != null ? Long.parseLong(acu.getIdCliente()) : 0L);
				acuReno.setNombre(acu.getNombre() != null ? acu.getNombre() : "NULL");
				acuReno.setFechaCancelacion(acu.getFechaCancelacion() != null ? LocalDate.parse(acu.getFechaCancelacion()) : LocalDate.parse("0000-00-00") );
				acuReno.setFechaReinversion(acu.getFechaReinversion() != null ? LocalDate.parse(acu.getFechaReinversion()) : LocalDate.parse("0000-00-00") );
				acuReno.setNroOficina(acu.getNroOficina()  != null ? Long.parseLong(acu.getNroOficina()) : 0L);
				acuReno.setRetencionFuente(acu.getRetencionFuente() != null ? new BigDecimal(acu.getRetencionFuente()) : new BigDecimal("0"));
				acuReno.setInteresCancelado(acu.getInteresCancelado() != null ? new BigDecimal(acu.getInteresCancelado()) : new BigDecimal("0"));
				acuReno.setCapitalCancelado(acu.getCapitalCancelado() != null ? new BigDecimal(acu.getCapitalCancelado()) : new BigDecimal("0"));
				acuReno.setValorReinvertido(acu.getValorReinvertido() != null ? new BigDecimal(acu.getValorReinvertido()) : new BigDecimal("0") );
				
				logger.info("acuReno: " + acuReno.toString());
				salItems.add(acuReno);			
			}); */
			
			//logger.info("acuRenovaRepo.saveAll...");
		//	acuRenovaRepo.saveAll(salItems);		
			
		}catch (Exception e) {
			logger.error("Error en CdtRenovadosTesoreriaTasklet: "+ e);
			throw new Exception("Error en CdtRenovadosTesoreriaTasklet");
		}
		
		return RepeatStatus.FINISHED;
	}

}
