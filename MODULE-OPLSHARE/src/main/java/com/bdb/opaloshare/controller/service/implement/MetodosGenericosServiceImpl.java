package com.bdb.opaloshare.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service("MetodosGenericosServiceImpl")
public class MetodosGenericosServiceImpl implements MetodosGenericos {

    ByteArrayOutputStream archivo = new ByteArrayOutputStream();

    private String archivoTypeString;

    private List<String> archivoTypeListString;

    @Override
    public void almacenarArchivo(ByteArrayOutputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public ByteArrayOutputStream cargarArchivo() {
        return archivo;
    }

    @Override
    public void almacenarArchivo(String archivo) {
        this.archivoTypeString = archivo;
    }

    @Override
    public String cargarArchivoTypeString() {
        return archivoTypeString;
    }

    @Override
    public void almacenarArchivo(List<String> archivo) {
        this.archivoTypeListString = archivo;
    }

    @Override
    public List<String> cargarArchivoTypeListString() {
        return archivoTypeListString;
    }

}
