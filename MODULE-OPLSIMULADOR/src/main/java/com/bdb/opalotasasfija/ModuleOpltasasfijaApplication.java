package com.bdb.opalotasasfija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@ComponentScan({"com.bdb.opaloshare.*", "com.bdb.opalotasasfija.*"})
@EnableJpaRepositories(basePackages =  "com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages = "com.bdb.opaloshare.persistence.entity")
@EnableSwagger2
public class ModuleOpltasasfijaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuleOpltasasfijaApplication.class, args);
	}

}
