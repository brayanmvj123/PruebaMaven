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
@Table(name="OPL_HIS_CDTXCTAINV_LARGE_TBL")
@IdClass(HisCdtxCtainvPk.class)
public class HisCdtxCtainvEntity implements Serializable {

	@Id
	@Column(name="NUM_CDT")
	private Long numCdt;

	@Id
	@Column(name="COD_ISIN")
	private String codIsin;

	@Id
	@Column(name="OPL_CTAINV_TBL_NUM_CTA")
	private String oplCtainvTblNumCta;

    private static final long serialVersionUID = 1L;

}
