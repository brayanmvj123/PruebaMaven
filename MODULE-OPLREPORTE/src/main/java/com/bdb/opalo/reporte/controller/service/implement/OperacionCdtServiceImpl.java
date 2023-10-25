package com.bdb.opalo.reporte.controller.service.implement;

import com.bdb.opalo.reporte.controller.service.interfaces.OperacionCdtService;
import com.bdb.opalo.reporte.exceptions.NoDataException;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OperacionCdtServiceImpl implements OperacionCdtService {
    private final FTPService serviceFTP;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<String> generarArchivoOperacionCdt() throws IOException, ErrorFtps {
        List<Map<String, Object>> filas = jdbcTemplate.queryForList("SELECT * FROM OPERACIONES_CDTS_DIARIOS_VIEW");

        if (filas.isEmpty()) throw new NoDataException("No hay datos para Mostrar");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Obtener nombres de columnas
        Map<String, Object> primeraFila = filas.get(0);
        String columnNames = String.join(";", primeraFila.keySet()) + "\n";
        outputStream.write(columnNames.getBytes());

        for(Map<String, Object> fila : filas){
            List<String> valores = new ArrayList<>();

            int columna = 0;
            for (Object celda : fila.values()) {
                String celdaOficial = celda != null ? celda.toString() : "";
                if (columna == 12 || columna == 13)
                    celdaOficial = celdaOficial.replace(".", ",");
                valores.add(celdaOficial);
                columna++;
            }

            String filaOficial = String.join(";", valores) + "\n";
            outputStream.write(filaOficial.getBytes());
        }

        ParametersConnectionFTPS parametersConnectionFTPS = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parametersConnectionFTPS.getHostname(), parametersConnectionFTPS.getPuerto(), parametersConnectionFTPS.getUsername(), parametersConnectionFTPS.getPassword());

        if (serviceFTP.verificarCarpetaDiaria(parametersConnectionFTPS.getRuta())) {
            serviceFTP.makeDirectoryDay(parametersConnectionFTPS.getRuta());
            serviceFTP.makeSubDirectorys();
        }

        String fechaActual = serviceFTP.obtenerFechaActual();
        serviceFTP.makeFile(outputStream, serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual), "CDTDigitalDiario.OPL");
        serviceFTP.disconnectFTP();

        return ResponseEntity.ok("Archivo generado exitosamente");
    }
}
