package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_CONDICIONCDTS_LARGE_TBL")
@IdClass(HisCondicionCdtsPK.class)
public class HisCondicionCdtsEntity implements Serializable {

    private BigDecimal capital;
    private Integer rendimientos;
    private Integer plazo;

    @Column(name = "TIP_PLAZO")
    private Integer tipPlazo;

    private Integer base;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "OPL_CTRCDTS_TBL_NUM_CDT", referencedColumnName = "NUM_CDT"),
            @JoinColumn(name = "OPL_CTRCDTS_TBL_NUM_TIT", referencedColumnName = "NUM_TIT"),
            @JoinColumn(name = "OPL_CTRCDTS_TBL_OPL_CONTROLES_TBL_TIP_CONTROL", referencedColumnName = "OPL_CONTROLES_TBL_TIP_CONTROL")
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HisCtrCdtsEntity hisCtrCdtsEntity;

}
