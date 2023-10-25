package com.bdb.opaloshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_TRANSACCIONES_LARGE_TBL")
//@IdClass(HisTransaccionesPk.class)
public class HisTransaccionesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM", insertable = false)
    private Long item;


//    @Id
    @Column(name = "OPL_CDTXCTAINV_TBL_NUM_CDT")
    private Long oplCdtxctainvTblNumCdt;

//    @Id
    @Column(name = "OPL_CDTXCTAINV_TBL_OPL_CTAINV_TBL_NUM_CTA")
    private Long oplCdtxctainvTblOplCtainvTblNumCta;

//    @Id
    @Column(name = "OPL_CDTXCTAINV_TBL_COD_ISIN")
    private String oplCdtxctainvTblCodIsin;

    @Column(name = "OPL_TIPPROCESO_TBL_TIP_PROCESO")
    private Integer oplTipprocesoTblTipProceso;

    @Column(name = "OPL_TIPTRANS_TBL_TIP_TRANSACCION")
    private Integer oplTiptransTblTipTransaccion;

    @Column(name = "OPL_TIPTRAN_TBL_TIP_TRAN")
    private Integer oplTiptranTblTipTran;

    @Column(name = "FECHA_TRAN")
    private Date fechaTran;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "NRO_PORD_DESTINO")
    private String nroPordDestino;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "ID_BENEFICIARIO")
    private Long idBeneficiario;

    @Column(name = "UNID_ORIGEN")
    private String unidOrigen;

    @Column(name = "UNID_DESTINO")
    private String unidDestino;

    @Column(name = "CAPITAL_GMF")
    private Integer capitalGmf;

    private static final long serialVersionUID = 1L;

//    @ManyToOne
//    @JoinColumn(name = "OPL_TIPTRAN_TBL_TIP_TRAN")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private TipTranEntity tipTranEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "OPL_TIPPROCESO_TBL_TIP_PROCESO")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private TipProcesoEntity tipProcesoEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "OPLCDTXCTAINV_TBL_NUM_CDT")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private HisCtaInvxCliEntity hisCtaInvxCliEntity;
}
