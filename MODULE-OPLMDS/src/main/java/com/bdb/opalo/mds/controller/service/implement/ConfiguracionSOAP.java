package com.bdb.opalo.mds.controller.service.implement;

import com.bancodebogota.rdm.classification.service.EntityMembersManagement;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfiguracionSOAP {

    @Bean(name = "mds")
    public EntityMembersManagement mds(JdbcTemplate jdbcTemplate){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(EntityMembersManagement.class);
        jaxWsProxyFactoryBean.setAddress(jdbcTemplate.queryForObject("SELECT DESC_RUTA FROM OPL_PAR_ENDPOINT_DOWN_TBL WHERE COD_RUTA = 7" , String.class));
//        jaxWsProxyFactoryBean.setAddress("http://localhost:3088/rdm/EntityMembersManagement");
        //http://10.86.154.122:3088/customer/product/service/TokenInquiryManagementV2"
        return (EntityMembersManagement) jaxWsProxyFactoryBean.create();
    }

}
