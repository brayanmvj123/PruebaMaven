package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_PAR_TXT_MEDIUM_TBL")
public class TxtMediumTbl {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_TXT")
	private Integer idTxt;
	
	@Lob
	@Column(name="TXT_DESC")
	private String txtDesc;

}
