package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "OPL_HIS_CTAINVXCLI_MEDIUM_TBL")
@IdClass(HisCtaInvxCliPk.class)
public class HisCtaInvxCliEntity implements Serializable {

    @Column(name = "TITULARIDAD")
    private Integer titularidad;

    @Id
    @Column(name = "OPL_CTAINV_TBL_NUM_CTA")
    private String oplCtainvTblNumCta;

    @Id
    @Column(name = "OPL_INFOCLIENTE_TBL_NUM_TIT", nullable = false)
    private String oplInfoclienteTblNumTit;

}
