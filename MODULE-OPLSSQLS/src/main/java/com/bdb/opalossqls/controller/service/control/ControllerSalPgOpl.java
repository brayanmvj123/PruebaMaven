package com.bdb.opalossqls.controller.service.control;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opalossqls.controller.service.interfaces.IDVCSalPgOplService;
import com.bdb.opalossqls.persistence.model.JSONCancelCdtDig;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerSalPgOpl {

    @Autowired
    private IDVCSalPgOplService dvcSalPgOplService;

    @Autowired
    private SharedService serviceShared;

    @PostMapping("cancelacion/cdtsdig")
    public String almacenarDatos(HttpServletRequest httpServletRequest) {
        log.info("SE INICIA EL PROCESO DE ALMACENAMIENTO DE LAS CANCELACIONES DE DIGITALES.");
        try {
            String host = serviceShared.generarUrlSql(httpServletRequest.getRequestURL().toString());
            String url = host + "OPLBATCH/CDTSDesmaterializado/v1/envioCancelaciones";
            RestTemplate restTemplate = new RestTemplate();
            log.info("Se inicia el consumo de las cancelaciones traidas desde el modulo OPLBATCH.");
            ResponseEntity<List<JSONCancelCdtDig>> response = restTemplate.exchange(url, HttpMethod.POST,
                    null, new ParameterizedTypeReference<List<JSONCancelCdtDig>>() {
                    });
            dvcSalPgOplService.guardarLista(response.getBody());
            return "Cancelaciones exitosas";
        } catch (DataAccessException e) {
            log.error("El almacenamiento de las cancelaciones de los CDTs Digitales ha fallado.");
            return "El proceso de cancelaciones ha fallado.";
        }
    }
}
