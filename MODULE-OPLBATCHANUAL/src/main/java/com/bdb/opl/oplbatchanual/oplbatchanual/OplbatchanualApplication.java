package com.bdb.opl.oplbatchanual.oplbatchanual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.bdb.opaloshare.*", "com.bdb.opl.oplbatchanual.*"})
@SpringBootApplication
@EnableJpaRepositories(basePackages="com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages="com.bdb.opaloshare.persistence.entity")
public class OplbatchanualApplication {

    public static void main(String[] args) {
        SpringApplication.run(OplbatchanualApplication.class, args);
    }

}
