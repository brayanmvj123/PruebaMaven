package com.bdb.opaloshare.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;

@Repository
public interface OplMaecdtsCarDownTblRepository extends JpaRepository<MaeCDTSCarEntity, Long> {

	@Query(value="SELECT (SELECT HOMO_CRM FROM OPL_PAR_TIPID_DOWN_TBL WHERE COD_ID != 4 AND HOMO_DCVBTA = TIP_ID ) as TIP_ID,(ltrim(ID_TIT , '0')) AS ID_TIT FROM OPL_CAR_MAECDTS_DOWN_TBL car WHERE NOT EXISTS (SELECT ID_TIT FROM OPL_TMP_MAEDCV_DOWN_TBL WHERE ID_TIT = car.ID_TIT)", nativeQuery=true) 
	List<List<String>> findDif();

}
