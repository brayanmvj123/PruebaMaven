package com.bdb.opaloapcdt.controller.service.implement;

import com.bdb.opaloapcdt.controller.service.interfaces.TransaccionesPagosCDTService;
import com.bdb.opaloapcdt.persistence.Model.InformacionTranpg;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONPagCDTDes;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CommonsLog
public class TransaccionesPagosServiceImplem implements TransaccionesPagosCDTService {

    private static final Logger LOG = LoggerFactory.getLogger(TransaccionesPagosServiceImplem.class);

    List<HisTranpgEntity> entityTransPgCdt;

    @Override
    public InformacionTranpg almacenarTransaccionesPagos(List<JSONPagCDTDes> requestList, boolean condicionesCDT, String numeroCDT) {
        LOG.info("Se ingresa a generar el objeto de transacción de pago del CDT Digital para ser almacenado.");

        boolean saber = false;
        InformacionTranpg info = new InformacionTranpg();
        if (condicionesCDT) {
            try {
                List<HisTranpgEntity> listTranPag = new ArrayList<>();
                for (JSONPagCDTDes request : requestList){
                    log.info("Inicia el llenado de la informacion de Transacción de pago");
                    HisTranpgEntity transacciones = new HisTranpgEntity();
                    transacciones.setIdCliente(Long.parseLong(request.getIdCliente()));
                    transacciones.setProceso(request.getProceso());
                    HisCDTSLargeEntity cdtsLargeEntity = new HisCDTSLargeEntity();
                    cdtsLargeEntity.setNumCdt(numeroCDT);
                    transacciones.setHisCDTSLargeEntity(cdtsLargeEntity);
                    transacciones.setTipTran(request.getTipTran());
                    transacciones.setNroPordDestino(request.getNroPordDestino());
                    transacciones.setValor(request.getValor());
                    transacciones.setIdBeneficiario(request.getIdBeneficiario());
                    transacciones.setUnidOrigen(request.getUnidadOrigen());
                    transacciones.setUnidDestino(Integer.parseInt(request.getUnidadDestino()));
                    transacciones.setOplTiptransTblTipTrasaccion(request.getTipoTransaccion());
                    log.info("El llenado fue exitoso, no se presente ningun problema");
                    info.setMensaje("Bueno");
                    listTranPag.add(transacciones);
                    info.setListTranPag(listTranPag);
                }
                guardarEntity(listTranPag);
                info.setEstado(true);
            } catch (IllegalArgumentException e) {
                info.setMensaje(e.getMessage());
                info.setEstado(saber);
                LOG.error(e.getMessage());
            }
        } else {
            info.setMensaje("Error");
            info.setEstado(saber);
        }
        LOG.info("Se termino de generar el objeto de transacción de pago del CDT Digital para ser almacenado.");
        return info;
    }

    @Override
    public void guardarEntity(List<HisTranpgEntity> entity) {
        this.entityTransPgCdt = entity;
    }

}
