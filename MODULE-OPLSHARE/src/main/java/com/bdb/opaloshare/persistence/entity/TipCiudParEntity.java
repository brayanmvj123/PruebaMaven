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
@Table(name="ACC_PAR_TIPCIUD_DOWN_TBL")
@IdClass(TipCiudParPK.class)
public class TipCiudParEntity implements Serializable {
	
	/**
	 * ESTE ENTITY PERTENECE A LA TABLA ACC_TIPCIUD_PAR_TBL
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long id;
	
	@Id
	@Column(name="HOMO_DCVSA" , length = 5)
	private Integer homoDcvsa;

	@Column(name="DES_CIUD" , length = 50)
	private String desCiud;

	@Id
	@Column(name="ACC_TIPPAIS_TBL_ID" , length = 4)
	private Long accTippaisTblId;
	
	@Id
	@Column(name="ACC_TIPDEP_TBL_ID")
	private Long accTipdepTblId;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@Id
	@JoinColumns({
		@JoinColumn(name="acc_tipdep_par_tbl_cod_dep" , referencedColumnName = "cod_dep") , 
		@JoinColumn(name="ACC_TIPDEP_PAR_TBL_COD_PAIS" , referencedColumnName = "ACC_TIPPAIS_PAR_TBL_COD_PAIS")
		})
	private TipDepParEntity acctipdeppartblcoddep;*/


}
