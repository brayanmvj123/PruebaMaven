package com.bdb.platform.servfront.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.OficinaParWithRelationsDownEntity;
import com.bdb.opaloshare.persistence.model.office.ResponseOffice;
import com.bdb.opaloshare.persistence.model.office.ResponseOfficeDetail;
import com.bdb.opaloshare.persistence.repository.*;
import com.bdb.platform.servfront.controller.service.interfaces.OfficeService;
import com.bdb.platform.servfront.mapper.Mapper;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OfficeServiceImpl")
@CommonsLog
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private RepositoryOficinaWithRelations repositoryOficina;

    @Override
    public List<OficinaParWithRelationsDownEntity> findOfficeWithoutEmailDaily() {
        return mapper.listOfficeToOficinaParEntity(repositoryOficina.officesInLiquidationDailyWithoutEmailUser());
    }

    @Override
    public List<OficinaParWithRelationsDownEntity> findOfficeWithoutEmailWeekly() {
        return mapper.listOfficeToOficinaParEntity(repositoryOficina.officesInLiquidationWeeklyWithoutEmailUser());
    }

    @Override
    public List<ResponseOffice> findAllOfficeByState(Integer status) {
        if(status == null) {
            return  mapper.listHisOficinaParToResponseOffice(repositoryOficina.findAll());
        } else {
            return mapper.listHisOficinaParToResponseOffice(repositoryOficina.findAllByOplEstadosTblTipEstado(status));
        }
    }

    @Override
    public ResponseOfficeDetail findOfficeById(Integer id) {
        return mapper.hisOficinaParToResponseOfficeDetail(repositoryOficina.findById(id).orElse(null));
    }
}
