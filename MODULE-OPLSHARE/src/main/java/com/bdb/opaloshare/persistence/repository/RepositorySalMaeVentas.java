package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.SalMaeVentasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorySalMaeVentas extends JpaRepository<SalMaeVentasEntity, Long> {

    @Query("select new SalMaeVentasEntity (CDT.fechaEmi, " +
            "CDT.codProd, CDT.numCdt, TRANPG.proceso, 'DIGITAL', CDT.codCut, " +
            "CDT.usuario, CDT.oplOficinaTblNroOficina, TIPID.homoCrm, CLIENTE.numTit) " +
            "from HisCDTSLargeEntity CDT " +
            "inner join HisClixCDTLarge CLIXCDT on CDT.numCdt = CLIXCDT.oplCdtsTblNumCdt " +
            "inner join HisTranpgEntity TRANPG on CLIXCDT.oplCdtsTblNumCdt = TRANPG.hisCDTSLargeEntity.numCdt " +
            "inner join HisClientesLargeEntity CLIENTE on CLIXCDT.oplClientesTblNumTit = CLIENTE.numTit " +
            "inner join TipidParDownEntity TIPID on CLIENTE.oplTipidTblCodId = TIPID.codId " +
            "where function('to_char', CDT.fecha , 'YYYY-MM-DD') = :fecha")
    List<SalMaeVentasEntity> queryMaeVentas(@Param("fecha") String fecha);
}
