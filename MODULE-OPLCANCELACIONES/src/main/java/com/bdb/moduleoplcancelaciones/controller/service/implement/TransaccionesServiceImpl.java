package com.bdb.moduleoplcancelaciones.controller.service.implement;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.CutService;
import com.bdb.moduleoplcancelaciones.controller.service.interfaces.InfoCDTService;
import com.bdb.moduleoplcancelaciones.controller.service.interfaces.PagosAutomaticosService;
import com.bdb.moduleoplcancelaciones.controller.service.interfaces.TransaccionesService;
import com.bdb.opaloshare.controller.service.interfaces.CDTVencidoDTO;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisCdtxCtainvEntity;
import com.bdb.opaloshare.persistence.entity.HisInfoCDTEntity;
import com.bdb.opaloshare.persistence.entity.HisRenovacion;
import com.bdb.opaloshare.persistence.entity.HisTransaccionesEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.Capital;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.InfoCliente;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.Intereses;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCancelCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestTraductor.RequestTraductor;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCdtxCtainv;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class TransaccionesServiceImpl implements TransaccionesService {

    @Autowired
    private RepositoryTransacciones repositoryTransacciones;

    @Autowired
    private RepositorySalPg repositorySalPg;

    @Autowired
    private RepositoryHisRenovacion repositoryHisRenovacion;

    @Autowired
    private RepositoryHisCdtxCtainv repositoryHisCdtxCtainv;

    @Autowired
    private PagosAutomaticosService pagosAutomaticosService;

    @Autowired
    private InfoCDTService infoCDTService;

    @Autowired
    SharedService serviceShared;

    @Autowired
    CutService cutService;

    @Autowired
    TramaTraductorCadiServiceImpl tramaTraductorCadiService;

    @Override
    public ResponseEntity<RequestResult<?>> registerTranp(RequestCancelCDT requestCancelCDT, HttpServletRequest http) throws Exception {

        int gmfCapital;
        long nroPord;
        HisTransaccionesEntity transaccionCapital;
        List<HisTransaccionesEntity> transacciones = new ArrayList<>();
        List<HisCdtxCtainvEntity> hisCdtxCtainvEntities = new ArrayList<>();
        String unidOrigen = requestCancelCDT.getInfoOficina().getOfiOrigen().toString();
        String unidDestino = requestCancelCDT.getInfoOficina().getOfiDestino().toString();
        List<InfoCliente> clientes = requestCancelCDT.getInfoCliente();
        List<Capital> pagosCapital = requestCancelCDT.getInfoTrans().getCapital();
        Intereses pagosIntereses = requestCancelCDT.getInfoTrans().getIntereses();
        List<HisTransaccionesEntity> transCapital = new ArrayList<>();

        if (!(pagosIntereses.getTipProceso().equals(2) || pagosIntereses.getTipProceso().equals(4))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "El campo de tipo de proceso solo puede ser 2 o 4, revisar datos ingresados en los intereses"));
        }

        if ((pagosIntereses.getTipProceso().equals(2) && !pagosIntereses.getTipPago().equals(7) ||
                (!pagosIntereses.getTipProceso().equals(2) && pagosIntereses.getTipPago().equals(7)))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
            "Para renovar el cdt, el tipo de proceso deber ser 2 y el tipo de pago 7, revisar datos ingresados en los intereses"));
        }

        boolean validRenovacion = pagosCapital.stream().map(capital ->
              (capital.getTipProceso().equals(2) && !capital.getTipPago().equals(7) ||
                    (!capital.getTipProceso().equals(2) && capital.getTipPago().equals(7))) ? 1 : 0
        ).anyMatch(x -> x.equals(1));
        if (validRenovacion) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
            "Para renovar el cdt, el tipo de proceso deber ser 2 y el tipo de pago 7, revisar datos ingresados en el capital"));
        }

        boolean validTipPago = pagosCapital.stream().map(capital ->
                (!(Optional.ofNullable(capital.getInfoCta().getTipTitCta()).isPresent()) && (capital.getTipPago() == 1
                        || capital.getTipPago() == 2)) ? 1 : 0
        ).anyMatch(x -> x.equals(1));
        if (validTipPago) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "Debe ingresar el numero de cuenta para el tipo de pago 1 y 2, revisar datos ingresados en el capital"));
        }

        if (!Optional.ofNullable(pagosIntereses.getInfoCta().getTipTitCta()).isPresent() && (pagosIntereses.getTipPago().equals(1) || pagosIntereses.getTipPago().equals(2))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "El campo de tipo de cuenta no puede estar vacío, Debe ingresar un tipo de cuenta para cancelar los intereses"));
        }

        if(pagosCapital.stream().filter(capital -> capital.getTipPago().equals(4)).count() > 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "No puede existir dos o mas pagos en efectivo en la misma transacción, revisar datos"));
        }

        if(pagosCapital.stream().filter(capital -> capital.getTipPago().equals(3)).count() > 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "No puede existir dos o mas pagos en cheque en la misma transacción, revisar datos"));
        }

        if(pagosCapital.stream().filter(capital -> capital.getTipPago().equals(7)).count() > 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "No puede existir dos o mas renovaciones en la misma transacción, revisar datos"));
        }

        if (pagosCapital.stream().noneMatch(cap -> cap.getTipPago().equals(7)) && pagosIntereses.getTipPago().equals(7)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "Se debe renovar el valor de intereses y capital, no se puede renovar unicamente el valor a capital"));
        }

        Long numTitularPrincipal = repositorySalPg.titularPrincipalByNumcdt(Long.valueOf(requestCancelCDT.getNumCdt()));
        if(numTitularPrincipal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND,
                    "No se encuentra el titular del cdt '"+requestCancelCDT.getNumCdt()+"' revise el número de cdt ingresado"));
        }

        CDTVencidoDTO ctaInv = repositorySalPg.cdtsInfo(numTitularPrincipal, Long.valueOf(requestCancelCDT.getNumCdt()), null, null);
        if (ctaInv == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND,
                    "No se encuentra el cdt '"+requestCancelCDT.getNumCdt()+"' en salpg, revise el número de cdt ingresado"));
        }

        HisCdtxCtainvEntity hisCdtxCtainv = repositoryHisCdtxCtainv.findByNumCdtAndOplCtainvTblNumCta(Long.valueOf(requestCancelCDT.getNumCdt()), ctaInv.getCtaInv().toString());
        if(hisCdtxCtainv != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResult<>(http, HttpStatus.NOT_FOUND,
                    "El cdt con número '"+requestCancelCDT.getNumCdt()+"' ya fue cancelado"));
        }

        CDTVencidoDTO cdt = repositorySalPg.cdtsInfo(numTitularPrincipal, Long.valueOf(requestCancelCDT.getNumCdt()), null, null);

        BigDecimal valorCapIngresado = pagosCapital.stream().map(Capital::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        if(!valorCapIngresado.equals(cdt.getCapPg())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "El valor total del capital ingresado, debe ser igual a $'"+cdt.getCapPg()+"' no puede ser mayor ni menor"));
        }

        BigDecimal valorIntIngresado = pagosIntereses.getValor();
        if(!valorIntIngresado.equals(cdt.getIntNeto())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                    "El valor total del interés ingresado, debe ser igual a $'"+cdt.getIntNeto()+"' no puede ser mayor ni menor"));
        }

        System.out.println("CDT numero '" + cdt.getNumCdt() + "' con capital total a pagar de $ " + cdt.getCapPg()+" e intereses de $ "+cdt.getIntNeto());

        log.info("preparando Data para insertar en hisTranpg: ");
        for (Capital capital: pagosCapital) {

            gmfCapital = 1;
            if(cdt.getRelCta().equals(3)) {
                if(clientes.stream().anyMatch(x -> Long.valueOf(x.getId()).equals(numTitularPrincipal))){
                    gmfCapital = 0;
                }
            }

            Long nroPordCapital = Long.parseLong(String.format("%09d",Optional.ofNullable(capital.getInfoCta().getNumCta()).orElse( 0L)));
            transaccionCapital = transaccion(cdt, capital.getValor(), unidOrigen, unidDestino, capital.getTipPago(), gmfCapital, 3, nroPordCapital.toString());
            transCapital.add(transaccionCapital);

            // Consumo de Servicio de pagos automaticos
            if (Optional.ofNullable(capital.getInfoCta().getTipTitCta()).isPresent()) {
                if (capital.getTipPago() == 1 || capital.getTipPago() == 2) {
                    pagosAutomaticosService.pagosAutomaticos(http,pagosIntereses.getTipPago(), nroPordCapital, transCapital);
                }
            }

            if (!(Optional.ofNullable(capital.getInfoCta().getTipTitCta()).isPresent()) && (capital.getTipPago() == 1 || capital.getTipPago() == 2)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST,
                        "Debe ingresar el numero de cuenta para el tipo de pago'" + capital.getTipPago() + "' no puede ser vacio"));
            }

            transacciones.add(transaccionCapital);
        }


        // Proceso para Intereses
        log.info("preparando Data para pago de Intereses: ");
        nroPord = Long.parseLong(String.format("%09d",Optional.ofNullable(pagosIntereses.getInfoCta().getNumCta()).orElse( 0L)));
        HisTransaccionesEntity transaccionIntereses = transaccion(cdt, cdt.getIntNeto(), unidOrigen, unidDestino,
                                                    pagosIntereses.getTipPago(), 0, pagosIntereses.getTipProceso(), Long.toString(nroPord));

        List<HisTransaccionesEntity> transInt = new ArrayList<>();
        transInt.add(transaccionIntereses);
        // Consumo de servicio de pagos automaticos para Pago de intereses
        if (pagosIntereses.getTipPago() == 1 || pagosIntereses.getTipPago() == 2) {
            log.info("Se realiza el pago automático de intereses por un valor de $"+ pagosIntereses.getValor());
            pagosAutomaticosService.pagosAutomaticos(http, pagosIntereses.getTipPago(), nroPord, transInt);
        }
        if(pagosIntereses.getTipPago().equals(7)) {
            BigDecimal valor = pagosCapital.stream().filter(x -> x.getTipPago().equals(7)).findFirst().get().getValor();
            HisRenovacion renovacion = new HisRenovacion(cdt.getNumCdt(), cdt.getCtaInv(),
                    cdt.getCodIsin(), null, 1, valor);
            repositoryHisRenovacion.save(renovacion);
        }
        transacciones.add(transaccionIntereses);


        //Proceso para ReteFuente
        log.info("preparando Data para pago de ReteFuente: ");
        HisTransaccionesEntity transaccionRteFte= transaccion(cdt, cdt.getRteFte(), unidOrigen, unidDestino,
                8, 0, 5, String.format("%09d", 0L));
        transacciones.add(transaccionRteFte);


        // ***** Creación de registros en la base de datos

        // Creación de CDT en la tabla OPL_HIS_CDTXCTAINV_LARGE_TBL
        hisCdtxCtainvEntities.add(new HisCdtxCtainvEntity(cdt.getNumCdt(), cdt.getCodIsin(), cdt.getCtaInv().toString()));
        repositoryHisCdtxCtainv.saveAll(hisCdtxCtainvEntities);
        log.info("El CDT '"+cdt.getNumCdt()+"' se ha registrado exitosamente en la tabla OPL_HIS_CDTXCTAINV_LARGE_TBL con " +
                "la cuenta de inversionista número: "+cdt.getCtaInv());

        // Creación de CDT en la tabla OPL_HIS_TRANSACCIONES_LARGE_TBL
        repositoryTransacciones.saveAll(transacciones);
        log.info("El CDT '"+cdt.getNumCdt()+"' se ha registrado exitosamente "+transacciones.size()+" transacciones en la tabla " +
                "OPL_HIS_TRANSACCIONES_LARGE_TBL");

        // Creación deL CDT en la tabla OPL_HIS_INFOCDT_LARGE_TBL
        HisInfoCDTEntity hisInfoCDTEntity = infoCDTService.agregarInfoCDT(requestCancelCDT.getNumCdt(), ctaInv.getCtaInv().toString(), requestCancelCDT.getCanal(), cdt.getTipProd());
        log.info("El CDT '"+cdt.getNumCdt()+"' se ha registrado exitosamente en la tabla OPL_HIS_INFOCDT_LARGE_TBL");

        //Creación del registro en la tabla de renovacion (OPL_HIS_RENOVACION_DOWN_TBL) si existe una transaccion de tipo 7
        HisRenovacion renovacion = null;
        if(pagosCapital.stream().anyMatch(x -> x.getTipPago().equals(7))) {
            BigDecimal valor = pagosCapital.stream().filter(x -> x.getTipPago().equals(7)).findFirst().get().getValor();
            renovacion = new HisRenovacion(cdt.getNumCdt(), cdt.getCtaInv(),
                    cdt.getCodIsin(), null, 1, valor);
            repositoryHisRenovacion.save(renovacion);
            log.info("El CDT '"+cdt.getNumCdt()+"' se ha registrado exitosamente en la tabla OPL_HIS_RENOVACION_DOWN_TBL con estado 1");
        }

        // Creación de las tramas en la tabla OPL_SAL_TRAMASTC_LARGE_TBL
        boolean verificionTarma = tramaTraductorCadiService.tramasTraductorCadi(http.getRequestURL().toString(),
                new RequestTraductor(Long.valueOf(requestCancelCDT.getNumCdt()), cdt.getCodIsin(), cdt.getCtaInv()),
                hisCdtxCtainvEntities, transacciones, hisInfoCDTEntity,  renovacion);

        // Creacion del codigo CUT
        if (verificionTarma) {
            cutService.crearCodCut(cdt, "EXITOSO");
        } else {
            cutService.crearCodCut(cdt, "ERROR");
            throw new Exception("No se crearon las tramas exitosamente, revisar data");
        }

        return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(http, HttpStatus.OK, ""));
    }


    private HisTransaccionesEntity transaccion(CDTVencidoDTO cdt, BigDecimal valor, String unidOrigen, String unidDestino,
                                               Integer tipTransaccion, Integer gmfCapital, Integer proceso, String numCta) {

        HisTransaccionesEntity transacciones;

        if (cdt.getCapPg().compareTo(BigDecimal.ZERO) > 0) {
            transacciones = guardarHisTranpg(
                    cdt.getNumTit(), "2",  validatePordDestino(numCta, tipTransaccion),cdt.getNumTit(),
                    unidOrigen, unidDestino, cdt.getNumCdt().toString(),
                    valor.toString(), proceso, tipTransaccion, gmfCapital, cdt.getCtaInv(), cdt.getCodIsin());
        } else {
            transacciones = guardarHisTranpg(
                    cdt.getNumTit(), "2",  validatePordDestino(numCta, tipTransaccion),cdt.getNumTit(),
                    unidOrigen, unidDestino, cdt.getNumCdt().toString(),
                    "0", proceso, tipTransaccion, gmfCapital, cdt.getCtaInv(), cdt.getCodIsin()
            );
        }
        return transacciones;
    }


    private HisTransaccionesEntity guardarHisTranpg(String idCliente, String tipTran, String nroPord, String idBen, String unidadOrigen,
                                                    String unidadDestino, String numCdt, String valor, Integer proceso, Integer tblTipTransa, Integer capitalGmf,
                                                    Long numCta, String codIsin) {
        HisTransaccionesEntity hisTransaccionesEntity = new HisTransaccionesEntity();

        hisTransaccionesEntity.setOplCdtxctainvTblNumCdt(Long.valueOf(numCdt));
        hisTransaccionesEntity.setOplTipprocesoTblTipProceso(proceso);
        hisTransaccionesEntity.setOplTiptransTblTipTransaccion(tblTipTransa);
        hisTransaccionesEntity.setOplTiptranTblTipTran(Integer.parseInt(tipTran));
        hisTransaccionesEntity.setFechaTran(new Date(System.currentTimeMillis()));
        hisTransaccionesEntity.setIdCliente(Long.valueOf(idCliente));
        hisTransaccionesEntity.setNroPordDestino(nroPord);
        hisTransaccionesEntity.setValor(new BigDecimal(valor));
        hisTransaccionesEntity.setIdBeneficiario(Long.valueOf(idBen));
        hisTransaccionesEntity.setUnidOrigen(unidadOrigen);
        hisTransaccionesEntity.setUnidDestino(unidadDestino);
        hisTransaccionesEntity.setCapitalGmf(capitalGmf);
        hisTransaccionesEntity.setOplCdtxctainvTblOplCtainvTblNumCta(numCta);
        hisTransaccionesEntity.setOplCdtxctainvTblCodIsin(codIsin);
        return hisTransaccionesEntity;
    }

    private String validatePordDestino(String pordDestino, Integer tipTransaccion) {
        if (tipTransaccion.equals(1) || tipTransaccion.equals(2)) return pordDestino;
        return String.format("%09d", 0L);
    }
}
