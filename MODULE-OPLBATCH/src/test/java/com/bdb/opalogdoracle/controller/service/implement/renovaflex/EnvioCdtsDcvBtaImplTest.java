package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ControlEstadoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.NotificacionDcvBtaService;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnvioCdtsDcvBtaImplTest {

    private RepositorySalRenautdig repositorySalRenautdig;

    private RepositoryParEndpointDown repositoryParEndpointDown;

    private ControlEstadoService controlEstadoService;

    private EnvioCdtsDcvBtaImpl envioCdtsDcvBtaImpl;

    private NotificacionDcvBtaService notificacionDcvBtaService;

    @Before
    public void setup(){
        repositoryParEndpointDown = mock(RepositoryParEndpointDown.class);
        repositorySalRenautdig = mock(RepositorySalRenautdig.class);
        controlEstadoService = mock(ControlEstadoService.class);
        envioCdtsDcvBtaImpl = new EnvioCdtsDcvBtaImpl(repositorySalRenautdig,
                repositoryParEndpointDown,
                controlEstadoService,
                notificacionDcvBtaService);
    }
    @Test()
    public void envioCdtsDcvBtaEmpty(){
        List<SalRenautdigEntity> salRenautdigEntityList = new ArrayList<>();
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L,
                "CC", "1018425059", "JUAN", new BigDecimal("10000"), new BigDecimal("100"),
                new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        ParEndpointDownEntity parEndpointDownEntity = new ParEndpointDownEntity(20L, "endpoints");

        EnvioCdtsDcvBtaImpl envioCdtsDcvBtaSpy = spy(new EnvioCdtsDcvBtaImpl(repositorySalRenautdig,
                repositoryParEndpointDown,
                controlEstadoService, notificacionDcvBtaService));

        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": "+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +",\"estado\": \"EJECUTANDOSE\",\"paso\": 4}");

        doReturn(Optional.of(parEndpointDownEntity)).when(repositoryParEndpointDown).findById(20L);
        doReturn((ResponseEntity.ok("Todo bien"))).when(envioCdtsDcvBtaSpy).getResponseService(any(), any());
        doNothing().when(controlEstadoService).actualizarHistorialResultado("paso3");
        doReturn(varentornoDownEntity).when(controlEstadoService).actualizarControlRenovacion("EJECUTANDOSE", 4);

        envioCdtsDcvBtaSpy.envioCdtsDcvBta();
//        assertEquals();

    }
    @Test()
    public void envioCdtsDcvBta(){
        List<SalRenautdigEntity> salRenautdigEntityList = new ArrayList<>();
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L,
                "CC", "1018425059", "JUAN", new BigDecimal("10000"), new BigDecimal("100"),
                new BigDecimal("10"), new BigDecimal("90"), "1", "P");
        salRenautdigEntityList.add(salRenautdigEntity);

        ParEndpointDownEntity parEndpointDownEntity = new ParEndpointDownEntity(20L, "endpoints");

        EnvioCdtsDcvBtaImpl envioCdtsDcvBtaSpy = spy(new EnvioCdtsDcvBtaImpl(repositorySalRenautdig,
                repositoryParEndpointDown,
                controlEstadoService, notificacionDcvBtaService));

        doReturn(salRenautdigEntityList).when(repositorySalRenautdig).findAll();
        doReturn(Optional.of(parEndpointDownEntity)).when(repositoryParEndpointDown).findById(20L);
        doReturn((ResponseEntity.ok("Todo bien"))).when(envioCdtsDcvBtaSpy).getResponseService(any(), any());

        envioCdtsDcvBtaSpy.envioCdtsDcvBta();
//        assertEquals();

    }

}