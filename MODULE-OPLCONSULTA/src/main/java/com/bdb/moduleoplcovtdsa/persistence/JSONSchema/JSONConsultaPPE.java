package com.bdb.moduleoplcovtdsa.persistence.JSONSchema;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONConsultaPPE {

	@NotNull
	@NotEmpty
	private String canal;
	
	@Valid
	@NotNull
	private ParametrosJSONPPE parametros;
}
