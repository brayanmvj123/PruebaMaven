package com.bdb.oplbacthdiarios.schudeler.serviceimpls;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalMaeVentasEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalMaeVentas;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchTransCdtsDigBi;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@CommonsLog
public class OperationBatchTransCdtsDigBiImpl implements OperationBatchTransCdtsDigBi {

    private final RepositorySalMaeVentas repositorySalMaeVentas;

    private final RepositoryTipVarentorno repositoryTipVarentorno;

    final FTPService serviceFTP;

    public OperationBatchTransCdtsDigBiImpl(RepositorySalMaeVentas repositorySalMaeVentas,
                                            RepositoryTipVarentorno repositoryTipVarentorno,
                                            @Qualifier("serviceFTPS") FTPService serviceFTP){
        this.repositorySalMaeVentas = repositorySalMaeVentas;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
        this.serviceFTP = serviceFTP;
    }

    /**
     * Se utiliza el metodo para almacenar la datos provenientes del cruce de información (query).
     * Solo se toman en cuenta los CDTs Digitales del <u>dia anterior a la fecha del día</u>.
     */
    @Override
    public void getData() {
        log.info("SE REALIZA LA CONSULTA, EL RESULTADO SERA ALMACENADO EN LA TABLA SAL_MAEVENTAS");
        List<SalMaeVentasEntity> salMaeVentasEntityList = repositorySalMaeVentas.queryMaeVentas(LocalDate.now().minusDays(1).toString());
        log.info("LA CONSULTA OBTUVO: "+ salMaeVentasEntityList.size() + " CDTS DIGITALES QUE SERAN ALMACENADOS.");
        repositorySalMaeVentas.saveAll(salMaeVentasEntityList);
    }

    /**
     * Permite la creación del archivo.
     */
    @Override
    public void makeFile() throws ErrorFtps {
        log.info("SE INICIA LA CREACION DEL ARCHIVO MAE_VENTAS_BIBA_AAAAMMDD.TXT");
        log.info("SE PROCEDE A CONSULTAR LOS DATOS ALMACENDOS EN LA TABLA SAL_MAEVEN");
        List<SalMaeVentasEntity> salMaeVentasEntityList = repositorySalMaeVentas.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);
        String nombre = repositoryTipVarentorno
                .findByDescVariable("OUT_FILE_MAE_VENTAS_BIBA")
                .getValVariable().replace("AAAAMMDD", LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        log.info("CANTIDAD DE TRAMAS: " + salMaeVentasEntityList.size());
        try {
            String nuevaLinea = "\n";
            salMaeVentasEntityList.forEach( item -> {
                try {
                    out.write(item.reportTransFileCdtsDigBi().getBytes());
                    out.write(nuevaLinea.getBytes());
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                    log.error("ERROR AL GENERAR LA ESTRUCTURA DEL ARCHIVO.");
                }
            });
        } catch (Exception e) {
            log.error(e);
        }

        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        serviceFTP.makeDirectoryDay(parameters.getRuta());
        serviceFTP.makeSubDirectorys();
        String fechaActual = serviceFTP.obtenerFechaActual();
        serviceFTP.makeFile(byteArrayOutputStream, serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual), nombre);
        serviceFTP.disconnectFTP();
    }


}
