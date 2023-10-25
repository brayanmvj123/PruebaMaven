package com.bdb.moduleoplcovtdsa.controller.service.implement;

import com.bdb.moduleoplcovtdsa.controller.service.interfaces.CDTsBVBMService;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.JSONConsultaBVBM;
import com.bdb.moduleoplcovtdsa.persistence.exception.ServiceBVBMException;
import com.bdb.moduleoplcovtdsa.persistence.model.MarcacionesCDT;
import com.bdb.moduleoplcovtdsa.persistence.model.ResponseBVBM;
import com.bdb.moduleoplcovtdsa.persistence.model.ResponseHisCdts;
import com.bdb.moduleoplcovtdsa.persistence.model.TipoCDT;
import com.bdb.moduleoplcovtdsa.persistence.model.constants.ConstantsBVBM;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@CommonsLog
public class CDTsBVBMServiceImpl implements CDTsBVBMService {

    @Autowired
    private RepositoryCDTsDesmaterializado repoCDTsBVBM;

    @Autowired
    private RepositoryCDTSLarge repoHisCDT;

    @Autowired
    private RepositoryHisCtrCdts repositoryHisCtrCdts;

    @Autowired
    private RepositoryTipVarentorno repositoryTipVarentorno;

    @Autowired
    private RepositoryCdtxCliente repositoryCdtxCliente;

    ConstantsBVBM constantsBVBM = new ConstantsBVBM();

    @Override
    public ResponseBVBM consultaCDTsBVBM(JSONConsultaBVBM parametersRequest) {

        String idTit = parametersRequest.getParametros().getIdentificacion();
        String tipId = String.valueOf(parametersRequest.getParametros().getTipoDocumento());

        try {
            if (!idTit.isEmpty() && !tipId.isEmpty()) {
                List<MaeDCVTempDownModel> queryResult = repoCDTsBVBM.consultaCDTsBVBM(ajustarIdentificacion(idTit), tipId);
                log.info("VALIDANDO: "+ajustarIdentificacion(idTit));
                log.info("CANTIDAD DE CDTS TEMP: " + queryResult.size());

                if (queryResult.isEmpty()) {
                    log.info("ENTRO A VERIFICAR EN LA TABLA HIS CDT");
                    List<HisCDTSLargeModel> queryHisCDTResult = repoHisCDT.consultaHisCDTsBVBM(idTit, Integer.parseInt(tipId));
                    if (!queryHisCDTResult.isEmpty()) {
                        return new ResponseBVBM(constantsBVBM.APLICACION, LocalDateTime.now(),
                                parametersRequest, queryResult.size(), queryHisCDTResult.size(),
                                constantsBVBM.NOMBRE_SERVICE, HttpStatus.OK.toString(),
                                infoRenovacion(queryHisCDTResult, idTit));
                    } else {
                        return new ResponseBVBM(constantsBVBM.APLICACION, LocalDateTime.now(),
                                parametersRequest, queryResult.size(), queryHisCDTResult.size(),
                                constantsBVBM.NOMBRE_SERVICE, HttpStatus.OK.toString(),
                                null);
                    }
                } else {
                    List<HisCDTSLargeModel> queryHisCDTResult = repoHisCDT.consultaHisCDTsBVBM(idTit, Integer.parseInt(tipId));
                    log.info("ANTES DEL IF TMP - CDTS: " + queryHisCDTResult.size());
                    List<ResponseHisCdts> responseHisCdtsList = Stream.concat(infoRenovacionTmp(queryResult, idTit).stream(),
                            infoRenovacion(queryHisCDTResult, idTit).stream())
                            .collect(Collectors.toList());
                    return new ResponseBVBM(constantsBVBM.APLICACION, LocalDateTime.now(),
                            parametersRequest, queryResult.size(), queryHisCDTResult.size(),
                            constantsBVBM.NOMBRE_SERVICE, HttpStatus.OK.toString(), responseHisCdtsList);
                }
            } else {
                throw new ServiceBVBMException(constantsBVBM.APLICACION, LocalDateTime.now(),
                        constantsBVBM.NOMBRE_SERVICE, constantsBVBM.ERROR_TIPO_DOS, HttpStatus.BAD_GATEWAY);
            }

        } catch (Exception e) {
            throw new ServiceBVBMException(constantsBVBM.APLICACION, LocalDateTime.now(),
                    constantsBVBM.NOMBRE_SERVICE, constantsBVBM.ERROR_TIPO_UNO + e.getMessage(),
                    HttpStatus.BAD_GATEWAY);
        }
    }

