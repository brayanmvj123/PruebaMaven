package com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT;

import com.bdb.opaloshare.persistence.model.CDTCancelaciones.InfoPlazo;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.TitularCtaInvDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCdtProxVen implements Serializable {

    //    @JsonIgnore
    private  Long numCdt;

    //    @JsonIgnore
    private String codIsin;

    //@JsonIgnore
    private Integer relCta;

    @JsonIgnore
    private String plazo;

    @JsonIgnore
    private String tipPlazo;

    private InfoPlazo infoPlazo;

    private String fechaVen;

    private String tipProd;

    private List<TitularCtaInvDTO> titulares;

    private BigDecimal capPg;

    private BigDecimal intNeto;

    private BigDecimal intTotal;

    private BigDecimal rteFte;

    private BigDecimal gmfIntereses;

    private BigDecimal gmfCapital;

//    @JsonIgnore
    private BigDecimal intBruto;

//    @JsonIgnore
    private BigDecimal totalPagar;

    @JsonIgnore
    private Integer oficina;

    @JsonIgnore
    private String nomOficina;

}
