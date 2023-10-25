package com.bdb.opaloshare.controller.service.interfaces;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface MetodosGenericos {

    void almacenarArchivo(ByteArrayOutputStream archivo);
    ByteArrayOutputStream cargarArchivo();

    void almacenarArchivo(String archivo);
    String cargarArchivoTypeString();

    void almacenarArchivo(List<String> archivo);

    List<String> cargarArchivoTypeListString();
}
