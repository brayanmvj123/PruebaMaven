package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositoryCondicionesCDT extends JpaRepository<HisCDTSLargeEntity, Serializable> {

	@Query("select max(numCdt) from HisCDTSLargeEntity")
	public abstract String maximoCDT();
	
	@Modifying
	@Transactional
	@Query("update HisCDTSLargeEntity set oplEstadosTblTipEstado = 2 where oplEstadosTblTipEstado = 1 "
			+ "and numCdt in (select ctacdtbb from SalPdcvlEntity)")
	void actualizarEstadoCDTS();

	List<HisCDTSLargeEntity> findByNumCdt(String numCdt);

	@Modifying
	@Transactional
	@Query("update HisCDTSLargeEntity set oplEstadosTblTipEstado = 3 where oplEstadosTblTipEstado = 2 ")
	void actualizarEstadoFinalizado();

	Boolean existsByNumCdt(String numCdt);

}
