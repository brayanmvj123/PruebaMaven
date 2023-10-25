package com.bdb.oplbacthdiarios.controller.service.implement;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalPgdigitalDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import com.bdb.opaloshare.util.Constants;
import com.bdb.oplbacthdiarios.controller.service.interfaces.ReporteExcel;
import com.bdb.oplbacthdiarios.persistence.model.XLSXSheetModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Getter;
import lombok.Setter;

@Service
public class ReportePagareDigitalServiceImpl implements ReporteExcel {

    @Autowired
    RepositoryParEndpointDown endpointRepo;

    @Autowired
    RepositorySalPgdigitalDown salPgDigitalRepo;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    Logger logger = LoggerFactory.getLogger(getClass());

    public boolean excelGenerado;

    public void loadDatatableToXlsxRequest() throws JsonProcessingException {

        XLSXSheetModel xlsx = new XLSXSheetModel();
        logger.info("loadDatatableToXlsxRequest: cargando datos de BD a objeto xlsx...");
        List<SalPgdigitalDownEntity> SalPgdigital = salPgDigitalRepo.findAll();
        List<List<String>> cellsToString = new ArrayList<>();

        for (SalPgdigitalDownEntity item : SalPgdigital) {
            cellsToString.add(item.toArrayValues());
        }

        xlsx.setTitle(Constants.PGDIGITAL);
        xlsx.setAuthor(Constants.PGDAUTOR);
        xlsx.setPassword(serviceFTP.obtenerFechaActual());
        List<String> headers = new ArrayList<>();
        headers.add("OFICINA");
        headers.add("NUM_CDT");
        headers.add("COD_ISIN");
        headers.add("TIP_ID");
        headers.add("NUM_TIT");
        headers.add("NOM_TIT");
        headers.add("INT_BRUTO");
        headers.add("RTE_FTE");
        headers.add("INT_NETO");
        headers.add("CAP_PG");
        headers.add("TIP_CTA");
        headers.add("NRO_CTA");
        headers.add("TOTAL_PAGAR");
        xlsx.setHeaders(headers);
        xlsx.setCells(cellsToString);
        logger.info("loadDatatableToXlsxRequest: cellsToString = " + cellsToString);
        logger.info("loadDatatableToXlsxRequest: finalizo carga datos de BD a objeto xlsx");

        logger.info("loadDatatableToXlsxRequest: inicio generacion excel...");
        generateExcel(xlsx);
        logger.info("loadDatatableToXlsxRequest: fin generacion excel");

    }

    public void generateExcel(XLSXSheetModel xlsx) {

        this.excelGenerado = false;

        try {

            ParEndpointDownEntity uri = endpointRepo.getParametro(Constants.FIND_URL_GENEXCEL);
            logger.info("generateExcel: endpoint a generar excel = " + uri.getRuta());
            RestTemplate restTemplate = new RestTemplate();
            logger.info("generateExcel: obteniendo blob excel...");
            byte[] result = restTemplate.postForObject(uri.getRuta(), xlsx, byte[].class);

            logger.info("generateExcel: byte to ByteArrayOutputStream");
            ByteArrayOutputStream baos = new ByteArrayOutputStream(result.length);
            baos.write(result, 0, result.length);

            logger.info("generateExcel: enviando excel a FTP...");
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
            String fechaActual = serviceFTP.obtenerFechaActual();
            String rutaArchivo = serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual);

            String nombreArchivo = "PG_CDTDIG_" + fechaActual + ".xlsx";
            logger.info("generateExcel: nombreArchivo = " + nombreArchivo);
            serviceFTP.makeFile(baos, rutaArchivo, nombreArchivo);
            logger.info("generateExcel: excel a FTP enviado");

            this.excelGenerado = true;

        } catch (Exception e) {
            logger.error("Error generateExcel --> PagareDigitalServiceImpl: " + e);

        }

    }

}
