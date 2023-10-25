package com.bdb.moduleoplcancelaciones.controller.service.control;

import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerCDTsCancelTest {

    @Autowired
    private TestRestTemplate client;

    private ObjectMapper objectMapper;

    @LocalServerPort
    private int puerto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCancelacion() throws IOException {

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
        capitalList.add(new Capital(3,5, BigDecimal.valueOf(200000), new InfoCta(654536291L, 2)));
        capitalList.add(new Capital(2,7, BigDecimal.valueOf(600000), new InfoCta(654536291L, 2)));
        infoTrans.setCapital(capitalList);

        Intereses intereses = new Intereses(4,4,BigDecimal.valueOf(3807), new InfoCta(0L,0));
        infoTrans.setIntereses(intereses);

        requestCancelCDT.setInfoTrans(infoTrans);



        ResponseEntity<String> response = client.postForEntity(crearUri("/CDTSDesmaterializado/v1/cdtsvencido/cancelar"), requestCancelCDT, String.class);
        System.out.println(puerto);
        String json = response.getBody();
        System.out.println(json);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertNotNull(json);
//        assertTrue(json.contains("Transferencia realizada con éxito!"));
//        assertTrue(json.contains("{\"cuentaOrigenId\":1,\"cuentaDestinoId\":2,\"monto\":100,\"bancoId\":1}"));

        JsonNode jsonNode = objectMapper.readTree(json);
//        assertEquals("Transferencia realizada con éxito!", jsonNode.path("mensaje").asText());
//        assertEquals(LocalDate.now().toString(), jsonNode.path("date").asText());
//        assertEquals("100", jsonNode.path("transaccion").path("monto").asText());
//        assertEquals(1L, jsonNode.path("transaccion").path("cuentaOrigenId").asLong());

        Map<String, Object> response2 = new HashMap<>();
//        response2.put("date", LocalDate.now().toString());
//        response2.put("status", "OK");
//        response2.put("mensaje", "Transferencia realizada con éxito!");
//        response2.put("transaccion", dto);
//
//        assertEquals(objectMapper.writeValueAsString(response2), json);

    }

    private String crearUri(String uri) {
        return "http://localhost:" + puerto + uri;
    }
}
