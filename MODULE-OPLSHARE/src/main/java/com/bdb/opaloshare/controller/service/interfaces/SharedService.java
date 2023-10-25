package com.bdb.opaloshare.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.ConexionesEntity;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public interface SharedService {

    JSONObject encabezado(Map<String, String> parametros);

    String formatoFecha();

    LocalDate formatoFechaSQL(String fecha);

    LocalDateTime formatoTimeStampSQL(String fecha);

    Map<String, String> parametros(Map<String, String> request, String nombreServicio);

    String campoEntrada(String campo);

    String conocerNumCDT();

    String generarUrlBatch(String url);

    String generarUrlSql(String url);

    String generarUrl(String url, String claveBusqueda);

    String isNullorData(String campo);

    ConexionesEntity infoCarpeta(String descripcion);

    String truncarCampo(String campo, Integer inicioLongitud, Integer finalLongitud, Integer comparador);

    String nombreArchivo(String clave);

    boolean obtenerArchivo(String nombreArchivo, String directorioAsignado, String directorioProcesado);

    String getHost(String url);

    void limpiarArchivo(ByteArrayOutputStream resource, int longitud) throws IOException;

    String reemplazarCaracterEspecial(String linea);

}
