package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisCtrCdtsEntity;
import com.bdb.opaloshare.persistence.entity.HisCtrCdtsPk;
import com.bdb.opaloshare.persistence.entity.ParControlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryHisCtrCdts extends JpaRepository<HisCtrCdtsEntity, HisCtrCdtsPk> {

    List<HisCtrCdtsEntity> findByNumCdtAndNumTit(Long numCdt, String numTit);

    List<HisCtrCdtsEntity> findByNumCdt(Long numCdt);

    List<HisCtrCdtsEntity> findByNumCdtAndParControlesEntity(Long numCdt, ParControlesEntity control);

    @Modifying
    @Query(value = "update opl_his_ctrcdts_large_tbl ctrCdt set ctrcdt.fecha_creacion = :fechaCreacion , " +
            "ctrcdt.novedad_v = :novedadV , " +
            "ctrcdt.fecha_modificacion = :fechaModificacion ," +
            "ctrcdt.descripcion = :descripcion , " +
            "ctrcdt.opl_controles_tbl_tip_control = :parControlesEntity , " +
            "ctrcdt.cod_cut = :codCut " +
            "where ctrcdt.num_cdt = :numCdt " +
            "AND ctrCdt.NUM_TIT = :numTit " +
            "AND ctrCdt.OPL_CONTROLES_TBL_TIP_CONTROL = :control ", nativeQuery = true)
    void updateByNumCdt(LocalDateTime fechaCreacion, Integer novedadV, LocalDateTime fechaModificacion,
                        String descripcion, int parControlesEntity, String codCut,
                        Long numCdt, String numTit, Long control);

}
