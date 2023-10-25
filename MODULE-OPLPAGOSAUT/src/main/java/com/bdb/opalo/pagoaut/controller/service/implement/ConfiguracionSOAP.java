package com.bdb.opalo.pagoaut.controller.service.implement;

import com.bancodebogota.customers.product.service.DematerializedCertificateDepositManagement;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfiguracionSOAP {

    @Bean(name = "desembolso")
    public DematerializedCertificateDepositManagement desmaterializado(JdbcTemplate jdbcTemplate){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(DematerializedCertificateDepositManagement.class);
        jaxWsProxyFactoryBean.setAddress(jdbcTemplate.queryForObject("SELECT DESC_RUTA FROM OPL_PAR_ENDPOINT_DOWN_TBL WHERE COD_RUTA = 10" , String.class));
        return (DematerializedCertificateDepositManagement) jaxWsProxyFactoryBean.create();
    }

}
