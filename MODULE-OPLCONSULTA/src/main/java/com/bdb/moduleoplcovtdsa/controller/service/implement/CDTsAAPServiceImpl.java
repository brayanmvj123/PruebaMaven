package com.bdb.moduleoplcovtdsa.controller.service.implement;

import com.bdb.moduleoplcovtdsa.controller.service.interfaces.CDTsAAPService;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.JSONConsultaPPE;
import com.bdb.opaloshare.controller.service.interfaces.ErrorsJsonService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeModel;
import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownModel;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTsDesmaterializado;
import com.bdb.opaloshare.persistence.repository.RepositoryDatosCliente;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CDTsAAPServiceImpl implements CDTsAAPService {

    @Autowired
    private RepositoryCDTsDesmaterializado repoCDTSDes;

    @Autowired
    private RepositoryCDTSLarge repoHisCDT;

    @Autowired
    private RepositoryDatosCliente repositoryDatosCliente;

    @Autowired
    private SharedService serviceShared;

    @Autowired
    private ErrorsJsonService serviceErrorJson;

    @Autowired
    private RepositoryCDTsDesmaterializado repoOracle;

    @Override
    public String consCDTSDesAAP(JSONConsultaPPE request) {
        Map<String, String> parametros = parseoJson(request);
        String nombreService = "consCDTSDesAAPRespuesta";
        try {
            if (verificarConsulta(request)) {
                String identificacion = request.getParametros().getIdentificacion() != null ? "%" + request.getParametros().getIdentificacion() : null;
                String numeroProducto = request.getParametros().getNumeroProducto();
                String tipoDocumento = request.getParametros().getTipoDocumento();
                System.out.println("antes de la consulta");
                List<MaeDCVTempDownModel> queryResult = repoCDTSDes.consultaCDTsAAP(identificacion, numeroProducto, tipoDocumento); //findSql(verificarCampos(parametros));

                System.out.println("valor del CDT: " + queryResult.size());

                JSONObject json = serviceShared.encabezado(serviceShared.parametros(parametros, nombreService));

                json.put("countTitle", queryResult.size());

                System.out.println("BOOLEAN: "+verificarCantidadClientes(identificacion, tipoDocumento));
                if (verificarCantidadClientes(identificacion, tipoDocumento)) {
                    if (queryResult.size() == 0) {
                        System.out.println("ENTRO A VERIFICAR EN LA TABLA CDT");
                        Integer tipoDocumentoConversion = tipoDocumento == null ? null : Integer.parseInt(tipoDocumento);
                        List<HisCDTSLargeModel> queryHisCDTResult = repoHisCDT.consultaHisCDTsAAP(identificacion, numeroProducto, tipoDocumentoConversion);
                        if (queryHisCDTResult.size() != 0) {
                            json.put("countTitle", queryHisCDTResult.size());
                            json.put("status", HttpStatus.OK);
                            json.put("titulo", queryHisCDTResult);
                        } else {
                            json.put("status", HttpStatus.NO_CONTENT);
                            JSONObject titulo = new JSONObject();
                            titulo.put("message", "El cliente no cuenta con CDTs");
                            json.put("titulo", titulo);
                        }
                    } else {
                        json.put("status", HttpStatus.OK);
                        json.put("titulo", queryResult);
                        Integer tipoDocumentoConversion = tipoDocumento == null ? null : Integer.parseInt(tipoDocumento);
                        List<HisCDTSLargeModel> queryHisCDTResult = repoHisCDT.consultaHisCDTsAAP(identificacion, numeroProducto, tipoDocumentoConversion);
                        System.out.println("ANTES DEL IF TMP - CDTS: "+queryHisCDTResult.size());
                        if (queryHisCDTResult.size() != 0){
                            System.out.println("ENTROOOOOOOOO A LA CONSULTA CDT");
                            queryHisCDTResult.forEach(data -> {
                                JSONObject objectCDT = new JSONObject(data);
                                json.getJSONArray("titulo").put(objectCDT);
                            });
                        }
                    }
                }else{
                    json.put("status", HttpStatus.NO_CONTENT);
                    JSONObject titulo = new JSONObject();
                    titulo.put("message", "El cliente no cuenta con CDTs");
                    json.put("titulo", titulo);
                }
                return json.toString();
            } else {
                //return serviceErrorJson.diagnostico(parametros, nombreService);
                return serviceErrorJson.problemParamRequest();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return serviceErrorJson.diagnostico(parametros, nombreService);
        }

    }

    public Map<String, String> parseoJson(JSONConsultaPPE request) {
        Map<String, String> parametros = new HashMap<>();
        JSONObject json = new JSONObject(request);
        System.out.println(json.get("parametros"));
        Iterator<?> valor = json.getJSONObject("parametros").keys();
        while (valor.hasNext()) {
            String key = (String) valor.next();
            System.out.println(key);
            System.out.println(json.getJSONObject("parametros").get(key).toString());
            parametros.put(key, json.getJSONObject("parametros").get(key).toString());
        }
        System.out.println(parametros.size());
        //parametros = verificarCampos(parametros);
        return parametros;
    }

    public Map<String, String> verificarCampos(Map<String, String> mapaParameters) {
        Map<String, String> parametrosSql = new HashMap<>();
        for (Map.Entry<String, String> entry : mapaParameters.entrySet()) {
            System.out.println(entry.getKey());
            switch (entry.getKey()) {
                case "identificacion":
                    parametrosSql.put("idTit", entry.getValue());
                    break;
                case "tipoDocumento":
                    parametrosSql.put("tipId", entry.getValue());
                    break;
                case "numeroProducto":
                    parametrosSql.put("numCDT", entry.getValue());
                    break;
                default:
                    break;
            }
        }
        return parametrosSql;
    }

    public boolean verificarConsulta(JSONConsultaPPE request) {
        String identificacion = serviceShared.campoEntrada(request.getParametros().getIdentificacion());
        System.out.println("verificar identifcaicon: " + identificacion);
        String numeroProducto = serviceShared.campoEntrada(request.getParametros().getNumeroProducto());
        System.out.println("verificar identifcaicon: " + numeroProducto);
        String tipoDocumento = serviceShared.campoEntrada(request.getParametros().getTipoDocumento());
        System.out.println("verificar identifcaicon: " + tipoDocumento);

        if (!identificacion.isEmpty() && !tipoDocumento.isEmpty() && !numeroProducto.isEmpty()) {
            System.out.println("entro if");
            return true;
        } else if (!identificacion.isEmpty() && !tipoDocumento.isEmpty() && numeroProducto.isEmpty()) {
            System.out.println("entro if if");
            return true;
        } else if (!numeroProducto.isEmpty() && identificacion.isEmpty() && tipoDocumento.isEmpty()) {
            System.out.println("entro if if if");
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarCantidadClientes(String identificacion, String tipoDocumento) {
        if (identificacion != null && !tipoDocumento.isEmpty()) {
            return (repoCDTSDes.cantidadClientesEncontrados(identificacion, tipoDocumento).size() == 1 ||
                    repoCDTSDes.cantidadClientesEncontrados(identificacion, tipoDocumento).size() == 0)
                && (repositoryDatosCliente.cantidadClientesEncontrados(identificacion, Integer.parseInt(tipoDocumento)).size() == 1 ||
                    repositoryDatosCliente.cantidadClientesEncontrados(identificacion, Integer.parseInt(tipoDocumento)).size() == 0);
        } else {
            return true;
        }
    }

}
