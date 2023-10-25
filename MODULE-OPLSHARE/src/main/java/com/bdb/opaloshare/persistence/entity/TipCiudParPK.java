package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TipCiudParPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long accTippaisTblId;
	
	private Long accTipdepTblId;
	//private String paisEntity;
	//private TipDepParPK acctipdeppartblcoddep;
	
	

	/*public TipDepParPK getAcctipdeppartblcoddep() {
		return acctipdeppartblcoddep;
	}

	public void setAcctipdeppartblcoddep(TipDepParPK acctipdeppartblcoddep) {
		this.acctipdeppartblcoddep = acctipdeppartblcoddep;
	}
	
	public TipCiudParPK(Integer codCiu, TipDepParPK acctipdeppartblcoddep) {
		super();
		this.codCiu = codCiu;
		this.acctipdeppartblcoddep = acctipdeppartblcoddep;
	}*/


	
}
