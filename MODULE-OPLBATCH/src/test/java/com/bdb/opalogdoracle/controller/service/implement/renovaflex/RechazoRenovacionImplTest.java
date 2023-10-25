package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.InformacionClienteService;
import com.bdb.opalogdoracle.mapper.MapperRechazosRenovacion;
import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opalogdoracle.persistence.model.servicecancel.PaymentTransaction;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.entity.SalPgdigitalDownEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RechazoRenovacionImplTest {

    private RepositorySalPg repositorySalPg;
    private RepositorySalRenautdig repositorySalRenautdig;
    private MapperRechazosRenovacion mapperRechazosRenovacion;
    private RepositorySalPgdigitalDown repositorySalPgdigitalDown;
    private InformacionClienteService informacionClienteService;

    RechazoRenovacionImpl rechazoRenovacionImpl;

    @Before
    public void setup() {
        repositorySalPg = mock(RepositorySalPg.class);
        repositorySalRenautdig = mock(RepositorySalRenautdig.class);
        mapperRechazosRenovacion = mock(MapperRechazosRenovacion.class);
        repositorySalPgdigitalDown = mock(RepositorySalPgdigitalDown.class);
        informacionClienteService = mock(InformacionClienteService.class);
        rechazoRenovacionImpl = new RechazoRenovacionImpl(repositorySalPg,
                repositorySalRenautdig,
                mapperRechazosRenovacion,
                repositorySalPgdigitalDown,
                informacionClienteService);
    }

    @Test(expected = ResponseStatusException.class)
    public void cdtNotExistsSalPg() {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        when(repositorySalPg.findByNumCdtAndCodIsinAndNumTit(any(), any(), any())).thenReturn(null);
        rechazoRenovacionImpl.rechazoRenovacion(salRenautdigEntity);
    }

    @Test
    public void cdt() {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        SalPgDownEntity salPgDownEntity = new SalPgDownEntity(1000L, 103L, "1", 80000777000191L, "COB01CD0JWN0",
                "02650418", 1, "91540424", "ALBARRACIN MU¿OZ JUAN CARLOS",
                LocalDate.now(), LocalDate.now(), "2022-12-19", "1", 180L, "1",
                "0", "1", new BigDecimal("7.2"), new BigDecimal("7.07"),
                new BigDecimal("0"), new BigDecimal("3000000"), new BigDecimal("104598"),
                new BigDecimal("0"), new BigDecimal("104598"), new BigDecimal("3000000"),
                new BigDecimal("3104598"), 4L, new BigDecimal("0"), new BigDecimal("0"),
                3001, 4, LocalDateTime.now());

        List<PaymentTransaction> transaccionPagoList = new ArrayList<>();
        transaccionPagoList.add(new PaymentTransaction(3, new BigDecimal("30000")));
        InfoCtaClienteModel infoCtaClienteModel = new InfoCtaClienteModel("CC", "91540424",
                "ALBARRACIN MU¿OZ JUAN CARLOS", 80000777000191L, "COB01CD0JWN0",
                103, 2650418L, 1, 103698741L, 2, 103, transaccionPagoList);

        SalPgdigitalDownEntity salPgdigitalDownEntity = new SalPgdigitalDownEntity(1000L, new BigDecimal("80000777000191"),
                "COB01CD0JWN0", "CC", "91540424", "ALBARRACIN MU¿OZ JUAN CARLOS",
                new BigDecimal("104598"), new BigDecimal("14598"), new BigDecimal("98000"),
                new BigDecimal("3104598"), new BigDecimal("3000000"), "Cuenta Ahorros", "103698741",
                "103", 2);

        when(repositorySalPg.findByNumCdtAndCodIsinAndNumTit(any(), any(), any())).thenReturn(salPgDownEntity);
        when(informacionClienteService.obtenerInformacion(any())).thenReturn(infoCtaClienteModel);
        when(mapperRechazosRenovacion.toSalPgdigitalDownEntity(any(), any())).thenReturn(salPgdigitalDownEntity);
        when(repositorySalPgdigitalDown.save(any())).thenReturn(salPgdigitalDownEntity);
        doNothing().when(repositorySalRenautdig).delete(salRenautdigEntity);
        when(repositorySalRenautdig.existsByCodIsinAndNumCdtAndNumTit(any(), any(), any())).thenReturn(false);
        when(repositorySalPgdigitalDown.existsByCodIsinAndNumCdtAndNumTit(salPgDownEntity.getCodIsin(),
                new BigDecimal(salPgdigitalDownEntity.getNumCdt().toString()), salPgDownEntity.getNumTit())).thenReturn(true);
        rechazoRenovacionImpl.rechazoRenovacion(salRenautdigEntity);

    }


}