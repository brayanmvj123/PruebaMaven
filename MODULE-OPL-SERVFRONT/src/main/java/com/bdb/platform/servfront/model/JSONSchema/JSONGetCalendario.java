package com.bdb.platform.servfront.model.JSONSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * Objeto de respuesta para la consulta a la tabla Calendario Historico
 *
 * @author: William Vasquez
 * @version: 03/05/2021
 * @since: 03/05/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONGetCalendario {
    /**
     * Tipo de Tasa
     */
    private Integer anno;
    /**
     * Tipo de Tasa
     */
    private Integer mes;
    /**
     * Tipo de Tasa
     */
    private Integer dia;
    /**
     * valor fecha
     */
    private Integer fechaValor;
}
