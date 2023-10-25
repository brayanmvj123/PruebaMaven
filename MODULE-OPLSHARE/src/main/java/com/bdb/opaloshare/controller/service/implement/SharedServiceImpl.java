package com.bdb.opaloshare.controller.service.implement;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.MetodosGenericos;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.ConexionesEntity;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryConexiones;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class SharedServiceImpl implements SharedService {

    JSONObject json;

    @Autowired
    private RepositoryConexiones repoConexiones;

    @Autowired
    private RepositoryTipVarentorno repositoryTipVarentorno;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    MetodosGenericos metodosGenericos;

    public JSONObject encabezado(Map<String, String> parametros) {
        this.json = new JSONObject();
        this.json.put("app", parametros.get("app"));
        parametros.remove("app");
        this.json.put("nameServiceResponse", parametros.get("nameServiceResponse"));
        parametros.remove("nameServiceResponse");
        this.json.put("date", formatoFecha());
        System.out.println("paso1");
        JSONObject parametrosRequest = new JSONObject();
        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            System.out.println("entro");
            parametrosRequest.put(entry.getKey(), entry.getValue() == null ? "null" : entry.getValue());
        }
        System.out.println("paso2");
		/*parametrosRequest.put("identificación", pruebas.getParametros().getIdentificacion() == null ? "null" : pruebas.getParametros().getIdentificacion());
		parametrosRequest.put("tipoDocumento", pruebas.getParametros().getTipoDocumento() == null ? "null" : pruebas.getParametros().getTipoDocumento());*/
        this.json.put("parametrosRequest", parametrosRequest);
        return json;
    }

    public String formatoFecha() {
        Date fechaActual = new Date();
        String formato = "dd/MM/yy HH:mm:ss";
        SimpleDateFormat simpleFormato = new SimpleDateFormat(formato);
        String obtenerFecha = simpleFormato.format(fechaActual);
        System.out.println("LA FECHA ES: " + obtenerFecha);
        return obtenerFecha;
    }

    public Map<String, String> parametros(Map<String, String> request, String nombreService) {
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("app", "OPALOBDB");
        parametros.put("nameServiceResponse", nombreService);
        for (Map.Entry<String, String> conocer : request.entrySet()) {
            parametros.put(conocer.getKey(), conocer.getValue());
        }
        return parametros;
    }

    public String campoEntrada(String campo) {
        campo = campo == null ? "" : campo;
        return campo;
    }

    @Override
    public LocalDate formatoFechaSQL(String fecha) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(fecha, date);
    }

    @Override
    public LocalDateTime formatoTimeStampSQL(String fecha) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(date.toString());
        LocalDateTime dateFormat = LocalDateTime.parse(fecha);
        System.out.println(dateFormat.toString());
        return dateFormat;
    }

    @Override
    public String conocerNumCDT() {
        return repositoryTipVarentorno.findByDescVariable("CDTDESMATERIALIZADO").getValVariable();
    }

    @Override
    public String generarUrlBatch(String url) {
        String[] cadena = url.split("OPLBATCH");
        return cadena[0];
    }

    @Override
    public String generarUrlSql(String url) {
        String[] cadena = url.split("OPLSSQLS");
        return cadena[0];
    }

    @Override
    public String generarUrl(String url, String claveBusqueda) {
        String[] cadena = url.split(claveBusqueda);
        return cadena[0];
    }

    @Override
    public String isNullorData(String campo) {
        return campo.isEmpty() ? null : truncarCampo(campo, 0, 40, 40);
    }

    public String truncarCampo(String campo, Integer inicioLongitud, Integer finalLongitud, Integer comparador) {
        return campo.length() > comparador ? campo.substring(inicioLongitud, finalLongitud) : campo;
    }

    @Override
    public String nombreArchivo(String clave) {
        log.info("SE BUSCA EL ARCHIVO BUSCADO EN EL SITIO FTPS.");
        Optional<VarentornoDownEntity> item = Optional.ofNullable(repositoryTipVarentorno.findByDescVariable(clave));
        return item.isPresent() ? item.get().getValVariable() : clave;
        //return repositoryTipVarentorno.findByDescVariable(clave);// .getValVariable();
    }

    @Override
    public boolean obtenerArchivo(String nombreArchivo, String directorioAsignado, String directorioProcesado) {
        String rutaArchivoProcesado;
        String inicioArchivo = nombreArchivo(nombreArchivo);
        try {

            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();

            //ESTE METODO SE ENCARGA DE LA CONEXION AL SITIO FTPS
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());

            serviceFTP.makeDirectoryDay(parameters.getRuta());
            serviceFTP.makeSubDirectorys();

            //LA VARIABLE FECHA ACTUAL SE GUARDA LA FECHA ACTUAL DEL SISTEMA
            String fechaActual = serviceFTP.obtenerFechaActual();

            //SE CONCATENA LA RUTA CON LA FECHA ACTUAL DONDE SE BUSCARA EL ARCHIVO
            String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%" + directorioAsignado + "%", fechaActual);

            //SE CREA LA RUTA DONDE SE GUARDARA EL ARCHIVO PROCESADO
            rutaArchivoProcesado = serviceFTP.rutaEspecifica("%" + directorioProcesado + "%", fechaActual);

            //SE OBTIENE EL ARCHIVO QUE INICIE CON LAS PRIMERAS PALABRAS DEL ARCHIVO
            String leerArchivo = serviceFTP.getFileFromSpecifiedPath(inicioArchivo, directorioAsignado);
            if (leerArchivo != null) {
                log.info("EL ARCHIVO A LEER ES: " + leerArchivo);

                //SE LE AGREGA PROCESADO AL NOMBRE DEL ARCHIVO
                String nombreArchivoProcesado = "Procesado_" + leerArchivo;

                //SE TRANSFORMA EL ARCHIVO EN BYTES
                ByteArrayOutputStream resource = serviceFTP.archivoResource(rutaArchivoOriginal + leerArchivo);

                //EL ARCHIVO PROCESADO SE GUARDA EN LA RUTA DECLARADA EN LA VARIABLE rutaArchivoProcesado
                serviceFTP.makeFile(resource, rutaArchivoProcesado, nombreArchivoProcesado);

                //ESTA VARIABLE SE ENCARGA DE LLENAR EL METODO DEL SERVICIO , EL CUAL LLEVARA LA INFORMACION DEL ARCHIVO PROCESADO
                //AL ITEMREADER
                metodosGenericos.almacenarArchivo(resource);

                //SE DESCONECTA DEL SERVIDOR FTPS
                serviceFTP.disconnectFTP();
                return true;
            } else {
                serviceFTP.disconnectFTP();
                return false;
            }
        } catch (ErrorFtps ftpErrors) {
            log.error(ftpErrors.getMessage());
            return false;
        }
    }

    @Override
    public void limpiarArchivo(ByteArrayOutputStream resourceSend, int longitud) throws IOException {

        log.info("ENTRO AL METODO EXAMINAR ARCHIVO, EL CUAL SEPARA POR LA LONGITUD DE LAS LINEAS.");

        ByteArrayOutputStream resource = resourceSend != null ? resourceSend : metodosGenericos.cargarArchivo();

        Predicate<String> stringPredicate = line -> line.length() > longitud;
        List<String> stringList = filtrarTipoRetencion(inputStream(resource), stringPredicate);

        log.info("SE TERMINO DE DIVIDIR EL ARCHIVO");

        metodosGenericos.almacenarArchivo(construirArchivoMemoria(stringList));
    }

    @Override
    public String reemplazarCaracterEspecial(String linea) {
        String lineaProcesada;
        switch (linea.length()){
            case 434:
                log.info("Se procede ajustar una linea la cual no tiene el tamaño esperado.");
                lineaProcesada = completarTamañoLinea(linea, 50, 63, 112);
                break;
            case 767:
                lineaProcesada = completarTamañoLinea(linea, 50, 73, 122);
                break;
            default:
                lineaProcesada = linea;
        }
        return lineaProcesada;
    }

    public String completarTamañoLinea(String linea, int longitudDeseada, int inicio, int fin){
        return linea.replace(linea.substring(inicio, fin), linea.substring(inicio, fin).concat(" "));
    }

    public InputStream inputStream(ByteArrayOutputStream resource) {
        return new ByteArrayInputStream(new ByteArrayResource(resource.toByteArray()).getByteArray());
    }

    public List<String> filtrarTipoRetencion(InputStream inputStream, Predicate<String> stringPredicate) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .filter(stringPredicate)
                .map(this::reemplazarCaracterEspecial)
                .collect(Collectors.toList());
    }

    public ByteArrayOutputStream construirArchivoMemoria(List<String> lista) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.write(String.join("\n", lista).getBytes());
        return byteArrayOutputStream;
    }

    @Override
    public String getHost(String url) {
        if (url.contains("https://opalo_des")) {
            System.out.println("desarrollo");
        } else if (url.contains("https://opalo_prue")) {
            System.out.println("prueba");
        } else if (url.contains("https://opalo")) {
            System.out.println("produccion");
        } else {
            System.out.println("localhost");
        }
        return null;
    }

    @Override
    public ConexionesEntity infoCarpeta(String descripcion) {
        return repoConexiones.rutaEspecifica(descripcion);
    }

}
