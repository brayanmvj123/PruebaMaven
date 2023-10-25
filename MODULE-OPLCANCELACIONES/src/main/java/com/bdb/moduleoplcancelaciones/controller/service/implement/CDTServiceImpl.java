package com.bdb.moduleoplcancelaciones.controller.service.implement;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.CDTService;
import com.bdb.moduleoplcancelaciones.controller.service.interfaces.PagosAutomaticosService;
import com.bdb.moduleoplcancelaciones.controller.service.interfaces.TransaccionesService;
import com.bdb.moduleoplcancelaciones.mapper.Mapper;
import com.bdb.opaloshare.controller.service.interfaces.CDTVencidoDTO;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisRenovacion;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCDTContingencia;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCancelCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestConfirmacionCancelacion;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestQueryCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT.ResponseCdtProxVen;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT.ResponseContingenciaCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.TitularCtaInvDTO;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.RepositoryHisRenovacion;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.opaloshare.persistence.repository.RepositoryTransacciones;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Service
@CommonsLog
public class CDTServiceImpl implements CDTService {

    @Autowired
    Mapper mapper;

    @Autowired
    TransaccionesService transaccionesService;

    @Autowired
    TramaTraductorCadiServiceImpl tramaTraductorCadiService;

    @Autowired
    PagosAutomaticosService pagosAutomaticosService;

    @Autowired
    SharedService sharedService;

    @Autowired
    RepositorySalPg repositorySalPg;

    @Autowired
    RepositoryHisRenovacion repositoryHisRenovacion;

    @Autowired
    RepositoryTransacciones repositoryTransacciones;

    @Override
    public ResponseCdtProxVen cdtProximoAVencer(RequestQueryCDT requestCDT) {

        CDTVencidoDTO cdtVencido = repositorySalPg.cdtsInfo(Long.valueOf(requestCDT.getNumTit()), requestCDT.getNumCdt(),
                requestCDT.getCodIsin(), requestCDT.getCtaInv());
        List<TitularCtaInvDTO> titularesDTO = repositorySalPg.titulares(cdtVencido.getCtaInv());
        return mapper.requestQueryCDTToCDTVencidoDTO(cdtVencido, titularesDTO);
    }


    @Override
    public ResponseEntity<RequestResult<?>> cancelarCdt(RequestCancelCDT requestCancelCDT, HttpServletRequest http) throws Exception {

        ResponseEntity<RequestResult<?>> responseTransacciones = transaccionesService.registerTranp(requestCancelCDT, http);
        if (responseTransacciones.getStatusCode().equals(HttpStatus.BAD_REQUEST)
                || responseTransacciones.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return responseTransacciones;
        }

        if (requestCancelCDT.getInfoTrans().getCapital().stream()
                .anyMatch(pagoCapital -> pagoCapital.getTipPago().equals(7))) {

            Long numTitularPrincipal = repositorySalPg.titularPrincipalByNumcdt(Long.valueOf(requestCancelCDT.getNumCdt()));
            CDTVencidoDTO cdt = repositorySalPg.cdtsInfo(numTitularPrincipal, Long.valueOf(requestCancelCDT.getNumCdt()), null, null);
            RequestQueryCDT requestQueryCDT = new RequestQueryCDT("1", numTitularPrincipal.toString(),
                    cdt.getNumCdt(), cdt.getOficina(), cdt.getCodIsin(), cdt.getCtaInv().toString(), requestCancelCDT.getCanal());

            ResponseCdtProxVen data = cdtProximoAVencer(requestQueryCDT);
            log.info("Consulta de cdts proximos a vencer en el canal " + requestQueryCDT.getCanal() + " realizada exitosamente");
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK, data));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK, "Proceso exitoso"));
        }
    }

    @Override
    public ResponseEntity<RequestResult<?>> renovarCDT(RequestConfirmacionCancelacion requestConfirmacionCancelacion, HttpServletRequest http) {

        HisRenovacion renovacion = repositoryHisRenovacion.findByOplCdtxctainvTblNumCdt(Long.valueOf(requestConfirmacionCancelacion.getCdt().getAnteriorNumCdt()));
        if (renovacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND,
                    "El cdt '" + requestConfirmacionCancelacion.getCdt().getAnteriorNumCdt() + "' no esta en proceso de renovación, verifique los datos"));
        } else if (renovacion.getOplEstadosTblTipEstado() == 1) {
            repositoryHisRenovacion.updateStateCdt(Long.valueOf(requestConfirmacionCancelacion.getCdt()
                    .getNuevoNumCdt()), Long.valueOf(requestConfirmacionCancelacion.getCdt().getAnteriorNumCdt()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND,
                    "El cdt '" + requestConfirmacionCancelacion.getCdt().getAnteriorNumCdt() + "' ya fue renovado"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK,
                "Se confirma la renovación del CDT '" + requestConfirmacionCancelacion.getCdt().getAnteriorNumCdt() +
                        "' y su nuevo número es '" + requestConfirmacionCancelacion.getCdt().getNuevoNumCdt() + "'"));
    }

    @Override
    public ResponseEntity<RequestResult<?>> contingencia(RequestCDTContingencia requestCDTContingencia, HttpServletRequest http) {

        Long numTitularPrincipal = Long.valueOf(requestCDTContingencia.getParametros().getId());
        HisRenovacion cdtRenovacion = repositoryHisRenovacion.findBynumTitular(numTitularPrincipal);

        if (cdtRenovacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND,
                    "La persona con id '" + numTitularPrincipal + "' no tiene CDTs pendientes por renovar"));
        }

        CDTVencidoDTO cdt = repositorySalPg.cdtsInfo(numTitularPrincipal, cdtRenovacion.getOplCdtxctainvTblNumCdt(), null, null);

        RequestQueryCDT requestQueryCDT = new RequestQueryCDT(cdt.getTipId(), numTitularPrincipal.toString(),
                cdtRenovacion.getOplCdtxctainvTblNumCdt(), cdt.getOficina(), cdtRenovacion.getOplCdtxctainvTblOplCodIsin(),
                cdtRenovacion.getOplCdtxctainvTblOplCtainvTblNumCta().toString(), requestCDTContingencia.getCanal());

        ResponseContingenciaCDT data = mapper.responseContingenciaCDTToResponseCdtProxVen(cdtProximoAVencer(requestQueryCDT));
        data.setValorRenovacion(cdtRenovacion.getValor().setScale(4));

        List<Integer> gmfCapitalList = repositoryTransacciones.findGmfCapital(cdtRenovacion.getOplCdtxctainvTblNumCdt(),
                cdtRenovacion.getOplCdtxctainvTblOplCodIsin(), cdtRenovacion.getOplCdtxctainvTblOplCtainvTblNumCta());

        if (gmfCapitalList.stream().anyMatch(gmfCapital -> gmfCapital == 1)) {
            data.setGmfCapital(gmfCalculate(data.getCapPg()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK, data));
    }

    private BigDecimal gmfCalculate(BigDecimal valor) {
        return valor.multiply((new BigDecimal(4)).divide(new BigDecimal(1000)).round(new MathContext(0, RoundingMode.UP))).setScale(4, RoundingMode.HALF_UP);
    }
}
