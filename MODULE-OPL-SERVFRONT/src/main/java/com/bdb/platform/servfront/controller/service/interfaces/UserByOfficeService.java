package com.bdb.platform.servfront.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisUsuarioxOficinaEntity;
import com.bdb.opaloshare.persistence.model.userbyoffice.RequestUserByOffice;
import com.bdb.opaloshare.persistence.model.userbyoffice.ResponseUserByOffice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserByOfficeService {

    List<ResponseUserByOffice> findAll();

    Page<ResponseUserByOffice> findAll(Pageable pageable);

    ResponseUserByOffice findById(Long id);

    List<ResponseUserByOffice> findAllParameters(Long nroOficina,  Integer estadoUsuario);

    HisUsuarioxOficinaEntity save(RequestUserByOffice requestUserByOffice);

    HisUsuarioxOficinaEntity update(RequestUserByOffice requestUserByOffice, Long id);

    Boolean delete(Long id);

    Boolean checkOffice(Long id);
}
