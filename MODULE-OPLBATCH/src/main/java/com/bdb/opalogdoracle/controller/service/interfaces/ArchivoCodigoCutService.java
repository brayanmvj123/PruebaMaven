package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;

import java.io.IOException;

public interface ArchivoCodigoCutService {

    void generarInformacionCut();

    void verificaGeneracionrArchivo() throws ErrorFtps, IOException;

    void limpiarTabla();

}
