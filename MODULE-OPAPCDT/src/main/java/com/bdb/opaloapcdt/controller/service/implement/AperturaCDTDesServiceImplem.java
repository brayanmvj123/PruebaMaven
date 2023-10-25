package com.bdb.opaloapcdt.controller.service.implement;

import com.bdb.opaloapcdt.controller.service.interfaces.AperturaCDTDesService;
import com.bdb.opaloapcdt.persistence.Model.InformacionCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionCliente;
import com.bdb.opaloapcdt.persistence.Model.InformacionClixCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionTranpg;
import com.bdb.opaloshare.persistence.repository.*;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@CommonsLog
public class AperturaCDTDesServiceImplem implements AperturaCDTDesService {

    private final RepositoryDatosCliente repoDatosCliente;

    private final RepositoryCondicionesCDT repoConCDT;

    private final RepositoryTransaccionesPago repoTransPg;

    private final RepositoryCdtxCliente repoCdtxCliente;

    private final RepositoryTipVarentorno repoTipVarentorno;

    public AperturaCDTDesServiceImplem(RepositoryDatosCliente repoDatosCliente, RepositoryCondicionesCDT repoConCDT,
                                       RepositoryTransaccionesPago repoTransPg, RepositoryCdtxCliente repoCdtxCliente,
                                       RepositoryTipVarentorno repoTipVarentorno) {
        this.repoDatosCliente = repoDatosCliente;
        this.repoConCDT = repoConCDT;
        this.repoTransPg = repoTransPg;
        this.repoCdtxCliente = repoCdtxCliente;
        this.repoTipVarentorno = repoTipVarentorno;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void crearCDTDes(InformacionCliente clientes, InformacionCDT cdts, InformacionClixCDT clixcdts,
                            InformacionTranpg transacPg, LocalDateTime horaInicio, LocalDateTime horaConsumo) throws Exception {
        log.info("Ingreso al metodo de almacenamiento del CDT Digital.");
        repoDatosCliente.saveAll(clientes.getListClients());
        repoConCDT.save(cdts.getCdt());
        repoCdtxCliente.saveAll(clixcdts.getListClixCDT());
        repoTransPg.saveAll(transacPg.getListTranPag());
        log.info("Tiempo transcurrido: " + Duration.between(horaConsumo, LocalDateTime.now()).getSeconds());
        if (Math.abs(Duration.between(horaConsumo, LocalDateTime.now()).getSeconds()) > 29)
            throw new Exception("El tiempo de apertura ha alcanzado el limite por tanto se cancela la apertura del CDT " +
                    "Digital.");
    }

    @Override
    @Transactional(rollbackForClassName = {"Exception"})
    public String asignarNumCdtDigital() {
        JSONObject json = new JSONObject();
        json.put("app", "OPLBDB");
        repoTipVarentorno.actualizarNumCdtDesma("CDTDESMATERIALIZADO");
        Long numCdt = Long.parseLong(repoTipVarentorno.findByDescVariable("CDTDESMATERIALIZADO").getValVariable()) + 1;
        json.put("numCdtDigital", numCdt.toString());
        json.put("nameServiceResponse", "asignarNumCdtDigitalResponse");
        json.put("status", "200-OK");
        return json.toString();
    }

    @Override
    public Boolean saberSiExisteNumCdt(String numCdtDigital) {
        return repoConCDT.existsByNumCdt(numCdtDigital);
    }
}
