package com.bdb.opalo.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.bdb.opaloshare.*", "com.bdb.opalo.email.*"})
@SpringBootApplication
@EnableJpaRepositories(basePackages="com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages="com.bdb.opaloshare.persistence.entity")
public class ModuleOplemailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleOplemailApplication.class, args);
    }

}
