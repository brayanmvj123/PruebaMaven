package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.CarCierreOfiEntity;
import com.bdb.opaloshare.persistence.model.columnselected.ColumnsCDTofficesClosed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryCarCierreOfi extends JpaRepository<CarCierreOfiEntity,Long> {

    @Query(value = "select CDT.numCdt as numCdt, CIERREOFI.ofiOri as ofiOri, CIERREOFI.ofiDes as ofiDes " +
            "from HisCDTSLargeEntity CDT " +
            "inner join CarCierreOfiEntity CIERREOFI on function('ltrim',CIERREOFI.ofiOri,'0') = CDT.oplOficinaTblNroOficina " +
            "group by CDT.numCdt, CIERREOFI.ofiOri, CIERREOFI.ofiDes")
    List<ColumnsCDTofficesClosed> knowCDTofficesClosed();

    @Query(value = "select CIERREOFI.ofiOri as ofiOri, CIERREOFI.ofiDes as ofiDes " +
            "from CarCierreOfiEntity CIERREOFI " +
            "inner join HisTranpgEntity TRANPG on function('ltrim', TRANPG.unidOrigen, '0') = function('ltrim',CIERREOFI.ofiOri,'0') " +
            "and function('ltrim', TRANPG.unidDestino, '0') = function('ltrim',CIERREOFI.ofiOri,'0') " +
            "group by CIERREOFI.ofiOri , CIERREOFI.ofiDes ")
    List<ColumnsCDTofficesClosed> knowTranPgOfficesClosed();

}
