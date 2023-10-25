/*
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
package com.bdb.platform.servfront.model.JSONSchema;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Objeto de respuesta para la consulta a la tabla de salida pgSemanal
 *
 * @author: Andres Marles
 * @version: 16/12/2020
 * @since: 16/12/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSalPgSemanal {

    @Schema(description = "Descripción: Item.", name = "item", type = "Long", required = true,
            example = "2236")
    @NotNull
    private Long item;

    @Schema(description = "Descripción: Número del CDT.", name = "numCdt", type = "Long", required = true,
            example = "250172020")
    @NotNull
    private Long numCdt;

    @Schema(description = "Descripción: Código Isin del CDT.", name = "codIsin", type = "String", required = true,
            example = "COB01CD0ADO7")
    @NotBlank
    private String codIsin;

    @Schema(description = "Descripción: Número de cuenta inversionista del titular del CDT.", name = "ctaInv",
            type = "String", required = true, example = "01233289")
    @NotBlank
    private String ctaInv;

    @Schema(description = "Descripción: Número de identificación del titular.", name = "numTit", type = "String",
            required = true, example = "8002561436")
    @NotBlank
    private String numTit;

    @Schema(description = "Descripción: Tipo de plazo: 1=Día, 2=Mes.", name = "tipPlazo", type = "String",
            required = true, example = "1")
    @NotBlank
    private String tipPlazo;

    @Schema(description = "Descripción: Plazo en días o meses según el tipo de plazo escogido.", name = "plazo",
            type = "String", required = true, example = "360")
    @NotBlank
    private String plazo;

    @Schema(description = "Descripción: Tipo de base: 1=365, 2=360, 3=real.", name = "tipBase", type = "String",
            required = true, example = "2")
    @NotBlank
    private String tipBase;

    @Schema(description = "Descripción: Tasa nominal del CDT.", name = "tasaNom", type = "String", required = true,
            example = "5.1500")
    @NotBlank
    private String tasaNom;

    @Schema(description = "Descripción: Tasa efectiva del CDT.", name = "tasaEfe", type = "String", required = true,
            example = "5.1500")
    @NotBlank
    private String tasaEfe;

    @Schema(description = "Descripción: Valor de interés bruto del CDT.", name = "intBruto", type = "String",
            required = true, example = "21925766.0000")
    @NotBlank
    private String intBruto;

    @Schema(description = "Descripción: Valor de interés neto del CDT.", name = "intNeto", type = "String",
            required = true, example = "21048735.0000")
    @NotBlank
    private String intNeto;

    @Schema(description = "Descripción: Valor de la retención en la fuente del CDT.", name = "rteFte",
            type = "String", required = true, example = "877031.0000")
    @NotBlank
    private String rteFte;

    @Schema(description = "Descripción: Valor nominal del CDT.", name = "valorNominal", type = "String",
            required = true, example = "425743025.0000")
    @NotBlank
    private String valorNominal;

    @Schema(description = "Descripción: Valor del capital a pagar del CDT.", name = "capPg", type = "String",
            required = true, example = "425743025.0000")
    @NotBlank
    private String capPg;

    @Schema(description = "Descripción: Valor total neto a pagar del CDT, incluye valor a capital, intereses y retención" +
            " el la fuente.", name = "totalPagar", type = "String", required = true, example = "446791760.0000")
    @NotBlank
    private String totalPagar;

    @Schema(description = "Descripción: Valor del Spread.", name = "spread", type = "String", required = true,
            example = "0")
    @NotBlank
    private String spread;

    @Schema(description = "Descripción: Factor de liquidación calculado por OPALO.", name = "factorOpl",
            type = "BigDecimal", required = true, example = "7")
    @NotNull
    private BigDecimal factorOpl;

    @Schema(description = "Descripción: Factor de liquidación calculado por Dcvsa.", name = "factorDcvsa",
            type = "BigDecimal", required = true, example = "0.002375")
    @NotNull
    private BigDecimal factorDcvsa;

    @Schema(description = "Descripción: Tipo de periodicidad: 0=Al plazo, 1= Mensual, 2=Bimestral, 3=Trimestral, etc.",
            name = "tipPeriodicidad", type = "String", required = true, example = "0")
    @NotBlank
    private String tipPeriodicidad;

    @Schema(description = "Descripción: Tipo de identificación del titular por código: 1=Fija, 2=DTF, 3=IPC, " +
            "6=IBR Un mes, 7=IBR Overnight, 8=IBR Tres Meses, 9=IBR Seis Meses.", name = "tipTasa", type = "String",
            required = true, example = "1")
    @NotBlank
    private String tipTasa;

}
