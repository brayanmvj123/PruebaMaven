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
@Table(name="OPL_CAR_CTAINV_DOWN_TBL")
public class CtaInvCarEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM")
	private Long item;
	
	@Column(name="TIP_REG")
	private Integer tipoReg;
	
	@Column(name="NUM_CTA")
	private Long numCta;
	
	@Column(name="NOM_CTA")
	private String nomCta;
	
	@Column(name="TIP_ID")
	private String tipId;
	
	@Column(name="ID_TIT" )
	private String idTit;
	
	@Column(name="NOM_TIT")
	private String nomTit;

	@Column(name="AUT_RET")
	private Integer autRet;
	
	@Column(name="DIR")
	private String dir;
	
	@Column(name="TEL")
	private String tel;
	
	@Column(name="CLA_TIT")
	private Integer claTit;
	
	@Column(name="COD_SECT")
	private Long codSect;
	
	@Column(name="CAR_TIT")
	private Integer carTit;
	
	@Column(name="REL_CTA")
	private Integer relCta;
	
	@Column(name="CLAS_CTA")
	private Integer clasCta;
	
	@Column(name="CTA_EMB")
	private Integer ctaEmb;
	
	@Column(name="EST_CTA")
	private Integer estCta;
	
	@Column(name="COD_PAIS")
	private String codPais;
	
	@Column(name="COD_DEP") //ES LONG PERO PRESENTA ERROR EN EL ARCHIVO
	private String codDep;
	
	@Column(name="COD_CIUD")
	private Integer codCiud;
	
	@Column(name="FECHA_INI")
	private Long fechaIni;
	
	@Column(name="FECHA_FIN")
	private Long fechaFin;
	
	@Column(name="IND_EXTR")
	private Integer indExtr;
	
	@Column(name="COD_CREE")
	private Integer codCree;

	@Column(name="IND_RET")
	private Integer indRet;

	public CtaInvCarEntity(Long numCta, String codPais, String codDep, Integer codCiud) {
		this.numCta = numCta;
		this.codPais = codPais;
		this.codDep = codDep;
		this.codCiud = codCiud;
	}
	
	public CtaInvCarEntity(String codPais) {
		this.codPais = codPais;
	}
	
	public CtaInvCarEntity(String codPais, String codDep , Long item) {
		this.codPais = codPais;
		this.codDep = codDep;
		this.item = item;
	}
	
	public CtaInvCarEntity(String codPais, String codDep, Integer codCiud) {
		this.codPais = codPais;
		this.codDep = codDep;
		this.codCiud = codCiud;
	}

	public CtaInvCarEntity(Integer codCree){
		this.codCree = codCree;
	}

    private static final long serialVersionUID = 1L;

}
