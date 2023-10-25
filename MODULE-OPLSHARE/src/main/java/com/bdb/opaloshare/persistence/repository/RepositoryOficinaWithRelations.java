package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.OficinaParWithRelationsDownEntity;
import com.bdb.opaloshare.persistence.model.office.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RepositoryOficinaWithRelations extends JpaRepository<OficinaParWithRelationsDownEntity, Integer> {

    List<OficinaParWithRelationsDownEntity> findAllByOplEstadosTblTipEstado(Integer estado);

    @Query(value = "SELECT  " +
            "       distinct OFICINA.NRO_OFICINA, " +
            "       OFICINA.NRO_OFICINA as nroOficina," +
            "       DESC_OFICINA as descOficina, " +
            "       OPL_TIPOFICINA_TBL_TIP_OFICINA as oplTipoficinaTblTipOficina, " +
            "       OPL_OFICINA_TBL_NRO_OFICINA as oplOficinaTblnroOficina, " +
            "       OPL_ESTADOS_TBL_TIP_ESTADO as oplEstadosTblTipEstado " +
            "FROM OPL_PAR_OFICINA_DOWN_TBL OFICINA " +
            "   INNER JOIN OPL_SAL_PGSEMANAL_DOWN_TBL SALPG_SEMANAL ON OFICINA.NRO_OFICINA = SALPG_SEMANAL.NRO_OFICINA " +
            "   LEFT JOIN OPL_HIS_USUARIOXOFICINA_LARGE_TBL USUARIOXOFICINA " +
            "   ON USUARIOXOFICINA.NRO_OFICINA = SALPG_SEMANAL.NRO_OFICINA " +
            "WHERE USUARIOXOFICINA.NRO_OFICINA is null " +
            "   AND SALPG_SEMANAL.TIP_POSICION <> 2 " +
            "   AND SALPG_SEMANAL.TIP_POSICION <> 4 " +
            "   ORDER BY NRO_OFICINA" +
            "", nativeQuery = true)
    List<Office> officesInLiquidationWeeklyWithoutEmailUser();

    @Query(value = "SELECT  " +
            "       distinct OFICINA.NRO_OFICINA, " +
            "       OFICINA.NRO_OFICINA as nroOficina," +
            "       DESC_OFICINA as descOficina, " +
            "       OPL_TIPOFICINA_TBL_TIP_OFICINA as oplTipoficinaTblTipOficina, " +
            "       OPL_OFICINA_TBL_NRO_OFICINA as oplOficinaTblnroOficina, " +
            "       OPL_ESTADOS_TBL_TIP_ESTADO as oplEstadosTblTipEstado " +
            "FROM OPL_PAR_OFICINA_DOWN_TBL OFICINA " +
            "   INNER JOIN OPL_SAL_PG_DOWN_TBL SALPG ON OFICINA.NRO_OFICINA = SALPG.NRO_OFICINA " +
            "   LEFT JOIN OPL_HIS_USUARIOXOFICINA_LARGE_TBL USUARIOXOFICINA " +
            "   ON USUARIOXOFICINA.NRO_OFICINA = SALPG.NRO_OFICINA " +
            "WHERE USUARIOXOFICINA.NRO_OFICINA is null " +
            "   AND SALPG.TIP_POSICION <> 2 " +
            "   AND SALPG.TIP_POSICION <> 4 " +
            "   ORDER BY NRO_OFICINA" +
            "", nativeQuery = true)
    List<Office> officesInLiquidationDailyWithoutEmailUser();

    @Transactional
    @Modifying
    @Query(value = "update OficinaParDownEntity ofi set ofi.oplEstadosTblTipEstado = :status where ofi.nroOficina = :closedOffice")
    void updateClosedOffices(@Param("status") Integer status, @Param("closedOffice") Integer closedOffice);
}
