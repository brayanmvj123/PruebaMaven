package com.bdb.opalossqls.controller.service.control;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
import com.bdb.opalossqls.controller.service.interfaces.SendSQLServerService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerSQLS {

    @Autowired
    private SendSQLServerService sendSql;

    @Autowired
    private SharedService serviceShared;

    @PostMapping("sendCdtsSQLServer")
    public ResponseEntity<String> sendSQLServer(HttpServletRequest http, HttpServletResponse responseStatus) {
        String host = serviceShared.generarUrlSql(http.getRequestURL().toString());
        log.info(host);
        String url = host + "OPLBATCH/CDTSDesmaterializado/v1/sendCDTDeceval";
        //"http://localhost:8080/CDTSDesmaterializado/v1/sendCDTDeceval";
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<SalPdcvlEntity>> response = restTemplate.exchange(url, HttpMethod.POST, null,
                    new ParameterizedTypeReference<List<SalPdcvlEntity>>() {
                    });
            ResponseEntity<String> resultado = sendSql.almacenarCDTDecevalBta(response);
            actualizarClientesCDT(host);
            return resultado;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"status\":500}");
        }
    }

    public void actualizarClientesCDT(String host) {
        String url = host + "OPLBATCH/CDTSDesmaterializado/v1/updateClientCDTDeceval";
        //"http://localhost:8080/CDTSDesmaterializado/v1/updateClientCDTDeceval";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<SalPdcvlEntity>> response = restTemplate.exchange(url, HttpMethod.POST, null, new ParameterizedTypeReference<List<SalPdcvlEntity>>() {
        });
        String resultado = sendSql.actualizarClientesCDT(response);
    }

    @PostMapping("verificarTablaArchivoP")
    public Long verificarTablaArchivoP() {
        return sendSql.verificarTablaArchivoP();
    }

    @PostMapping("getEstadoCarga")
    public String getEstadoCarga() {
        return sendSql.getEstado();
    }

    @DeleteMapping("eliminarDataTablaArchivoP")
    public void eliminarDataTablaArchivoP() {
        sendSql.eliminarDataTablaArchivoP();
    }
}
