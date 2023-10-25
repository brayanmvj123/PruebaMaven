package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.CtaInvCarEntity;
import com.bdb.opaloshare.persistence.model.response.CtaInvCarModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepositoryCtaInvCar extends JpaRepository<CtaInvCarEntity, CtaInvCarEntity> {
	
	/*
	 * ESTE REPOSITORIO PERTENECE A LA TABLA ACC_CTAINV_CAR_TBL
	 */
	
	
	/*HOMOLOGA TIPOS DE DOCUMENTO ACC-DCVSA (PENDIENTE UNIR SENTENCIA) */
//	@Modifying
//	@Transactional
//	@Query("UPDATE CtaInvCarEntity C SET tipId = (SELECT codId FROM TipIdParEntity h WHERE h.homodcvsa = :tipoDocumento ) "
//			+ "WHERE C.tipId = :tipoDocumento ")
//	void actualizarPAP(@Param("tipoDocumento") String tipoDocumento);
//
//	@Modifying
//	@Transactional
//	@Query("UPDATE CtaInvCarEntity C SET tipId = (SELECT codId FROM TipIdParEntity h WHERE h.homodcvsa = CONCAT( :tipoDocumento , :claseTitular ) )"
//			+ "WHERE C.tipId = :tipoDocumento AND C.claTit = :claseTitular ")
//	void actualizarNIT(@Param("tipoDocumento") String tipoDocumento , @Param("claseTitular") int claseTitular);
	
	/*LIMPIA TABLA ACC_CTAINV_CAR_TBL*/
	@Modifying
	@Transactional
	@Query("DELETE FROM CtaInvCarEntity")
	public abstract void borrarContenido();
	
	List<CtaInvCarEntity> findByTipoReg(Integer tipoRegistro);
	
	Page<CtaInvCarEntity> findByTipoReg(Integer tipoRegistro, Pageable pageable);

//	@Query("select distinct(CTA.codCree) from CtaInvCarEntity CTA where CTA.codCree not in (select codCIIU from TipCiiuParEntity)")
//	public abstract List<String> codigosCreeErroneos();

	@Query("select new CtaInvCarEntity(codCree) from CtaInvCarEntity group by codCree")
	List<CtaInvCarEntity> codigosCree();

	@Query("select distinct(codSect) from CtaInvCarEntity group by codSect order by codSect asc")
	List<String> codigosSector();

	@Modifying
	@Transactional
	@Query("update CtaInvCarEntity set codSect = '9999' where codSect is null")
	void modificarCodSectorNulos();

//	@Transactional
//	@Modifying
//	@Query("update CtaInvCarEntity set codCree = (select max(tipCiiu.id) from TipCiiuParEntity tipCiiu where tipCiiu.codCIIU  = :codCREE) where codCree = :codCREE ")
//	void HomologarCodigosCREE(@Param("codCREE") Integer codCREE);

//	@Query("select distinct(CTA.codSect) from CtaInvCarEntity CTA where CTA.codSect not in (select codSect from TipSectParEntity)")
//	List<String> codigosSectorErroneos();

	//HOMOLOGACION CODIGOS PAISES
	@Query("select new CtaInvCarEntity(codPais) from CtaInvCarEntity group by codPais")
	List<CtaInvCarEntity> codigosPais();

	//HOMOLOGACION CODIGOS DEPARTAMENTOS
	@Query("select new CtaInvCarEntity(CTA.codPais , CTA.codDep , CTA.item) from CtaInvCarEntity CTA group by CTA.codPais , CTA.codDep , CTA.item")
	List<CtaInvCarEntity> codigosDepartamento();

//	@Transactional
//	@Modifying
//	@Query(value = "update ACC_CAR_CTAINV_DOWN_TBL CTA set CTA.COD_DEP = 9999 where not REGEXP_LIKE(COD_DEP,'^[[:digit:]]+$')",nativeQuery = true)
//	void codigosDepErroneos();

	//HOMOLOGACION CODIGOS CIUDADES
	@Query("select new CtaInvCarEntity(CTA.codPais , CTA.codDep , CTA.codCiud) from CtaInvCarEntity CTA group by CTA.codPais , CTA.codDep , CTA.codCiud")
	public abstract List<CtaInvCarEntity> codigosCiudad();
	
	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codCree = 9999 where codCree = :codigoCree")
	void ModificarCodigoCreeErroneos(@Param("codigoCree") Integer codigoCree);

	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codSect = 9999 where codSect = :codigoSector")
	void modificarCodSectorErroneos(@Param("codigoSector") Integer codigoSector);

//	@Transactional
//	@Modifying
//	@Query("update CtaInvCarEntity set codSect = (select id from TipSectParEntity where id = :codigoSector ) where codSect = :codigoSector")
//	void homologarCodSector(@Param("codigoSector") Integer codigoSector);

//	@Transactional
//	@Modifying
//	@Query("update CtaInvCarEntity set codPais = 9999 , codDep = 9999 , codCiud = 9999 where codPais not in (select homoDcvsa from TipPaisParEntity)")
//	void conocerPaisesDesconocidos();

//	@Transactional
//	@Modifying
//	@Query("update CtaInvCarEntity set codPais = (select id from TipPaisParEntity where homoDcvsa = :pais) where codPais = :pais")
//	public abstract void HomologarPaises(@Param("pais") String pais);
//
//	@Transactional
//	@Modifying
//	@Query("update CtaInvCarEntity cta set cta.codDep = (select id from TipDepParEntity where accTippaisTblId = :pais AND homoDcvsa = :depar) "
//			+ "where cta.codDep = :depar and cta.codPais = :pais")
//	void HomologarDepartamentos(@Param("pais") Long pais,@Param("depar") Integer depar);

	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity cta set cta.codDep = 9999 where cta.item = :item ")
	void HomologarDepCaracteresErroneos(@Param("item") Long item);
	
//	@Transactional
//	@Modifying
//	@Query("update CtaInvCarEntity set codCiud = (select id from TipCiudParEntity where homoDcvsa = :ciudad AND accTippaisTblId = :pais AND accTipdepTblId = :depar) "
//			+ "where codDep = :deparCta and codPais = :paisCta and codCiud = :ciudadCta")
//	void HomologarCiudades( @Param("ciudad") Integer ciudad, @Param("pais") Long pais , @Param("depar") Long depar ,
//			@Param("deparCta") String deparCta , @Param("paisCta") String paisCta , @Param("ciudadCta") Integer ciudadCta);

	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codPais = '9999' where codDep = '9999'")
	void corregirPais();

	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codDep = '9999' , codPais = '9999' where (codDep is null OR codPais = '9999')")
	void corregirDepartamento();

	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codCiud = 9999 where codDep = '9999' and codPais = '9999'")
	void corregirCiudades();
		
	List<CtaInvCarEntity> findByCodPaisIsNull();

	List<CtaInvCarEntity> findByCodDepIsNull();

	List<CtaInvCarEntity> findByCodCiudIsNull();

	@Modifying
	@Transactional
	@Query("update CtaInvCarEntity set codPais = '9999' WHERE codPais is null")
	void HomologarNulosPais();
	
	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codDep = 9999 WHERE codDep is null")
	public abstract void HomologarNulosDep();
	
	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codCiud = 9999 , codDep = 9999 , codPais = 9999 WHERE codPais is null")
	public abstract void HomologarNulosCiud();
	
	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codDep = '9999' where codDep = '**'")
	public abstract void verificarNumero();

	List<CtaInvCarEntity> findByCodPaisOrCodDepOrCodCiudOrCodSectOrCodCree(String codPais,String codDep,Integer codCiud,
																		   Long codSect , Integer codCree);
	
	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set indExtr = 2 where indExtr is null ")
	public abstract void HomologarNulosIndExt();
	/*@Query("select new CtaInvCarEntity(cta.numCta,cta.codPais,cta.codDep,cta.codCiud) "
			+ "from CtaInvCarEntity cta "
			+ "where (cta.codCiud not in (select codCiud from TipCiudParEntity)"
			+ "	or to_char(concat(replace(substr(cta.codDep,1,1),'0',''),substr(cta.codDep,2,2))) not in (select to_char(accTipdepParTblCodDep) from TipCiudParEntity)"
			+ "	or cta.codPais not in (select accTipdepParTblCodPais from TipCiudParEntity))")
	public abstract List<CtaInvCarEntity> PaisDepCiudDesconocidos();*/

	@Transactional
	@Modifying
	@Query("update CtaInvCarEntity set codCree = 9999 where codCree is null")
	void modificarCodCreeNulos();

	@Query("SELECT CTA.claTit , CTA.idTit, CTA.numCta FROM CtaInvCarEntity CTA")
	List<CtaInvCarModel> consultarDataCtaInvCar();
}
