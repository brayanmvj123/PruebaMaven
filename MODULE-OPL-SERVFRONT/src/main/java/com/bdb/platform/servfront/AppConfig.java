package com.bdb.platform.servfront;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Configuration
@ComponentScan({"com.bdb.opaloshare.*", "com.bdb.platform.servfront.*"})
@EnableJpaRepositories(basePackages = "com.bdb.opaloshare.persistence.repository")
@EntityScan(basePackages = "com.bdb.opaloshare.persistence.entity")
public class AppConfig {
}
