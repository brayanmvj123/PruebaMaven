package com.bdb.opaloshare.persistence.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ParametersConnectionFTPS {

	private String username;

	private String password;
	
	private String hostname;
	
	private Integer puerto;

	private String ruta;

}
