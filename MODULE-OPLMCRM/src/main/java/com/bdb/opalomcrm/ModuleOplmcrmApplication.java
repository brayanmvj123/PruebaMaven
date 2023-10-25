package com.bdb.opalomcrm;

import java.util.concurrent.Executor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan({"com.bdb.opaloshare.*", "com.bdb.opalomcrm*"})
@SpringBootApplication
@EnableBatchProcessing
@EnableJpaRepositories(basePackages =  "com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages = "com.bdb.opaloshare.persistence.entity")
@EnableAsync
public class ModuleOplmcrmApplication {
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("CrmThread-");
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(ModuleOplmcrmApplication.class, args);
	}

}
