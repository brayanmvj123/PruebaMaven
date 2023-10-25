package com.bdb.platform.servfront.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.OficinaParWithRelationsDownEntity;
import com.bdb.opaloshare.persistence.model.office.ResponseOffice;
import com.bdb.opaloshare.persistence.model.office.ResponseOfficeDetail;

import java.util.List;

public interface OfficeService {

    List<OficinaParWithRelationsDownEntity> findOfficeWithoutEmailDaily();

    List<OficinaParWithRelationsDownEntity> findOfficeWithoutEmailWeekly();

    List<ResponseOffice> findAllOfficeByState(Integer status);

    ResponseOfficeDetail findOfficeById(Integer id);

}
