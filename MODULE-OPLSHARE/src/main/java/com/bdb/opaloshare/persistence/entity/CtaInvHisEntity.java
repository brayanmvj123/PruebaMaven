package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_CTAINV_MEDIUM_TBL")
public class CtaInvHisEntity implements Serializable {


    @Id
    @Column(name = "NUM_CTA")
    private String numCta;

    @Column(name = "TIP_REG")
    private String tipReg;

    @Column(name = "NOM_CTA")
    private String nomCta;

    @Column(name = "TIP_ID")
    private String tipId;

    @Column(name = "ID_TIT")
    private String idTit;

    @Column(name = "NOM_TIT")
    private String nomTit;

    @Column(name = "IND_RET")
    private Integer indRet;

    @Column(name = "AUT_RET")
    private Integer autRet;

    @Column(name = "DIR")
    private String dir;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "CLA_TIT")
    private Integer claTit;

    @Column(name = "COD_SECT")
    private Long codSect;

    @Column(name = "CAR_TIT")
    private Integer carTit;

    @Column(name = "REL_CTA")
    private Integer relCta;

    @Column(name = "CLAS_CTA")
    private Integer clasCta;

    @Column(name = "CTA_EMB")
    private Integer ctaEmb;

    @Column(name = "EST_CTA")
    private Integer estCta;

    @Column(name = "COD_PAIS")
    private String codPais;

    @Column(name = "COD_DEP")
    private String codDep;

    @Column(name = "COD_CIUD")
    private Integer codCiud;

    @Column(name = "FECHA_INI")
    private Long fechaIni;

    @Column(name = "FECHA_FIN")
    private Long fechaFin;

    @Column(name = "IND_EXTR")
    private Integer indExtr;

    @Column(name = "COD_CREE")
    private Integer codCree;

    private static final long serialVersionUID = 1L;
}
