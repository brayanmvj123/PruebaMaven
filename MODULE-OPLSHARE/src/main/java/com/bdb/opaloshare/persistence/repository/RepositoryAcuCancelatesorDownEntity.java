package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;
import java.util.List;

import com.bdb.opaloshare.controller.service.interfaces.DataCdtCanceladoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.AcuCancelatesorDownEntity;


@Repository
public interface RepositoryAcuCancelatesorDownEntity extends JpaRepository<AcuCancelatesorDownEntity, Serializable>  {
	
	@Query(value=" SELECT * FROM  OPL_ACU_CANCELATESOR_DOWN_TBL WHERE SYS_CARGUE > :menorFecha", nativeQuery=true)
	List<AcuCancelatesorDownEntity> findAllMinusDate(@Param("menorFecha") int menorFecha);

	@Query(value = " SELECT  TRANSFRONT._Item 'idTransaccion'," +
			"                PG.PgTitTes 'cdtCancelado' ," +
			"                PG.PgIdCli 'idCliente'," +
			"                PG.PgNombId 'nombre'," +
			"                PG.PgFecVen 'fechaCancelacion'," +
			"                PG._FechaProc 'fechaAbono'," +
			"                '0877' 'nroOficina'," +
			"                CASE " +
			"                   WHEN TRANSFRONT.TipTrans ='1' THEN 'Cuenta Ahorros'" +
			"                   WHEN TRANSFRONT.TipTrans ='2' THEN 'Cuenta Corriente'" +
			"                   WHEN TRANSFRONT.TipTrans ='3' THEN 'Cheque'" +
			"                   WHEN TRANSFRONT.TipTrans ='4' THEN 'Sebra'" +
			"                END AS 'tipoCuenta', " +
			"                TRANSFRONT.NumCtaTran 'numeroCuenta'," +
			"                TRANSFRONT.CliTrans 'idBeneficiario'," +
			"                PG.PgNombId 'nombreBeneficiario'," +
			"                TRANSFRONT.NumVal 'valorAbonado'," +
			"                PG.PgRteFte 'retencionFuente'," +
			"                PG.PgInNeto 'interesCancelado'," +
			"                PG.PgCapPag 'capitalCancelado'" +
			"        FROM    DCV_PG_TMP_TBL PG " +
			"                INNER JOIN DCV_TRANSFRONT_TMP_TBL TRANSFRONT on PG.PgTitTes = TRANSFRONT.TitVen " +
			"                           AND PG.PgCapPag > 0" +
			"                           AND (TRANSFRONT.TipTrans = '1' OR TRANSFRONT.TipTrans = '2') " +
			"        ORDER BY PG.PgTitTes", nativeQuery = true)
	List<DataCdtCanceladoDTO> searchCdtOfiCancelados();


}
