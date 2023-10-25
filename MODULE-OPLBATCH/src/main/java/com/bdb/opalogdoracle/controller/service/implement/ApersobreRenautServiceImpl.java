package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.ApersobreRenautService;
import com.bdb.opalogdoracle.persistence.Response.ResponseService;
import com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura.*;
import com.bdb.opalogdoracle.persistence.jsonschema.servicedias.JSONCalculoDiasCDT;
import com.bdb.opalogdoracle.persistence.jsonschema.servicefechaven.JSONCalculoFechaVencimiento;
import com.bdb.opalogdoracle.persistence.jsonschema.servicesimuintereses.JSONTasaEfectivaNominal;
import com.bdb.opalogdoracle.persistence.jsonschema.servicesimuintereses.ParametrosJSONTasaEfectivaNominal;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.userdata.JSONIdentificacion;
import com.bdb.opaloshare.persistence.model.userdata.JSONTelefono;
import com.bdb.opaloshare.persistence.repository.*;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;

@Service
@CommonsLog
public class ApersobreRenautServiceImpl implements ApersobreRenautService {

    @Autowired
    private RepositorySalRenautdig repositorySalRenautdig;

    @Autowired
    private RepositoryCDTSLarge repositoryCDTSLarge;

    @Autowired
    private RepositoryTmpTasasCdtMds repositoryTmpTasasCdtMds;

    @Autowired
    private RepositoryDatosCliente repositoryDatosCliente;

    @Autowired
    private RepositoryTipPaisPar repositoryTipPaisPar;

    @Autowired
    private RepositoryTipCiud repoCiud;

    @Autowired
    private RepositoryTipDepar repoDepar;

    @Autowired
    private RepositoryTipDane repoDane;

    @Autowired
    private RepositoryTipCIUU repoCiiu;

    @Autowired
    private RepositoryTipSegmento repoSegmento;

    @Autowired
    private RepositoryTipDepositante repoDepo;

    @Autowired
    private RepositoryHisCtrCdts repositoryHisCtrCdts;

    @Autowired
    private RepositoryHisRenovaCdt repositoryHisRenovaCdt;

    @Autowired
    private RepositoryTipId repositoryTipId;

    @Autowired
    private RepositoryTipPeriodicidad repositoryTipPeriodicidad;

    @Autowired
    RepositorySalPg repositorySalPg;

    @Autowired
    RepositoryTipVarentorno repositoryTipVarentorno;

    @Autowired
    RepositoryTasaSeg repositoryTasaSeg;

    @Autowired
    private SharedService sharedService;

