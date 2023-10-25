package com.bdb.opalossqls.persistence.repository;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bdb.opalossqls.persistence.entity.DCVSalPdcvlEntity;

public interface RepositoryDCVSalPdcvl extends JpaRepository<DCVSalPdcvlEntity, Serializable> {

	@Transactional
	@Modifying
	@Query("update DCVSalPdcvlEntity set nmbtit2 = :nmbtit2 , nmbtit3 = :nmbtit3 , nmbtit4 = :nmbtit4, "
			+ "tipdoc2 = :tipdoc2 , tipdoc3 = :tipdoc3 , tipdoc4 = :tipdoc4, "
			+ "nrodoc2 = :nrodoc2 , nrodoc3 = :nrodoc3 , nrodoc4 = :nrodoc4 "
			+ "WHERE ctacdtbb = :ctacdtbb")
	public void actualizarClientes(@Param("nmbtit2") String nmbtit2 , @Param("nmbtit3") String nmbtit3 , @Param("nmbtit4") String nmbtit4,
			@Param("tipdoc2") String tipdoc2 , @Param("tipdoc3") String tipdoc3, @Param("tipdoc4") String tipdoc4,
			@Param("nrodoc2") String nrodoc2 , @Param("nrodoc3") String nrodoc3, @Param("nrodoc4") String nrodoc4,
			@Param("ctacdtbb") String ctacdtbb);
}
