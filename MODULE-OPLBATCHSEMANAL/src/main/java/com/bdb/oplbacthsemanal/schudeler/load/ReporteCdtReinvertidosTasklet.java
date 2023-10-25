package com.bdb.oplbacthsemanal.schudeler.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdb.oplbacthsemanal.controller.service.implement.ReporteCdtReinvertidos;

public class ReporteCdtReinvertidosTasklet implements Tasklet {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ReporteCdtReinvertidos reporRein;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			reporRein.loadDatatableToXlsxRequest();
			if(reporRein.excelGenerado) {
				logger.info("ReporteCdtReinvertidosTasklet: reporte generado ");
			}
		}catch (Exception e) {
			logger.error("Error en ReporteCdtReinvertidosTasklet: "+ e);
			throw new UnexpectedJobExecutionException("Error en ReporteCdtReinvertidosTasklet");
		}
		
		return RepeatStatus.FINISHED;
	}


}
