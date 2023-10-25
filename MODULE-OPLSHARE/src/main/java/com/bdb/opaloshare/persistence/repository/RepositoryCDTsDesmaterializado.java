package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownEntity;
import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryCDTsDesmaterializado extends JpaRepository<MaeDCVTempDownEntity, Serializable>{
	
	//public abstract List<CDTsDesmaterializadoEntity> findByIdentAndTipoDoc(Integer ident , Integer tipoDoc);

	@Query("select new com.bdb.opaloshare.persistence.entity.MaeDCVTempDownModel(CDTS.item , CDTS.numCDT , CDTS.vlrCDT , CDTS.fechaVen, "
			+ "CDTS.fechaProxPg, CDTS.oplTiptasaTblTipTasa , TASA.descTasa, CDTS.fechaEmi, CDTS.tasEfe ) "
			+ "from MaeDCVTempDownEntity CDTS "
			+ "inner join HisCDTSLargeEntity HisCDTsDig on CDTS.numCDT = HisCDTsDig.numCdt "
			+ "inner join TiptasaParDownEntity TASA on TASA.tipTasa = CDTS.oplTiptasaTblTipTasa "
			+ "WHERE CDTS.idTit = :idTit "
			+ "and CDTS.oplTipidTblCodId = :tipId")
	List<MaeDCVTempDownModel> consultaCDTsBVBM(String idTit , String tipId);
	
	@Query("select new com.bdb.opaloshare.persistence.entity.MaeDCVTempDownModel(CDTS.item, CDTS.codIsin, CDTS.numCDT, CDTS.idTit , CDTS.oplTipidTblCodId, IDENT.nomId , CDTS.nomTit, CDTS.ctaInv, "+  
			"PLAZO.descPlazo , CDTS.oplTipplazoTblTipPlazo , CDTS.plazo , CDTS.fechaEmi, CDTS.fechaVen, CDTS.vlrCDT, "+
			"CDTS.oplTipbaseTblTipBase , BASE.descBase , CDTS.oplTipperiodTblTipPeriodicidad , " + 
			"PERIOD.descPeriodicidad ," + 
			"CDTS.spread, CDTS.tasNom, CDTS.tasEfe, CDTS.oficina , CDTS.oplTipposicionTblTipPosicion , POSICION.descPosicion , 'ACTIVO' , " + 
			"CDTS.oplTiptasaTblTipTasa, TASA.descTasa , CDTS.fechaProxPg , 'NO EXISTE' , case when count(CDTS.numCDT) > 1 then 'SI' else 'NO' end )  "
			+ "from MaeDCVTempDownEntity CDTS "
			+ "JOIN MaeDCVTempDownEntity B on B.numCDT = CDTS.numCDT "
			+ "INNER JOIN TipplazoParDownEntity PLAZO on PLAZO.tipPlazo = CDTS.oplTipplazoTblTipPlazo "
			+ "INNER JOIN TipbaseParDownEntity BASE on BASE.tipBase = CDTS.oplTipbaseTblTipBase "
			+ "INNER JOIN TipPeriodParDownEntity PERIOD on PERIOD.tipPeriodicidad = CDTS.oplTipperiodTblTipPeriodicidad "
			+ "INNER JOIN TipposicionParDownEntity POSICION on POSICION.tipPosicion = CDTS.oplTipposicionTblTipPosicion "
			+ "INNER JOIN TiptasaParDownEntity TASA on TASA.tipTasa = CDTS.oplTiptasaTblTipTasa "
			+ "INNER JOIN TipidParDownEntity IDENT on IDENT.codId = CDTS.oplTipidTblCodId "
			+ "where (:idTit is null or CDTS.idTit like (:idTit)) and (:numCDT is null or CDTS.numCDT = :numCDT) and (:tipId is null or CDTS.oplTipidTblCodId = :tipId) "
			+ "group by CDTS.item, CDTS.codIsin, CDTS.numCDT, CDTS.idTit , CDTS.oplTipidTblCodId, IDENT.nomId , CDTS.nomTit, CDTS.ctaInv, " + 
			"PLAZO.descPlazo , CDTS.oplTipplazoTblTipPlazo , CDTS.plazo , CDTS.fechaEmi, CDTS.fechaVen, CDTS.vlrCDT, " +
			"CDTS.oplTipbaseTblTipBase , BASE.descBase , CDTS.oplTipperiodTblTipPeriodicidad , " + 
			"PERIOD.descPeriodicidad , " + 
			"CDTS.spread, CDTS.tasNom, CDTS.tasEfe, CDTS.oficina , CDTS.oplTipposicionTblTipPosicion , POSICION.descPosicion , " + 
			"CDTS.oplTiptasaTblTipTasa, TASA.descTasa , CDTS.fechaProxPg ")
	public abstract List<MaeDCVTempDownModel> consultaCDTsAAP(@Param("idTit") String idTit , @Param("numCDT") String numCDT , @Param("tipId") String tipId);
	
	@Query("select new MaeDCVTempDownEntity(cdt.oplTipidTblCodId,cdt.nomTit,cdt.idTit) from MaeDCVTempDownEntity cdt")
	List<MaeDCVTempDownEntity> pruebaSQL();

	@Query("select distinct(cdtTemp.idTit) from MaeDCVTempDownEntity cdtTemp where cdtTemp.idTit like :numTit and cdtTemp.oplTipidTblCodId = :tipId ")
	List<String> cantidadClientesEncontrados(String numTit , String tipId);

	Optional<MaeDCVTempDownEntity> findByNumCDT(String numCdt);

	boolean existsByNumCDT(String numCdt);

}


