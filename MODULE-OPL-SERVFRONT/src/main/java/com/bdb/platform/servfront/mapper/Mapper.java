package com.bdb.platform.servfront.mapper;

import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.columnselected.ColumnsReportPgWeekly;
import com.bdb.opaloshare.persistence.model.jsonschema.semanal.JSONGetSalPgSemanal;
import com.bdb.opaloshare.persistence.model.office.Office;
import com.bdb.opaloshare.persistence.model.office.ResponseOffice;
import com.bdb.opaloshare.persistence.model.office.ResponseOfficeDetail;
import com.bdb.opaloshare.persistence.model.userbyoffice.RequestUserByOffice;
import com.bdb.opaloshare.persistence.model.userbyoffice.ResponseUserByOffice;
import com.bdb.platform.servfront.model.JSONSchema.JSONGetSalPgDiaria;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPg;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPgSemanal;
import org.mapstruct.Mapping;

import java.util.List;


@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Mapping(target = "fechaEmi", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaEmi(), java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss.s\")))")
    @Mapping(target = "fechaVen", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaVen(), java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss.s\")))")
    JSONGetSalPgDiaria columnsReportPgWeeklytoJSONzGetSalPgDiaria(ColumnsReportPgWeekly columnsReportPgWeekly);

    List<JSONGetSalPgDiaria> listColumnsReportPgWeeklytoJSONGetSalPgDiaria(List<ColumnsReportPgWeekly> columnsReportPgWeekly);


    @Mapping(target = "fechaEmi", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaEmi(), java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss.s\")))")
    @Mapping(target = "fechaVen", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaVen(), java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss.s\")))")
    JSONGetSalPgSemanal columnsReportPgWeeklytoJSONGetSalPgSemanal(ColumnsReportPgWeekly columnsReportPgWeekly);

    List<JSONGetSalPgSemanal> listColumnsReportPgWeeklytoJSONGetSalPgDSemanal(List<ColumnsReportPgWeekly> columnsReportPgWeekly);

    HisPgEntity salpgtoHisPg(SalPgDownEntity salPg);

    @Mapping(target = "item", source = "salPgRequest.item")
    @Mapping(target = "nroOficina", source = "salPgDatabase.nroOficina")
    @Mapping(target = "depositante", source = "salPgDatabase.depositante")
    @Mapping(target = "numCdt", source = "salPgRequest.numCdt")
    @Mapping(target = "codIsin", source = "salPgRequest.codIsin")
    @Mapping(target = "ctaInv", source = "salPgRequest.ctaInv")
    @Mapping(target = "codId", source = "salPgDatabase.codId")
    @Mapping(target = "numTit", source = "salPgRequest.numTit")
    @Mapping(target = "nomTit", source = "salPgDatabase.nomTit")
    @Mapping(target = "fechaEmi", source = "salPgDatabase.fechaEmi")
    @Mapping(target = "fechaVen", source = "salPgDatabase.fechaVen")
    @Mapping(target = "fechaProxPg", source = "salPgDatabase.fechaProxPg")
    @Mapping(target = "tipPlazo", source = "salPgRequest.tipPlazo")
    @Mapping(target = "plazo", source = "salPgRequest.plazo")
    @Mapping(target = "tipBase", source = "salPgRequest.tipBase")
    @Mapping(target = "tipPeriodicidad", source = "salPgRequest.tipPeriodicidad")
    @Mapping(target = "tipTasa", source = "salPgRequest.tipTasa")
    @Mapping(target = "tasaEfe", source = "salPgRequest.tasaEfe")
    @Mapping(target = "tasaNom", source = "salPgRequest.tasaNom")
    @Mapping(target = "spread", source = "salPgRequest.spread")
    @Mapping(target = "valorNominal", source = "salPgRequest.valorNominal")
    @Mapping(target = "intBruto", source = "salPgRequest.intBruto")
    @Mapping(target = "rteFte", source = "salPgRequest.rteFte")
    @Mapping(target = "intNeto", source = "salPgRequest.intNeto")
    @Mapping(target = "capPg", source = "salPgRequest.capPg")
    @Mapping(target = "totalPagar", source = "salPgRequest.totalPagar")
    @Mapping(target = "tipPosicion", source = "salPgDatabase.tipPosicion")
    @Mapping(target = "factorDcvsa", source = "salPgRequest.factorDcvsa")
    @Mapping(target = "factorOpl", source = "salPgRequest.factorOpl")
    @Mapping(target = "codProd", source = "salPgDatabase.codProd")
    @Mapping(target = "estado", source = "salPgDatabase.estado")
    @Mapping(target = "fecha", source = "salPgDatabase.fecha")
    SalPgDownEntity salPgRequestAndSalpgDbtoSalPgEntity(SalPgDownEntity salPgDatabase, RequestSalPg salPgRequest);



    @Mapping(target = "item", source = "salPgRequest.item")
    @Mapping(target = "nroOficina", source = "salPgDatabase.nroOficina")
    @Mapping(target = "depositante", source = "salPgDatabase.depositante")
    @Mapping(target = "numCdt", source = "salPgRequest.numCdt")
    @Mapping(target = "codIsin", source = "salPgRequest.codIsin")
    @Mapping(target = "ctaInv", source = "salPgRequest.ctaInv")
    @Mapping(target = "codId", source = "salPgDatabase.codId")
    @Mapping(target = "numTit", source = "salPgRequest.numTit")
    @Mapping(target = "nomTit", source = "salPgDatabase.nomTit")
    @Mapping(target = "fechaEmi", source = "salPgDatabase.fechaEmi")
    @Mapping(target = "fechaVen", source = "salPgDatabase.fechaVen")
    @Mapping(target = "fechaProxPg", source = "salPgDatabase.fechaProxPg")
    @Mapping(target = "tipPlazo", source = "salPgRequest.tipPlazo")
    @Mapping(target = "plazo", source = "salPgRequest.plazo")
    @Mapping(target = "tipBase", source = "salPgRequest.tipBase")
    @Mapping(target = "tipPeriodicidad", source = "salPgRequest.tipPeriodicidad")
    @Mapping(target = "tipTasa", source = "salPgRequest.tipTasa")
    @Mapping(target = "tasaEfe", source = "salPgRequest.tasaEfe")
    @Mapping(target = "tasaNom", source = "salPgRequest.tasaNom")
    @Mapping(target = "spread", source = "salPgRequest.spread")
    @Mapping(target = "valorNominal", source = "salPgRequest.valorNominal")
    @Mapping(target = "intBruto", source = "salPgRequest.intBruto")
    @Mapping(target = "rteFte", source = "salPgRequest.rteFte")
    @Mapping(target = "intNeto", source = "salPgRequest.intNeto")
    @Mapping(target = "capPg", source = "salPgRequest.capPg")
    @Mapping(target = "totalPagar", source = "salPgRequest.totalPagar")
    @Mapping(target = "tipPosicion", source = "salPgDatabase.tipPosicion")
    @Mapping(target = "factorDcvsa", source = "salPgRequest.factorDcvsa")
    @Mapping(target = "factorOpl", source = "salPgRequest.factorOpl")
    @Mapping(target = "codProd", source = "salPgDatabase.codProd")
    @Mapping(target = "estado", source = "salPgDatabase.estado")
    @Mapping(target = "fecha", source = "salPgDatabase.fecha")
    SalPgSemanalDownEntity salPgRequestAndSalpgDbtoSalPgSemanalEntity(SalPgSemanalDownEntity salPgDatabase, RequestSalPgSemanal salPgRequest);


    HisUsuarioxOficinaEntity requestUserByOfficeToHisUsuarioxOficina(RequestUserByOffice requestUserByOffice);

    ResponseOffice hisOficinaParToResponseOffice(OficinaParWithRelationsDownEntity hisUsuarioxOficina);

    List<ResponseOffice> listHisOficinaParToResponseOffice(List<OficinaParWithRelationsDownEntity> hisUsuarioxOficina);

    ResponseOfficeDetail hisOficinaParToResponseOfficeDetail(OficinaParWithRelationsDownEntity oficinaParWithRelationsDownEntity);

    List<ResponseOfficeDetail> listHisOficinaParToResponseOfficeDetail(List<OficinaParWithRelationsDownEntity> oficinaParWithRelationsDownEntities);

    RequestUserByOffice hisUsuarioxOficinaToRequestHisUsuarioxOficina(HisUsuarioxOficinaEntity hisUsuarioxOficinaEntity);

    List<RequestUserByOffice> listHisUsuarioxOficinaToRequestHisUsuarioxOficina(List<HisUsuarioxOficinaEntity> hisUsuarioxOficinaEntity);

    ResponseUserByOffice hisUsuarioxOficinaToResponseHisUsuarioxOficina(HisUsuarioxOficinaEntity hisUsuarioxOficinaEntity);

    List<ResponseUserByOffice> listHisUsuarioxOficinaToResponseHisUsuarioxOficina(List<HisUsuarioxOficinaEntity> hisUsuarioxOficinaEntity);

    OficinaParWithRelationsDownEntity officeToOficinaParEntity(Office office);

    List<OficinaParWithRelationsDownEntity> listOfficeToOficinaParEntity(List<Office> officeList);
}
