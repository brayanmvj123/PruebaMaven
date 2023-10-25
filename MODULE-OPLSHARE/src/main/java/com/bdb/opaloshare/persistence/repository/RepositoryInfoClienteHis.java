package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisInfoClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryInfoClienteHis extends JpaRepository<HisInfoClienteEntity, String> {
	
	/*
	 * ESTE REPOSITORIO PERTENECE A LA TABLA ACC_ACCIONISTA_HIS_TBL
	 */
	
	/*@Transactional
	@Modifying
	@Query("update HisInfoClienteEntity set estPermV = :estPermV , sociExtrV = :sociExtrV , inverExV = :inverEx ,"
			+ "contribV = :contribV , declaV = :declaV , tarespV = :tarespV , diploV = :diploV , cdiV = :cdiV "
			+ "where idAcc = :idAcc")
	void actualizarCampoEspecialesAcc(@Param("estPermV") String estPermV,@Param("sociExtrV") String sociExtrV,
			@Param("inverEx") String inverEx,@Param("contribV") String contribV,@Param("declaV") String declaV,
			@Param("tarespV") String tarespV,@Param("diploV") String diploV,@Param("cdiV") String cdiV,
			@Param("idAcc") String idAcc);*/

	/*List<HisInfoClienteEntity> findByAccTippaisTblIdOrAccTipdepTblIdOrAccTipciudTblIdOrAccTipsectTblIdOrAccTipciiuTblId
			(String codPais, Integer codDep, Integer codCiud, String codSect , Integer codCree);*/

//	@Transactional
//	@Modifying
//	@Query(value = "update HisInfoClienteEntity set contribV = '1'")
//	void actualizarContribVHisAcc();

}