package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
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
@Table(name="OPL_PAR_ENDPOINT_DOWN_TBL")
public class OplParEndpoint  implements Serializable{
	
private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="COD_RUTA")
	private Long codRuta;
	
	@Column(name="DESC_RUTA")
	private String descRuta;
	
}
