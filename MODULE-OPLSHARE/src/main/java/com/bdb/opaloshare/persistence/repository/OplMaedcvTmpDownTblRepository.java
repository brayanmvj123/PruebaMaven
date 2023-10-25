package com.bdb.opaloshare.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownEntity;

@Repository
public interface OplMaedcvTmpDownTblRepository extends JpaRepository<MaeDCVTempDownEntity, Long> {

    @Query(value = "SELECT (SELECT HOMO_CRM FROM OPL_PAR_TIPID_DOWN_TBL WHERE COD_ID != 4 AND COD_ID = OPL_TIPID_TBL_COD_ID ) as TIP_ID,(ltrim(ID_TIT , '0')) AS ID_TIT FROM OPL_TMP_MAEDCV_DOWN_TBL tmp WHERE NOT EXISTS (SELECT ID_TIT FROM OPL_CAR_MAECDTS_DOWN_TBL WHERE ID_TIT = tmp.ID_TIT)", nativeQuery = true)
    List<List<String>> findDif();

    @Query(value = " select * from opl_tmp_maedcv_down_tbl WHERE num_cdt = :numCDT and ltrim(id_tit,'0') = :idTit", nativeQuery = true)
    List<MaeDCVTempDownEntity> findByNumCdt(String numCDT, String idTit);

    @Query(value = "SELECT  * " +
            "FROM OPL_TMP_MAEDCV_DOWN_TBL MAE " +
            "WHERE ltrim(MAE.NUM_CDT, 0) = ltrim(:numCdt, 0) and ltrim(MAE.CTA_INV,0) = ltrim(:ctaInv, 0) " +
            "", nativeQuery = true)
    MaeDCVTempDownEntity findByNumCDTAndCtainv(@Param("numCdt") String numCdt, @Param("ctaInv") String ctaInv);

}
