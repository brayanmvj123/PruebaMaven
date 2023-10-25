package com.bdb.opalo.cofnal;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.bdb.opalo.cofnal.*"})// "com.bdb.opaloshare.*",
@EnableJpaRepositories(basePackages="com.bdb.opalo.cofnal.persistance.model.repository")
@EntityScan(basePackages="com.bdb.opalo.cofnal.persistance.model.entity")
public class AppConfig {
}
