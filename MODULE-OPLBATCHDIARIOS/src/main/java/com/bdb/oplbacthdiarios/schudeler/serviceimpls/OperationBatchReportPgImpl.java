package com.bdb.oplbacthdiarios.schudeler.serviceimpls;

import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.oplbacthdiarios.mapper.MapperReportPg;
import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchReportPg;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@CommonsLog
public class OperationBatchReportPgImpl implements OperationBatchReportPg {

    @Autowired
    private RepositorySalPg repositorySalPg;

    @Autowired
    private MapperReportPg mapperReportPg;


    /**
     * Se encarga de <i>almacenar</i> la data obtenida del cruce de información entre las tablas
     * {@link com.bdb.opaloshare.persistence.entity.SalPgDownEntity} .
     */
    @Override
    public void reportFechaVenOfic() throws Exception {
        log.info("INICIO EL CRUCE DE INFORMACIÓN PARA LLENAR LA TABLA SAL_PG.");
        try {
            repositorySalPg
                    .cruceReportFechaVenOfic()
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(x -> x.getDepositante().equals("1"))
                    .forEach(item -> repositorySalPg.save(mapperReportPg.toColumnsReportPgWeekly(item)));
        } catch (Exception e) {
            log.error("ERROR AL ALMACENAR LA INFORMACIÓN EN LA TABLA DE SALIDA...");
            throw new Exception("ERROR AL ALMACENAR LA INFORMACIÓN EN LA TABLA DE SALIDA..." + e.getMessage());
        }
    }
}
