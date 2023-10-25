package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_HIS_CLIENTES_LARGE_TBL")
public class HisClientesLargeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="NUM_TIT")
	private String numTit;
	
	@Column(name="OPL_TIPID_TBL_COD_ID")
	private Integer oplTipidTblCodId;
			
	@Column(name="NOM_TIT")
	private String nomTit;
	
	@Column(name="DIR_TIT")
	private String dirTit;
	
	@Column(name="TEL_TIT")
	private String telTit;
	
	@Column(name="FAX_TIT")
	private String faxTit;
	
	@Column(name="EXTENSION")
	private String extension;
	
	@Column(name="CORREO")
	private String correo;
	
	@Column(name="CLASE_PER")
	private String claPer;
		
	@Column(name="DECLA_RENTA")
	private String declaRenta;
	
	@Column(name="IND_EXTRA")
	private String indExtra;
	
	@Column(name="FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	
	@Column(name="PAIS_NACIMIENTO")
	private Integer paisNacimiento;
	
	@Column(name="OPL_TIPCIIU_TBL_COD_CIIU")
	private Integer oplTipciiuTblCodCiiu;
	
	@Column(name="OPL_TIPCIUD_TBL_COD_CIUD")
	private Integer oplTipciudTblCodCiud;
	
	@Column(name="OPL_TIPDEPAR_TBL_COD_DEP")
	private Integer oplTipdeparTblCodDep;
	
	@Column(name="OPL_TIPPAIS_TBL_COD_PAIS")
	private Integer oplTippaisTblCodPais;
	
	@Column(name="OPL_TIPSEGMENTO_TBL_COD_SEGMENTO")
	private Integer oplTipsegmentoTblCodSegmento;
	
	@Column(name="RETENCION")
	private String retencion;

	@Column(name="OPL_PAR_TIPDANE_TBL_COD_DANE")
	private Integer oplParTipdaneTblCodDane;
	
}
