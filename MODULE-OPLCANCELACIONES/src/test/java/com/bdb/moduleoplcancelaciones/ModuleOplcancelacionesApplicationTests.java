package com.bdb.moduleoplcancelaciones;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.CDTService;
import com.bdb.moduleoplcancelaciones.controller.service.interfaces.TransaccionesService;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import static com.bdb.moduleoplcancelaciones.Datos.*;
//import org.junit.jupiter.api.BeforeEach;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleOplcancelacionesApplicationTests {

    @MockBean
    TransaccionesService transaccionesService;

    @Autowired
    CDTService cdtService;

//    @BeforeEach
    void setUp() {
//		cuentaRepository = mock(CuentaRepository.class);
//		bancoRepository = mock(BancoRepository.class);
//		service = new CuentaServiceImpl(cuentaRepository, bancoRepository);
//		Datos.CUENTA_001.setSaldo(new BigDecimal("1000"));
//		Datos.CUENTA_002.setSaldo(new BigDecimal("2000"));
//		Datos.BANCO.setTotalTransferencias(0);
    }

    @Test
    public void contextLoads() throws Exception {

        //Data
        HttpServletRequest http = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<RequestResult<?>> responseEntity = new ResponseEntity<>(new RequestResult<>(Datos.http, HttpStatus.OK, ""), HttpStatus.OK);

//        Mockito.when(transaccionesService.registerTranp(loco(),http)).thenReturn(responseEntity);
        Mockito.when(transaccionesService.registerTranp(request(),http)).thenReturn(responseEntity);


        ResponseEntity<RequestResult<?>> r = (ResponseEntity<RequestResult<?>>) cdtService.cancelarCdt(request(), http);
        System.out.println("r = " + r);
        Assert.assertEquals(HttpStatus.OK.value(), r.getBody().getStatus().getCode());
        System.out.println("Finalizó");
    }


    @Test
    public void contextLoads2() throws Exception {

        //Data
        HttpServletRequest http = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<RequestResult<?>> responseEntity = new ResponseEntity<>(new RequestResult<>(Datos.http, HttpStatus.NOT_FOUND, "No encontrado"), HttpStatus.NOT_FOUND);

//        Mockito.when(transaccionesService.registerTranp(loco(),http)).thenReturn(responseEntity);
        Mockito.when(transaccionesService.registerTranp(request(),http)).thenReturn(responseEntity);

        ResponseEntity<RequestResult<?>> r = (ResponseEntity<RequestResult<?>>) cdtService.cancelarCdt(request(), http);
        RequestResult<?> body = r.getBody();
        System.out.println("body = " + body);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), body.getStatus().getCode());
        System.out.println("Finalizó");
    }

}
