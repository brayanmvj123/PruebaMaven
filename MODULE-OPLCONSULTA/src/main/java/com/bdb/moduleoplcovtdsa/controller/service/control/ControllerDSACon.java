package com.bdb.moduleoplcovtdsa.controller.service.control;

import com.bdb.moduleoplcovtdsa.controller.service.interfaces.SalPdcvlDownService;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.JSONValoresTotales;
import com.bdb.opaloshare.persistence.entity.SalPdcvlDownEntity;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@CrossOrigin(origins = "*", maxAge = 0)
@RequestMapping("TDSA/v1/consulta")
@CommonsLog
public class ControllerDSACon {

    @Autowired
    private SalPdcvlDownService service;

    /**
     * Servicio de consulta de valor total de los títulos enviados a constituir a Deceval S.A. por digital.
     *
     * @param request Request Http de la petición detallado.
     * @param fecha   Fecha de emisión por el cual se va a consultar.
     * @param canal   Canal que hace la correspondiente consulta.
     * @return Valores totales de los títulos.
     */
    @RequestMapping(method = {RequestMethod.POST}, value = "valores", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<RequestResult<JSONValoresTotales>> consultaTitulosPorDigital(
            HttpServletRequest request,
            @RequestParam(value = "fecha", defaultValue = "2019-10-18") String fecha,
            @RequestParam("canal") String canal) {
        List<SalPdcvlDownEntity> res = service.consultaCdtsDia(); // new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(fecha)

        if (!res.isEmpty()) {
            RequestResult<JSONValoresTotales> result = new RequestResult<>(request, HttpStatus.OK);
            BigDecimal total = BigDecimal.ZERO;

            for (SalPdcvlDownEntity item : res) total = total.add(item.getKapital());

            result.setResult(new JSONValoresTotales(total, res.size()));
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new RequestResult<>(
                    request,
                    HttpStatus.NO_CONTENT,
                    new JSONValoresTotales(BigDecimal.ZERO, res.size())));
        }
    }
}
