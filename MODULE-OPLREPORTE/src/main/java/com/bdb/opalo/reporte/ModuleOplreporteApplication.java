package com.bdb.opalo.reporte;

import com.bdb.opalo.reporte.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.bdb.opaloshare.*","com.bdb.opalo.reporte.*"})
@EnableJpaRepositories(basePackages = "com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages = "com.bdb.opaloshare.persistence.entity")
@SpringBootApplication
@Import(SwaggerConfig.class)
@ComponentScan(basePackages = "com.bdb.opalo.reporte.exceptions")
public class ModuleOplreporteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleOplreporteApplication.class, args);
    }
}
