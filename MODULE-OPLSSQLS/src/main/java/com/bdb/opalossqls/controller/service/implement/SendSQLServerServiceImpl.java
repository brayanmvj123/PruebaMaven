package com.bdb.opalossqls.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
import com.bdb.opalossqls.controller.service.interfaces.EstadosProcesosService;
import com.bdb.opalossqls.controller.service.interfaces.SendSQLServerService;
import com.bdb.opalossqls.persistence.entity.DCVSalPdcvlEntity;
import com.bdb.opalossqls.persistence.entity.StatusInsertOpl;
import com.bdb.opalossqls.persistence.repository.RepositoryDCVSalPdcvl;
import com.bdb.opalossqls.persistence.repository.RepositoryStatusInsertOpl;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class SendSQLServerServiceImpl implements SendSQLServerService {

    @Autowired
    private RepositoryDCVSalPdcvl repoDCVSalPdcvl;

    @Autowired
    private RepositoryStatusInsertOpl repoStatus;

    @Autowired
    private EstadosProcesosService estadosProcesosService;

    @Override
    public ResponseEntity<String> almacenarCDTDecevalBta(ResponseEntity<List<SalPdcvlEntity>> response) {
        if (conversion(response.getBody())) {
            log.info("CANTIDAD DE REGISTROS ENVIADOS A DCVBTA: "+response.getBody().size());
            if (response.getBody().isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"status\":204}");
            else
                return ResponseEntity.status(HttpStatus.OK).body("{\"status\":200}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"status\":500}");
        }
    }

    public boolean conversion(List<SalPdcvlEntity> opl) {
        List<DCVSalPdcvlEntity> listaDcvl = new ArrayList<>();
        log.info("LA CANTIDAD DE REGISTROS A INSERTAR (SQLSSERVER): " + opl.size());
        boolean saber;
        try {
            if (!opl.isEmpty()) {
                for (SalPdcvlEntity item : opl) {
                    DCVSalPdcvlEntity dcvl = new DCVSalPdcvlEntity();
                    dcvl.setSecdiatxd(item.getSecdiatxd());
                    dcvl.setOfioritx(item.getOfioritx());
                    dcvl.setCodinttx(item.getCodinttx());
                    dcvl.setCtacdtbb(item.getCtacdtbb().substring(5,14));
                    dcvl.setTipocdt(item.getTipocdt());
                    dcvl.setOfduena(item.getOfduena());
                    dcvl.setNmbtit1(item.getNmbtit1());
                    dcvl.setTipdoc1(item.getTipdoc1());
                    dcvl.setNrodoc1(item.getNrodoc1());
                    dcvl.setPlazoti(item.getPlazoti());
                    dcvl.setKapital(item.getKapital());
                    dcvl.setFchvenc(item.getFchvenc());
                    dcvl.setCtaaabo(item.getCtaaabo());
                    dcvl.setDeposit(item.getDeposit());
                    dcvl.setRespcdts(item.getRespcdts());
                    dcvl.setFchaper(item.getFchaper());
                    dcvl.setTipprod(item.getTipprod());
                    dcvl.setClasper(item.getClasper());
                    dcvl.setActeco(item.getActeco());
                    dcvl.setNmbtit2(item.getNmbtit2());
                    dcvl.setTipdoc2(item.getTipdoc2());
                    dcvl.setNrodoc2(item.getNrodoc2());
                    dcvl.setNmbtit3(item.getNmbtit3());
                    dcvl.setTipdoc3(item.getTipdoc3());
                    dcvl.setNrodoc3(item.getNrodoc3());
                    dcvl.setNmbtit4(item.getNmbtit4());
                    dcvl.setTipdoc4(item.getTipdoc4());
                    dcvl.setNrodoc4(item.getNrodoc4());
                    dcvl.setTiprela(item.getTiprela());
                    dcvl.setDirresi(item.getDirresi());
                    dcvl.setBarrio(item.getBarrio());
                    dcvl.setCiudad(item.getCiudad());
                    dcvl.setCiudane(item.getCiudane());
                    dcvl.setTeleres(item.getTeleres());
                    dcvl.setExtOfi(item.getExtOfi());
                    dcvl.setEstTit(item.getEstTit());
                    dcvl.setTiprete(item.getTiprete());
                    dcvl.setTasnomi(item.getTasnomi());
                    dcvl.setTasefec(item.getTasefec());
                    dcvl.setTasadtf(item.getTasadtf());
                    dcvl.setTiptasa(item.getTiptasa());
                    dcvl.setSpread(item.getSpread());
                    dcvl.setFechape(item.getFechape());
                    dcvl.setBaseliq(item.getBaseliq());
                    dcvl.setPeriodi(item.getPeriodi());
                    dcvl.setTipplaz(item.getTipplaz());
                    listaDcvl.add(dcvl);
                }
                repoDCVSalPdcvl.saveAll(listaDcvl);
                estadosProcesosService.llenarEstado("APDI_COMPLETED");
                saber = true;
            }else{
                saber = true;
                estadosProcesosService.llenarEstado("APDI_NO_DATA");
            }
        } catch (Exception e) {
            saber = false;
            estadosProcesosService.llenarEstado("APDI_FAIL");
            log.fatal(e.getMessage());
        }
        return saber;
    }

    @Override
    public String getEstado() {
        Optional<StatusInsertOpl> statusapdi = Optional.ofNullable(repoStatus.getMaximoEstado(LocalDate.now(), "APDI%"));
        Optional<StatusInsertOpl> statusrenaut = Optional.ofNullable(repoStatus.getMaximoEstado(LocalDate.now(), "RENAUT%"));

        if (statusapdi.isPresent() && statusrenaut.isPresent()) {
            log.info("ESTADO: " + statusapdi.get().getEstado() + " - ITEM: " + statusapdi.get().getItem());
            return validateStatus(statusapdi.get().getEstado()) && validateStatus(statusrenaut.get().getEstado()) ? "COMPLETED" : "FAIL";
        }else
            return "FAIL";
    }

    public boolean validateStatus(String descripcionStatus){
        return !descripcionStatus.contains("FAIL");
    }

    @Override
    public String actualizarClientesCDT(ResponseEntity<List<SalPdcvlEntity>> response) {
        List<SalPdcvlEntity> opl = response.getBody();
        try {
            for (SalPdcvlEntity item : opl) {
                repoDCVSalPdcvl.actualizarClientes(item.getNmbtit2(), item.getNmbtit3(), item.getNmbtit4(),
                        item.getTipdoc2(), item.getTipdoc3(), item.getTipdoc4(),
                        item.getNrodoc2(), item.getNrodoc3(), item.getNrodoc4(), item.getCtacdtbb());
            }
            return "{\"status\":200}";
        } catch (Exception e) {
            log.fatal(e.getMessage());
            return "{\"status\":500}";
        }
    }

    @Override
    public Long verificarTablaArchivoP() {
        Long cantidadRegistros = repoDCVSalPdcvl.count();
        log.info("CANTIDAD DE REGISTROS SQL: "+cantidadRegistros);
        return cantidadRegistros;
    }

    @Override
    public void eliminarDataTablaArchivoP() {
        repoDCVSalPdcvl.deleteAll();
    }

}
