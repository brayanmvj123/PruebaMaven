package com.bdb.opalo.oficina.controller.service.implement.operationbatch;

import com.bdb.opalo.oficina.controller.service.interfaces.operationbatch.OperationClosingOfficesService;
import com.bdb.opaloshare.persistence.entity.CarCierreOfiEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import com.bdb.opaloshare.persistence.repository.RepositoryCarCierreOfi;
import com.bdb.opaloshare.persistence.repository.RepositoryOficina;
import com.bdb.opaloshare.persistence.repository.RepositoryTransaccionesPago;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@CommonsLog
@Service
public class OperationClosingOfficesServiceImpl implements OperationClosingOfficesService {

    final
    RepositoryCarCierreOfi repositoryCarCierreOfi;

    final
    RepositoryCDTSLarge repositoryCDTSLarge;

    final
    RepositoryTransaccionesPago repositoryTransaccionesPago;

    final
    RepositoryOficina repositoryOficina;

    public OperationClosingOfficesServiceImpl(RepositoryCarCierreOfi repositoryCarCierreOfi,
                                     RepositoryCDTSLarge repositoryCDTSLarge,
                                     RepositoryTransaccionesPago repositoryTransaccionesPago,
                                     RepositoryOficina repositoryOficina) {
        this.repositoryCarCierreOfi = repositoryCarCierreOfi;
        this.repositoryCDTSLarge = repositoryCDTSLarge;
        this.repositoryTransaccionesPago = repositoryTransaccionesPago;
        this.repositoryOficina = repositoryOficina;
    }

    /**
     * Realiza los siguientes pasos:
     * <ol>
     *     <li>Obtiene las oficinas que cerraran.</li>
     *     <li>Actualiza el estado de las oficinas a <u><i>CERRADO</i></u></li>
     * </ol>
     * @see RepositoryCarCierreOfi
     * @see RepositoryOficina
     */
    @Override
    public void getOfficesClosed() {
        List<CarCierreOfiEntity> cierre = repositoryCarCierreOfi.findAll()
                .stream()
                .map(item -> new CarCierreOfiEntity(item.getOfiOri(),item.getOfiDes()))
                .distinct()
                .collect(Collectors.toList());
        cierre.forEach(item -> repositoryOficina.updateClosedOffices(5, Integer.parseInt(item.getOfiOri())));
    }

    /**
     * Realiza los siguientes pasos:
     * <ol>
     *     <li>Obtiene las oficinas que cerraran y en las cuales se han aperturado CDTs Digitales.</li>
     *     <li>Actualiza el ID de la oficina que cerraran por el nuevo ID de la oficina que tomara el lugar.</li>
     * </ol>
     * @see RepositoryCarCierreOfi
     * @see RepositoryCDTSLarge
     */
    @Override
    public void changeCDTOfficesId() {
        log.info("CAMBIOS DE IDS DE LAS OFICINAS QUE CIERRAN DE LOS CDTS DIGITALES ");
        repositoryCarCierreOfi.knowCDTofficesClosed()
                .forEach(item -> repositoryCDTSLarge.updateOffices(item.getOfiDes(), item.getOfiOri(), item.getNumCdt()));
    }

    /**
     * Realiza los siguientes pasos:
     * <ol>
     *    <li>Obtiene las oficinas que cerraran y en las cuales se han aperturado las <u>transacciones de pago</u> de los CDTs Digitales.</li>
     *    <li>Actualiza el ID de la oficinas que cerraran, en los campos <u>Número Destino</u> y <u>Número Origen</u>
     *    por el nuevo ID de la oficina que tomara el lugar.</li>
     * </ol>
     * @see RepositoryCarCierreOfi
     * @see RepositoryTransaccionesPago
     */
    @Override
    public void changeTranPgOfficesId() {
        log.info("ACTUALIZACIÓN DEL ID DE LAS OFICINAS EN LAS TRANPG");
        repositoryCarCierreOfi.knowTranPgOfficesClosed()
                .forEach(item -> repositoryTransaccionesPago.updateOffices(item.getOfiDes(), item.getOfiOri()));
    }

}
