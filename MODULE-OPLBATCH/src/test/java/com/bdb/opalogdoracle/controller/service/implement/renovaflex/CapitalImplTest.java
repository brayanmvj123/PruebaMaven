package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.AbonoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.InformacionClienteService;
import com.bdb.opalogdoracle.mapper.MapperDebito;
import com.bdb.opalogdoracle.persistence.model.debito.request.DebitoRequest;
import com.bdb.opalogdoracle.persistence.model.debito.response.ResponseDebito;
import com.bdb.opalogdoracle.persistence.model.exception.ControlCdtsException;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.RepositoryDebitoAut;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCDTSCancelation;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCtrCdts;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest()
public class CapitalImplTest {

    private  RepositoryHisCtrCdts repositoryHisCtrCdts;
    private  MapperDebito mapperDebito;
    private  RepositoryParEndpointDown repositoryParEndpointDown;
    private  AbonoService abonoService;
    private  InformacionClienteService informacionClienteService;
    private  RepositoryDebitoAut repositoryDebitoAut;
    private  RepositoryHisCDTSCancelation repositoryHisCDTSCancelation;
    CapitalImpl capitalImpl;

    @Before
    public void setup(){
        repositoryHisCtrCdts = mock(RepositoryHisCtrCdts.class);
        repositoryDebitoAut = mock(RepositoryDebitoAut.class);
        repositoryHisCDTSCancelation = mock(RepositoryHisCDTSCancelation.class);
        capitalImpl = new CapitalImpl(repositoryHisCtrCdts,
                mapperDebito,
                repositoryParEndpointDown,
                abonoService,
                informacionClienteService,
                repositoryDebitoAut,
                repositoryHisCDTSCancelation);
    }

    @Test(expected = Exception.class)
    public void capital_findCtrCdts_no_find() throws ControlCdtsException {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        when(repositoryHisCtrCdts.findById(new HisCtrCdtsPk())).thenReturn(Optional.of(new HisCtrCdtsEntity()));

        assertEquals(new BigDecimal(0), capitalImpl.capital(salRenautdigEntity, "lo"));

    }

    @Test
    public void capital_duplicate_debit_or_payment() throws ControlCdtsException {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);
        Optional<HisCtrCdtsEntity> optionalCtr = Optional.of(hisCtrCdtsEntity);

        when(repositoryHisCtrCdts.findById(any(HisCtrCdtsPk.class))).thenReturn(optionalCtr);
        when(repositoryDebitoAut.findById(any(HisDebitoAutPk.class))).thenReturn(Optional.of(new HisDebitoAutEntity()));
        when(repositoryHisCDTSCancelation.findById(any(HisCDTSCancelationPk.class))).thenReturn(Optional.of(new HisCDTSCancelationEntity()));

