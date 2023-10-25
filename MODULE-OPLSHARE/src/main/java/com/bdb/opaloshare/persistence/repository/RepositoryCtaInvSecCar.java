package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.CtaInvSecCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface RepositoryCtaInvSecCar extends JpaRepository<CtaInvSecCarEntity, Serializable> {

//	@Modifying
//	@Transactional
//	@Query("UPDATE CtaInvSecCarEntity C SET C.tipId = (SELECT h.codId FROM TipIdParEntity h WHERE h.homodcvsa = :tipoDocumento ) "
//			+ "WHERE C.tipId = :tipoDocumento ")
//	void homologaTipIdCtaSec(@Param("tipoDocumento") String tipoDocumento);
//
//	@Modifying
//	@Transactional
//	@Query("UPDATE CtaInvSecCarEntity C SET C.tipId = (SELECT h.codId FROM TipIdParEntity h WHERE h.homodcvsa = CONCAT( :tipoDocumento , :claseTitular ) )"
//			+ "WHERE C.tipId = :tipoDocumento AND C.claTit = :claseTitular ")
//	public abstract void homologaTipIdCtaSecNIT(@Param("tipoDocumento") String tipoDocumento , @Param("claseTitular") int claseTitular);

	@Query("select distinct(codSect) from CtaInvSecCarEntity group by codSect order by codSect asc")
	List<String> codigosSectorSec();

//	@Transactional
//	@Modifying
//	@Query("update CtaInvSecCarEntity set codSect = (select id from TipSectParEntity where id = :codigoSector ) where codSect = :codigoSector")
//	void homologarCodSectorSec(@Param("codigoSector") Integer codigoSector);
//
//	@Query("select distinct(CTA.codSect) from CtaInvSecCarEntity CTA where NOT EXISTS (select SECT.codSect " +
//			"from TipSectParEntity SECT where SECT.codSect = CTA.codSect)")
//	List<String> codigosSectorSecErroneos();

	@Modifying
	@Transactional
	@Query("update CtaInvSecCarEntity set codSect = '9999' where codSect = :codigoDesSector or codSect is null")
	void modificarCodSectorErroneosSec(@Param("codigoDesSector") Integer codigoDesSector);

	@Modifying
	@Transactional
	@Query("update CtaInvSecCarEntity set codSect = '9999' where codSect is null")
	void modificarCodSectorNulosSec();

	@Modifying
	@Transactional
	@Query("delete from CtaInvSecCarEntity")
	public abstract void borrarContenido();

}
