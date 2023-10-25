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
@Table(name=" OPL_CAR_CTAINVSEC_DOWN_TBL")
public class CtaInvSecCarEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM")
	private Long item;
	
	@Column(name = "TIP_REG" , length = 2)
	private String tipReg;
	
	@Column(name = "NUM_CTA" , length = 10)
	private Long numCta;
	
	@Column(name = "TIP_REL")
	private Integer tipRel;
	
	@Column(name = "TIP_ID" , length = 3)
	private String tipId;
	
	@Column(name = "ID_TIT" , length = 15)
	private String idTit;
	
	@Column(name = "NOM_TIT" , length = 60)
	private String nomTit;
	
	@Column(name = "DIR" , length = 50)
	private String dir;
	
	@Column(name = "TEL" , length = 20)
	private String tel;
	
	@Column(name = "CLA_TIT")
	private Integer claTit;
	
	@Column(name = "COD_SECT" , length = 4)
	private Integer codSect;
	
	@Column(name = "CAR_TIT")
	private String carTit;

	@Column(name="NOM_CTA", length = 50)
	private String nomCta;

	@Column(name="REL_CTA",length = 1)
	private Integer relCta;

	@Column(name="EST_CTA",length = 1)
	private Integer estCta;

}
