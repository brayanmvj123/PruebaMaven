package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RepositoryOficina extends JpaRepository<OficinaParDownEntity, Integer> {

    OficinaParDownEntity getByNroOficinaAndOplEstadosTblTipEstado(Integer nroOficina, Integer estado);

    @Transactional
    @Modifying
    @Query(value = "update OficinaParDownEntity ofi set ofi.oplEstadosTblTipEstado = :status where ofi.nroOficina = :closedOffice")
    void updateClosedOffices(@Param("status") Integer status, @Param("closedOffice") Integer closedOffice);

}
