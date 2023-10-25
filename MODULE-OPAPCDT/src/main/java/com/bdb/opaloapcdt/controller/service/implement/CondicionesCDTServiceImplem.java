package com.bdb.opaloapcdt.controller.service.implement;

import com.bdb.opaloapcdt.controller.service.interfaces.CondicionesCDTService;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONCondCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionCDT;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryOficina;
import com.bdb.opaloshare.persistence.repository.RepositoryTipDepositante;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;

@Service
@CommonsLog
public class CondicionesCDTServiceImplem implements CondicionesCDTService {

    private final SharedService serviceShared;

    private final RepositoryTipDepositante repoDepo;

    private final RepositoryOficina repositoryOficina;

    private static final Logger LOG = LoggerFactory.getLogger(CondicionesCDTServiceImplem.class);

    public CondicionesCDTServiceImplem(SharedService serviceShared, RepositoryTipDepositante repoDepo, RepositoryOficina repositoryOficina) {
        this.serviceShared = serviceShared;
        this.repoDepo = repoDepo;
        this.repositoryOficina = repositoryOficina;
    }

    @Override
    public InformacionCDT almacenarCondicionesCDT(JSONCondCDT request, boolean datosClientes) throws SQLException {
        LOG.info("Se ingresa a generar el objeto del CDT Digital para ser almacenado.");
        InformacionCDT informacionCDT = new InformacionCDT();
        if (datosClientes) {
            try {
                if (verificarTipTasaAndSpread(request.getTipoTasa(),request.getSpread(),request.getSignoSpread()) &&
                        verificarUnidUVRandCantUVR(request.getUnidadUVR(),request.getCantidadUnidad())) {
                    HisCDTSLargeEntity condCDT = new HisCDTSLargeEntity();
                    condCDT.setFecha(serviceShared.formatoTimeStampSQL(request.getFecha()));
                    condCDT.setCanal(request.getCanal());
                    condCDT.setUsuario(request.getUsuarioNt());
                    condCDT.setCodCut(request.getCodCut());
                    condCDT.setCodTrn(request.getCodTransaccion());
                    condCDT.setNumCdt(String.valueOf(request.getNumCdtDigital()));
                    condCDT.setCodProd(request.getCodProd());
                    condCDT.setMercado(request.getMercado());
                    condCDT.setUnidNegocio(request.getUnidadNegocio());
                    condCDT.setUnidCeo(request.getUnidCEO());
                    condCDT.setFormaPago(request.getFormaPago());
                    condCDT.setOplDepositanteTblTipDepositante(repoDepo.conocerDepositante(request.getDepositante()));

                    Integer codigoOficina = identificarOficina(Integer.parseInt(request.getUnidadNegocio()),
                            Integer.parseInt(request.getUnidCEO()));
                    log.info("CODIGO DE OFICNA: " + codigoOficina);
                    condCDT.setOplOficinaTblNroOficina(repositoryOficina.getByNroOficinaAndOplEstadosTblTipEstado(codigoOficina,4).getNroOficina());

                    condCDT.setPlazo(request.getPlazo().getCantidadPlazo());
                    condCDT.setOplTipplazoTblTipPlazo(Integer.parseInt(request.getPlazo().getTipo()));
                    condCDT.setFechaEmi(serviceShared.formatoFechaSQL(request.getFechaEmision()));
                    condCDT.setFechaVen(serviceShared.formatoFechaSQL(request.getFechaVencimiento()));
                    condCDT.setOplTipbaseTblTipBase(Integer.parseInt(request.getBase()));
                    condCDT.setModalidad(request.getModalidad());
                    condCDT.setOplTiptasaTblTipTasa(request.getTipoTasa());
                    condCDT.setOplTipperiodTblTipPeriodicidad(request.getTipoPeriodicidad());
                    condCDT.setSpread(request.getSpread());
                    condCDT.setSignoSpread(request.getSignoSpread());
                    condCDT.setTasEfe(request.getTasaEfectiva());
                    condCDT.setTasNom(request.getTasaNominal());
                    condCDT.setMoneda(request.getMoneda());
                    condCDT.setUnidUvr(request.getUnidadUVR());
                    condCDT.setCantUnid(request.getCantidadUnidad());
                    condCDT.setValor(request.getValor());
                    condCDT.setTipTitularidad(request.getTipoTitularidad());
                    condCDT.setOplEstadosTblTipEstado(1);

                    informacionCDT.setEstado(true);
                    informacionCDT.setCdt(condCDT);
                    LOG.info("Se termina de generar el objeto del CDT Digital para ser almacenado.");

                }else{
                    LOG.error("El TIPO TASA no es coherente con el valor del SPREAD");
                    LOG.error("La UNIDAD UVR no es coherente con la CANTIDAD DE UNIDAD");
                    informacionCDT.setEstado(false);
                }
            } catch (DataException | IllegalArgumentException e) {
                LOG.error(e.getMessage());
                informacionCDT.setEstado(false);
            }
        }
        return informacionCDT;
    }

    public Integer identificarOficina(Integer unidadNegocio, Integer CEO) {
        Integer codigo = null;
        if (unidadNegocio != null || unidadNegocio == null && CEO != null) {
            codigo = CEO;
        } else if (CEO == null) {
            codigo = unidadNegocio;
        }
        return codigo;
    }

    public Boolean verificarTipTasaAndSpread(Integer tipTasa , BigDecimal spread , String signoSpread ){
        if (tipTasa == 1){
            return spread.equals(new BigDecimal(0)) && signoSpread.equals("+");
        }else{
            return true;
        }
    }

    public Boolean verificarUnidUVRandCantUVR(String unidUVR , BigDecimal cantUVR){
        if (unidUVR.equals("2")){
            return cantUVR.equals(new BigDecimal(0));
        }else{
            return cantUVR.compareTo(new BigDecimal(0)) > 0 ;
        }
    }

}