    /**
     * @param queryHisCDTResult {@link HisCDTSLargeModel} Resultado de la consulta con la cantidad
     *                          registros pertenecientes al cliente consultado.
     * @param idTit             Número o Identificacion del cliente.
     * @return List<ResponseHisCdts> {@link ResponseHisCdts} Retorna de forma organizada los titulos.
     */
    public List<ResponseHisCdts> infoRenovacion(List<HisCDTSLargeModel> queryHisCDTResult, String idTit) {
        return queryHisCDTResult.stream().map(item -> new ResponseHisCdts(item.getNumCDT(), item.getVlrCDT(),
                item.getFechaVen(), item.getFechaProPago(), item.getTipTasa(), item.getDesTasa(),
                item.getFechaEmi(), item.getTasEfe(), condicionesCDT(item.getNumCDT(), idTit,
                item.getFechaProPago()))).collect(Collectors.toList());
    }

    public List<ResponseHisCdts> infoRenovacionTmp(List<MaeDCVTempDownModel> queryHisCDTResult, String idTit) {
        return queryHisCDTResult
                .stream()
                .map(item -> new ResponseHisCdts(item.getNumCDT(), item.getVlrCDT(),
                        LocalDate.parse(item.getFechaVen()), LocalDate.parse(item.getFechaProPago()), Integer.parseInt(item.getTipTasa()),
                        item.getDesTasa(), LocalDate.parse(item.getFechaEmi()), item.getTasEfe(), condicionesCDT(item.getNumCDT(),
                        idTit, LocalDate.parse(item.getFechaProPago()))))
                .collect(Collectors.toList());
    }

    /**
     * @param numCDT      El número de CDT asignado al titulo.
     * @param idTit       La identificacion o número de titular del CDT.
     * @param fechaProxPg Fecha del proximo pago, se utiliza para verificar si el cliente puede
     *                    activar/desactiviar la renovación automatica.
     * @return Genera un objeto con las condiciones o controles del CDT actuales.
     */
    public TipoCDT condicionesCDT(String numCDT, String idTit, LocalDate fechaProxPg) {
        List<HisCtrCdtsEntity> hisCtrCdtsEntity = repositoryHisCtrCdts.findByNumCdtAndNumTit(Long.parseLong(numCDT), idTit);
        boolean hisCDTSLargeModel = repositoryCdtxCliente.existsByOplCdtsTblNumCdtAndOplClientesTblNumTit(numCDT, idTit);
        boolean maeDCVTempDownEntity = repoCDTsBVBM.existsByNumCDT(numCDT);
        Integer diasHabilesRenovacion = Integer.parseInt(repositoryTipVarentorno
                .findByDescVariable("DIAS_RENOVACION").getValVariable());
        TipoCDT tipoCDT = new TipoCDT();
        if (!hisCtrCdtsEntity.isEmpty() && hisCDTSLargeModel) {
            tipoCDT.setCodigo(esDigital(hisCDTSLargeModel));
            tipoCDT.setDescripcion(descripcionCdt(hisCDTSLargeModel));
            tipoCDT.setObservaciones(constantsBVBM.MENSAJE_CONTROLES);
            tipoCDT.setMarcacionesCDT(hisCtrCdtsEntity
                    .stream()
                    .map(item -> new MarcacionesCDT(item.getParControlesEntity().getTipControl(),
                            item.getParControlesEntity().getDescControl(), item.getNovedadV(),
                            esDisponible(fechaProxPg, diasHabilesRenovacion, item.getFechaModificacion())))
                    .collect(Collectors.toList()));
        } else {
            tipoCDT.setCodigo(esDigital(hisCDTSLargeModel));
            tipoCDT.setDescripcion(descripcionCdt(hisCDTSLargeModel));
            tipoCDT.setObservaciones(constantsBVBM.MENSAJE_NO_CONTROLES);
            tipoCDT.setMarcacionesCDT(controlNuevosCDTs(fechaProxPg, diasHabilesRenovacion,
                    hisCDTSLargeModel, maeDCVTempDownEntity,
                    LocalDateTime.now().minusDays(2)));
        }
        return tipoCDT;
    }

