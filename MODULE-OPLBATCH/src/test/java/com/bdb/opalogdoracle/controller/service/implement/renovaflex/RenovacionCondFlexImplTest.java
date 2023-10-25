package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.*;
import com.bdb.opalogdoracle.mapper.MapperNuevasCondicionesCdt;
import com.bdb.opalogdoracle.mapper.MapperReApertura;
import com.bdb.opalogdoracle.persistence.Response.ResponseService;
import com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura.*;
import com.bdb.opalogdoracle.persistence.model.NuevasCondicionesCdtModel;
import com.bdb.opalogdoracle.persistence.model.ReAperturaModel;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest()
public class RenovacionCondFlexImplTest {

    private RepositorySalRenautdig repositorySalRenautdig;

    private RepositoryCDTSLarge repositoryCDTSLarge;

    private RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds;

    private RepositoryDatosCliente repositoryDatosCliente;

    private RepositoryTipPaisPar repositoryTipPaisPar;

    private RepositoryTipCiud repoCiud;

    private RepositoryTipDepar repoDepar;

    private RepositoryTipDane repoDane;

    private RepositoryTipCIUU repoCiiu;

    private RepositoryTipSegmento repoSegmento;

    private RepositoryTipDepositante repoDepo;

    private RepositoryHisCtrCdts repositoryHisCtrCdts;

    private RepositoryHisRenovaCdt repositoryHisRenovaCdt;

    private RepositoryTipId repositoryTipId;

    private RepositoryTipPeriodicidad repositoryTipPeriodicidad;

    private RepositorySalPg repositorySalPg;

    private RepositoryTipVarentorno repositoryTipVarentorno;

    private RepositoryTasaSeg repositoryTasaSeg;

    private SharedService sharedService;

    private ResponseService responseService;

    private CapitalService capitalService;

    private MapperNuevasCondicionesCdt mapperNuevasCondicionesCdt;

    private InformacionClienteService informacionClienteService;

    private AbonoService abonoService;

    private RepositoryParEndpointDown repositoryParEndpointDown;

    RenovacionCondFlexImpl renovacionCondFlex;

    private MapperReApertura mapperReApertura;

    private ControlEstadoService controlEstadoService;

    private RechazoRenovacionService rechazoRenovacionService;

    private ValidarMontoMinimoService validarMontoMinimoService;

    private NotificacionDcvBtaService notificacionDcvBtaService;

    @Before
    public void init() {
        repositoryHisCtrCdts = mock(RepositoryHisCtrCdts.class);
        capitalService = mock(CapitalService.class);
        abonoService = mock(AbonoService.class);
        mapperNuevasCondicionesCdt = mock(MapperNuevasCondicionesCdt.class);
        repositoryParEndpointDown = mock(RepositoryParEndpointDown.class);
        informacionClienteService = mock(InformacionClienteService.class);
        repositorySalRenautdig = mock(RepositorySalRenautdig.class);
        repositorySalPg = mock(RepositorySalPg.class);
        repositoryTipVarentorno = Mockito.mock(RepositoryTipVarentorno.class);
        validarMontoMinimoService = Mockito.mock(ValidarMontoMinimoService.class);
        renovacionCondFlex = new RenovacionCondFlexImpl(repositorySalRenautdig,
                repositoryCDTSLarge,
                repositoryTmpTasasCdtMds,
                repositoryDatosCliente,
                repositoryTipPaisPar,
                repoCiud,
                repoDepar,
                repoDane,
                repoCiiu,
                repoSegmento,
                repoDepo,
                repositoryHisCtrCdts,
                repositoryHisRenovaCdt,
                repositoryTipId,
                repositoryTipPeriodicidad,
                repositorySalPg,
                repositoryTipVarentorno,
                repositoryTasaSeg,
                sharedService,
                responseService,
                capitalService,
                mapperNuevasCondicionesCdt,
                informacionClienteService,
                abonoService,
                repositoryParEndpointDown,
                mapperReApertura,
                controlEstadoService,
                rechazoRenovacionService,
                validarMontoMinimoService,
                notificacionDcvBtaService);
    }

