package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;
import java.util.List;

import com.bdb.opaloshare.controller.service.interfaces.DataCdtCanceladoDTO;
import com.bdb.opaloshare.controller.service.interfaces.DataCdtRenovadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.AcuRenovatesorDownEntity;

@Repository
public interface RepositoryAcuRenovatesorDownEntity extends JpaRepository<AcuRenovatesorDownEntity, Serializable> {
	
	@Query(value=" SELECT * FROM  OPL_ACU_RENOVATESOR_DOWN_TBL WHERE SYS_CARGUE > :menorFecha", nativeQuery=true)
	List<AcuRenovatesorDownEntity> findAllMinusDate(@Param("menorFecha") int menorFecha);

	@Query(value = " SELECT     TRANSASCTIT.TitOld_Asc 'cdtCancelado' ," +
			"                   TRANSASCTIT.TitNew_Asc 'cdtReinvertido'," +
			"                   PG.PgIdCli 'idCliente'," +
			"                   PG.PgNombId 'nombre'," +
			"                   PG.PgFecVen 'fechaCancelacion'," +
			"                   PG.PgFecVen 'fechaReinversion'," +
			"                   '0877' 'nroOficina'," +
			"                   PG.PgRteFte 'retencionFuente'," +
			"                   PG.PgInNeto 'interesCancelado'," +
			"                   PG.PgCapPag 'capitalCancelado'," +
			"                   PG.PgValCap 'valorReinvertido'" +
			"        FROM       DCV_PG_TMP_TBL PG " +
			"                   INNER JOIN DCV_TRANSASCTIT_TMP_TBL TRANSASCTIT ON PG.PgTitTes = TRANSASCTIT.TitOld_Asc " +
			"                   INNER JOIN DCV_TRANSTIT_TMP_TBL TRANSTIT ON TRANSASCTIT.TitNew_Asc = TRANSTIT.NumCdtTran " +
			"		                       AND PG.PgCapPag > 0 " +
			"        ORDER BY   TRANSASCTIT.TitNew_Asc", nativeQuery = true)
	List<DataCdtRenovadoDTO> searchCdtOfiRenovados();

}
