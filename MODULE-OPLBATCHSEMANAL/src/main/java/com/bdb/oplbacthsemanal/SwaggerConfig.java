package com.bdb.oplbacthsemanal;

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

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .host("DEV: https://opalo_des.banbta.net:8002/ \n QA: https://opalo_prue.banbta.net:8002/ \n PROD: https://opalo.bancobogota.net:8002")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bdb.oplbacthsemanal.controller.service.control"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("MODULE-OPLBATCHSEMANAL",
                "Este modulo realiza tareas de carga, procesamiento y almacenamiento de archivos.",
                version,
                "Terms of service",
                new Contact("Banco de Bogota", "www.bancodebogota.com.co", "myeaddress@company.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
