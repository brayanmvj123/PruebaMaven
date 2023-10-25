package com.bdb.oplbacthsemanal.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.AcuRenovatesorDownEntity;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuRenovatesorDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.opaloshare.util.Constants;
import com.bdb.opaloshare.util.Utils;
import com.bdb.oplbacthsemanal.controller.service.interfaces.ReporteExcel;
import com.bdb.oplbacthsemanal.persistence.model.XLSXSheetModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReporteCdtReinvertidos implements ReporteExcel {

    Logger logger = LoggerFactory.getLogger(getClass());

    final FTPService serviceFTP;

    final RepositoryParEndpointDown endpointRepo;

    final RepositoryAcuRenovatesorDownEntity acuRenovaRepo;

    private final RepositoryTipVarentorno varEntornoRepo;

    final Utils utils;

    public boolean excelGenerado;

    public ReporteCdtReinvertidos(@Qualifier("serviceFTPS") FTPService serviceFTP, RepositoryParEndpointDown endpointRepo,
                                  RepositoryAcuRenovatesorDownEntity acuRenovaRepo, RepositoryTipVarentorno varEntornoRepo,
                                  Utils utils) {
        this.serviceFTP = serviceFTP;
        this.endpointRepo = endpointRepo;
        this.acuRenovaRepo = acuRenovaRepo;
        this.varEntornoRepo = varEntornoRepo;
        this.utils = utils;
    }

    @Override
    public void loadDatatableToXlsxRequest() {

        SimpleDateFormat bdbFormat = new SimpleDateFormat(Constants.BDB_DATE_FORMAT);
        Date fechaActual = new Date();
        VarentornoDownEntity diasTesorCdt = varEntornoRepo.findByDescVariable(Constants.DIAS_TESORCDT);
        int diasAtras = Integer.parseInt(diasTesorCdt.getValVariable());
        Date fechaAtras = utils.subtractDays(fechaActual, diasAtras);
        int menorFecha = Integer.parseInt(bdbFormat.format(fechaAtras));
        logger.info("Dias atras reporte: {}", menorFecha);

        XLSXSheetModel xlsx = new XLSXSheetModel();
        logger.info("loadDatatableToXlsxRequest: cargando datos de BD a objeto xlsx ReporteCdtReinvertidos...");
        List<AcuRenovatesorDownEntity> salPgdigital = acuRenovaRepo.findAllMinusDate(menorFecha);
        List<List<String>> cellsToString = new ArrayList<>();

        for (AcuRenovatesorDownEntity item : salPgdigital) {
            cellsToString.add(item.toArrayValues());
        }

        xlsx.setTitle(Constants.CDTREIN);
        xlsx.setAuthor(Constants.PGDAUTOR);
        xlsx.setPassword(serviceFTP.obtenerFechaActual());
        List<String> headers = new ArrayList<>();

        headers.add("NUM-CDT-CANCELADO");//CDT-CANCELADO
        headers.add("NUM-CDT-REINVERTIDO");//CDT_REINVERTIDO
        headers.add("ID_TITULAR");//NOMBRE
        headers.add("TITULAR");//ID_CLIENTE
        headers.add("FECHA-CANCELACION");//FECHA_CANCELACION
        headers.add("FECHA-REINVERSION");//FECHA_REINVERSION
        headers.add("NRO-OFICINA");//NRO_OFICINA
        headers.add("RTE-FUENTE");//RTE-FUENTE
        headers.add("CAPITAL-CANCELADO");//CAPITAL_CANCELADO
        headers.add("INTERES-CANCELADO");//INTERES_CANCELADO
        headers.add("VALOR_REINVERTIDO");//VALOR_REINVERTIDO

        xlsx.setHeaders(headers);
        xlsx.setCells(cellsToString);
        logger.info("loadDatatableToXlsxRequest: cellsToString = {}", cellsToString);
        logger.info("loadDatatableToXlsxRequest: finalizo carga datos de BD a objeto xlsx");

        logger.info("loadDatatableToXlsxRequest: inicio generacion excel...");
        generateExcel(xlsx);
        logger.info("loadDatatableToXlsxRequest: fin generacion excel");

    }

    @Override
    public void generateExcel(XLSXSheetModel xlsx) {

        this.excelGenerado = false;

        try {

            ParEndpointDownEntity uri = endpointRepo.getParametro(Constants.FIND_URL_GENEXCEL);
            logger.info("generateExcel: endpoint a generar excel ReporteCdtReinvertidos = {}", uri.getRuta());
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

            String nombreArchivo = "CDT_DESMATRV_" + fechaActual + ".xlsx";
            logger.info("generateExcel: nombreArchivo = {}", nombreArchivo);
            serviceFTP.makeFile(baos, rutaArchivo, nombreArchivo);
            logger.info("generateExcel: excel a FTP enviado");

            this.excelGenerado = true;

        } catch (Exception e) {
            logger.error("Error generateExcel --> ReporteCdtReinvertidos: []", e);

        }


    }

}
