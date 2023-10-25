package com.bdb.opalomcrm.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancodebogota.customers.arrangement.service.CustomerInformationNoveltyManagement;
import com.bancodebogota.customers.arrangement.service.ModCustNoveltyFaultMsg;
import com.bancodebogota.customers.arrangement.service.ModCustNoveltyRequest;
import com.bancodebogota.customers.arrangement.service.ModCustNoveltyResponse;
import com.bancodebogota.customers.involvedparty.event.CustomerNoveltyModRqType;
import com.bancodebogota.ifx.base.v1.BankInfoType;
import com.bancodebogota.ifx.base.v1.CustIdType;
import com.bancodebogota.ifx.base.v1.OtherIdentDocType;
import com.bancodebogota.ifx.base.v1.RefInfoType;
import com.bancodebogota.ifx.base.v2.NetworkTrnInfoType;
import com.bdb.opalomcrm.batchcontrollers.JSONAudit;
import com.bdb.opalomcrm.batchcontrollers.ResultJSONAudit;
import com.bdb.opalomcrm.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NovedadesSoapAudit {
	
    String ip;
	Logger logger = LoggerFactory.getLogger(getClass());
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private CustomerInformationNoveltyManagement cuswe;

	public ResultJSONAudit SoapAudit(JSONAudit pruebas, String ip) {
		
		this.ip = ip;
		
		ResultJSONAudit resultJSONAudit =  new ResultJSONAudit();
		resultJSONAudit.setStatus("N");
		resultJSONAudit.setException("SIN EXCEPTION");
		
    try {
			
			UUID uuid = UUID.randomUUID();
	        String randomUUID = uuid.toString();
	        
	        logger.info("randomUUID: " + randomUUID);
	        
	        DateFormat format = new SimpleDateFormat(Constants.SOAP_RQ_DATE_FORMAT);
	        Date date = new Date();
	        XMLGregorianCalendar gDateFormatted = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
	        
	        logger.info("gDateFormatted: " + gDateFormatted);
			
	        CustIdType custIdType = new  CustIdType();
	        custIdType.setCustLoginId(Constants.CUST_LOGIN_ID);
	        
	        BankInfoType bankInfoType  =  new BankInfoType();
	        bankInfoType.setBankId(Constants.BANK_ID);
	        
	        NetworkTrnInfoType networkTrnInfoType = new NetworkTrnInfoType();
	        networkTrnInfoType.setNetworkOwner(Constants.NETWORK_OWNER);
	        networkTrnInfoType.setTerminalId(this.ip);
	        networkTrnInfoType.setBankInfo(bankInfoType);
	        
	        OtherIdentDocType otherIdentDocType = new OtherIdentDocType();
	        otherIdentDocType.setTypeId(pruebas.getTipId());
	        otherIdentDocType.setParticipantId(pruebas.getIdTit());
	        
	        String tipNovedad = String.valueOf(49);
	        
	        RefInfoType refInfoType = new RefInfoType();
	        refInfoType.getRefId().add(tipNovedad);
	        refInfoType.getDesc().add(pruebas.getInfNovedad());
	       
			
			CustomerNoveltyModRqType customerNoveltyModRqType = new CustomerNoveltyModRqType();
			
			customerNoveltyModRqType.setRqUID(randomUUID);
			customerNoveltyModRqType.setCustId(custIdType);
			customerNoveltyModRqType.setNetworkTrnInfo(networkTrnInfoType);
			customerNoveltyModRqType.setClientDt(gDateFormatted.toString());
			customerNoveltyModRqType.setOtherIdentDoc(otherIdentDocType);
			customerNoveltyModRqType.setRefInfo(refInfoType);
			
			ModCustNoveltyRequest modCustNoveltyRequest = new ModCustNoveltyRequest();
			modCustNoveltyRequest.setCustomerNoveltyModRq(customerNoveltyModRqType);
			
			String jsonRq = mapper.writeValueAsString(modCustNoveltyRequest);
			logger.info("modCustNoveltyRequest: " + jsonRq);
			resultJSONAudit.setRequestAlSoap(jsonRq);
				
			ModCustNoveltyResponse modCustNoveltyResponse=  cuswe.modCustNovelty(modCustNoveltyRequest);
			
			String jsonRs = mapper.writeValueAsString(modCustNoveltyResponse);
	        logger.info("ModCustNoveltyResponse: " + jsonRs);
	        resultJSONAudit.setResponseDelSoap(jsonRs);

			if (modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode().equals(Constants.SOAP_RS_CODE_OK)
				|| modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode().equals(Constants.SOAP_RS_CODE_ERRGEN)) {

	        	resultJSONAudit.setStatus("Y");
		        
	        }else {
	        	
		        logger.error(Constants.JOBNOV_ERROR + " " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode());

	        } 
	        
	        logger.info("responseSoap:RqUID " + modCustNoveltyResponse.getCustomerNoveltyModRs().getRqUID());
	        logger.info("responseSoap:StatusCode " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode());
	        logger.info("responseSoap:ServerStatusCode " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getServerStatusCode());
	        logger.info("responseSoap:Severity " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getSeverity());
	        logger.info("responseSoap:ServerStatusDesc " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getServerStatusDesc());
	        logger.info("responseSoap:StatusDesc" + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusDesc()); 
	        
		} catch (ModCustNoveltyFaultMsg me) {
		      System.err.println("Error ModCustNoveltyFaultMsg: " + me);
		      logger.error("ModCustNoveltyFaultMsg:Message: " + me.getMessage());
		      resultJSONAudit.setException("ModCustNoveltyFaultMsg:Message: " + me.getMessage());

		 }catch (SOAPFaultException sr)  {
			 System.err.println("Error SOAPFaultException: " + sr);
		      resultJSONAudit.setException("Error SOAPFaultException: " + sr);
			 
		 }catch (Exception e) {
			 System.err.println("Error Exception: " + e);
		      resultJSONAudit.setException("Error Exception: " + e);
         }
		
		
		return resultJSONAudit;
		
	}
	
	

}
