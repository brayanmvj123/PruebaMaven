package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_CDTS_LARGE_TBL")
public class HisCDTSLargeEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NUM_CDT")
    private String numCdt;

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

    @org.springframework.data.annotation.Version
    @Column(name = "OPL_OFICINA_TBL_NRO_OFICINA")
    private Integer oplOficinaTblNroOficina;

    @Column(name = "OPL_TIPPLAZO_TBL_TIP_PLAZO")
    private Integer oplTipplazoTblTipPlazo;

    //@Column(name = "TIP_PLAZO")
    //private String tipPlazo;

    @Column(name = "PLAZO")
    private Integer plazo;

    @Column(name = "FECHA_EMI")
    private LocalDate fechaEmi;

    @Column(name = "FECHA_VEN")
    private LocalDate fechaVen;

    @Column(name = "OPL_TIPBASE_TBL_TIP_BASE")
    private Integer oplTipbaseTblTipBase;

    @Column(name = "MODALIDAD")
    private String modalidad;

    @Column(name = "OPL_TIPTASA_TBL_TIP_TASA")
    private Integer oplTiptasaTblTipTasa;

    @Column(name = "OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD")
    private Integer oplTipperiodTblTipPeriodicidad;

    @Column(name = "SIGNO_SPREAD")
    private String signoSpread;

    @Column(name = "SPREAD")
    private BigDecimal spread;

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

    @Column(name = "OPL_ESTADOS_TBL_TIP_ESTADO")
    private Integer oplEstadosTblTipEstado;

    @OneToMany(targetEntity = HisTranpgEntity.class,
            mappedBy = "hisCDTSLargeEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<HisTranpgEntity> transacciones;

    public HisCDTSLargeEntity(HisCDTSLargeEntity entity) {
        this.numCdt = entity.getNumCdt();
        this.fecha = entity.getFecha();
        this.usuario = entity.getUsuario();
        this.canal = entity.getCanal();
        this.codCut = entity.getCodCut();
        this.codTrn = entity.getCodTrn();
        this.codProd = entity.getCodProd();
        this.mercado = entity.getMercado();
        this.unidNegocio = entity.getUnidNegocio();
        this.unidCeo = entity.getUnidCeo();
        this.formaPago = entity.getFormaPago();
        this.oplDepositanteTblTipDepositante = entity.getOplDepositanteTblTipDepositante();
        this.oplOficinaTblNroOficina = entity.getOplOficinaTblNroOficina();
        this.oplTipplazoTblTipPlazo = entity.getOplTipplazoTblTipPlazo();
        this.plazo = entity.getPlazo();
        this.fechaEmi = entity.getFechaEmi();
        this.fechaVen = entity.getFechaVen();
        this.oplTipbaseTblTipBase = entity.getOplTipbaseTblTipBase();
        this.modalidad = entity.getModalidad();
        this.oplTiptasaTblTipTasa = entity.getOplTiptasaTblTipTasa();
        this.oplTipperiodTblTipPeriodicidad = entity.getOplTipperiodTblTipPeriodicidad();
        this.signoSpread = entity.getSignoSpread();
        this.spread = entity.getSpread();
        this.tasEfe = entity.getTasEfe();
        this.tasNom = entity.getTasNom();
        this.moneda = entity.getMoneda();
        this.unidUvr = entity.getUnidUvr();
        this.cantUnid = entity.getCantUnid();
        this.valor = entity.getValor();
        this.tipTitularidad = entity.getTipTitularidad();
        this.oplEstadosTblTipEstado = entity.getOplEstadosTblTipEstado();
    }
}
