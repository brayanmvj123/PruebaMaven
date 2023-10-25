package com.bdb.opalo.reporte.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface OperacionCdtService {
    ResponseEntity<String> generarArchivoOperacionCdt() throws IOException, ErrorFtps;
}
