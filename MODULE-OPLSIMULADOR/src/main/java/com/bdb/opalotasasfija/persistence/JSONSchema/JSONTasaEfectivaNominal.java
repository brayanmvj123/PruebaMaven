package com.bdb.opalotasasfija.persistence.JSONSchema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
public class JSONTasaEfectivaNominal {

	@NotNull
    @Valid
	private String canal;
	
	@NotNull
    @Valid
	private ParametrosJSONTasaEfectivaNominal parametros;

}
