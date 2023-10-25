package com.bdb.opalo.reporte.config;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${spring.application.version}") private String version;
    @Value("${server.port}") private String port;
    @Bean
    public GroupedOpenApi customOpenApi(){
        String[] packagesToScan = {"com.bdb.opalo.reporte.controller.service.control"};
        return GroupedOpenApi.builder()
                .group("custom")
                .packagesToScan(packagesToScan)
                .build();
    }
    @Bean
    public OpenAPI customeOpenAPI() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag().name("Vistas").description("Genera Archivo apartir de una Vista"));

        return new OpenAPI()
                .tags(tags)
                .components(new Components())
                .info(new Info()

                        .contact(new Contact()
                                .name("Banco de Bogota")
                                .url("https://www.bancodebogota.com")
                        )
                        .title("MODULE-OPLREPORTE")
                        .version(version)
                        .description("Servicio encargado de generar archivos que se deban ejecutar durante el día")
                        .termsOfService("Terms of service")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org"))
                ).addServersItem(new Server().url("http://localhost:"+port+"/")
                        .description("Local Server URL"))
                .addServersItem(new Server()
                        .url("https://opalodes.banbta.net:8002/OPLREPORTE/")
                        .description("Development Server URL")
                )
                .addServersItem(new Server()
                        .url("https://opaloprue.banbta.net:8002/OPLREPORTE/")
                        .description("QA Server URL")
                )
                .addServersItem(new Server()
                        .url("https://api-prod.example.com")
                        .description("Production Server URL")
                );
    }
}
