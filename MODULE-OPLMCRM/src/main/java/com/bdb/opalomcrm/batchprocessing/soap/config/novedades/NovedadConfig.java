package com.bdb.opalomcrm.batchprocessing.soap.config.novedades;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bancodebogota.customers.arrangement.service.CustomerInformationNoveltyManagement;
import com.bdb.opaloshare.persistence.repository.OplParEndpointRepository;

@Configuration
public class NovedadConfig {
	
	@Autowired
	OplParEndpointRepository endPointRepo;
		 
	  @Bean(name = "novedadProxy")
	  public CustomerInformationNoveltyManagement novedadProxy() {
		  
		String endPoint = endPointRepo.getParametro();
		  
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
	    jaxWsProxyFactoryBean.setServiceClass(CustomerInformationNoveltyManagement.class);
	    jaxWsProxyFactoryBean.setAddress(endPoint);

	    return (CustomerInformationNoveltyManagement) jaxWsProxyFactoryBean.create();
	  }
	 
	  

}
