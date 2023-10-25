package com.bdb.platform.servfront.controller.service.implement;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.HttpClient;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.email.RequestEmail;
import com.bdb.opaloshare.persistence.model.email.ResponseEmail;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.*;
import com.bdb.platform.servfront.controller.service.interfaces.ConciliactionService;
import com.bdb.platform.servfront.mapper.Mapper;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPg;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPgSemanal;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service("ConciliationServicempl")
@CommonsLog
public class ConciliationServicempl implements ConciliactionService {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    RepositoryHisPg repositoryHisPg;

    @Autowired
    RepositorySalPg repositorySalPg;

    @Autowired
    RepositorySalPgSemanalDown repositorySalPgSemanal;

    @Autowired
    Mapper mapper;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private SharedService sharedService;

    @Autowired
    private RepositoryHisUsuarioxOficina repositoryHisUsuarioxOficina;

    @Autowired
    private RepositoryOficina repositoryOficina;


    @Override
    public ResponseEntity<RequestResult<Map<String, Object>>> updateDaily(RequestSalPg request, HttpServletRequest http) {

        Map<String, Object> response = new HashMap<>();
        SalPgDownEntity salPgDb = repositorySalPg.findByNumCdtAndCodIsinAndCtaInvAndNumTit(request.getNumCdt(), request.getCodIsin(), request.getCtaInv(), request.getNumTit());
        if (salPgDb == null) {
            response.put("Not Found", "El cdt con item '"+request.getItem()+"' no está registrados en la tabla OPL_SAL_PG_DOWN_TBL");
            log.error("El cdt con item '"+request.getItem()+"' no está registrado en la tabla OPL_SAL_PG_DOWN_TBL");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND, response));
        }

        request.setItem(salPgDb.getItem());
        SalPgDownEntity salPgEntities = mapper.salPgRequestAndSalpgDbtoSalPgEntity(salPgDb, request);

        repositorySalPg.save(salPgEntities);
        log.info("El cdt con item '" + request.getItem() +"' fue registrado en la tabla OPL_SAL_PG_DOWN_TBL");
        log.info("Registros actualizados exitosamente en la tabla OPL_SAL_PG_DOWN_TBL");

        HisPgEntity hisPgEntities = mapper.salpgtoHisPg(salPgEntities);
        repositoryHisPg.save(hisPgEntities);
        log.info("El cdt con item '" + request.getItem() +"' fue registrado en la tabla OPL_HIS_PG_DOWN_TBL");
        log.info("Registros guardados exitosamente en la tabla OPL_HIS_PG_DOWN_TBL");

        response.put("message", "Los cdts mostrados fueron actualizados exitosamente en la tabla OPL_SAL_PG_DOWN_TBL y OPL_HIS_PG_DOWN_TBL");
        response.put("cdts", request);

        return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK, response));
    }

    @Override
    public ResponseEntity<RequestResult<Map<String, Object>>> updateWeekly(RequestSalPgSemanal request, HttpServletRequest http) {

        Map<String, Object> response = new HashMap<>();
        SalPgSemanalDownEntity salPgDb = repositorySalPgSemanal.findByNumCdtAndCodIsinAndCtaInvAndNumTit(request.getNumCdt(), request.getCodIsin(), request.getCtaInv(), request.getNumTit());
        if (salPgDb == null) {
            response.put("Not Found", "El cdt con item '"+request.getItem()+"' no está registrados en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL");
            log.error("El cdt con item '"+request.getItem()+"' no está registrado en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND, response));
        }

        request.setItem(salPgDb.getItem());
        SalPgSemanalDownEntity salPgEntities = mapper.salPgRequestAndSalpgDbtoSalPgSemanalEntity(salPgDb, request);

        repositorySalPgSemanal.save(salPgEntities);
        log.info("El cdt con item '" + request.getItem() +"' fue registrado en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL");
        response.put("message", "Los cdts mostrados fueron actualizados exitosamente en la tabla OPL_SAL_PGSEMANAL_DOWN_TBL");
        response.put("cdts", request);

        return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK, response));
    }


    @Override
    public Boolean confirmationDaily() throws ErrorFtps {
        List<SalPgDownEntity> salPgList = repositorySalPg.cdtsNotReconcilied();
        if (!salPgList.isEmpty()) return false;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String nombre = "cdtsconcilia_YYYYMMdd.OPL".replace("YYYYMMdd", new SimpleDateFormat("YYYYMMdd").format(new Date(System.currentTimeMillis())));
//        DataOutputStream out = new DataOutputStream(new FileOutputStream("D:\\Archivos_andres\\Documentos\\Opalo\\opalobdb\\MODULE-OPL-SERVFRONT\\src\\main\\resources\\"+nombre));
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

        try {
            out.write(("").getBytes(StandardCharsets.UTF_8));
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            log.error("Error en al crear el archivo en el sitio FTPS: {0}", e);
            e.printStackTrace();
        }

        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        String fechaActual = serviceFTP.obtenerFechaActual();
        serviceFTP.makeFile(byteArrayOutputStream, serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual), nombre);
        updateStateDaily();
        return true;
    }



    @Override
    public Boolean confirmationWeekly() {
        List<SalPgSemanalDownEntity> salPgSemanalList = repositorySalPgSemanal.cdtsNotConcilied();
        if (!salPgSemanalList.isEmpty()) return false;
        updateStateWeekly();
        return true;
    }


    void updateStateDaily() {
        List<SalPgDownEntity> repositorySalPgList = repositorySalPg.findAll();
        repositorySalPgList.forEach(salPg ->  repositorySalPg.updateStateValue(salPg.getNumCdt(), 4));
        log.info("Se ha actualizado el estado de todos los cdts a '4'");
    }

    void updateStateWeekly() {
        List<SalPgSemanalDownEntity> repositorySalPgSemanalList = repositorySalPgSemanal.findAll();
        repositorySalPgSemanalList.forEach(salPgSemanal ->  repositorySalPgSemanal.updateStateValue(salPgSemanal.getNumCdt(), 4));
        log.info("Se ha actualizado el estado de todos los cdts a '4'");
    }


    @Override
    public Map<String, List<ResponseEmail>> sendFilesOfficeDaily(HttpServletRequest http) throws Exception {

        Map<String, List<ResponseEmail>> responseService;

        // Validaciones
        List<SalPgDownEntity> salPgList = repositorySalPg.cdtsNotReconcilied();
        if (!salPgList.isEmpty()) {
            throw new Exception("No se puede enviar información de la liquidación diaria a las oficinas ya que uno o más " +
                    "CDTs no fueron conciliados, revisar datos");
        }

        // Crea  archivos de excel en la carpeta de OPALO/OUTPUT en el sitio FTPS a partir de la tabla salPg
        String host = sharedService.generarUrl(http.getRequestURL().toString(), "OPLSERVFRONT");
        final String url = host + "OPLBATCHDIARIO/CDTSDesmaterializado/v1/simuladorPg/createFiles";
//        final String url = "http://localhost:9090/CDTSDesmaterializado/v1/simuladorPg/createFiles";
        createFilesLiquidation(url, "JOB EXCEL GENERICOS DEL MODULO OPLBATCHDIARIOS");

        // Envia correos a la respectivas oficinas con los archivos creados en el paso anterior
        List<Long> nroOficinasSorted = repositorySalPg.nroOficinasSorted();
        Map<String, Object> process = sendEmailToOffices(nroOficinasSorted, "REPORTE_OFICINA_DIARIO", http);
        responseService = process.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> (List<ResponseEmail>) e.getValue()));
        return responseService;
    }


    @Override
    public Map<String, Object> sendFilesOfficeWeekly(HttpServletRequest http) {

        Map<String, Object> responseService = new HashMap<>();

        // Validaciones
        List<SalPgSemanalDownEntity> salPgSemanalList = repositorySalPgSemanal.cdtsNotConcilied();
        if (!salPgSemanalList.isEmpty()) {
            responseService.put("validation", "No se puede enviar información de la liquidación semanal a las oficinas " +
                    "ya que uno o más CDTs no fueron conciliados, revisar datos");
            return responseService;
        }

        List<Long> nroOficinasSorted = repositorySalPgSemanal.nroOficinasSorted();
        if (nroOficinasSorted.isEmpty()) {
            String message = "No hay correos nuevos o pendientes por enviar, todos los correos correspondientes al " +
                    "periodo actual fueron enviados anteriormente.";
            responseService.put("info", message);
            log.info(message);
            return responseService;
        }

        // Crea  archivos de excel en la carpeta de OPALO/OUTPUT en el sitio FTPS a partir de la tabla salPgSemanal
        String host = sharedService.generarUrl(http.getRequestURL().toString(), "OPLSERVFRONT");
        final String url = host + "OPLBATCHSEMANAL/CDTSDesmaterializado/v1/simuladorPg/createFiles";
//        final String url = "http://localhost:9095/CDTSDesmaterializado/v1/simuladorPg/createFiles";
        createFilesLiquidation(url, "JOB EXCEL GENERICOS DEL MODULO BATCHSEMANAL");

        // Envia correos a la respectivas oficinas con los archivos creados en el paso anterior
        responseService = sendEmailToOffices(nroOficinasSorted, "REPORTE_OFICINA_SEMANAL", http);
        return responseService;
    }


    private RequestEmail jsonRequestEmail(Long nroOficina, List<String> listEmail, String folder) {

        // Parámetros para consumir el servicio de email
        RequestEmail jsonRequestEmail = new RequestEmail();
        jsonRequestEmail.setFrom("opalo@bancodebogota.com.co");
//        jsonRequestEmail.setTo(Arrays.asList("amarles@bancodebogota.com.co", "amarles@bancodebogota.com.co"));
        jsonRequestEmail.setTo(listEmail);
        jsonRequestEmail.setCc(Collections.singletonList(""));
        jsonRequestEmail.setCo(Collections.singletonList(""));
        jsonRequestEmail.setSubject("Vencimientos CDTS Desmaterializados");
        jsonRequestEmail.setMessage("" +
                "    <div>" +
                "        <br>" +
                "        <br>" +
                "        <h4>Buen dia</h4>" +
                "        <ins style=\"color: black\">Favor revisar la información de la liquidación de acuerdo a la Resolución 000055 de 2017, si" +
                "            presenta alguna diferencia informar de inmediato al Centro de Gestión Mercado de Capitales para" +
                "            verificar la información con Deceval SA</ins>" +
                "        <br>" +
                "        <br>" +
                "        <p>Utilizar este link para el envío de información <a href=\"#\" style=\"color: blue\">(CEX_FOR_084)</a></p>" +
                "        <br>" +
                "        <ul>" +
                "            <li><b>SI NO SOMOS DEPOSITANTES CANCELAR (VIA SEBRA) O RENOVAR ANTES DE LAS  2:00 P.M.</b></li>\n" +
                "            <li><b>SI SOMOS DEPOSITANTES FAVOR CANCELAR  O RENOVAR ANTES DE LAS TRES PM</b></li>\n" +
                "        </ul>" +
                "        <br>" +
                "    </div>" +
                "    <br>" +
                "    <div>" +
                "        <p>Atentamente,</p>" +
                "       <p style=\"margin: 0; padding: 0\">OPALO Cdts desmaterializados Oficina</p>" +
                "    </div>" +
                "    <hr>" +
                "");
        String nameFile = "liquidacion_"+nroOficina + "." + new SimpleDateFormat("yyyyMMdd").format(new Date())+".xlsx";
        jsonRequestEmail.setFiles(Collections.singletonList(nameFile));
        jsonRequestEmail.setFolderinOutputFTPS(folder);
        return jsonRequestEmail;
    }


    public Map<String, Object> sendEmailToOffices(List<Long> nroOficinasSorted, String folderFTPS, HttpServletRequest http)  {

        List<ResponseEmail> listRejectedEmail = new ArrayList<>();
        List<ResponseEmail> listSentEmail = new ArrayList<>();
        Map<String, Object> responseService = new HashMap<>();
        List<HisUsuarioxOficinaEntity> listUsersByOffice = repositoryHisUsuarioxOficina.findAll();

        String host = sharedService.generarUrl(http.getRequestURL().toString(), "OPLSERVFRONT");
        final String urlEmail = host + "OPLEMAIL/Email/v2/sendEmailWithFilesFTPS";
//        final String urlEmail = "http://localhost:9092/Email/v2/sendEmailWithFilesFTPS";

        for (Long nroOficina : nroOficinasSorted) {
//        for (int i = 0; i < 1; i++) { Long nroOficina=1749L;

            List<String> listEmail = new ArrayList<>();
            OficinaParDownEntity  oficina = repositoryOficina.findById(nroOficina.intValue()).get();
            List<String> emailByOffice = listUsersByOffice.stream().filter(x -> x.getNroOficina().equals(nroOficina))
                    .map(HisUsuarioxOficinaEntity::getCorreo).collect(Collectors.toList());

            if (emailByOffice.isEmpty()) {
                ResponseEmail responseEmail = new ResponseEmail(oficina.getNroOficina(), oficina.getDescOficina(),
                        "No existen correos asignados para esta oficina", new ArrayList<>());
                listRejectedEmail.add(responseEmail);
            } else {
                listEmail.addAll(emailByOffice);
            }

            RequestEmail jsonRequestEmail = jsonRequestEmail(nroOficina, listEmail, folderFTPS);

            List<HisUsuarioxOficinaEntity> hisUsuarioxOficinaEntity = listUsersByOffice.stream().filter(x -> x.getNroOficina().equals(nroOficina)).collect(Collectors.toList());
            if (!listEmail.isEmpty()) {
                try {
                        ResponseEntity<String> responseEmail = httpClient.post(jsonRequestEmail, urlEmail, "EMAIL", String.class);
                        //ResponseEntity<String> responseEmail = new ResponseEntity<>("", HttpStatus.CREATED);
                    if (responseEmail.getStatusCodeValue() == 201) {
                        log.info("Correos enviados exitosamente a la oficina: " + nroOficina);
                        ResponseEmail responseOffice = new ResponseEmail(oficina.getNroOficina(), oficina.getDescOficina(), "Correo enviado exitosamente a la oficina: " + nroOficina, hisUsuarioxOficinaEntity);
                        listSentEmail.add(responseOffice);
                    } else {
                        log.error("Falló el envío de correos a la oficina: " + nroOficina);
                        ResponseEmail responseOffice = new ResponseEmail(oficina.getNroOficina(), oficina.getDescOficina(), "Falló el envío de correos a la oficina: " + nroOficina, hisUsuarioxOficinaEntity);
                        listRejectedEmail.add(responseOffice);
                    }
                } catch (HttpClientErrorException e){
                    e.printStackTrace();
                    String messageError = e.getMostSpecificCause().toString();
                    log.error(messageError);
                    ResponseEmail responseEmail = new ResponseEmail(oficina.getNroOficina(), oficina.getDescOficina(), messageError, hisUsuarioxOficinaEntity);
                    listRejectedEmail.add(responseEmail);
                } catch (Exception e){
                    String messageError = "Falló el envío de correos a la oficina, posibles causas: 1- El servicio de envío " +
                            "de correos del módulo OPLEMAIL no está disponible (Conexión rechazada). " +
                            "2- Correo no válido, debe pertenecer al banco de Bogotá, revisar correos.";
                    log.error(e.getMessage());
                    log.error(messageError);
                    ResponseEmail responseEmail = new ResponseEmail(oficina.getNroOficina(), oficina.getDescOficina(), messageError, hisUsuarioxOficinaEntity);
                    listRejectedEmail.add(responseEmail);
                }
            }
        }

        responseService.put("Correos enviados", listSentEmail);
        responseService.put("Correos NO enviados", listRejectedEmail);
        log.info(responseService);
        return responseService;
    }

    void createFilesLiquidation(String url, String messageLogs){
        ResponseEntity<String> response = httpClient.get(url, messageLogs, String.class);
        if(response.getStatusCodeValue() != 200) {
            log.error("Falló la el servicio del creación de archivos de excel");
            throw new RuntimeException("Falló la el servicio del creación de archivos de excel");
        }
    }
}
