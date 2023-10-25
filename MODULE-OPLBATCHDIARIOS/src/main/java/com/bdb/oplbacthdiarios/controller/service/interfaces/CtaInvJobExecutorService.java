/*
 * Copyright (c) 2021 Banco de Bogotá. All Rights Reserved.
 * <p>
 * ACCIONESBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.oplbacthdiarios.controller.service.interfaces;

import org.springframework.batch.core.JobParameters;

import java.util.concurrent.CompletableFuture;

/**
 * Interface del servicio encargado de cargar y realizar el cruce de cuentas inversionistas por accionistas principales
 * y secundarios
 *
 * @author: Andres Marles
 * @version: 20/10/2021
 * @since: 20/10/2021
 */
public interface CtaInvJobExecutorService {
    CompletableFuture<String> execJobCtaInv(JobParameters jobParameters) throws InterruptedException;
}
