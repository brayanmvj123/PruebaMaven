package com.bdb.opaloshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_TRANPG_LARGE_TBL")
public class HisTranpgEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    //@Column(name = "OPL_CDTS_TBL_NUM_CDT")
    //private BigInteger oplCdtsTblNumCdt;

    @Column(name = "PROCESO")
    private String proceso;

    @Column(name = "TIP_TRAN")
    private String tipTran;

    @Column(name = "OPL_TIPTRANS_TBL_TIP_TRANSACCION")
    private Integer oplTiptransTblTipTrasaccion;

    @Column(name = "NRO_PORD_DESTINO")
    private String nroPordDestino;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "ID_BENEFICIARIO")
    private Long idBeneficiario;

    @Column(name = "UNID_ORIGEN")
    private String unidOrigen;

    @Column(name = "UNID_DESTINO")
    private Integer unidDestino;

    @ManyToOne
    @JoinColumn(name = "OPL_CDTS_TBL_NUM_CDT")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private HisCDTSLargeEntity hisCDTSLargeEntity;
}
