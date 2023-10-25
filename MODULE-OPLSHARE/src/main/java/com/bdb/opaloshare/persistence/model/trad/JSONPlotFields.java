package com.bdb.opaloshare.persistence.model.trad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file war write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONPlotFields {

    /**
     * Identificador del Banco. Permite ingresar movimiento de otras entidades.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,4})$", message = "Debe ser numérico de 4 dígitos.")
    private String codEntidad;

    /**
     * Identifica la aplicación que origina el movimiento.
     */
    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9]{1,4})$", message = "Debe ser alfanumérico de 4 carácteres.")
    private String appFuente;

    /**
     * Identificador de la transacción dentro de la aplicación.
     */
    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9]{1,4})$", message = "Debe ser alfanumérico de 4 carácteres.")
    private String codTrasaccion;

    /**
     * AAAAMMDD (Fecha con la que se tramita la operación)
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{8})$", message = "Debe ser numérico de 8 dígitos.")
    private String fechaContable;

    /**
     * Código del centro de responsabilidad /oficina que irigina la operación.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,4})$", message = "Debe ser numérico de 4 dígitos.")
    private String oficinaOrigen;

    /**
     * Código del Centro de Responsabilidad / Oficina afectada por la operación.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,4})$", message = "Debe ser numérico de 4 dígitos.")
    private String oficinaDestino;

    /**
     * Identificador del tipo de producto.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,2})$", message = "Debe ser numérico de 2 dígitos.")
    private String producto;

    /**
     * Identificación del producto, Número de Cuenta / Negocio.
     * Ceros (0) si no aplica.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,16})$", message = "Debe ser numérico de 16 dígitos.")
    private String numProducto;

    /**
     * Identificador del tipo de producto.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,2})$", message = "Debe ser numérico de 2 dígitos.")
    private String tipoNegocio;

    /**
     * Identificacion del producto, numero de cuenta / negocio.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,16})$", message = "Debe ser numérico de 16 dígitos.")
    private String numeroNegocio;

    /**
     * Código de oficina recepora de la transacción.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,4})$", message = "Debe ser numérico de 4 dígitos.")
    private String cxvcvxvx;

    /**
     * Ceros
     */
    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9]{1,8})$", message = "Debe ser alfanumérico de 8 carácteres.")
    private String codCajero;

    /**
     * Ceros
     */
    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9]{1,6})$", message = "Debe ser alfanumérico de 6 carácteres.")
    private String filler;

    /**
     * Número de campos monetarios incluidos en el registro.
     * Define implicitamente el tamaño del segmento campos monetarios.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{1,2})$", message = "Debe ser numérico de 2 dígitos.")
    private String numCamposMonetarios;

    /**
     * Tamaño [bytes] de la parte variable: segmento campos monetarios + datos adicionales.
     */
    @NotNull
    @Pattern(regexp = "^([0-4]{1,4})$", message = "Debe ser numérico de 4 dígitos.")
    private String tamParteVariable;

    /**
     * Datos obligatorios para todos los registros.
     * Longitud variable.
     * Serie de campos de valor separados por identificadores de campo.
     * No se deben incluir campos con valor en cero (0).
     */
    @Valid
    @NotNull
    private List<JSONMonetaryField> camposMonetarios;
}
