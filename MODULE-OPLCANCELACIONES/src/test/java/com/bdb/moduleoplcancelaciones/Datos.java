package com.bdb.moduleoplcancelaciones;

import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.*;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datos {

    public static HttpServletRequest http = Mockito.mock(HttpServletRequest.class);

    public static ResponseEntity<RequestResult<String>> response = new ResponseEntity<>(new RequestResult<>(http, HttpStatus.OK, ""), HttpStatus.OK);

    public static ResponseEntity<RequestResult<String>> result() {
        return new ResponseEntity<>(new RequestResult<>(http, HttpStatus.OK, ""), HttpStatus.OK);
    }


    public static final RequestCancelCDT request() {
        RequestCancelCDT requestCancelCDT = new RequestCancelCDT();

        requestCancelCDT.setCanal("OPALO loco");
        List<InfoCliente> infoClienteList = Collections.singletonList(new InfoCliente("8300237821", "ASOCIACION NACIONAL DE EMPRESAS DE SERVICIOS PUBLI", 1));
        requestCancelCDT.setInfoCliente(infoClienteList);
        requestCancelCDT.setNumCdt("340410141");
        requestCancelCDT.setFecha("2021-11-21 10:31:00");

        InfoOficina infoOficina = new InfoOficina(2014, 2014);
        requestCancelCDT.setInfoOficina(infoOficina);

        InfoTrans infoTrans = new InfoTrans();
        List<Capital> capitalList = new ArrayList<>();
        capitalList.add(new Capital(3,5, BigDecimal.valueOf(20000), new InfoCta(654536291L, 2)));
        capitalList.add(new Capital(2,7, BigDecimal.valueOf(600000), new InfoCta(654536291L, 2)));
        infoTrans.setCapital(capitalList);

        Intereses intereses = new Intereses(4,4,BigDecimal.valueOf(3807), new InfoCta(0L,0));
        infoTrans.setIntereses(intereses);

        requestCancelCDT.setInfoTrans(infoTrans);

        return requestCancelCDT;
    }
}