        assertEquals(new BigDecimal(0), capitalImpl.capital(salRenautdigEntity, "lo"));

    }

    @Test
    public void capital_no_debit() throws ControlCdtsException {

        CapitalImpl capitalImplSpy = spy(new CapitalImpl(repositoryHisCtrCdts,
                mapperDebito,
                repositoryParEndpointDown,
                abonoService,
                informacionClienteService,
                repositoryDebitoAut,
                repositoryHisCDTSCancelation));

        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);
        Optional<HisCtrCdtsEntity> optionalCtr = Optional.of(hisCtrCdtsEntity);

        DebitoRequest debitoRequest = new DebitoRequest("C", "1090", 800007L, 47,
                321246L, "SDA", new BigDecimal("100000"));

        HisDebitoAutEntity hisDebitoAutEntity = new HisDebitoAutEntity(salRenautdigEntity.getCodIsin(), salRenautdigEntity.getNumCdt(),
                salRenautdigEntity.getNumTit(), "PAGO EXITOSO", LocalDateTime.now());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DebitoRequest> requestEntity = new HttpEntity<>(debitoRequest, httpHeaders);

        when(repositoryHisCtrCdts.findById(any(HisCtrCdtsPk.class))).thenReturn(optionalCtr);
        when(repositoryDebitoAut.findById(any(HisDebitoAutPk.class))).thenReturn(Optional.of(hisDebitoAutEntity));

        ResponseDebito responseDebito = new ResponseDebito(HttpStatus.OK, LocalDateTime.now(), "Debito realizado");
        doReturn(ResponseEntity.ok(responseDebito)).when(capitalImplSpy).getResponseDebitoResponseEntity(any(), any());

        assertEquals(new BigDecimal(0), capitalImplSpy.capital(salRenautdigEntity, "debito"));

    }

    @Test
    public void capital_keep() throws ControlCdtsException {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("0"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);
        Optional<HisCtrCdtsEntity> optionalCtr = Optional.of(hisCtrCdtsEntity);

        when(repositoryHisCtrCdts.findById(any(HisCtrCdtsPk.class))).thenReturn(optionalCtr);
        when(repositoryDebitoAut.findById(any(HisDebitoAutPk.class))).thenReturn(Optional.of(new HisDebitoAutEntity()));
        when(repositoryHisCDTSCancelation.findById(any(HisCDTSCancelationPk.class))).thenReturn(Optional.of(new HisCDTSCancelationEntity()));

        assertEquals(new BigDecimal(0), capitalImpl.capital(salRenautdigEntity, "localhost"));

    }

    @Test
    public void capital_increases_ok() throws ControlCdtsException {
        CapitalImpl capitalImplSpy = spy(new CapitalImpl(repositoryHisCtrCdts,
                mapperDebito,
                repositoryParEndpointDown,
                abonoService,
                informacionClienteService,
                repositoryDebitoAut,
                repositoryHisCDTSCancelation));

        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);
        Optional<HisCtrCdtsEntity> optionalCtr = Optional.of(hisCtrCdtsEntity);

        DebitoRequest debitoRequest = new DebitoRequest("C", "1090", 800007L, 47,
                321246L, "SDA", new BigDecimal("100000"));

        when(repositoryHisCtrCdts.findById(any(HisCtrCdtsPk.class))).thenReturn(optionalCtr);
        when(repositoryDebitoAut.findById(new HisDebitoAutPk("COB", 80000777000191L, "1018425059")))
                .thenReturn(Optional.of(new HisDebitoAutEntity()));
        when(repositoryHisCDTSCancelation.findById(new HisCDTSCancelationPk("COB", 80000777000191L, "1018425059")))
                .thenReturn(Optional.of(new HisCDTSCancelationEntity()));
        doReturn(debitoRequest).when(capitalImplSpy)
                .debitoRequest(new BigDecimal("100000"), hisCtrCdtsEntity, hisCtaCliEntity);
        doReturn(true).when(capitalImplSpy).debito(debitoRequest,salRenautdigEntity.getCodIsin(), 0);

        assertEquals(new BigDecimal(100000), capitalImplSpy.capital(salRenautdigEntity, "localhost"));

    }

    @Test
    public void capital_increases_fail() throws ControlCdtsException {
        CapitalImpl capitalImplSpy = spy(new CapitalImpl(repositoryHisCtrCdts,
                mapperDebito,
                repositoryParEndpointDown,
                abonoService,
                informacionClienteService,
                repositoryDebitoAut,
                repositoryHisCDTSCancelation));

        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);
        Optional<HisCtrCdtsEntity> optionalCtr = Optional.of(hisCtrCdtsEntity);

        DebitoRequest debitoRequest = new DebitoRequest("C", "1090", 800007L, 47,
                321246L, "SDA", new BigDecimal("100000"));

        when(repositoryHisCtrCdts.findById(any(HisCtrCdtsPk.class))).thenReturn(optionalCtr);
        when(repositoryDebitoAut.findById(new HisDebitoAutPk("COB", 80000777000191L, "1018425059")))
                .thenReturn(Optional.of(new HisDebitoAutEntity()));
        when(repositoryHisCDTSCancelation.findById(new HisCDTSCancelationPk("COB", 80000777000191L, "1018425059")))
                .thenReturn(Optional.of(new HisCDTSCancelationEntity()));
        doReturn(debitoRequest).when(capitalImplSpy)
                .debitoRequest(new BigDecimal("100000"), hisCtrCdtsEntity, hisCtaCliEntity);
        doReturn(false).when(capitalImplSpy).debito(debitoRequest,salRenautdigEntity.getCodIsin(), 0);

        assertEquals(new BigDecimal(0), capitalImplSpy.capital(salRenautdigEntity, "localhost"));

    }

    @Test
    public void capital_payment() throws ControlCdtsException {
        CapitalImpl capitalImplSpy = spy(new CapitalImpl(repositoryHisCtrCdts,
                mapperDebito,
                repositoryParEndpointDown,
                abonoService,
                informacionClienteService,
                repositoryDebitoAut,
                repositoryHisCDTSCancelation));

        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("-100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);
        Optional<HisCtrCdtsEntity> optionalCtr = Optional.of(hisCtrCdtsEntity);

        DebitoRequest debitoRequest = new DebitoRequest("C", "1090", 800007L, 47,
                321246L, "SDA", new BigDecimal("100000"));

        when(repositoryHisCtrCdts.findById(any(HisCtrCdtsPk.class))).thenReturn(optionalCtr);
        when(repositoryDebitoAut.findById(new HisDebitoAutPk("COB", 80000777000191L, "1018425059")))
                .thenReturn(Optional.of(new HisDebitoAutEntity()));
        when(repositoryHisCDTSCancelation.findById(new HisCDTSCancelationPk("COB", 80000777000191L, "1018425059")))
                .thenReturn(Optional.of(new HisCDTSCancelationEntity()));
        doReturn(new BigDecimal("-100000")).when(capitalImplSpy)
                .abonoCapital("localhost", salRenautdigEntity, condicionCdts);

        assertEquals(new BigDecimal(-100000), capitalImplSpy.capital(salRenautdigEntity, "localhost"));

    }

}