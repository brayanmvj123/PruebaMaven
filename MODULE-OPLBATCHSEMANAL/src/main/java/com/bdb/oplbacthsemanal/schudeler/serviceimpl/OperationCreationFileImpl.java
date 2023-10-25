package com.bdb.oplbacthsemanal.schudeler.serviceimpl;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.HttpClient;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.model.jsonschema.XLSX.XLSXSheetModel;
import com.bdb.opaloshare.persistence.model.jsonschema.semanal.JSONGetSalPgSemanal;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgSemanalDown;
import com.bdb.oplbacthsemanal.mapper.MapperReportPg;
import com.bdb.oplbacthsemanal.schudeler.services.OperationCreationFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteSource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class OperationCreationFileImpl implements OperationCreationFile {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    private RepositorySalPgSemanalDown repositorySalPgSemanalDown;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private MapperReportPg mapperReportPg;

    @Autowired
    private ObjectMapper objectMapper;


    private static final String[] HEADERS = new String[] {
            "Oficina", "Depositante", "Número Cdt", "Código Isin", "Cta Inv", "Código Id", "Núm Tit","Nom Tit",
            "Fecha Emi","Fecha Ven","Fecha Prox Pago","Tip Plazo","Plazo","Tip Base","Tip Periodicidad", "Tip Tasa",
            "Tasa Efec", "Tasa Nom", "Valor Nom", "Int Bruto","Retención","Int Neto","Cap Pagar","Tot Pagar","Posición",
            "Factor Dcvsa", "Factor Opl","Spread", "Estado","Descripción Posicion", "Conciliación","Codigo Producto", "Fecha Creación"
    };


    @Override
    public void creationFile(String host) throws Exception {

        List<JSONGetSalPgSemanal> salPgSemanalList = mapperReportPg.listColumnsReportPgWeeklytoJSONGetSalPgSemanal(repositorySalPgSemanalDown.cdtsReconcilied());
        List<Long> nroOficinasSorted = salPgSemanalList.stream().map(x -> Long.valueOf(x.getNroOficina())).distinct().sorted().collect(Collectors.toList());

        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        serviceFTP.makeSubDirectorys();
        for (Long nroOficina : nroOficinasSorted) {

            List<List<String>> cells = new ArrayList<>();
            List<JSONGetSalPgSemanal> jsonGetSalPgSemanaSorted = salPgSemanalList.stream().filter(x -> Long.valueOf(x.getNroOficina()).equals(nroOficina))
                        .sorted(Comparator.comparing(JSONGetSalPgSemanal::getItem)).collect(Collectors.toList());

            jsonGetSalPgSemanaSorted.forEach(item -> {
                Map<String, String> map = objectMapper.convertValue(item, new TypeReference<Map<String, String>>(){});
                List<String> valores = new ArrayList<>(map.values()).subList(1, map.values().size());
                cells.add(valores);
            });

            XLSXSheetModel request = new XLSXSheetModel();
            request.setTitle("liquidacion_"+nroOficina);
            request.setAuthor("Opalo");
            request.setPassword(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            request.setHeaders(Arrays.asList(HEADERS));
            request.setCells(cells);

            final String url = host + "OPLSERVFRONT/file/excel/generation";
//            final String url = "http://localhost:9091/file/excel/generation";

            ResponseEntity<byte[]> response = httpClient.post(request, url, "FILE EXCEL GENERATION", byte[].class);
            byte[] file = response.getBody();
            String nameFile = Objects.requireNonNull(response.getHeaders().get("Content-Disposition"), "La cabecera 'Content-Disposition'" +
                    "no debe ser nula, revisar respuesta del servicio "+url).get(0).split("filename=")[1];


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            DataOutputStream out = new DataOutputStream(new FileOutputStream("C:\\Users\\andre\\Documents"+"\\OPALO\\" + serviceFTP.obtenerFechaActual() + "\\OUTPUT\\REPORTE_OFICINA_SEMANAL\\" + nameFile));
            DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

            InputStream is = ByteSource.wrap(Objects.requireNonNull(file, "El archivo obtenido en el servicio '"+url +"' " +
                    "no debe ser nulo, revisar respuesta de este servicio")).openStream();
            int nRead;
            try {
                while ((nRead = is.read(file, 0, file.length)) != -1) out.write(file, 0, nRead);
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (Exception e) {
                log.error("Error en al crear el archivo en el sitio FTPS: {0}", e);
                throw new Exception("Error en al crear el archivo en el sitio FTPS: " +e);
            }

            String fechaActual = serviceFTP.obtenerFechaActual();
            String path = "/OPALO/"+fechaActual+"/OUTPUT/REPORTE_OFICINA_SEMANAL/";
            serviceFTP.makeFile(byteArrayOutputStream, path, nameFile);
        }
    }
}
