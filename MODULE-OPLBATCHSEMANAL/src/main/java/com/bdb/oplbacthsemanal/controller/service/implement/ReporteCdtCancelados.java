package com.bdb.oplbacthsemanal.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.AcuCancelatesorDownEntity;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuCancelatesorDownEntity;
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
public class ReporteCdtCancelados implements ReporteExcel {

    Logger logger = LoggerFactory.getLogger(getClass());

    final FTPService serviceFTP;

    final RepositoryParEndpointDown endpointRepo;

    final RepositoryAcuCancelatesorDownEntity acuCalcelRepo;

    private final RepositoryTipVarentorno varEntornoRepo;

    final Utils utils;

    public boolean excelGenerado;

    public ReporteCdtCancelados(@Qualifier("serviceFTPS") FTPService serviceFTP, RepositoryParEndpointDown endpointRepo, RepositoryAcuCancelatesorDownEntity acuCalcelRepo, RepositoryTipVarentorno varEntornoRepo, Utils utils) {
        this.serviceFTP = serviceFTP;
        this.endpointRepo = endpointRepo;
        this.acuCalcelRepo = acuCalcelRepo;
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
        logger.info("loadDatatableToXlsxRequest: cargando datos de BD a objeto xlsx ReporteCdtCancelados...");
        List<AcuCancelatesorDownEntity> salPgdigital = acuCalcelRepo.findAllMinusDate(menorFecha);
        List<List<String>> cellsToString = new ArrayList<>();

        for (AcuCancelatesorDownEntity item : salPgdigital) {
            cellsToString.add(item.toArrayValues());
        }

        xlsx.setTitle(Constants.CDTCANC);
        xlsx.setAuthor(Constants.PGDAUTOR);
        xlsx.setPassword(serviceFTP.obtenerFechaActual());
        List<String> headers = new ArrayList<>();

        headers.add("NUM-CDT");//CDT_CANCELADO
        headers.add("TITULAR");//NOMBRE
        headers.add("ID_TITULAR");//ID_CLIENTE
        headers.add("FECHA-CANCELACION");//FECHA_CANCELACION
        headers.add("FECHA-ABONO");//FECHA_ABONO
        headers.add("NRO-OFICINA");//NRO_OFICINA
        headers.add("TIPO-CUENTA");//TIPO_CUENTA
        headers.add("NUMERO-CUENTA"); //NUMERO_CUENTA
        headers.add("TIT-CUENTA-ABONO");//NOMBRE_BENEFICIARIO
        headers.add("ID-TIT-CUENTA");//ID_BENEFICIARIO
        headers.add("RTE-FUENTE");//RTE-FUENTE
        headers.add("VALOR-ABONADO");//VALOR_ABONADO
        headers.add("VALOR_ABONADO_TOTAL");//VALOR_ABONADO

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
            logger.info("generateExcel: endpoint a generar excel ReporteCdtCancelados = {}", uri.getRuta());
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

            String nombreArchivo = "CDT_DESMATCDO_" + fechaActual + ".xlsx";
            logger.info("generateExcel: nombreArchivo = {}", nombreArchivo);
            serviceFTP.makeFile(baos, rutaArchivo, nombreArchivo);
            logger.info("generateExcel: excel a FTP enviado");

            this.excelGenerado = true;

        } catch (Exception e) {
            logger.error("Error generateExcel --> ReporteCdtCancelados: []", e);
        }


    }

}