    @Test
    public void nuevas_condiciones_resultado_ok() throws Exception {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(14L, "pagosAut"));

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
        HisCtrCdtsPk hisCtrCdtsPk = new HisCtrCdtsPk(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), 1L);

        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(39, "VALOR_MIN_APERTURA",
                "100000");

        when(repositoryHisCtrCdts.findById(any(HisCtrCdtsPk.class))).thenReturn(optionalCtr);
        BigDecimal capitalFlexible = new BigDecimal("0");
        BigDecimal capitalCdt = salRenautdigEntity.getCapPg();

        when(repositoryTipVarentorno.findByDescVariable(any())).thenReturn(varentornoDownEntity);

        when(validarMontoMinimoService.montoMinimo(any(), any(), any())).thenReturn(true);

        when(capitalService.capital(any(), any())).thenReturn(new BigDecimal("0"));

        NuevasCondicionesCdtModel nuevasCondicionesCdtModel = new NuevasCondicionesCdtModel(capitalCdt.add(capitalFlexible), salRenautdigEntity.getIntNeto(),
                condicionCdts.getTipPlazo(), condicionCdts.getPlazo(), condicionCdts.getBase(), "3002");
        when(mapperNuevasCondicionesCdt.nuevasCondiciones(capitalCdt.add(capitalFlexible), salRenautdigEntity.getIntNeto(),
                condicionCdts)).thenReturn(nuevasCondicionesCdtModel);

        assertEquals(nuevasCondicionesCdtModel, renovacionCondFlex.nuevasCondicionesCdt(salRenautdigEntity, parEndpointDownEntityList));
    }

    @Test(expected = RuntimeException.class)
    public void nuevas_condiciones_resultado_fail() throws Exception {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(14L, "pagosAut"));

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(),
                        new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059",
                LocalDateTime.now(), "123", 1, LocalDateTime.now(), "RENOVACION",
                new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);

        Optional<HisCtrCdtsEntity> optionalCtr = Optional.of(hisCtrCdtsEntity);
        HisCtrCdtsPk hisCtrCdtsPk = new HisCtrCdtsPk(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), 1L);

        when(repositoryHisCtrCdts.findById(hisCtrCdtsPk)).thenReturn(optionalCtr);

        NuevasCondicionesCdtModel nuevasCondicionesCdtModel = new NuevasCondicionesCdtModel(salRenautdigEntity.getCapPg(),
                salRenautdigEntity.getIntNeto(), condicionCdts.getTipPlazo(), condicionCdts.getPlazo(),
                condicionCdts.getBase(), "3002");

        renovacionCondFlex.nuevasCondicionesCdt(salRenautdigEntity, parEndpointDownEntityList);
    }

    @Test
    public void simuladorFechaVencimiento() {
        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(15L, "simuladorFechaVencimiento"));

        RenovacionCondFlexImpl spy = spy(new RenovacionCondFlexImpl(repositorySalRenautdig,
                repositoryCDTSLarge,
                repositoryTmpTasasCdtMds,
                repositoryDatosCliente,
                repositoryTipPaisPar,
                repoCiud,
                repoDepar,
                repoDane,
                repoCiiu,
                repoSegmento,
                repoDepo,
                repositoryHisCtrCdts,
                repositoryHisRenovaCdt,
                repositoryTipId,
                repositoryTipPeriodicidad,
                repositorySalPg,
                repositoryTipVarentorno,
                repositoryTasaSeg,
                sharedService,
                responseService,
                capitalService,
                mapperNuevasCondicionesCdt,
                informacionClienteService,
                abonoService, repositoryParEndpointDown, mapperReApertura, controlEstadoService, rechazoRenovacionService, validarMontoMinimoService, notificacionDcvBtaService));
        doReturn(ResponseEntity.ok("{\"resultado\": {\n" +
                "        \"periodicidad\": [\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Mensual\",\n" +
                "                \"tipPeriodicidad\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Cada 5 meses\",\n" +
                "                \"tipPeriodicidad\": 5\n" +
                "            },\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Bimestral\",\n" +
                "                \"tipPeriodicidad\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Al plazo\",\n" +
                "                \"tipPeriodicidad\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"fechaVencimiento\": \"2024-03-18\"\n" +
                "    }\n" +
                "}")).when(spy).getResponseService(any(), any());
        assertEquals("2024-03-18", spy.simuladorFechaVenCdt(1, 10, 2, parEndpointDownEntityList));
    }

    @Test
    public void simuladorIntereses() {
        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(17L, "simuladorCuota"));

        RenovacionCondFlexImpl spy = spy(new RenovacionCondFlexImpl(repositorySalRenautdig,
                repositoryCDTSLarge,
                repositoryTmpTasasCdtMds,
                repositoryDatosCliente,
                repositoryTipPaisPar,
                repoCiud,
                repoDepar,
                repoDane,
                repoCiiu,
                repoSegmento,
                repoDepo,
                repositoryHisCtrCdts,
                repositoryHisRenovaCdt,
                repositoryTipId,
                repositoryTipPeriodicidad,
                repositorySalPg,
                repositoryTipVarentorno,
                repositoryTasaSeg,
                sharedService,
                responseService,
                capitalService,
                mapperNuevasCondicionesCdt,
                informacionClienteService,
                abonoService,
                repositoryParEndpointDown,
                mapperReApertura, controlEstadoService, rechazoRenovacionService, validarMontoMinimoService, notificacionDcvBtaService));

        doReturn("300")
                .when(spy)
                .calculoServiceDias(any(), any(), any());


        doReturn(ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"message\": \"OK\"\n" +
                "    },\n" +
                "    \"requestUrl\": \"https://opaloprue.banbta.net:8002/OPLSIMULADOR/TasaEfectivaNominal/v1/simulacionCuota\",\n" +
                "    \"parameters\": {\n" +
                "        \"tasaFija\": \"11.03\",\n" +
                "        \"periodicidad\": \"1\",\n" +
                "        \"base\": \"2\",\n" +
                "        \"diasPlazo\": \"365\",\n" +
                "        \"capital\": \"2000000\",\n" +
                "        \"retencion\": \"4\"\n" +
                "    },\n" +
                "    \"result\": {\n" +
                "        \"interesBruto\": \"223830\",\n" +
                "        \"retencionIntereses\": \"8953\",\n" +
                "        \"interesNeto\": \"214877\",\n" +
                "        \"interesBrutoPeriodo\": \"223830\",\n" +
                "        \"retencionInteresesPeriodo\": \"8953\",\n" +
                "        \"interesNetoPeriodo\": \"214877\",\n" +
                "        \"tasaNominal\": 11.0382\n" +
                "    }\n" +
                "}")).when(spy).getResponseService(any(), any());

        doReturn("1").when(spy).homologacionPeriodicidad(1);

        assertEquals(new BigDecimal("11.0382"), spy.simuladorIntereses(2, "2000000", "1", "20",
                "11.03", "2024-03-18", parEndpointDownEntityList).getAsJsonObject("result").get("tasaNominal").getAsBigDecimal());
    }

    @Test
    public void simuladorCalculoDias() {
        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(16L, "simuladorCalculoDias"));

        RenovacionCondFlexImpl spy = spy(new RenovacionCondFlexImpl(repositorySalRenautdig,
                repositoryCDTSLarge,
                repositoryTmpTasasCdtMds,
                repositoryDatosCliente,
                repositoryTipPaisPar,
                repoCiud,
                repoDepar,
                repoDane,
                repoCiiu,
                repoSegmento,
                repoDepo,
                repositoryHisCtrCdts,
                repositoryHisRenovaCdt,
                repositoryTipId,
                repositoryTipPeriodicidad,
                repositorySalPg,
                repositoryTipVarentorno,
                repositoryTasaSeg,
                sharedService,
                responseService,
                capitalService,
                mapperNuevasCondicionesCdt,
                informacionClienteService,
                abonoService, repositoryParEndpointDown, mapperReApertura, controlEstadoService, rechazoRenovacionService, validarMontoMinimoService, notificacionDcvBtaService));

        doReturn(ResponseEntity.ok("{\n" +
                "    \"resultado\": {\n" +
                "        \"periodicidad\": [\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Mensual\",\n" +
                "                \"tipPeriodicidad\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Cada 5 meses\",\n" +
                "                \"tipPeriodicidad\": 5\n" +
                "            },\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Bimestral\",\n" +
                "                \"tipPeriodicidad\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"descPeriodicidad\": \"Al plazo\",\n" +
                "                \"tipPeriodicidad\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"plazoenDias\": \"300\"\n" +
                "    }\n" +
                "}")).when(spy).getResponseService(any(), any());

        assertEquals("300", spy.calculoServiceDias(1, "2024-03-18", parEndpointDownEntityList));
    }

    @Test
    public void createCdtExits() {
        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(18L, "aperturaCdt"));

        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(),
                        new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059",
                LocalDateTime.now(), "123", 1, LocalDateTime.now(), "RENOVACION",
                new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);

        NuevasCondicionesCdtModel nuevasCondicionesCdtModel = new NuevasCondicionesCdtModel(salRenautdigEntity.getCapPg(),
                salRenautdigEntity.getIntNeto(), condicionCdts.getTipPlazo(), condicionCdts.getPlazo(),
                condicionCdts.getBase(), "3002");

        Set<HisTranpgEntity> transacciones = new HashSet<>();

        HisCDTSLargeEntity hisCDTSLargeEntity = new HisCDTSLargeEntity(salRenautdigEntity.getNumCdt().toString(),
                LocalDateTime.now(), "usuario", "OPALO", "123456", "12346", "3002",
                "3", "103", "103", "1", 1,
                103, 3, 1, LocalDate.now(), LocalDate.now(), 1,
                "1", 1, 0, "+", new BigDecimal("0"),
                new BigDecimal("11.14"), new BigDecimal("11.16"), new BigDecimal("0"), "+",
                new BigDecimal("0"), new BigDecimal("10000"), "1", 3, transacciones);

        ReAperturaModel reAperturaModel = new ReAperturaModel(nuevasCondicionesCdtModel, 1090L,
                1L, hisCDTSLargeEntity, "2023/05/29", new BigDecimal("14"),
                new BigDecimal("14"), new BigDecimal("10000"), "123CUT", parEndpointDownEntityList);

        RenovacionCondFlexImpl spy = spy(new RenovacionCondFlexImpl(repositorySalRenautdig,
                repositoryCDTSLarge,
                repositoryTmpTasasCdtMds,
                repositoryDatosCliente,
                repositoryTipPaisPar,
                repoCiud,
                repoDepar,
                repoDane,
                repoCiiu,
                repoSegmento,
                repoDepo,
                repositoryHisCtrCdts,
                repositoryHisRenovaCdt,
                repositoryTipId,
                repositoryTipPeriodicidad,
                repositorySalPg,
                repositoryTipVarentorno,
                repositoryTasaSeg,
                sharedService,
                responseService,
                capitalService,
                mapperNuevasCondicionesCdt,
                informacionClienteService,
                abonoService, repositoryParEndpointDown, mapperReApertura, controlEstadoService, rechazoRenovacionService, validarMontoMinimoService, notificacionDcvBtaService));

        List<JSONClientDatos> cliente = new ArrayList<>();
        List<JSONPagCDTDes> transaccionesPagoCdt = new ArrayList<>();

