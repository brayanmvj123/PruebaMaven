package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.*;
import com.bdb.opalogdoracle.mapper.MapperNuevasCondicionesCdt;
import com.bdb.opalogdoracle.mapper.MapperReApertura;
import com.bdb.opalogdoracle.persistence.Response.ResponseService;
import com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura.*;
import com.bdb.opalogdoracle.persistence.jsonschema.servicedias.JSONCalculoDiasCDT;
import com.bdb.opalogdoracle.persistence.jsonschema.servicefechaven.JSONCalculoFechaVencimiento;
import com.bdb.opalogdoracle.persistence.jsonschema.servicesimuintereses.JSONTasaEfectivaNominal;
import com.bdb.opalogdoracle.persistence.jsonschema.servicesimuintereses.ParametrosJSONTasaEfectivaNominal;
import com.bdb.opalogdoracle.persistence.model.NuevasCondicionesCdtModel;
import com.bdb.opalogdoracle.persistence.model.ReAperturaModel;
import com.bdb.opalogdoracle.persistence.model.exception.ControlCdtsException;
import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opalogdoracle.persistence.model.servicecancel.PaymentTransaction;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.userdata.JSONIdentificacion;
import com.bdb.opaloshare.persistence.model.userdata.JSONTelefono;
import com.bdb.opaloshare.persistence.repository.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@JBossLog
public class RenovacionCondFlexImpl implements RenovacionCondFlexService {

    private final RepositorySalRenautdig repositorySalRenautdig;

    private final RepositoryCDTSLarge repositoryCDTSLarge;

    private final RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds;

    private final RepositoryDatosCliente repositoryDatosCliente;

    private final RepositoryTipPaisPar repositoryTipPaisPar;

    private final RepositoryTipCiud repoCiud;

    private final RepositoryTipDepar repoDepar;

    private final RepositoryTipDane repoDane;

    private final RepositoryTipCIUU repoCiiu;

    private final RepositoryTipSegmento repoSegmento;

    private final RepositoryTipDepositante repoDepo;

    private final RepositoryHisCtrCdts repositoryHisCtrCdts;

    private final RepositoryHisRenovaCdt repositoryHisRenovaCdt;

    private final RepositoryTipId repositoryTipId;

    private final RepositoryTipPeriodicidad repositoryTipPeriodicidad;

    private final RepositorySalPg repositorySalPg;

    private final RepositoryTipVarentorno repositoryTipVarentorno;

    private final RepositoryTasaSeg repositoryTasaSeg;

    private final SharedService sharedService;

    private final ResponseService responseService;

    private final CapitalService capitalService;

    private final MapperNuevasCondicionesCdt mapperNuevasCondicionesCdt;

    private final InformacionClienteService informacionClienteService;

    private final AbonoService abonoService;

    private final RepositoryParEndpointDown repositoryParEndpointDown;

    private final MapperReApertura mapperReApertura;

    private final ControlEstadoService controlEstadoService;

    private final RechazoRenovacionService rechazoRenovacionService;

    private final ValidarMontoMinimoService validarMontoMinimoService;

    private final NotificacionDcvBtaService notificacionDcvBtaService;


    RenovacionCondFlexImpl(RepositorySalRenautdig repositorySalRenautdig,
                           RepositoryCDTSLarge repositoryCDTSLarge,
                           RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds,
                           RepositoryDatosCliente repositoryDatosCliente,
                           RepositoryTipPaisPar repositoryTipPaisPar,
                           RepositoryTipCiud repoCiud,
                           RepositoryTipDepar repoDepar,
                           RepositoryTipDane repoDane,
                           RepositoryTipCIUU repoCiiu,
                           RepositoryTipSegmento repoSegmento,
                           RepositoryTipDepositante repoDepo,
                           RepositoryHisCtrCdts repositoryHisCtrCdts,
                           RepositoryHisRenovaCdt repositoryHisRenovaCdt,
                           RepositoryTipId repositoryTipId,
                           RepositoryTipPeriodicidad repositoryTipPeriodicidad,
                           RepositorySalPg repositorySalPg,
                           RepositoryTipVarentorno repositoryTipVarentorno,
                           RepositoryTasaSeg repositoryTasaSeg,
                           SharedService sharedService,
                           ResponseService responseService,
                           CapitalService capitalService,
                           MapperNuevasCondicionesCdt mapperNuevasCondicionesCdt,
                           InformacionClienteService informacionClienteService,
                           AbonoService abonoService,
                           RepositoryParEndpointDown repositoryParEndpointDown,
                           MapperReApertura mapperReApertura,
                           ControlEstadoService controlEstadoService1,
                           RechazoRenovacionService rechazoRenovacionService,
                           ValidarMontoMinimoService validarMontoMinimoService,
                           NotificacionDcvBtaService notificacionDcvBtaService) {
        this.repositorySalRenautdig = repositorySalRenautdig;
        this.repositoryCDTSLarge = repositoryCDTSLarge;
        this.repositoryTmpTasasCdtMds = repositoryTmpTasasCdtMds;
        this.repositoryDatosCliente = repositoryDatosCliente;
        this.repositoryTipPaisPar = repositoryTipPaisPar;
        this.repoCiud = repoCiud;
        this.repoDepar = repoDepar;
        this.repoDane = repoDane;
        this.repoCiiu = repoCiiu;
        this.repoSegmento = repoSegmento;
        this.repoDepo = repoDepo;
        this.repositoryHisCtrCdts = repositoryHisCtrCdts;
        this.repositoryHisRenovaCdt = repositoryHisRenovaCdt;
        this.repositoryTipId = repositoryTipId;
        this.repositoryTipPeriodicidad = repositoryTipPeriodicidad;
        this.repositorySalPg = repositorySalPg;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
        this.repositoryTasaSeg = repositoryTasaSeg;
        this.sharedService = sharedService;
        this.responseService = responseService;
        this.capitalService = capitalService;
        this.mapperNuevasCondicionesCdt = mapperNuevasCondicionesCdt;
        this.informacionClienteService = informacionClienteService;
        this.abonoService = abonoService;
        this.repositoryParEndpointDown = repositoryParEndpointDown;
        this.mapperReApertura = mapperReApertura;
        this.controlEstadoService = controlEstadoService1;
        this.rechazoRenovacionService = rechazoRenovacionService;
        this.validarMontoMinimoService = validarMontoMinimoService;
        this.notificacionDcvBtaService = notificacionDcvBtaService;
    }