    /**
     * Información del servicio.
     *
     * @param app               Aplicación.
     * @param parametersRequest Request enviado.
     * @param sizeTmp           tamaño de los CDTS obtenidos de la tabla {@link MaeDCVTempDownEntity}.
     * @param sizeHis           tamaño de los CDTS obtenidos de la tabla {@link HisCDTSLargeEntity}.
     * @param nameService       nombre del servicio.
     * @param status            resultado de la consulta.
     * @return Información del servicio.
     */

    /**
     * Permite saber si el CDT es 1 (Digital) - 2 (Desmaterializado).
     *
     * @param valor
     * @return Si el CDT se encuentra {@link HisCDTSLargeEntity} es 1.
     */
    public String esDigital(boolean valor) {
        return valor ? constantsBVBM.CODIGO_CDTDIGITAL : constantsBVBM.CODIGO_CDTDESMATERIALIZADO;
    }

    /**
     * Permite saber la descripcion del tipo de CDT.
     *
     * @param valor
     * @return Si el CDT se encuentra {@link HisCDTSLargeEntity} es DIGITAL
     */
    public String descripcionCdt(boolean valor) {
        return valor ? constantsBVBM.CDTS_DIGITAL : constantsBVBM.CDTS_DESMATERIALIZADO;
    }

    public List<MarcacionesCDT> controlNuevosCDTs(LocalDate fechaProxPg, Integer diasHabilesRenovacion,
                                                  boolean presenteHisCDT, boolean presentTmpCdt,
                                                  LocalDateTime fechaModificacion) {
        List<MarcacionesCDT> marcacionesCDTList = new ArrayList<>();
        if (esDisponible(fechaProxPg, diasHabilesRenovacion, fechaModificacion) == 1 &&
                presenteHisCDT && presentTmpCdt) {
            MarcacionesCDT marcacionesCDT = new MarcacionesCDT(1L,
                    "RENOVACION", 2, 1);
            marcacionesCDTList.add(marcacionesCDT);
        }
        return marcacionesCDTList;
    }

    /**
     * El metodo valida si el CDT Digital se encuentra disponible para renovación.
     *
     * @param fechaProxPg           Es tipo LocalDate.
     * @param diasHabilesRenovacion Busca en la tabla {@link VarentornoDownEntity} el minimo de dias
     *                              para realizar la validación.
     * @return 1 (UNO) DISPONIBLE para la renovación ó 2 (DOS) NO DISPONIBLE para la renovación
     */
    public Integer esDisponible(LocalDate fechaProxPg, Integer diasHabilesRenovacion,
                                LocalDateTime fechaModificacion) {
        BiPredicate<LocalDate, Integer> validate = (x, y) -> ChronoUnit.DAYS.between(LocalDate.now(), x) > y;
        Predicate<LocalDateTime> fechaRenovacion = x -> ChronoUnit.HOURS.between(x, LocalDateTime.now()) >= 24;
        return validate.test(fechaProxPg, diasHabilesRenovacion) && fechaRenovacion.test(fechaModificacion) ?
                constantsBVBM.disponible : constantsBVBM.noDisponible;
    }

    private String ajustarIdentificacion(String idTit){
        return String.format("%012d", Long.parseLong(idTit));
    }

}
