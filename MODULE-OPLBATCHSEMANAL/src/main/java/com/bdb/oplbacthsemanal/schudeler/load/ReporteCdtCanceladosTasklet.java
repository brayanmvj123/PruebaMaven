package com.bdb.oplbacthsemanal.schudeler.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdb.oplbacthsemanal.controller.service.implement.ReporteCdtCancelados;

public class ReporteCdtCanceladosTasklet  implements Tasklet {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ReporteCdtCancelados reporCancel;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			reporCancel.loadDatatableToXlsxRequest();
			if(reporCancel.excelGenerado) {
				logger.info("ReporteCdtCanceladosTasklet: reporte generado ");
			}
		}catch (Exception e) {
			logger.error("Error en ReporteCdtCanceladosTasklet: "+ e);
			throw new UnexpectedJobExecutionException("Error en ReporteCdtCanceladosTasklet");
		}
		
		return RepeatStatus.FINISHED;
	}


}
