package com.bdb.platform.servfront.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.HisUsuarioxOficinaEntity;
import com.bdb.opaloshare.persistence.model.userbyoffice.RequestUserByOffice;
import com.bdb.opaloshare.persistence.model.userbyoffice.ResponseUserByOffice;
import com.bdb.opaloshare.persistence.repository.*;
import com.bdb.platform.servfront.controller.service.interfaces.UserByOfficeService;
import com.bdb.platform.servfront.mapper.Mapper;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserByOfficeServiceImpl")
@CommonsLog
public class UserByOfficeServiceImpl implements UserByOfficeService {

    @Autowired
    private RepositoryHisUsuarioxOficina repositoryHisUsuarioxOficina;

    @Autowired
    private RepositoryOficinaWithRelations repositoryOficina;

    @Autowired
    private Mapper mapper;

    @Override
    public List<ResponseUserByOffice> findAll() {
        return  mapper.listHisUsuarioxOficinaToResponseHisUsuarioxOficina(repositoryHisUsuarioxOficina.findAll());
    }

    @Override
    public Page<ResponseUserByOffice> findAll(Pageable pageable) {
        Page<HisUsuarioxOficinaEntity> usuarioxOficinaPage = repositoryHisUsuarioxOficina.findAll(pageable);
        List<ResponseUserByOffice> dtos = mapper.listHisUsuarioxOficinaToResponseHisUsuarioxOficina(usuarioxOficinaPage.getContent());
        return new PageImpl<>(dtos, pageable, usuarioxOficinaPage.getTotalElements());
    }

    @Override
    public ResponseUserByOffice findById(Long id) {
        return mapper.hisUsuarioxOficinaToResponseHisUsuarioxOficina(repositoryHisUsuarioxOficina.findById(id).orElse(null));
    }

    @Override
    public List<ResponseUserByOffice> findAllParameters(Long nroOficina, Integer estadoUsuario) {
        if(nroOficina == null && estadoUsuario == null) {
            return  mapper.listHisUsuarioxOficinaToResponseHisUsuarioxOficina(repositoryHisUsuarioxOficina.findAll());
        }
        if(nroOficina == null && estadoUsuario != null){
            return mapper.listHisUsuarioxOficinaToResponseHisUsuarioxOficina(repositoryHisUsuarioxOficina.findAllByEstadoUsuario(estadoUsuario));
        }
        if(nroOficina != null && estadoUsuario == null){
            return mapper.listHisUsuarioxOficinaToResponseHisUsuarioxOficina(repositoryHisUsuarioxOficina.findAllByNroOficina(nroOficina));
        } else {
            return mapper.listHisUsuarioxOficinaToResponseHisUsuarioxOficina(repositoryHisUsuarioxOficina.findAllByNroOficinaAndEstadoUsuario(nroOficina, estadoUsuario));
        }
    }

    @Override
    public HisUsuarioxOficinaEntity save(RequestUserByOffice requestUserByOffice) {
        return repositoryHisUsuarioxOficina.save(mapper.requestUserByOfficeToHisUsuarioxOficina(requestUserByOffice));
    }

    @Override
    public HisUsuarioxOficinaEntity update(RequestUserByOffice requestUserByOffice, Long id) {
        if (!repositoryHisUsuarioxOficina.findById(id).isPresent()) return null;
        HisUsuarioxOficinaEntity usuarioxOficina = mapper.requestUserByOfficeToHisUsuarioxOficina(requestUserByOffice);
        usuarioxOficina.setItem(id);
        return repositoryHisUsuarioxOficina.save(usuarioxOficina);
    }

    @Override
    public Boolean delete(Long id) {
        if ( !repositoryHisUsuarioxOficina.findById(id).isPresent()) return false;
        repositoryHisUsuarioxOficina.deleteById(id);
        return true;
    }

    @Override
    public Boolean checkOffice(Long id) {
        if ( !repositoryOficina.findById(Integer.parseInt(id.toString())).isPresent()) return false;
        return true;
    }
}