    /**
     * <p>Este metodo es todo el CORE del servicio de renovación, el cual llama a todos lo metodos para consumirlos y asi
     * realizar la respectiva renovación de cada CDT Digital.</p>
     * <p>Para que el servicio de renovación sea exitoso se debe realizar el consumo de los siguientes servicios:</p>
     * <ul>
     *     <li><b>ratesCdt</b>: Este metodo permite consultar las tasas de los Cdts Digitales.</li>
     *     <li><b>simuladorFechaVenCdt</b>: Consume el servicio <i><u>Fecha de Vencimiento</u></i> expuesto en el
     *     <i><u>MODULE-OPLSIMULADOR</u></i>.</li>
     *     <li><b>simuladorIntereses</b>: Consume el servicio <u>Simulador de Cuota</u> expuesto en el
     *     <i><u>MODULE-OPLSIMULADOR</u></i>.</li>
     *     <li><b>apertura</b>: Este metodo se encarga de consumir el servicio <i><u>Apertura de CDTs Digitales</u></i>
     *     expuestos en el <i><u>MODULE-OPACDT</u></i>.</li>
     * </ul>
     *
     * @return Un <b>boolean</b> para para validar el estado del servicio, definiendo el valor de la siguiente manera:
     * <p>Si es <b>true</b>:</p>
     * <ol>
     *     <li>El servicio funciono y realiza correectamente las renovaciones.</li>
     *     <li>No se encontraron CDTs Digitales para renovar, por tanto se dara como exitoso el proceso.</li>
     * </ol>
     * <p>Ahora si el valor de retorno es <b>false</b>:</p>
     * <ol>
     *     <li>Indica que ha ocurrido una falla, validar en las tablas creadas en la BD por <u>SPRING_BATCH</th>.</u>
     * </ol>
     */
    @Override
    public void procesoRenovacion(HttpServletRequest http) {
        List<ParEndpointDownEntity> host = repositoryParEndpointDown.findAll();
//        final boolean[] resultAper = new boolean[1];
//        log.info("VALUE HOST: " + host);
        List<SalRenautdigEntity> listRenaut = getCdtsRenaut();
        try {
            if (!listRenaut.isEmpty()) {
                listRenaut.stream()
                        .filter(Objects::nonNull)
                        .filter(cdtRenaut -> cdtRenaut.getEstadoV().equals("P"))
                        .forEach(item -> getInfoHisCdts(item.getNumCdt())
                                .stream()
                                .filter(Objects::nonNull)
                                .filter(cdtDig -> !validacionApertura(Long.parseLong(cdtDig.getNumCdt())))
                                .forEach(cdt -> {
                                    log.info("SE INICIA EL PROCESO DE LA RENOVACIÓN");
                                    NuevasCondicionesCdtModel nuevasCondicionesCdt = nuevasCondicionesCdt(item, host);
                                    if (nuevasCondicionesCdt.getCapital() == null) return;
                                    log.info("Condiciones nuevas/actuales: " + nuevasCondicionesCdt.toString());
                                    BigDecimal capital = nuevasCondicionesCdt.getCapital().add(nuevasCondicionesCdt.getRendimientos());
                                    BigDecimal rates = ratesCdt(nuevasCondicionesCdt.getCodProd(),
                                            nuevasCondicionesCdt.getTipPlazo().toString(),
                                            nuevasCondicionesCdt.getPlazo(), capital, item.getNumTit());
                                    log.info("TASA: " + rates);
                                    log.info("INICIO CONSUMO SERVICIO SIMULADOR FECHA DE VENCIMIENTO");
                                    String resultSimuFechVen = simuladorFechaVenCdt(
                                            nuevasCondicionesCdt.getBase(),
                                            nuevasCondicionesCdt.getPlazo(),
                                            nuevasCondicionesCdt.getTipPlazo(),
                                            host);
                                    log.info("BASE: " + nuevasCondicionesCdt.getBase());
                                    log.info("MONTO A REINVERTIR: " + capital);
                                    log.info(cdt.getOplTipperiodTblTipPeriodicidad().toString());
                                    log.info("20");
                                    log.info("TASA EFECTIVA: " + rates.toString());
                                    log.info("SIMULADOR FECHA VENCIMIENTO: " + resultSimuFechVen);
                                    log.info(host);
                                    /*resultado.put("montoReinvertir", capital);
                                    resultado.put("fechaVencimiento", resultSimuFechVen);
                                    resultado.put("tasaEfectiva", rates);*/
                                    log.info("INICIO CONSUMO SERVICIO SIMULADOR DE INTERESES");
                                    JsonObject resultSimuInters = simuladorIntereses(nuevasCondicionesCdt.getBase(),
                                            capital.toString(),
                                            cdt.getOplTipperiodTblTipPeriodicidad().toString(),
                                            "20",
                                            rates.toString(),
                                            resultSimuFechVen,
                                            host);
                                    /*resultado.put("tasaNominal", resultSimuInters.getAsJsonObject("result")
                                            .get("tasaNominal")
                                            .getAsBigDecimal());*/
                                    log.info("INICIO CONSUMO SERVICIO APERTURA DE CDTS DIGITALES");

                                    apertura(mapperReApertura.reAperturaModel(nuevasCondicionesCdt,
                                            Long.parseLong(item.getNumTit()),
                                            homologarTipId(item.getTipId()),
                                            cdt,
                                            resultSimuFechVen,
                                            new BigDecimal(resultSimuInters.getAsJsonObject("result")
                                                    .get("tasaNominal")
                                                    .getAsString()),
                                            rates,
                                            capital,
                                            codigoCut(item.getNumCdt(), item.getNumTit(), 1L),
                                            host)
                                    );
                                }));
//                return resultAper[0];
                controlEstadoService.actualizarControlRenovacion("EJECUTANDOSE", 3);
                controlEstadoService.actualizarHistorialResultado("paso2");
            } else {
                log.warn("No hay CDTs Digitales para renovar.");
                controlEstadoService.actualizarControlRenovacion("EJECUTANDOSE", 5);
                controlEstadoService.actualizarHistorialResultado("paso2");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro CDTS Digitales para renovar.");
            }
//                return true;
        } catch (Exception e) {
            log.error("Error en el proceso de renovación: " + e.getMessage());
            controlEstadoService.actualizarControlRenovacion("FALLIDO", 2);
            notificacionDcvBtaService.enviarNotificacion("RENAUT_FAIL");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Genero error :(");
//            return false;
        }
    }

    /**
     * Este metodo permite obtener los CDTs Digitales que se renovarán, esta data se obtiene de la tabla
     * {@link SalRenautdigEntity}.
     *
     * @return La lista de los CDTS Digitales con la respectiva información de cancelación.
     */
    public List<SalRenautdigEntity> getCdtsRenaut() {
        return repositorySalRenautdig.findAll();
    }

    /**
     * Obtiene la información historica de los CDTs de la tabla {@link HisCDTSLargeEntity}.
     *
     * @param cdtsRenaut <i><u>Titulo</u></i> o <i><u>Número de CDT</u></i> que se renovarán.
     * @return Toda la información del CDT Digital es encontrada en la tabla {@link HisCDTSLargeEntity},
     * con algunos campos especificos se podra realizar el consumo de los servicios de Tasas, Fecha de vencimiento y
     * Simulador
     */
    public List<HisCDTSLargeEntity> getInfoHisCdts(Long cdtsRenaut) {
        return Optional.ofNullable(repositoryCDTSLarge.findByNumCdt(cdtsRenaut.toString())).orElse(null);
    }

    /**
     * Se realiza la consulta a la tabla de {@link TmpTasasCdtMdsEntity} para validar la tasa adecuada a los CDTs
     * Digitales de acueroo a los siguiente parametros.
     *
     * @param codProd  Codigo de producto (Se utiliza el valor almacenado en el campo <i><b>codProd</b></i> de la tabla
     *                 {@link HisCDTSLargeEntity})
     * @param tipPlazo El tipo de plazo (Se utiliza el valor almacenado en el campo <i><b>OPL_PAR_TIPPLAZO</b></i>
     *                 de la tabla {@link HisCDTSLargeEntity})
     * @param plazo    El plazo (Se utiliza el valor almacenado en el campo <i><b>plazo</b></i> de la tabla
     *                 {@link HisCDTSLargeEntity})
     * @param valorCdt El valor del CDT (Se utiliza el valor almacenado en el campo <i><b>valorCDT</b></i> de la tabla
     *                 {@link HisCDTSLargeEntity})
     * @return La información de la tasa que satisface las necesidades del CDT.
     */
    public BigDecimal ratesCdt(String codProd, String tipPlazo, Integer plazo, BigDecimal valorCdt, String idClient) {
        log.info("Se inicia la consulta de la tasa.");
        tipPlazo = tipPlazo.equals("2") ? "1" : "2";
        return repositoryTmpTasasCdtMds.findByCodProdAndTipPlazoAndPlazoMinLessThanEqualAndPlazoMaxGreaterThanEqualAndMontoMinLessThanEqualAndMontoMaxGreaterThanEqualOrderByIdentificadorAsc(
                        codProd, tipPlazo, plazo, plazo, valorCdt.longValue(), valorCdt.longValue())
                .getTasaSpread()
                .add(validateClientSegment(idClient));
    }

    /**
     * Se realiza la validación al tipo de segmento del cliente, si el cliente esta en un segmento preferencial, pyme le
     * adicionara unos puntos adicionales a la tasa efectiva parametrizada en MDS.
     *
     * @param idClient Identificación del cliente.
     * @return Valor adicional para agregar a la tasas afectiva.
     */
    private BigDecimal validateClientSegment(String idClient) {
        Optional<HisClientesLargeEntity> cliente = repositoryDatosCliente.findById(idClient);
        if (cliente.isPresent())
            return validateRatesSegment(cliente.get().getOplTipsegmentoTblCodSegmento());
        else throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private BigDecimal validateRatesSegment(Integer oplTipsegmentoTblCodSegmento) {
        return repoSegmento.findById(oplTipsegmentoTblCodSegmento)
                .map(item -> Optional.ofNullable(repositoryTasaSeg.findBySegmento(Integer.parseInt(item.getHomoCrm())))
                        .map(TmpTasaSegEntity::getPuntos)
                        .orElse(new BigDecimal("0")))
                .orElse(new BigDecimal("0"));
    }

    /**
     * Este metodo realiza el consumo del servicio expuesto en el modulo OPLSIMULADOR, el cual calcula la fecha de
     * vencimiento de un CDT Digital.
     *
     * @param base     Base, este campo se toma del valor almacenado en el campo <b><i>OPL_PAR_TIPBASE</i></b>.
     * @param plazo    Plazo, este campo se toma del valor almacenado en el campo <b><i>PLAZO</i></b>
     * @param tipPlazo Tipo de plazo, esta campo se toma del valor almacenado en el campo <b><i>OPL_PAR_TIPPLAZO</i></b>
     * @return La <i><b>fecha de vencimiento</b></i> del CDT a renovar.
     */
    public String simuladorFechaVenCdt(Integer base, Integer plazo, Integer tipPlazo, List<ParEndpointDownEntity> endpoints) {
        log.info("Se inicia la operación simuladorFechaVenCdt...");
        JSONCalculoFechaVencimiento requestFechaVen = new JSONCalculoFechaVencimiento();
        requestFechaVen.setBase(base);
        requestFechaVen.setPlazo(plazo.longValue());
        requestFechaVen.setTipoPlazo(tipPlazo);
        requestFechaVen.setFechaApertura(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONCalculoFechaVencimiento> requestEntity = new HttpEntity<>(
                requestFechaVen, httpHeaders);
        String url = endpoints.stream()
                .filter(endpoint -> endpoint.getId() == 15L)
                .findFirst()
                .orElse(new ParEndpointDownEntity(15L, "http://localhost:9090/CDTSDesmaterializado/v1/simulador/calculoFechaVencimiento")).getRuta();
//        String url = host + "OPLSIMULADOR/CDTSDesmaterializado/v1/simulador/calculoFechaVencimiento";
        //"http://localhost:8080/CDTSDesmaterializado/v1/simulador/calculoFechaVencimiento";

        log.info("Se inicia el consumo del servicio...");
        ResponseEntity<String> response = getResponseService(requestEntity, url);
        JsonObject resultado = new Gson().fromJson(response.getBody(), JsonObject.class);
        return resultado.getAsJsonObject("resultado").get("fechaVencimiento").getAsString();
    }

    public <T> ResponseEntity<String> getResponseService(HttpEntity<T> requestEntity, String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        } catch (HttpStatusCodeException e) {
            controlEstadoService.actualizarControlRenovacion("FALLIDO", 1);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lastimosamente el servicio consumido: " +
                    url + " fallo: " + e.getMessage());
        }
    }

    /**
     * Este metodo realiza el consumo del servicio expuesto en el modulo OPLSIMULADOR, el cual calcula los dias entre la
     * fecha de apertura y la fecha de vencimiento.
     *
     * @param base      Base, este campo se toma del valor almacenado en el campo <b><i>OPL_PAR_TIPBASE</i></b>.
     * @param fechaVen  Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                  del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param endpoints Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El resultado del <i>número de dias</i> entre la fecha de apertura y la fecha vencimiento.
     */
    public String calculoServiceDias(Integer base, String fechaVen, List<ParEndpointDownEntity> endpoints) {
        log.info("INICIO CONSUMO DE SIMULADOR DE PLAZO EN DIAS");
        JSONCalculoDiasCDT jsonCalculoDiasCDT = new JSONCalculoDiasCDT();
        jsonCalculoDiasCDT.setBase(base);
        jsonCalculoDiasCDT.setFechaApertura(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        log.info("FORMATO FECHA VEN: " + fechaVen + " - " +
                LocalDate.parse(fechaVen, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        jsonCalculoDiasCDT.setFechaVencimiento(LocalDate.parse(fechaVen, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONCalculoDiasCDT> requestEntity = new HttpEntity<>(
                jsonCalculoDiasCDT, httpHeaders);
        String url = endpoints.stream()
                .filter(endpoint -> endpoint.getId() == 16L)
                .findFirst()
                .orElse(new ParEndpointDownEntity(16L, "http://localhost:9090/CDTSDesmaterializado/v1/simulador/calculoDiasCDT")).getRuta();
//                host + "OPLSIMULADOR/CDTSDesmaterializado/v1/simulador/calculoDiasCDT";
        //"http://localhost:8080/CDTSDesmaterializado/v1/simulador/calculoDiasCDT";

        log.info("Se inicia el consumo del servicio...");
        ResponseEntity<String> response = getResponseService(requestEntity, url);
        JsonObject resultado = new Gson().fromJson(response.getBody(), JsonObject.class);
        return resultado.getAsJsonObject("resultado").get("plazoenDias").getAsString();
    }

    /**
     * Este metodo consume el servicio expuesto en el modulo OPLSIMULADOR, el cual se encarga simular los datos enviados
     * al request y calcular la tasa Nominal y Efectiva.
     *
     * @param base         Base, este campo se toma del valor almacenado en el campo <b><i>OPL_PAR_TIPBASE</i></b>.
     * @param capital      Capital (Se utiliza el valor almacenado en el campo <i><b>valorCDT</b></i> de la tabla {@link HisCDTSLargeEntity}).
     * @param periodicidad Periodicidad, se utiliza el mismo valor almacenado en el capo <i><b>OPL_PAR_TIPPERIODICIDAD</b></i>
     *                     de la tabla {@link HisCDTSLargeEntity}).
     * @param retencion    Campo fijo, valor predeterminado 20.
     * @param tasaFija     Tasa Fija, se obtiene de la consulta a la tabla <b><i>TmpTasasCdtMdsEntity</i></b>.
     * @param fechaVen     Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                     del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param endpoints    Endpoints, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return Un objeto de tipo JSONObject, los valores importantes a tener en cuenta en el response son los valores de
     * TasaNominal y TasaEfectiva.
     */
    public JsonObject simuladorIntereses(Integer base, String capital, String periodicidad, String retencion,
                                         String tasaFija, String fechaVen, List<ParEndpointDownEntity> endpoints) {
        log.info("ENTRA AL CONSUMO DEL SERVICIO SIMULADOR CUOTA V1.");
        JSONTasaEfectivaNominal requestSimu = new JSONTasaEfectivaNominal();
        requestSimu.setCanal("OPL766");

        ParametrosJSONTasaEfectivaNominal requestParam = new ParametrosJSONTasaEfectivaNominal();
        requestParam.setBase(String.valueOf(base == 1 ? 2 : 1));
        requestParam.setCapital(capital);
        requestParam.setDiasPlazo(calculoServiceDias(base, fechaVen, endpoints));
        log.info("despues de los dias");
        requestParam.setPeriodicidad(homologacionPeriodicidad(Integer.parseInt(periodicidad)));
        requestParam.setRetencion(retencion);
        requestParam.setTasaFija(tasaFija);

        log.info("RequestParam: " + requestParam.toString());

        requestSimu.setParametros(requestParam);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONTasaEfectivaNominal> requestEntity = new HttpEntity<>(
                requestSimu, httpHeaders);

        String url = endpoints.stream()
                .filter(endpoint -> endpoint.getId() == 17L)
                .findFirst()
                .orElse(new ParEndpointDownEntity(17L, "http://localhost:9090/TasaEfectivaNominal/v1/simulacionCuota")).getRuta();
//        final String url = host + "OPLSIMULADOR/TasaEfectivaNominal/v1/simulacionCuota";
        //"http://localhost:8080/TasaEfectivaNominal/v1/simulacionCuota";

        log.info("Se inicia el consumo del servicio...");
        ResponseEntity<String> response = getResponseService(requestEntity, url);
        return new Gson().fromJson(response.getBody(), JsonObject.class);
    }

    /**
     * Este metodo realiza el consumo de la tabla {@link HisCtrCdtsEntity} para traer el campo CODIGO CUT, el cual es
     * enviado por el Lab y guardado por el servicio expuesto en el modulo OPLCONTROL.
     *
     * @param numCdt  Número de CDT.
     * @param numTit  Número de identificación del cliente.
     * @param control El tipo de control asignado al CDT.
     * @return El <i>código CUT</i> de la renovación.
     */
    public String codigoCut(Long numCdt, String numTit, Long control) {
        log.info("Se inicia la operación codigoCut...");
        HisCtrCdtsPk hisCtrCdtsPk = new HisCtrCdtsPk();
        hisCtrCdtsPk.setNumCdt(numCdt);
        hisCtrCdtsPk.setNumTit(numTit);
        hisCtrCdtsPk.setParControlesEntity(control);
        return repositoryHisCtrCdts.findById(hisCtrCdtsPk).get().getCodCut();
    }

    /**
     * Este metodo crea el request de consumo del servicio aperturaCDT expuesto en el modulo OPAPCDT.
     *
     * @param reAperturaModel - numTit       Número de identificación del cliente.
     *                        - tipId        Tipo de identificación del cliente.
     *                        - cdt          Número de CDT.
     *                        - fechaVen     Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                        del modulo <i>MODULE-OPLSIMULADOR</i>.
     *                        - tasaNominal  Tasa Nominal se obtiene del consumo del <u>servicio</u> de <i>Simulador de Cuota</i>.
     *                        - tasaEfectiva Tasa Efectiva, se obtiene de la <u>consulta</u> a las tasas.
     *                        - valorCdt     Valor del Cdt o Capital, se obtiene de la tabla <i><u>SalRenautdigEntity</u></i>.
     *                        - hosts         Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El request completo para realizar el consumo del <u><i>servicio de apertura</i></u>.
     */
    public JSONAperCDT requestApertura(ReAperturaModel reAperturaModel) {
            /*Long numTit, Long tipId, HisCDTSLargeEntity cdt, String fechaVen,
                                       BigDecimal tasaNominal, BigDecimal tasaEfectiva, BigDecimal valorCdt,
                                       String codCut, List<ParEndpointDownEntity> hosts) {*/
        log.info("Se inicia la operación requestApertura...");
        JSONAperCDT jsonAperCDT = new JSONAperCDT();
        jsonAperCDT.setCliente(jsonClientDatos(reAperturaModel.getNumTit(), reAperturaModel.getTipId()));
        jsonAperCDT.setCdt(jsonCondCDT(reAperturaModel));
                /*reAperturaModel.getCdt(), reAperturaModel.getResultSimuFechVen(),
                reAperturaModel.getTasaNominal(), reAperturaModel.getTasaEfectiva(), reAperturaModel.getCapital(),
                reAperturaModel.getCodigoCut(), reAperturaModel.getHost()));*/
        jsonAperCDT.setTransaccionesPagoCdt(jsonPagCDTDes(reAperturaModel.getNumTit(),
                reAperturaModel.getCdt().getOplOficinaTblNroOficina(), reAperturaModel.getCapital(),
                reAperturaModel.getCdt().getNumCdt()));
        return jsonAperCDT;
    }

    /**
     * Este metodo crea la lista para llenar el objeto Cliente del request de Apertura.
     *
     * @param numTit Número de identificación del cliente.
     * @param tipId  Tipo de identificación del cliente.
     * @return La lista con la información de los clientes dueños del CDT.
     */
    public List<JSONClientDatos> jsonClientDatos(Long numTit, Long tipId) {
        log.info("Se inicia la operación jsonClientDatos...");

        HisClientesLargeEntity cliente = repositoryDatosCliente.findByNumTitAndOplTipidTblCodId(numTit.toString(), tipId.intValue());
        List<JSONClientDatos> listClients = new ArrayList<>();
        JSONClientDatos jsonClientDatos = new JSONClientDatos();

        JSONIdentificacion jsonIdentificacion = new JSONIdentificacion();
        jsonIdentificacion.setTipoId(cliente.getOplTipidTblCodId().toString());
        jsonIdentificacion.setNumId(cliente.getNumTit());
        jsonClientDatos.setIdentificacion(jsonIdentificacion);

        jsonClientDatos.setNombre(cliente.getNomTit());
        jsonClientDatos.setDireccion(cliente.getDirTit());

        JSONTelefono jsonTelefono = new JSONTelefono();
        jsonTelefono.setExtension(cliente.getExtension());
        jsonTelefono.setNumero_fax(cliente.getFaxTit());
        jsonTelefono.setTelefono(cliente.getTelTit());

        jsonClientDatos.setTelefono(jsonTelefono);
        jsonClientDatos.setCorreoElectronico(cliente.getCorreo());
        jsonClientDatos.setClasePersona(cliente.getClaPer());
        jsonClientDatos.setDeclaraRenta(cliente.getDeclaRenta());
        jsonClientDatos.setIndicadorExtranjero(cliente.getIndExtra());
        jsonClientDatos.setFechaNacimiento(cliente.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        jsonClientDatos.setPaisNacimiento(repositoryTipPaisPar.findByCodPais(cliente.getPaisNacimiento()).getHomoCrm());
        jsonClientDatos.setCodigoCIUU(repoCiiu.findById(cliente.getOplTipciiuTblCodCiiu())
                .orElse(new TipCIIUEntity()).getHomoCrm());
        jsonClientDatos.setCodigoPais(repositoryTipPaisPar.findByCodPais(cliente.getOplTippaisTblCodPais()).getHomoCrm());
        jsonClientDatos.setCodigoDepartamento(repoDepar.findById(cliente.getOplTipdeparTblCodDep())
                .orElse(new TipDeparEntity()).getHomoCrm());
        jsonClientDatos.setCodigoCiudad(repoCiud.findById(cliente.getOplTipciudTblCodCiud())
                .orElse(new TipCiudEntity()).getHomoCrm());
        jsonClientDatos.setCodigoSegmentoComercial(repoSegmento.findById(cliente.getOplTipsegmentoTblCodSegmento())
                .orElse(new TipSegmentoEntity()).getHomoCrm());
        jsonClientDatos.setRetencion(cliente.getRetencion());
        jsonClientDatos.setTipoTitular(1);
        jsonClientDatos.setCodigoDane(repoDane.findById(cliente.getOplParTipdaneTblCodDane())
                .orElse(new TipDaneEntity()).getHomoCrm());
        listClients.add(jsonClientDatos);
        return listClients;
    }

    /**
     * Este metodo crea el objeto Condiciones de CDT del request de Apertura.
     *
     * @param reAperturaModel - cdt          Objeto con toda la informacion de la tabla {@link HisCDTSLargeEntity} del CDT a renovar.
     *                        - fechaVen     Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                        del modulo <i>MODULE-OPLSIMULADOR</i>.
     *                        - tasaNominal  Tasa Nominal se obtiene del consumo del <u>servicio</u> de <i>Simulador de Cuota</i>.
     *                        - tasaEfectiva Tasa Efectiva, se obtiene de la <u>consulta</u> a las tasas.
     *                        - valorCdt     Valor del Cdt o Capital, se obtiene de la tabla <i><u>SalRenautdigEntity</u></i>.
     *                        - host         Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return Tipo de objeto de la clase {@link JSONCondCDT} con la informacion del <i><u>nuevo CDT</u></i>.
     */
    public JSONCondCDT jsonCondCDT(ReAperturaModel reAperturaModel) {
            /*HisCDTSLargeEntity cdt, String fechaVen, BigDecimal tasaNominal, BigDecimal tasaEfectiva,
                                   BigDecimal valorCdt, String codCut, List<ParEndpointDownEntity> host) {*/
        log.info("Se inicia la operación jsonCondCDT...");

        JSONCondCDT jsonCondCDT = new JSONCondCDT();
        jsonCondCDT.setFecha(LocalDateTime.now().toString());
        jsonCondCDT.setUsuarioNt(reAperturaModel.getCdt().getUsuario());
        jsonCondCDT.setCanal(reAperturaModel.getCdt().getCanal());
        jsonCondCDT.setCodCut(reAperturaModel.getCodigoCut());
        jsonCondCDT.setCodTransaccion(reAperturaModel.getCdt().getCodTrn());
        jsonCondCDT.setNumCdtDigital(numCdtAsignado(reAperturaModel.getHost()));
        jsonCondCDT.setCodProd(reAperturaModel.getNuevasCondicionesCdtModel().getCodProd()); //cambia
        jsonCondCDT.setMercado(reAperturaModel.getCdt().getMercado()); ///validar
        jsonCondCDT.setUnidadNegocio(reAperturaModel.getCdt().getUnidNegocio());
        jsonCondCDT.setUnidCEO(reAperturaModel.getCdt().getUnidCeo());
        jsonCondCDT.setFormaPago(reAperturaModel.getCdt().getFormaPago());
        jsonCondCDT.setDepositante(repoDepo.findById(reAperturaModel.getCdt().getOplDepositanteTblTipDepositante())
                .orElse(new TipDepositanteEntity()).getHomoDcvsa());

        JSONPlazoCDT jsonPlazoCDT = new JSONPlazoCDT();
        jsonPlazoCDT.setCantidadPlazo(reAperturaModel.getNuevasCondicionesCdtModel().getPlazo()); //cambia
        jsonPlazoCDT.setTipo(reAperturaModel.getNuevasCondicionesCdtModel().getTipPlazo().toString()); //cambia

        jsonCondCDT.setPlazo(jsonPlazoCDT);
        jsonCondCDT.setFechaEmision(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        jsonCondCDT.setFechaVencimiento(LocalDate.parse(reAperturaModel.getResultSimuFechVen(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        jsonCondCDT.setBase(reAperturaModel.getNuevasCondicionesCdtModel().getBase().toString()); //cambia
        jsonCondCDT.setModalidad(reAperturaModel.getCdt().getModalidad());
        jsonCondCDT.setTipoTasa(reAperturaModel.getCdt().getOplTiptasaTblTipTasa()); //SI EL TIPO DE TASAS ES FIJA EL SPREAD ES 0
        jsonCondCDT.setTipoPeriodicidad(reAperturaModel.getCdt().getOplTipperiodTblTipPeriodicidad());
        jsonCondCDT.setSpread(reAperturaModel.getCdt().getSpread()); //DEPENDE DEL TIPO DE TASA , SI NO ERROR
        jsonCondCDT.setSignoSpread(reAperturaModel.getCdt().getSignoSpread());
        jsonCondCDT.setTasaEfectiva(reAperturaModel.getTasaEfectiva());
        jsonCondCDT.setTasaNominal(reAperturaModel.getTasaNominal()); //cambia
        jsonCondCDT.setMoneda(reAperturaModel.getCdt().getMoneda());
        jsonCondCDT.setUnidadUVR(reAperturaModel.getCdt().getUnidUvr());
        jsonCondCDT.setCantidadUnidad(reAperturaModel.getCdt().getCantUnid());
        jsonCondCDT.setValor(reAperturaModel.getCapital()); //cambia
        jsonCondCDT.setTipoTitularidad(reAperturaModel.getCdt().getTipTitularidad());
        return jsonCondCDT;
    }

    /**
     * Este metodo crea la lista para llenar el objeto Transacciones de pago del request de Apertura.
     *
     * @param numTit   Número de identificación del cliente.
     * @param oficina  Número de oficina en la cual radico el CDT.
     * @param valorCdt Valor del Cdt o Capital, se obtiene de la tabla <i><u>SalRenautdigEntity</u></i>.
     * @return Lista de Objetos de la clase {@link JSONPagCDTDes}, el cual tiene la informacion del pago del CDT.
     */
    public List<JSONPagCDTDes> jsonPagCDTDes(Long numTit, Integer oficina, BigDecimal valorCdt, String numCdt) {
        log.info("Se inicia la operación jsonPagCDTDes...");

        List<JSONPagCDTDes> listPagCDT = new ArrayList<>();
        JSONPagCDTDes jsonPagCDTDes = new JSONPagCDTDes();
        jsonPagCDTDes.setIdBeneficiario(numTit);
        jsonPagCDTDes.setIdCliente(numTit.toString());
        jsonPagCDTDes.setNroPordDestino(numCdt);// # cdt viejo
        jsonPagCDTDes.setProceso("2"); //RENOVACION
        jsonPagCDTDes.setTipoTransaccion(7);
        jsonPagCDTDes.setTipTran("1");
        jsonPagCDTDes.setUnidadDestino(oficina.toString());// OPL_OFICINA
        jsonPagCDTDes.setUnidadOrigen(oficina.toString());
        jsonPagCDTDes.setValor(valorCdt);
        listPagCDT.add(jsonPagCDTDes);
        return listPagCDT;
    }

    /**
     * En los servicios de <u><i>SimuladorFechaVencimiento</i></u> y <i><u>SimuladorDias</u></i> la base <u>1</u> y <u>2</u>
     * se encuentran invertidos por lo tanto este metodo invierte los codigos.
     *
     * @param valor Base enviada.
     * @return El valor de la base convertida.
     */
    public Integer convertBase(Integer valor) {
        if (valor == 1) return 2;
        else if (valor == 2) return 1;
        else return valor;
    }

    /**
     * Este metodo realiza el consumo del servicio de <i><u>Asignación Número de CDT</u></i> para obtener el nuevo
     * número del CDT Digital.
     *
     * @param hosts Requiere el host para realizar el consumo de acuerdo al ambiente.
     * @return El nuevo número del CDT Digital.
     */
    public String numCdtAsignado(List<ParEndpointDownEntity> hosts) {
        log.info("Se inicia la operación numCdtAsignado...");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONAsignarNumCDT jsonAsignarNumCDT = new JSONAsignarNumCDT();
        jsonAsignarNumCDT.setCanal("OPL766");
        Parametros parametros = new Parametros();
        parametros.setCodTransaccion("1");
        jsonAsignarNumCDT.setParametros(parametros);

        HttpEntity<JSONAsignarNumCDT> requestEntity = new HttpEntity<>(jsonAsignarNumCDT, httpHeaders);
        String url = hosts.stream()
                .filter(endpoint -> endpoint.getId() == 19L)
                .findFirst()
                .orElse(new ParEndpointDownEntity(19L, "http://localhost:8082/CDTSDesmaterializado/v1/asignarNumCDTDigital"))
                .getRuta();
//        final String url = host + "OPAPCDT/CDTSDesmaterializado/v1/asignarNumCDTDigital";
        //"http://localhost:8080/CDTSDesmaterializado/v1/asignarNumCDTDigital";

        log.info("Se inicia el consumo del servicio...");
//        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = getResponseService(requestEntity, url);
                /*restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });*/
        JsonObject body = new Gson().fromJson(response.getBody(), JsonObject.class);
        return body.get("numCdtDigital").getAsString();
    }

    /**
     * @param reAperturaModel Contiene los siguientes parametros:
     *                        - numTit       Número de identificación del cliente.
     *                        - tipId        Tipo de identificación del cliente.
     *                        - cdt          Objeto de tipo {@link HisCDTSLargeEntity} con toda la informacion de la tabla {@link HisCDTSLargeEntity} del CDT a renovar.
     *                        - fechaVen     <p>Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                        del modulo <i>MODULE-OPLSIMULADOR</i>.</p>
     *                        - tasaNominal  Tasa Nominal se obtiene del consumo del <u>servicio</u> de <i>Simulador de Cuota</i>.
     *                        - tasaEfectiva Tasa Efectiva, se obtiene de la <u>consulta</u> a las tasas.
     *                        - valorCdt     Valor del Cdt o Capital, se obtiene de la tabla <i><u>SalRenautdigEntity</u></i>.
     *                        - codCut       Codigo CUT, esta valor es enviado por el frontend al momento realizar la notificación de renovación, este valor queda
     *                        almacenado en la tabla {@link HisCtrCdtsEntity} en el campo <i><u>COD_CUT</u></i>.
     *                        - host         Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El tipo resultado varia segun:
     * <ol>
     *     <li>Si el resultado es <b><i><u>TRUE : La <u>apertura</u> fue correcta.</u></i></b></li>
     *     <li>Si el resultado es <b><i><u>FALSE : La <u>apertura</u> fue incorrecta.</u></i></b></li>
     * </ol>
     */
    public boolean apertura(ReAperturaModel reAperturaModel)

//            Long numTit, Long tipId, HisCDTSLargeEntity cdt, String fechaVen, BigDecimal tasaNominal,
//                            BigDecimal tasaEfectiva, BigDecimal valorCdt, String codCut, String host)
    {
        log.info("Se inicia la operación apertura...");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONAperCDT> requestEntity = new HttpEntity<>(
                requestApertura(reAperturaModel), httpHeaders);
        log.info("Ates apertura :)...");

        String url = reAperturaModel.getHost().stream()
                .filter(endpoint -> endpoint.getId() == 18L)
                .findFirst()
                .orElse(new ParEndpointDownEntity(18L, "http://localhost:8082/CDTSDesmaterializado/v1/aperturaCDTsDes")).getRuta();
//        final String url = host + "OPAPCDT/CDTSDesmaterializado/v1/aperturaCDTsDes";
        //"http://localhost:8080/CDTSDesmaterializado/v1/aperturaCDTsDes";
        log.info("despues apertura :)...");

        if (!validacionApertura(Long.parseLong(reAperturaModel.getCdt().getNumCdt()))) {
//            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = getResponseService(requestEntity, url);
//            restTemplate.exchange(url,
//                    HttpMethod.POST,
//                    requestEntity,
//                    new ParameterizedTypeReference<String>() {
//                    });

            log.info("STATUS DEL SERVICIO DE APERTURA: " + response.getStatusCode());

            if (response.getStatusCode().is2xxSuccessful()) {
                repositorySalRenautdig.actualizarEstadoRenovacion(Long.parseLong(reAperturaModel.getCdt().getNumCdt()));
                guardarHistorial(Long.parseLong(requestEntity.getBody().getCdt().getNumCdtDigital()),
                        Long.parseLong(reAperturaModel.getCdt().getNumCdt()));

                repositorySalPg.updateStateValue(Long.parseLong(reAperturaModel.getCdt().getNumCdt()), 3);
            }

            return response.getStatusCode().is2xxSuccessful();
        } else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se encuentra un CDT Digital que ya " +
                    "fue renovado: " + reAperturaModel.getCdt().getNumCdt());

    }

    public boolean validacionMontoMinimo(HisCtrCdtsEntity hisCtrCdtsEntity, int inicioIntentos) throws ControlCdtsException {
        log.info("SE VALIDA EL MONTO A RENOVAR SE HA MAYOR AL VALOR MINIMO.");
        SalRenautdigEntity salRenautdigByNumCdt = repositorySalRenautdig.findByNumCdt(hisCtrCdtsEntity.getNumCdt());
        BigDecimal valorMinimo = new BigDecimal(repositoryTipVarentorno.findByDescVariable("VALOR_MIN_APERTURA")
                .getValVariable());
        if (!validarMontoMinimoService.montoMinimo(salRenautdigByNumCdt, hisCtrCdtsEntity, valorMinimo)) {
            log.info("Se realiza el inicio del transpaso del CDT Digital por no cumplir con el monto minimo exigido.");
            if (rechazoRenovacionService.rechazoRenovacion(salRenautdigByNumCdt)) {
                log.warn("Lastimosamente el monto a reinvertir no supera el monto minimo parametrizado. El CDT se " +
                        "cancelara en el proceso de cancelación automatica.");
                return false;
            } else {
                log.warn("POR ALGÚN MOTIVO NO SE REALIZO EL TRANSPASO ... SE INTENTARA NUEVAMENTE ...");
                for (int i = inicioIntentos; i < 3; i++) {
                    validacionMontoMinimo(hisCtrCdtsEntity, inicioIntentos);
                }
            }
        }
        return true;
    }

    /**
     * Este metodo permite almacena en la tabla {@link HisRenovaCdtEntity} el historial de las renovaciones de los CDTs
     * Digitales.
     * Al momento de realizar el almacenamiento se tienen en cuenta:
     * <ul>
     *     <li>Si, el CDT Digital nunca se ha renovado se guardara sin realizar alguna validación de CDT Origen o
     *     cantidad de veces renovado.</li>
     *     <li>Si, el CDT Digital se ha renoavado más de una vez, se validara el CDT Origen, la cantidad de veces que ha
     *     sido renovado.</li>
     * </ul>
     *
     * @param numCdtActual Nuevo número de CDT Digital.
     * @param numCdtAnt    Anterior número de CDT Digital.
     */
    public void guardarHistorial(Long numCdtActual, Long numCdtAnt) {
        if (repositoryHisRenovaCdt.existsByCdtAct(numCdtAnt)) {
            HisRenovaCdtEntity hisRenovaCdtEntity = repositoryHisRenovaCdt.findByCdtAct(numCdtAnt);
            Long cantidad = repositoryHisRenovaCdt.countByCdtOrigen(hisRenovaCdtEntity.getCdtOrigen());
            repositoryHisRenovaCdt.save(new HisRenovaCdtEntity(numCdtActual, numCdtAnt,
                    hisRenovaCdtEntity.getCdtOrigen(), cantidad.intValue() + 1));
        } else {
            repositoryHisRenovaCdt.save(new HisRenovaCdtEntity(numCdtActual, numCdtAnt, numCdtAnt, 1));
        }
    }

    /**
     * Este metodo realiza el consumo del servicio <i><u>estadoRenaut</u></i> expuesto por <i><u>MODULE_OPLSSQLS</u></i>,
     * el cual almacena el estado final del proceso de renovación.
     *
     * @param http   URL donde se consume el servicio.
     * @param estado Los estados (AJUSTAR LA DOCUMENTACION)
     */
    public void almacenarEstadoRenautSQLServer(String http, String estado) {
        String host = sharedService.generarUrl(http, "OPLBATCH");
        final String url = host + "OPLSSQLS/CDTSDesmaterializado/v1/renovacion/estadoRenaut?status=" + estado;
        //"http://localhost:8080/CDTSDesmaterializado/v1/renovacion/estadoRenaut?status="+estado;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<String>() {
                });
        response.getStatusCodeValue();
    }

    /**
     * <p>Este metodo realiza la homologación de la descripción del tipo de identificación por el codigo asignado en el
     * campo HOMO_DCVSA en la tabla {@link TipidParDownEntity}.</p>
     *
     * @param tipId Descripción del tipo de identificación del cliente.
     * @return Codigo homologado del tipo de identificación.
     */
    public Long homologarTipId(String tipId) {
        return repositoryTipId.findByHomoDcvsa(tipId.equals("NIT") ? "NIT2" : tipId).getCodId().longValue();
    }

    /**
     * Este metodo permite homologar el codigo de periodicidad al codigo del <u>servicio</u> de <i><u>Simulador de Cuota</u></i>.
     *
     * @param periodicidad Periodicidad, se envia el valor almacenado en la tabla {@link HisCDTSLargeEntity}, en el campo
     *                     <u><i>OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD</i></u>.
     * @return El codigo emitido por el campo <u><i>HOMO_SIMCOUTA</i></u>.
     */
    public String homologacionPeriodicidad(Integer periodicidad) {
        return repositoryTipPeriodicidad.findByTipPeriodicidad(periodicidad).getHomoSimcuota().toString();
    }

    /**
     * Realiza la validación si el CDT Digital que se quiere aperturar ya existe.
     *
     * @param numCdt Número de digital.
     * @return El resultado de la validación si existe o no el CDT Digital.
     */
    public boolean validacionApertura(Long numCdt) {
        return repositoryHisRenovaCdt.existsByCdtAnt(numCdt);
    }

    /**
     * Reinversión y retiro flexible del CDT.
     *
     * @param cdt Info CDT
     */
    private void flexibleSubscription(SalRenautdigEntity cdt, HisCtrCdtsEntity hisCtrCdtsEntity, String endpoint) {
        log.infof("Starting flexible subscription for CDT %s...", cdt.getNumCdt());

        // Getting Client Account Data
        InfoCtaClienteModel ctaClient = informacionClienteService.obtenerInformacion(cdt);

        //  Monetary Returns
        List<PaymentTransaction> capitals = new ArrayList<>();

        // Conditions
        for (HisCondicionCdtsEntity item : hisCtrCdtsEntity.getHisCondicionCdtsEntity()) {
            if (item.getRendimientos() == 2) { // Withdrawal
                capitals.add(new PaymentTransaction(4, cdt.getIntNeto()));
                // Adding payment transactions
                ctaClient.setTransaccionPagoList(capitals);

                // Consumption
                log.info("Now doing rest request to OPLPAGOSAUT...");
                abonoService.abono(endpoint, ctaClient);
            }
        }
    }

    /**
     * Este metodo realiza la actualización de las condiciones del CDT que se quiere renovar, las condiciones que se
     * tienen en cuentan son:
     * <ul>
     *     <li>Capital: Si el valor es <b>positivo</b> se debitara de la cuenta del cliente el monto que desea
     *     aumentar el capital.</li>
     *     <li>Rendimientos: Si el valor es <b>1</b> se reinvertira los rendimientos; Si el valor es <b>2</b> se
     *     retiraran los fondos y no serán tenidos en sumados para el monto que se reinvertira.</li>
     *     <li>Tipo plazo: Meses o días.</li>
     *     <li>Plazo: Cantidad de meses o días.</li>
     *     <li>Base: Calendario, 360 o 365.</li>
     * </ul>
     *
     * @param cdt  Objeto {@link SalRenautdigEntity}.
     * @param host Endpoints de los servicios a consumir.
     * @return Nuevas condiciones del CDT Digital a renovar. Objeto {@link NuevasCondicionesCdtModel}.
     */
    public NuevasCondicionesCdtModel nuevasCondicionesCdt(SalRenautdigEntity cdt, List<ParEndpointDownEntity> host) {
        try {
            log.info("SE INICIA LA VALIDACIÓN DE NUEVAS CONDICIONES DEL CDT DIGITAL A RENOVAR ...");
            HisCtrCdtsEntity hisCtrCdtsEntity = repositoryHisCtrCdts.findById(new HisCtrCdtsPk(cdt.getNumCdt(),
                            cdt.getNumTit(), 1L))
                    .orElseThrow(() -> new ControlCdtsException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Inconveniente"));

            if (hisCtrCdtsEntity.getHisCondicionCdtsEntity().isEmpty()) {
                log.info("EL CDT DIGITAL NO CONTIENE CONDICIONES ...");
                return repositoryCDTSLarge.findByNumCdt(cdt.getNumCdt().toString()).stream()
                        .findFirst()
                        .map(cdtActual -> mapperNuevasCondicionesCdt.condicionesActuales(cdt.getCapPg(),
                                cdt.getIntNeto(), cdtActual))
                        .orElseThrow(() -> new Exception("Error"));
            }

            if (!validacionMontoMinimo(hisCtrCdtsEntity, 0)) {
                log.warn("Se termina metodo nuevas condiciones");
                return new NuevasCondicionesCdtModel();
            }

            ParEndpointDownEntity endpointPayment = host.stream()
                    .filter(endpoint -> endpoint.getId() == 14).findFirst()
                    .orElse(new ParEndpointDownEntity(14L, "http://localhost:8084/CDTSDesmaterializado/v1/cancelacion/pagosaut"));

            BigDecimal capitalDebitado = capitalService.capital(cdt, endpointPayment.getRuta());
            BigDecimal capital = cdt.getCapPg().add(capitalDebitado);
            flexibleSubscription(cdt, hisCtrCdtsEntity, endpointPayment.getRuta());

            Optional<HisCondicionCdtsEntity> hisCondicionCdtsEntity = hisCtrCdtsEntity.getHisCondicionCdtsEntity()
                    .stream()
                    .findFirst();

            return hisCondicionCdtsEntity
                    .map(condiciones -> mapperNuevasCondicionesCdt.nuevasCondiciones(capital,
                            condiciones.getRendimientos() == 2 ? new BigDecimal("0") : cdt.getIntNeto(),
                            condiciones))
                    .orElseThrow(() -> new Exception("Error"));
        } catch (Exception e) {
            log.error("Se ha generado un error: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lastimosamente fallo: " + e.getMessage());
        }
    }


}
