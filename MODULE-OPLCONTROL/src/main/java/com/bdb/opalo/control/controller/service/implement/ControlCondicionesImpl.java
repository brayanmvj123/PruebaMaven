package com.bdb.opalo.control.controller.service.implement;

import com.bdb.opalo.control.controller.service.interfaces.ControlCondicionesService;
import com.bdb.opalo.control.controller.service.interfaces.SimuladorRendimientoService;
import com.bdb.opalo.control.model.mapper.ControlNovedadMapper;
import com.bdb.opalo.control.persistence.Constants.ConstantsControlCdt;
import com.bdb.opalo.control.persistence.JSONSchema.ResultJSONCuotas;
import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.exception.ControlCdtsException;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.OplMaedcvTmpDownTblRepository;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCtrCdts;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class ControlCondicionesImpl implements ControlCondicionesService {

    /**
     * Repository para manejar el control de la renavocacion marcacion del CDT
     */
    private final RepositoryHisCtrCdts repositoryHisCtrCdts;

    /**
     * Repository encargado de buscar si el CDT aplica para renovacion
     */
    private final RepositoryCDTSLarge repositoryCDTSLarge;

    /**
     * Repository encargado de buscar alguna variable de Entorno
     */
    private final RepositoryTipVarentorno repositoryTipVarentorno;

    /**
     * Repository encargado de buscar el CDT en la tabla TMP mae
     */
    private final OplMaedcvTmpDownTblRepository repositoryMaeCdtsTmpDownTbl;

    private final ControlNovedadMapper controlNovedadMapper;

    private final SimuladorRendimientoService simuladorRendimientoService;

    /**
     * Clase constants
     */
    ConstantsControlCdt constantsControlCdt = new ConstantsControlCdt();

    public ControlCondicionesImpl(RepositoryHisCtrCdts repositoryHisCtrCdts,
                                  RepositoryCDTSLarge repositoryCDTSLarge,
                                  RepositoryTipVarentorno repositoryTipVarentorno,
                                  OplMaedcvTmpDownTblRepository repositoryMaeCdtsTmpDownTbl,
                                  ControlNovedadMapper controlNovedadMapper, SimuladorRendimientoService simuladorRendimientoService) {
        this.repositoryHisCtrCdts = repositoryHisCtrCdts;
        this.repositoryCDTSLarge = repositoryCDTSLarge;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
        this.repositoryMaeCdtsTmpDownTbl = repositoryMaeCdtsTmpDownTbl;
        this.controlNovedadMapper = controlNovedadMapper;
        this.simuladorRendimientoService = simuladorRendimientoService;
    }

    @Override
    public boolean controlCondicinoesCdt(ControlCdtDto controlCdtDto) throws ControlCdtsException {
        log.info("INGRESO AL CONTROL DE NOVEDAD DE RENOVACIÓN VERSION 2.");
        Optional<HisCDTSLargeEntity> getCdt = repositoryCDTSLarge.findById(controlCdtDto.getParametros().getNumCdt().toString());
        if (getCdt.isPresent()) {
            if (getCdt.get().getOplEstadosTblTipEstado() == 3) {
                orquestacionValidacionesControlCdt(controlCdtDto);
                novedadCtrCdt(controlCdtDto, getCdt.get());
                return true;
            } else {
                log.info("Cdt no aplica para renovacion automatica, debido a que no esta constituido");
                throw new ControlCdtsException(422, "Cdt no aplica para renovacion automatica, debido a que no esta constituido", controlCdtDto);
            }
        } else {
            throw new ControlCdtsException(404, "Cdt No existe", controlCdtDto);
        }
    }

    /**
     * Metodo encargado de orquestar las validaciones del CDT si aplica o no para renovacion automatica
     * Valida si el Cdt esta en estado 3 es decir Constituido
     * Valida si cumple la fecha de proximo pago
     * Valida si el Cdt esta registrado en la tabla Historica de CDTS digitales
     *
     * @param controlCdtDto parametros recibios en el request de la peticion
     * @throws ControlCdtsException exception de negocio
     */
    private void orquestacionValidacionesControlCdt(ControlCdtDto controlCdtDto) throws ControlCdtsException {
        log.info("SE VALIDA SI ES POSIBLE RENOVAR EL CDT DIGITAL DE ACUERDO A LA COMPARACIÓN ENTRE LA FECHA DEL PROXIMO PAGO Y LA FECHA ACTUAL.");
        //Consulta los dias habiles para renovar el CDT en la tabla de varEntorno
        Integer diasHabilesRenovacion = Integer.parseInt(repositoryTipVarentorno
                .findByDescVariable("DIAS_RENOVACION").getValVariable());

        //Consulta el CDT y sus datos en la tabla TMP_MAE
        List<MaeDCVTempDownEntity> maeCdt = repositoryMaeCdtsTmpDownTbl.findByNumCdt(controlCdtDto.getParametros().getNumCdt().toString(),
                controlCdtDto.getParametros().getNumTit());

        //Valida que la fecha de proximo pago no este en los dias habiles(Este valor esta en la varEntorno)
        if (!maeCdt.isEmpty()) {
            DateTimeFormatter dateFechaProxPagFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateFechaProxPag = LocalDate.parse(maeCdt.get(0).getFechaProxPg(), dateFechaProxPagFormat);
            if (esDisponible(dateFechaProxPag, diasHabilesRenovacion) == 2) {
                throw new ControlCdtsException(422,
                        "De acuerdo a la fecha de proximo pago del CDT no se puede renovar", controlCdtDto);
            }
        } else {
            throw new ControlCdtsException(404,
                    "Cdt no aplica para renovación automatica", controlCdtDto);
        }

    }

    /**
     * El metodo valida si el CDT Digital se encuentra disponible para renovación.
     *
     * @param fechaProxPg           Es tipo LocalDate.
     * @param diasHabilesRenovacion Busca en la tabla {@link VarentornoDownEntity} el minimo de dias
     *                              para realizar la validación.
     * @return 1 (UNO) DISPONIBLE para la renovación ó 2 (DOS) NO DISPONIBLE para la renovación
     */
    public Integer esDisponible(LocalDate fechaProxPg, Integer diasHabilesRenovacion) {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaProxPg) > diasHabilesRenovacion
                ? constantsControlCdt.disponible : constantsControlCdt.noDisponible;
    }

    /**
     * Metodo encargado de consultar en la base de datos por numero de CDT y retornar los registros correspondientes
     * a el y guarda o modifica para marcarlos con el codigo de control recibido en el request de este servicio
     *
     * @param controlCdtDto objeto de entrada del servicio
     */
    private void novedadCtrCdt(ControlCdtDto controlCdtDto, HisCDTSLargeEntity hisCDTSLargeEntity) throws ControlCdtsException {
        log.info("SE VALIDA SI LA NOVEDAD PARA EL CDT " + controlCdtDto.getParametros().getNumCdt() + " HA SIDO CREADA " +
                "O ES NECESARIO UNA ACTUALIZACIÓN DE LA NOVEDAD Y CONDICIONES.");
        List<HisCtrCdtsEntity> registroCdtControl = repositoryHisCtrCdts.findByNumCdtAndParControlesEntity(controlCdtDto.getParametros().getNumCdt(),
                new ParControlesEntity(controlCdtDto.getParametros().getCodMarcacion()));
        if (!registroCdtControl.isEmpty()) {
            for (HisCtrCdtsEntity dataCtrCdt : registroCdtControl) {
                if (dataCtrCdt.getFechaModificacion().toLocalDate().isEqual(LocalDate.now())) {
                    throw new ControlCdtsException(409,
                            "El servicio de controlCDTs solo se puede consumir una vez al día", controlCdtDto);
                }
                validacionCapitaleIntereses(controlCdtDto, hisCDTSLargeEntity, dataCtrCdt.getFechaCreacion());
            }
        } else
            validacionCapitaleIntereses(controlCdtDto, hisCDTSLargeEntity, LocalDateTime.now());
    }

    private void validacionCapitaleIntereses(ControlCdtDto controlCdtDto, HisCDTSLargeEntity hisCDTSLargeEntity, LocalDateTime dataCtrCdt) throws ControlCdtsException {
        BigDecimal valorMin = new BigDecimal(repositoryTipVarentorno
                .findByDescVariable("VALOR_MIN_APERTURA")
                .getValVariable());
        log.info("CONSULTO EL VALOR MINIMO APERTURA de la tabla PAR_VARENTORNO: " + valorMin);
        BigDecimal capital = BigDecimal.valueOf(controlCdtDto.getCondiciones().getCapital());
        BigDecimal valor = hisCDTSLargeEntity.getValor();
        log.info("Valor consultado: " + valor + "; Y Capital Digitado " + capital + "\n \n");
        log.info("Esta es la suma " + valor.add(capital));

        if (capital.compareTo(new BigDecimal("0")) < 0) {
            validacionRetiroMayorCapitalActual(controlCdtDto, capital, valor);
        }

        if (valor.add(capital).compareTo(valorMin) >= 0) {
            log.info("SE VERIFICA QUE VALOR DEL CAPITAL SEA MAYOR O IGUAL AL ESPERADO");
            almacenarNovedad(controlCdtDto, dataCtrCdt);
        } else {
            //Consulta el CDT y sus datos en la tabla TMP_MAE
            validacionInteresesNetos(controlCdtDto, valorMin, capital, valor);
            log.info("SE VERIFICA QUE LOS INTERESES NETO SEA MAYOR O IGUAL AL ESPERADO Y QUE EL CLIENTE DESEE " +
                    "REINVERTIR LOS RENDIMIENTOS.");
            if (controlCdtDto.getCondiciones().getRendimientos() == 1)
                almacenarNovedad(controlCdtDto, dataCtrCdt);
        }
    }

    private static void validacionRetiroMayorCapitalActual(ControlCdtDto controlCdtDto, BigDecimal capital, BigDecimal valor) throws ControlCdtsException {
        if (BigDecimal.valueOf(Math.abs(capital.longValue())).compareTo(valor) > 0) {
            throw new ControlCdtsException(409,
                    "El valor que esta intentando retirar del capital es mayor al valor del CDT Digital actual.",
                    controlCdtDto);
        }
    }

    private void validacionInteresesNetos(ControlCdtDto controlCdtDto, BigDecimal valorMin, BigDecimal capital, BigDecimal valor) throws ControlCdtsException {
        List<MaeDCVTempDownEntity> maeCdt = repositoryMaeCdtsTmpDownTbl.findByNumCdt(controlCdtDto.getParametros().getNumCdt().toString(),
                controlCdtDto.getParametros().getNumTit());
        log.info("Valor de periocidad: " + maeCdt.get(0).getOplTipperiodTblTipPeriodicidad());
        //webClient
        RequestResult<ResultJSONCuotas> resultadoSimulador = simuladorRendimientoService.simulador(controlCdtDto.getCondiciones().getBase(), maeCdt.get(0).getVlrCDT(), maeCdt.get(0).getOplTipperiodTblTipPeriodicidad(), 20,
                maeCdt.get(0).getTasEfe(), maeCdt.get(0).getPlazo());
        log.info("Interes Neto: " + resultadoSimulador.getResult().getInteresNeto());

        BigDecimal nuevoCapital = valor.add(capital);
        if (controlCdtDto.getCondiciones().getRendimientos() == 1)
            nuevoCapital = BigDecimal.valueOf(Long.parseLong(String.valueOf(resultadoSimulador.getResult().getInteresNeto()))).add(nuevoCapital);

        if (nuevoCapital.compareTo(valorMin) < 0) {
            log.info("Los interses neto: " + nuevoCapital + "; y son menores al valor mínimo: " + valorMin);
            throw new ControlCdtsException(409,
                    "El valor de los interese neto es menor a lo esperado ", controlCdtDto);
        }
    }

    /**
     * Metodo encargado de mapear el objeto de entrada con el objeto entity para guardar/actualizar en la
     * base de datos la marcacion de renovacion CDT con la nuevas condiciones.
     *
     * @param controlCdtDto objeto de entrada del servicio
     */
    private void almacenarNovedad(ControlCdtDto controlCdtDto, LocalDateTime fechaCreacion) {
        log.info("Se inicia la creación / actualización de la novedad de renovación.");
        repositoryHisCtrCdts.save(controlNovedadMapper.toHisCtrCdtsEntity(controlCdtDto, fechaCreacion));
    }

}
