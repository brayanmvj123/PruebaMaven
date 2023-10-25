package com.bdb.opaloshare.util;

import com.bdb.opaloshare.persistence.model.trad.JSONMonetaryField;
import com.bdb.opaloshare.persistence.model.trad.JSONPlotFields;
import lombok.extern.apachecommons.CommonsLog;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@CommonsLog
public class Plot {

    /**
     * Plot generation DECE APDI Apertura de CDT Desmaterializado.
     *
     * @param data JSON Plot fields
     * @return Plot
     */
    public static String deceApdi(JSONPlotFields data) {
        StringBuilder mon = new StringBuilder();

        for (JSONMonetaryField field : data.getCamposMonetarios()) {
            String value = String.format("%1$s", field.getValor().setScale(2, RoundingMode.FLOOR)).replace(".", "");
            mon.append(String.format("" +
                            "%1$02d" + // Identificador
                            "%2$018d", // Valor
                    Integer.parseInt(field.getIdentificador()),
                    new BigInteger(value)
            ));

            log.info("ID =====> " + field.getIdentificador());
        }

        data.setTamParteVariable("" + mon.toString().length());
        data.setNumCamposMonetarios("" + data.getCamposMonetarios().size());

        String plot = String.format("" +
                        "%1$04d" + // Codigo entidad
                        "%2$4s" + // Aplicacion fuente
                        "%3$4s" + // Codigo transaccion
                        "%4$8s" + // Fecha contable
                        "%5$04d" + // Oficina origen
                        "%6$04d" + // Oficina destino
                        "%7$02d" + // Producto
                        "%8$016d" + // Numero producto
                        "%9$02d" + // Tipo negocio
                        "%10$016d" + // Numero negocio
                        "%11$04d" + // Cxvcvxvx
                        "%12$8s" + // Codigo cajero
                        "%13$6s" + // Filler
                        "%14$02d" + // Numero de campos monetarios
                        "%15$04d", // Tamaño parte variable
                Integer.parseInt(data.getCodEntidad()),
                data.getAppFuente(),
                data.getCodTrasaccion(),
                data.getFechaContable(),
                Integer.parseInt(data.getOficinaOrigen()),
                Integer.parseInt(data.getOficinaDestino()),
                Integer.parseInt(data.getProducto()),
                new BigInteger(data.getNumProducto()),
                Integer.parseInt(data.getTipoNegocio()),
                new BigInteger(data.getNumeroNegocio()),
                Integer.parseInt(data.getCxvcvxvx()),
                data.getCodCajero().toUpperCase(),
                data.getFiller().toUpperCase(),
                Integer.parseInt(data.getNumCamposMonetarios()),
                Integer.parseInt(data.getTamParteVariable())
        );

        return String.format("%1$s%2$s", plot, mon.toString()).replace(" ", "0");
    }
}
