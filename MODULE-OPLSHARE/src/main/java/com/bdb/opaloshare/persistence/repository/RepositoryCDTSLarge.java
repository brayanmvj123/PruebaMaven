package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Repository
public interface RepositoryCDTSLarge extends JpaRepository<HisCDTSLargeEntity, Serializable> {

    @Query("select cdt from HisCDTSLargeEntity cdt inner join SalPdcvlDownEntity pdcvl on cdt.numCdt = pdcvl.ctacdtbb")
    public List<HisCDTSLargeEntity> findAllByPdcvl();

    List<HisCDTSLargeEntity> findByNumCdt(String numCdt);

    @Query("select new com.bdb.opaloshare.persistence.entity.HisCDTSLargeModel(CDTS.numCdt , CDTS.valor , CDTS.fechaVen , "
            + "CDTS.fechaEmi , CDTS.oplTiptasaTblTipTasa , TASA.descTasa, CDTS.fechaEmi, CDTS.tasEfe) "
            + "from HisCDTSLargeEntity CDTS "
            + "JOIN TiptasaParDownEntity TASA on TASA.tipTasa = CDTS.oplTiptasaTblTipTasa "
            + "INNER JOIN HisClixCDTLarge CLIxCDT on CLIxCDT.oplCdtsTblNumCdt = CDTS.numCdt "
            + "INNER JOIN HisClientesLargeEntity CLIENTES on CLIENTES.numTit = CLIxCDT.oplClientesTblNumTit "
            + "INNER JOIN TipidParDownEntity IDENT on IDENT.codId = CLIENTES.oplTipidTblCodId "
            + "WHERE CLIENTES.numTit = :idTit "
            + "and CLIENTES.oplTipidTblCodId = :tipId "
            + "and CDTS.oplEstadosTblTipEstado != 3")
    List<HisCDTSLargeModel> consultaHisCDTsBVBM(@Param("idTit") String idTit, @Param("tipId") Integer tipId);

    @Query("select new com.bdb.opaloshare.persistence.entity.HisCDTSLargeModel('Aun no se ha asignado un codigo ISIN', " +
            "CDTS.numCdt, CLIENTES.numTit , CLIENTES.oplTipidTblCodId, IDENT.nomId , CLIENTES.nomTit, CLIxCDT.ctaInv, " +
            "PLAZO.descPlazo , CDTS.oplTipplazoTblTipPlazo , CDTS.plazo , CDTS.fechaEmi, CDTS.fechaVen, CDTS.valor, " +
            "CDTS.oplTipbaseTblTipBase , BASE.descBase , CDTS.oplTipperiodTblTipPeriodicidad , " +
            "PERIOD.descPeriodicidad ,CDTS.spread, CDTS.tasNom, CDTS.tasEfe, CDTS.oplOficinaTblNroOficina, " +
            "POSICION.tipPosicion , POSICION.descPosicion , ESTADOS.descEstado, " +
            "CDTS.oplTiptasaTblTipTasa, TASA.descTasa , CDTS.fechaEmi , CLIENTES.correo , " +
            "case when count(CLIxCDT.oplCdtsTblNumCdt) > 1 then 'SI' else 'NO' end )  "
            + "from HisCDTSLargeEntity CDTS "
            + "INNER JOIN HisClixCDTLarge CLIxCDT on CLIxCDT.oplCdtsTblNumCdt = CDTS.numCdt "
            + "INNER JOIN HisClientesLargeEntity CLIENTES on CLIENTES.numTit = CLIxCDT.oplClientesTblNumTit "
            + "INNER JOIN TipplazoParDownEntity PLAZO on PLAZO.tipPlazo = CDTS.oplTipplazoTblTipPlazo "
            + "INNER JOIN TipbaseParDownEntity BASE on BASE.tipBase = CDTS.oplTipbaseTblTipBase "
            + "INNER JOIN TipPeriodParDownEntity PERIOD on PERIOD.tipPeriodicidad = CDTS.oplTipperiodTblTipPeriodicidad "
            + "INNER JOIN TipposicionParDownEntity POSICION on POSICION.tipPosicion = 4 "
            + "INNER JOIN TiptasaParDownEntity TASA on TASA.tipTasa = CDTS.oplTiptasaTblTipTasa "
            + "INNER JOIN TipidParDownEntity IDENT on IDENT.codId = CLIENTES.oplTipidTblCodId "
            + "INNER JOIN TipEstadosEntity ESTADOS on ESTADOS.tipEstado = CDTS.oplEstadosTblTipEstado "
            + "where (:numTit is null or CLIENTES.numTit like (:numTit)) and (:numCDT is null or CDTS.numCdt = :numCDT) and (:tipId is null or CLIENTES.oplTipidTblCodId = :tipId) "
            + "and CDTS.oplEstadosTblTipEstado != 3"
            + "group by CDTS.numCdt, CLIENTES.numTit , CLIENTES.oplTipidTblCodId, IDENT.nomId , CLIENTES.nomTit, CLIxCDT.ctaInv, " +
            "PLAZO.descPlazo , CDTS.oplTipplazoTblTipPlazo , CDTS.plazo , CDTS.fechaEmi, CLIENTES.correo ,CDTS.fechaVen, CDTS.valor, " +
            "CDTS.oplTipbaseTblTipBase , BASE.descBase , CDTS.oplTipperiodTblTipPeriodicidad , " +
            "PERIOD.descPeriodicidad , " +
            "CDTS.spread, CDTS.tasNom, CDTS.tasEfe, CDTS.oplOficinaTblNroOficina , POSICION.tipPosicion , POSICION.descPosicion , " +
            "ESTADOS.descEstado,CDTS.oplTiptasaTblTipTasa, TASA.descTasa")
    List<HisCDTSLargeModel> consultaHisCDTsAAP(@Param("numTit") String numTit, @Param("numCDT") String numCDT, @Param("tipId") Integer tipId);

    List<HisCDTSLargeEntity> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

    @Transactional
    @Modifying
    @Query("update HisCDTSLargeEntity CDT set CDT.oplOficinaTblNroOficina = :newOfficeId , CDT.unidCeo = :newOfficeId , " +
            "CDT.unidNegocio = :newOfficeId where CDT.oplOficinaTblNroOficina = :oldOfficeId and CDT.numCdt = :numCdt")
    void updateOffices(@Param("newOfficeId") Integer newOfficeId, @Param("oldOfficeId") Integer oldOfficeId, @Param("numCdt") String numCdt);

}