//        doNothing().when(spy).validacionMontoMinimo(hisCtrCdtsEntity, 0);

        doReturn(new JSONAperCDT(new JSONCondCDT(), cliente, transaccionesPagoCdt))
                .when(spy)
                .requestApertura(any());

        doReturn(false).when(spy).validacionApertura(salRenautdigEntity.getNumCdt());

        doReturn(ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("{\n" +
                "    \"status\": {\n" +
                "        \"code\": 502,\n" +
                "        \"message\": \"BAD_GATEWAY\"\n" +
                "    },\n" +
                "    \"requestUrl\": \"https://opaloprue.banbta.net:8002/OPAPCDT/CDTSDesmaterializado/v1/aperturaCDTsDes\",\n" +
                "    \"parameters\": {},\n" +
                "    \"result\": {\n" +
                "        \"message\": \"Error !!! El número de CDT Digital utilizado para esta apertura YA EXISTE\"\n" +
                "    }\n" +
                "}")).when(spy).getResponseService(any(), any());

        assertFalse(spy.apertura(reAperturaModel));
    }

    @Test(expected = ResponseStatusException.class)
    public void createCdtRenovated() {
        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(18L, "aperturaCdt"));

        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(),
                        new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059",
                LocalDateTime.now(), "123", 1, LocalDateTime.now(), "RENOVACION",
                new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);

        NuevasCondicionesCdtModel nuevasCondicionesCdtModel = new NuevasCondicionesCdtModel(salRenautdigEntity.getCapPg(),
                salRenautdigEntity.getIntNeto(), condicionCdts.getTipPlazo(), condicionCdts.getPlazo(),
                condicionCdts.getBase(), "3002");

        Set<HisTranpgEntity> transacciones = new HashSet<>();

        HisCDTSLargeEntity hisCDTSLargeEntity = new HisCDTSLargeEntity(salRenautdigEntity.getNumCdt().toString(),
                LocalDateTime.now(), "usuario", "OPALO", "123456", "12346", "3002",
                "3", "103", "103", "1", 1,
                103, 3, 1, LocalDate.now(), LocalDate.now(), 1,
                "1", 1, 0, "+", new BigDecimal("0"),
                new BigDecimal("11.14"), new BigDecimal("11.16"), new BigDecimal("0"), "+",
                new BigDecimal("0"), new BigDecimal("10000"), "1", 3, transacciones);

        ReAperturaModel reAperturaModel = new ReAperturaModel(nuevasCondicionesCdtModel, 1090L,
                1L, hisCDTSLargeEntity, "2023/05/29", new BigDecimal("14"),
                new BigDecimal("14"), new BigDecimal("10000"), "123CUT", parEndpointDownEntityList);

        RenovacionCondFlexImpl spy = spy(new RenovacionCondFlexImpl(repositorySalRenautdig,
                repositoryCDTSLarge,
                repositoryTmpTasasCdtMds,
                repositoryDatosCliente,
                repositoryTipPaisPar,
                repoCiud,
                repoDepar,
                repoDane,
                repoCiiu,
                repoSegmento,
                repoDepo,
                repositoryHisCtrCdts,
                repositoryHisRenovaCdt,
                repositoryTipId,
                repositoryTipPeriodicidad,
                repositorySalPg,
                repositoryTipVarentorno,
                repositoryTasaSeg,
                sharedService,
                responseService,
                capitalService,
                mapperNuevasCondicionesCdt,
                informacionClienteService,
                abonoService, repositoryParEndpointDown, mapperReApertura, controlEstadoService, rechazoRenovacionService, validarMontoMinimoService, notificacionDcvBtaService));

        List<JSONClientDatos> cliente = new ArrayList<>();
        List<JSONPagCDTDes> transaccionesPagoCdt = new ArrayList<>();

