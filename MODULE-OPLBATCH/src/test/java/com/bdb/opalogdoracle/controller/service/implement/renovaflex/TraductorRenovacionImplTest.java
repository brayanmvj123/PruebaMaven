package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ConsumoGenericoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ControlEstadoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.NotificacionDcvBtaService;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TraductorRenovacionImplTest {

    private RepositoryParEndpointDown repositoryParEndpointDown;
    private NotificacionDcvBtaService notificacionDcvBtaService;
    private ConsumoGenericoService consumoGenericoService;
    private ControlEstadoService controlEstadoService;
    TraductorRenovacionImpl traductorRenovacion;

    @Before
    public void setUp() throws Exception {
        repositoryParEndpointDown = mock(RepositoryParEndpointDown.class);
        notificacionDcvBtaService = mock(NotificacionDcvBtaService.class);
        consumoGenericoService = mock(ConsumoGenericoService.class);
        controlEstadoService = mock(ControlEstadoService.class);
        traductorRenovacion = new TraductorRenovacionImpl(repositoryParEndpointDown,
                notificacionDcvBtaService,
                consumoGenericoService,
                controlEstadoService);
    }

    @Test(expected = ResponseStatusException.class)
    public void generarTraductorFallido(){
        ParEndpointDownEntity parEndpointDownEntity = new ParEndpointDownEntity(21L,
                "http://localhost:8082/DO/v1/process/cancelacion");
        when(repositoryParEndpointDown.findById(21L)).thenReturn(Optional.of(parEndpointDownEntity));
        when(consumoGenericoService.getResponseService(any(), any()))
                .thenReturn(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Response"));
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": "+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +",\"estado\": \"FALLIDO\",\"paso\": 1}");
        when(controlEstadoService.actualizarControlRenovacion(any(), any())).thenReturn(varentornoDownEntity);
        when(notificacionDcvBtaService.enviarNotificacion(any())).thenReturn(200);
        traductorRenovacion.generarTraductor();
    }

    @Test
    public void generarTraductor(){
        ParEndpointDownEntity parEndpointDownEntity = new ParEndpointDownEntity(21L,
                "http://localhost:8082/DO/v1/process/cancelacion");
        when(repositoryParEndpointDown.findById(21L)).thenReturn(Optional.of(parEndpointDownEntity));
        when(consumoGenericoService.getResponseService(any(), any())).thenReturn(ResponseEntity.ok("Response"));
        Assert.assertEquals(200, traductorRenovacion.generarTraductor());
    }
}