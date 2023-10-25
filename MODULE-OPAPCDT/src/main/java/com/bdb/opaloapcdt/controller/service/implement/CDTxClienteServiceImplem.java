package com.bdb.opaloapcdt.controller.service.implement;

import com.bdb.opaloapcdt.controller.service.interfaces.CDTxClienteService;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONClientDatos;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONCondCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionClixCDT;
import com.bdb.opaloshare.persistence.entity.HisClixCDTLarge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CDTxClienteServiceImplem implements CDTxClienteService {

    private static final Logger LOG = LoggerFactory.getLogger(CDTxClienteServiceImplem.class);

    @Override
    public InformacionClixCDT almacenarCDTxCliente(List<JSONClientDatos> cliente, JSONCondCDT cdt, boolean condicionesCDT) {
        LOG.info("Se ingresa a generar el objeto de relación entre el CDT Digital y el usuario para ser almacenado.");
        InformacionClixCDT informacionClixCDT = new InformacionClixCDT();
        if (condicionesCDT) {
            try {
                List<HisClixCDTLarge> informacionCliente = new ArrayList<>();
                long cantidad = cliente.stream().filter(item -> item.getTipoTitular() == 1).count();
                if (cantidad == 1) {
                    for (JSONClientDatos request : cliente) {
                        HisClixCDTLarge datosCliente = new HisClixCDTLarge();
                        datosCliente.setOplClientesTblNumTit(request.getIdentificacion().getNumId());
                        datosCliente.setOplCdtsTblNumCdt(String.valueOf(cdt.getNumCdtDigital()));
                        datosCliente.setCtaInv(request.getCuentaInversionista());
                        datosCliente.setTipTitular(String.valueOf(request.getTipoTitular()));
                        informacionCliente.add(datosCliente);
                    }
                    informacionClixCDT.setEstado(true);
                    informacionClixCDT.setListClixCDT(informacionCliente);
                } else
                    informacionClixCDT.setEstado(false);
            } catch (IllegalArgumentException e) {
                LOG.error(e.getMessage());
                informacionClixCDT.setEstado(false);
            }
        } else {
            informacionClixCDT.setEstado(false);
        }
        LOG.info("Se termina de generar el objeto de relación entre el CDT Digital y el usuario para ser almacenado.");
        return informacionClixCDT;
    }

}
