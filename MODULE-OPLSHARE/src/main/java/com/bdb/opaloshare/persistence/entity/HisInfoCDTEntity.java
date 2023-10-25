package com.bdb.opaloshare.persistence.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_INFOCDT_LARGE_TBL")
public class HisInfoCDTEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "FECHA")
    private LocalDateTime fecha;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "canal")
    private String canal;

    @Column(name = "COD_CUT")
    private String codCut;

    @Column(name = "COD_TRN")
    private String codTrn;

    @Column(name = "COD_PROD")
    private String codProd;

    @Column(name = "MERCADO")
    private String mercado;

    @Column(name = "UNID_NEGOCIO")
    private String unidNegocio;

    @Column(name = "UNID_CEO")
    private String unidCeo;

    @Column(name = "FORMA_PAGO")
    private String formaPago;

    @Column(name = "OPL_DEPOSITANTE_TBL_TIP_DEPOSITANTE")
    private Integer oplDepositanteTblTipDepositante;

    @Column(name = "OPL_OFICINA_TBL_NRO_OFICINA")
    private Integer oplOficinaTblNroOficina;

    @Column(name = "OPL_TIPPLAZO_TBL_TIP_PLAZO")
    private Integer oplTipplazoTblTipPlazo;

    @Column(name = "PLAZO")
    private Integer plazo;

    @Column(name = "OPL_TIPBASE_TBL_TIP_BASE")
    private Integer oplTipbaseTblTipBase;

    @Column(name = "FECHA_EMI")
    private LocalDate fechaEmi;

    @Column(name = "FECHA_VEN")
    private LocalDate fechaVen;

    @Column(name = "MODALIDAD")
    private String modalidad;

    @Column(name = "OPL_TIPTASA_TBL_TIP_TASA")
    private Integer oplTiptasaTblTipTasa;

    @Column(name = "OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD")
    private Integer oplTipperiodTblTipPeriodicidad;

    @Column(name = "SPREAD")
    private BigDecimal spread;

    @Column(name = "SIGNO_SPREAD")
    private String signoSpread;

    @Column(name = "TASA_EFE")
    private BigDecimal tasEfe;

    @Column(name = "TASA_NOM")
    private BigDecimal tasNom;

    @Column(name = "MONEDA")
    private BigDecimal moneda;

    @Column(name = "UNID_UVR")
    private String unidUvr;

    @Column(name = "CANT_UNID")
    private BigDecimal cantUnid;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "TIP_TITULARIDAD")
    private String tipTitularidad;

    @Column(name = "OPL_CDTXCTAINV_TBL_NUM_CDT")
    private Long oplCdtxctainvTblNumCdt;

    @Column(name = "OPL_CDTXCTAINV_TBL_OPL_CTAINV_TBL_NUM_CTA")
    private Long oplCdtxctainvTblOplCtainvTblNumCta;

    @Column(name = "OPL_CDTXCTAINV_TBL_COD_ISIN")
    private String oplCdtxctainvTblCodIsin;

    private static final long serialVersionUID = 1L;
}
