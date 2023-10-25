package com.bdb.opaloshare;

import com.bdb.opaloshare.persistence.model.trad.JSONMonetaryField;
import com.bdb.opaloshare.persistence.model.trad.JSONPlotFields;
import com.bdb.opaloshare.util.Plot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
public class Testing {
    public static void main(String[] args) {

        JSONPlotFields data = new JSONPlotFields();
        data.setCodEntidad("1");
        data.setAppFuente("DECE");
        data.setCodTrasaccion("APDI");
        data.setFechaContable("20190120");
        data.setOficinaOrigen("1234");
        data.setOficinaDestino("1234");
        data.setProducto("3");
        data.setNumProducto("80000700000002");
        data.setTipoNegocio("3");
        data.setNumeroNegocio("12345");
        data.setCxvcvxvx("1234");
        data.setCodCajero("0");
        data.setFiller("1234");
        data.setNumCamposMonetarios("6");
        data.setTamParteVariable("120");

        List<JSONMonetaryField> mf = new ArrayList<JSONMonetaryField>() {{
            add(new JSONMonetaryField("01", new BigDecimal("5000.12")));
            add(new JSONMonetaryField("02", new BigDecimal("5000")));
            add(new JSONMonetaryField("03", new BigDecimal("5000")));
            add(new JSONMonetaryField("04", new BigDecimal("5000")));
            add(new JSONMonetaryField("05", new BigDecimal("5000")));
            add(new JSONMonetaryField("06", new BigDecimal("5000.1234")));
        }};

        data.setCamposMonetarios(mf);

        String dd = Plot.deceApdi(data);

        System.out.println(dd);
        System.out.println("tamaño que debe tener : " + 208);
        System.out.println("tamaño real : " + dd.length());
    }
}
