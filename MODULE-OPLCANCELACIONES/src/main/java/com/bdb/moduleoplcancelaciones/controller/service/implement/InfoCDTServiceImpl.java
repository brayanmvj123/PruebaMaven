package com.bdb.moduleoplcancelaciones.controller.service.implement;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.InfoCDTService;
import com.bdb.moduleoplcancelaciones.mapper.Mapper;
import com.bdb.opaloshare.persistence.entity.HisInfoCDTEntity;
import com.bdb.opaloshare.persistence.repository.OplMaedcvTmpDownTblRepository;
import com.bdb.opaloshare.persistence.repository.RepositoryHisInfoCDT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfoCDTServiceImpl implements InfoCDTService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private RepositoryHisInfoCDT repositoryHisInfoCDT;

    @Autowired
    private OplMaedcvTmpDownTblRepository repositoryTmpMaedcv;

    @Override
    public HisInfoCDTEntity agregarInfoCDT(String numCdt, String ctaInv, String canal, String codProd) {

        HisInfoCDTEntity hisInfoCDTEntity = mapper.maeDCVTempDownEntityToHisInfoCDTEntity(repositoryTmpMaedcv.findByNumCDTAndCtainv(numCdt, ctaInv), canal, codProd);
        repositoryHisInfoCDT.save(hisInfoCDTEntity);
        return hisInfoCDTEntity;
    }
}
