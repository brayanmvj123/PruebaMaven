package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ConsumoGenericoService;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificacionDcvBtaImplTest {

    private RepositoryParEndpointDown repositoryParEndpointDown;
    private ConsumoGenericoService consumoGenericoService;

    NotificacionDcvBtaImpl notificacionDcvBta;

    @Before
    public void init(){
        repositoryParEndpointDown = mock(RepositoryParEndpointDown.class);
        consumoGenericoService = mock(ConsumoGenericoService.class);
        notificacionDcvBta = new NotificacionDcvBtaImpl(repositoryParEndpointDown, consumoGenericoService);
    }

    @Test
    public void envioNotificación(){
        ParEndpointDownEntity parEndpointDownEntity = new ParEndpointDownEntity(22L,
                "http://localhost:8082/CDTSDesmaterializado/v1/renovacion/estadoRenaut?status=");
        when(repositoryParEndpointDown.findById(21L)).thenReturn(Optional.of(parEndpointDownEntity));
        when(consumoGenericoService.getResponseService(any(), any())).thenReturn(ResponseEntity.ok("Response"));
        Assert.assertEquals(200, notificacionDcvBta.enviarNotificacion("RENAUT_COMPLETED"));
    }

}