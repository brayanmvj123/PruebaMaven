package com.bdb.opalossqls.persistence.repository;

import com.bdb.opaloshare.controller.service.interfaces.DataCdtCanceladoDTO;
import com.bdb.opaloshare.controller.service.interfaces.DataCdtRenovadoDTO;
import com.bdb.opalossqls.persistence.entity.RootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositoryCdtTesoreria extends JpaRepository<RootEntity, Serializable> {

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
    List<DataCdtRenovadoDTO> loadInfoCdtRenovados();

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
    List<DataCdtCanceladoDTO> loadInfoCdtCancelados();

}
