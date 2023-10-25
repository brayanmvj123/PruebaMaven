package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.HisLoginService;
import com.bdb.opalogdoracle.persistence.model.XLSXSheetModel;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.repository.RepositoryHisLoginDown;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.bdb.opaloshare.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HisLoginServiceImpl implements HisLoginService {

    @Autowired
    RepositoryHisLoginDown repositoryHisLoginDown;

    @Autowired
    RepositoryTipVarentorno repositoryTipVarentorno;

    @Autowired
    RepositoryParEndpointDown endpointRepo;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    Logger logger = LoggerFactory.getLogger(getClass());
    public boolean excelGenerado;

    public String nombreArchivoExcel;

    @Override
    public void bloquearUsuario(List<? extends HisLoginDownEntity> items) {
        repositoryHisLoginDown.saveAll(items);
    }

    @Override
    public String cantidadDias() {
        return repositoryTipVarentorno.findByDescVariable("CANT_DIAS_BLOQUEO_USR").getValVariable();
    }

    @Override
    public List<HisLoginDownEntity> generarData(String tipoArchivo) {
        List<HisLoginDownEntity> listadoUsuario;
        if (tipoArchivo.equals("2")) {
            listadoUsuario = repositoryHisLoginDown.findAll();
        } else {
            listadoUsuario = repositoryHisLoginDown.findByUserConnectionDate();
        }
        return listadoUsuario;
    }

    @Override
    public boolean generarExcelSegunEstadoUsuario(List<HisLoginDownEntity> data, String tipoArchivo) {
        logger.info("ingreso metodo generarExcelSegunEstadoUsuario");
        boolean generaArchivo = false;
        if (data.size() > 0) {
            generaArchivo = true;
            List<List<String>> cellsToString = new ArrayList<>();
            logger.info("iterando consulta a las celdas del excel");
            for (HisLoginDownEntity item : data) {
                cellsToString.add(item.toArrayValues(tipoArchivo));
            }
            logger.info("terminado de iterar consulta a las celdas del excel");

            logger.info("loadDatatableToXlsxRequest: inicio generacion excel...");
            transmitFileToFtp(generateExcel(createHeadersPropertiesExcel(cellsToString, tipoArchivo)));
            logger.info("loadDatatableToXlsxRequest: fin generacion excel");
        }
        return generaArchivo;
    }

    private XLSXSheetModel createHeadersPropertiesExcel(List<List<String>> cellsToString, String tipoArchivo) {
        logger.info("loadDatatableToXlsxRequest: inicio cargaa datos de BD a objeto xlsx");
        XLSXSheetModel xlsx = new XLSXSheetModel();
        List<String> headers = new ArrayList<>();

        xlsx.setPassword(serviceFTP.obtenerFechaActual());
        if (tipoArchivo.equals("1")) {
            nombreArchivoExcel = "Reporte_Inactivacion_Usuarios_Opalo_";
            xlsx.setTitle(Constants.TITLE_FILE_BLOQUEAR_USUARIO);
            xlsx.setAuthor(Constants.AUTHOR_FILE_BLOQUEAR_USUARIO);
            headers.add("USUARIO_DE_RED");
            headers.add("FECHA_CONEXION");
        } else {
            nombreArchivoExcel = "Reporte_Usuarios_Opalo_";
            xlsx.setTitle(Constants.TITLE_FILE_REPORTE_HIS_LOGIN);
            xlsx.setAuthor(Constants.AUTHOR_FILE_REPORTE_HIS_LOGIN);
            headers.add("IDENTIFICACION");
            headers.add("USUARIO_DE_RED");
            headers.add("PERFIL");
            headers.add("ESTADO");
            headers.add("FECHA_CONEXION");
            //headers.add("NOMBRES_DEL_FUNCIONARIO");
            //headers.add("APELLIDOS_DEL_FUNCIONARIO");
        }

        xlsx.setHeaders(headers);
        xlsx.setCells(cellsToString);

        logger.info("loadDatatableToXlsxRequest: cellsToString = " + cellsToString);
        logger.info("loadDatatableToXlsxRequest: finalizo carga datos de BD a objeto xlsx");

        return xlsx;
    }

    private ByteArrayOutputStream generateExcel(XLSXSheetModel xlsx) {

        this.excelGenerado = false;

        ParEndpointDownEntity uri = endpointRepo.getParametro(Constants.FIND_URL_GENEXCEL);
        logger.info("generateExcel: endpoint a generar excel = " + uri.getRuta());
        RestTemplate restTemplate = new RestTemplate();
        logger.info("generateExcel: obteniendo blob excel...");
        byte[] result = restTemplate.postForObject(uri.getRuta(), xlsx, byte[].class);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (Optional.ofNullable(result).isPresent()) {
            logger.info("generateExcel: byte to ByteArrayOutputStream");
            baos = new ByteArrayOutputStream(result.length);
            baos.write(result, 0, result.length);
        }

        this.excelGenerado = true;

        return baos;

    }

    private void transmitFileToFtp(ByteArrayOutputStream baos) {
        try {
            logger.info("generateExcel: enviando excel a FTP...");
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
            logger.info("generateExcel: validando si existe la carpeta del dia en el FTP...");
            if (serviceFTP.verificarCarpetaDiaria(parameters.getRuta())) {
                logger.info("generateExcel: creando la carpeta del dia en el FTP dado que no existia...");
                serviceFTP.makeDirectoryDay(parameters.getRuta());
                serviceFTP.makeSubDirectorys();
            }
            String fechaActual = serviceFTP.obtenerFechaActual();
            String rutaArchivo = serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual);

            String nombreArchivo = nombreArchivoExcel + fechaActual + ".xlsx";
            logger.info("generateExcel: nombreArchivo = " + nombreArchivo);
            serviceFTP.makeFile(baos, rutaArchivo, nombreArchivo);
            serviceFTP.disconnectFTP();
            logger.info("generateExcel: excel a FTP enviado");
        } catch (Exception e) {
            logger.error("Error generateExcel --> HisLoginServiceImpl: " + e);

        }
    }


}
