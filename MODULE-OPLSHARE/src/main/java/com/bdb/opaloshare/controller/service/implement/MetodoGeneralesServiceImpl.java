package com.bdb.opaloshare.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.MetodosGeneralesService;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


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
@Service
public class MetodoGeneralesServiceImpl implements MetodosGeneralesService {
	
	@Autowired
	private RepositoryTipVarentorno repositoryTipVarentorno;

    public String convertirRespuesta(String dato) {
        return dato.equals("SI") ? "1" : "2";
    }

    public String convertirRespuestaTipoII(String dato) {
        return dato.equals("S") ? "1" : "2";
    }

    @Override
    public String eliminarCerosIzquierda(String dato) {
        return StringUtils.stripStart(dato, "0");
    }

    @Override
    public <T> String entityToJSON(Optional<T> entity) {
        String conversion = "";
        ObjectMapper nuevo = new ObjectMapper();
        try {
            conversion = nuevo.writeValueAsString(entity.get());
            System.out.println(conversion);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return conversion;
    }

	@Override
	public String formatoFecha(String fecha) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		Date fechaFormato = formato.parse(fecha);
		SimpleDateFormat formatoDos = new SimpleDateFormat("yy/MM/dd");
		String fechaDos = formatoDos.format(fechaFormato);
		return fechaDos;
	}

	@Override
	public String nombreArchivo(String clave) {
		return repositoryTipVarentorno.findByDescVariable(clave).getValVariable();
	}
}
