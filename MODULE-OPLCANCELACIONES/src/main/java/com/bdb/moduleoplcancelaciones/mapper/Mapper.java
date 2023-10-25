package com.bdb.moduleoplcancelaciones.mapper;

import com.bdb.opaloshare.controller.service.interfaces.CDTVencidoDTO;
import com.bdb.opaloshare.persistence.entity.HisInfoCDTEntity;
import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT.ResponseCdtProxVen;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT.ResponseContingenciaCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.TitularCtaInvDTO;
import org.mapstruct.Mapping;

import java.util.List;


@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Mapping(target = "capPg", expression = "java(cdt.getCapPg().setScale(4))")
    @Mapping(target = "intNeto", expression = "java(cdt.getIntNeto().setScale(4))")
    @Mapping(target = "intTotal", expression = "java(cdt.getIntTotal().setScale(4))")
    @Mapping(target = "rteFte", expression = "java(cdt.getRteFte().setScale(4))")
    @Mapping(target = "gmfIntereses", expression = "java(cdt.getIntNeto().multiply((new java.math.BigDecimal(4)).divide(new java.math.BigDecimal(1000)).round(new java.math.MathContext(0, java.math.RoundingMode.UP))).setScale(4))")
    @Mapping(target = "gmfCapital", expression = "java(java.math.BigDecimal.ZERO.setScale(4))")
    @Mapping(target = "intBruto", expression = "java(cdt.getIntBruto().setScale(4))")
    @Mapping(target = "totalPagar", expression = "java(cdt.getTotalPagar().setScale(4))")
    @Mapping(target = "infoPlazo", expression = "java(new com.bdb.opaloshare.persistence.model.CDTCancelaciones.InfoPlazo(cdt.getTipPlazo(), cdt.getPlazo()))")
    @Mapping(target = "titulares", source = "titularesDTO")
    ResponseCdtProxVen requestQueryCDTToCDTVencidoDTO(CDTVencidoDTO cdt, List<TitularCtaInvDTO> titularesDTO );

    ResponseContingenciaCDT responseContingenciaCDTToResponseCdtProxVen(ResponseCdtProxVen responseCdtProxVen);


    @Mapping(target = "item", expression = "java(null)")
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuario", source = "maeDCVTempDownEntity.idTit")
    @Mapping(target = "canal", source = "canal")
    @Mapping(target = "codCut", constant = " ")
    @Mapping(target = "codTrn", constant = "12345678")
    @Mapping(target = "codProd", source = "codProd")
    @Mapping(target = "mercado", constant = "2")
    @Mapping(target = "unidNegocio", source = "maeDCVTempDownEntity.oficina")
    @Mapping(target = "unidCeo", source = "maeDCVTempDownEntity.oficina")
    @Mapping(target = "formaPago", constant = "2")
    @Mapping(target = "oplDepositanteTblTipDepositante", expression = "java(java.lang.Integer.parseInt(\"1\"))")
    @Mapping(target = "oplOficinaTblNroOficina", expression = "java(java.lang.Integer.parseInt(maeDCVTempDownEntity.getOficina()))")
    @Mapping(target = "oplTipplazoTblTipPlazo", expression = "java(java.lang.Integer.parseInt(maeDCVTempDownEntity.getOplTipplazoTblTipPlazo()))")
    @Mapping(target = "plazo", expression = "java(Integer.parseInt(maeDCVTempDownEntity.getPlazo()))")
    @Mapping(target = "oplTipbaseTblTipBase", expression = "java(java.lang.Integer.parseInt(maeDCVTempDownEntity.getOplTipbaseTblTipBase()))")
    @Mapping(target = "fechaEmi", expression = "java(java.time.LocalDate.parse(maeDCVTempDownEntity.getFechaEmi(), java.time.format.DateTimeFormatter.ofPattern(\"dd-MM-yyyy\")))")
    @Mapping(target = "fechaVen", expression = "java(java.time.LocalDate.parse(maeDCVTempDownEntity.getFechaVen(), java.time.format.DateTimeFormatter.ofPattern(\"dd-MM-yyyy\")))")
    @Mapping(target = "modalidad", constant = " ")
    @Mapping(target = "oplTiptasaTblTipTasa", expression = "java(java.lang.Integer.parseInt(maeDCVTempDownEntity.getOplTiptasaTblTipTasa()))")
    @Mapping(target = "oplTipperiodTblTipPeriodicidad", expression = "java(Integer.parseInt(maeDCVTempDownEntity.getOplTipperiodTblTipPeriodicidad()))")
    @Mapping(target = "spread", source = "maeDCVTempDownEntity.spread")
    @Mapping(target = "signoSpread", constant = "+")
    @Mapping(target = "tasEfe", source = "maeDCVTempDownEntity.tasEfe")
    @Mapping(target = "tasNom", source = "maeDCVTempDownEntity.tasNom")
    @Mapping(target = "moneda", expression = "java(java.math.BigDecimal.valueOf(1.0000))")
    @Mapping(target = "unidUvr", constant = "2")
    @Mapping(target = "cantUnid", expression = "java(java.math.BigDecimal.valueOf(0.0000))")
    @Mapping(target = "valor", source = "maeDCVTempDownEntity.vlrCDT")
    @Mapping(target = "tipTitularidad", constant = "1")
    @Mapping(target = "oplCdtxctainvTblNumCdt", expression = "java(java.lang.Long.valueOf(maeDCVTempDownEntity.getNumCDT()))")
    @Mapping(target = "oplCdtxctainvTblOplCtainvTblNumCta", expression = "java(java.lang.Long.valueOf(maeDCVTempDownEntity.getCtaInv()))")
    @Mapping(target = "oplCdtxctainvTblCodIsin", source = "maeDCVTempDownEntity.codIsin")
    HisInfoCDTEntity maeDCVTempDownEntityToHisInfoCDTEntity(MaeDCVTempDownEntity maeDCVTempDownEntity, String canal, String codProd);

}
