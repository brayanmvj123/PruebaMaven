package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;

import java.io.IOException;
import java.util.List;

public interface ArchivoTraductorService {

    void generarArchivo(List<? extends SalTramastcDownEntity> items) throws Exception;

    void verificaGeneracionrArchivo() throws ErrorFtps, IOException;

    Boolean verificarTablaArchivoP(Long resultadoRegistros);

    void eliminarTramasDeceapdi();

}
