package com.bdb.opalomcrm.batchcontrollers;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdb.opalomcrm.common.Constants;
import com.bdb.opalomcrm.impl.NovedadesSoapAudit;
import com.bdb.opalomcrm.interfaces.NovedadesLoaderService;
import com.bdb.opalomcrm.interfaces.CrmJobExecutorService;
import com.bdb.opaloshare.persistence.model.response.RequestResult;

@RestController
@RequestMapping("/batchNovedades/v1")
public class JobNovedadesController {
	
	@Autowired
	NovedadesSoapAudit audit;

	@Autowired
	CrmJobExecutorService CrmJobExecutorService;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	NovedadesLoaderService novedadesLoader;

	@Autowired
	@Qualifier("novedadesBeanJob")
	Job novedadesBeanJob;

	Logger logger = LoggerFactory.getLogger(getClass());

	@CrossOrigin
	@GetMapping(value = "runDesmarcacion", produces = { "application/json" })
	public ResponseEntity<RequestResult<ResultJSON>> handleNovedades(HttpServletRequest request) throws Exception {

		RequestResult<ResultJSON> result;
		ResultJSON resultJSON = new ResultJSON();

		LocalDate date = LocalDate.now();

		novedadesLoader.novedadesLoader();

		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.addString("ip", request.getRemoteAddr()).toJobParameters();

		CompletableFuture<String> resJob = CrmJobExecutorService.execJob(jobParameters);

		logger.info(resJob.toString());

		resultJSON.setDate(date.toString());
		resultJSON.setMessage(Constants.MSG_JOBNOV_INVOCADO);
		result = new RequestResult<>(request, HttpStatus.OK);
		result.setResult(resultJSON);

		return ResponseEntity.ok(result);
	}
	
	@CrossOrigin
	@PostMapping(value = "auditMarcacionCrm", produces = { "application/json" })
	public ResponseEntity<RequestResult<ResultJSONAudit>> auditMarcacionCrm(HttpServletRequest request, @RequestBody JSONAudit pruebas) throws Exception {

		RequestResult<ResultJSONAudit> result;
		ResultJSONAudit resultJSON = audit.SoapAudit(pruebas, request.getRemoteAddr());

        result = new RequestResult<>(request, HttpStatus.OK);
		result.setResult(resultJSON);

		return ResponseEntity.ok(result);
	}
}