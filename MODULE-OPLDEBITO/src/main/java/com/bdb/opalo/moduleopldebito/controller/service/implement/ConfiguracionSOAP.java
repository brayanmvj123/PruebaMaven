package com.bdb.opalo.moduleopldebito.controller.service.implement;

import com.bancodebogota.customers.product.service.CustomerProductManagement;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfiguracionSOAP {

    @Bean(name = "debito")
    public CustomerProductManagement desmaterializado(JdbcTemplate jdbcTemplate){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(CustomerProductManagement.class);
        jaxWsProxyFactoryBean.setAddress(jdbcTemplate.queryForObject("SELECT DESC_RUTA FROM OPL_PAR_ENDPOINT_DOWN_TBL WHERE COD_RUTA = 11" , String.class));
        return (CustomerProductManagement) jaxWsProxyFactoryBean.create();
    }

}
