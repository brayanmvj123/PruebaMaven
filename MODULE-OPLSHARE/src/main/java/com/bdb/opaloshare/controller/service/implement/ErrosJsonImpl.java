package com.bdb.opaloshare.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.ErrorsJsonService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrosJsonImpl implements ErrorsJsonService {

	@Autowired
	private SharedService serviceShared;
	
	@Override
	public String diagnostico(Map<String, String> parametersRequest , String nombreService) {
				
		JSONObject json = serviceShared.encabezado(serviceShared.parametros( parametersRequest , nombreService ));
		System.out.println("OCURRIO UN PROBLEMA");
		json.put("status", HttpStatus.BAD_REQUEST);
		JSONObject parametros = new JSONObject();
		parametros.put("message","Los parametros identificación o tipo de documento no se estan enviando o el valor es nulo");
		JSONObject request = new JSONObject();
		
		List<Map<String, Object>> informacion = infoCampos();
		
		System.out.println(informacion.get(0).get("identificacion"));
		for(int i = 0 ; i < informacion.size() ; i++) {
			for(Map.Entry<String, String> mapaParametros : parametersRequest.entrySet()) {
				if(mapaParametros.getKey().equals(informacion.get(i).get("clave"))) {
					System.out.println("entro");
					informacion.get(i).put("value", mapaParametros.getValue());
					request.put(mapaParametros.getKey(), informacion.get(i)); 
				}
			}
		}
		
		parametros.put("paramRequest", request);
		
		json.put("error", parametros);
		
		return json.toString();
	}
	
	public List<Map<String, Object>> infoCampos() {
		List<Map<String, Object>> listaInformativa = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> infoIdentificacion = new HashMap<>();
		infoIdentificacion.put("value", null);
		infoIdentificacion.put("type", "String");
		infoIdentificacion.put("notNull", true);
		infoIdentificacion.put("lengthMax", 12);
		infoIdentificacion.put("clave", "identificacion");
		
		Map<String, Object> infoTipoDocumento = new HashMap<>();
		infoTipoDocumento.put("value", null);
		infoTipoDocumento.put("type", "String");
		infoTipoDocumento.put("notNull", true);
		infoTipoDocumento.put("lengthMax", 1);
		infoTipoDocumento.put("clave", "tipoDocumento");
		
		Map<String, Object> infoNumeroCDT = new HashMap<>();
		infoNumeroCDT.put("value", null);
		infoNumeroCDT.put("type", "String");
		infoNumeroCDT.put("notNull", true);
		infoNumeroCDT.put("lengthMax", 1);
		infoNumeroCDT.put("clave", "numeroProducto");
		
		listaInformativa.add(infoIdentificacion);
		listaInformativa.add(infoTipoDocumento);
		
		return listaInformativa;
	}

	@Override
	public String problemParamRequest() {
		JSONObject objeto = new JSONObject();
		objeto.put("mensaje", "Esta intentando utilizar un tipo de filtro de busqueda incorrecta");
		JSONObject combinaciones = new JSONObject();
		combinaciones.put("primera", "identificacion / tipoDocumento");
		combinaciones.put("segunda", "numeroProducto");
		combinaciones.put("tercera", "identificacion / tipoDocumento / numeroProducto");
		objeto.put("tipoFiltros", combinaciones);
		objeto.put("status", HttpStatus.BAD_REQUEST);
		return objeto.toString();
	}

}
