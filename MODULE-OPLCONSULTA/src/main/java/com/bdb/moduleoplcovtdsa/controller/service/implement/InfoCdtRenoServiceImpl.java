package com.bdb.moduleoplcovtdsa.controller.service.implement;

import com.bdb.moduleoplcovtdsa.controller.service.interfaces.InfoCdtRenoService;
import com.bdb.moduleoplcovtdsa.mapper.infocdtreno.InfoCdtRenoMapper;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.request.JSONRequestInfoCdtReno;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.response.JSONResponseInfoCdtReno;
import com.bdb.moduleoplcovtdsa.persistence.model.error.ErrorException;
import com.bdb.opaloshare.persistence.entity.HisRenovaCdtEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import com.bdb.opaloshare.persistence.repository.RepositoryHisRenovaCdt;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@CommonsLog
public class InfoCdtRenoServiceImpl implements InfoCdtRenoService {

    final RepositoryHisRenovaCdt repositoryHisRenovaCdt;
    final RepositoryCDTSLarge repositoryCDTSLarge;
    final InfoCdtRenoMapper infoCdtRenoMapper;

    public InfoCdtRenoServiceImpl(RepositoryHisRenovaCdt repositoryHisRenovaCdt,
                                  RepositoryCDTSLarge repositoryCDTSLarge,
                                  InfoCdtRenoMapper infoCdtRenoMapper) {
        this.repositoryHisRenovaCdt = repositoryHisRenovaCdt;
        this.repositoryCDTSLarge = repositoryCDTSLarge;
        this.infoCdtRenoMapper = infoCdtRenoMapper;
    }

    /**
     * Consulta la información de renovación del CDT Digital Desmaterializado.
     * @param request   Parametros de busqueda, consultar {@link JSONRequestInfoCdtReno}.
     * @param urlRequest    Url de consumo del servicio.
     * @return  La información de renovación obtenida del CDT Digital.
     * @see JSONResponseInfoCdtReno
     * @see ResponseEntity
     */
    @Override
    public ResponseEntity<JSONResponseInfoCdtReno> consultarNumCdt(JSONRequestInfoCdtReno request, HttpServletRequest urlRequest) {
        log.info("SE INICIA LA CONSULTA DEL CDT DIGITAL DESMATERIALIZADO Y OBTENER LA INFORMACIÓN DE SU RENOVACIÓN.");
        Optional<HisRenovaCdtEntity> hisRenovaCdtEntity = Optional.ofNullable(repositoryHisRenovaCdt.findByCdtAct(request.getNumCdtAct()));
        return hisRenovaCdtEntity.map(renovaCdtEntity -> ResponseEntity
                .status(HttpStatus.OK)
                .body(new JSONResponseInfoCdtReno(urlRequest.getRequestURI(),
                        HttpStatus.OK,
                        request,
                        "Se ha encontrado información sobre el CDT Digital Desmaterializado consultado.",
                        infoCdtRenoMapper.toInfoCdtReno(renovaCdtEntity, repositoryCDTSLarge))))
                .orElseThrow(() -> new ErrorException("El CDT Digital Desmaterializado consultado NO ha sido renovado",
                        HttpStatus.NOT_FOUND));
    }

}
