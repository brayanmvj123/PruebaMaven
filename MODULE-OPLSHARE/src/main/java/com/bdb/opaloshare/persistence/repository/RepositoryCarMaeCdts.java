package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;

public interface RepositoryCarMaeCdts extends JpaRepository<MaeCDTSCarEntity, Serializable> {
		
	@Transactional
	@Modifying
	@Query("update MaeCDTSCarEntity c set tipId = (SELECT max(codId) FROM TipidParDownEntity h WHERE h.homoDcvbta = :tipoDocumento ) " +
			"WHERE c.tipId = :tipoDocumento  ")
	void homologarCodId(String tipoDocumento);
	
	@Transactional
	@Modifying
	@Query("update MaeCDTSCarEntity set tipId = 4 where tip_id = 5 and (id_tit < 8000000000 or id_tit > 9999999999)")
	void homologarCodIdNit();

	@Transactional
	@Modifying
	@Query("update MaeCDTSCarEntity c set tipPlazo = (SELECT tipPlazo FROM TipplazoParDownEntity h WHERE h.homoDcvbta = :tipoPlazo ) " + 
			"WHERE c.tipPlazo = :tipoPlazo  ")
	void homologarTipPlazo(String tipoPlazo);
	
	@Transactional
	@Modifying
	@Query("update MaeCDTSCarEntity c set tipBase = (SELECT tipBase FROM TipbaseParDownEntity h WHERE h.homoDcvbta = :tipoBase ) " + 
			"WHERE c.tipBase = :tipoBase  ")
	void homologarTipBase(String tipoBase);
	
	@Transactional
	@Modifying
	@Query("update MaeCDTSCarEntity c set c.tipPeriod = (SELECT tipPeriodicidad FROM TipPeriodParDownEntity h WHERE h.homoDcvbta = :tipoPeriodicidad ) " +
			"WHERE c.tipPeriod = :tipoPeriodicidad  ")
	void homologarTipPeriodicidad(String tipoPeriodicidad);
	
	@Transactional
	@Modifying
	@Query("update MaeCDTSCarEntity c set tipTasa = (SELECT tipTasa FROM TiptasaParDownEntity h WHERE h.homoDcvbta = :tipoTasa ) " + 
			"WHERE c.tipTasa = :tipoTasa  ")
	void homologarTipTasa(String tipoTasa);
	
	@Transactional
	@Modifying
	@Query("update MaeCDTSCarEntity c set posicion = (SELECT tipPosicion FROM TipposicionParDownEntity h WHERE h.homoDcvbta = :tipoPosicion ) " +
			"WHERE c.posicion = :tipoPosicion  ")
	void homologarTipPosicion(String tipoPosicion);

	@Transactional
	@Modifying
	@Query(value = "update MaeCDTSCarEntity c set c.numCDT = concat('80000',c.numCDT) where length(c.numCDT) = 9 and substring(c.numCDT,1,1) = '7' ")
	void actualizarNumCdtsinRelleno();

	@Transactional
	@Modifying
	@Query(value = "update MaeCDTSCarEntity c set c.numCDT = concat(function('replace',substring(c.numCDT,1,1),substring(c.numCDT,1,1),8),substring(c.numCDT,2,13)) " +
			"where substring(c.numCDT,6,1) = '7' and substring(c.numCDT,1,1) = '0' and length(c.numCDT) = 14 ")
	void actualizarNumCdtconRelleno();

}
