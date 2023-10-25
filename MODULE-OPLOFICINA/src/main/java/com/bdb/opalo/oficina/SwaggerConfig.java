package com.bdb.opalo.oficina;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("DEV: https://opalo_des.banbta.net:8002/OPLOFICINA \n QA: https://opalo_prue.banbta.net:8002/OPLOFICINA \n PROD: https://opalo.bancobogota.net:8002/OPLOFICINA")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bdb.opalo.oficina.controller.service.control"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("MODULE-OPLOFICINA",
                "Este modulo realiza tareas de carga, procesamiento y almacenamiento de archivos para el cierre de oficinas.",
                version,
                "Terms of service",
                new Contact("Banco de Bogota", "www.bancodebogota.com.co", "myeaddress@company.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
