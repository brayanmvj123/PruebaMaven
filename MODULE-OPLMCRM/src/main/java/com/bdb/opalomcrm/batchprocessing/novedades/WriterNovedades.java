package com.bdb.opalomcrm.batchprocessing.novedades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.xml.bind.UnmarshalException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.bdb.opalomcrm.common.Constants;
import com.bdb.opaloshare.persistence.entity.SalNovCrmCliEntity;
import com.bdb.opaloshare.persistence.repository.OplNovcrmcliSalDownTblRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WriterNovedades implements ItemWriter<SalNovCrmCliEntity> {
		
	@Autowired
	private CustomerInformationNoveltyManagement cuswe;
	
	String ip;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OplNovcrmcliSalDownTblRepository repoOplNov;
	
	@Override
	public void write(List<? extends SalNovCrmCliEntity> itemsNov) throws Exception, UnmarshalException {
		
		ObjectMapper mapper = new ObjectMapper();
		for (SalNovCrmCliEntity tempNov : itemsNov) {
			
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
	        otherIdentDocType.setTypeId(tempNov.getTipId());
	        otherIdentDocType.setParticipantId(tempNov.getIdTit());
	        
	        String tipNovedad = String.valueOf( tempNov.getTipNovedad());
	        
	        RefInfoType refInfoType = new RefInfoType();
	        refInfoType.getRefId().add(tipNovedad);
	        refInfoType.getDesc().add(tempNov.getInfNovedad());
	       
			
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
				
			ModCustNoveltyResponse modCustNoveltyResponse=  cuswe.modCustNovelty(modCustNoveltyRequest);
			
			String jsonRs = mapper.writeValueAsString(modCustNoveltyResponse);
	        logger.info("ModCustNoveltyResponse: " + jsonRs);  

			if (modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode().equals(Constants.SOAP_RS_CODE_OK)
				|| modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode().equals(Constants.SOAP_RS_CODE_ERRGEN)) {

	        	tempNov.setStatus(Constants.NOV_MARCA_BUS_SOAP_Y);
		        repoOplNov.save(tempNov);
		        
	        }else {
	        	
		        logger.error(Constants.JOBNOV_ERROR + " " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode());

	        } 
	        
	        logger.info("responseSoapSAlNovCrm:RqUID " + modCustNoveltyResponse.getCustomerNoveltyModRs().getRqUID());
	        logger.info("responseSoapSAlNovCrm:StatusCode " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusCode());
	        logger.info("responseSoapSAlNovCrm:ServerStatusCode " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getServerStatusCode());
	        logger.info("responseSoapSAlNovCrm:Severity " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getSeverity());
	        logger.info("responseSoapSAlNovCrm:ServerStatusDesc " + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getServerStatusDesc());
	        logger.info("responseSoapSAlNovCrm:StatusDesc" + modCustNoveltyResponse.getCustomerNoveltyModRs().getStatus().getStatusDesc()); 
	        
		} catch (ModCustNoveltyFaultMsg me) {
		      System.err.println("Error ModCustNoveltyFaultMsg: " + me);
		      logger.error("ModCustNoveltyFaultMsg:Message: " + me.getMessage());

		 }catch (SOAPFaultException sr)  {
			 System.err.println("Error SOAPFaultException: " + sr);
			 
		 }catch (Exception e) {
			 System.err.println("Error Exception: " + e);
         }
	        
		}
		
	}

}
