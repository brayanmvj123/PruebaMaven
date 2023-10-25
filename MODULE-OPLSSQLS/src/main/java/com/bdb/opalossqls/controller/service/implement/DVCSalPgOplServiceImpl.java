package com.bdb.opalossqls.controller.service.implement;

import com.bdb.opalossqls.controller.service.interfaces.IDVCSalPgOplService;
import com.bdb.opalossqls.persistence.entity.DCVSalPgOpl;
import com.bdb.opalossqls.persistence.model.JSONCancelCdtDig;
import com.bdb.opalossqls.persistence.repository.RepositoryDVCSalPgOpl;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class DVCSalPgOplServiceImpl implements IDVCSalPgOplService {

    @Autowired
    RepositoryDVCSalPgOpl repositoryDVCSalPgOpl;

    @Override
    public DCVSalPgOpl guardar(DCVSalPgOpl dcvSalPgOpl) {
        log.info("REGISTRANDO LOS DATOS DEL CDT :"+dcvSalPgOpl +" EN LA TABLA  DCV_SAL_PGOPALO_DOWN_TB");
        return repositoryDVCSalPgOpl.save(dcvSalPgOpl);
    }

    @Override
    public void guardarLista(List<JSONCancelCdtDig> cancelacioens) {
        log.info("BORRANDO LOS CDTS DIGITALES DEL PROCESO ANTERIOR");
        repositoryDVCSalPgOpl.deleteAllInBatch();
        log.info("REGISTRANDO EL LISTADO DE TODOS LOS DATOS DEL CDT EN LA TABLA  DCV_SAL_PGOPALO_DOWN_TB");
        repositoryDVCSalPgOpl.saveAll(cancelacioens
                .stream()
                .map(cdt-> new DCVSalPgOpl(cdt.getCodIsin(),
                        cdt.getNumCdt(),
                        cdt.getTipId(),
                        cdt.getNumTit(),
                        cdt.getNomTit(),
                        cdt.getCapPg(),
                        cdt.getIntBruto(),
                        cdt.getRteFte(),
                        cdt.getIntNeto(),
                        cdt.getFormaPago()))
                .collect(Collectors.toList()));
        log.info("Se ha almacenado la información de las cancelaciones de CDTs Digitales con exito.");
    }
}
