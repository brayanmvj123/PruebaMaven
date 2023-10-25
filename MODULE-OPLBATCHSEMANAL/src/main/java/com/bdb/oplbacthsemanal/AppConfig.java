package com.bdb.oplbacthsemanal;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.bdb.opaloshare.*","com.bdb.oplbacthsemanal.*"})
@EnableJpaRepositories(basePackages="com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages="com.bdb.opaloshare.persistence.entity")
public class AppConfig {
}
