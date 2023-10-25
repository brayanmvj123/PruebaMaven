package com.bdb.oplbacthdiarios.mapper;

import com.bdb.opaloshare.persistence.entity.AjusLiqDiaEntity;
import com.bdb.opaloshare.persistence.entity.HisPgEntity;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.model.columnselected.ColumnsReportPgWeekly;
import com.bdb.opaloshare.persistence.model.jsonschema.calculadoradias.JSONRequestCalculadoraDias;
import com.bdb.opaloshare.persistence.model.jsonschema.diaria.JSONGetSalPgDiaria;
import com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo.ParametersFechaInicioPeriodo;
import com.bdb.opaloshare.persistence.model.jsonschema.semanal.JSONGetSalPgSemanal;
import com.bdb.opaloshare.persistence.model.jsonschema.simuladorcuota.ParametersSimuladorCuota;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.ParametersTasaVariable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperReportPg {

    /**
     * Cruce entre los CDTs Desmaterializados emisor y depositante.
     * Importante: Para solicitud de cancelación automatica de CDTs Digitales el codigo estado debe estar con valor de 4
     * , para cuando salga la solicitud de conciliación de CDTs Digitales y oficina se debe cambiar el estado en 0.
     * @param columnsReportPgWeekly
     * @return SalPgDownEntity
     */
    @Mapping(source = "nroOficina", target = "nroOficina")
    @Mapping(source = "depositante", target = "depositante")
    @Mapping(source = "numCdt", target = "numCdt")
    @Mapping(source = "codIsin", target = "codIsin")
    @Mapping(source = "ctaInv", target = "ctaInv")
    @Mapping(source = "codId", target = "codId")
    @Mapping(source = "numTit", target = "numTit")
    @Mapping(source = "nomTit", target = "nomTit")
    @Mapping(source = "tipPlazo", target = "tipPlazo")
    @Mapping(source = "plazo", target = "plazo")
    @Mapping(source = "tipBase", target = "tipBase")
    @Mapping(source = "tipPeriodicidad", target = "tipPeriodicidad")
    @Mapping(source = "tipTasa", target = "tipTasa")
    @Mapping(source = "tasaEfe", target = "tasaEfe")
    @Mapping(source = "tasaNom", target = "tasaNom")
    @Mapping(source = "spread", target = "spread")
    @Mapping(source = "valorNominal", target = "valorNominal")
    @Mapping(source = "intBruto", target = "intBruto")
    @Mapping(source = "rteFte", target = "rteFte")
    @Mapping(source = "intNeto", target = "intNeto")
    @Mapping(source = "capPg", target = "capPg")
    @Mapping(source = "totalPagar", target = "totalPagar")
    @Mapping(source = "tipPosicion", target = "tipPosicion")
    @Mapping(target = "fechaEmi", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaEmi()))")
    @Mapping(target = "fechaVen", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaVen()))")
    @Mapping(target = "fechaProxPg", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaProxPg()).toString())")
    @Mapping(target = "factorDcvsa", expression = "java(new java.math.BigDecimal(columnsReportPgWeekly.getFactorDcvsa()).movePointLeft(6))")
    @Mapping(target = "factorOpl", expression = "java(new java.math.BigDecimal(0))")
    @Mapping(target = "estado", constant = "4")
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "codProd", target = "codProd")
    SalPgDownEntity toColumnsReportPgWeekly(ColumnsReportPgWeekly columnsReportPgWeekly);

    List<HisPgEntity> listToSalpgtoHisPg(List<SalPgDownEntity> columnsReportPgWeekly);

    @Mapping(source = "numCdt", target = "oplPgTblNumCdt")
    @Mapping(source = "codIsin", target = "oplPgTblCodIsin")
    @Mapping(source = "ctaInv", target = "oplPgTblCtaInv")
    @Mapping(source = "numTit", target = "oplPgTblNumTit")
    AjusLiqDiaEntity tooSalpgtoAjusLiqDia(SalPgDownEntity columnsReportPgWeekly);

    List<AjusLiqDiaEntity> listToSalpgtoAjusLiqDia(List<SalPgDownEntity> columnsReportPgWeekly);

    @Mapping(source = "tipBase", target = "base")
    @Mapping(target = "tipoPlazo", expression = "java(java.lang.Integer.parseInt(salPg.getTipPlazo()))")
    @Mapping(target = "fechaVencimiento", expression = "java( salPg.getFechaVen().format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy/MM/dd\")))")
    @Mapping(target = "fechaProxPago", expression = "java(java.time.LocalDate.parse(salPg.getFechaProxPg(), java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd\")).format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy/MM/dd\")))")
    @Mapping(source = "tipPeriodicidad", target = "periodicidad")
    ParametersFechaInicioPeriodo salPgToParamsFechaInicioPeriodo(SalPgDownEntity salPg);


    @Mapping(target = "tipotasa", expression = "java(java.lang.Integer.parseInt(tipoTasa))")
    @Mapping(target = "fecha", expression = "java(fechaEmi.format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy/MM/dd\")))")
    ParametersTasaVariable salPgToParamsTasaVariable(String tipoTasa, LocalDate fechaEmi);

    @Mapping(source = "salPg.tipBase", target = "base")
    @Mapping(source = "salPg.capPg", target = "capital")
    @Mapping(source = "salPg.tipPlazo", target = "tipoPlazo")
    @Mapping(source = "diasPlazo", target = "diasPlazo")
    @Mapping(source = "salPg.tipPeriodicidad", target = "periodicidad")
    @Mapping(source = "retencion", target = "retencion")
    @Mapping(source = "tasa", target = "tasa")
    @Mapping(source = "salPg.tipTasa", target = "tipoTasa")
    @Mapping(source = "salPg.spread", target = "spread")
    ParametersSimuladorCuota salPgToParametersSimuladorCuota(SalPgDownEntity salPg, String tasa, String diasPlazo, String retencion);

    @Mapping(source = "base", target = "base")
    @Mapping(target = "fechaApertura", expression = "java(fechaEmi.format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy/MM/dd\")))")
    @Mapping(target = "fechaVencimiento", expression = "java(fechaVen.format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy/MM/dd\")))")
    JSONRequestCalculadoraDias salPgToJSONRequestCalculadoraDias(Integer base, LocalDate fechaEmi, LocalDate fechaVen);

    @Mapping(target = "fechaEmi", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaEmi(), java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss.s\")))")
    @Mapping(target = "fechaVen", expression = "java(java.time.LocalDate.parse(columnsReportPgWeekly.getFechaVen(), java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss.s\")))")
    JSONGetSalPgDiaria columnsReportPgWeeklytoJSONGetSalPgDiaria(ColumnsReportPgWeekly columnsReportPgWeekly);

    List<JSONGetSalPgDiaria> listColumnsReportPgWeeklytoJSONGetSalPgDiaria(List<ColumnsReportPgWeekly> columnsReportPgWeekly);
}
