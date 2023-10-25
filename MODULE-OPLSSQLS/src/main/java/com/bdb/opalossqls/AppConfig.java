package com.bdb.opalossqls;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.bdb.opaloshare.*", "com.bdb.opalossqls.*"})
@EntityScan(basePackages = {"com.bdb.opaloshare.persistence.entity","com.bdb.opalossqls.persistence.entity"})
@EnableJpaRepositories(basePackages = {"com.bdb.opaloshare.persistence.repository","com.bdb.opalossqls.persistence.repository"})
public class AppConfig {
}