    @Autowired
    ResponseService responseService;

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
     * @param http Parametro que contiene la URL desde donde se realiza el consumo del servicio.
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
    public boolean aperIntoRenaut(String http) {
        String host = sharedService.generarUrl(http, "OPLBATCH");
        final boolean[] resultAper = new boolean[1];
        log.info("VALUE HOST: " + host);
        List<SalRenautdigEntity> listRenaut = getCdtsRenaut();
        try {
            if (!listRenaut.isEmpty()) {
                listRenaut.stream()
                        .filter(Objects::nonNull)
                        .filter(cdtRenaut -> cdtRenaut.getEstadoV().equals("P"))
                        .forEach(item -> getInfoHisCdts(item.getNumCdt())
                                .stream()
                                .filter(Objects::nonNull)
                                .forEach(cdt -> {
                                    BigDecimal capital = item.getCapPg().add(item.getIntNeto());
                                    BigDecimal rates = ratesCdt(cdt.getCodProd(), cdt.getOplTipplazoTblTipPlazo().toString(),
                                            cdt.getPlazo(), capital, item.getNumTit());
                                    log.info("TASSA: " + rates);
                                    log.info("INICIO CONSUMO SERVICIO SIMULADOR FECHA DE VENCIMIENTO");
                                    String resultSimuFechVen = simuladorFechaVenCdt(cdt.getOplTipbaseTblTipBase(),
                                            cdt.getPlazo(),
                                            cdt.getOplTipplazoTblTipPlazo(),
                                            host);
                                    log.info("INICIO CONSUMO SERVICIO SIMULADOR DE INTERESES");
                                    log.info("ANTES DE ENTRAR AL SERVICIO DE INTERESES");
                                    log.info(cdt.getOplTipbaseTblTipBase().toString());
                                    log.info(capital.toString());
                                    log.info(cdt.getOplTipperiodTblTipPeriodicidad().toString());
                                    log.info("20");
                                    log.info(rates.toString());
                                    log.info(resultSimuFechVen);
                                    log.info(host);
                                    JSONObject resultSimuInters = simuladorIntereses(cdt.getOplTipbaseTblTipBase().toString(),
                                            capital.toString(),
                                            cdt.getOplTipperiodTblTipPeriodicidad().toString(),
                                            "20",
                                            rates.toString(),
                                            resultSimuFechVen,
                                            host);
                                    log.info("INICIO CONSUMO SERVICIO APERTURA DE CDTS DIGITALES");
                                    resultAper[0] = apertura(Long.parseLong(item.getNumTit()),
                                            homologarTipId(item.getTipId()),
                                            cdt,
                                            resultSimuFechVen,
                                            new BigDecimal(resultSimuInters.getJSONObject("result").get("tasaNominal").toString()),
                                            rates,
                                            capital,
                                            codigoCut(item.getNumCdt(), item.getNumTit(), 1L),
                                            host);
                                }));
                return resultAper[0];
            } else
                return true;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Este metodo permite obtener los CDTs Digitales que se renovarán, esta data se obtiene de la tabla
     * {@link SalRenautdigEntity}.
     *
     * @return La lista de los CDTS Digitales con la respectiva información de cancelación.
     */
    @Override
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
    @Override
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
    public String simuladorFechaVenCdt(Integer base, Integer plazo, Integer tipPlazo, String host) {
        log.info("Se inicia la operación simuladorFechaVenCdt...");
        JSONCalculoFechaVencimiento requestFechaVen = new JSONCalculoFechaVencimiento();
        requestFechaVen.setBase(convertBase(base));
        requestFechaVen.setPlazo(plazo.longValue());
        requestFechaVen.setTipoPlazo(tipPlazo);
        requestFechaVen.setFechaApertura(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONCalculoFechaVencimiento> requestEntity = new HttpEntity<>(
                requestFechaVen, httpHeaders);
        final String url = host + "OPLSIMULADOR/CDTSDesmaterializado/v1/simulador/calculoFechaVencimiento";
        //"http://localhost:8080/CDTSDesmaterializado/v1/simulador/calculoFechaVencimiento";

        log.info("Se inicia el consumo del servicio...");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });
        JSONObject resultado = new JSONObject(response.getBody());
        return resultado.getJSONObject("resultado").get("fechaVencimiento").toString();
    }

    /**
     * Este metodo realiza el consumo del servicio expuesto en el modulo OPLSIMULADOR, el cual calcula los dias entre la
     * fecha de apertura y la fecha de vencimiento.
     *
     * @param base     Base, este campo se toma del valor almacenado en el campo <b><i>OPL_PAR_TIPBASE</i></b>.
     * @param fechaVen Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                 del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param host     Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El resultado del <i>número de dias</i> entre la fecha de apertura y la fecha vencimiento.
     */
    public String calculoServiceDias(Integer base, String fechaVen, String host) {
        log.info("INICIO CONSUMO DE SIMULADOR DE PLAZO EN DIAS");
        JSONCalculoDiasCDT jsonCalculoDiasCDT = new JSONCalculoDiasCDT();
        jsonCalculoDiasCDT.setBase(convertBase(base));
        jsonCalculoDiasCDT.setFechaApertura(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        System.out.println("FORMATO FECHA VEN: " + fechaVen + " - " + LocalDate.parse(fechaVen, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        jsonCalculoDiasCDT.setFechaVencimiento(LocalDate.parse(fechaVen, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONCalculoDiasCDT> requestEntity = new HttpEntity<>(
                jsonCalculoDiasCDT, httpHeaders);
        final String url = host + "OPLSIMULADOR/CDTSDesmaterializado/v1/simulador/calculoDiasCDT";
        //"http://localhost:8080/CDTSDesmaterializado/v1/simulador/calculoDiasCDT";

        log.info("Se inicia el consumo del servicio...");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });
        JSONObject resultado = new JSONObject(response.getBody());
        return resultado.getJSONObject("resultado").get("plazoenDias").toString();
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
     * @param host         Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return Un objeto de tipo JSONObject, los valores importantes a tener en cuenta en el response son los valores de
     * TasaNominal y TasaEfectiva.
     */
    public JSONObject simuladorIntereses(String base, String capital, String periodicidad, String retencion,
                                         String tasaFija, String fechaVen, String host) {
        log.info("ENTRA AL CONSUMO DEL SERVICIO SIMULADOR CUOTA V1.");
        JSONTasaEfectivaNominal requestSimu = new JSONTasaEfectivaNominal();
        requestSimu.setCanal("OPL766");

        ParametrosJSONTasaEfectivaNominal requestParam = new ParametrosJSONTasaEfectivaNominal();
        requestParam.setBase(base);
        requestParam.setCapital(capital);
        requestParam.setDiasPlazo(calculoServiceDias(Integer.parseInt(base), fechaVen, host));
        log.info("despues de los dias");
        requestParam.setPeriodicidad(homologacionPeriodicidad(Integer.parseInt(periodicidad)));
        requestParam.setRetencion(retencion);
        requestParam.setTasaFija(tasaFija);

        requestSimu.setParametros(requestParam);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONTasaEfectivaNominal> requestEntity = new HttpEntity<>(
                requestSimu, httpHeaders);
        final String url = host + "OPLSIMULADOR/TasaEfectivaNominal/v1/simulacionCuota";
        //"http://localhost:8080/TasaEfectivaNominal/v1/simulacionCuota";

        log.info("Se inicia el consumo del servicio...");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });
        return new JSONObject(response.getBody());
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
     * @param numTit       Número de identificación del cliente.
     * @param tipId        Tipo de identificación del cliente.
     * @param cdt          Número de CDT.
     * @param fechaVen     Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                     del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param tasaNominal  Tasa Nominal se obtiene del consumo del <u>servicio</u> de <i>Simulador de Cuota</i>.
     * @param tasaEfectiva Tasa Efectiva, se obtiene de la <u>consulta</u> a las tasas.
     * @param valorCdt     Valor del Cdt o Capital, se obtiene de la tabla <i><u>SalRenautdigEntity</u></i>.
     * @param host         Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El request completo para realizar el consumo del <u><i>servicio de apertura</i></u>.
     */
    public JSONAperCDT requestApertura(Long numTit, Long tipId, HisCDTSLargeEntity cdt, String fechaVen,
                                       BigDecimal tasaNominal, BigDecimal tasaEfectiva, BigDecimal valorCdt,
                                       String codCut, String host) {
        log.info("Se inicia la operación requestApertura...");
        JSONAperCDT jsonAperCDT = new JSONAperCDT();
        jsonAperCDT.setCliente(jsonClientDatos(numTit, tipId));
        jsonAperCDT.setCdt(jsonCondCDT(cdt, fechaVen, tasaNominal, tasaEfectiva, valorCdt, codCut, host));
        jsonAperCDT.setTransaccionesPagoCdt(jsonPagCDTDes(numTit, cdt.getOplOficinaTblNroOficina(), valorCdt, cdt.getNumCdt()));
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
        jsonClientDatos.setCodigoCIUU(repoCiiu.findById(cliente.getOplTipciiuTblCodCiiu()).get().getHomoCrm());
        jsonClientDatos.setCodigoPais(repositoryTipPaisPar.findByCodPais(cliente.getOplTippaisTblCodPais()).getHomoCrm());
        jsonClientDatos.setCodigoDepartamento(repoDepar.findById(cliente.getOplTipdeparTblCodDep()).get().getHomoCrm());
        jsonClientDatos.setCodigoCiudad(repoCiud.findById(cliente.getOplTipciudTblCodCiud()).get().getHomoCrm());
        jsonClientDatos.setCodigoSegmentoComercial(repoSegmento.findById(cliente.getOplTipsegmentoTblCodSegmento()).get().getHomoCrm());
        jsonClientDatos.setRetencion(cliente.getRetencion());
        jsonClientDatos.setTipoTitular(1);
        jsonClientDatos.setCodigoDane(repoDane.findById(cliente.getOplParTipdaneTblCodDane()).get().getHomoCrm());
        listClients.add(jsonClientDatos);
        return listClients;
    }

    /**
     * Este metodo crea el objeto Condiciones de CDT del request de Apertura.
     *
     * @param cdt          Objeto con toda la informacion de la tabla {@link HisCDTSLargeEntity} del CDT a renovar.
     * @param fechaVen     Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                     del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param tasaNominal  Tasa Nominal se obtiene del consumo del <u>servicio</u> de <i>Simulador de Cuota</i>.
     * @param tasaEfectiva Tasa Efectiva, se obtiene de la <u>consulta</u> a las tasas.
     * @param valorCdt     Valor del Cdt o Capital, se obtiene de la tabla <i><u>SalRenautdigEntity</u></i>.
     * @param host         Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return Tipo de objeto de la clase {@link JSONCondCDT} con la informacion del <i><u>nuevo CDT</u></i>.
     */
    public JSONCondCDT jsonCondCDT(HisCDTSLargeEntity cdt, String fechaVen, BigDecimal tasaNominal, BigDecimal tasaEfectiva,
                                   BigDecimal valorCdt, String codCut, String host) {
        log.info("Se inicia la operación jsonCondCDT...");

        JSONCondCDT jsonCondCDT = new JSONCondCDT();
        jsonCondCDT.setFecha(LocalDateTime.now().toString());
        jsonCondCDT.setUsuarioNt(cdt.getUsuario());
        jsonCondCDT.setCanal(cdt.getCanal());
        jsonCondCDT.setCodCut(codCut);
        jsonCondCDT.setCodTransaccion(cdt.getCodTrn());
        jsonCondCDT.setNumCdtDigital(numCdtAsignado(host));
        jsonCondCDT.setCodProd(cdt.getCodProd());
        jsonCondCDT.setMercado(cdt.getMercado());
        jsonCondCDT.setUnidadNegocio(cdt.getUnidNegocio());
        jsonCondCDT.setUnidCEO(cdt.getUnidCeo());
        jsonCondCDT.setFormaPago(cdt.getFormaPago());
        jsonCondCDT.setDepositante(repoDepo.findById(cdt.getOplDepositanteTblTipDepositante()).get().getHomoDcvsa());

        JSONPlazoCDT jsonPlazoCDT = new JSONPlazoCDT();
        jsonPlazoCDT.setCantidadPlazo(cdt.getPlazo());
        jsonPlazoCDT.setTipo(cdt.getOplTipplazoTblTipPlazo().toString());

        jsonCondCDT.setPlazo(jsonPlazoCDT);
        jsonCondCDT.setFechaEmision(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        jsonCondCDT.setFechaVencimiento(LocalDate.parse(fechaVen, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        jsonCondCDT.setBase(cdt.getOplTipbaseTblTipBase().toString());
        jsonCondCDT.setModalidad(cdt.getModalidad());
        jsonCondCDT.setTipoTasa(cdt.getOplTiptasaTblTipTasa()); //SI EL TIPO DE TASAS ES FIJA EL SPREAD ES 0
        jsonCondCDT.setTipoPeriodicidad(cdt.getOplTipperiodTblTipPeriodicidad());
        jsonCondCDT.setSpread(cdt.getSpread()); //DEPENDE DEL TIPO DE TASA , SI NO ERROR
        jsonCondCDT.setSignoSpread(cdt.getSignoSpread());
        jsonCondCDT.setTasaEfectiva(tasaEfectiva);
        jsonCondCDT.setTasaNominal(tasaNominal);
        jsonCondCDT.setMoneda(cdt.getMoneda());
        jsonCondCDT.setUnidadUVR(cdt.getUnidUvr());
        jsonCondCDT.setCantidadUnidad(cdt.getCantUnid());
        jsonCondCDT.setValor(valorCdt);
        jsonCondCDT.setTipoTitularidad(cdt.getTipTitularidad());
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
     * @param host Requiere el host para realizar el consumo de acuerdo al ambiente.
     * @return El nuevo número del CDT Digital.
     */
    public String numCdtAsignado(String host) {
        log.info("Se inicia la operación numCdtAsignado...");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONAsignarNumCDT jsonAsignarNumCDT = new JSONAsignarNumCDT();
        jsonAsignarNumCDT.setCanal("OPL766");
        Parametros parametros = new Parametros();
        parametros.setCodTransaccion("1");
        jsonAsignarNumCDT.setParametros(parametros);

        HttpEntity<JSONAsignarNumCDT> requestEntity = new HttpEntity<>(jsonAsignarNumCDT, httpHeaders);
        final String url = host + "OPAPCDT/CDTSDesmaterializado/v1/asignarNumCDTDigital";
        //"http://localhost:8080/CDTSDesmaterializado/v1/asignarNumCDTDigital";

        log.info("Se inicia el consumo del servicio...");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });
        JSONObject body = new JSONObject(response.getBody());
        return body.get("numCdtDigital").toString();
    }

    /**
     * @param numTit       Número de identificación del cliente.
     * @param tipId        Tipo de identificación del cliente.
     * @param cdt          Objeto de tipo {@link HisCDTSLargeEntity} con toda la informacion de la tabla {@link HisCDTSLargeEntity} del CDT a renovar.
     * @param fechaVen     <p>Fecha de vencimiento, este valor se toma del consumo del servicio <b><i>calculoFechaVencimiento</i></b>
     *                     del modulo <i>MODULE-OPLSIMULADOR</i>.</p>
     * @param tasaNominal  Tasa Nominal se obtiene del consumo del <u>servicio</u> de <i>Simulador de Cuota</i>.
     * @param tasaEfectiva Tasa Efectiva, se obtiene de la <u>consulta</u> a las tasas.
     * @param valorCdt     Valor del Cdt o Capital, se obtiene de la tabla <i><u>SalRenautdigEntity</u></i>.
     * @param codCut       Codigo CUT, esta valor es enviado por el frontend al momento realizar la notificación de renovación, este valor queda
     *                     almacenado en la tabla {@link HisCtrCdtsEntity} en el campo <i><u>COD_CUT</u></i>.
     * @param host         Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El tipo resultado varia segun:
     * <ol>
     *     <li>Si el resultado es <b><i><u>TRUE : La <u>apertura</u> fue correcta.</u></i></b></li>
     *     <li>Si el resultado es <b><i><u>FALSE : La <u>apertura</u> fue incorrecta.</u></i></b></li>
     * </ol>
     */
    public boolean apertura(Long numTit, Long tipId, HisCDTSLargeEntity cdt, String fechaVen, BigDecimal tasaNominal,
                            BigDecimal tasaEfectiva, BigDecimal valorCdt, String codCut, String host) {
        log.info("Se inicia la operación apertura...");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONAperCDT> requestEntity = new HttpEntity<>(
                requestApertura(numTit, tipId, cdt, fechaVen, tasaNominal, tasaEfectiva, valorCdt, codCut, host), httpHeaders);

        final String url = host + "OPAPCDT/CDTSDesmaterializado/v1/aperturaCDTsDes";
        //"http://localhost:8080/CDTSDesmaterializado/v1/aperturaCDTsDes";

        if (!validacionApertura(Long.parseLong(cdt.getNumCdt()))) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<String>() {
                    });

            log.info("STATUS DEL SERVICIO DE APERTURA: " + response.getStatusCode());

            if (response.getStatusCode().is2xxSuccessful()) {
                repositorySalRenautdig.actualizarEstadoRenovacion(Long.parseLong(cdt.getNumCdt()));
                guardarHistorial(Long.parseLong(requestEntity.getBody().getCdt().getNumCdtDigital()),
                        Long.parseLong(cdt.getNumCdt()));

                repositorySalPg.updateStateValue(Long.parseLong(cdt.getNumCdt()), 3);
            }

            return response.getStatusCode().is2xxSuccessful();
        } else return false;
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
     * el cual almacena el estado final de todo el proceso de renovación.
     *
     * @param http   URL donde se consume el servicio.
     * @param estado Los estados (AJUSTAR LA DOCUMENTACION)
     */
    @Override
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

    public boolean validacionApertura(Long numCdt) {
        return repositoryHisRenovaCdt.existsByCdtAnt(numCdt);
    }
}
