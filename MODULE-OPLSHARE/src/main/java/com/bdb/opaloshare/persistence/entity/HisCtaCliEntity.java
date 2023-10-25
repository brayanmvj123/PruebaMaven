package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_HIS_CTACLI_LARGE_TBL")
@IdClass(HisCtaCliPK.class)
public class HisCtaCliEntity implements Serializable {

    @Column(name = "NUM_CTA")
    private String numCta;

    @Column(name = "TIP_CTA")
    private String tipCta;

    @Column(name = "OFI_ORIGEN")
    private Integer ofiOrigen;

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
