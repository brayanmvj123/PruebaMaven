package com.bdb.opalo.mds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan({"com.bdb.opaloshare.*", "com.bdb.opalo.mds.*"})
@EnableJpaRepositories(basePackages="com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages="com.bdb.opaloshare.persistence.entity")
@SpringBootApplication
public class ModuleOplmdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleOplmdsApplication.class, args);
    }

}
