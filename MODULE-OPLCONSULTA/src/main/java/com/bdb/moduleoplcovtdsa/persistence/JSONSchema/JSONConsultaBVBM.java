package com.bdb.moduleoplcovtdsa.persistence.JSONSchema;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONConsultaBVBM {

	@NotNull
	@ApiModelProperty(notes = "Aplicación la cual consume el servicio", required = true)
	private String canal;
	
	@Valid
	@NotNull
	@ApiModelProperty(notes = "Contiene los parametros necesarios para realizar la consulta",
			required = true)
	private ParametrosJSONBVBM parametros;
}