//        doNothing().when(spy).validacionMontoMinimo(hisCtrCdtsEntity, 0);

        doReturn(new JSONAperCDT(new JSONCondCDT(), cliente, transaccionesPagoCdt))
                .when(spy)
                .requestApertura(any());

        doReturn(true).when(spy).validacionApertura(salRenautdigEntity.getNumCdt());

        spy.apertura(reAperturaModel);
    }

    @Test
    public void createCdtOk() {
        List<ParEndpointDownEntity> parEndpointDownEntityList = new ArrayList<>();
        parEndpointDownEntityList.add(new ParEndpointDownEntity(18L, "aperturaCdt"));

        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L, "CC", "1018425059", "JUAN",
                new BigDecimal("10000"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("90"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(),
                        new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059",
                LocalDateTime.now(), "123", 1, LocalDateTime.now(), "RENOVACION",
                new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);

        NuevasCondicionesCdtModel nuevasCondicionesCdtModel = new NuevasCondicionesCdtModel(salRenautdigEntity.getCapPg(),
                salRenautdigEntity.getIntNeto(), condicionCdts.getTipPlazo(), condicionCdts.getPlazo(),
                condicionCdts.getBase(), "3002");

        Set<HisTranpgEntity> transacciones = new HashSet<>();

        HisCDTSLargeEntity hisCDTSLargeEntity = new HisCDTSLargeEntity(salRenautdigEntity.getNumCdt().toString(),
                LocalDateTime.now(), "usuario", "OPALO", "123456", "12346", "3002",
                "3", "103", "103", "1", 1,
                103, 3, 1, LocalDate.now(), LocalDate.now(), 1,
                "1", 1, 0, "+", new BigDecimal("0"),
                new BigDecimal("11.14"), new BigDecimal("11.16"), new BigDecimal("0"), "+",
                new BigDecimal("0"), new BigDecimal("10000"), "1", 3, transacciones);

        ReAperturaModel reAperturaModel = new ReAperturaModel(nuevasCondicionesCdtModel, 1090L,
                1L, hisCDTSLargeEntity, "2023/05/29", new BigDecimal("14"),
                new BigDecimal("14"), new BigDecimal("10000"), "123CUT", parEndpointDownEntityList);

        RenovacionCondFlexImpl spy = spy(new RenovacionCondFlexImpl(repositorySalRenautdig,
                repositoryCDTSLarge,
                repositoryTmpTasasCdtMds,
                repositoryDatosCliente,
                repositoryTipPaisPar,
                repoCiud,
                repoDepar,
                repoDane,
                repoCiiu,
                repoSegmento,
                repoDepo,
                repositoryHisCtrCdts,
                repositoryHisRenovaCdt,
                repositoryTipId,
                repositoryTipPeriodicidad,
                repositorySalPg,
                repositoryTipVarentorno,
                repositoryTasaSeg,
                sharedService,
                responseService,
                capitalService,
                mapperNuevasCondicionesCdt,
                informacionClienteService,
                abonoService, repositoryParEndpointDown, mapperReApertura, controlEstadoService, rechazoRenovacionService, validarMontoMinimoService, notificacionDcvBtaService));

        List<JSONClientDatos> cliente = new ArrayList<>();
        List<JSONPagCDTDes> transaccionesPagoCdt = new ArrayList<>();

        JSONCondCDT condicionesCdts = new JSONCondCDT(LocalDateTime.now().toString(), "usuario", "OPALO",
                "123456", "123456", "80000700010110", "3002", "3",
                "103", "103", "1", "1",
                new JSONPlazoCDT("1", 103), LocalDate.now().toString(), LocalDate.now().toString(),
                "1", "1", 1, 0, new BigDecimal("0"), "+",
                new BigDecimal("11.14"), new BigDecimal("11.16"), new BigDecimal("0"), "0",
                new BigDecimal("0"), new BigDecimal("10000"), "1");

//        doNothing().when(spy).validacionMontoMinimo(hisCtrCdtsEntity, 0);

        doReturn(new JSONAperCDT(condicionesCdts, cliente, transaccionesPagoCdt))
                .when(spy)
                .requestApertura(any());

        doReturn(false).when(spy).validacionApertura(salRenautdigEntity.getNumCdt());

        doReturn(ResponseEntity.status(HttpStatus.OK)
                .body("{\n" +
                        "    \"status\": {\n" +
                        "        \"code\": 202,\n" +
                        "        \"message\": \"ACCEPTED\"\n" +
                        "    },\n" +
                        "    \"requestUrl\": \"https://opaloprue.banbta.net:8002/OPAPCDT/CDTSDesmaterializado/v1/aperturaCDTsDes\",\n" +
                        "    \"parameters\": {},\n" +
                        "    \"result\": {\n" +
                        "        \"message\": \"La apertura del CDT Desmaterialiazado ha sido exitoso\"\n" +
                        "    }\n" +
                        "}")).when(spy).getResponseService(any(), any());

        doNothing().when(repositorySalRenautdig).actualizarEstadoRenovacion(salRenautdigEntity.getNumCdt());
        doNothing().when(spy).guardarHistorial(any(), any());
        doNothing().when(repositorySalPg).updateStateValue(salRenautdigEntity.getNumCdt(), 3);
//                Long.parseLong(reAperturaModel.getCdt().getNumCdt()));
//
//        repositorySalPg.updateStateValue(Long.parseLong(reAperturaModel.getCdt().getNumCdt()), 3);

        assertTrue(spy.apertura(reAperturaModel));
    }
}
