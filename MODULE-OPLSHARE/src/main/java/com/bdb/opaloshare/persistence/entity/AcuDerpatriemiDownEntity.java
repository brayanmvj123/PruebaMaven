package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_ACU_DERPATRIEMI_DOWN_TBL")
public class AcuDerpatriemiDownEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "COD_EMISOR")
    private String codEmisor;

    @Column(name = "ISIN")
    private String isin;

    @Column(name = "COD_DEP")
    private String codDep;

    @Column(name = "TIP_DER")
    private String tipDer;

    @Column(name = "FECHA_VENC")
    private String fechaVenc;

    @Column(name = "CTA_INV")
    private String ctaInv;

    @Column(name = "TIP_ID")
    private String tipId;

    @Column(name = "ID_TIT")
    private String idTit;

    @Column(name = "NOM_TIT")
    private String nomTit;

    @Column(name = "SAL_CONT")
    private String salCont;

    @Column(name = "COB_DIV_EFE")
    private String cobDivEfe;

    @Column(name = "COB_DIV_ACC")
    private String cobDivAcc;

    @Column(name = "COB_CAP")
    private String cobCap;

    @Column(name = "COB_REND")
    private String cobRend;

    @Column(name = "REINV")
    private String reinv;

    @Column(name = "REC_CAP")
    private String recCap;

    @Column(name = "REC_DIV_ACC")
    private String recDivAcc;

    @Column(name = "REC_REND")
    private String recRend;

    @Column(name = "RTE_FTE")
    private String rteFte;

    @Column(name = "ENAJENACION")
    private String enajenacion;

    @Column(name = "ADMIN_DCVL")
    private String adminDcvl;

    @Column(name = "PREIMPRESO")
    private String preimpreso;

    @Column(name = "FECHA_INI")
    private String fechaIni;

    @Column(name = "FECHA_FIN")
    private String fechaFin;

    @Column(name = "TAS_EFE")
    private String tasEfe;

    @Column(name = "FACTOR")
    private String factor;

    @Column(name = "IMP_ICA")
    private String impIca;

    @Column(name = "IMP_CRE")
    private String impCre;

    @Column(name = "IMP_ADI")
    private String impAdi;

    @Column(name = "DEPOSITANTE_V")
    private String depositanteV;

}
